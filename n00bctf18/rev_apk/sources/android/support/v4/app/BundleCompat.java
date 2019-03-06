package android.support.v4.app;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.IBinder;
import java.lang.reflect.Method;

public final class BundleCompat {

    static class BundleCompatBaseImpl {
        private static final String TAG = "BundleCompatBaseImpl";
        private static Method sGetIBinderMethod;
        private static boolean sGetIBinderMethodFetched;
        private static Method sPutIBinderMethod;
        private static boolean sPutIBinderMethodFetched;

        BundleCompatBaseImpl() {
        }

        /* JADX WARNING: Removed duplicated region for block: B:12:0x0038 A:{ExcHandler: java.lang.reflect.InvocationTargetException (r0_9 'e' java.lang.Exception), Splitter: B:9:0x002b} */
        /* JADX WARNING: Removed duplicated region for block: B:12:0x0038 A:{ExcHandler: java.lang.reflect.InvocationTargetException (r0_9 'e' java.lang.Exception), Splitter: B:9:0x002b} */
        /* JADX WARNING: Missing block: B:12:0x0038, code:
            r0 = move-exception;
     */
        /* JADX WARNING: Missing block: B:13:0x0039, code:
            android.util.Log.i(TAG, "Failed to invoke getIBinder via reflection", r0);
            sGetIBinderMethod = null;
     */
        public static android.os.IBinder getBinder(android.os.Bundle r6, java.lang.String r7) {
            /*
            r0 = sGetIBinderMethodFetched;
            r1 = 0;
            r2 = 1;
            if (r0 != 0) goto L_0x0026;
        L_0x0006:
            r0 = android.os.Bundle.class;
            r3 = "getIBinder";
            r4 = new java.lang.Class[r2];	 Catch:{ NoSuchMethodException -> 0x001c }
            r5 = java.lang.String.class;
            r4[r1] = r5;	 Catch:{ NoSuchMethodException -> 0x001c }
            r0 = r0.getMethod(r3, r4);	 Catch:{ NoSuchMethodException -> 0x001c }
            sGetIBinderMethod = r0;	 Catch:{ NoSuchMethodException -> 0x001c }
            r0 = sGetIBinderMethod;	 Catch:{ NoSuchMethodException -> 0x001c }
            r0.setAccessible(r2);	 Catch:{ NoSuchMethodException -> 0x001c }
            goto L_0x0024;
        L_0x001c:
            r0 = move-exception;
            r3 = "BundleCompatBaseImpl";
            r4 = "Failed to retrieve getIBinder method";
            android.util.Log.i(r3, r4, r0);
        L_0x0024:
            sGetIBinderMethodFetched = r2;
        L_0x0026:
            r0 = sGetIBinderMethod;
            r3 = 0;
            if (r0 == 0) goto L_0x0042;
        L_0x002b:
            r0 = sGetIBinderMethod;	 Catch:{ InvocationTargetException -> 0x0038, InvocationTargetException -> 0x0038, InvocationTargetException -> 0x0038 }
            r2 = new java.lang.Object[r2];	 Catch:{ InvocationTargetException -> 0x0038, InvocationTargetException -> 0x0038, InvocationTargetException -> 0x0038 }
            r2[r1] = r7;	 Catch:{ InvocationTargetException -> 0x0038, InvocationTargetException -> 0x0038, InvocationTargetException -> 0x0038 }
            r0 = r0.invoke(r6, r2);	 Catch:{ InvocationTargetException -> 0x0038, InvocationTargetException -> 0x0038, InvocationTargetException -> 0x0038 }
            r0 = (android.os.IBinder) r0;	 Catch:{ InvocationTargetException -> 0x0038, InvocationTargetException -> 0x0038, InvocationTargetException -> 0x0038 }
            return r0;
        L_0x0038:
            r0 = move-exception;
            r1 = "BundleCompatBaseImpl";
            r2 = "Failed to invoke getIBinder via reflection";
            android.util.Log.i(r1, r2, r0);
            sGetIBinderMethod = r3;
        L_0x0042:
            return r3;
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.BundleCompat.BundleCompatBaseImpl.getBinder(android.os.Bundle, java.lang.String):android.os.IBinder");
        }

        /* JADX WARNING: Removed duplicated region for block: B:11:0x003b A:{ExcHandler: java.lang.reflect.InvocationTargetException (r0_7 'e' java.lang.Exception), Splitter: B:9:0x002f} */
        /* JADX WARNING: Removed duplicated region for block: B:11:0x003b A:{ExcHandler: java.lang.reflect.InvocationTargetException (r0_7 'e' java.lang.Exception), Splitter: B:9:0x002f} */
        /* JADX WARNING: Missing block: B:11:0x003b, code:
            r0 = move-exception;
     */
        /* JADX WARNING: Missing block: B:12:0x003c, code:
            android.util.Log.i(TAG, "Failed to invoke putIBinder via reflection", r0);
            sPutIBinderMethod = null;
     */
        /* JADX WARNING: Missing block: B:13:?, code:
            return;
     */
        public static void putBinder(android.os.Bundle r7, java.lang.String r8, android.os.IBinder r9) {
            /*
            r0 = sPutIBinderMethodFetched;
            r1 = 0;
            r2 = 2;
            r3 = 1;
            if (r0 != 0) goto L_0x002b;
        L_0x0007:
            r0 = android.os.Bundle.class;
            r4 = "putIBinder";
            r5 = new java.lang.Class[r2];	 Catch:{ NoSuchMethodException -> 0x0021 }
            r6 = java.lang.String.class;
            r5[r1] = r6;	 Catch:{ NoSuchMethodException -> 0x0021 }
            r6 = android.os.IBinder.class;
            r5[r3] = r6;	 Catch:{ NoSuchMethodException -> 0x0021 }
            r0 = r0.getMethod(r4, r5);	 Catch:{ NoSuchMethodException -> 0x0021 }
            sPutIBinderMethod = r0;	 Catch:{ NoSuchMethodException -> 0x0021 }
            r0 = sPutIBinderMethod;	 Catch:{ NoSuchMethodException -> 0x0021 }
            r0.setAccessible(r3);	 Catch:{ NoSuchMethodException -> 0x0021 }
            goto L_0x0029;
        L_0x0021:
            r0 = move-exception;
            r4 = "BundleCompatBaseImpl";
            r5 = "Failed to retrieve putIBinder method";
            android.util.Log.i(r4, r5, r0);
        L_0x0029:
            sPutIBinderMethodFetched = r3;
        L_0x002b:
            r0 = sPutIBinderMethod;
            if (r0 == 0) goto L_0x0046;
        L_0x002f:
            r0 = sPutIBinderMethod;	 Catch:{ InvocationTargetException -> 0x003b, InvocationTargetException -> 0x003b, InvocationTargetException -> 0x003b }
            r2 = new java.lang.Object[r2];	 Catch:{ InvocationTargetException -> 0x003b, InvocationTargetException -> 0x003b, InvocationTargetException -> 0x003b }
            r2[r1] = r8;	 Catch:{ InvocationTargetException -> 0x003b, InvocationTargetException -> 0x003b, InvocationTargetException -> 0x003b }
            r2[r3] = r9;	 Catch:{ InvocationTargetException -> 0x003b, InvocationTargetException -> 0x003b, InvocationTargetException -> 0x003b }
            r0.invoke(r7, r2);	 Catch:{ InvocationTargetException -> 0x003b, InvocationTargetException -> 0x003b, InvocationTargetException -> 0x003b }
            goto L_0x0046;
        L_0x003b:
            r0 = move-exception;
            r1 = "BundleCompatBaseImpl";
            r2 = "Failed to invoke putIBinder via reflection";
            android.util.Log.i(r1, r2, r0);
            r1 = 0;
            sPutIBinderMethod = r1;
        L_0x0046:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.BundleCompat.BundleCompatBaseImpl.putBinder(android.os.Bundle, java.lang.String, android.os.IBinder):void");
        }
    }

    private BundleCompat() {
    }

    public static IBinder getBinder(Bundle bundle, String key) {
        if (VERSION.SDK_INT >= 18) {
            return bundle.getBinder(key);
        }
        return BundleCompatBaseImpl.getBinder(bundle, key);
    }

    public static void putBinder(Bundle bundle, String key, IBinder binder) {
        if (VERSION.SDK_INT >= 18) {
            bundle.putBinder(key, binder);
        } else {
            BundleCompatBaseImpl.putBinder(bundle, key, binder);
        }
    }
}
