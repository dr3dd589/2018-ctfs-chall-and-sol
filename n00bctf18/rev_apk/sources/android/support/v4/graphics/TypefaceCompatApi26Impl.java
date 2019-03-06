package android.support.v4.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.Typeface.Builder;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.content.res.FontResourcesParserCompat.FontFamilyFilesResourceEntry;
import android.support.v4.content.res.FontResourcesParserCompat.FontFileResourceEntry;
import android.support.v4.provider.FontsContractCompat;
import android.support.v4.provider.FontsContractCompat.FontInfo;
import android.util.Log;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Map;

@RequiresApi(26)
@RestrictTo({Scope.LIBRARY_GROUP})
public class TypefaceCompatApi26Impl extends TypefaceCompatApi21Impl {
    private static final String ABORT_CREATION_METHOD = "abortCreation";
    private static final String ADD_FONT_FROM_ASSET_MANAGER_METHOD = "addFontFromAssetManager";
    private static final String ADD_FONT_FROM_BUFFER_METHOD = "addFontFromBuffer";
    private static final String CREATE_FROM_FAMILIES_WITH_DEFAULT_METHOD = "createFromFamiliesWithDefault";
    private static final String FONT_FAMILY_CLASS = "android.graphics.FontFamily";
    private static final String FREEZE_METHOD = "freeze";
    private static final int RESOLVE_BY_FONT_TABLE = -1;
    private static final String TAG = "TypefaceCompatApi26Impl";
    private static final Method sAbortCreation;
    private static final Method sAddFontFromAssetManager;
    private static final Method sAddFontFromBuffer;
    private static final Method sCreateFromFamiliesWithDefault;
    private static final Class sFontFamily;
    private static final Constructor sFontFamilyCtor;
    private static final Method sFreeze;

    /* JADX WARNING: Removed duplicated region for block: B:30:0x00a6 A:{ExcHandler: java.lang.ClassNotFoundException (e java.lang.ClassNotFoundException), Splitter: B:1:0x0001} */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00a3 A:{ExcHandler: java.lang.ClassNotFoundException (e java.lang.ClassNotFoundException), Splitter: B:4:0x0008} */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00a0 A:{ExcHandler: java.lang.ClassNotFoundException (e java.lang.ClassNotFoundException), Splitter: B:6:0x000e} */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x009d A:{ExcHandler: java.lang.ClassNotFoundException (e java.lang.ClassNotFoundException), Splitter: B:8:0x003f} */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0097 A:{ExcHandler: java.lang.ClassNotFoundException (e java.lang.ClassNotFoundException), Splitter: B:10:0x005b} */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0094 A:{ExcHandler: java.lang.ClassNotFoundException (e java.lang.ClassNotFoundException), Splitter: B:12:0x0063} */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x008f A:{PHI: r0 , ExcHandler: java.lang.ClassNotFoundException (e java.lang.ClassNotFoundException), Splitter: B:14:0x006b} */
    /* JADX WARNING: Missing block: B:17:0x008f, code:
            r2 = e;
     */
    /* JADX WARNING: Missing block: B:18:0x0090, code:
            r15 = r5;
            r5 = r0;
            r0 = r15;
     */
    /* JADX WARNING: Missing block: B:19:0x0094, code:
            r2 = e;
     */
    /* JADX WARNING: Missing block: B:20:0x0095, code:
            r10 = null;
     */
    /* JADX WARNING: Missing block: B:21:0x0097, code:
            r2 = e;
     */
    /* JADX WARNING: Missing block: B:22:0x0098, code:
            r10 = null;
     */
    /* JADX WARNING: Missing block: B:23:0x009a, code:
            r0 = r5;
            r5 = r10;
     */
    /* JADX WARNING: Missing block: B:24:0x009d, code:
            r2 = e;
     */
    /* JADX WARNING: Missing block: B:25:0x009e, code:
            r5 = null;
     */
    /* JADX WARNING: Missing block: B:26:0x00a0, code:
            r2 = e;
     */
    /* JADX WARNING: Missing block: B:27:0x00a1, code:
            r4 = null;
     */
    /* JADX WARNING: Missing block: B:28:0x00a3, code:
            r2 = e;
     */
    /* JADX WARNING: Missing block: B:29:0x00a4, code:
            r3 = null;
     */
    /* JADX WARNING: Missing block: B:30:0x00a6, code:
            r2 = e;
     */
    /* JADX WARNING: Missing block: B:31:0x00a7, code:
            r3 = null;
     */
    /* JADX WARNING: Missing block: B:32:0x00a9, code:
            r4 = r3;
     */
    /* JADX WARNING: Missing block: B:33:0x00aa, code:
            r5 = r4;
     */
    /* JADX WARNING: Missing block: B:34:0x00ab, code:
            r10 = r5;
     */
    /* JADX WARNING: Missing block: B:35:0x00ad, code:
            r7 = TAG;
            r8 = new java.lang.StringBuilder();
            r8.append("Unable to collect necessary methods for class ");
            r8.append(r2.getClass().getName());
            android.util.Log.e(r7, r8.toString(), r2);
            r1 = null;
            r3 = null;
            r4 = null;
            r0 = null;
            r6 = null;
            r10 = null;
            r2 = null;
     */
    static {
        /*
        r0 = 0;
        r1 = "android.graphics.FontFamily";
        r1 = java.lang.Class.forName(r1);	 Catch:{ ClassNotFoundException -> 0x00a6, ClassNotFoundException -> 0x00a6 }
        r2 = 0;
        r3 = new java.lang.Class[r2];	 Catch:{ ClassNotFoundException -> 0x00a3, ClassNotFoundException -> 0x00a3 }
        r3 = r1.getConstructor(r3);	 Catch:{ ClassNotFoundException -> 0x00a3, ClassNotFoundException -> 0x00a3 }
        r4 = "addFontFromAssetManager";
        r5 = 8;
        r5 = new java.lang.Class[r5];	 Catch:{ ClassNotFoundException -> 0x00a0, ClassNotFoundException -> 0x00a0 }
        r6 = android.content.res.AssetManager.class;
        r5[r2] = r6;	 Catch:{ ClassNotFoundException -> 0x00a0, ClassNotFoundException -> 0x00a0 }
        r6 = java.lang.String.class;
        r7 = 1;
        r5[r7] = r6;	 Catch:{ ClassNotFoundException -> 0x00a0, ClassNotFoundException -> 0x00a0 }
        r6 = java.lang.Integer.TYPE;	 Catch:{ ClassNotFoundException -> 0x00a0, ClassNotFoundException -> 0x00a0 }
        r8 = 2;
        r5[r8] = r6;	 Catch:{ ClassNotFoundException -> 0x00a0, ClassNotFoundException -> 0x00a0 }
        r6 = java.lang.Boolean.TYPE;	 Catch:{ ClassNotFoundException -> 0x00a0, ClassNotFoundException -> 0x00a0 }
        r9 = 3;
        r5[r9] = r6;	 Catch:{ ClassNotFoundException -> 0x00a0, ClassNotFoundException -> 0x00a0 }
        r6 = java.lang.Integer.TYPE;	 Catch:{ ClassNotFoundException -> 0x00a0, ClassNotFoundException -> 0x00a0 }
        r10 = 4;
        r5[r10] = r6;	 Catch:{ ClassNotFoundException -> 0x00a0, ClassNotFoundException -> 0x00a0 }
        r6 = java.lang.Integer.TYPE;	 Catch:{ ClassNotFoundException -> 0x00a0, ClassNotFoundException -> 0x00a0 }
        r11 = 5;
        r5[r11] = r6;	 Catch:{ ClassNotFoundException -> 0x00a0, ClassNotFoundException -> 0x00a0 }
        r6 = 6;
        r12 = java.lang.Integer.TYPE;	 Catch:{ ClassNotFoundException -> 0x00a0, ClassNotFoundException -> 0x00a0 }
        r5[r6] = r12;	 Catch:{ ClassNotFoundException -> 0x00a0, ClassNotFoundException -> 0x00a0 }
        r6 = 7;
        r12 = android.graphics.fonts.FontVariationAxis[].class;
        r5[r6] = r12;	 Catch:{ ClassNotFoundException -> 0x00a0, ClassNotFoundException -> 0x00a0 }
        r4 = r1.getMethod(r4, r5);	 Catch:{ ClassNotFoundException -> 0x00a0, ClassNotFoundException -> 0x00a0 }
        r5 = "addFontFromBuffer";
        r6 = new java.lang.Class[r11];	 Catch:{ ClassNotFoundException -> 0x009d, ClassNotFoundException -> 0x009d }
        r11 = java.nio.ByteBuffer.class;
        r6[r2] = r11;	 Catch:{ ClassNotFoundException -> 0x009d, ClassNotFoundException -> 0x009d }
        r11 = java.lang.Integer.TYPE;	 Catch:{ ClassNotFoundException -> 0x009d, ClassNotFoundException -> 0x009d }
        r6[r7] = r11;	 Catch:{ ClassNotFoundException -> 0x009d, ClassNotFoundException -> 0x009d }
        r11 = android.graphics.fonts.FontVariationAxis[].class;
        r6[r8] = r11;	 Catch:{ ClassNotFoundException -> 0x009d, ClassNotFoundException -> 0x009d }
        r11 = java.lang.Integer.TYPE;	 Catch:{ ClassNotFoundException -> 0x009d, ClassNotFoundException -> 0x009d }
        r6[r9] = r11;	 Catch:{ ClassNotFoundException -> 0x009d, ClassNotFoundException -> 0x009d }
        r11 = java.lang.Integer.TYPE;	 Catch:{ ClassNotFoundException -> 0x009d, ClassNotFoundException -> 0x009d }
        r6[r10] = r11;	 Catch:{ ClassNotFoundException -> 0x009d, ClassNotFoundException -> 0x009d }
        r5 = r1.getMethod(r5, r6);	 Catch:{ ClassNotFoundException -> 0x009d, ClassNotFoundException -> 0x009d }
        r6 = "freeze";
        r10 = new java.lang.Class[r2];	 Catch:{ ClassNotFoundException -> 0x0097, ClassNotFoundException -> 0x0097 }
        r6 = r1.getMethod(r6, r10);	 Catch:{ ClassNotFoundException -> 0x0097, ClassNotFoundException -> 0x0097 }
        r10 = "abortCreation";
        r11 = new java.lang.Class[r2];	 Catch:{ ClassNotFoundException -> 0x0094, ClassNotFoundException -> 0x0094 }
        r10 = r1.getMethod(r10, r11);	 Catch:{ ClassNotFoundException -> 0x0094, ClassNotFoundException -> 0x0094 }
        r11 = java.lang.reflect.Array.newInstance(r1, r7);	 Catch:{ ClassNotFoundException -> 0x008f, ClassNotFoundException -> 0x008f }
        r12 = android.graphics.Typeface.class;
        r13 = "createFromFamiliesWithDefault";
        r9 = new java.lang.Class[r9];	 Catch:{ ClassNotFoundException -> 0x008f, ClassNotFoundException -> 0x008f }
        r14 = r11.getClass();	 Catch:{ ClassNotFoundException -> 0x008f, ClassNotFoundException -> 0x008f }
        r9[r2] = r14;	 Catch:{ ClassNotFoundException -> 0x008f, ClassNotFoundException -> 0x008f }
        r2 = java.lang.Integer.TYPE;	 Catch:{ ClassNotFoundException -> 0x008f, ClassNotFoundException -> 0x008f }
        r9[r7] = r2;	 Catch:{ ClassNotFoundException -> 0x008f, ClassNotFoundException -> 0x008f }
        r2 = java.lang.Integer.TYPE;	 Catch:{ ClassNotFoundException -> 0x008f, ClassNotFoundException -> 0x008f }
        r9[r8] = r2;	 Catch:{ ClassNotFoundException -> 0x008f, ClassNotFoundException -> 0x008f }
        r2 = r12.getDeclaredMethod(r13, r9);	 Catch:{ ClassNotFoundException -> 0x008f, ClassNotFoundException -> 0x008f }
        r0 = r2;
        r0.setAccessible(r7);	 Catch:{ ClassNotFoundException -> 0x008f, ClassNotFoundException -> 0x008f }
        r2 = r0;
        r0 = r5;
        goto L_0x00d2;
    L_0x008f:
        r2 = move-exception;
        r15 = r5;
        r5 = r0;
        r0 = r15;
        goto L_0x00ad;
    L_0x0094:
        r2 = move-exception;
        r10 = r0;
        goto L_0x009a;
    L_0x0097:
        r2 = move-exception;
        r6 = r0;
        r10 = r6;
    L_0x009a:
        r0 = r5;
        r5 = r10;
        goto L_0x00ad;
    L_0x009d:
        r2 = move-exception;
        r5 = r0;
        goto L_0x00ab;
    L_0x00a0:
        r2 = move-exception;
        r4 = r0;
        goto L_0x00aa;
    L_0x00a3:
        r2 = move-exception;
        r3 = r0;
        goto L_0x00a9;
    L_0x00a6:
        r2 = move-exception;
        r1 = r0;
        r3 = r1;
    L_0x00a9:
        r4 = r3;
    L_0x00aa:
        r5 = r4;
    L_0x00ab:
        r6 = r5;
        r10 = r6;
    L_0x00ad:
        r7 = "TypefaceCompatApi26Impl";
        r8 = new java.lang.StringBuilder;
        r8.<init>();
        r9 = "Unable to collect necessary methods for class ";
        r8.append(r9);
        r9 = r2.getClass();
        r9 = r9.getName();
        r8.append(r9);
        r8 = r8.toString();
        android.util.Log.e(r7, r8, r2);
        r1 = 0;
        r3 = 0;
        r4 = 0;
        r0 = 0;
        r6 = 0;
        r10 = 0;
        r2 = 0;
    L_0x00d2:
        sFontFamilyCtor = r3;
        sFontFamily = r1;
        sAddFontFromAssetManager = r4;
        sAddFontFromBuffer = r0;
        sFreeze = r6;
        sAbortCreation = r10;
        sCreateFromFamiliesWithDefault = r2;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.TypefaceCompatApi26Impl.<clinit>():void");
    }

    private static boolean isFontFamilyPrivateAPIAvailable() {
        if (sAddFontFromAssetManager == null) {
            Log.w(TAG, "Unable to collect necessary private methods.Fallback to legacy implementation.");
        }
        return sAddFontFromAssetManager != null;
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
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.TypefaceCompatApi26Impl.newFamily():java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x0042 A:{ExcHandler: java.lang.IllegalAccessException (r0_3 'e' java.lang.ReflectiveOperationException), Splitter: B:0:0x0000} */
    /* JADX WARNING: Missing block: B:3:0x0042, code:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:5:0x0048, code:
            throw new java.lang.RuntimeException(r0);
     */
    private static boolean addFontFromAssetManager(android.content.Context r5, java.lang.Object r6, java.lang.String r7, int r8, int r9, int r10) {
        /*
        r0 = sAddFontFromAssetManager;	 Catch:{ IllegalAccessException -> 0x0042, IllegalAccessException -> 0x0042 }
        r1 = 8;
        r1 = new java.lang.Object[r1];	 Catch:{ IllegalAccessException -> 0x0042, IllegalAccessException -> 0x0042 }
        r2 = r5.getAssets();	 Catch:{ IllegalAccessException -> 0x0042, IllegalAccessException -> 0x0042 }
        r3 = 0;
        r1[r3] = r2;	 Catch:{ IllegalAccessException -> 0x0042, IllegalAccessException -> 0x0042 }
        r2 = 1;
        r1[r2] = r7;	 Catch:{ IllegalAccessException -> 0x0042, IllegalAccessException -> 0x0042 }
        r2 = 2;
        r4 = java.lang.Integer.valueOf(r3);	 Catch:{ IllegalAccessException -> 0x0042, IllegalAccessException -> 0x0042 }
        r1[r2] = r4;	 Catch:{ IllegalAccessException -> 0x0042, IllegalAccessException -> 0x0042 }
        r2 = 3;
        r3 = java.lang.Boolean.valueOf(r3);	 Catch:{ IllegalAccessException -> 0x0042, IllegalAccessException -> 0x0042 }
        r1[r2] = r3;	 Catch:{ IllegalAccessException -> 0x0042, IllegalAccessException -> 0x0042 }
        r2 = 4;
        r3 = java.lang.Integer.valueOf(r8);	 Catch:{ IllegalAccessException -> 0x0042, IllegalAccessException -> 0x0042 }
        r1[r2] = r3;	 Catch:{ IllegalAccessException -> 0x0042, IllegalAccessException -> 0x0042 }
        r2 = 5;
        r3 = java.lang.Integer.valueOf(r9);	 Catch:{ IllegalAccessException -> 0x0042, IllegalAccessException -> 0x0042 }
        r1[r2] = r3;	 Catch:{ IllegalAccessException -> 0x0042, IllegalAccessException -> 0x0042 }
        r2 = 6;
        r3 = java.lang.Integer.valueOf(r10);	 Catch:{ IllegalAccessException -> 0x0042, IllegalAccessException -> 0x0042 }
        r1[r2] = r3;	 Catch:{ IllegalAccessException -> 0x0042, IllegalAccessException -> 0x0042 }
        r2 = 7;
        r3 = 0;
        r1[r2] = r3;	 Catch:{ IllegalAccessException -> 0x0042, IllegalAccessException -> 0x0042 }
        r0 = r0.invoke(r6, r1);	 Catch:{ IllegalAccessException -> 0x0042, IllegalAccessException -> 0x0042 }
        r0 = (java.lang.Boolean) r0;	 Catch:{ IllegalAccessException -> 0x0042, IllegalAccessException -> 0x0042 }
        r1 = r0.booleanValue();	 Catch:{ IllegalAccessException -> 0x0042, IllegalAccessException -> 0x0042 }
        return r1;
    L_0x0042:
        r0 = move-exception;
        r1 = new java.lang.RuntimeException;
        r1.<init>(r0);
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.TypefaceCompatApi26Impl.addFontFromAssetManager(android.content.Context, java.lang.Object, java.lang.String, int, int, int):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x002c A:{ExcHandler: java.lang.IllegalAccessException (r0_3 'e' java.lang.ReflectiveOperationException), Splitter: B:0:0x0000} */
    /* JADX WARNING: Missing block: B:3:0x002c, code:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:5:0x0032, code:
            throw new java.lang.RuntimeException(r0);
     */
    private static boolean addFontFromBuffer(java.lang.Object r4, java.nio.ByteBuffer r5, int r6, int r7, int r8) {
        /*
        r0 = sAddFontFromBuffer;	 Catch:{ IllegalAccessException -> 0x002c, IllegalAccessException -> 0x002c }
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
        r3 = java.lang.Integer.valueOf(r8);	 Catch:{ IllegalAccessException -> 0x002c, IllegalAccessException -> 0x002c }
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
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.TypefaceCompatApi26Impl.addFontFromBuffer(java.lang.Object, java.nio.ByteBuffer, int, int, int):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x0028 A:{ExcHandler: java.lang.IllegalAccessException (r0_2 'e' java.lang.ReflectiveOperationException), Splitter: B:0:0x0000} */
    /* JADX WARNING: Missing block: B:3:0x0028, code:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:5:0x002e, code:
            throw new java.lang.RuntimeException(r0);
     */
    private static android.graphics.Typeface createFromFamiliesWithDefault(java.lang.Object r7) {
        /*
        r0 = sFontFamily;	 Catch:{ IllegalAccessException -> 0x0028, IllegalAccessException -> 0x0028 }
        r1 = 1;
        r0 = java.lang.reflect.Array.newInstance(r0, r1);	 Catch:{ IllegalAccessException -> 0x0028, IllegalAccessException -> 0x0028 }
        r2 = 0;
        java.lang.reflect.Array.set(r0, r2, r7);	 Catch:{ IllegalAccessException -> 0x0028, IllegalAccessException -> 0x0028 }
        r3 = sCreateFromFamiliesWithDefault;	 Catch:{ IllegalAccessException -> 0x0028, IllegalAccessException -> 0x0028 }
        r4 = 0;
        r5 = 3;
        r5 = new java.lang.Object[r5];	 Catch:{ IllegalAccessException -> 0x0028, IllegalAccessException -> 0x0028 }
        r5[r2] = r0;	 Catch:{ IllegalAccessException -> 0x0028, IllegalAccessException -> 0x0028 }
        r2 = -1;
        r6 = java.lang.Integer.valueOf(r2);	 Catch:{ IllegalAccessException -> 0x0028, IllegalAccessException -> 0x0028 }
        r5[r1] = r6;	 Catch:{ IllegalAccessException -> 0x0028, IllegalAccessException -> 0x0028 }
        r1 = 2;
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ IllegalAccessException -> 0x0028, IllegalAccessException -> 0x0028 }
        r5[r1] = r2;	 Catch:{ IllegalAccessException -> 0x0028, IllegalAccessException -> 0x0028 }
        r1 = r3.invoke(r4, r5);	 Catch:{ IllegalAccessException -> 0x0028, IllegalAccessException -> 0x0028 }
        r1 = (android.graphics.Typeface) r1;	 Catch:{ IllegalAccessException -> 0x0028, IllegalAccessException -> 0x0028 }
        return r1;
    L_0x0028:
        r0 = move-exception;
        r1 = new java.lang.RuntimeException;
        r1.<init>(r0);
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.TypefaceCompatApi26Impl.createFromFamiliesWithDefault(java.lang.Object):android.graphics.Typeface");
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x0010 A:{ExcHandler: java.lang.IllegalAccessException (r0_3 'e' java.lang.ReflectiveOperationException), Splitter: B:0:0x0000} */
    /* JADX WARNING: Missing block: B:3:0x0010, code:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:5:0x0016, code:
            throw new java.lang.RuntimeException(r0);
     */
    private static boolean freeze(java.lang.Object r2) {
        /*
        r0 = sFreeze;	 Catch:{ IllegalAccessException -> 0x0010, IllegalAccessException -> 0x0010 }
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ IllegalAccessException -> 0x0010, IllegalAccessException -> 0x0010 }
        r0 = r0.invoke(r2, r1);	 Catch:{ IllegalAccessException -> 0x0010, IllegalAccessException -> 0x0010 }
        r0 = (java.lang.Boolean) r0;	 Catch:{ IllegalAccessException -> 0x0010, IllegalAccessException -> 0x0010 }
        r1 = r0.booleanValue();	 Catch:{ IllegalAccessException -> 0x0010, IllegalAccessException -> 0x0010 }
        return r1;
    L_0x0010:
        r0 = move-exception;
        r1 = new java.lang.RuntimeException;
        r1.<init>(r0);
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.TypefaceCompatApi26Impl.freeze(java.lang.Object):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x0010 A:{ExcHandler: java.lang.IllegalAccessException (r0_3 'e' java.lang.ReflectiveOperationException), Splitter: B:0:0x0000} */
    /* JADX WARNING: Missing block: B:3:0x0010, code:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:5:0x0016, code:
            throw new java.lang.RuntimeException(r0);
     */
    private static boolean abortCreation(java.lang.Object r2) {
        /*
        r0 = sAbortCreation;	 Catch:{ IllegalAccessException -> 0x0010, IllegalAccessException -> 0x0010 }
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ IllegalAccessException -> 0x0010, IllegalAccessException -> 0x0010 }
        r0 = r0.invoke(r2, r1);	 Catch:{ IllegalAccessException -> 0x0010, IllegalAccessException -> 0x0010 }
        r0 = (java.lang.Boolean) r0;	 Catch:{ IllegalAccessException -> 0x0010, IllegalAccessException -> 0x0010 }
        r1 = r0.booleanValue();	 Catch:{ IllegalAccessException -> 0x0010, IllegalAccessException -> 0x0010 }
        return r1;
    L_0x0010:
        r0 = move-exception;
        r1 = new java.lang.RuntimeException;
        r1.<init>(r0);
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.TypefaceCompatApi26Impl.abortCreation(java.lang.Object):boolean");
    }

    public Typeface createFromFontFamilyFilesResourceEntry(Context context, FontFamilyFilesResourceEntry entry, Resources resources, int style) {
        if (!isFontFamilyPrivateAPIAvailable()) {
            return super.createFromFontFamilyFilesResourceEntry(context, entry, resources, style);
        }
        Object fontFamily = newFamily();
        FontFileResourceEntry[] entries = entry.getEntries();
        int length = entries.length;
        int i = 0;
        while (i < length) {
            FontFileResourceEntry fontFile = entries[i];
            if (addFontFromAssetManager(context, fontFamily, fontFile.getFileName(), 0, fontFile.getWeight(), fontFile.isItalic())) {
                i++;
            } else {
                abortCreation(fontFamily);
                return null;
            }
        }
        if (freeze(fontFamily)) {
            return createFromFamiliesWithDefault(fontFamily);
        }
        return null;
    }

    public Typeface createFromFontInfo(Context context, @Nullable CancellationSignal cancellationSignal, @NonNull FontInfo[] fonts, int style) {
        ParcelFileDescriptor pfd;
        Throwable th;
        Throwable th2;
        CancellationSignal cancellationSignal2 = cancellationSignal;
        FontInfo[] fontInfoArr = fonts;
        if (fontInfoArr.length < 1) {
            return null;
        }
        if (isFontFamilyPrivateAPIAvailable()) {
            int i = style;
            Map<Uri, ByteBuffer> uriBuffer = FontsContractCompat.prepareFontData(context, fontInfoArr, cancellationSignal2);
            Object fontFamily = newFamily();
            boolean atLeastOneFont = false;
            for (FontInfo font : fontInfoArr) {
                ByteBuffer fontBuffer = (ByteBuffer) uriBuffer.get(font.getUri());
                if (fontBuffer != null) {
                    if (addFontFromBuffer(fontFamily, fontBuffer, font.getTtcIndex(), font.getWeight(), font.isItalic())) {
                        atLeastOneFont = true;
                    } else {
                        abortCreation(fontFamily);
                        return null;
                    }
                }
            }
            if (!atLeastOneFont) {
                abortCreation(fontFamily);
                return null;
            } else if (freeze(fontFamily)) {
                return createFromFamiliesWithDefault(fontFamily);
            } else {
                return null;
            }
        }
        FontInfo bestFont = findBestInfo(fontInfoArr, style);
        try {
            pfd = context.getContentResolver().openFileDescriptor(bestFont.getUri(), "r", cancellationSignal2);
            try {
                Typeface build = new Builder(pfd.getFileDescriptor()).setWeight(bestFont.getWeight()).setItalic(bestFont.isItalic()).build();
                if (pfd != null) {
                    pfd.close();
                }
                return build;
            } catch (Throwable th3) {
                th2 = th3;
                th = th3;
            }
        } catch (IOException e) {
            return null;
        }
        if (pfd != null) {
            if (th2 != null) {
                try {
                    pfd.close();
                } catch (Throwable th32) {
                    th2.addSuppressed(th32);
                }
            } else {
                pfd.close();
            }
        }
        throw th;
        throw th;
    }

    @Nullable
    public Typeface createFromResourcesFontFile(Context context, Resources resources, int id, String path, int style) {
        if (!isFontFamilyPrivateAPIAvailable()) {
            return super.createFromResourcesFontFile(context, resources, id, path, style);
        }
        Object fontFamily = newFamily();
        if (!addFontFromAssetManager(context, fontFamily, path, 0, -1, -1)) {
            abortCreation(fontFamily);
            return null;
        } else if (freeze(fontFamily)) {
            return createFromFamiliesWithDefault(fontFamily);
        } else {
            return null;
        }
    }
}
