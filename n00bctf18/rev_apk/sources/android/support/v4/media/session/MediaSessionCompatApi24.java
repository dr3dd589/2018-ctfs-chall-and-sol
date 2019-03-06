package android.support.v4.media.session;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.RequiresApi;

@RequiresApi(24)
class MediaSessionCompatApi24 {
    private static final String TAG = "MediaSessionCompatApi24";

    public interface Callback extends android.support.v4.media.session.MediaSessionCompatApi23.Callback {
        void onPrepare();

        void onPrepareFromMediaId(String str, Bundle bundle);

        void onPrepareFromSearch(String str, Bundle bundle);

        void onPrepareFromUri(Uri uri, Bundle bundle);
    }

    static class CallbackProxy<T extends Callback> extends CallbackProxy<T> {
        public CallbackProxy(T callback) {
            super(callback);
        }

        public void onPrepare() {
            ((Callback) this.mCallback).onPrepare();
        }

        public void onPrepareFromMediaId(String mediaId, Bundle extras) {
            ((Callback) this.mCallback).onPrepareFromMediaId(mediaId, extras);
        }

        public void onPrepareFromSearch(String query, Bundle extras) {
            ((Callback) this.mCallback).onPrepareFromSearch(query, extras);
        }

        public void onPrepareFromUri(Uri uri, Bundle extras) {
            ((Callback) this.mCallback).onPrepareFromUri(uri, extras);
        }
    }

    MediaSessionCompatApi24() {
    }

    public static Object createCallback(Callback callback) {
        return new CallbackProxy(callback);
    }

    /* JADX WARNING: Removed duplicated region for block: B:4:0x0019 A:{ExcHandler: java.lang.NoSuchMethodException (r1_2 'e' java.lang.ReflectiveOperationException), Splitter: B:1:0x0003} */
    /* JADX WARNING: Removed duplicated region for block: B:4:0x0019 A:{ExcHandler: java.lang.NoSuchMethodException (r1_2 'e' java.lang.ReflectiveOperationException), Splitter: B:1:0x0003} */
    /* JADX WARNING: Missing block: B:4:0x0019, code:
            r1 = move-exception;
     */
    /* JADX WARNING: Missing block: B:5:0x001a, code:
            android.util.Log.e(TAG, "Cannot execute MediaSession.getCallingPackage()", r1);
     */
    /* JADX WARNING: Missing block: B:6:0x0022, code:
            return null;
     */
    public static java.lang.String getCallingPackage(java.lang.Object r5) {
        /*
        r0 = r5;
        r0 = (android.media.session.MediaSession) r0;
        r1 = r0.getClass();	 Catch:{ NoSuchMethodException -> 0x0019, NoSuchMethodException -> 0x0019, NoSuchMethodException -> 0x0019 }
        r2 = "getCallingPackage";
        r3 = 0;
        r4 = new java.lang.Class[r3];	 Catch:{ NoSuchMethodException -> 0x0019, NoSuchMethodException -> 0x0019, NoSuchMethodException -> 0x0019 }
        r1 = r1.getMethod(r2, r4);	 Catch:{ NoSuchMethodException -> 0x0019, NoSuchMethodException -> 0x0019, NoSuchMethodException -> 0x0019 }
        r2 = new java.lang.Object[r3];	 Catch:{ NoSuchMethodException -> 0x0019, NoSuchMethodException -> 0x0019, NoSuchMethodException -> 0x0019 }
        r2 = r1.invoke(r0, r2);	 Catch:{ NoSuchMethodException -> 0x0019, NoSuchMethodException -> 0x0019, NoSuchMethodException -> 0x0019 }
        r2 = (java.lang.String) r2;	 Catch:{ NoSuchMethodException -> 0x0019, NoSuchMethodException -> 0x0019, NoSuchMethodException -> 0x0019 }
        return r2;
    L_0x0019:
        r1 = move-exception;
        r2 = "MediaSessionCompatApi24";
        r3 = "Cannot execute MediaSession.getCallingPackage()";
        android.util.Log.e(r2, r3, r1);
        r1 = 0;
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.session.MediaSessionCompatApi24.getCallingPackage(java.lang.Object):java.lang.String");
    }
}
