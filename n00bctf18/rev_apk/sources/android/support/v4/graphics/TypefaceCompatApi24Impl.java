package android.support.v4.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.CancellationSignal;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.content.res.FontResourcesParserCompat.FontFamilyFilesResourceEntry;
import android.support.v4.content.res.FontResourcesParserCompat.FontFileResourceEntry;
import android.support.v4.provider.FontsContractCompat.FontInfo;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;

@RequiresApi(24)
@RestrictTo({Scope.LIBRARY_GROUP})
class TypefaceCompatApi24Impl extends TypefaceCompatBaseImpl {
    private static final String ADD_FONT_WEIGHT_STYLE_METHOD = "addFontWeightStyle";
    private static final String CREATE_FROM_FAMILIES_WITH_DEFAULT_METHOD = "createFromFamiliesWithDefault";
    private static final String FONT_FAMILY_CLASS = "android.graphics.FontFamily";
    private static final String TAG = "TypefaceCompatApi24Impl";
    private static final Method sAddFontWeightStyle;
    private static final Method sCreateFromFamiliesWithDefault;
    private static final Class sFontFamily;
    private static final Constructor sFontFamilyCtor;

    TypefaceCompatApi24Impl() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x004a A:{ExcHandler: java.lang.ClassNotFoundException (e java.lang.ClassNotFoundException), Splitter: B:1:0x0001} */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0047 A:{ExcHandler: java.lang.ClassNotFoundException (e java.lang.ClassNotFoundException), Splitter: B:4:0x0008} */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0045 A:{ExcHandler: java.lang.ClassNotFoundException (e java.lang.ClassNotFoundException), Splitter: B:6:0x000e} */
    /* JADX WARNING: Missing block: B:8:0x0045, code:
            r2 = e;
     */
    /* JADX WARNING: Missing block: B:9:0x0047, code:
            r2 = e;
     */
    /* JADX WARNING: Missing block: B:10:0x0048, code:
            r3 = null;
     */
    /* JADX WARNING: Missing block: B:11:0x004a, code:
            r2 = e;
     */
    /* JADX WARNING: Missing block: B:12:0x004b, code:
            r3 = null;
     */
    /* JADX WARNING: Missing block: B:13:0x004d, code:
            android.util.Log.e(TAG, r2.getClass().getName(), r2);
            r1 = null;
            r3 = null;
            r0 = null;
            r2 = null;
     */
    static {
        /*
        r0 = 0;
        r1 = "android.graphics.FontFamily";
        r1 = java.lang.Class.forName(r1);	 Catch:{ ClassNotFoundException -> 0x004a, ClassNotFoundException -> 0x004a }
        r2 = 0;
        r3 = new java.lang.Class[r2];	 Catch:{ ClassNotFoundException -> 0x0047, ClassNotFoundException -> 0x0047 }
        r3 = r1.getConstructor(r3);	 Catch:{ ClassNotFoundException -> 0x0047, ClassNotFoundException -> 0x0047 }
        r4 = "addFontWeightStyle";
        r5 = 5;
        r5 = new java.lang.Class[r5];	 Catch:{ ClassNotFoundException -> 0x0045, ClassNotFoundException -> 0x0045 }
        r6 = java.nio.ByteBuffer.class;
        r5[r2] = r6;	 Catch:{ ClassNotFoundException -> 0x0045, ClassNotFoundException -> 0x0045 }
        r6 = java.lang.Integer.TYPE;	 Catch:{ ClassNotFoundException -> 0x0045, ClassNotFoundException -> 0x0045 }
        r7 = 1;
        r5[r7] = r6;	 Catch:{ ClassNotFoundException -> 0x0045, ClassNotFoundException -> 0x0045 }
        r6 = 2;
        r8 = java.util.List.class;
        r5[r6] = r8;	 Catch:{ ClassNotFoundException -> 0x0045, ClassNotFoundException -> 0x0045 }
        r6 = 3;
        r8 = java.lang.Integer.TYPE;	 Catch:{ ClassNotFoundException -> 0x0045, ClassNotFoundException -> 0x0045 }
        r5[r6] = r8;	 Catch:{ ClassNotFoundException -> 0x0045, ClassNotFoundException -> 0x0045 }
        r6 = 4;
        r8 = java.lang.Boolean.TYPE;	 Catch:{ ClassNotFoundException -> 0x0045, ClassNotFoundException -> 0x0045 }
        r5[r6] = r8;	 Catch:{ ClassNotFoundException -> 0x0045, ClassNotFoundException -> 0x0045 }
        r4 = r1.getMethod(r4, r5);	 Catch:{ ClassNotFoundException -> 0x0045, ClassNotFoundException -> 0x0045 }
        r0 = r4;
        r4 = java.lang.reflect.Array.newInstance(r1, r7);	 Catch:{ ClassNotFoundException -> 0x0045, ClassNotFoundException -> 0x0045 }
        r5 = android.graphics.Typeface.class;
        r6 = "createFromFamiliesWithDefault";
        r7 = new java.lang.Class[r7];	 Catch:{ ClassNotFoundException -> 0x0045, ClassNotFoundException -> 0x0045 }
        r8 = r4.getClass();	 Catch:{ ClassNotFoundException -> 0x0045, ClassNotFoundException -> 0x0045 }
        r7[r2] = r8;	 Catch:{ ClassNotFoundException -> 0x0045, ClassNotFoundException -> 0x0045 }
        r2 = r5.getMethod(r6, r7);	 Catch:{ ClassNotFoundException -> 0x0045, ClassNotFoundException -> 0x0045 }
        goto L_0x005e;
    L_0x0045:
        r2 = move-exception;
        goto L_0x004d;
    L_0x0047:
        r2 = move-exception;
        r3 = r0;
        goto L_0x004d;
    L_0x004a:
        r2 = move-exception;
        r1 = r0;
        r3 = r1;
    L_0x004d:
        r4 = "TypefaceCompatApi24Impl";
        r5 = r2.getClass();
        r5 = r5.getName();
        android.util.Log.e(r4, r5, r2);
        r1 = 0;
        r3 = 0;
        r0 = 0;
        r2 = 0;
    L_0x005e:
        sFontFamilyCtor = r3;
        sFontFamily = r1;
        sAddFontWeightStyle = r0;
        sCreateFromFamiliesWithDefault = r2;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.TypefaceCompatApi24Impl.<clinit>():void");
    }

    public static boolean isUsable() {
        if (sAddFontWeightStyle == null) {
            Log.w(TAG, "Unable to collect necessary private methods.Fallback to legacy implementation.");
        }
        return sAddFontWeightStyle != null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x000a A:{ExcHandler: java.lang.IllegalAccessException (r0_2 'e' java.lang.ReflectiveOperationException), Splitter: B:0:0x0000} */
    /* JADX WARNING: Removed duplicated region for block: B:3:0x000a A:{ExcHandler: java.lang.IllegalAccessException (r0_2 'e' java.lang.ReflectiveOperationException), Splitter: B:0:0x0000} */
    /* JADX WARNING: Missing block: B:3:0x000a, code:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:5:0x0010, code:
            throw new java.lang.RuntimeException(r0);
     */
    private static java.lang.Object newFamily() {
        /*
        r0 = sFontFamilyCtor;	 Catch:{ IllegalAccessException -> 0x000a, IllegalAccessException -> 0x000a, IllegalAccessException -> 0x000a }
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ IllegalAccessException -> 0x000a, IllegalAccessException -> 0x000a, IllegalAccessException -> 0x000a }
        r0 = r0.newInstance(r1);	 Catch:{ IllegalAccessException -> 0x000a, IllegalAccessException -> 0x000a, IllegalAccessException -> 0x000a }
        return r0;
    L_0x000a:
        r0 = move-exception;
        r1 = new java.lang.RuntimeException;
        r1.<init>(r0);
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.TypefaceCompatApi24Impl.newFamily():java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x002c A:{ExcHandler: java.lang.IllegalAccessException (r0_3 'e' java.lang.ReflectiveOperationException), Splitter: B:0:0x0000} */
    /* JADX WARNING: Missing block: B:3:0x002c, code:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:5:0x0032, code:
            throw new java.lang.RuntimeException(r0);
     */
    private static boolean addFontWeightStyle(java.lang.Object r4, java.nio.ByteBuffer r5, int r6, int r7, boolean r8) {
        /*
        r0 = sAddFontWeightStyle;	 Catch:{ IllegalAccessException -> 0x002c, IllegalAccessException -> 0x002c }
        r1 = 5;
        r1 = new java.lang.Object[r1];	 Catch:{ IllegalAccessException -> 0x002c, IllegalAccessException -> 0x002c }
        r2 = 0;
        r1[r2] = r5;	 Catch:{ IllegalAccessException -> 0x002c, IllegalAccessException -> 0x002c }
        r2 = 1;
        r3 = java.lang.Integer.valueOf(r6);	 Catch:{ IllegalAccessException -> 0x002c, IllegalAccessException -> 0x002c }
        r1[r2] = r3;	 Catch:{ IllegalAccessException -> 0x002c, IllegalAccessException -> 0x002c }
        r2 = 2;
        r3 = 0;
        r1[r2] = r3;	 Catch:{ IllegalAccessException -> 0x002c, IllegalAccessException -> 0x002c }
        r2 = 3;
        r3 = java.lang.Integer.valueOf(r7);	 Catch:{ IllegalAccessException -> 0x002c, IllegalAccessException -> 0x002c }
        r1[r2] = r3;	 Catch:{ IllegalAccessException -> 0x002c, IllegalAccessException -> 0x002c }
        r2 = 4;
        r3 = java.lang.Boolean.valueOf(r8);	 Catch:{ IllegalAccessException -> 0x002c, IllegalAccessException -> 0x002c }
        r1[r2] = r3;	 Catch:{ IllegalAccessException -> 0x002c, IllegalAccessException -> 0x002c }
        r0 = r0.invoke(r4, r1);	 Catch:{ IllegalAccessException -> 0x002c, IllegalAccessException -> 0x002c }
        r0 = (java.lang.Boolean) r0;	 Catch:{ IllegalAccessException -> 0x002c, IllegalAccessException -> 0x002c }
        r1 = r0.booleanValue();	 Catch:{ IllegalAccessException -> 0x002c, IllegalAccessException -> 0x002c }
        return r1;
    L_0x002c:
        r0 = move-exception;
        r1 = new java.lang.RuntimeException;
        r1.<init>(r0);
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.TypefaceCompatApi24Impl.addFontWeightStyle(java.lang.Object, java.nio.ByteBuffer, int, int, boolean):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x0019 A:{ExcHandler: java.lang.IllegalAccessException (r0_2 'e' java.lang.ReflectiveOperationException), Splitter: B:0:0x0000} */
    /* JADX WARNING: Missing block: B:3:0x0019, code:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:5:0x001f, code:
            throw new java.lang.RuntimeException(r0);
     */
    private static android.graphics.Typeface createFromFamiliesWithDefault(java.lang.Object r5) {
        /*
        r0 = sFontFamily;	 Catch:{ IllegalAccessException -> 0x0019, IllegalAccessException -> 0x0019 }
        r1 = 1;
        r0 = java.lang.reflect.Array.newInstance(r0, r1);	 Catch:{ IllegalAccessException -> 0x0019, IllegalAccessException -> 0x0019 }
        r2 = 0;
        java.lang.reflect.Array.set(r0, r2, r5);	 Catch:{ IllegalAccessException -> 0x0019, IllegalAccessException -> 0x0019 }
        r3 = sCreateFromFamiliesWithDefault;	 Catch:{ IllegalAccessException -> 0x0019, IllegalAccessException -> 0x0019 }
        r4 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ IllegalAccessException -> 0x0019, IllegalAccessException -> 0x0019 }
        r1[r2] = r0;	 Catch:{ IllegalAccessException -> 0x0019, IllegalAccessException -> 0x0019 }
        r1 = r3.invoke(r4, r1);	 Catch:{ IllegalAccessException -> 0x0019, IllegalAccessException -> 0x0019 }
        r1 = (android.graphics.Typeface) r1;	 Catch:{ IllegalAccessException -> 0x0019, IllegalAccessException -> 0x0019 }
        return r1;
    L_0x0019:
        r0 = move-exception;
        r1 = new java.lang.RuntimeException;
        r1.<init>(r0);
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.TypefaceCompatApi24Impl.createFromFamiliesWithDefault(java.lang.Object):android.graphics.Typeface");
    }

    public Typeface createFromFontInfo(Context context, @Nullable CancellationSignal cancellationSignal, @NonNull FontInfo[] fonts, int style) {
        Object family = newFamily();
        SimpleArrayMap<Uri, ByteBuffer> bufferCache = new SimpleArrayMap();
        for (FontInfo font : fonts) {
            Uri uri = font.getUri();
            ByteBuffer buffer = (ByteBuffer) bufferCache.get(uri);
            if (buffer == null) {
                buffer = TypefaceCompatUtil.mmap(context, cancellationSignal, uri);
                bufferCache.put(uri, buffer);
            }
            if (!addFontWeightStyle(family, buffer, font.getTtcIndex(), font.getWeight(), font.isItalic())) {
                return null;
            }
        }
        return createFromFamiliesWithDefault(family);
    }

    public Typeface createFromFontFamilyFilesResourceEntry(Context context, FontFamilyFilesResourceEntry entry, Resources resources, int style) {
        Object family = newFamily();
        for (FontFileResourceEntry e : entry.getEntries()) {
            if (!addFontWeightStyle(family, TypefaceCompatUtil.copyToDirectBuffer(context, resources, e.getResourceId()), 0, e.getWeight(), e.isItalic())) {
                return null;
            }
        }
        return createFromFamiliesWithDefault(family);
    }
}
