package android.support.v4.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.content.res.Resources.Theme;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.FontRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.content.res.FontResourcesParserCompat.FamilyResourceEntry;
import android.support.v4.graphics.TypefaceCompat;
import android.util.Log;
import android.util.TypedValue;
import android.widget.TextView;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

public final class ResourcesCompat {
    private static final String TAG = "ResourcesCompat";

    @Nullable
    public static Drawable getDrawable(@NonNull Resources res, @DrawableRes int id, @Nullable Theme theme) throws NotFoundException {
        if (VERSION.SDK_INT >= 21) {
            return res.getDrawable(id, theme);
        }
        return res.getDrawable(id);
    }

    @Nullable
    public static Drawable getDrawableForDensity(@NonNull Resources res, @DrawableRes int id, int density, @Nullable Theme theme) throws NotFoundException {
        if (VERSION.SDK_INT >= 21) {
            return res.getDrawableForDensity(id, density, theme);
        }
        if (VERSION.SDK_INT >= 15) {
            return res.getDrawableForDensity(id, density);
        }
        return res.getDrawable(id);
    }

    @ColorInt
    public static int getColor(@NonNull Resources res, @ColorRes int id, @Nullable Theme theme) throws NotFoundException {
        if (VERSION.SDK_INT >= 23) {
            return res.getColor(id, theme);
        }
        return res.getColor(id);
    }

    @Nullable
    public static ColorStateList getColorStateList(@NonNull Resources res, @ColorRes int id, @Nullable Theme theme) throws NotFoundException {
        if (VERSION.SDK_INT >= 23) {
            return res.getColorStateList(id, theme);
        }
        return res.getColorStateList(id);
    }

    @Nullable
    public static Typeface getFont(@NonNull Context context, @FontRes int id) throws NotFoundException {
        if (context.isRestricted()) {
            return null;
        }
        return loadFont(context, id, new TypedValue(), 0, null);
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public static Typeface getFont(@NonNull Context context, @FontRes int id, TypedValue value, int style, @Nullable TextView targetView) throws NotFoundException {
        if (context.isRestricted()) {
            return null;
        }
        return loadFont(context, id, value, style, targetView);
    }

    private static Typeface loadFont(@NonNull Context context, int id, TypedValue value, int style, @Nullable TextView targetView) {
        Resources resources = context.getResources();
        resources.getValue(id, value, true);
        Typeface typeface = loadFont(context, resources, value, id, style, targetView);
        if (typeface != null) {
            return typeface;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Font resource ID #0x");
        stringBuilder.append(Integer.toHexString(id));
        throw new NotFoundException(stringBuilder.toString());
    }

    private static Typeface loadFont(@NonNull Context context, Resources wrapper, TypedValue value, int id, int style, @Nullable TextView targetView) {
        XmlPullParserException e;
        String str;
        StringBuilder stringBuilder;
        IOException e2;
        Resources resources = wrapper;
        TypedValue typedValue = value;
        int i = id;
        int i2 = style;
        Context context2;
        if (typedValue.string != null) {
            String file = typedValue.string.toString();
            if (!file.startsWith("res/")) {
                return null;
            }
            Typeface cached = TypefaceCompat.findFromCache(resources, i, i2);
            if (cached != null) {
                return cached;
            }
            try {
                if (file.toLowerCase().endsWith(".xml")) {
                    FamilyResourceEntry familyEntry = FontResourcesParserCompat.parse(resources.getXml(i), resources);
                    if (familyEntry != null) {
                        return TypefaceCompat.createFromResourcesFamilyXml(context, familyEntry, wrapper, id, style, targetView);
                    }
                    Log.e(TAG, "Failed to find font-family tag");
                    return null;
                }
                context2 = context;
                try {
                    return TypefaceCompat.createFromResourcesFontFile(context, resources, i, file, i2);
                } catch (XmlPullParserException e3) {
                    e = e3;
                    str = TAG;
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Failed to parse xml resource ");
                    stringBuilder.append(file);
                    Log.e(str, stringBuilder.toString(), e);
                    return null;
                } catch (IOException e4) {
                    e2 = e4;
                    str = TAG;
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Failed to read xml resource ");
                    stringBuilder.append(file);
                    Log.e(str, stringBuilder.toString(), e2);
                    return null;
                }
            } catch (XmlPullParserException e5) {
                e = e5;
                context2 = context;
                str = TAG;
                stringBuilder = new StringBuilder();
                stringBuilder.append("Failed to parse xml resource ");
                stringBuilder.append(file);
                Log.e(str, stringBuilder.toString(), e);
                return null;
            } catch (IOException e6) {
                e2 = e6;
                context2 = context;
                str = TAG;
                stringBuilder = new StringBuilder();
                stringBuilder.append("Failed to read xml resource ");
                stringBuilder.append(file);
                Log.e(str, stringBuilder.toString(), e2);
                return null;
            }
        }
        context2 = context;
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Resource \"");
        stringBuilder2.append(resources.getResourceName(i));
        stringBuilder2.append("\" (");
        stringBuilder2.append(Integer.toHexString(id));
        stringBuilder2.append(") is not a Font: ");
        stringBuilder2.append(typedValue);
        throw new NotFoundException(stringBuilder2.toString());
    }

    private ResourcesCompat() {
    }
}
