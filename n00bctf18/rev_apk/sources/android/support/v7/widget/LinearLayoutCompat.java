package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class LinearLayoutCompat extends ViewGroup {
    public static final int HORIZONTAL = 0;
    private static final int INDEX_BOTTOM = 2;
    private static final int INDEX_CENTER_VERTICAL = 0;
    private static final int INDEX_FILL = 3;
    private static final int INDEX_TOP = 1;
    public static final int SHOW_DIVIDER_BEGINNING = 1;
    public static final int SHOW_DIVIDER_END = 4;
    public static final int SHOW_DIVIDER_MIDDLE = 2;
    public static final int SHOW_DIVIDER_NONE = 0;
    public static final int VERTICAL = 1;
    private static final int VERTICAL_GRAVITY_COUNT = 4;
    private boolean mBaselineAligned;
    private int mBaselineAlignedChildIndex;
    private int mBaselineChildTop;
    private Drawable mDivider;
    private int mDividerHeight;
    private int mDividerPadding;
    private int mDividerWidth;
    private int mGravity;
    private int[] mMaxAscent;
    private int[] mMaxDescent;
    private int mOrientation;
    private int mShowDividers;
    private int mTotalLength;
    private boolean mUseLargestChild;
    private float mWeightSum;

    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface DividerMode {
    }

    public static class LayoutParams extends MarginLayoutParams {
        public int gravity;
        public float weight;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            this.gravity = -1;
            TypedArray a = c.obtainStyledAttributes(attrs, R.styleable.LinearLayoutCompat_Layout);
            this.weight = a.getFloat(R.styleable.LinearLayoutCompat_Layout_android_layout_weight, 0.0f);
            this.gravity = a.getInt(R.styleable.LinearLayoutCompat_Layout_android_layout_gravity, -1);
            a.recycle();
        }

        public LayoutParams(int width, int height) {
            super(width, height);
            this.gravity = -1;
            this.weight = 0.0f;
        }

        public LayoutParams(int width, int height, float weight) {
            super(width, height);
            this.gravity = -1;
            this.weight = weight;
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams p) {
            super(p);
            this.gravity = -1;
        }

        public LayoutParams(MarginLayoutParams source) {
            super(source);
            this.gravity = -1;
        }

        public LayoutParams(LayoutParams source) {
            super(source);
            this.gravity = -1;
            this.weight = source.weight;
            this.gravity = source.gravity;
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface OrientationMode {
    }

    public LinearLayoutCompat(Context context) {
        this(context, null);
    }

    public LinearLayoutCompat(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LinearLayoutCompat(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mBaselineAligned = true;
        this.mBaselineAlignedChildIndex = -1;
        this.mBaselineChildTop = 0;
        this.mGravity = 8388659;
        TintTypedArray a = TintTypedArray.obtainStyledAttributes(context, attrs, R.styleable.LinearLayoutCompat, defStyleAttr, 0);
        int index = a.getInt(R.styleable.LinearLayoutCompat_android_orientation, -1);
        if (index >= 0) {
            setOrientation(index);
        }
        index = a.getInt(R.styleable.LinearLayoutCompat_android_gravity, -1);
        if (index >= 0) {
            setGravity(index);
        }
        boolean baselineAligned = a.getBoolean(R.styleable.LinearLayoutCompat_android_baselineAligned, true);
        if (!baselineAligned) {
            setBaselineAligned(baselineAligned);
        }
        this.mWeightSum = a.getFloat(R.styleable.LinearLayoutCompat_android_weightSum, -1.0f);
        this.mBaselineAlignedChildIndex = a.getInt(R.styleable.LinearLayoutCompat_android_baselineAlignedChildIndex, -1);
        this.mUseLargestChild = a.getBoolean(R.styleable.LinearLayoutCompat_measureWithLargestChild, false);
        setDividerDrawable(a.getDrawable(R.styleable.LinearLayoutCompat_divider));
        this.mShowDividers = a.getInt(R.styleable.LinearLayoutCompat_showDividers, 0);
        this.mDividerPadding = a.getDimensionPixelSize(R.styleable.LinearLayoutCompat_dividerPadding, 0);
        a.recycle();
    }

    public void setShowDividers(int showDividers) {
        if (showDividers != this.mShowDividers) {
            requestLayout();
        }
        this.mShowDividers = showDividers;
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public int getShowDividers() {
        return this.mShowDividers;
    }

    public Drawable getDividerDrawable() {
        return this.mDivider;
    }

    public void setDividerDrawable(Drawable divider) {
        if (divider != this.mDivider) {
            this.mDivider = divider;
            boolean z = false;
            if (divider != null) {
                this.mDividerWidth = divider.getIntrinsicWidth();
                this.mDividerHeight = divider.getIntrinsicHeight();
            } else {
                this.mDividerWidth = 0;
                this.mDividerHeight = 0;
            }
            if (divider == null) {
                z = true;
            }
            setWillNotDraw(z);
            requestLayout();
        }
    }

    public void setDividerPadding(int padding) {
        this.mDividerPadding = padding;
    }

    public int getDividerPadding() {
        return this.mDividerPadding;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public int getDividerWidth() {
        return this.mDividerWidth;
    }

    protected void onDraw(Canvas canvas) {
        if (this.mDivider != null) {
            if (this.mOrientation == 1) {
                drawDividersVertical(canvas);
            } else {
                drawDividersHorizontal(canvas);
            }
        }
    }

    void drawDividersVertical(Canvas canvas) {
        int count = getVirtualChildCount();
        int i = 0;
        while (i < count) {
            View child = getVirtualChildAt(i);
            if (!(child == null || child.getVisibility() == 8 || !hasDividerBeforeChildAt(i))) {
                drawHorizontalDivider(canvas, (child.getTop() - ((LayoutParams) child.getLayoutParams()).topMargin) - this.mDividerHeight);
            }
            i++;
        }
        if (hasDividerBeforeChildAt(count)) {
            int bottom;
            View child2 = getVirtualChildAt(count - 1);
            if (child2 == null) {
                bottom = (getHeight() - getPaddingBottom()) - this.mDividerHeight;
            } else {
                bottom = child2.getBottom() + ((LayoutParams) child2.getLayoutParams()).bottomMargin;
            }
            drawHorizontalDivider(canvas, bottom);
        }
    }

    void drawDividersHorizontal(Canvas canvas) {
        int count = getVirtualChildCount();
        boolean isLayoutRtl = ViewUtils.isLayoutRtl(this);
        int i = 0;
        while (i < count) {
            View child = getVirtualChildAt(i);
            if (!(child == null || child.getVisibility() == 8 || !hasDividerBeforeChildAt(i))) {
                int position;
                LayoutParams lp = (LayoutParams) child.getLayoutParams();
                if (isLayoutRtl) {
                    position = child.getRight() + lp.rightMargin;
                } else {
                    position = (child.getLeft() - lp.leftMargin) - this.mDividerWidth;
                }
                drawVerticalDivider(canvas, position);
            }
            i++;
        }
        if (hasDividerBeforeChildAt(count)) {
            int position2;
            View child2 = getVirtualChildAt(count - 1);
            if (child2 != null) {
                LayoutParams lp2 = (LayoutParams) child2.getLayoutParams();
                if (isLayoutRtl) {
                    position2 = (child2.getLeft() - lp2.leftMargin) - this.mDividerWidth;
                } else {
                    position2 = child2.getRight() + lp2.rightMargin;
                }
            } else if (isLayoutRtl) {
                position2 = getPaddingLeft();
            } else {
                position2 = (getWidth() - getPaddingRight()) - this.mDividerWidth;
            }
            drawVerticalDivider(canvas, position2);
        }
    }

    void drawHorizontalDivider(Canvas canvas, int top) {
        this.mDivider.setBounds(getPaddingLeft() + this.mDividerPadding, top, (getWidth() - getPaddingRight()) - this.mDividerPadding, this.mDividerHeight + top);
        this.mDivider.draw(canvas);
    }

    void drawVerticalDivider(Canvas canvas, int left) {
        this.mDivider.setBounds(left, getPaddingTop() + this.mDividerPadding, this.mDividerWidth + left, (getHeight() - getPaddingBottom()) - this.mDividerPadding);
        this.mDivider.draw(canvas);
    }

    public boolean isBaselineAligned() {
        return this.mBaselineAligned;
    }

    public void setBaselineAligned(boolean baselineAligned) {
        this.mBaselineAligned = baselineAligned;
    }

    public boolean isMeasureWithLargestChildEnabled() {
        return this.mUseLargestChild;
    }

    public void setMeasureWithLargestChildEnabled(boolean enabled) {
        this.mUseLargestChild = enabled;
    }

    public int getBaseline() {
        if (this.mBaselineAlignedChildIndex < 0) {
            return super.getBaseline();
        }
        if (getChildCount() > this.mBaselineAlignedChildIndex) {
            View child = getChildAt(this.mBaselineAlignedChildIndex);
            int childBaseline = child.getBaseline();
            if (childBaseline != -1) {
                int childTop = this.mBaselineChildTop;
                if (this.mOrientation == 1) {
                    int majorGravity = this.mGravity & 112;
                    if (majorGravity != 48) {
                        if (majorGravity == 16) {
                            childTop += ((((getBottom() - getTop()) - getPaddingTop()) - getPaddingBottom()) - this.mTotalLength) / 2;
                        } else if (majorGravity == 80) {
                            childTop = ((getBottom() - getTop()) - getPaddingBottom()) - this.mTotalLength;
                        }
                    }
                }
                return (((LayoutParams) child.getLayoutParams()).topMargin + childTop) + childBaseline;
            } else if (this.mBaselineAlignedChildIndex == 0) {
                return -1;
            } else {
                throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
            }
        }
        throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
    }

    public int getBaselineAlignedChildIndex() {
        return this.mBaselineAlignedChildIndex;
    }

    public void setBaselineAlignedChildIndex(int i) {
        if (i < 0 || i >= getChildCount()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("base aligned child index out of range (0, ");
            stringBuilder.append(getChildCount());
            stringBuilder.append(")");
            throw new IllegalArgumentException(stringBuilder.toString());
        }
        this.mBaselineAlignedChildIndex = i;
    }

    View getVirtualChildAt(int index) {
        return getChildAt(index);
    }

    int getVirtualChildCount() {
        return getChildCount();
    }

    public float getWeightSum() {
        return this.mWeightSum;
    }

    public void setWeightSum(float weightSum) {
        this.mWeightSum = Math.max(0.0f, weightSum);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (this.mOrientation == 1) {
            measureVertical(widthMeasureSpec, heightMeasureSpec);
        } else {
            measureHorizontal(widthMeasureSpec, heightMeasureSpec);
        }
    }

    protected boolean hasDividerBeforeChildAt(int childIndex) {
        boolean hasVisibleViewBefore = false;
        if (childIndex == 0) {
            if ((this.mShowDividers & 1) != 0) {
                hasVisibleViewBefore = true;
            }
            return hasVisibleViewBefore;
        } else if (childIndex == getChildCount()) {
            if ((this.mShowDividers & 4) != 0) {
                hasVisibleViewBefore = true;
            }
            return hasVisibleViewBefore;
        } else if ((this.mShowDividers & 2) == 0) {
            return false;
        } else {
            hasVisibleViewBefore = false;
            for (int i = childIndex - 1; i >= 0; i--) {
                if (getChildAt(i).getVisibility() != 8) {
                    hasVisibleViewBefore = true;
                    break;
                }
            }
            return hasVisibleViewBefore;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:65:0x019b  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x018f  */
    /* JADX WARNING: Removed duplicated region for block: B:157:0x03da  */
    /* JADX WARNING: Removed duplicated region for block: B:156:0x03d7  */
    /* JADX WARNING: Removed duplicated region for block: B:163:0x03ea  */
    /* JADX WARNING: Removed duplicated region for block: B:160:0x03e1  */
    /* JADX WARNING: Removed duplicated region for block: B:192:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:174:0x045a  */
    void measureVertical(int r58, int r59) {
        /*
        r57 = this;
        r7 = r57;
        r8 = r58;
        r9 = r59;
        r10 = 0;
        r7.mTotalLength = r10;
        r0 = 0;
        r1 = 0;
        r2 = 0;
        r3 = 0;
        r4 = 1;
        r5 = 0;
        r11 = r57.getVirtualChildCount();
        r12 = android.view.View.MeasureSpec.getMode(r58);
        r13 = android.view.View.MeasureSpec.getMode(r59);
        r6 = 0;
        r14 = 0;
        r15 = r7.mBaselineAlignedChildIndex;
        r10 = r7.mUseLargestChild;
        r17 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r18 = r6;
        r6 = r2;
        r2 = r0;
        r0 = 0;
        r56 = r4;
        r4 = r3;
        r3 = r17;
        r17 = r56;
    L_0x002f:
        r19 = r4;
        r21 = 1;
        r22 = 0;
        if (r0 >= r11) goto L_0x01c2;
    L_0x0037:
        r4 = r7.getVirtualChildAt(r0);
        if (r4 != 0) goto L_0x0054;
    L_0x003d:
        r25 = r1;
        r1 = r7.mTotalLength;
        r20 = r7.measureNullChild(r0);
        r1 = r1 + r20;
        r7.mTotalLength = r1;
        r35 = r11;
        r34 = r13;
        r4 = r19;
        r1 = r25;
        goto L_0x01b6;
    L_0x0054:
        r25 = r1;
        r1 = r4.getVisibility();
        r26 = r2;
        r2 = 8;
        if (r1 != r2) goto L_0x0072;
    L_0x0060:
        r1 = r7.getChildrenSkipCount(r4, r0);
        r0 = r0 + r1;
        r35 = r11;
        r34 = r13;
        r4 = r19;
        r1 = r25;
        r2 = r26;
        goto L_0x01b6;
    L_0x0072:
        r1 = r7.hasDividerBeforeChildAt(r0);
        if (r1 == 0) goto L_0x007f;
    L_0x0078:
        r1 = r7.mTotalLength;
        r2 = r7.mDividerHeight;
        r1 = r1 + r2;
        r7.mTotalLength = r1;
    L_0x007f:
        r1 = r4.getLayoutParams();
        r2 = r1;
        r2 = (android.support.v7.widget.LinearLayoutCompat.LayoutParams) r2;
        r1 = r2.weight;
        r23 = r5 + r1;
        r5 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        if (r13 != r5) goto L_0x00bc;
    L_0x008e:
        r1 = r2.height;
        if (r1 != 0) goto L_0x00bc;
    L_0x0092:
        r1 = r2.weight;
        r1 = (r1 > r22 ? 1 : (r1 == r22 ? 0 : -1));
        if (r1 <= 0) goto L_0x00bc;
    L_0x0098:
        r1 = r7.mTotalLength;
        r5 = r2.topMargin;
        r5 = r5 + r1;
        r27 = r0;
        r0 = r2.bottomMargin;
        r5 = r5 + r0;
        r0 = java.lang.Math.max(r1, r5);
        r7.mTotalLength = r0;
        r14 = 1;
        r0 = r2;
        r35 = r11;
        r34 = r13;
        r32 = r14;
        r9 = r19;
        r8 = r25;
        r31 = r26;
        r29 = r27;
        r11 = r6;
        goto L_0x0133;
    L_0x00bc:
        r27 = r0;
        r0 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r1 = r2.height;
        if (r1 != 0) goto L_0x00ce;
    L_0x00c4:
        r1 = r2.weight;
        r1 = (r1 > r22 ? 1 : (r1 == r22 ? 0 : -1));
        if (r1 <= 0) goto L_0x00ce;
    L_0x00ca:
        r0 = 0;
        r1 = -2;
        r2.height = r1;
    L_0x00ce:
        r5 = r0;
        r24 = 0;
        r0 = (r23 > r22 ? 1 : (r23 == r22 ? 0 : -1));
        if (r0 != 0) goto L_0x00da;
    L_0x00d5:
        r0 = r7.mTotalLength;
        r28 = r0;
        goto L_0x00dc;
    L_0x00da:
        r28 = 0;
    L_0x00dc:
        r1 = r27;
        r0 = r57;
        r29 = r1;
        r8 = r25;
        r1 = r4;
        r30 = r2;
        r31 = r26;
        r2 = r29;
        r32 = r14;
        r14 = r3;
        r3 = r58;
        r33 = r4;
        r34 = r13;
        r9 = r19;
        r13 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r4 = r24;
        r13 = r5;
        r5 = r59;
        r35 = r11;
        r11 = r6;
        r6 = r28;
        r0.measureChildBeforeLayout(r1, r2, r3, r4, r5, r6);
        r0 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        if (r13 == r0) goto L_0x010e;
    L_0x0109:
        r0 = r30;
        r0.height = r13;
        goto L_0x0110;
    L_0x010e:
        r0 = r30;
    L_0x0110:
        r1 = r33.getMeasuredHeight();
        r2 = r7.mTotalLength;
        r3 = r2 + r1;
        r4 = r0.topMargin;
        r3 = r3 + r4;
        r4 = r0.bottomMargin;
        r3 = r3 + r4;
        r4 = r33;
        r5 = r7.getNextLocationOffset(r4);
        r3 = r3 + r5;
        r3 = java.lang.Math.max(r2, r3);
        r7.mTotalLength = r3;
        if (r10 == 0) goto L_0x0132;
    L_0x012d:
        r3 = java.lang.Math.max(r1, r14);
        goto L_0x0133;
    L_0x0132:
        r3 = r14;
    L_0x0133:
        if (r15 < 0) goto L_0x0140;
    L_0x0135:
        r1 = r29;
        r2 = r1 + 1;
        if (r15 != r2) goto L_0x0142;
    L_0x013b:
        r2 = r7.mTotalLength;
        r7.mBaselineChildTop = r2;
        goto L_0x0142;
    L_0x0140:
        r1 = r29;
    L_0x0142:
        if (r1 >= r15) goto L_0x0153;
    L_0x0144:
        r2 = r0.weight;
        r2 = (r2 > r22 ? 1 : (r2 == r22 ? 0 : -1));
        if (r2 > 0) goto L_0x014b;
    L_0x014a:
        goto L_0x0153;
    L_0x014b:
        r2 = new java.lang.RuntimeException;
        r5 = "A child of LinearLayout with index less than mBaselineAlignedChildIndex has weight > 0, which won't work.  Either remove the weight, or don't set mBaselineAlignedChildIndex.";
        r2.<init>(r5);
        throw r2;
    L_0x0153:
        r2 = 0;
        r5 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        if (r12 == r5) goto L_0x0161;
    L_0x0158:
        r5 = r0.width;
        r6 = -1;
        if (r5 != r6) goto L_0x0162;
    L_0x015d:
        r18 = 1;
        r2 = 1;
        goto L_0x0162;
    L_0x0161:
        r6 = -1;
    L_0x0162:
        r5 = r0.leftMargin;
        r13 = r0.rightMargin;
        r5 = r5 + r13;
        r13 = r4.getMeasuredWidth();
        r13 = r13 + r5;
        r14 = r31;
        r14 = java.lang.Math.max(r14, r13);
        r6 = r4.getMeasuredState();
        r6 = android.view.View.combineMeasuredStates(r8, r6);
        if (r17 == 0) goto L_0x0186;
    L_0x017d:
        r8 = r0.width;
        r36 = r3;
        r3 = -1;
        if (r8 != r3) goto L_0x0188;
    L_0x0184:
        r3 = 1;
        goto L_0x0189;
    L_0x0186:
        r36 = r3;
    L_0x0188:
        r3 = 0;
    L_0x0189:
        r8 = r0.weight;
        r8 = (r8 > r22 ? 1 : (r8 == r22 ? 0 : -1));
        if (r8 <= 0) goto L_0x019b;
    L_0x018f:
        if (r2 == 0) goto L_0x0193;
    L_0x0191:
        r8 = r5;
        goto L_0x0194;
    L_0x0193:
        r8 = r13;
    L_0x0194:
        r8 = java.lang.Math.max(r9, r8);
        r9 = r8;
        r8 = r11;
        goto L_0x01a4;
    L_0x019b:
        if (r2 == 0) goto L_0x019f;
    L_0x019d:
        r8 = r5;
        goto L_0x01a0;
    L_0x019f:
        r8 = r13;
    L_0x01a0:
        r8 = java.lang.Math.max(r11, r8);
    L_0x01a4:
        r11 = r7.getChildrenSkipCount(r4, r1);
        r0 = r1 + r11;
        r17 = r3;
        r1 = r6;
        r6 = r8;
        r4 = r9;
        r2 = r14;
        r5 = r23;
        r14 = r32;
        r3 = r36;
    L_0x01b6:
        r0 = r0 + 1;
        r13 = r34;
        r11 = r35;
        r8 = r58;
        r9 = r59;
        goto L_0x002f;
    L_0x01c2:
        r8 = r1;
        r0 = r2;
        r35 = r11;
        r34 = r13;
        r32 = r14;
        r9 = r19;
        r14 = r3;
        r11 = r6;
        r1 = r7.mTotalLength;
        if (r1 <= 0) goto L_0x01e2;
    L_0x01d2:
        r1 = r35;
        r2 = r7.hasDividerBeforeChildAt(r1);
        if (r2 == 0) goto L_0x01e4;
    L_0x01da:
        r2 = r7.mTotalLength;
        r3 = r7.mDividerHeight;
        r2 = r2 + r3;
        r7.mTotalLength = r2;
        goto L_0x01e4;
    L_0x01e2:
        r1 = r35;
    L_0x01e4:
        if (r10 == 0) goto L_0x0242;
    L_0x01e6:
        r2 = r34;
        r3 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        if (r2 == r3) goto L_0x01f2;
    L_0x01ec:
        if (r2 != 0) goto L_0x01ef;
    L_0x01ee:
        goto L_0x01f2;
    L_0x01ef:
        r37 = r0;
        goto L_0x0246;
    L_0x01f2:
        r3 = 0;
        r7.mTotalLength = r3;
        r3 = 0;
    L_0x01f6:
        if (r3 >= r1) goto L_0x023f;
    L_0x01f8:
        r4 = r7.getVirtualChildAt(r3);
        if (r4 != 0) goto L_0x0208;
    L_0x01fe:
        r6 = r7.mTotalLength;
        r13 = r7.measureNullChild(r3);
        r6 = r6 + r13;
        r7.mTotalLength = r6;
        goto L_0x0216;
    L_0x0208:
        r6 = r4.getVisibility();
        r13 = 8;
        if (r6 != r13) goto L_0x0219;
    L_0x0210:
        r6 = r7.getChildrenSkipCount(r4, r3);
        r3 = r3 + r6;
    L_0x0216:
        r37 = r0;
        goto L_0x023a;
        r6 = r4.getLayoutParams();
        r6 = (android.support.v7.widget.LinearLayoutCompat.LayoutParams) r6;
        r13 = r7.mTotalLength;
        r19 = r13 + r14;
        r37 = r0;
        r0 = r6.topMargin;
        r19 = r19 + r0;
        r0 = r6.bottomMargin;
        r19 = r19 + r0;
        r0 = r7.getNextLocationOffset(r4);
        r0 = r19 + r0;
        r0 = java.lang.Math.max(r13, r0);
        r7.mTotalLength = r0;
    L_0x023a:
        r3 = r3 + 1;
        r0 = r37;
        goto L_0x01f6;
    L_0x023f:
        r37 = r0;
        goto L_0x0246;
    L_0x0242:
        r37 = r0;
        r2 = r34;
    L_0x0246:
        r0 = r7.mTotalLength;
        r3 = r57.getPaddingTop();
        r4 = r57.getPaddingBottom();
        r3 = r3 + r4;
        r0 = r0 + r3;
        r7.mTotalLength = r0;
        r0 = r7.mTotalLength;
        r3 = r57.getSuggestedMinimumHeight();
        r0 = java.lang.Math.max(r0, r3);
        r4 = r9;
        r3 = r59;
        r6 = 0;
        r9 = android.view.View.resolveSizeAndState(r0, r3, r6);
        r6 = 16777215; // 0xffffff float:2.3509886E-38 double:8.2890456E-317;
        r0 = r9 & r6;
        r6 = r7.mTotalLength;
        r6 = r0 - r6;
        if (r32 != 0) goto L_0x02fb;
    L_0x0271:
        if (r6 == 0) goto L_0x0281;
    L_0x0273:
        r13 = (r5 > r22 ? 1 : (r5 == r22 ? 0 : -1));
        if (r13 <= 0) goto L_0x0281;
    L_0x0277:
        r38 = r0;
        r39 = r4;
        r40 = r5;
        r43 = r6;
        goto L_0x0303;
    L_0x0281:
        r11 = java.lang.Math.max(r11, r4);
        if (r10 == 0) goto L_0x02e3;
    L_0x0287:
        r13 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        if (r2 == r13) goto L_0x02e3;
    L_0x028b:
        r16 = 0;
    L_0x028d:
        r13 = r16;
        if (r13 >= r1) goto L_0x02e3;
    L_0x0291:
        r38 = r0;
        r0 = r7.getVirtualChildAt(r13);
        if (r0 == 0) goto L_0x02d2;
    L_0x0299:
        r39 = r4;
        r4 = r0.getVisibility();
        r40 = r5;
        r5 = 8;
        if (r4 != r5) goto L_0x02a9;
        r43 = r6;
        goto L_0x02d8;
        r4 = r0.getLayoutParams();
        r4 = (android.support.v7.widget.LinearLayoutCompat.LayoutParams) r4;
        r5 = r4.weight;
        r16 = (r5 > r22 ? 1 : (r5 == r22 ? 0 : -1));
        if (r16 <= 0) goto L_0x02cf;
        r41 = r4;
        r4 = r0.getMeasuredWidth();
        r42 = r5;
        r5 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r4 = android.view.View.MeasureSpec.makeMeasureSpec(r4, r5);
        r43 = r6;
        r6 = android.view.View.MeasureSpec.makeMeasureSpec(r14, r5);
        r0.measure(r4, r6);
        goto L_0x02d8;
    L_0x02cf:
        r43 = r6;
        goto L_0x02d8;
    L_0x02d2:
        r39 = r4;
        r40 = r5;
        r43 = r6;
    L_0x02d8:
        r16 = r13 + 1;
        r0 = r38;
        r4 = r39;
        r5 = r40;
        r6 = r43;
        goto L_0x028d;
    L_0x02e3:
        r38 = r0;
        r39 = r4;
        r40 = r5;
        r43 = r6;
        r50 = r2;
        r44 = r10;
        r45 = r14;
        r46 = r15;
        r6 = r37;
        r13 = r43;
        r14 = r58;
        goto L_0x0438;
    L_0x02fb:
        r38 = r0;
        r39 = r4;
        r40 = r5;
        r43 = r6;
    L_0x0303:
        r0 = r7.mWeightSum;
        r0 = (r0 > r22 ? 1 : (r0 == r22 ? 0 : -1));
        if (r0 <= 0) goto L_0x030c;
    L_0x0309:
        r5 = r7.mWeightSum;
        goto L_0x030e;
    L_0x030c:
        r5 = r40;
    L_0x030e:
        r0 = r5;
        r4 = 0;
        r7.mTotalLength = r4;
        r6 = r37;
        r13 = r43;
        r0 = 0;
    L_0x0317:
        if (r0 >= r1) goto L_0x041f;
    L_0x0319:
        r4 = r7.getVirtualChildAt(r0);
        r44 = r10;
        r10 = r4.getVisibility();
        r45 = r14;
        r14 = 8;
        if (r10 != r14) goto L_0x0332;
        r50 = r2;
        r46 = r15;
        r14 = r58;
        goto L_0x0412;
    L_0x0332:
        r10 = r4.getLayoutParams();
        r10 = (android.support.v7.widget.LinearLayoutCompat.LayoutParams) r10;
        r14 = r10.weight;
        r16 = (r14 > r22 ? 1 : (r14 == r22 ? 0 : -1));
        if (r16 <= 0) goto L_0x03ad;
    L_0x033e:
        r46 = r15;
        r15 = (float) r13;
        r15 = r15 * r14;
        r15 = r15 / r5;
        r15 = (int) r15;
        r5 = r5 - r14;
        r13 = r13 - r15;
        r16 = r57.getPaddingLeft();
        r19 = r57.getPaddingRight();
        r16 = r16 + r19;
        r47 = r5;
        r5 = r10.leftMargin;
        r16 = r16 + r5;
        r5 = r10.rightMargin;
        r5 = r16 + r5;
        r48 = r13;
        r13 = r10.width;
        r49 = r14;
        r14 = r58;
        r5 = getChildMeasureSpec(r14, r5, r13);
        r13 = r10.height;
        if (r13 != 0) goto L_0x0386;
    L_0x036c:
        r13 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        if (r2 == r13) goto L_0x0373;
    L_0x0370:
        r50 = r2;
        goto L_0x0388;
    L_0x0373:
        if (r15 <= 0) goto L_0x0377;
    L_0x0375:
        r13 = r15;
        goto L_0x0378;
    L_0x0377:
        r13 = 0;
    L_0x0378:
        r50 = r2;
        r2 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r13 = android.view.View.MeasureSpec.makeMeasureSpec(r13, r2);
        r4.measure(r5, r13);
        r51 = r15;
        goto L_0x039d;
    L_0x0386:
        r50 = r2;
    L_0x0388:
        r2 = r4.getMeasuredHeight();
        r2 = r2 + r15;
        if (r2 >= 0) goto L_0x0390;
    L_0x038f:
        r2 = 0;
        r51 = r15;
        r13 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r15 = android.view.View.MeasureSpec.makeMeasureSpec(r2, r13);
        r4.measure(r5, r15);
        r2 = r4.getMeasuredState();
        r2 = r2 & -256;
        r8 = android.view.View.combineMeasuredStates(r8, r2);
        r5 = r47;
        r13 = r48;
        goto L_0x03b5;
    L_0x03ad:
        r50 = r2;
        r49 = r14;
        r46 = r15;
        r14 = r58;
    L_0x03b5:
        r2 = r10.leftMargin;
        r15 = r10.rightMargin;
        r2 = r2 + r15;
        r15 = r4.getMeasuredWidth();
        r15 = r15 + r2;
        r6 = java.lang.Math.max(r6, r15);
        r52 = r2;
        r2 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        if (r12 == r2) goto L_0x03d2;
    L_0x03c9:
        r2 = r10.width;
        r53 = r5;
        r5 = -1;
        if (r2 != r5) goto L_0x03d4;
    L_0x03d0:
        r2 = 1;
        goto L_0x03d5;
    L_0x03d2:
        r53 = r5;
    L_0x03d4:
        r2 = 0;
    L_0x03d5:
        if (r2 == 0) goto L_0x03da;
    L_0x03d7:
        r5 = r52;
        goto L_0x03db;
    L_0x03da:
        r5 = r15;
    L_0x03db:
        r5 = java.lang.Math.max(r11, r5);
        if (r17 == 0) goto L_0x03ea;
    L_0x03e1:
        r11 = r10.width;
        r54 = r2;
        r2 = -1;
        if (r11 != r2) goto L_0x03ed;
    L_0x03e8:
        r11 = 1;
        goto L_0x03ee;
    L_0x03ea:
        r54 = r2;
        r2 = -1;
    L_0x03ed:
        r11 = 0;
    L_0x03ee:
        r2 = r7.mTotalLength;
        r16 = r4.getMeasuredHeight();
        r16 = r2 + r16;
        r55 = r5;
        r5 = r10.topMargin;
        r16 = r16 + r5;
        r5 = r10.bottomMargin;
        r16 = r16 + r5;
        r5 = r7.getNextLocationOffset(r4);
        r5 = r16 + r5;
        r5 = java.lang.Math.max(r2, r5);
        r7.mTotalLength = r5;
        r17 = r11;
        r5 = r53;
        r11 = r55;
    L_0x0412:
        r0 = r0 + 1;
        r10 = r44;
        r14 = r45;
        r15 = r46;
        r2 = r50;
        r4 = 0;
        goto L_0x0317;
    L_0x041f:
        r50 = r2;
        r44 = r10;
        r45 = r14;
        r46 = r15;
        r14 = r58;
        r0 = r7.mTotalLength;
        r2 = r57.getPaddingTop();
        r4 = r57.getPaddingBottom();
        r2 = r2 + r4;
        r0 = r0 + r2;
        r7.mTotalLength = r0;
    L_0x0438:
        if (r17 != 0) goto L_0x043f;
    L_0x043a:
        r0 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        if (r12 == r0) goto L_0x043f;
    L_0x043e:
        r6 = r11;
    L_0x043f:
        r0 = r57.getPaddingLeft();
        r2 = r57.getPaddingRight();
        r0 = r0 + r2;
        r6 = r6 + r0;
        r0 = r57.getSuggestedMinimumWidth();
        r0 = java.lang.Math.max(r6, r0);
        r2 = android.view.View.resolveSizeAndState(r0, r14, r8);
        r7.setMeasuredDimension(r2, r9);
        if (r18 == 0) goto L_0x045d;
    L_0x045a:
        r7.forceUniformWidth(r1, r3);
    L_0x045d:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.LinearLayoutCompat.measureVertical(int, int):void");
    }

    private void forceUniformWidth(int count, int heightMeasureSpec) {
        int uniformMeasureSpec = MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824);
        for (int i = 0; i < count; i++) {
            View child = getVirtualChildAt(i);
            if (child.getVisibility() != 8) {
                LayoutParams lp = (LayoutParams) child.getLayoutParams();
                if (lp.width == -1) {
                    int oldHeight = lp.height;
                    lp.height = child.getMeasuredHeight();
                    measureChildWithMargins(child, uniformMeasureSpec, 0, heightMeasureSpec, 0);
                    lp.height = oldHeight;
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:81:0x021f  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x020f  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x01c9  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0201  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x020f  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x021f  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x01c9  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0201  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x021f  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x020f  */
    /* JADX WARNING: Removed duplicated region for block: B:225:0x060a  */
    /* JADX WARNING: Removed duplicated region for block: B:224:0x0602  */
    void measureHorizontal(int r65, int r66) {
        /*
        r64 = this;
        r7 = r64;
        r8 = r65;
        r9 = r66;
        r10 = 0;
        r7.mTotalLength = r10;
        r0 = 0;
        r1 = 0;
        r2 = 0;
        r3 = 0;
        r4 = 1;
        r5 = 0;
        r11 = r64.getVirtualChildCount();
        r12 = android.view.View.MeasureSpec.getMode(r65);
        r13 = android.view.View.MeasureSpec.getMode(r66);
        r6 = 0;
        r14 = 0;
        r15 = r7.mMaxAscent;
        if (r15 == 0) goto L_0x0025;
    L_0x0021:
        r15 = r7.mMaxDescent;
        if (r15 != 0) goto L_0x002e;
    L_0x0025:
        r15 = 4;
        r10 = new int[r15];
        r7.mMaxAscent = r10;
        r10 = new int[r15];
        r7.mMaxDescent = r10;
    L_0x002e:
        r10 = r7.mMaxAscent;
        r15 = r7.mMaxDescent;
        r17 = 3;
        r18 = r6;
        r6 = -1;
        r10[r17] = r6;
        r19 = 2;
        r10[r19] = r6;
        r20 = 1;
        r10[r20] = r6;
        r16 = 0;
        r10[r16] = r6;
        r15[r17] = r6;
        r15[r19] = r6;
        r15[r20] = r6;
        r15[r16] = r6;
        r6 = r7.mBaselineAligned;
        r22 = r14;
        r14 = r7.mUseLargestChild;
        r9 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        if (r12 != r9) goto L_0x005a;
    L_0x0057:
        r23 = 1;
        goto L_0x005c;
    L_0x005a:
        r23 = 0;
    L_0x005c:
        r24 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r25 = r1;
        r1 = r24;
        r24 = r18;
        r18 = r4;
        r4 = r0;
        r0 = 0;
        r63 = r3;
        r3 = r2;
        r2 = r63;
    L_0x006d:
        r28 = 0;
        if (r0 >= r11) goto L_0x024c;
    L_0x0071:
        r9 = r7.getVirtualChildAt(r0);
        if (r9 != 0) goto L_0x008c;
    L_0x0077:
        r30 = r1;
        r1 = r7.mTotalLength;
        r27 = r7.measureNullChild(r0);
        r1 = r1 + r27;
        r7.mTotalLength = r1;
        r21 = r6;
        r41 = r12;
        r1 = r30;
        goto L_0x0240;
    L_0x008c:
        r30 = r1;
        r1 = r9.getVisibility();
        r31 = r2;
        r2 = 8;
        if (r1 != r2) goto L_0x00a8;
    L_0x0098:
        r1 = r7.getChildrenSkipCount(r9, r0);
        r0 = r0 + r1;
        r21 = r6;
        r41 = r12;
        r1 = r30;
        r2 = r31;
        goto L_0x0240;
    L_0x00a8:
        r1 = r7.hasDividerBeforeChildAt(r0);
        if (r1 == 0) goto L_0x00b5;
    L_0x00ae:
        r1 = r7.mTotalLength;
        r2 = r7.mDividerWidth;
        r1 = r1 + r2;
        r7.mTotalLength = r1;
        r1 = r9.getLayoutParams();
        r2 = r1;
        r2 = (android.support.v7.widget.LinearLayoutCompat.LayoutParams) r2;
        r1 = r2.weight;
        r29 = r5 + r1;
        r1 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        if (r12 != r1) goto L_0x011d;
    L_0x00c5:
        r1 = r2.width;
        if (r1 != 0) goto L_0x011d;
    L_0x00c9:
        r1 = r2.weight;
        r1 = (r1 > r28 ? 1 : (r1 == r28 ? 0 : -1));
        if (r1 <= 0) goto L_0x011d;
    L_0x00cf:
        if (r23 == 0) goto L_0x00de;
    L_0x00d1:
        r1 = r7.mTotalLength;
        r5 = r2.leftMargin;
        r32 = r0;
        r0 = r2.rightMargin;
        r5 = r5 + r0;
        r1 = r1 + r5;
        r7.mTotalLength = r1;
        goto L_0x00ee;
    L_0x00de:
        r32 = r0;
        r0 = r7.mTotalLength;
        r1 = r2.leftMargin;
        r1 = r1 + r0;
        r5 = r2.rightMargin;
        r1 = r1 + r5;
        r1 = java.lang.Math.max(r0, r1);
        r7.mTotalLength = r1;
    L_0x00ee:
        if (r6 == 0) goto L_0x010b;
    L_0x00f0:
        r0 = 0;
        r1 = android.view.View.MeasureSpec.makeMeasureSpec(r0, r0);
        r9.measure(r1, r1);
        r0 = r2;
        r39 = r3;
        r40 = r4;
        r21 = r6;
        r41 = r12;
        r2 = r30;
        r38 = r31;
        r35 = r32;
        r12 = -1;
        goto L_0x01a5;
    L_0x010b:
        r22 = 1;
        r0 = r2;
        r39 = r3;
        r40 = r4;
        r21 = r6;
        r41 = r12;
        r38 = r31;
        r35 = r32;
        r12 = -1;
        goto L_0x01a7;
    L_0x011d:
        r32 = r0;
        r0 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r1 = r2.width;
        if (r1 != 0) goto L_0x012f;
    L_0x0125:
        r1 = r2.weight;
        r1 = (r1 > r28 ? 1 : (r1 == r28 ? 0 : -1));
        if (r1 <= 0) goto L_0x012f;
    L_0x012b:
        r0 = 0;
        r1 = -2;
        r2.width = r1;
    L_0x012f:
        r5 = r0;
        r0 = (r29 > r28 ? 1 : (r29 == r28 ? 0 : -1));
        if (r0 != 0) goto L_0x0139;
    L_0x0134:
        r0 = r7.mTotalLength;
        r33 = r0;
        goto L_0x013b;
    L_0x0139:
        r33 = 0;
    L_0x013b:
        r34 = 0;
        r1 = r32;
        r0 = r64;
        r35 = r1;
        r36 = r30;
        r1 = r9;
        r37 = r2;
        r38 = r31;
        r2 = r35;
        r39 = r3;
        r3 = r65;
        r40 = r4;
        r4 = r33;
        r8 = r5;
        r5 = r66;
        r21 = r6;
        r41 = r12;
        r12 = -1;
        r6 = r34;
        r0.measureChildBeforeLayout(r1, r2, r3, r4, r5, r6);
        r0 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        if (r8 == r0) goto L_0x016a;
    L_0x0165:
        r0 = r37;
        r0.width = r8;
        goto L_0x016c;
    L_0x016a:
        r0 = r37;
    L_0x016c:
        r1 = r9.getMeasuredWidth();
        if (r23 == 0) goto L_0x0183;
    L_0x0172:
        r2 = r7.mTotalLength;
        r3 = r0.leftMargin;
        r3 = r3 + r1;
        r4 = r0.rightMargin;
        r3 = r3 + r4;
        r4 = r7.getNextLocationOffset(r9);
        r3 = r3 + r4;
        r2 = r2 + r3;
        r7.mTotalLength = r2;
        goto L_0x0198;
    L_0x0183:
        r2 = r7.mTotalLength;
        r3 = r2 + r1;
        r4 = r0.leftMargin;
        r3 = r3 + r4;
        r4 = r0.rightMargin;
        r3 = r3 + r4;
        r4 = r7.getNextLocationOffset(r9);
        r3 = r3 + r4;
        r3 = java.lang.Math.max(r2, r3);
        r7.mTotalLength = r3;
    L_0x0198:
        if (r14 == 0) goto L_0x01a3;
    L_0x019a:
        r2 = r36;
        r1 = java.lang.Math.max(r1, r2);
        r30 = r1;
        goto L_0x01a7;
    L_0x01a3:
        r2 = r36;
    L_0x01a5:
        r30 = r2;
    L_0x01a7:
        r1 = 0;
        r2 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        if (r13 == r2) goto L_0x01b3;
    L_0x01ac:
        r2 = r0.height;
        if (r2 != r12) goto L_0x01b3;
    L_0x01b0:
        r24 = 1;
        r1 = 1;
    L_0x01b3:
        r2 = r0.topMargin;
        r3 = r0.bottomMargin;
        r2 = r2 + r3;
        r3 = r9.getMeasuredHeight();
        r3 = r3 + r2;
        r4 = r9.getMeasuredState();
        r6 = r25;
        r4 = android.view.View.combineMeasuredStates(r6, r4);
        if (r21 == 0) goto L_0x01f7;
    L_0x01c9:
        r5 = r9.getBaseline();
        if (r5 == r12) goto L_0x01f7;
    L_0x01cf:
        r6 = r0.gravity;
        if (r6 >= 0) goto L_0x01d6;
    L_0x01d3:
        r6 = r7.mGravity;
        goto L_0x01d8;
    L_0x01d6:
        r6 = r0.gravity;
    L_0x01d8:
        r6 = r6 & 112;
        r8 = r6 >> 4;
        r25 = -2;
        r8 = r8 & -2;
        r8 = r8 >> 1;
        r12 = r10[r8];
        r12 = java.lang.Math.max(r12, r5);
        r10[r8] = r12;
        r12 = r15[r8];
        r42 = r2;
        r2 = r3 - r5;
        r2 = java.lang.Math.max(r12, r2);
        r15[r8] = r2;
        goto L_0x01f9;
    L_0x01f7:
        r42 = r2;
    L_0x01f9:
        r8 = r40;
        r2 = java.lang.Math.max(r8, r3);
        if (r18 == 0) goto L_0x0208;
    L_0x0201:
        r5 = r0.height;
        r6 = -1;
        if (r5 != r6) goto L_0x0208;
    L_0x0206:
        r5 = 1;
        goto L_0x0209;
    L_0x0208:
        r5 = 0;
    L_0x0209:
        r6 = r0.weight;
        r6 = (r6 > r28 ? 1 : (r6 == r28 ? 0 : -1));
        if (r6 <= 0) goto L_0x021f;
    L_0x020f:
        if (r1 == 0) goto L_0x0214;
    L_0x0211:
        r6 = r42;
        goto L_0x0215;
    L_0x0214:
        r6 = r3;
    L_0x0215:
        r12 = r38;
        r6 = java.lang.Math.max(r12, r6);
        r12 = r6;
        r6 = r39;
        goto L_0x022d;
    L_0x021f:
        r12 = r38;
        if (r1 == 0) goto L_0x0226;
    L_0x0223:
        r6 = r42;
        goto L_0x0227;
    L_0x0226:
        r6 = r3;
    L_0x0227:
        r8 = r39;
        r6 = java.lang.Math.max(r8, r6);
    L_0x022d:
        r8 = r35;
        r18 = r7.getChildrenSkipCount(r9, r8);
        r0 = r8 + r18;
        r25 = r4;
        r18 = r5;
        r3 = r6;
        r5 = r29;
        r1 = r30;
        r4 = r2;
        r2 = r12;
    L_0x0240:
        r0 = r0 + 1;
        r6 = r21;
        r12 = r41;
        r8 = r65;
        r9 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        goto L_0x006d;
    L_0x024c:
        r8 = r4;
        r21 = r6;
        r41 = r12;
        r6 = r25;
        r12 = r2;
        r2 = r1;
        r0 = r7.mTotalLength;
        if (r0 <= 0) goto L_0x0266;
    L_0x0259:
        r0 = r7.hasDividerBeforeChildAt(r11);
        if (r0 == 0) goto L_0x0266;
    L_0x025f:
        r0 = r7.mTotalLength;
        r1 = r7.mDividerWidth;
        r0 = r0 + r1;
        r7.mTotalLength = r0;
    L_0x0266:
        r0 = r10[r20];
        r1 = -1;
        if (r0 != r1) goto L_0x027d;
    L_0x026b:
        r0 = 0;
        r4 = r10[r0];
        if (r4 != r1) goto L_0x027d;
    L_0x0270:
        r0 = r10[r19];
        if (r0 != r1) goto L_0x027d;
    L_0x0274:
        r0 = r10[r17];
        if (r0 == r1) goto L_0x0279;
    L_0x0278:
        goto L_0x027d;
    L_0x0279:
        r43 = r6;
        r4 = r8;
        goto L_0x02af;
    L_0x027d:
        r0 = r10[r17];
        r1 = 0;
        r4 = r10[r1];
        r9 = r10[r20];
        r1 = r10[r19];
        r1 = java.lang.Math.max(r9, r1);
        r1 = java.lang.Math.max(r4, r1);
        r0 = java.lang.Math.max(r0, r1);
        r1 = r15[r17];
        r4 = 0;
        r9 = r15[r4];
        r4 = r15[r20];
        r43 = r6;
        r6 = r15[r19];
        r4 = java.lang.Math.max(r4, r6);
        r4 = java.lang.Math.max(r9, r4);
        r1 = java.lang.Math.max(r1, r4);
        r4 = r0 + r1;
        r4 = java.lang.Math.max(r8, r4);
    L_0x02af:
        if (r14 == 0) goto L_0x0326;
    L_0x02b1:
        r0 = r41;
        r1 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        if (r0 == r1) goto L_0x02be;
    L_0x02b7:
        if (r0 != 0) goto L_0x02ba;
    L_0x02b9:
        goto L_0x02be;
    L_0x02ba:
        r45 = r4;
        goto L_0x032a;
    L_0x02be:
        r1 = 0;
        r7.mTotalLength = r1;
        r1 = 0;
    L_0x02c2:
        if (r1 >= r11) goto L_0x0323;
    L_0x02c4:
        r6 = r7.getVirtualChildAt(r1);
        if (r6 != 0) goto L_0x02d4;
    L_0x02ca:
        r8 = r7.mTotalLength;
        r9 = r7.measureNullChild(r1);
        r8 = r8 + r9;
        r7.mTotalLength = r8;
        goto L_0x02e2;
    L_0x02d4:
        r8 = r6.getVisibility();
        r9 = 8;
        if (r8 != r9) goto L_0x02e7;
    L_0x02dc:
        r8 = r7.getChildrenSkipCount(r6, r1);
        r1 = r1 + r8;
    L_0x02e2:
        r44 = r1;
        r45 = r4;
        goto L_0x031e;
        r8 = r6.getLayoutParams();
        r8 = (android.support.v7.widget.LinearLayoutCompat.LayoutParams) r8;
        if (r23 == 0) goto L_0x0305;
    L_0x02f0:
        r9 = r7.mTotalLength;
        r44 = r1;
        r1 = r8.leftMargin;
        r1 = r1 + r2;
        r45 = r4;
        r4 = r8.rightMargin;
        r1 = r1 + r4;
        r4 = r7.getNextLocationOffset(r6);
        r1 = r1 + r4;
        r9 = r9 + r1;
        r7.mTotalLength = r9;
        goto L_0x031e;
    L_0x0305:
        r44 = r1;
        r45 = r4;
        r1 = r7.mTotalLength;
        r4 = r1 + r2;
        r9 = r8.leftMargin;
        r4 = r4 + r9;
        r9 = r8.rightMargin;
        r4 = r4 + r9;
        r9 = r7.getNextLocationOffset(r6);
        r4 = r4 + r9;
        r4 = java.lang.Math.max(r1, r4);
        r7.mTotalLength = r4;
    L_0x031e:
        r1 = r44 + 1;
        r4 = r45;
        goto L_0x02c2;
    L_0x0323:
        r45 = r4;
        goto L_0x032a;
    L_0x0326:
        r45 = r4;
        r0 = r41;
    L_0x032a:
        r1 = r7.mTotalLength;
        r4 = r64.getPaddingLeft();
        r6 = r64.getPaddingRight();
        r4 = r4 + r6;
        r1 = r1 + r4;
        r7.mTotalLength = r1;
        r1 = r7.mTotalLength;
        r4 = r64.getSuggestedMinimumWidth();
        r1 = java.lang.Math.max(r1, r4);
        r4 = r65;
        r6 = 0;
        r8 = android.view.View.resolveSizeAndState(r1, r4, r6);
        r6 = 16777215; // 0xffffff float:2.3509886E-38 double:8.2890456E-317;
        r1 = r8 & r6;
        r6 = r7.mTotalLength;
        r6 = r1 - r6;
        if (r22 != 0) goto L_0x03e6;
    L_0x0354:
        if (r6 == 0) goto L_0x0365;
    L_0x0356:
        r25 = (r5 > r28 ? 1 : (r5 == r28 ? 0 : -1));
        if (r25 <= 0) goto L_0x0365;
    L_0x035a:
        r46 = r1;
        r51 = r2;
        r2 = r3;
        r48 = r5;
        r3 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        goto L_0x03ef;
    L_0x0365:
        r3 = java.lang.Math.max(r3, r12);
        if (r14 == 0) goto L_0x03cd;
    L_0x036b:
        r9 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        if (r0 == r9) goto L_0x03cd;
    L_0x036f:
        r16 = 0;
    L_0x0371:
        r9 = r16;
        if (r9 >= r11) goto L_0x03cd;
    L_0x0375:
        r46 = r1;
        r1 = r7.getVirtualChildAt(r9);
        if (r1 == 0) goto L_0x03ba;
    L_0x037d:
        r47 = r3;
        r3 = r1.getVisibility();
        r48 = r5;
        r5 = 8;
        if (r3 != r5) goto L_0x038f;
        r51 = r2;
        r3 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        goto L_0x03c2;
        r3 = r1.getLayoutParams();
        r3 = (android.support.v7.widget.LinearLayoutCompat.LayoutParams) r3;
        r5 = r3.weight;
        r16 = (r5 > r28 ? 1 : (r5 == r28 ? 0 : -1));
        if (r16 <= 0) goto L_0x03b5;
        r49 = r3;
        r50 = r5;
        r3 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r5 = android.view.View.MeasureSpec.makeMeasureSpec(r2, r3);
        r51 = r2;
        r2 = r1.getMeasuredHeight();
        r2 = android.view.View.MeasureSpec.makeMeasureSpec(r2, r3);
        r1.measure(r5, r2);
        goto L_0x03c2;
    L_0x03b5:
        r51 = r2;
        r3 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        goto L_0x03c2;
    L_0x03ba:
        r51 = r2;
        r47 = r3;
        r48 = r5;
        r3 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
    L_0x03c2:
        r16 = r9 + 1;
        r1 = r46;
        r3 = r47;
        r5 = r48;
        r2 = r51;
        goto L_0x0371;
    L_0x03cd:
        r46 = r1;
        r51 = r2;
        r47 = r3;
        r48 = r5;
        r3 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r59 = r0;
        r0 = r6;
        r58 = r8;
        r54 = r11;
        r52 = r12;
        r53 = r14;
        r6 = r66;
        goto L_0x05d6;
    L_0x03e6:
        r46 = r1;
        r51 = r2;
        r2 = r3;
        r48 = r5;
        r3 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
    L_0x03ef:
        r1 = r7.mWeightSum;
        r1 = (r1 > r28 ? 1 : (r1 == r28 ? 0 : -1));
        if (r1 <= 0) goto L_0x03f8;
    L_0x03f5:
        r5 = r7.mWeightSum;
        goto L_0x03fa;
    L_0x03f8:
        r5 = r48;
    L_0x03fa:
        r1 = r5;
        r5 = -1;
        r10[r17] = r5;
        r10[r19] = r5;
        r10[r20] = r5;
        r9 = 0;
        r10[r9] = r5;
        r15[r17] = r5;
        r15[r19] = r5;
        r15[r20] = r5;
        r15[r9] = r5;
        r5 = -1;
        r7.mTotalLength = r9;
        r3 = r2;
        r9 = r43;
        r2 = r1;
        r1 = 0;
    L_0x0415:
        if (r1 >= r11) goto L_0x0570;
    L_0x0417:
        r52 = r12;
        r12 = r7.getVirtualChildAt(r1);
        if (r12 == 0) goto L_0x054f;
    L_0x041f:
        r53 = r14;
        r14 = r12.getVisibility();
        r4 = 8;
        if (r14 != r4) goto L_0x0437;
        r59 = r0;
        r0 = r6;
        r58 = r8;
        r54 = r11;
        r6 = r66;
        r25 = -2;
        goto L_0x055c;
        r14 = r12.getLayoutParams();
        r14 = (android.support.v7.widget.LinearLayoutCompat.LayoutParams) r14;
        r4 = r14.weight;
        r25 = (r4 > r28 ? 1 : (r4 == r28 ? 0 : -1));
        if (r25 <= 0) goto L_0x04a8;
    L_0x0444:
        r54 = r11;
        r11 = (float) r6;
        r11 = r11 * r4;
        r11 = r11 / r2;
        r11 = (int) r11;
        r2 = r2 - r4;
        r6 = r6 - r11;
        r25 = r64.getPaddingTop();
        r26 = r64.getPaddingBottom();
        r25 = r25 + r26;
        r55 = r2;
        r2 = r14.topMargin;
        r25 = r25 + r2;
        r2 = r14.bottomMargin;
        r2 = r25 + r2;
        r56 = r4;
        r4 = r14.height;
        r57 = r6;
        r58 = r8;
        r6 = r66;
        r8 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r2 = getChildMeasureSpec(r6, r2, r4);
        r4 = r14.width;
        if (r4 != 0) goto L_0x0488;
    L_0x0476:
        if (r0 == r8) goto L_0x0479;
    L_0x0478:
        goto L_0x0488;
    L_0x0479:
        if (r11 <= 0) goto L_0x047d;
    L_0x047b:
        r4 = r11;
        goto L_0x047e;
    L_0x047d:
        r4 = 0;
    L_0x047e:
        r4 = android.view.View.MeasureSpec.makeMeasureSpec(r4, r8);
        r12.measure(r4, r2);
        r59 = r0;
        goto L_0x049b;
    L_0x0488:
        r4 = r12.getMeasuredWidth();
        r4 = r4 + r11;
        if (r4 >= 0) goto L_0x0490;
    L_0x048f:
        r4 = 0;
        r59 = r0;
        r0 = android.view.View.MeasureSpec.makeMeasureSpec(r4, r8);
        r12.measure(r0, r2);
        r0 = r12.getMeasuredState();
        r4 = -16777216; // 0xffffffffff000000 float:-1.7014118E38 double:NaN;
        r0 = r0 & r4;
        r9 = android.view.View.combineMeasuredStates(r9, r0);
        goto L_0x04b9;
    L_0x04a8:
        r59 = r0;
        r56 = r4;
        r0 = r6;
        r58 = r8;
        r54 = r11;
        r6 = r66;
        r8 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r57 = r0;
        r55 = r2;
    L_0x04b9:
        if (r23 == 0) goto L_0x04d0;
    L_0x04bb:
        r0 = r7.mTotalLength;
        r2 = r12.getMeasuredWidth();
        r4 = r14.leftMargin;
        r2 = r2 + r4;
        r4 = r14.rightMargin;
        r2 = r2 + r4;
        r4 = r7.getNextLocationOffset(r12);
        r2 = r2 + r4;
        r0 = r0 + r2;
        r7.mTotalLength = r0;
        goto L_0x04e8;
    L_0x04d0:
        r0 = r7.mTotalLength;
        r2 = r12.getMeasuredWidth();
        r2 = r2 + r0;
        r4 = r14.leftMargin;
        r2 = r2 + r4;
        r4 = r14.rightMargin;
        r2 = r2 + r4;
        r4 = r7.getNextLocationOffset(r12);
        r2 = r2 + r4;
        r2 = java.lang.Math.max(r0, r2);
        r7.mTotalLength = r2;
    L_0x04e8:
        if (r13 == r8) goto L_0x04f1;
    L_0x04ea:
        r0 = r14.height;
        r2 = -1;
        if (r0 != r2) goto L_0x04f1;
    L_0x04ef:
        r0 = 1;
        goto L_0x04f2;
    L_0x04f1:
        r0 = 0;
    L_0x04f2:
        r2 = r14.topMargin;
        r4 = r14.bottomMargin;
        r2 = r2 + r4;
        r4 = r12.getMeasuredHeight();
        r4 = r4 + r2;
        r5 = java.lang.Math.max(r5, r4);
        if (r0 == 0) goto L_0x0504;
    L_0x0502:
        r11 = r2;
        goto L_0x0505;
    L_0x0504:
        r11 = r4;
    L_0x0505:
        r3 = java.lang.Math.max(r3, r11);
        if (r18 == 0) goto L_0x0512;
    L_0x050b:
        r11 = r14.height;
        r8 = -1;
        if (r11 != r8) goto L_0x0512;
    L_0x0510:
        r8 = 1;
        goto L_0x0513;
    L_0x0512:
        r8 = 0;
    L_0x0513:
        if (r21 == 0) goto L_0x0548;
    L_0x0515:
        r11 = r12.getBaseline();
        r60 = r0;
        r0 = -1;
        if (r11 == r0) goto L_0x0548;
    L_0x051e:
        r0 = r14.gravity;
        if (r0 >= 0) goto L_0x0525;
    L_0x0522:
        r0 = r7.mGravity;
        goto L_0x0527;
    L_0x0525:
        r0 = r14.gravity;
    L_0x0527:
        r0 = r0 & 112;
        r18 = r0 >> 4;
        r25 = -2;
        r18 = r18 & -2;
        r18 = r18 >> 1;
        r61 = r0;
        r0 = r10[r18];
        r0 = java.lang.Math.max(r0, r11);
        r10[r18] = r0;
        r0 = r15[r18];
        r62 = r2;
        r2 = r4 - r11;
        r0 = java.lang.Math.max(r0, r2);
        r15[r18] = r0;
        goto L_0x054a;
    L_0x0548:
        r25 = -2;
    L_0x054a:
        r18 = r8;
        r2 = r55;
        goto L_0x055e;
    L_0x054f:
        r59 = r0;
        r0 = r6;
        r58 = r8;
        r54 = r11;
        r53 = r14;
        r6 = r66;
        r25 = -2;
    L_0x055c:
        r57 = r0;
    L_0x055e:
        r1 = r1 + 1;
        r12 = r52;
        r14 = r53;
        r11 = r54;
        r6 = r57;
        r8 = r58;
        r0 = r59;
        r4 = r65;
        goto L_0x0415;
    L_0x0570:
        r59 = r0;
        r0 = r6;
        r58 = r8;
        r54 = r11;
        r52 = r12;
        r53 = r14;
        r6 = r66;
        r1 = r7.mTotalLength;
        r4 = r64.getPaddingLeft();
        r8 = r64.getPaddingRight();
        r4 = r4 + r8;
        r1 = r1 + r4;
        r7.mTotalLength = r1;
        r1 = r10[r20];
        r4 = -1;
        if (r1 != r4) goto L_0x05a0;
    L_0x0590:
        r1 = 0;
        r8 = r10[r1];
        if (r8 != r4) goto L_0x05a0;
    L_0x0595:
        r1 = r10[r19];
        if (r1 != r4) goto L_0x05a0;
    L_0x0599:
        r1 = r10[r17];
        if (r1 == r4) goto L_0x059e;
    L_0x059d:
        goto L_0x05a0;
    L_0x059e:
        r4 = r5;
        goto L_0x05d0;
    L_0x05a0:
        r1 = r10[r17];
        r4 = 0;
        r8 = r10[r4];
        r11 = r10[r20];
        r12 = r10[r19];
        r11 = java.lang.Math.max(r11, r12);
        r8 = java.lang.Math.max(r8, r11);
        r1 = java.lang.Math.max(r1, r8);
        r8 = r15[r17];
        r4 = r15[r4];
        r11 = r15[r20];
        r12 = r15[r19];
        r11 = java.lang.Math.max(r11, r12);
        r4 = java.lang.Math.max(r4, r11);
        r4 = java.lang.Math.max(r8, r4);
        r8 = r1 + r4;
        r1 = java.lang.Math.max(r5, r8);
        r4 = r1;
    L_0x05d0:
        r47 = r3;
        r45 = r4;
        r43 = r9;
    L_0x05d6:
        if (r18 != 0) goto L_0x05de;
    L_0x05d8:
        r1 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        if (r13 == r1) goto L_0x05de;
    L_0x05dc:
        r45 = r47;
    L_0x05de:
        r1 = r64.getPaddingTop();
        r2 = r64.getPaddingBottom();
        r1 = r1 + r2;
        r1 = r45 + r1;
        r2 = r64.getSuggestedMinimumHeight();
        r1 = java.lang.Math.max(r1, r2);
        r2 = -16777216; // 0xffffffffff000000 float:-1.7014118E38 double:NaN;
        r2 = r43 & r2;
        r2 = r58 | r2;
        r3 = r43 << 16;
        r3 = android.view.View.resolveSizeAndState(r1, r6, r3);
        r7.setMeasuredDimension(r2, r3);
        if (r24 == 0) goto L_0x060a;
    L_0x0602:
        r3 = r54;
        r2 = r65;
        r7.forceUniformHeight(r3, r2);
        goto L_0x060e;
    L_0x060a:
        r3 = r54;
        r2 = r65;
    L_0x060e:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.LinearLayoutCompat.measureHorizontal(int, int):void");
    }

    private void forceUniformHeight(int count, int widthMeasureSpec) {
        int uniformMeasureSpec = MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824);
        for (int i = 0; i < count; i++) {
            View child = getVirtualChildAt(i);
            if (child.getVisibility() != 8) {
                LayoutParams lp = (LayoutParams) child.getLayoutParams();
                if (lp.height == -1) {
                    int oldWidth = lp.width;
                    lp.width = child.getMeasuredWidth();
                    measureChildWithMargins(child, widthMeasureSpec, 0, uniformMeasureSpec, 0);
                    lp.width = oldWidth;
                }
            }
        }
    }

    int getChildrenSkipCount(View child, int index) {
        return 0;
    }

    int measureNullChild(int childIndex) {
        return 0;
    }

    void measureChildBeforeLayout(View child, int childIndex, int widthMeasureSpec, int totalWidth, int heightMeasureSpec, int totalHeight) {
        measureChildWithMargins(child, widthMeasureSpec, totalWidth, heightMeasureSpec, totalHeight);
    }

    int getLocationOffset(View child) {
        return 0;
    }

    int getNextLocationOffset(View child) {
        return 0;
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (this.mOrientation == 1) {
            layoutVertical(l, t, r, b);
        } else {
            layoutHorizontal(l, t, r, b);
        }
    }

    void layoutVertical(int left, int top, int right, int bottom) {
        int childTop;
        int paddingLeft = getPaddingLeft();
        int width = right - left;
        int childRight = width - getPaddingRight();
        int childSpace = (width - paddingLeft) - getPaddingRight();
        int count = getVirtualChildCount();
        int majorGravity = this.mGravity & 112;
        int minorGravity = this.mGravity & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK;
        if (majorGravity == 16) {
            childTop = getPaddingTop() + (((bottom - top) - this.mTotalLength) / 2);
        } else if (majorGravity != 80) {
            childTop = getPaddingTop();
        } else {
            childTop = ((getPaddingTop() + bottom) - top) - this.mTotalLength;
        }
        int i = 0;
        while (true) {
            int i2 = i;
            int paddingLeft2;
            if (i2 < count) {
                int majorGravity2;
                View child = getVirtualChildAt(i2);
                if (child == null) {
                    childTop += measureNullChild(i2);
                    majorGravity2 = majorGravity;
                    paddingLeft2 = paddingLeft;
                } else if (child.getVisibility() != 8) {
                    int childWidth = child.getMeasuredWidth();
                    int childHeight = child.getMeasuredHeight();
                    LayoutParams lp = (LayoutParams) child.getLayoutParams();
                    int gravity = lp.gravity;
                    if (gravity < 0) {
                        gravity = minorGravity;
                    }
                    int layoutDirection = ViewCompat.getLayoutDirection(this);
                    int gravity2 = gravity;
                    gravity = GravityCompat.getAbsoluteGravity(gravity, layoutDirection) & 7;
                    majorGravity2 = majorGravity;
                    gravity = gravity != 1 ? gravity != 5 ? lp.leftMargin + paddingLeft : (childRight - childWidth) - lp.rightMargin : ((((childSpace - childWidth) / 2) + paddingLeft) + lp.leftMargin) - lp.rightMargin;
                    if (hasDividerBeforeChildAt(i2) != 0) {
                        childTop += this.mDividerHeight;
                    }
                    gravity2 = childTop + lp.topMargin;
                    LayoutParams lp2 = lp;
                    View child2 = child;
                    paddingLeft2 = paddingLeft;
                    paddingLeft = i2;
                    setChildFrame(child, gravity, gravity2 + getLocationOffset(child), childWidth, childHeight);
                    i2 = paddingLeft + getChildrenSkipCount(child2, paddingLeft);
                    childTop = gravity2 + ((childHeight + lp2.bottomMargin) + getNextLocationOffset(child2));
                } else {
                    majorGravity2 = majorGravity;
                    paddingLeft2 = paddingLeft;
                    paddingLeft = i2;
                }
                i = i2 + 1;
                majorGravity = majorGravity2;
                paddingLeft = paddingLeft2;
            } else {
                paddingLeft2 = paddingLeft;
                return;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x00c3  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00fb  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00cf  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x010e  */
    void layoutHorizontal(int r32, int r33, int r34, int r35) {
        /*
        r31 = this;
        r6 = r31;
        r9 = android.support.v7.widget.ViewUtils.isLayoutRtl(r31);
        r10 = r31.getPaddingTop();
        r13 = r35 - r33;
        r0 = r31.getPaddingBottom();
        r14 = r13 - r0;
        r0 = r13 - r10;
        r1 = r31.getPaddingBottom();
        r15 = r0 - r1;
        r5 = r31.getVirtualChildCount();
        r0 = r6.mGravity;
        r1 = 8388615; // 0x800007 float:1.1754953E-38 double:4.1445265E-317;
        r4 = r0 & r1;
        r0 = r6.mGravity;
        r16 = r0 & 112;
        r2 = r6.mBaselineAligned;
        r1 = r6.mMaxAscent;
        r0 = r6.mMaxDescent;
        r3 = android.support.v4.view.ViewCompat.getLayoutDirection(r31);
        r11 = android.support.v4.view.GravityCompat.getAbsoluteGravity(r4, r3);
        r17 = 2;
        r12 = 1;
        if (r11 == r12) goto L_0x0052;
    L_0x003c:
        r12 = 5;
        if (r11 == r12) goto L_0x0046;
    L_0x003f:
        r11 = r31.getPaddingLeft();
    L_0x0043:
        r18 = r3;
        goto L_0x0061;
    L_0x0046:
        r11 = r31.getPaddingLeft();
        r11 = r11 + r34;
        r11 = r11 - r32;
        r12 = r6.mTotalLength;
        r11 = r11 - r12;
        goto L_0x0043;
    L_0x0052:
        r11 = r31.getPaddingLeft();
        r12 = r34 - r32;
        r18 = r3;
        r3 = r6.mTotalLength;
        r12 = r12 - r3;
        r12 = r12 / 2;
        r11 = r11 + r12;
    L_0x0061:
        r3 = r11;
        r11 = 0;
        r12 = 1;
        if (r9 == 0) goto L_0x0069;
    L_0x0066:
        r11 = r5 + -1;
        r12 = -1;
    L_0x0069:
        r19 = 0;
        r20 = r3;
    L_0x006d:
        r3 = r19;
        if (r3 >= r5) goto L_0x0160;
    L_0x0071:
        r19 = r12 * r3;
        r7 = r11 + r19;
        r8 = r6.getVirtualChildAt(r7);
        if (r8 != 0) goto L_0x008f;
    L_0x007b:
        r19 = r6.measureNullChild(r7);
        r20 = r20 + r19;
        r26 = r0;
        r28 = r1;
        r25 = r2;
        r22 = r4;
        r27 = r5;
        r30 = r9;
        goto L_0x014f;
    L_0x008f:
        r21 = r3;
        r3 = r8.getVisibility();
        r22 = r4;
        r4 = 8;
        if (r3 == r4) goto L_0x0143;
    L_0x009b:
        r19 = r8.getMeasuredWidth();
        r23 = r8.getMeasuredHeight();
        r3 = -1;
        r4 = r8.getLayoutParams();
        r4 = (android.support.v7.widget.LinearLayoutCompat.LayoutParams) r4;
        r24 = r3;
        r3 = -1;
        if (r2 == 0) goto L_0x00bb;
    L_0x00b0:
        r25 = r2;
        r2 = r4.height;
        if (r2 == r3) goto L_0x00bd;
    L_0x00b6:
        r2 = r8.getBaseline();
        goto L_0x00bf;
    L_0x00bb:
        r25 = r2;
    L_0x00bd:
        r2 = r24;
    L_0x00bf:
        r3 = r4.gravity;
        if (r3 >= 0) goto L_0x00c5;
    L_0x00c3:
        r3 = r16;
    L_0x00c5:
        r24 = r3;
        r3 = r24 & 112;
        r27 = r5;
        r5 = 16;
        if (r3 == r5) goto L_0x00fb;
    L_0x00cf:
        r5 = 48;
        if (r3 == r5) goto L_0x00ed;
    L_0x00d3:
        r5 = 80;
        if (r3 == r5) goto L_0x00d9;
    L_0x00d7:
        r3 = r10;
        goto L_0x0107;
    L_0x00d9:
        r3 = r14 - r23;
        r5 = r4.bottomMargin;
        r3 = r3 - r5;
        r5 = -1;
        if (r2 == r5) goto L_0x0107;
    L_0x00e1:
        r5 = r8.getMeasuredHeight();
        r5 = r5 - r2;
        r26 = r0[r17];
        r26 = r26 - r5;
        r3 = r3 - r26;
        goto L_0x0107;
    L_0x00ed:
        r3 = r4.topMargin;
        r3 = r3 + r10;
        r5 = -1;
        if (r2 == r5) goto L_0x0107;
    L_0x00f3:
        r5 = 1;
        r26 = r1[r5];
        r26 = r26 - r2;
        r3 = r3 + r26;
        goto L_0x0107;
    L_0x00fb:
        r3 = r15 - r23;
        r3 = r3 / 2;
        r3 = r3 + r10;
        r5 = r4.topMargin;
        r3 = r3 + r5;
        r5 = r4.bottomMargin;
        r3 = r3 - r5;
        r5 = r6.hasDividerBeforeChildAt(r7);
        if (r5 == 0) goto L_0x0112;
    L_0x010e:
        r5 = r6.mDividerWidth;
        r20 = r20 + r5;
    L_0x0112:
        r5 = r4.leftMargin;
        r20 = r20 + r5;
        r5 = r6.getLocationOffset(r8);
        r5 = r20 + r5;
        r26 = r0;
        r0 = r31;
        r28 = r1;
        r1 = r8;
        r29 = r2;
        r2 = r5;
        r5 = r4;
        r4 = r19;
        r30 = r9;
        r9 = r5;
        r5 = r23;
        r0.setChildFrame(r1, r2, r3, r4, r5);
        r0 = r9.rightMargin;
        r0 = r19 + r0;
        r1 = r6.getNextLocationOffset(r8);
        r0 = r0 + r1;
        r20 = r20 + r0;
        r0 = r6.getChildrenSkipCount(r8, r7);
        r3 = r21 + r0;
        goto L_0x014f;
    L_0x0143:
        r26 = r0;
        r28 = r1;
        r25 = r2;
        r27 = r5;
        r30 = r9;
        r3 = r21;
    L_0x014f:
        r0 = 1;
        r19 = r3 + 1;
        r4 = r22;
        r2 = r25;
        r0 = r26;
        r5 = r27;
        r1 = r28;
        r9 = r30;
        goto L_0x006d;
    L_0x0160:
        r26 = r0;
        r28 = r1;
        r25 = r2;
        r22 = r4;
        r27 = r5;
        r30 = r9;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.LinearLayoutCompat.layoutHorizontal(int, int, int, int):void");
    }

    private void setChildFrame(View child, int left, int top, int width, int height) {
        child.layout(left, top, left + width, top + height);
    }

    public void setOrientation(int orientation) {
        if (this.mOrientation != orientation) {
            this.mOrientation = orientation;
            requestLayout();
        }
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public void setGravity(int gravity) {
        if (this.mGravity != gravity) {
            if ((GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK & gravity) == 0) {
                gravity |= GravityCompat.START;
            }
            if ((gravity & 112) == 0) {
                gravity |= 48;
            }
            this.mGravity = gravity;
            requestLayout();
        }
    }

    public int getGravity() {
        return this.mGravity;
    }

    public void setHorizontalGravity(int horizontalGravity) {
        int gravity = horizontalGravity & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK;
        if ((GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK & this.mGravity) != gravity) {
            this.mGravity = (this.mGravity & -8388616) | gravity;
            requestLayout();
        }
    }

    public void setVerticalGravity(int verticalGravity) {
        int gravity = verticalGravity & 112;
        if ((this.mGravity & 112) != gravity) {
            this.mGravity = (this.mGravity & -113) | gravity;
            requestLayout();
        }
    }

    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    protected LayoutParams generateDefaultLayoutParams() {
        if (this.mOrientation == 0) {
            return new LayoutParams(-2, -2);
        }
        if (this.mOrientation == 1) {
            return new LayoutParams(-1, -2);
        }
        return null;
    }

    protected LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams p) {
        return new LayoutParams(p);
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent event) {
        if (VERSION.SDK_INT >= 14) {
            super.onInitializeAccessibilityEvent(event);
            event.setClassName(LinearLayoutCompat.class.getName());
        }
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) {
        if (VERSION.SDK_INT >= 14) {
            super.onInitializeAccessibilityNodeInfo(info);
            info.setClassName(LinearLayoutCompat.class.getName());
        }
    }
}
