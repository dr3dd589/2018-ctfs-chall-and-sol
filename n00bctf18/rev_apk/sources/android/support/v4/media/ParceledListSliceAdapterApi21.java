package android.support.v4.media;

import android.support.annotation.RequiresApi;
import java.lang.reflect.Constructor;

@RequiresApi(21)
class ParceledListSliceAdapterApi21 {
    private static Constructor sConstructor;

    ParceledListSliceAdapterApi21() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:2:0x0015 A:{ExcHandler: java.lang.ClassNotFoundException (r0_2 'e' java.lang.ReflectiveOperationException), Splitter: B:0:0x0000} */
    /* JADX WARNING: Missing block: B:2:0x0015, code:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:3:0x0016, code:
            r0.printStackTrace();
     */
    /* JADX WARNING: Missing block: B:4:?, code:
            return;
     */
    static {
        /*
        r0 = "android.content.pm.ParceledListSlice";
        r0 = java.lang.Class.forName(r0);	 Catch:{ ClassNotFoundException -> 0x0015, ClassNotFoundException -> 0x0015 }
        r1 = 1;
        r1 = new java.lang.Class[r1];	 Catch:{ ClassNotFoundException -> 0x0015, ClassNotFoundException -> 0x0015 }
        r2 = 0;
        r3 = java.util.List.class;
        r1[r2] = r3;	 Catch:{ ClassNotFoundException -> 0x0015, ClassNotFoundException -> 0x0015 }
        r1 = r0.getConstructor(r1);	 Catch:{ ClassNotFoundException -> 0x0015, ClassNotFoundException -> 0x0015 }
        sConstructor = r1;	 Catch:{ ClassNotFoundException -> 0x0015, ClassNotFoundException -> 0x0015 }
        goto L_0x0019;
    L_0x0015:
        r0 = move-exception;
        r0.printStackTrace();
    L_0x0019:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.ParceledListSliceAdapterApi21.<clinit>():void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:4:0x000f A:{ExcHandler: java.lang.InstantiationException (r1_2 'e' java.lang.ReflectiveOperationException), Splitter: B:1:0x0001} */
    /* JADX WARNING: Removed duplicated region for block: B:4:0x000f A:{ExcHandler: java.lang.InstantiationException (r1_2 'e' java.lang.ReflectiveOperationException), Splitter: B:1:0x0001} */
    /* JADX WARNING: Missing block: B:4:0x000f, code:
            r1 = move-exception;
     */
    /* JADX WARNING: Missing block: B:5:0x0010, code:
            r1.printStackTrace();
     */
    /* JADX WARNING: Missing block: B:6:?, code:
            return null;
     */
    static java.lang.Object newInstance(java.util.List<android.media.browse.MediaBrowser.MediaItem> r4) {
        /*
        r0 = 0;
        r1 = sConstructor;	 Catch:{ InstantiationException -> 0x000f, InstantiationException -> 0x000f, InstantiationException -> 0x000f }
        r2 = 1;
        r2 = new java.lang.Object[r2];	 Catch:{ InstantiationException -> 0x000f, InstantiationException -> 0x000f, InstantiationException -> 0x000f }
        r3 = 0;
        r2[r3] = r4;	 Catch:{ InstantiationException -> 0x000f, InstantiationException -> 0x000f, InstantiationException -> 0x000f }
        r1 = r1.newInstance(r2);	 Catch:{ InstantiationException -> 0x000f, InstantiationException -> 0x000f, InstantiationException -> 0x000f }
        r0 = r1;
        goto L_0x0013;
    L_0x000f:
        r1 = move-exception;
        r1.printStackTrace();
    L_0x0013:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.ParceledListSliceAdapterApi21.newInstance(java.util.List):java.lang.Object");
    }
}
