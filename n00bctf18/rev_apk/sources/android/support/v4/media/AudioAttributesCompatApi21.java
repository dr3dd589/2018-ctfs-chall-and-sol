package android.support.v4.media;

import android.media.AudioAttributes;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import java.lang.reflect.Method;

@RequiresApi(21)
class AudioAttributesCompatApi21 {
    private static final String TAG = "AudioAttributesCompat";
    private static Method sAudioAttributesToLegacyStreamType;

    static final class Wrapper {
        private AudioAttributes mWrapped;

        private Wrapper(AudioAttributes obj) {
            this.mWrapped = obj;
        }

        public static Wrapper wrap(@NonNull AudioAttributes obj) {
            if (obj != null) {
                return new Wrapper(obj);
            }
            throw new IllegalArgumentException("AudioAttributesApi21.Wrapper cannot wrap null");
        }

        public AudioAttributes unwrap() {
            return this.mWrapped;
        }
    }

    AudioAttributesCompatApi21() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:7:0x002d A:{ExcHandler: java.lang.NoSuchMethodException (r1_5 'e' java.lang.Exception), Splitter: B:1:0x0004} */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x002d A:{ExcHandler: java.lang.NoSuchMethodException (r1_5 'e' java.lang.Exception), Splitter: B:1:0x0004} */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x002d A:{ExcHandler: java.lang.NoSuchMethodException (r1_5 'e' java.lang.Exception), Splitter: B:1:0x0004} */
    /* JADX WARNING: Missing block: B:7:0x002d, code:
            r1 = move-exception;
     */
    /* JADX WARNING: Missing block: B:8:0x002e, code:
            android.util.Log.w(TAG, "getLegacyStreamType() failed on API21+", r1);
     */
    /* JADX WARNING: Missing block: B:9:0x0036, code:
            return -1;
     */
    public static int toLegacyStreamType(android.support.v4.media.AudioAttributesCompatApi21.Wrapper r7) {
        /*
        r0 = r7.unwrap();
        r1 = sAudioAttributesToLegacyStreamType;	 Catch:{ NoSuchMethodException -> 0x002d, NoSuchMethodException -> 0x002d, NoSuchMethodException -> 0x002d, NoSuchMethodException -> 0x002d }
        r2 = 0;
        r3 = 1;
        if (r1 != 0) goto L_0x001a;
    L_0x000a:
        r1 = android.media.AudioAttributes.class;
        r4 = "toLegacyStreamType";
        r5 = new java.lang.Class[r3];	 Catch:{ NoSuchMethodException -> 0x002d, NoSuchMethodException -> 0x002d, NoSuchMethodException -> 0x002d, NoSuchMethodException -> 0x002d }
        r6 = android.media.AudioAttributes.class;
        r5[r2] = r6;	 Catch:{ NoSuchMethodException -> 0x002d, NoSuchMethodException -> 0x002d, NoSuchMethodException -> 0x002d, NoSuchMethodException -> 0x002d }
        r1 = r1.getMethod(r4, r5);	 Catch:{ NoSuchMethodException -> 0x002d, NoSuchMethodException -> 0x002d, NoSuchMethodException -> 0x002d, NoSuchMethodException -> 0x002d }
        sAudioAttributesToLegacyStreamType = r1;	 Catch:{ NoSuchMethodException -> 0x002d, NoSuchMethodException -> 0x002d, NoSuchMethodException -> 0x002d, NoSuchMethodException -> 0x002d }
    L_0x001a:
        r1 = sAudioAttributesToLegacyStreamType;	 Catch:{ NoSuchMethodException -> 0x002d, NoSuchMethodException -> 0x002d, NoSuchMethodException -> 0x002d, NoSuchMethodException -> 0x002d }
        r4 = 0;
        r3 = new java.lang.Object[r3];	 Catch:{ NoSuchMethodException -> 0x002d, NoSuchMethodException -> 0x002d, NoSuchMethodException -> 0x002d, NoSuchMethodException -> 0x002d }
        r3[r2] = r0;	 Catch:{ NoSuchMethodException -> 0x002d, NoSuchMethodException -> 0x002d, NoSuchMethodException -> 0x002d, NoSuchMethodException -> 0x002d }
        r1 = r1.invoke(r4, r3);	 Catch:{ NoSuchMethodException -> 0x002d, NoSuchMethodException -> 0x002d, NoSuchMethodException -> 0x002d, NoSuchMethodException -> 0x002d }
        r2 = r1;
        r2 = (java.lang.Integer) r2;	 Catch:{ NoSuchMethodException -> 0x002d, NoSuchMethodException -> 0x002d, NoSuchMethodException -> 0x002d, NoSuchMethodException -> 0x002d }
        r2 = r2.intValue();	 Catch:{ NoSuchMethodException -> 0x002d, NoSuchMethodException -> 0x002d, NoSuchMethodException -> 0x002d, NoSuchMethodException -> 0x002d }
        return r2;
    L_0x002d:
        r1 = move-exception;
        r2 = "AudioAttributesCompat";
        r3 = "getLegacyStreamType() failed on API21+";
        android.util.Log.w(r2, r3, r1);
        r2 = -1;
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.AudioAttributesCompatApi21.toLegacyStreamType(android.support.v4.media.AudioAttributesCompatApi21$Wrapper):int");
    }
}
