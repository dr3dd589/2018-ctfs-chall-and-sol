package android.support.v4.app;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.provider.Settings.Secure;
import android.support.annotation.GuardedBy;
import android.support.v4.app.INotificationSideChannel.Stub;
import android.util.Log;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class NotificationManagerCompat {
    public static final String ACTION_BIND_SIDE_CHANNEL = "android.support.BIND_NOTIFICATION_SIDE_CHANNEL";
    private static final String CHECK_OP_NO_THROW = "checkOpNoThrow";
    public static final String EXTRA_USE_SIDE_CHANNEL = "android.support.useSideChannel";
    public static final int IMPORTANCE_DEFAULT = 3;
    public static final int IMPORTANCE_HIGH = 4;
    public static final int IMPORTANCE_LOW = 2;
    public static final int IMPORTANCE_MAX = 5;
    public static final int IMPORTANCE_MIN = 1;
    public static final int IMPORTANCE_NONE = 0;
    public static final int IMPORTANCE_UNSPECIFIED = -1000;
    static final int MAX_SIDE_CHANNEL_SDK_VERSION = 19;
    private static final String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";
    private static final String SETTING_ENABLED_NOTIFICATION_LISTENERS = "enabled_notification_listeners";
    private static final int SIDE_CHANNEL_RETRY_BASE_INTERVAL_MS = 1000;
    private static final int SIDE_CHANNEL_RETRY_MAX_COUNT = 6;
    private static final String TAG = "NotifManCompat";
    @GuardedBy("sEnabledNotificationListenersLock")
    private static Set<String> sEnabledNotificationListenerPackages = new HashSet();
    @GuardedBy("sEnabledNotificationListenersLock")
    private static String sEnabledNotificationListeners;
    private static final Object sEnabledNotificationListenersLock = new Object();
    private static final Object sLock = new Object();
    @GuardedBy("sLock")
    private static SideChannelManager sSideChannelManager;
    private final Context mContext;
    private final NotificationManager mNotificationManager = ((NotificationManager) this.mContext.getSystemService("notification"));

    private static class ServiceConnectedEvent {
        final ComponentName componentName;
        final IBinder iBinder;

        ServiceConnectedEvent(ComponentName componentName, IBinder iBinder) {
            this.componentName = componentName;
            this.iBinder = iBinder;
        }
    }

    private static class SideChannelManager implements Callback, ServiceConnection {
        private static final int MSG_QUEUE_TASK = 0;
        private static final int MSG_RETRY_LISTENER_QUEUE = 3;
        private static final int MSG_SERVICE_CONNECTED = 1;
        private static final int MSG_SERVICE_DISCONNECTED = 2;
        private Set<String> mCachedEnabledPackages = new HashSet();
        private final Context mContext;
        private final Handler mHandler;
        private final HandlerThread mHandlerThread;
        private final Map<ComponentName, ListenerRecord> mRecordMap = new HashMap();

        private static class ListenerRecord {
            public boolean bound = false;
            public final ComponentName componentName;
            public int retryCount = 0;
            public INotificationSideChannel service;
            public LinkedList<Task> taskQueue = new LinkedList();

            public ListenerRecord(ComponentName componentName) {
                this.componentName = componentName;
            }
        }

        public SideChannelManager(Context context) {
            this.mContext = context;
            this.mHandlerThread = new HandlerThread("NotificationManagerCompat");
            this.mHandlerThread.start();
            this.mHandler = new Handler(this.mHandlerThread.getLooper(), this);
        }

        public void queueTask(Task task) {
            this.mHandler.obtainMessage(0, task).sendToTarget();
        }

        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    handleQueueTask((Task) msg.obj);
                    return true;
                case 1:
                    ServiceConnectedEvent event = msg.obj;
                    handleServiceConnected(event.componentName, event.iBinder);
                    return true;
                case 2:
                    handleServiceDisconnected((ComponentName) msg.obj);
                    return true;
                case 3:
                    handleRetryListenerQueue((ComponentName) msg.obj);
                    return true;
                default:
                    return false;
            }
        }

        private void handleQueueTask(Task task) {
            updateListenerMap();
            for (ListenerRecord record : this.mRecordMap.values()) {
                record.taskQueue.add(task);
                processListenerQueue(record);
            }
        }

        private void handleServiceConnected(ComponentName componentName, IBinder iBinder) {
            ListenerRecord record = (ListenerRecord) this.mRecordMap.get(componentName);
            if (record != null) {
                record.service = Stub.asInterface(iBinder);
                record.retryCount = 0;
                processListenerQueue(record);
            }
        }

        private void handleServiceDisconnected(ComponentName componentName) {
            ListenerRecord record = (ListenerRecord) this.mRecordMap.get(componentName);
            if (record != null) {
                ensureServiceUnbound(record);
            }
        }

        private void handleRetryListenerQueue(ComponentName componentName) {
            ListenerRecord record = (ListenerRecord) this.mRecordMap.get(componentName);
            if (record != null) {
                processListenerQueue(record);
            }
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                String str = NotificationManagerCompat.TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Connected to service ");
                stringBuilder.append(componentName);
                Log.d(str, stringBuilder.toString());
            }
            this.mHandler.obtainMessage(1, new ServiceConnectedEvent(componentName, iBinder)).sendToTarget();
        }

        public void onServiceDisconnected(ComponentName componentName) {
            if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                String str = NotificationManagerCompat.TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Disconnected from service ");
                stringBuilder.append(componentName);
                Log.d(str, stringBuilder.toString());
            }
            this.mHandler.obtainMessage(2, componentName).sendToTarget();
        }

        private void updateListenerMap() {
            Set<String> enabledPackages = NotificationManagerCompat.getEnabledListenerPackages(this.mContext);
            if (!enabledPackages.equals(this.mCachedEnabledPackages)) {
                String str;
                StringBuilder stringBuilder;
                this.mCachedEnabledPackages = enabledPackages;
                List<ResolveInfo> resolveInfos = this.mContext.getPackageManager().queryIntentServices(new Intent().setAction(NotificationManagerCompat.ACTION_BIND_SIDE_CHANNEL), 0);
                Set<ComponentName> enabledComponents = new HashSet();
                for (ResolveInfo resolveInfo : resolveInfos) {
                    if (enabledPackages.contains(resolveInfo.serviceInfo.packageName)) {
                        ComponentName componentName = new ComponentName(resolveInfo.serviceInfo.packageName, resolveInfo.serviceInfo.name);
                        if (resolveInfo.serviceInfo.permission != null) {
                            str = NotificationManagerCompat.TAG;
                            stringBuilder = new StringBuilder();
                            stringBuilder.append("Permission present on component ");
                            stringBuilder.append(componentName);
                            stringBuilder.append(", not adding listener record.");
                            Log.w(str, stringBuilder.toString());
                        } else {
                            enabledComponents.add(componentName);
                        }
                    }
                }
                for (ComponentName componentName2 : enabledComponents) {
                    if (!this.mRecordMap.containsKey(componentName2)) {
                        if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                            String str2 = NotificationManagerCompat.TAG;
                            StringBuilder stringBuilder2 = new StringBuilder();
                            stringBuilder2.append("Adding listener record for ");
                            stringBuilder2.append(componentName2);
                            Log.d(str2, stringBuilder2.toString());
                        }
                        this.mRecordMap.put(componentName2, new ListenerRecord(componentName2));
                    }
                }
                Iterator<Entry<ComponentName, ListenerRecord>> it = this.mRecordMap.entrySet().iterator();
                while (it.hasNext()) {
                    Entry<ComponentName, ListenerRecord> entry = (Entry) it.next();
                    if (!enabledComponents.contains(entry.getKey())) {
                        if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                            str = NotificationManagerCompat.TAG;
                            stringBuilder = new StringBuilder();
                            stringBuilder.append("Removing listener record for ");
                            stringBuilder.append(entry.getKey());
                            Log.d(str, stringBuilder.toString());
                        }
                        ensureServiceUnbound((ListenerRecord) entry.getValue());
                        it.remove();
                    }
                }
            }
        }

        private boolean ensureServiceBound(ListenerRecord record) {
            if (record.bound) {
                return true;
            }
            record.bound = this.mContext.bindService(new Intent(NotificationManagerCompat.ACTION_BIND_SIDE_CHANNEL).setComponent(record.componentName), this, 33);
            if (record.bound) {
                record.retryCount = 0;
            } else {
                String str = NotificationManagerCompat.TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Unable to bind to listener ");
                stringBuilder.append(record.componentName);
                Log.w(str, stringBuilder.toString());
                this.mContext.unbindService(this);
            }
            return record.bound;
        }

        private void ensureServiceUnbound(ListenerRecord record) {
            if (record.bound) {
                this.mContext.unbindService(this);
                record.bound = false;
            }
            record.service = null;
        }

        private void scheduleListenerRetry(ListenerRecord record) {
            if (!this.mHandler.hasMessages(3, record.componentName)) {
                record.retryCount++;
                if (record.retryCount > 6) {
                    String str = NotificationManagerCompat.TAG;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Giving up on delivering ");
                    stringBuilder.append(record.taskQueue.size());
                    stringBuilder.append(" tasks to ");
                    stringBuilder.append(record.componentName);
                    stringBuilder.append(" after ");
                    stringBuilder.append(record.retryCount);
                    stringBuilder.append(" retries");
                    Log.w(str, stringBuilder.toString());
                    record.taskQueue.clear();
                    return;
                }
                int delayMs = (1 << (record.retryCount - 1)) * 1000;
                if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                    String str2 = NotificationManagerCompat.TAG;
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("Scheduling retry for ");
                    stringBuilder2.append(delayMs);
                    stringBuilder2.append(" ms");
                    Log.d(str2, stringBuilder2.toString());
                }
                this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(3, record.componentName), (long) delayMs);
            }
        }

        private void processListenerQueue(ListenerRecord record) {
            if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                String str = NotificationManagerCompat.TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Processing component ");
                stringBuilder.append(record.componentName);
                stringBuilder.append(", ");
                stringBuilder.append(record.taskQueue.size());
                stringBuilder.append(" queued tasks");
                Log.d(str, stringBuilder.toString());
            }
            if (!record.taskQueue.isEmpty()) {
                if (!ensureServiceBound(record) || record.service == null) {
                    scheduleListenerRetry(record);
                    return;
                }
                while (true) {
                    Task task = (Task) record.taskQueue.peek();
                    if (task == null) {
                        break;
                    }
                    String str2;
                    StringBuilder stringBuilder2;
                    try {
                        if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                            str2 = NotificationManagerCompat.TAG;
                            stringBuilder2 = new StringBuilder();
                            stringBuilder2.append("Sending task ");
                            stringBuilder2.append(task);
                            Log.d(str2, stringBuilder2.toString());
                        }
                        task.send(record.service);
                        record.taskQueue.remove();
                    } catch (DeadObjectException e) {
                        if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                            String str3 = NotificationManagerCompat.TAG;
                            stringBuilder2 = new StringBuilder();
                            stringBuilder2.append("Remote service has died: ");
                            stringBuilder2.append(record.componentName);
                            Log.d(str3, stringBuilder2.toString());
                        }
                    } catch (RemoteException e2) {
                        str2 = NotificationManagerCompat.TAG;
                        stringBuilder2 = new StringBuilder();
                        stringBuilder2.append("RemoteException communicating with ");
                        stringBuilder2.append(record.componentName);
                        Log.w(str2, stringBuilder2.toString(), e2);
                    }
                }
                if (!record.taskQueue.isEmpty()) {
                    scheduleListenerRetry(record);
                }
            }
        }
    }

    private interface Task {
        void send(INotificationSideChannel iNotificationSideChannel) throws RemoteException;
    }

    private static class CancelTask implements Task {
        final boolean all;
        final int id;
        final String packageName;
        final String tag;

        CancelTask(String packageName) {
            this.packageName = packageName;
            this.id = 0;
            this.tag = null;
            this.all = true;
        }

        CancelTask(String packageName, int id, String tag) {
            this.packageName = packageName;
            this.id = id;
            this.tag = tag;
            this.all = false;
        }

        public void send(INotificationSideChannel service) throws RemoteException {
            if (this.all) {
                service.cancelAll(this.packageName);
            } else {
                service.cancel(this.packageName, this.id, this.tag);
            }
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("CancelTask[");
            sb.append("packageName:");
            sb.append(this.packageName);
            sb.append(", id:");
            sb.append(this.id);
            sb.append(", tag:");
            sb.append(this.tag);
            sb.append(", all:");
            sb.append(this.all);
            sb.append("]");
            return sb.toString();
        }
    }

    private static class NotifyTask implements Task {
        final int id;
        final Notification notif;
        final String packageName;
        final String tag;

        NotifyTask(String packageName, int id, String tag, Notification notif) {
            this.packageName = packageName;
            this.id = id;
            this.tag = tag;
            this.notif = notif;
        }

        public void send(INotificationSideChannel service) throws RemoteException {
            service.notify(this.packageName, this.id, this.tag, this.notif);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("NotifyTask[");
            sb.append("packageName:");
            sb.append(this.packageName);
            sb.append(", id:");
            sb.append(this.id);
            sb.append(", tag:");
            sb.append(this.tag);
            sb.append("]");
            return sb.toString();
        }
    }

    public static NotificationManagerCompat from(Context context) {
        return new NotificationManagerCompat(context);
    }

    private NotificationManagerCompat(Context context) {
        this.mContext = context;
    }

    public void cancel(int id) {
        cancel(null, id);
    }

    public void cancel(String tag, int id) {
        this.mNotificationManager.cancel(tag, id);
        if (VERSION.SDK_INT <= 19) {
            pushSideChannelQueue(new CancelTask(this.mContext.getPackageName(), id, tag));
        }
    }

    public void cancelAll() {
        this.mNotificationManager.cancelAll();
        if (VERSION.SDK_INT <= 19) {
            pushSideChannelQueue(new CancelTask(this.mContext.getPackageName()));
        }
    }

    public void notify(int id, Notification notification) {
        notify(null, id, notification);
    }

    public void notify(String tag, int id, Notification notification) {
        if (useSideChannelForNotification(notification)) {
            pushSideChannelQueue(new NotifyTask(this.mContext.getPackageName(), id, tag, notification));
            this.mNotificationManager.cancel(tag, id);
            return;
        }
        this.mNotificationManager.notify(tag, id, notification);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0082 A:{ExcHandler: java.lang.ClassNotFoundException (e java.lang.ClassNotFoundException), Splitter: B:7:0x0030} */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0082 A:{ExcHandler: java.lang.ClassNotFoundException (e java.lang.ClassNotFoundException), Splitter: B:7:0x0030} */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0082 A:{ExcHandler: java.lang.ClassNotFoundException (e java.lang.ClassNotFoundException), Splitter: B:7:0x0030} */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0082 A:{ExcHandler: java.lang.ClassNotFoundException (e java.lang.ClassNotFoundException), Splitter: B:7:0x0030} */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0082 A:{ExcHandler: java.lang.ClassNotFoundException (e java.lang.ClassNotFoundException), Splitter: B:7:0x0030} */
    /* JADX WARNING: Missing block: B:13:0x0083, code:
            return true;
     */
    public boolean areNotificationsEnabled() {
        /*
        r13 = this;
        r0 = android.os.Build.VERSION.SDK_INT;
        r1 = 24;
        if (r0 < r1) goto L_0x000d;
    L_0x0006:
        r0 = r13.mNotificationManager;
        r0 = r0.areNotificationsEnabled();
        return r0;
    L_0x000d:
        r0 = android.os.Build.VERSION.SDK_INT;
        r1 = 19;
        r2 = 1;
        if (r0 < r1) goto L_0x0084;
    L_0x0014:
        r0 = r13.mContext;
        r1 = "appops";
        r0 = r0.getSystemService(r1);
        r0 = (android.app.AppOpsManager) r0;
        r1 = r13.mContext;
        r1 = r1.getApplicationInfo();
        r3 = r13.mContext;
        r3 = r3.getApplicationContext();
        r3 = r3.getPackageName();
        r4 = r1.uid;
        r5 = android.app.AppOpsManager.class;
        r5 = r5.getName();	 Catch:{ ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082 }
        r5 = java.lang.Class.forName(r5);	 Catch:{ ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082 }
        r6 = "checkOpNoThrow";
        r7 = 3;
        r8 = new java.lang.Class[r7];	 Catch:{ ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082 }
        r9 = java.lang.Integer.TYPE;	 Catch:{ ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082 }
        r10 = 0;
        r8[r10] = r9;	 Catch:{ ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082 }
        r9 = java.lang.Integer.TYPE;	 Catch:{ ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082 }
        r8[r2] = r9;	 Catch:{ ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082 }
        r9 = java.lang.String.class;
        r11 = 2;
        r8[r11] = r9;	 Catch:{ ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082 }
        r6 = r5.getMethod(r6, r8);	 Catch:{ ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082 }
        r8 = "OP_POST_NOTIFICATION";
        r8 = r5.getDeclaredField(r8);	 Catch:{ ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082 }
        r9 = java.lang.Integer.class;
        r9 = r8.get(r9);	 Catch:{ ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082 }
        r9 = (java.lang.Integer) r9;	 Catch:{ ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082 }
        r9 = r9.intValue();	 Catch:{ ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082 }
        r7 = new java.lang.Object[r7];	 Catch:{ ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082 }
        r12 = java.lang.Integer.valueOf(r9);	 Catch:{ ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082 }
        r7[r10] = r12;	 Catch:{ ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082 }
        r12 = java.lang.Integer.valueOf(r4);	 Catch:{ ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082 }
        r7[r2] = r12;	 Catch:{ ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082 }
        r7[r11] = r3;	 Catch:{ ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082 }
        r7 = r6.invoke(r0, r7);	 Catch:{ ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082 }
        r7 = (java.lang.Integer) r7;	 Catch:{ ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082 }
        r7 = r7.intValue();	 Catch:{ ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082, ClassNotFoundException -> 0x0082 }
        if (r7 != 0) goto L_0x0080;
    L_0x007f:
        goto L_0x0081;
    L_0x0080:
        r2 = 0;
    L_0x0081:
        return r2;
    L_0x0082:
        r5 = move-exception;
        return r2;
    L_0x0084:
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.NotificationManagerCompat.areNotificationsEnabled():boolean");
    }

    public int getImportance() {
        if (VERSION.SDK_INT >= 24) {
            return this.mNotificationManager.getImportance();
        }
        return IMPORTANCE_UNSPECIFIED;
    }

    public static Set<String> getEnabledListenerPackages(Context context) {
        String enabledNotificationListeners = Secure.getString(context.getContentResolver(), SETTING_ENABLED_NOTIFICATION_LISTENERS);
        synchronized (sEnabledNotificationListenersLock) {
            if (enabledNotificationListeners != null) {
                if (!enabledNotificationListeners.equals(sEnabledNotificationListeners)) {
                    String[] components = enabledNotificationListeners.split(":");
                    Set<String> packageNames = new HashSet(components.length);
                    for (String component : components) {
                        ComponentName componentName = ComponentName.unflattenFromString(component);
                        if (componentName != null) {
                            packageNames.add(componentName.getPackageName());
                        }
                    }
                    sEnabledNotificationListenerPackages = packageNames;
                    sEnabledNotificationListeners = enabledNotificationListeners;
                }
            }
        }
        return sEnabledNotificationListenerPackages;
    }

    private static boolean useSideChannelForNotification(Notification notification) {
        Bundle extras = NotificationCompat.getExtras(notification);
        return extras != null && extras.getBoolean(EXTRA_USE_SIDE_CHANNEL);
    }

    private void pushSideChannelQueue(Task task) {
        synchronized (sLock) {
            if (sSideChannelManager == null) {
                sSideChannelManager = new SideChannelManager(this.mContext.getApplicationContext());
            }
            sSideChannelManager.queueTask(task);
        }
    }
}
