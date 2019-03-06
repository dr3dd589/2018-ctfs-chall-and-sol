package android.support.constraint;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build.VERSION;
import android.support.constraint.solver.Metrics;
import android.support.constraint.solver.widgets.Analyzer;
import android.support.constraint.solver.widgets.ConstraintAnchor;
import android.support.constraint.solver.widgets.ConstraintAnchor.Strength;
import android.support.constraint.solver.widgets.ConstraintAnchor.Type;
import android.support.constraint.solver.widgets.ConstraintWidget;
import android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour;
import android.support.constraint.solver.widgets.ConstraintWidgetContainer;
import android.support.constraint.solver.widgets.Guideline;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import java.util.ArrayList;
import java.util.HashMap;

public class ConstraintLayout extends ViewGroup {
    static final boolean ALLOWS_EMBEDDED = false;
    private static final boolean CACHE_MEASURED_DIMENSION = false;
    private static final boolean DEBUG = false;
    public static final int DESIGN_INFO_ID = 0;
    private static final String TAG = "ConstraintLayout";
    private static final boolean USE_CONSTRAINTS_HELPER = true;
    public static final String VERSION = "ConstraintLayout-1.1.3";
    SparseArray<View> mChildrenByIds = new SparseArray();
    private ArrayList<ConstraintHelper> mConstraintHelpers = new ArrayList(4);
    private ConstraintSet mConstraintSet = null;
    private int mConstraintSetId = -1;
    private HashMap<String, Integer> mDesignIds = new HashMap();
    private boolean mDirtyHierarchy = USE_CONSTRAINTS_HELPER;
    private int mLastMeasureHeight = -1;
    int mLastMeasureHeightMode = 0;
    int mLastMeasureHeightSize = -1;
    private int mLastMeasureWidth = -1;
    int mLastMeasureWidthMode = 0;
    int mLastMeasureWidthSize = -1;
    ConstraintWidgetContainer mLayoutWidget = new ConstraintWidgetContainer();
    private int mMaxHeight = ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
    private int mMaxWidth = ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
    private Metrics mMetrics;
    private int mMinHeight = 0;
    private int mMinWidth = 0;
    private int mOptimizationLevel = 7;
    private final ArrayList<ConstraintWidget> mVariableDimensionsWidgets = new ArrayList(100);

    public static class LayoutParams extends MarginLayoutParams {
        public static final int BASELINE = 5;
        public static final int BOTTOM = 4;
        public static final int CHAIN_PACKED = 2;
        public static final int CHAIN_SPREAD = 0;
        public static final int CHAIN_SPREAD_INSIDE = 1;
        public static final int END = 7;
        public static final int HORIZONTAL = 0;
        public static final int LEFT = 1;
        public static final int MATCH_CONSTRAINT = 0;
        public static final int MATCH_CONSTRAINT_PERCENT = 2;
        public static final int MATCH_CONSTRAINT_SPREAD = 0;
        public static final int MATCH_CONSTRAINT_WRAP = 1;
        public static final int PARENT_ID = 0;
        public static final int RIGHT = 2;
        public static final int START = 6;
        public static final int TOP = 3;
        public static final int UNSET = -1;
        public static final int VERTICAL = 1;
        public int baselineToBaseline;
        public int bottomToBottom;
        public int bottomToTop;
        public float circleAngle;
        public int circleConstraint;
        public int circleRadius;
        public boolean constrainedHeight;
        public boolean constrainedWidth;
        public String dimensionRatio;
        int dimensionRatioSide;
        float dimensionRatioValue;
        public int editorAbsoluteX;
        public int editorAbsoluteY;
        public int endToEnd;
        public int endToStart;
        public int goneBottomMargin;
        public int goneEndMargin;
        public int goneLeftMargin;
        public int goneRightMargin;
        public int goneStartMargin;
        public int goneTopMargin;
        public int guideBegin;
        public int guideEnd;
        public float guidePercent;
        public boolean helped;
        public float horizontalBias;
        public int horizontalChainStyle;
        boolean horizontalDimensionFixed;
        public float horizontalWeight;
        boolean isGuideline;
        boolean isHelper;
        boolean isInPlaceholder;
        public int leftToLeft;
        public int leftToRight;
        public int matchConstraintDefaultHeight;
        public int matchConstraintDefaultWidth;
        public int matchConstraintMaxHeight;
        public int matchConstraintMaxWidth;
        public int matchConstraintMinHeight;
        public int matchConstraintMinWidth;
        public float matchConstraintPercentHeight;
        public float matchConstraintPercentWidth;
        boolean needsBaseline;
        public int orientation;
        int resolveGoneLeftMargin;
        int resolveGoneRightMargin;
        int resolvedGuideBegin;
        int resolvedGuideEnd;
        float resolvedGuidePercent;
        float resolvedHorizontalBias;
        int resolvedLeftToLeft;
        int resolvedLeftToRight;
        int resolvedRightToLeft;
        int resolvedRightToRight;
        public int rightToLeft;
        public int rightToRight;
        public int startToEnd;
        public int startToStart;
        public int topToBottom;
        public int topToTop;
        public float verticalBias;
        public int verticalChainStyle;
        boolean verticalDimensionFixed;
        public float verticalWeight;
        ConstraintWidget widget;

        private static class Table {
            public static final int ANDROID_ORIENTATION = 1;
            public static final int LAYOUT_CONSTRAINED_HEIGHT = 28;
            public static final int LAYOUT_CONSTRAINED_WIDTH = 27;
            public static final int LAYOUT_CONSTRAINT_BASELINE_CREATOR = 43;
            public static final int LAYOUT_CONSTRAINT_BASELINE_TO_BASELINE_OF = 16;
            public static final int LAYOUT_CONSTRAINT_BOTTOM_CREATOR = 42;
            public static final int LAYOUT_CONSTRAINT_BOTTOM_TO_BOTTOM_OF = 15;
            public static final int LAYOUT_CONSTRAINT_BOTTOM_TO_TOP_OF = 14;
            public static final int LAYOUT_CONSTRAINT_CIRCLE = 2;
            public static final int LAYOUT_CONSTRAINT_CIRCLE_ANGLE = 4;
            public static final int LAYOUT_CONSTRAINT_CIRCLE_RADIUS = 3;
            public static final int LAYOUT_CONSTRAINT_DIMENSION_RATIO = 44;
            public static final int LAYOUT_CONSTRAINT_END_TO_END_OF = 20;
            public static final int LAYOUT_CONSTRAINT_END_TO_START_OF = 19;
            public static final int LAYOUT_CONSTRAINT_GUIDE_BEGIN = 5;
            public static final int LAYOUT_CONSTRAINT_GUIDE_END = 6;
            public static final int LAYOUT_CONSTRAINT_GUIDE_PERCENT = 7;
            public static final int LAYOUT_CONSTRAINT_HEIGHT_DEFAULT = 32;
            public static final int LAYOUT_CONSTRAINT_HEIGHT_MAX = 37;
            public static final int LAYOUT_CONSTRAINT_HEIGHT_MIN = 36;
            public static final int LAYOUT_CONSTRAINT_HEIGHT_PERCENT = 38;
            public static final int LAYOUT_CONSTRAINT_HORIZONTAL_BIAS = 29;
            public static final int LAYOUT_CONSTRAINT_HORIZONTAL_CHAINSTYLE = 47;
            public static final int LAYOUT_CONSTRAINT_HORIZONTAL_WEIGHT = 45;
            public static final int LAYOUT_CONSTRAINT_LEFT_CREATOR = 39;
            public static final int LAYOUT_CONSTRAINT_LEFT_TO_LEFT_OF = 8;
            public static final int LAYOUT_CONSTRAINT_LEFT_TO_RIGHT_OF = 9;
            public static final int LAYOUT_CONSTRAINT_RIGHT_CREATOR = 41;
            public static final int LAYOUT_CONSTRAINT_RIGHT_TO_LEFT_OF = 10;
            public static final int LAYOUT_CONSTRAINT_RIGHT_TO_RIGHT_OF = 11;
            public static final int LAYOUT_CONSTRAINT_START_TO_END_OF = 17;
            public static final int LAYOUT_CONSTRAINT_START_TO_START_OF = 18;
            public static final int LAYOUT_CONSTRAINT_TOP_CREATOR = 40;
            public static final int LAYOUT_CONSTRAINT_TOP_TO_BOTTOM_OF = 13;
            public static final int LAYOUT_CONSTRAINT_TOP_TO_TOP_OF = 12;
            public static final int LAYOUT_CONSTRAINT_VERTICAL_BIAS = 30;
            public static final int LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE = 48;
            public static final int LAYOUT_CONSTRAINT_VERTICAL_WEIGHT = 46;
            public static final int LAYOUT_CONSTRAINT_WIDTH_DEFAULT = 31;
            public static final int LAYOUT_CONSTRAINT_WIDTH_MAX = 34;
            public static final int LAYOUT_CONSTRAINT_WIDTH_MIN = 33;
            public static final int LAYOUT_CONSTRAINT_WIDTH_PERCENT = 35;
            public static final int LAYOUT_EDITOR_ABSOLUTEX = 49;
            public static final int LAYOUT_EDITOR_ABSOLUTEY = 50;
            public static final int LAYOUT_GONE_MARGIN_BOTTOM = 24;
            public static final int LAYOUT_GONE_MARGIN_END = 26;
            public static final int LAYOUT_GONE_MARGIN_LEFT = 21;
            public static final int LAYOUT_GONE_MARGIN_RIGHT = 23;
            public static final int LAYOUT_GONE_MARGIN_START = 25;
            public static final int LAYOUT_GONE_MARGIN_TOP = 22;
            public static final int UNUSED = 0;
            public static final SparseIntArray map = new SparseIntArray();

            private Table() {
            }

            static {
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintLeft_toLeftOf, 8);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintLeft_toRightOf, 9);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintRight_toLeftOf, 10);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintRight_toRightOf, 11);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintTop_toTopOf, 12);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintTop_toBottomOf, 13);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintBottom_toTopOf, 14);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintBottom_toBottomOf, 15);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintBaseline_toBaselineOf, 16);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintCircle, 2);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintCircleRadius, 3);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintCircleAngle, 4);
                map.append(R.styleable.ConstraintLayout_Layout_layout_editor_absoluteX, 49);
                map.append(R.styleable.ConstraintLayout_Layout_layout_editor_absoluteY, 50);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintGuide_begin, 5);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintGuide_end, 6);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintGuide_percent, 7);
                map.append(R.styleable.ConstraintLayout_Layout_android_orientation, 1);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintStart_toEndOf, 17);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintStart_toStartOf, 18);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintEnd_toStartOf, 19);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintEnd_toEndOf, 20);
                map.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginLeft, 21);
                map.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginTop, 22);
                map.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginRight, 23);
                map.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginBottom, 24);
                map.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginStart, 25);
                map.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginEnd, 26);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHorizontal_bias, 29);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintVertical_bias, 30);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintDimensionRatio, 44);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHorizontal_weight, 45);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintVertical_weight, 46);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHorizontal_chainStyle, 47);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintVertical_chainStyle, 48);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constrainedWidth, 27);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constrainedHeight, 28);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintWidth_default, 31);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHeight_default, 32);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintWidth_min, 33);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintWidth_max, 34);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintWidth_percent, 35);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHeight_min, 36);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHeight_max, 37);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHeight_percent, 38);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintLeft_creator, 39);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintTop_creator, 40);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintRight_creator, 41);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintBottom_creator, 42);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintBaseline_creator, 43);
            }
        }

        public void reset() {
            if (this.widget != null) {
                this.widget.reset();
            }
        }

        public LayoutParams(LayoutParams source) {
            super(source);
            this.guideBegin = -1;
            this.guideEnd = -1;
            this.guidePercent = -1.0f;
            this.leftToLeft = -1;
            this.leftToRight = -1;
            this.rightToLeft = -1;
            this.rightToRight = -1;
            this.topToTop = -1;
            this.topToBottom = -1;
            this.bottomToTop = -1;
            this.bottomToBottom = -1;
            this.baselineToBaseline = -1;
            this.circleConstraint = -1;
            this.circleRadius = 0;
            this.circleAngle = 0.0f;
            this.startToEnd = -1;
            this.startToStart = -1;
            this.endToStart = -1;
            this.endToEnd = -1;
            this.goneLeftMargin = -1;
            this.goneTopMargin = -1;
            this.goneRightMargin = -1;
            this.goneBottomMargin = -1;
            this.goneStartMargin = -1;
            this.goneEndMargin = -1;
            this.horizontalBias = 0.5f;
            this.verticalBias = 0.5f;
            this.dimensionRatio = null;
            this.dimensionRatioValue = 0.0f;
            this.dimensionRatioSide = 1;
            this.horizontalWeight = -1.0f;
            this.verticalWeight = -1.0f;
            this.horizontalChainStyle = 0;
            this.verticalChainStyle = 0;
            this.matchConstraintDefaultWidth = 0;
            this.matchConstraintDefaultHeight = 0;
            this.matchConstraintMinWidth = 0;
            this.matchConstraintMinHeight = 0;
            this.matchConstraintMaxWidth = 0;
            this.matchConstraintMaxHeight = 0;
            this.matchConstraintPercentWidth = 1.0f;
            this.matchConstraintPercentHeight = 1.0f;
            this.editorAbsoluteX = -1;
            this.editorAbsoluteY = -1;
            this.orientation = -1;
            this.constrainedWidth = false;
            this.constrainedHeight = false;
            this.horizontalDimensionFixed = ConstraintLayout.USE_CONSTRAINTS_HELPER;
            this.verticalDimensionFixed = ConstraintLayout.USE_CONSTRAINTS_HELPER;
            this.needsBaseline = false;
            this.isGuideline = false;
            this.isHelper = false;
            this.isInPlaceholder = false;
            this.resolvedLeftToLeft = -1;
            this.resolvedLeftToRight = -1;
            this.resolvedRightToLeft = -1;
            this.resolvedRightToRight = -1;
            this.resolveGoneLeftMargin = -1;
            this.resolveGoneRightMargin = -1;
            this.resolvedHorizontalBias = 0.5f;
            this.widget = new ConstraintWidget();
            this.helped = false;
            this.guideBegin = source.guideBegin;
            this.guideEnd = source.guideEnd;
            this.guidePercent = source.guidePercent;
            this.leftToLeft = source.leftToLeft;
            this.leftToRight = source.leftToRight;
            this.rightToLeft = source.rightToLeft;
            this.rightToRight = source.rightToRight;
            this.topToTop = source.topToTop;
            this.topToBottom = source.topToBottom;
            this.bottomToTop = source.bottomToTop;
            this.bottomToBottom = source.bottomToBottom;
            this.baselineToBaseline = source.baselineToBaseline;
            this.circleConstraint = source.circleConstraint;
            this.circleRadius = source.circleRadius;
            this.circleAngle = source.circleAngle;
            this.startToEnd = source.startToEnd;
            this.startToStart = source.startToStart;
            this.endToStart = source.endToStart;
            this.endToEnd = source.endToEnd;
            this.goneLeftMargin = source.goneLeftMargin;
            this.goneTopMargin = source.goneTopMargin;
            this.goneRightMargin = source.goneRightMargin;
            this.goneBottomMargin = source.goneBottomMargin;
            this.goneStartMargin = source.goneStartMargin;
            this.goneEndMargin = source.goneEndMargin;
            this.horizontalBias = source.horizontalBias;
            this.verticalBias = source.verticalBias;
            this.dimensionRatio = source.dimensionRatio;
            this.dimensionRatioValue = source.dimensionRatioValue;
            this.dimensionRatioSide = source.dimensionRatioSide;
            this.horizontalWeight = source.horizontalWeight;
            this.verticalWeight = source.verticalWeight;
            this.horizontalChainStyle = source.horizontalChainStyle;
            this.verticalChainStyle = source.verticalChainStyle;
            this.constrainedWidth = source.constrainedWidth;
            this.constrainedHeight = source.constrainedHeight;
            this.matchConstraintDefaultWidth = source.matchConstraintDefaultWidth;
            this.matchConstraintDefaultHeight = source.matchConstraintDefaultHeight;
            this.matchConstraintMinWidth = source.matchConstraintMinWidth;
            this.matchConstraintMaxWidth = source.matchConstraintMaxWidth;
            this.matchConstraintMinHeight = source.matchConstraintMinHeight;
            this.matchConstraintMaxHeight = source.matchConstraintMaxHeight;
            this.matchConstraintPercentWidth = source.matchConstraintPercentWidth;
            this.matchConstraintPercentHeight = source.matchConstraintPercentHeight;
            this.editorAbsoluteX = source.editorAbsoluteX;
            this.editorAbsoluteY = source.editorAbsoluteY;
            this.orientation = source.orientation;
            this.horizontalDimensionFixed = source.horizontalDimensionFixed;
            this.verticalDimensionFixed = source.verticalDimensionFixed;
            this.needsBaseline = source.needsBaseline;
            this.isGuideline = source.isGuideline;
            this.resolvedLeftToLeft = source.resolvedLeftToLeft;
            this.resolvedLeftToRight = source.resolvedLeftToRight;
            this.resolvedRightToLeft = source.resolvedRightToLeft;
            this.resolvedRightToRight = source.resolvedRightToRight;
            this.resolveGoneLeftMargin = source.resolveGoneLeftMargin;
            this.resolveGoneRightMargin = source.resolveGoneRightMargin;
            this.resolvedHorizontalBias = source.resolvedHorizontalBias;
            this.widget = source.widget;
        }

        public LayoutParams(android.content.Context r20, android.util.AttributeSet r21) {
            /*
            r19 = this;
            r1 = r19;
            r19.<init>(r20, r21);
            r2 = -1;
            r1.guideBegin = r2;
            r1.guideEnd = r2;
            r0 = -1082130432; // 0xffffffffbf800000 float:-1.0 double:NaN;
            r1.guidePercent = r0;
            r1.leftToLeft = r2;
            r1.leftToRight = r2;
            r1.rightToLeft = r2;
            r1.rightToRight = r2;
            r1.topToTop = r2;
            r1.topToBottom = r2;
            r1.bottomToTop = r2;
            r1.bottomToBottom = r2;
            r1.baselineToBaseline = r2;
            r1.circleConstraint = r2;
            r3 = 0;
            r1.circleRadius = r3;
            r4 = 0;
            r1.circleAngle = r4;
            r1.startToEnd = r2;
            r1.startToStart = r2;
            r1.endToStart = r2;
            r1.endToEnd = r2;
            r1.goneLeftMargin = r2;
            r1.goneTopMargin = r2;
            r1.goneRightMargin = r2;
            r1.goneBottomMargin = r2;
            r1.goneStartMargin = r2;
            r1.goneEndMargin = r2;
            r5 = 1056964608; // 0x3f000000 float:0.5 double:5.222099017E-315;
            r1.horizontalBias = r5;
            r1.verticalBias = r5;
            r6 = 0;
            r1.dimensionRatio = r6;
            r1.dimensionRatioValue = r4;
            r6 = 1;
            r1.dimensionRatioSide = r6;
            r1.horizontalWeight = r0;
            r1.verticalWeight = r0;
            r1.horizontalChainStyle = r3;
            r1.verticalChainStyle = r3;
            r1.matchConstraintDefaultWidth = r3;
            r1.matchConstraintDefaultHeight = r3;
            r1.matchConstraintMinWidth = r3;
            r1.matchConstraintMinHeight = r3;
            r1.matchConstraintMaxWidth = r3;
            r1.matchConstraintMaxHeight = r3;
            r0 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
            r1.matchConstraintPercentWidth = r0;
            r1.matchConstraintPercentHeight = r0;
            r1.editorAbsoluteX = r2;
            r1.editorAbsoluteY = r2;
            r1.orientation = r2;
            r1.constrainedWidth = r3;
            r1.constrainedHeight = r3;
            r1.horizontalDimensionFixed = r6;
            r1.verticalDimensionFixed = r6;
            r1.needsBaseline = r3;
            r1.isGuideline = r3;
            r1.isHelper = r3;
            r1.isInPlaceholder = r3;
            r1.resolvedLeftToLeft = r2;
            r1.resolvedLeftToRight = r2;
            r1.resolvedRightToLeft = r2;
            r1.resolvedRightToRight = r2;
            r1.resolveGoneLeftMargin = r2;
            r1.resolveGoneRightMargin = r2;
            r1.resolvedHorizontalBias = r5;
            r0 = new android.support.constraint.solver.widgets.ConstraintWidget;
            r0.<init>();
            r1.widget = r0;
            r1.helped = r3;
            r0 = android.support.constraint.R.styleable.ConstraintLayout_Layout;
            r5 = r20;
            r7 = r21;
            r8 = r5.obtainStyledAttributes(r7, r0);
            r9 = r8.getIndexCount();
            r0 = 0;
        L_0x00a0:
            r10 = r0;
            if (r10 >= r9) goto L_0x0434;
        L_0x00a3:
            r11 = r8.getIndex(r10);
            r0 = android.support.constraint.ConstraintLayout.LayoutParams.Table.map;
            r12 = r0.get(r11);
            r13 = -2;
            switch(r12) {
                case 0: goto L_0x042c;
                case 1: goto L_0x0421;
                case 2: goto L_0x040c;
                case 3: goto L_0x0401;
                case 4: goto L_0x03e6;
                case 5: goto L_0x03db;
                case 6: goto L_0x03d0;
                case 7: goto L_0x03c5;
                case 8: goto L_0x03af;
                case 9: goto L_0x0399;
                case 10: goto L_0x0383;
                case 11: goto L_0x036d;
                case 12: goto L_0x0357;
                case 13: goto L_0x0341;
                case 14: goto L_0x032b;
                case 15: goto L_0x0315;
                case 16: goto L_0x02ff;
                case 17: goto L_0x02e9;
                case 18: goto L_0x02d3;
                case 19: goto L_0x02bd;
                case 20: goto L_0x02a7;
                case 21: goto L_0x029c;
                case 22: goto L_0x0291;
                case 23: goto L_0x0286;
                case 24: goto L_0x027b;
                case 25: goto L_0x0270;
                case 26: goto L_0x0265;
                case 27: goto L_0x025a;
                case 28: goto L_0x024f;
                case 29: goto L_0x0244;
                case 30: goto L_0x0239;
                case 31: goto L_0x0225;
                case 32: goto L_0x0211;
                case 33: goto L_0x01fa;
                case 34: goto L_0x01e3;
                case 35: goto L_0x01d5;
                case 36: goto L_0x01be;
                case 37: goto L_0x01a7;
                case 38: goto L_0x0199;
                case 39: goto L_0x0197;
                case 40: goto L_0x0195;
                case 41: goto L_0x0193;
                case 42: goto L_0x0191;
                case 43: goto L_0x00b1;
                case 44: goto L_0x00e7;
                case 45: goto L_0x00de;
                case 46: goto L_0x00d5;
                case 47: goto L_0x00ce;
                case 48: goto L_0x00c7;
                case 49: goto L_0x00be;
                case 50: goto L_0x00b5;
                default: goto L_0x00b1;
            };
        L_0x00b1:
            r2 = 0;
        L_0x00b2:
            r3 = -1;
            goto L_0x042e;
        L_0x00b5:
            r0 = r1.editorAbsoluteY;
            r0 = r8.getDimensionPixelOffset(r11, r0);
            r1.editorAbsoluteY = r0;
            goto L_0x00b1;
        L_0x00be:
            r0 = r1.editorAbsoluteX;
            r0 = r8.getDimensionPixelOffset(r11, r0);
            r1.editorAbsoluteX = r0;
            goto L_0x00b1;
        L_0x00c7:
            r0 = r8.getInt(r11, r3);
            r1.verticalChainStyle = r0;
            goto L_0x00b1;
        L_0x00ce:
            r0 = r8.getInt(r11, r3);
            r1.horizontalChainStyle = r0;
            goto L_0x00b1;
        L_0x00d5:
            r0 = r1.verticalWeight;
            r0 = r8.getFloat(r11, r0);
            r1.verticalWeight = r0;
            goto L_0x00b1;
        L_0x00de:
            r0 = r1.horizontalWeight;
            r0 = r8.getFloat(r11, r0);
            r1.horizontalWeight = r0;
            goto L_0x00b1;
        L_0x00e7:
            r0 = r8.getString(r11);
            r1.dimensionRatio = r0;
            r0 = 2143289344; // 0x7fc00000 float:NaN double:1.058925634E-314;
            r1.dimensionRatioValue = r0;
            r1.dimensionRatioSide = r2;
            r0 = r1.dimensionRatio;
            if (r0 == 0) goto L_0x00b1;
        L_0x00f7:
            r0 = r1.dimensionRatio;
            r13 = r0.length();
            r0 = r1.dimensionRatio;
            r14 = 44;
            r0 = r0.indexOf(r14);
            if (r0 <= 0) goto L_0x0129;
        L_0x0107:
            r14 = r13 + -1;
            if (r0 >= r14) goto L_0x0129;
        L_0x010b:
            r14 = r1.dimensionRatio;
            r14 = r14.substring(r3, r0);
            r15 = "W";
            r15 = r14.equalsIgnoreCase(r15);
            if (r15 == 0) goto L_0x011c;
        L_0x0119:
            r1.dimensionRatioSide = r3;
            goto L_0x0126;
        L_0x011c:
            r15 = "H";
            r15 = r14.equalsIgnoreCase(r15);
            if (r15 == 0) goto L_0x0126;
        L_0x0124:
            r1.dimensionRatioSide = r6;
        L_0x0126:
            r0 = r0 + 1;
            goto L_0x012a;
        L_0x0129:
            r0 = 0;
        L_0x012a:
            r14 = r0;
            r0 = r1.dimensionRatio;
            r15 = 58;
            r15 = r0.indexOf(r15);
            if (r15 < 0) goto L_0x017b;
        L_0x0135:
            r0 = r13 + -1;
            if (r15 >= r0) goto L_0x017b;
        L_0x0139:
            r0 = r1.dimensionRatio;
            r16 = r0.substring(r14, r15);
            r0 = r1.dimensionRatio;
            r2 = r15 + 1;
            r2 = r0.substring(r2);
            r0 = r16.length();
            if (r0 <= 0) goto L_0x017a;
        L_0x014d:
            r0 = r2.length();
            if (r0 <= 0) goto L_0x017a;
        L_0x0153:
            r0 = java.lang.Float.parseFloat(r16);	 Catch:{ NumberFormatException -> 0x0179 }
            r17 = java.lang.Float.parseFloat(r2);	 Catch:{ NumberFormatException -> 0x0179 }
            r18 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
            if (r18 <= 0) goto L_0x0178;
        L_0x015f:
            r18 = (r17 > r4 ? 1 : (r17 == r4 ? 0 : -1));
            if (r18 <= 0) goto L_0x0178;
        L_0x0163:
            r3 = r1.dimensionRatioSide;	 Catch:{ NumberFormatException -> 0x0179 }
            if (r3 != r6) goto L_0x0170;
        L_0x0167:
            r3 = r17 / r0;
            r3 = java.lang.Math.abs(r3);	 Catch:{ NumberFormatException -> 0x0179 }
            r1.dimensionRatioValue = r3;	 Catch:{ NumberFormatException -> 0x0179 }
            goto L_0x0178;
        L_0x0170:
            r3 = r0 / r17;
            r3 = java.lang.Math.abs(r3);	 Catch:{ NumberFormatException -> 0x0179 }
            r1.dimensionRatioValue = r3;	 Catch:{ NumberFormatException -> 0x0179 }
        L_0x0178:
            goto L_0x017a;
        L_0x0179:
            r0 = move-exception;
        L_0x017a:
            goto L_0x018f;
        L_0x017b:
            r0 = r1.dimensionRatio;
            r2 = r0.substring(r14);
            r0 = r2.length();
            if (r0 <= 0) goto L_0x018f;
        L_0x0187:
            r0 = java.lang.Float.parseFloat(r2);	 Catch:{ NumberFormatException -> 0x018e }
            r1.dimensionRatioValue = r0;	 Catch:{ NumberFormatException -> 0x018e }
            goto L_0x018f;
        L_0x018e:
            r0 = move-exception;
        L_0x018f:
            goto L_0x00b1;
        L_0x0191:
            goto L_0x00b1;
        L_0x0193:
            goto L_0x00b1;
        L_0x0195:
            goto L_0x00b1;
        L_0x0197:
            goto L_0x00b1;
        L_0x0199:
            r0 = r1.matchConstraintPercentHeight;
            r0 = r8.getFloat(r11, r0);
            r0 = java.lang.Math.max(r4, r0);
            r1.matchConstraintPercentHeight = r0;
            goto L_0x00b1;
        L_0x01a7:
            r0 = r1.matchConstraintMaxHeight;	 Catch:{ Exception -> 0x01b1 }
            r0 = r8.getDimensionPixelSize(r11, r0);	 Catch:{ Exception -> 0x01b1 }
            r1.matchConstraintMaxHeight = r0;	 Catch:{ Exception -> 0x01b1 }
            goto L_0x00b1;
        L_0x01b1:
            r0 = move-exception;
            r2 = r1.matchConstraintMaxHeight;
            r2 = r8.getInt(r11, r2);
            if (r2 != r13) goto L_0x01bc;
        L_0x01ba:
            r1.matchConstraintMaxHeight = r13;
        L_0x01bc:
            goto L_0x00b1;
        L_0x01be:
            r0 = r1.matchConstraintMinHeight;	 Catch:{ Exception -> 0x01c8 }
            r0 = r8.getDimensionPixelSize(r11, r0);	 Catch:{ Exception -> 0x01c8 }
            r1.matchConstraintMinHeight = r0;	 Catch:{ Exception -> 0x01c8 }
            goto L_0x00b1;
        L_0x01c8:
            r0 = move-exception;
            r2 = r1.matchConstraintMinHeight;
            r2 = r8.getInt(r11, r2);
            if (r2 != r13) goto L_0x01d3;
        L_0x01d1:
            r1.matchConstraintMinHeight = r13;
        L_0x01d3:
            goto L_0x00b1;
        L_0x01d5:
            r0 = r1.matchConstraintPercentWidth;
            r0 = r8.getFloat(r11, r0);
            r0 = java.lang.Math.max(r4, r0);
            r1.matchConstraintPercentWidth = r0;
            goto L_0x00b1;
        L_0x01e3:
            r0 = r1.matchConstraintMaxWidth;	 Catch:{ Exception -> 0x01ed }
            r0 = r8.getDimensionPixelSize(r11, r0);	 Catch:{ Exception -> 0x01ed }
            r1.matchConstraintMaxWidth = r0;	 Catch:{ Exception -> 0x01ed }
            goto L_0x00b1;
        L_0x01ed:
            r0 = move-exception;
            r2 = r1.matchConstraintMaxWidth;
            r2 = r8.getInt(r11, r2);
            if (r2 != r13) goto L_0x01f8;
        L_0x01f6:
            r1.matchConstraintMaxWidth = r13;
        L_0x01f8:
            goto L_0x00b1;
        L_0x01fa:
            r0 = r1.matchConstraintMinWidth;	 Catch:{ Exception -> 0x0204 }
            r0 = r8.getDimensionPixelSize(r11, r0);	 Catch:{ Exception -> 0x0204 }
            r1.matchConstraintMinWidth = r0;	 Catch:{ Exception -> 0x0204 }
            goto L_0x00b1;
        L_0x0204:
            r0 = move-exception;
            r2 = r1.matchConstraintMinWidth;
            r2 = r8.getInt(r11, r2);
            if (r2 != r13) goto L_0x020f;
        L_0x020d:
            r1.matchConstraintMinWidth = r13;
        L_0x020f:
            goto L_0x00b1;
        L_0x0211:
            r2 = 0;
            r0 = r8.getInt(r11, r2);
            r1.matchConstraintDefaultHeight = r0;
            r0 = r1.matchConstraintDefaultHeight;
            if (r0 != r6) goto L_0x00b1;
        L_0x021c:
            r0 = "ConstraintLayout";
            r2 = "layout_constraintHeight_default=\"wrap\" is deprecated.\nUse layout_height=\"WRAP_CONTENT\" and layout_constrainedHeight=\"true\" instead.";
            android.util.Log.e(r0, r2);
            goto L_0x00b1;
        L_0x0225:
            r2 = 0;
            r0 = r8.getInt(r11, r2);
            r1.matchConstraintDefaultWidth = r0;
            r0 = r1.matchConstraintDefaultWidth;
            if (r0 != r6) goto L_0x00b2;
        L_0x0230:
            r0 = "ConstraintLayout";
            r3 = "layout_constraintWidth_default=\"wrap\" is deprecated.\nUse layout_width=\"WRAP_CONTENT\" and layout_constrainedWidth=\"true\" instead.";
            android.util.Log.e(r0, r3);
            goto L_0x00b2;
        L_0x0239:
            r2 = 0;
            r0 = r1.verticalBias;
            r0 = r8.getFloat(r11, r0);
            r1.verticalBias = r0;
            goto L_0x00b2;
        L_0x0244:
            r2 = 0;
            r0 = r1.horizontalBias;
            r0 = r8.getFloat(r11, r0);
            r1.horizontalBias = r0;
            goto L_0x00b2;
        L_0x024f:
            r2 = 0;
            r0 = r1.constrainedHeight;
            r0 = r8.getBoolean(r11, r0);
            r1.constrainedHeight = r0;
            goto L_0x00b2;
        L_0x025a:
            r2 = 0;
            r0 = r1.constrainedWidth;
            r0 = r8.getBoolean(r11, r0);
            r1.constrainedWidth = r0;
            goto L_0x00b2;
        L_0x0265:
            r2 = 0;
            r0 = r1.goneEndMargin;
            r0 = r8.getDimensionPixelSize(r11, r0);
            r1.goneEndMargin = r0;
            goto L_0x00b2;
        L_0x0270:
            r2 = 0;
            r0 = r1.goneStartMargin;
            r0 = r8.getDimensionPixelSize(r11, r0);
            r1.goneStartMargin = r0;
            goto L_0x00b2;
        L_0x027b:
            r2 = 0;
            r0 = r1.goneBottomMargin;
            r0 = r8.getDimensionPixelSize(r11, r0);
            r1.goneBottomMargin = r0;
            goto L_0x00b2;
        L_0x0286:
            r2 = 0;
            r0 = r1.goneRightMargin;
            r0 = r8.getDimensionPixelSize(r11, r0);
            r1.goneRightMargin = r0;
            goto L_0x00b2;
        L_0x0291:
            r2 = 0;
            r0 = r1.goneTopMargin;
            r0 = r8.getDimensionPixelSize(r11, r0);
            r1.goneTopMargin = r0;
            goto L_0x00b2;
        L_0x029c:
            r2 = 0;
            r0 = r1.goneLeftMargin;
            r0 = r8.getDimensionPixelSize(r11, r0);
            r1.goneLeftMargin = r0;
            goto L_0x00b2;
        L_0x02a7:
            r2 = 0;
            r0 = r1.endToEnd;
            r0 = r8.getResourceId(r11, r0);
            r1.endToEnd = r0;
            r0 = r1.endToEnd;
            r3 = -1;
            if (r0 != r3) goto L_0x042e;
        L_0x02b5:
            r0 = r8.getInt(r11, r3);
            r1.endToEnd = r0;
            goto L_0x042e;
        L_0x02bd:
            r2 = 0;
            r3 = -1;
            r0 = r1.endToStart;
            r0 = r8.getResourceId(r11, r0);
            r1.endToStart = r0;
            r0 = r1.endToStart;
            if (r0 != r3) goto L_0x042e;
        L_0x02cb:
            r0 = r8.getInt(r11, r3);
            r1.endToStart = r0;
            goto L_0x042e;
        L_0x02d3:
            r2 = 0;
            r3 = -1;
            r0 = r1.startToStart;
            r0 = r8.getResourceId(r11, r0);
            r1.startToStart = r0;
            r0 = r1.startToStart;
            if (r0 != r3) goto L_0x042e;
        L_0x02e1:
            r0 = r8.getInt(r11, r3);
            r1.startToStart = r0;
            goto L_0x042e;
        L_0x02e9:
            r2 = 0;
            r3 = -1;
            r0 = r1.startToEnd;
            r0 = r8.getResourceId(r11, r0);
            r1.startToEnd = r0;
            r0 = r1.startToEnd;
            if (r0 != r3) goto L_0x042e;
        L_0x02f7:
            r0 = r8.getInt(r11, r3);
            r1.startToEnd = r0;
            goto L_0x042e;
        L_0x02ff:
            r2 = 0;
            r3 = -1;
            r0 = r1.baselineToBaseline;
            r0 = r8.getResourceId(r11, r0);
            r1.baselineToBaseline = r0;
            r0 = r1.baselineToBaseline;
            if (r0 != r3) goto L_0x042e;
        L_0x030d:
            r0 = r8.getInt(r11, r3);
            r1.baselineToBaseline = r0;
            goto L_0x042e;
        L_0x0315:
            r2 = 0;
            r3 = -1;
            r0 = r1.bottomToBottom;
            r0 = r8.getResourceId(r11, r0);
            r1.bottomToBottom = r0;
            r0 = r1.bottomToBottom;
            if (r0 != r3) goto L_0x042e;
        L_0x0323:
            r0 = r8.getInt(r11, r3);
            r1.bottomToBottom = r0;
            goto L_0x042e;
        L_0x032b:
            r2 = 0;
            r3 = -1;
            r0 = r1.bottomToTop;
            r0 = r8.getResourceId(r11, r0);
            r1.bottomToTop = r0;
            r0 = r1.bottomToTop;
            if (r0 != r3) goto L_0x042e;
        L_0x0339:
            r0 = r8.getInt(r11, r3);
            r1.bottomToTop = r0;
            goto L_0x042e;
        L_0x0341:
            r2 = 0;
            r3 = -1;
            r0 = r1.topToBottom;
            r0 = r8.getResourceId(r11, r0);
            r1.topToBottom = r0;
            r0 = r1.topToBottom;
            if (r0 != r3) goto L_0x042e;
        L_0x034f:
            r0 = r8.getInt(r11, r3);
            r1.topToBottom = r0;
            goto L_0x042e;
        L_0x0357:
            r2 = 0;
            r3 = -1;
            r0 = r1.topToTop;
            r0 = r8.getResourceId(r11, r0);
            r1.topToTop = r0;
            r0 = r1.topToTop;
            if (r0 != r3) goto L_0x042e;
        L_0x0365:
            r0 = r8.getInt(r11, r3);
            r1.topToTop = r0;
            goto L_0x042e;
        L_0x036d:
            r2 = 0;
            r3 = -1;
            r0 = r1.rightToRight;
            r0 = r8.getResourceId(r11, r0);
            r1.rightToRight = r0;
            r0 = r1.rightToRight;
            if (r0 != r3) goto L_0x042e;
        L_0x037b:
            r0 = r8.getInt(r11, r3);
            r1.rightToRight = r0;
            goto L_0x042e;
        L_0x0383:
            r2 = 0;
            r3 = -1;
            r0 = r1.rightToLeft;
            r0 = r8.getResourceId(r11, r0);
            r1.rightToLeft = r0;
            r0 = r1.rightToLeft;
            if (r0 != r3) goto L_0x042e;
        L_0x0391:
            r0 = r8.getInt(r11, r3);
            r1.rightToLeft = r0;
            goto L_0x042e;
        L_0x0399:
            r2 = 0;
            r3 = -1;
            r0 = r1.leftToRight;
            r0 = r8.getResourceId(r11, r0);
            r1.leftToRight = r0;
            r0 = r1.leftToRight;
            if (r0 != r3) goto L_0x042e;
        L_0x03a7:
            r0 = r8.getInt(r11, r3);
            r1.leftToRight = r0;
            goto L_0x042e;
        L_0x03af:
            r2 = 0;
            r3 = -1;
            r0 = r1.leftToLeft;
            r0 = r8.getResourceId(r11, r0);
            r1.leftToLeft = r0;
            r0 = r1.leftToLeft;
            if (r0 != r3) goto L_0x042e;
        L_0x03bd:
            r0 = r8.getInt(r11, r3);
            r1.leftToLeft = r0;
            goto L_0x00b2;
        L_0x03c5:
            r2 = 0;
            r0 = r1.guidePercent;
            r0 = r8.getFloat(r11, r0);
            r1.guidePercent = r0;
            goto L_0x00b2;
        L_0x03d0:
            r2 = 0;
            r0 = r1.guideEnd;
            r0 = r8.getDimensionPixelOffset(r11, r0);
            r1.guideEnd = r0;
            goto L_0x00b2;
        L_0x03db:
            r2 = 0;
            r0 = r1.guideBegin;
            r0 = r8.getDimensionPixelOffset(r11, r0);
            r1.guideBegin = r0;
            goto L_0x00b2;
        L_0x03e6:
            r2 = 0;
            r0 = r1.circleAngle;
            r0 = r8.getFloat(r11, r0);
            r3 = 1135869952; // 0x43b40000 float:360.0 double:5.611943214E-315;
            r0 = r0 % r3;
            r1.circleAngle = r0;
            r0 = r1.circleAngle;
            r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
            if (r0 >= 0) goto L_0x00b2;
        L_0x03f8:
            r0 = r1.circleAngle;
            r0 = r3 - r0;
            r0 = r0 % r3;
            r1.circleAngle = r0;
            goto L_0x00b2;
        L_0x0401:
            r2 = 0;
            r0 = r1.circleRadius;
            r0 = r8.getDimensionPixelSize(r11, r0);
            r1.circleRadius = r0;
            goto L_0x00b2;
        L_0x040c:
            r2 = 0;
            r0 = r1.circleConstraint;
            r0 = r8.getResourceId(r11, r0);
            r1.circleConstraint = r0;
            r0 = r1.circleConstraint;
            r3 = -1;
            if (r0 != r3) goto L_0x042e;
        L_0x041a:
            r0 = r8.getInt(r11, r3);
            r1.circleConstraint = r0;
            goto L_0x042e;
        L_0x0421:
            r2 = 0;
            r3 = -1;
            r0 = r1.orientation;
            r0 = r8.getInt(r11, r0);
            r1.orientation = r0;
            goto L_0x042e;
        L_0x042c:
            r2 = 0;
            r3 = -1;
        L_0x042e:
            r0 = r10 + 1;
            r2 = -1;
            r3 = 0;
            goto L_0x00a0;
        L_0x0434:
            r8.recycle();
            r19.validate();
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.ConstraintLayout.LayoutParams.<init>(android.content.Context, android.util.AttributeSet):void");
        }

        public void validate() {
            this.isGuideline = false;
            this.horizontalDimensionFixed = ConstraintLayout.USE_CONSTRAINTS_HELPER;
            this.verticalDimensionFixed = ConstraintLayout.USE_CONSTRAINTS_HELPER;
            if (this.width == -2 && this.constrainedWidth) {
                this.horizontalDimensionFixed = false;
                this.matchConstraintDefaultWidth = 1;
            }
            if (this.height == -2 && this.constrainedHeight) {
                this.verticalDimensionFixed = false;
                this.matchConstraintDefaultHeight = 1;
            }
            if (this.width == 0 || this.width == -1) {
                this.horizontalDimensionFixed = false;
                if (this.width == 0 && this.matchConstraintDefaultWidth == 1) {
                    this.width = -2;
                    this.constrainedWidth = ConstraintLayout.USE_CONSTRAINTS_HELPER;
                }
            }
            if (this.height == 0 || this.height == -1) {
                this.verticalDimensionFixed = false;
                if (this.height == 0 && this.matchConstraintDefaultHeight == 1) {
                    this.height = -2;
                    this.constrainedHeight = ConstraintLayout.USE_CONSTRAINTS_HELPER;
                }
            }
            if (this.guidePercent != -1.0f || this.guideBegin != -1 || this.guideEnd != -1) {
                this.isGuideline = ConstraintLayout.USE_CONSTRAINTS_HELPER;
                this.horizontalDimensionFixed = ConstraintLayout.USE_CONSTRAINTS_HELPER;
                this.verticalDimensionFixed = ConstraintLayout.USE_CONSTRAINTS_HELPER;
                if (!(this.widget instanceof Guideline)) {
                    this.widget = new Guideline();
                }
                ((Guideline) this.widget).setOrientation(this.orientation);
            }
        }

        public LayoutParams(int width, int height) {
            super(width, height);
            this.guideBegin = -1;
            this.guideEnd = -1;
            this.guidePercent = -1.0f;
            this.leftToLeft = -1;
            this.leftToRight = -1;
            this.rightToLeft = -1;
            this.rightToRight = -1;
            this.topToTop = -1;
            this.topToBottom = -1;
            this.bottomToTop = -1;
            this.bottomToBottom = -1;
            this.baselineToBaseline = -1;
            this.circleConstraint = -1;
            this.circleRadius = 0;
            this.circleAngle = 0.0f;
            this.startToEnd = -1;
            this.startToStart = -1;
            this.endToStart = -1;
            this.endToEnd = -1;
            this.goneLeftMargin = -1;
            this.goneTopMargin = -1;
            this.goneRightMargin = -1;
            this.goneBottomMargin = -1;
            this.goneStartMargin = -1;
            this.goneEndMargin = -1;
            this.horizontalBias = 0.5f;
            this.verticalBias = 0.5f;
            this.dimensionRatio = null;
            this.dimensionRatioValue = 0.0f;
            this.dimensionRatioSide = 1;
            this.horizontalWeight = -1.0f;
            this.verticalWeight = -1.0f;
            this.horizontalChainStyle = 0;
            this.verticalChainStyle = 0;
            this.matchConstraintDefaultWidth = 0;
            this.matchConstraintDefaultHeight = 0;
            this.matchConstraintMinWidth = 0;
            this.matchConstraintMinHeight = 0;
            this.matchConstraintMaxWidth = 0;
            this.matchConstraintMaxHeight = 0;
            this.matchConstraintPercentWidth = 1.0f;
            this.matchConstraintPercentHeight = 1.0f;
            this.editorAbsoluteX = -1;
            this.editorAbsoluteY = -1;
            this.orientation = -1;
            this.constrainedWidth = false;
            this.constrainedHeight = false;
            this.horizontalDimensionFixed = ConstraintLayout.USE_CONSTRAINTS_HELPER;
            this.verticalDimensionFixed = ConstraintLayout.USE_CONSTRAINTS_HELPER;
            this.needsBaseline = false;
            this.isGuideline = false;
            this.isHelper = false;
            this.isInPlaceholder = false;
            this.resolvedLeftToLeft = -1;
            this.resolvedLeftToRight = -1;
            this.resolvedRightToLeft = -1;
            this.resolvedRightToRight = -1;
            this.resolveGoneLeftMargin = -1;
            this.resolveGoneRightMargin = -1;
            this.resolvedHorizontalBias = 0.5f;
            this.widget = new ConstraintWidget();
            this.helped = false;
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams source) {
            super(source);
            this.guideBegin = -1;
            this.guideEnd = -1;
            this.guidePercent = -1.0f;
            this.leftToLeft = -1;
            this.leftToRight = -1;
            this.rightToLeft = -1;
            this.rightToRight = -1;
            this.topToTop = -1;
            this.topToBottom = -1;
            this.bottomToTop = -1;
            this.bottomToBottom = -1;
            this.baselineToBaseline = -1;
            this.circleConstraint = -1;
            this.circleRadius = 0;
            this.circleAngle = 0.0f;
            this.startToEnd = -1;
            this.startToStart = -1;
            this.endToStart = -1;
            this.endToEnd = -1;
            this.goneLeftMargin = -1;
            this.goneTopMargin = -1;
            this.goneRightMargin = -1;
            this.goneBottomMargin = -1;
            this.goneStartMargin = -1;
            this.goneEndMargin = -1;
            this.horizontalBias = 0.5f;
            this.verticalBias = 0.5f;
            this.dimensionRatio = null;
            this.dimensionRatioValue = 0.0f;
            this.dimensionRatioSide = 1;
            this.horizontalWeight = -1.0f;
            this.verticalWeight = -1.0f;
            this.horizontalChainStyle = 0;
            this.verticalChainStyle = 0;
            this.matchConstraintDefaultWidth = 0;
            this.matchConstraintDefaultHeight = 0;
            this.matchConstraintMinWidth = 0;
            this.matchConstraintMinHeight = 0;
            this.matchConstraintMaxWidth = 0;
            this.matchConstraintMaxHeight = 0;
            this.matchConstraintPercentWidth = 1.0f;
            this.matchConstraintPercentHeight = 1.0f;
            this.editorAbsoluteX = -1;
            this.editorAbsoluteY = -1;
            this.orientation = -1;
            this.constrainedWidth = false;
            this.constrainedHeight = false;
            this.horizontalDimensionFixed = ConstraintLayout.USE_CONSTRAINTS_HELPER;
            this.verticalDimensionFixed = ConstraintLayout.USE_CONSTRAINTS_HELPER;
            this.needsBaseline = false;
            this.isGuideline = false;
            this.isHelper = false;
            this.isInPlaceholder = false;
            this.resolvedLeftToLeft = -1;
            this.resolvedLeftToRight = -1;
            this.resolvedRightToLeft = -1;
            this.resolvedRightToRight = -1;
            this.resolveGoneLeftMargin = -1;
            this.resolveGoneRightMargin = -1;
            this.resolvedHorizontalBias = 0.5f;
            this.widget = new ConstraintWidget();
            this.helped = false;
        }

        @TargetApi(17)
        public void resolveLayoutDirection(int layoutDirection) {
            int preLeftMargin = this.leftMargin;
            int preRightMargin = this.rightMargin;
            super.resolveLayoutDirection(layoutDirection);
            this.resolvedRightToLeft = -1;
            this.resolvedRightToRight = -1;
            this.resolvedLeftToLeft = -1;
            this.resolvedLeftToRight = -1;
            this.resolveGoneLeftMargin = -1;
            this.resolveGoneRightMargin = -1;
            this.resolveGoneLeftMargin = this.goneLeftMargin;
            this.resolveGoneRightMargin = this.goneRightMargin;
            this.resolvedHorizontalBias = this.horizontalBias;
            this.resolvedGuideBegin = this.guideBegin;
            this.resolvedGuideEnd = this.guideEnd;
            this.resolvedGuidePercent = this.guidePercent;
            if (1 == getLayoutDirection() ? ConstraintLayout.USE_CONSTRAINTS_HELPER : false) {
                boolean startEndDefined = false;
                if (this.startToEnd != -1) {
                    this.resolvedRightToLeft = this.startToEnd;
                    startEndDefined = ConstraintLayout.USE_CONSTRAINTS_HELPER;
                } else if (this.startToStart != -1) {
                    this.resolvedRightToRight = this.startToStart;
                    startEndDefined = ConstraintLayout.USE_CONSTRAINTS_HELPER;
                }
                if (this.endToStart != -1) {
                    this.resolvedLeftToRight = this.endToStart;
                    startEndDefined = ConstraintLayout.USE_CONSTRAINTS_HELPER;
                }
                if (this.endToEnd != -1) {
                    this.resolvedLeftToLeft = this.endToEnd;
                    startEndDefined = ConstraintLayout.USE_CONSTRAINTS_HELPER;
                }
                if (this.goneStartMargin != -1) {
                    this.resolveGoneRightMargin = this.goneStartMargin;
                }
                if (this.goneEndMargin != -1) {
                    this.resolveGoneLeftMargin = this.goneEndMargin;
                }
                if (startEndDefined) {
                    this.resolvedHorizontalBias = 1.0f - this.horizontalBias;
                }
                if (this.isGuideline && this.orientation == 1) {
                    if (this.guidePercent != -1.0f) {
                        this.resolvedGuidePercent = 1.0f - this.guidePercent;
                        this.resolvedGuideBegin = -1;
                        this.resolvedGuideEnd = -1;
                    } else if (this.guideBegin != -1) {
                        this.resolvedGuideEnd = this.guideBegin;
                        this.resolvedGuideBegin = -1;
                        this.resolvedGuidePercent = -1.0f;
                    } else if (this.guideEnd != -1) {
                        this.resolvedGuideBegin = this.guideEnd;
                        this.resolvedGuideEnd = -1;
                        this.resolvedGuidePercent = -1.0f;
                    }
                }
            } else {
                if (this.startToEnd != -1) {
                    this.resolvedLeftToRight = this.startToEnd;
                }
                if (this.startToStart != -1) {
                    this.resolvedLeftToLeft = this.startToStart;
                }
                if (this.endToStart != -1) {
                    this.resolvedRightToLeft = this.endToStart;
                }
                if (this.endToEnd != -1) {
                    this.resolvedRightToRight = this.endToEnd;
                }
                if (this.goneStartMargin != -1) {
                    this.resolveGoneLeftMargin = this.goneStartMargin;
                }
                if (this.goneEndMargin != -1) {
                    this.resolveGoneRightMargin = this.goneEndMargin;
                }
            }
            if (this.endToStart == -1 && this.endToEnd == -1 && this.startToStart == -1 && this.startToEnd == -1) {
                if (this.rightToLeft != -1) {
                    this.resolvedRightToLeft = this.rightToLeft;
                    if (this.rightMargin <= 0 && preRightMargin > 0) {
                        this.rightMargin = preRightMargin;
                    }
                } else if (this.rightToRight != -1) {
                    this.resolvedRightToRight = this.rightToRight;
                    if (this.rightMargin <= 0 && preRightMargin > 0) {
                        this.rightMargin = preRightMargin;
                    }
                }
                if (this.leftToLeft != -1) {
                    this.resolvedLeftToLeft = this.leftToLeft;
                    if (this.leftMargin <= 0 && preLeftMargin > 0) {
                        this.leftMargin = preLeftMargin;
                    }
                } else if (this.leftToRight != -1) {
                    this.resolvedLeftToRight = this.leftToRight;
                    if (this.leftMargin <= 0 && preLeftMargin > 0) {
                        this.leftMargin = preLeftMargin;
                    }
                }
            }
        }
    }

    public void setDesignInformation(int type, Object value1, Object value2) {
        if (type == 0 && (value1 instanceof String) && (value2 instanceof Integer)) {
            if (this.mDesignIds == null) {
                this.mDesignIds = new HashMap();
            }
            String name = (String) value1;
            int index = name.indexOf("/");
            if (index != -1) {
                name = name.substring(index + 1);
            }
            this.mDesignIds.put(name, Integer.valueOf(((Integer) value2).intValue()));
        }
    }

    public Object getDesignInformation(int type, Object value) {
        if (type == 0 && (value instanceof String)) {
            String name = (String) value;
            if (this.mDesignIds != null && this.mDesignIds.containsKey(name)) {
                return this.mDesignIds.get(name);
            }
        }
        return null;
    }

    public ConstraintLayout(Context context) {
        super(context);
        init(null);
    }

    public ConstraintLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ConstraintLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public void setId(int id) {
        this.mChildrenByIds.remove(getId());
        super.setId(id);
        this.mChildrenByIds.put(getId(), this);
    }

    private void init(AttributeSet attrs) {
        this.mLayoutWidget.setCompanionWidget(this);
        this.mChildrenByIds.put(getId(), this);
        this.mConstraintSet = null;
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ConstraintLayout_Layout);
            int N = a.getIndexCount();
            for (int i = 0; i < N; i++) {
                int attr = a.getIndex(i);
                if (attr == R.styleable.ConstraintLayout_Layout_android_minWidth) {
                    this.mMinWidth = a.getDimensionPixelOffset(attr, this.mMinWidth);
                } else if (attr == R.styleable.ConstraintLayout_Layout_android_minHeight) {
                    this.mMinHeight = a.getDimensionPixelOffset(attr, this.mMinHeight);
                } else if (attr == R.styleable.ConstraintLayout_Layout_android_maxWidth) {
                    this.mMaxWidth = a.getDimensionPixelOffset(attr, this.mMaxWidth);
                } else if (attr == R.styleable.ConstraintLayout_Layout_android_maxHeight) {
                    this.mMaxHeight = a.getDimensionPixelOffset(attr, this.mMaxHeight);
                } else if (attr == R.styleable.ConstraintLayout_Layout_layout_optimizationLevel) {
                    this.mOptimizationLevel = a.getInt(attr, this.mOptimizationLevel);
                } else if (attr == R.styleable.ConstraintLayout_Layout_constraintSet) {
                    int id = a.getResourceId(attr, 0);
                    try {
                        this.mConstraintSet = new ConstraintSet();
                        this.mConstraintSet.load(getContext(), id);
                    } catch (NotFoundException e) {
                        this.mConstraintSet = null;
                    }
                    this.mConstraintSetId = id;
                }
            }
            a.recycle();
        }
        this.mLayoutWidget.setOptimizationLevel(this.mOptimizationLevel);
    }

    public void addView(View child, int index, android.view.ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        if (VERSION.SDK_INT < 14) {
            onViewAdded(child);
        }
    }

    public void removeView(View view) {
        super.removeView(view);
        if (VERSION.SDK_INT < 14) {
            onViewRemoved(view);
        }
    }

    public void onViewAdded(View view) {
        if (VERSION.SDK_INT >= 14) {
            super.onViewAdded(view);
        }
        ConstraintWidget widget = getViewWidget(view);
        if ((view instanceof Guideline) && !(widget instanceof Guideline)) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            layoutParams.widget = new Guideline();
            layoutParams.isGuideline = USE_CONSTRAINTS_HELPER;
            ((Guideline) layoutParams.widget).setOrientation(layoutParams.orientation);
        }
        if (view instanceof ConstraintHelper) {
            ConstraintHelper helper = (ConstraintHelper) view;
            helper.validateParams();
            ((LayoutParams) view.getLayoutParams()).isHelper = USE_CONSTRAINTS_HELPER;
            if (!this.mConstraintHelpers.contains(helper)) {
                this.mConstraintHelpers.add(helper);
            }
        }
        this.mChildrenByIds.put(view.getId(), view);
        this.mDirtyHierarchy = USE_CONSTRAINTS_HELPER;
    }

    public void onViewRemoved(View view) {
        if (VERSION.SDK_INT >= 14) {
            super.onViewRemoved(view);
        }
        this.mChildrenByIds.remove(view.getId());
        ConstraintWidget widget = getViewWidget(view);
        this.mLayoutWidget.remove(widget);
        this.mConstraintHelpers.remove(view);
        this.mVariableDimensionsWidgets.remove(widget);
        this.mDirtyHierarchy = USE_CONSTRAINTS_HELPER;
    }

    public void setMinWidth(int value) {
        if (value != this.mMinWidth) {
            this.mMinWidth = value;
            requestLayout();
        }
    }

    public void setMinHeight(int value) {
        if (value != this.mMinHeight) {
            this.mMinHeight = value;
            requestLayout();
        }
    }

    public int getMinWidth() {
        return this.mMinWidth;
    }

    public int getMinHeight() {
        return this.mMinHeight;
    }

    public void setMaxWidth(int value) {
        if (value != this.mMaxWidth) {
            this.mMaxWidth = value;
            requestLayout();
        }
    }

    public void setMaxHeight(int value) {
        if (value != this.mMaxHeight) {
            this.mMaxHeight = value;
            requestLayout();
        }
    }

    public int getMaxWidth() {
        return this.mMaxWidth;
    }

    public int getMaxHeight() {
        return this.mMaxHeight;
    }

    private void updateHierarchy() {
        int count = getChildCount();
        boolean recompute = false;
        for (int i = 0; i < count; i++) {
            if (getChildAt(i).isLayoutRequested()) {
                recompute = USE_CONSTRAINTS_HELPER;
                break;
            }
        }
        if (recompute) {
            this.mVariableDimensionsWidgets.clear();
            setChildrenConstraints();
        }
    }

    private void setChildrenConstraints() {
        int i;
        int i2;
        View view;
        String IdAsString;
        boolean isInEditMode = isInEditMode();
        int count = getChildCount();
        boolean z = false;
        int i3 = -1;
        if (isInEditMode) {
            i = 0;
            while (true) {
                i2 = i;
                if (i2 >= count) {
                    break;
                }
                view = getChildAt(i2);
                try {
                    IdAsString = getResources().getResourceName(view.getId());
                    setDesignInformation(0, IdAsString, Integer.valueOf(view.getId()));
                    int slashIndex = IdAsString.indexOf(47);
                    if (slashIndex != -1) {
                        IdAsString = IdAsString.substring(slashIndex + 1);
                    }
                    getTargetWidget(view.getId()).setDebugName(IdAsString);
                } catch (NotFoundException e) {
                }
                i = i2 + 1;
            }
        }
        for (i = 0; i < count; i++) {
            ConstraintWidget widget = getViewWidget(getChildAt(i));
            if (widget != null) {
                widget.reset();
            }
        }
        if (this.mConstraintSetId != -1) {
            for (i = 0; i < count; i++) {
                View child = getChildAt(i);
                if (child.getId() == this.mConstraintSetId && (child instanceof Constraints)) {
                    this.mConstraintSet = ((Constraints) child).getConstraintSet();
                }
            }
        }
        if (this.mConstraintSet != null) {
            this.mConstraintSet.applyToInternal(this);
        }
        this.mLayoutWidget.removeAllChildren();
        i2 = this.mConstraintHelpers.size();
        if (i2 > 0) {
            for (i = 0; i < i2; i++) {
                ((ConstraintHelper) this.mConstraintHelpers.get(i)).updatePreLayout(this);
            }
        }
        for (i = 0; i < count; i++) {
            view = getChildAt(i);
            if (view instanceof Placeholder) {
                ((Placeholder) view).updatePreLayout(this);
            }
        }
        i = 0;
        while (true) {
            int i4 = i;
            int helperCount;
            if (i4 < count) {
                int count2;
                View child2 = getChildAt(i4);
                ConstraintWidget widget2 = getViewWidget(child2);
                if (widget2 != null) {
                    LayoutParams layoutParams = (LayoutParams) child2.getLayoutParams();
                    layoutParams.validate();
                    if (layoutParams.helped) {
                        layoutParams.helped = z;
                    } else if (isInEditMode) {
                        try {
                            IdAsString = getResources().getResourceName(child2.getId());
                            setDesignInformation(z, IdAsString, Integer.valueOf(child2.getId()));
                            getTargetWidget(child2.getId()).setDebugName(IdAsString.substring(IdAsString.indexOf("id/") + 3));
                        } catch (NotFoundException e2) {
                        }
                    }
                    widget2.setVisibility(child2.getVisibility());
                    if (layoutParams.isInPlaceholder) {
                        widget2.setVisibility(8);
                    }
                    widget2.setCompanionWidget(child2);
                    this.mLayoutWidget.add(widget2);
                    if (!(layoutParams.verticalDimensionFixed && layoutParams.horizontalDimensionFixed)) {
                        this.mVariableDimensionsWidgets.add(widget2);
                    }
                    int resolvedGuideBegin;
                    int resolvedGuideEnd;
                    if (layoutParams.isGuideline) {
                        Guideline guideline = (Guideline) widget2;
                        resolvedGuideBegin = layoutParams.resolvedGuideBegin;
                        resolvedGuideEnd = layoutParams.resolvedGuideEnd;
                        float resolvedGuidePercent = layoutParams.resolvedGuidePercent;
                        if (VERSION.SDK_INT < 17) {
                            resolvedGuideBegin = layoutParams.guideBegin;
                            resolvedGuideEnd = layoutParams.guideEnd;
                            resolvedGuidePercent = layoutParams.guidePercent;
                        }
                        if (resolvedGuidePercent != -1.0f) {
                            guideline.setGuidePercent(resolvedGuidePercent);
                        } else if (resolvedGuideBegin != i3) {
                            guideline.setGuideBegin(resolvedGuideBegin);
                        } else if (resolvedGuideEnd != i3) {
                            guideline.setGuideEnd(resolvedGuideEnd);
                        }
                    } else if (!(layoutParams.leftToLeft == i3 && layoutParams.leftToRight == i3 && layoutParams.rightToLeft == i3 && layoutParams.rightToRight == i3 && layoutParams.startToStart == i3 && layoutParams.startToEnd == i3 && layoutParams.endToStart == i3 && layoutParams.endToEnd == i3 && layoutParams.topToTop == i3 && layoutParams.topToBottom == i3 && layoutParams.bottomToTop == i3 && layoutParams.bottomToBottom == i3 && layoutParams.baselineToBaseline == i3 && layoutParams.editorAbsoluteX == i3 && layoutParams.editorAbsoluteY == i3 && layoutParams.circleConstraint == i3 && layoutParams.width != i3 && layoutParams.height != i3)) {
                        int resolvedLeftToRight;
                        LayoutParams layoutParams2;
                        i = layoutParams.resolvedLeftToLeft;
                        resolvedGuideBegin = layoutParams.resolvedLeftToRight;
                        resolvedGuideEnd = layoutParams.resolvedRightToLeft;
                        int resolvedRightToRight = layoutParams.resolvedRightToRight;
                        int resolveGoneLeftMargin = layoutParams.resolveGoneLeftMargin;
                        int resolveGoneRightMargin = layoutParams.resolveGoneRightMargin;
                        i3 = layoutParams.resolvedHorizontalBias;
                        int resolvedLeftToLeft = i;
                        if (VERSION.SDK_INT < 17) {
                            int i5;
                            int i6;
                            i = layoutParams.leftToLeft;
                            resolvedLeftToRight = layoutParams.leftToRight;
                            resolvedGuideEnd = layoutParams.rightToLeft;
                            resolvedRightToRight = layoutParams.rightToRight;
                            resolvedGuideBegin = layoutParams.goneLeftMargin;
                            resolveGoneRightMargin = layoutParams.goneRightMargin;
                            i3 = layoutParams.horizontalBias;
                            if (i == -1 && resolvedLeftToRight == -1) {
                                i5 = i;
                                if (layoutParams.startToStart != -1) {
                                    i = layoutParams.startToStart;
                                    if (resolvedGuideEnd == -1 || resolvedRightToRight != -1) {
                                        i6 = i;
                                    } else {
                                        i6 = i;
                                        if (layoutParams.endToStart != -1) {
                                            resolvedGuideEnd = layoutParams.endToStart;
                                        } else if (layoutParams.endToEnd != -1) {
                                            resolvedRightToRight = layoutParams.endToEnd;
                                        }
                                    }
                                    resolvedLeftToLeft = resolveGoneRightMargin;
                                    resolveGoneRightMargin = resolvedLeftToRight;
                                    resolveGoneLeftMargin = resolvedGuideEnd;
                                    i = i6;
                                    resolvedLeftToRight = -1;
                                    resolvedGuideEnd = i3;
                                    i3 = resolvedGuideBegin;
                                } else if (layoutParams.startToEnd != -1) {
                                    resolvedLeftToRight = layoutParams.startToEnd;
                                }
                            } else {
                                i5 = i;
                            }
                            i = i5;
                            if (resolvedGuideEnd == -1) {
                            }
                            i6 = i;
                            resolvedLeftToLeft = resolveGoneRightMargin;
                            resolveGoneRightMargin = resolvedLeftToRight;
                            resolveGoneLeftMargin = resolvedGuideEnd;
                            i = i6;
                            resolvedLeftToRight = -1;
                            resolvedGuideEnd = i3;
                            i3 = resolvedGuideBegin;
                        } else {
                            resolvedLeftToRight = -1;
                            i = resolvedLeftToLeft;
                            resolvedLeftToLeft = resolveGoneRightMargin;
                            resolveGoneRightMargin = resolvedGuideBegin;
                            int i7 = resolvedGuideEnd;
                            resolvedGuideEnd = i3;
                            i3 = resolveGoneLeftMargin;
                            resolveGoneLeftMargin = i7;
                        }
                        View view2;
                        if (layoutParams.circleConstraint != resolvedLeftToRight) {
                            ConstraintWidget target = getTargetWidget(layoutParams.circleConstraint);
                            if (target != null) {
                                count2 = count;
                                widget2.connectCircularConstraint(target, layoutParams.circleAngle, layoutParams.circleRadius);
                            } else {
                                count2 = count;
                            }
                            int i8 = i;
                            helperCount = i2;
                            view2 = child2;
                            i = resolvedGuideEnd;
                            count = resolvedRightToRight;
                            i2 = resolveGoneLeftMargin;
                            layoutParams2 = layoutParams;
                        } else {
                            float resolvedHorizontalBias;
                            ConstraintWidget target2;
                            count2 = count;
                            if (i != -1) {
                                count = getTargetWidget(i);
                                if (count != 0) {
                                    resolvedHorizontalBias = resolvedGuideEnd;
                                    ConstraintWidget constraintWidget = count;
                                    ConstraintWidget target3 = count;
                                    count = resolvedRightToRight;
                                    helperCount = i2;
                                    i2 = resolveGoneLeftMargin;
                                    layoutParams2 = layoutParams;
                                    widget2.immediateConnect(Type.LEFT, constraintWidget, Type.LEFT, layoutParams.leftMargin, i3);
                                } else {
                                    helperCount = i2;
                                    view2 = child2;
                                    resolvedHorizontalBias = resolvedGuideEnd;
                                    count = resolvedRightToRight;
                                    i2 = resolveGoneLeftMargin;
                                    layoutParams2 = layoutParams;
                                }
                            } else {
                                helperCount = i2;
                                view2 = child2;
                                resolvedHorizontalBias = resolvedGuideEnd;
                                count = resolvedRightToRight;
                                i2 = resolveGoneLeftMargin;
                                layoutParams2 = layoutParams;
                                if (resolveGoneRightMargin != -1) {
                                    target2 = getTargetWidget(resolveGoneRightMargin);
                                    if (target2 != null) {
                                        widget2.immediateConnect(Type.LEFT, target2, Type.RIGHT, layoutParams2.leftMargin, i3);
                                    }
                                }
                            }
                            if (i2 != -1) {
                                target2 = getTargetWidget(i2);
                                if (target2 != null) {
                                    widget2.immediateConnect(Type.RIGHT, target2, Type.LEFT, layoutParams2.rightMargin, resolvedLeftToLeft);
                                }
                            } else if (count != -1) {
                                target2 = getTargetWidget(count);
                                if (target2 != null) {
                                    widget2.immediateConnect(Type.RIGHT, target2, Type.RIGHT, layoutParams2.rightMargin, resolvedLeftToLeft);
                                }
                            }
                            if (layoutParams2.topToTop != -1) {
                                target2 = getTargetWidget(layoutParams2.topToTop);
                                if (target2 != null) {
                                    widget2.immediateConnect(Type.TOP, target2, Type.TOP, layoutParams2.topMargin, layoutParams2.goneTopMargin);
                                }
                            } else if (layoutParams2.topToBottom != -1) {
                                target2 = getTargetWidget(layoutParams2.topToBottom);
                                if (target2 != null) {
                                    widget2.immediateConnect(Type.TOP, target2, Type.BOTTOM, layoutParams2.topMargin, layoutParams2.goneTopMargin);
                                }
                            }
                            if (layoutParams2.bottomToTop != -1) {
                                target2 = getTargetWidget(layoutParams2.bottomToTop);
                                if (target2 != null) {
                                    widget2.immediateConnect(Type.BOTTOM, target2, Type.TOP, layoutParams2.bottomMargin, layoutParams2.goneBottomMargin);
                                }
                            } else if (layoutParams2.bottomToBottom != -1) {
                                target2 = getTargetWidget(layoutParams2.bottomToBottom);
                                if (target2 != null) {
                                    widget2.immediateConnect(Type.BOTTOM, target2, Type.BOTTOM, layoutParams2.bottomMargin, layoutParams2.goneBottomMargin);
                                }
                            }
                            if (layoutParams2.baselineToBaseline != -1) {
                                View view3 = (View) this.mChildrenByIds.get(layoutParams2.baselineToBaseline);
                                ConstraintWidget target4 = getTargetWidget(layoutParams2.baselineToBaseline);
                                if (!(target4 == null || view3 == null || !(view3.getLayoutParams() instanceof LayoutParams))) {
                                    LayoutParams targetParams = (LayoutParams) view3.getLayoutParams();
                                    layoutParams2.needsBaseline = USE_CONSTRAINTS_HELPER;
                                    targetParams.needsBaseline = USE_CONSTRAINTS_HELPER;
                                    ConstraintAnchor baseline = widget2.getAnchor(Type.BASELINE);
                                    baseline.connect(target4.getAnchor(Type.BASELINE), 0, -1, Strength.STRONG, 0, USE_CONSTRAINTS_HELPER);
                                    widget2.getAnchor(Type.TOP).reset();
                                    widget2.getAnchor(Type.BOTTOM).reset();
                                }
                            }
                            if (resolvedHorizontalBias >= 0.0f && resolvedHorizontalBias != 0.5f) {
                                widget2.setHorizontalBiasPercent(resolvedHorizontalBias);
                            }
                            if (layoutParams2.verticalBias >= 0.0f && layoutParams2.verticalBias != 0.5f) {
                                widget2.setVerticalBiasPercent(layoutParams2.verticalBias);
                            }
                        }
                        if (isInEditMode && !(layoutParams2.editorAbsoluteX == -1 && layoutParams2.editorAbsoluteY == -1)) {
                            widget2.setOrigin(layoutParams2.editorAbsoluteX, layoutParams2.editorAbsoluteY);
                        }
                        if (layoutParams2.horizontalDimensionFixed) {
                            widget2.setHorizontalDimensionBehaviour(DimensionBehaviour.FIXED);
                            widget2.setWidth(layoutParams2.width);
                        } else if (layoutParams2.width == -1) {
                            widget2.setHorizontalDimensionBehaviour(DimensionBehaviour.MATCH_PARENT);
                            widget2.getAnchor(Type.LEFT).mMargin = layoutParams2.leftMargin;
                            widget2.getAnchor(Type.RIGHT).mMargin = layoutParams2.rightMargin;
                        } else {
                            widget2.setHorizontalDimensionBehaviour(DimensionBehaviour.MATCH_CONSTRAINT);
                            widget2.setWidth(0);
                        }
                        if (layoutParams2.verticalDimensionFixed) {
                            widget2.setVerticalDimensionBehaviour(DimensionBehaviour.FIXED);
                            widget2.setHeight(layoutParams2.height);
                        } else if (layoutParams2.height == -1) {
                            widget2.setVerticalDimensionBehaviour(DimensionBehaviour.MATCH_PARENT);
                            widget2.getAnchor(Type.TOP).mMargin = layoutParams2.topMargin;
                            widget2.getAnchor(Type.BOTTOM).mMargin = layoutParams2.bottomMargin;
                        } else {
                            widget2.setVerticalDimensionBehaviour(DimensionBehaviour.MATCH_CONSTRAINT);
                            widget2.setHeight(0);
                        }
                        if (layoutParams2.dimensionRatio != null) {
                            widget2.setDimensionRatio(layoutParams2.dimensionRatio);
                        }
                        widget2.setHorizontalWeight(layoutParams2.horizontalWeight);
                        widget2.setVerticalWeight(layoutParams2.verticalWeight);
                        widget2.setHorizontalChainStyle(layoutParams2.horizontalChainStyle);
                        widget2.setVerticalChainStyle(layoutParams2.verticalChainStyle);
                        widget2.setHorizontalMatchStyle(layoutParams2.matchConstraintDefaultWidth, layoutParams2.matchConstraintMinWidth, layoutParams2.matchConstraintMaxWidth, layoutParams2.matchConstraintPercentWidth);
                        widget2.setVerticalMatchStyle(layoutParams2.matchConstraintDefaultHeight, layoutParams2.matchConstraintMinHeight, layoutParams2.matchConstraintMaxHeight, layoutParams2.matchConstraintPercentHeight);
                        i = i4 + 1;
                        count = count2;
                        i2 = helperCount;
                        z = false;
                        i3 = -1;
                    }
                }
                count2 = count;
                helperCount = i2;
                i = i4 + 1;
                count = count2;
                i2 = helperCount;
                z = false;
                i3 = -1;
            } else {
                helperCount = i2;
                return;
            }
        }
    }

    private final ConstraintWidget getTargetWidget(int id) {
        if (id == 0) {
            return this.mLayoutWidget;
        }
        View view = (View) this.mChildrenByIds.get(id);
        if (view == null) {
            view = findViewById(id);
            if (!(view == null || view == this || view.getParent() != this)) {
                onViewAdded(view);
            }
        }
        if (view == this) {
            return this.mLayoutWidget;
        }
        return view == null ? null : ((LayoutParams) view.getLayoutParams()).widget;
    }

    public final ConstraintWidget getViewWidget(View view) {
        if (view == this) {
            return this.mLayoutWidget;
        }
        return view == null ? null : ((LayoutParams) view.getLayoutParams()).widget;
    }

    private void internalMeasureChildren(int parentWidthSpec, int parentHeightSpec) {
        ConstraintLayout constraintLayout = this;
        int i = parentWidthSpec;
        int i2 = parentHeightSpec;
        int heightPadding = getPaddingTop() + getPaddingBottom();
        int widthPadding = getPaddingLeft() + getPaddingRight();
        int widgetsCount = getChildCount();
        int i3 = 0;
        while (i3 < widgetsCount) {
            View child = constraintLayout.getChildAt(i3);
            if (child.getVisibility() != 8) {
                LayoutParams params = (LayoutParams) child.getLayoutParams();
                ConstraintWidget widget = params.widget;
                if (!(params.isGuideline || params.isHelper)) {
                    widget.setVisibility(child.getVisibility());
                    int width = params.width;
                    int height = params.height;
                    boolean doMeasure = (params.horizontalDimensionFixed || params.verticalDimensionFixed || ((!params.horizontalDimensionFixed && params.matchConstraintDefaultWidth == 1) || params.width == -1 || (!params.verticalDimensionFixed && (params.matchConstraintDefaultHeight == 1 || params.height == -1)))) ? USE_CONSTRAINTS_HELPER : false;
                    boolean didWrapMeasureWidth = false;
                    boolean didWrapMeasureHeight = false;
                    if (doMeasure) {
                        int childWidthMeasureSpec;
                        if (width == 0) {
                            childWidthMeasureSpec = getChildMeasureSpec(i, widthPadding, -2);
                            didWrapMeasureWidth = USE_CONSTRAINTS_HELPER;
                        } else if (width == -1) {
                            childWidthMeasureSpec = getChildMeasureSpec(i, widthPadding, -1);
                        } else {
                            if (width == -2) {
                                didWrapMeasureWidth = USE_CONSTRAINTS_HELPER;
                            }
                            childWidthMeasureSpec = getChildMeasureSpec(i, widthPadding, width);
                        }
                        int childWidthMeasureSpec2 = childWidthMeasureSpec;
                        if (height == 0) {
                            childWidthMeasureSpec = getChildMeasureSpec(i2, heightPadding, -2);
                            didWrapMeasureHeight = USE_CONSTRAINTS_HELPER;
                        } else if (height == -1) {
                            childWidthMeasureSpec = getChildMeasureSpec(i2, heightPadding, -1);
                        } else {
                            if (height == -2) {
                                didWrapMeasureHeight = USE_CONSTRAINTS_HELPER;
                            }
                            childWidthMeasureSpec = getChildMeasureSpec(i2, heightPadding, height);
                        }
                        child.measure(childWidthMeasureSpec2, childWidthMeasureSpec);
                        if (constraintLayout.mMetrics != null) {
                            Metrics metrics = constraintLayout.mMetrics;
                            metrics.measures++;
                        }
                        widget.setWidthWrapContent(width == -2 ? USE_CONSTRAINTS_HELPER : false);
                        widget.setHeightWrapContent(height == -2 ? USE_CONSTRAINTS_HELPER : false);
                        width = child.getMeasuredWidth();
                        height = child.getMeasuredHeight();
                    }
                    widget.setWidth(width);
                    widget.setHeight(height);
                    if (didWrapMeasureWidth) {
                        widget.setWrapWidth(width);
                    }
                    if (didWrapMeasureHeight) {
                        widget.setWrapHeight(height);
                    }
                    if (params.needsBaseline) {
                        int baseline = child.getBaseline();
                        if (baseline != -1) {
                            widget.setBaselineDistance(baseline);
                        }
                    }
                }
            }
            i3++;
            constraintLayout = this;
            i = parentWidthSpec;
        }
    }

    private void updatePostMeasures() {
        int i;
        int widgetsCount = getChildCount();
        for (i = 0; i < widgetsCount; i++) {
            View child = getChildAt(i);
            if (child instanceof Placeholder) {
                ((Placeholder) child).updatePostMeasure(this);
            }
        }
        i = this.mConstraintHelpers.size();
        if (i > 0) {
            for (int i2 = 0; i2 < i; i2++) {
                ((ConstraintHelper) this.mConstraintHelpers.get(i2)).updatePostMeasure(this);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:131:0x0284  */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x0275  */
    /* JADX WARNING: Removed duplicated region for block: B:135:0x028f  */
    /* JADX WARNING: Removed duplicated region for block: B:134:0x028d  */
    /* JADX WARNING: Removed duplicated region for block: B:139:0x0297  */
    /* JADX WARNING: Removed duplicated region for block: B:138:0x0295  */
    /* JADX WARNING: Removed duplicated region for block: B:142:0x02ab  */
    /* JADX WARNING: Removed duplicated region for block: B:144:0x02b0  */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x02bd  */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x02b5  */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x02ce  */
    /* JADX WARNING: Removed duplicated region for block: B:149:0x02c6  */
    /* JADX WARNING: Removed duplicated region for block: B:156:0x02e6  */
    /* JADX WARNING: Removed duplicated region for block: B:153:0x02db  */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x0275  */
    /* JADX WARNING: Removed duplicated region for block: B:131:0x0284  */
    /* JADX WARNING: Removed duplicated region for block: B:134:0x028d  */
    /* JADX WARNING: Removed duplicated region for block: B:135:0x028f  */
    /* JADX WARNING: Removed duplicated region for block: B:138:0x0295  */
    /* JADX WARNING: Removed duplicated region for block: B:139:0x0297  */
    /* JADX WARNING: Removed duplicated region for block: B:142:0x02ab  */
    /* JADX WARNING: Removed duplicated region for block: B:144:0x02b0  */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x02b5  */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x02bd  */
    /* JADX WARNING: Removed duplicated region for block: B:149:0x02c6  */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x02ce  */
    /* JADX WARNING: Removed duplicated region for block: B:153:0x02db  */
    /* JADX WARNING: Removed duplicated region for block: B:156:0x02e6  */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x0254  */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x0216  */
    /* JADX WARNING: Removed duplicated region for block: B:131:0x0284  */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x0275  */
    /* JADX WARNING: Removed duplicated region for block: B:135:0x028f  */
    /* JADX WARNING: Removed duplicated region for block: B:134:0x028d  */
    /* JADX WARNING: Removed duplicated region for block: B:139:0x0297  */
    /* JADX WARNING: Removed duplicated region for block: B:138:0x0295  */
    /* JADX WARNING: Removed duplicated region for block: B:142:0x02ab  */
    /* JADX WARNING: Removed duplicated region for block: B:144:0x02b0  */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x02bd  */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x02b5  */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x02ce  */
    /* JADX WARNING: Removed duplicated region for block: B:149:0x02c6  */
    /* JADX WARNING: Removed duplicated region for block: B:156:0x02e6  */
    /* JADX WARNING: Removed duplicated region for block: B:153:0x02db  */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x0216  */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x0254  */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x0275  */
    /* JADX WARNING: Removed duplicated region for block: B:131:0x0284  */
    /* JADX WARNING: Removed duplicated region for block: B:134:0x028d  */
    /* JADX WARNING: Removed duplicated region for block: B:135:0x028f  */
    /* JADX WARNING: Removed duplicated region for block: B:138:0x0295  */
    /* JADX WARNING: Removed duplicated region for block: B:139:0x0297  */
    /* JADX WARNING: Removed duplicated region for block: B:142:0x02ab  */
    /* JADX WARNING: Removed duplicated region for block: B:144:0x02b0  */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x02b5  */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x02bd  */
    /* JADX WARNING: Removed duplicated region for block: B:149:0x02c6  */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x02ce  */
    /* JADX WARNING: Removed duplicated region for block: B:153:0x02db  */
    /* JADX WARNING: Removed duplicated region for block: B:156:0x02e6  */
    private void internalMeasureDimensions(int r31, int r32) {
        /*
        r30 = this;
        r0 = r30;
        r1 = r31;
        r2 = r32;
        r3 = r30.getPaddingTop();
        r4 = r30.getPaddingBottom();
        r3 = r3 + r4;
        r4 = r30.getPaddingLeft();
        r5 = r30.getPaddingRight();
        r4 = r4 + r5;
        r5 = r30.getChildCount();
        r7 = 0;
    L_0x001d:
        r10 = 8;
        r12 = -2;
        if (r7 >= r5) goto L_0x00e0;
    L_0x0022:
        r14 = r0.getChildAt(r7);
        r15 = r14.getVisibility();
        if (r15 != r10) goto L_0x002e;
    L_0x002c:
        goto L_0x00dc;
    L_0x002e:
        r10 = r14.getLayoutParams();
        r10 = (android.support.constraint.ConstraintLayout.LayoutParams) r10;
        r15 = r10.widget;
        r6 = r10.isGuideline;
        if (r6 != 0) goto L_0x00dc;
    L_0x003a:
        r6 = r10.isHelper;
        if (r6 == 0) goto L_0x0040;
    L_0x003e:
        goto L_0x00dc;
    L_0x0040:
        r6 = r14.getVisibility();
        r15.setVisibility(r6);
        r6 = r10.width;
        r13 = r10.height;
        if (r6 == 0) goto L_0x00cd;
    L_0x004d:
        if (r13 != 0) goto L_0x0051;
    L_0x004f:
        goto L_0x00cd;
    L_0x0051:
        r16 = 0;
        r17 = 0;
        if (r6 != r12) goto L_0x0059;
    L_0x0057:
        r16 = 1;
    L_0x0059:
        r11 = getChildMeasureSpec(r1, r4, r6);
        if (r13 != r12) goto L_0x0061;
    L_0x005f:
        r17 = 1;
    L_0x0061:
        r12 = getChildMeasureSpec(r2, r3, r13);
        r14.measure(r11, r12);
        r8 = r0.mMetrics;
        if (r8 == 0) goto L_0x007b;
    L_0x006c:
        r8 = r0.mMetrics;
        r20 = r11;
        r21 = r12;
        r11 = r8.measures;
        r18 = 1;
        r11 = r11 + r18;
        r8.measures = r11;
        goto L_0x007f;
    L_0x007b:
        r20 = r11;
        r21 = r12;
    L_0x007f:
        r8 = -2;
        if (r6 != r8) goto L_0x0084;
    L_0x0082:
        r9 = 1;
        goto L_0x0085;
    L_0x0084:
        r9 = 0;
    L_0x0085:
        r15.setWidthWrapContent(r9);
        if (r13 != r8) goto L_0x008c;
    L_0x008a:
        r8 = 1;
        goto L_0x008d;
    L_0x008c:
        r8 = 0;
    L_0x008d:
        r15.setHeightWrapContent(r8);
        r6 = r14.getMeasuredWidth();
        r8 = r14.getMeasuredHeight();
        r15.setWidth(r6);
        r15.setHeight(r8);
        if (r16 == 0) goto L_0x00a3;
    L_0x00a0:
        r15.setWrapWidth(r6);
    L_0x00a3:
        if (r17 == 0) goto L_0x00a8;
    L_0x00a5:
        r15.setWrapHeight(r8);
    L_0x00a8:
        r9 = r10.needsBaseline;
        if (r9 == 0) goto L_0x00b6;
    L_0x00ac:
        r9 = r14.getBaseline();
        r11 = -1;
        if (r9 == r11) goto L_0x00b6;
    L_0x00b3:
        r15.setBaselineDistance(r9);
    L_0x00b6:
        r9 = r10.horizontalDimensionFixed;
        if (r9 == 0) goto L_0x00dc;
    L_0x00ba:
        r9 = r10.verticalDimensionFixed;
        if (r9 == 0) goto L_0x00dc;
    L_0x00be:
        r9 = r15.getResolutionWidth();
        r9.resolve(r6);
        r9 = r15.getResolutionHeight();
        r9.resolve(r8);
        goto L_0x00dc;
    L_0x00cd:
        r8 = r15.getResolutionWidth();
        r8.invalidate();
        r8 = r15.getResolutionHeight();
        r8.invalidate();
    L_0x00dc:
        r7 = r7 + 1;
        goto L_0x001d;
    L_0x00e0:
        r6 = r0.mLayoutWidget;
        r6.solveGraph();
        r6 = 0;
    L_0x00e6:
        if (r6 >= r5) goto L_0x0303;
    L_0x00e8:
        r7 = r0.getChildAt(r6);
        r8 = r7.getVisibility();
        if (r8 != r10) goto L_0x0100;
    L_0x00f3:
        r28 = r3;
        r29 = r4;
        r22 = r5;
        r23 = r6;
    L_0x00fb:
        r2 = -1;
        r18 = 1;
        goto L_0x02f3;
    L_0x0100:
        r8 = r7.getLayoutParams();
        r8 = (android.support.constraint.ConstraintLayout.LayoutParams) r8;
        r9 = r8.widget;
        r11 = r8.isGuideline;
        if (r11 != 0) goto L_0x02e8;
    L_0x010c:
        r11 = r8.isHelper;
        if (r11 == 0) goto L_0x0111;
    L_0x0110:
        goto L_0x00f3;
    L_0x0111:
        r11 = r7.getVisibility();
        r9.setVisibility(r11);
        r11 = r8.width;
        r12 = r8.height;
        if (r11 == 0) goto L_0x0121;
    L_0x011e:
        if (r12 == 0) goto L_0x0121;
    L_0x0120:
        goto L_0x00f3;
    L_0x0121:
        r13 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.LEFT;
        r13 = r9.getAnchor(r13);
        r13 = r13.getResolutionNode();
        r14 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.RIGHT;
        r14 = r9.getAnchor(r14);
        r14 = r14.getResolutionNode();
        r15 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.LEFT;
        r15 = r9.getAnchor(r15);
        r15 = r15.getTarget();
        if (r15 == 0) goto L_0x014f;
    L_0x0141:
        r15 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.RIGHT;
        r15 = r9.getAnchor(r15);
        r15 = r15.getTarget();
        if (r15 == 0) goto L_0x014f;
    L_0x014d:
        r15 = 1;
        goto L_0x0150;
    L_0x014f:
        r15 = 0;
    L_0x0150:
        r10 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.TOP;
        r10 = r9.getAnchor(r10);
        r10 = r10.getResolutionNode();
        r22 = r5;
        r5 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.BOTTOM;
        r5 = r9.getAnchor(r5);
        r5 = r5.getResolutionNode();
        r23 = r6;
        r6 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.TOP;
        r6 = r9.getAnchor(r6);
        r6 = r6.getTarget();
        if (r6 == 0) goto L_0x0182;
    L_0x0174:
        r6 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.BOTTOM;
        r6 = r9.getAnchor(r6);
        r6 = r6.getTarget();
        if (r6 == 0) goto L_0x0182;
    L_0x0180:
        r6 = 1;
        goto L_0x0183;
    L_0x0182:
        r6 = 0;
    L_0x0183:
        if (r11 != 0) goto L_0x0192;
    L_0x0185:
        if (r12 != 0) goto L_0x0192;
    L_0x0187:
        if (r15 == 0) goto L_0x0192;
    L_0x0189:
        if (r6 == 0) goto L_0x0192;
        r28 = r3;
        r29 = r4;
        goto L_0x00fb;
    L_0x0192:
        r16 = 0;
        r17 = 0;
        r24 = r8;
        r8 = r0.mLayoutWidget;
        r8 = r8.getHorizontalDimensionBehaviour();
        r25 = r7;
        r7 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (r8 == r7) goto L_0x01a6;
    L_0x01a4:
        r7 = 1;
        goto L_0x01a7;
    L_0x01a6:
        r7 = 0;
    L_0x01a7:
        r8 = r0.mLayoutWidget;
        r8 = r8.getVerticalDimensionBehaviour();
        r0 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (r8 == r0) goto L_0x01b3;
    L_0x01b1:
        r0 = 1;
        goto L_0x01b4;
    L_0x01b3:
        r0 = 0;
    L_0x01b4:
        if (r7 != 0) goto L_0x01bd;
    L_0x01b6:
        r8 = r9.getResolutionWidth();
        r8.invalidate();
    L_0x01bd:
        if (r0 != 0) goto L_0x01c6;
    L_0x01bf:
        r8 = r9.getResolutionHeight();
        r8.invalidate();
    L_0x01c6:
        if (r11 != 0) goto L_0x01fe;
    L_0x01c8:
        if (r7 == 0) goto L_0x01f5;
    L_0x01ca:
        r8 = r9.isSpreadWidth();
        if (r8 == 0) goto L_0x01f5;
    L_0x01d0:
        if (r15 == 0) goto L_0x01f5;
    L_0x01d2:
        r8 = r13.isResolved();
        if (r8 == 0) goto L_0x01f5;
    L_0x01d8:
        r8 = r14.isResolved();
        if (r8 == 0) goto L_0x01f5;
    L_0x01de:
        r8 = r14.getResolvedValue();
        r20 = r13.getResolvedValue();
        r8 = r8 - r20;
        r11 = (int) r8;
        r8 = r9.getResolutionWidth();
        r8.resolve(r11);
        r8 = getChildMeasureSpec(r1, r4, r11);
        goto L_0x0210;
    L_0x01f5:
        r8 = -2;
        r20 = getChildMeasureSpec(r1, r4, r8);
        r16 = 1;
        r7 = 0;
        goto L_0x0212;
    L_0x01fe:
        r8 = -1;
        if (r11 != r8) goto L_0x0206;
    L_0x0201:
        r20 = getChildMeasureSpec(r1, r4, r8);
        goto L_0x0212;
    L_0x0206:
        r8 = -2;
        if (r11 != r8) goto L_0x020c;
    L_0x0209:
        r8 = 1;
        r16 = r8;
    L_0x020c:
        r8 = getChildMeasureSpec(r1, r4, r11);
    L_0x0210:
        r20 = r8;
    L_0x0212:
        r8 = r20;
        if (r12 != 0) goto L_0x0254;
    L_0x0216:
        if (r0 == 0) goto L_0x0245;
    L_0x0218:
        r20 = r9.isSpreadHeight();
        if (r20 == 0) goto L_0x0245;
    L_0x021e:
        if (r6 == 0) goto L_0x0245;
    L_0x0220:
        r20 = r10.isResolved();
        if (r20 == 0) goto L_0x0245;
    L_0x0226:
        r20 = r5.isResolved();
        if (r20 == 0) goto L_0x0245;
    L_0x022c:
        r20 = r5.getResolvedValue();
        r21 = r10.getResolvedValue();
        r26 = r0;
        r0 = r20 - r21;
        r12 = (int) r0;
        r0 = r9.getResolutionHeight();
        r0.resolve(r12);
        r0 = getChildMeasureSpec(r2, r3, r12);
        goto L_0x0268;
    L_0x0245:
        r26 = r0;
        r0 = -2;
        r20 = getChildMeasureSpec(r2, r3, r0);
        r17 = 1;
        r0 = 0;
        r26 = r0;
    L_0x0251:
        r0 = r20;
        goto L_0x0268;
    L_0x0254:
        r26 = r0;
        r0 = -1;
        if (r12 != r0) goto L_0x025e;
    L_0x0259:
        r20 = getChildMeasureSpec(r2, r3, r0);
        goto L_0x0251;
    L_0x025e:
        r0 = -2;
        if (r12 != r0) goto L_0x0264;
    L_0x0261:
        r0 = 1;
        r17 = r0;
    L_0x0264:
        r0 = getChildMeasureSpec(r2, r3, r12);
    L_0x0268:
        r1 = r25;
        r1.measure(r8, r0);
        r27 = r0;
        r0 = r30;
        r2 = r0.mMetrics;
        if (r2 == 0) goto L_0x0284;
    L_0x0275:
        r2 = r0.mMetrics;
        r28 = r3;
        r29 = r4;
        r3 = r2.measures;
        r18 = 1;
        r3 = r3 + r18;
        r2.measures = r3;
        goto L_0x028a;
    L_0x0284:
        r28 = r3;
        r29 = r4;
        r18 = 1;
    L_0x028a:
        r2 = -2;
        if (r11 != r2) goto L_0x028f;
    L_0x028d:
        r3 = 1;
        goto L_0x0290;
    L_0x028f:
        r3 = 0;
    L_0x0290:
        r9.setWidthWrapContent(r3);
        if (r12 != r2) goto L_0x0297;
    L_0x0295:
        r3 = 1;
        goto L_0x0298;
    L_0x0297:
        r3 = 0;
    L_0x0298:
        r9.setHeightWrapContent(r3);
        r3 = r1.getMeasuredWidth();
        r4 = r1.getMeasuredHeight();
        r9.setWidth(r3);
        r9.setHeight(r4);
        if (r16 == 0) goto L_0x02ae;
    L_0x02ab:
        r9.setWrapWidth(r3);
    L_0x02ae:
        if (r17 == 0) goto L_0x02b3;
    L_0x02b0:
        r9.setWrapHeight(r4);
    L_0x02b3:
        if (r7 == 0) goto L_0x02bd;
    L_0x02b5:
        r11 = r9.getResolutionWidth();
        r11.resolve(r3);
        goto L_0x02c4;
    L_0x02bd:
        r11 = r9.getResolutionWidth();
        r11.remove();
    L_0x02c4:
        if (r26 == 0) goto L_0x02ce;
    L_0x02c6:
        r11 = r9.getResolutionHeight();
        r11.resolve(r4);
        goto L_0x02d5;
    L_0x02ce:
        r11 = r9.getResolutionHeight();
        r11.remove();
    L_0x02d5:
        r11 = r24;
        r12 = r11.needsBaseline;
        if (r12 == 0) goto L_0x02e6;
    L_0x02db:
        r12 = r1.getBaseline();
        r2 = -1;
        if (r12 == r2) goto L_0x02f3;
    L_0x02e2:
        r9.setBaselineDistance(r12);
        goto L_0x02f3;
    L_0x02e6:
        r2 = -1;
        goto L_0x02f3;
    L_0x02e8:
        r28 = r3;
        r29 = r4;
        r22 = r5;
        r23 = r6;
        r2 = -1;
        r18 = 1;
    L_0x02f3:
        r6 = r23 + 1;
        r5 = r22;
        r3 = r28;
        r4 = r29;
        r1 = r31;
        r2 = r32;
        r10 = 8;
        goto L_0x00e6;
    L_0x0303:
        r28 = r3;
        r29 = r4;
        r22 = r5;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.ConstraintLayout.internalMeasureDimensions(int, int):void");
    }

    public void fillMetrics(Metrics metrics) {
        this.mMetrics = metrics;
        this.mLayoutWidget.fillMetrics(metrics);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width;
        int height;
        int REMEASURES_B;
        int i = widthMeasureSpec;
        int i2 = heightMeasureSpec;
        long time = System.currentTimeMillis();
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        this.mLayoutWidget.setX(paddingLeft);
        this.mLayoutWidget.setY(paddingTop);
        this.mLayoutWidget.setMaxWidth(this.mMaxWidth);
        this.mLayoutWidget.setMaxHeight(this.mMaxHeight);
        if (VERSION.SDK_INT >= 17) {
            this.mLayoutWidget.setRtl(getLayoutDirection() == 1 ? USE_CONSTRAINTS_HELPER : false);
        }
        setSelfDimensionBehaviour(widthMeasureSpec, heightMeasureSpec);
        int startingWidth = this.mLayoutWidget.getWidth();
        int startingHeight = this.mLayoutWidget.getHeight();
        boolean runAnalyzer = false;
        if (this.mDirtyHierarchy) {
            this.mDirtyHierarchy = false;
            updateHierarchy();
            runAnalyzer = USE_CONSTRAINTS_HELPER;
        }
        boolean optimiseDimensions = (this.mOptimizationLevel & 8) == 8 ? USE_CONSTRAINTS_HELPER : false;
        if (optimiseDimensions) {
            this.mLayoutWidget.preOptimize();
            this.mLayoutWidget.optimizeForDimensions(startingWidth, startingHeight);
            internalMeasureDimensions(widthMeasureSpec, heightMeasureSpec);
        } else {
            internalMeasureChildren(widthMeasureSpec, heightMeasureSpec);
        }
        updatePostMeasures();
        if (getChildCount() > 0 && runAnalyzer) {
            Analyzer.determineGroups(this.mLayoutWidget);
        }
        if (this.mLayoutWidget.mGroupsWrapOptimized) {
            if (this.mLayoutWidget.mHorizontalWrapOptimized && widthMode == Integer.MIN_VALUE) {
                if (this.mLayoutWidget.mWrapFixedWidth < widthSize) {
                    this.mLayoutWidget.setWidth(this.mLayoutWidget.mWrapFixedWidth);
                }
                this.mLayoutWidget.setHorizontalDimensionBehaviour(DimensionBehaviour.FIXED);
            }
            if (this.mLayoutWidget.mVerticalWrapOptimized && heightMode == Integer.MIN_VALUE) {
                if (this.mLayoutWidget.mWrapFixedHeight < heightSize) {
                    this.mLayoutWidget.setHeight(this.mLayoutWidget.mWrapFixedHeight);
                }
                this.mLayoutWidget.setVerticalDimensionBehaviour(DimensionBehaviour.FIXED);
            }
        }
        int REMEASURES_A = 0;
        if ((this.mOptimizationLevel & 32) == 32) {
            width = this.mLayoutWidget.getWidth();
            height = this.mLayoutWidget.getHeight();
            if (this.mLastMeasureWidth == width || widthMode != 1073741824) {
                REMEASURES_B = 0;
            } else {
                REMEASURES_B = 0;
                Analyzer.setPosition(this.mLayoutWidget.mWidgetGroups, 0, width);
            }
            if (this.mLastMeasureHeight != height && heightMode == 1073741824) {
                Analyzer.setPosition(this.mLayoutWidget.mWidgetGroups, 1, height);
            }
            if (this.mLayoutWidget.mHorizontalWrapOptimized && this.mLayoutWidget.mWrapFixedWidth > widthSize) {
                Analyzer.setPosition(this.mLayoutWidget.mWidgetGroups, 0, widthSize);
            }
            if (this.mLayoutWidget.mVerticalWrapOptimized && this.mLayoutWidget.mWrapFixedHeight > heightSize) {
                Analyzer.setPosition(this.mLayoutWidget.mWidgetGroups, 1, heightSize);
            }
        } else {
            REMEASURES_B = 0;
        }
        if (getChildCount() > 0) {
            solveLinearSystem("First pass");
        }
        int sizeDependentWidgetsCount = this.mVariableDimensionsWidgets.size();
        height = getPaddingBottom() + paddingTop;
        int widthPadding = paddingLeft + getPaddingRight();
        int childState = 0;
        int paddingTop2;
        int startingWidth2;
        if (sizeDependentWidgetsCount > 0) {
            ConstraintWidget widget;
            int sizeDependentWidgetsCount2;
            int startingWidth3;
            int startingHeight2;
            boolean needSolverPass = false;
            boolean containerWrapWidth = this.mLayoutWidget.getHorizontalDimensionBehaviour() == DimensionBehaviour.WRAP_CONTENT ? USE_CONSTRAINTS_HELPER : false;
            widthMode = this.mLayoutWidget.getVerticalDimensionBehaviour() == DimensionBehaviour.WRAP_CONTENT ? 1 : 0;
            paddingLeft = childState;
            heightMode = Math.max(this.mLayoutWidget.getWidth(), this.mMinWidth);
            heightSize = Math.max(this.mLayoutWidget.getHeight(), this.mMinHeight);
            widthSize = 0;
            while (widthSize < sizeDependentWidgetsCount) {
                int i3;
                paddingTop2 = paddingTop;
                widget = (ConstraintWidget) this.mVariableDimensionsWidgets.get(widthSize);
                sizeDependentWidgetsCount2 = sizeDependentWidgetsCount;
                View sizeDependentWidgetsCount3 = (View) widget.getCompanionWidget();
                if (sizeDependentWidgetsCount3 == null) {
                    i3 = widthSize;
                    startingWidth3 = startingWidth;
                    startingHeight2 = startingHeight;
                } else {
                    startingHeight2 = startingHeight;
                    LayoutParams params = (LayoutParams) sizeDependentWidgetsCount3.getLayoutParams();
                    startingWidth3 = startingWidth;
                    if (params.isHelper) {
                        i3 = widthSize;
                    } else if (params.isGuideline) {
                        i3 = widthSize;
                    } else {
                        i3 = widthSize;
                        if (sizeDependentWidgetsCount3.getVisibility() != 8 && (!optimiseDimensions || widget.getResolutionWidth().isResolved() == 0 || widget.getResolutionHeight().isResolved() == 0)) {
                            int widthSpec = 0;
                            int heightSpec = 0;
                            if (params.width != -2 || params.horizontalDimensionFixed == 0) {
                                widthSize = MeasureSpec.makeMeasureSpec(widget.getWidth(), 1073741824);
                            } else {
                                widthSize = getChildMeasureSpec(i, widthPadding, params.width);
                            }
                            if (params.height == -2 && params.verticalDimensionFixed) {
                                i = getChildMeasureSpec(i2, height, params.height);
                            } else {
                                i = MeasureSpec.makeMeasureSpec(widget.getHeight(), 1073741824);
                            }
                            sizeDependentWidgetsCount3.measure(widthSize, i);
                            if (this.mMetrics != null) {
                                Metrics metrics = this.mMetrics;
                                metrics.additionalMeasures++;
                            }
                            REMEASURES_A++;
                            i = sizeDependentWidgetsCount3.getMeasuredWidth();
                            i2 = sizeDependentWidgetsCount3.getMeasuredHeight();
                            if (i != widget.getWidth()) {
                                widget.setWidth(i);
                                if (optimiseDimensions) {
                                    widget.getResolutionWidth().resolve(i);
                                }
                                if (!containerWrapWidth || widget.getRight() <= heightMode) {
                                } else {
                                    heightMode = Math.max(heightMode, widget.getRight() + widget.getAnchor(Type.RIGHT).getMargin());
                                }
                                needSolverPass = USE_CONSTRAINTS_HELPER;
                            }
                            if (i2 != widget.getHeight()) {
                                widget.setHeight(i2);
                                if (optimiseDimensions) {
                                    widget.getResolutionHeight().resolve(i2);
                                }
                                if (widthMode != 0 && widget.getBottom() > heightSize) {
                                    heightSize = Math.max(heightSize, widget.getBottom() + widget.getAnchor(Type.BOTTOM).getMargin());
                                }
                                needSolverPass = USE_CONSTRAINTS_HELPER;
                            }
                            if (params.needsBaseline) {
                                i = sizeDependentWidgetsCount3.getBaseline();
                                if (!(i == -1 || i == widget.getBaselineDistance())) {
                                    widget.setBaselineDistance(i);
                                    needSolverPass = USE_CONSTRAINTS_HELPER;
                                }
                            }
                            if (VERSION.SDK_INT >= 11) {
                                paddingLeft = combineMeasuredStates(paddingLeft, sizeDependentWidgetsCount3.getMeasuredState());
                            }
                        }
                    }
                }
                widthSize = i3 + 1;
                paddingTop = paddingTop2;
                sizeDependentWidgetsCount = sizeDependentWidgetsCount2;
                startingHeight = startingHeight2;
                startingWidth = startingWidth3;
                i = widthMeasureSpec;
                i2 = heightMeasureSpec;
            }
            sizeDependentWidgetsCount2 = sizeDependentWidgetsCount;
            paddingTop2 = paddingTop;
            startingWidth3 = startingWidth;
            startingHeight2 = startingHeight;
            if (needSolverPass) {
                i2 = startingWidth3;
                this.mLayoutWidget.setWidth(i2);
                this.mLayoutWidget.setHeight(startingHeight2);
                if (optimiseDimensions) {
                    this.mLayoutWidget.solveGraph();
                }
                solveLinearSystem("2nd pass");
                boolean needSolverPass2 = false;
                if (this.mLayoutWidget.getWidth() < heightMode) {
                    this.mLayoutWidget.setWidth(heightMode);
                    needSolverPass2 = USE_CONSTRAINTS_HELPER;
                }
                if (this.mLayoutWidget.getHeight() < heightSize) {
                    this.mLayoutWidget.setHeight(heightSize);
                    needSolverPass2 = USE_CONSTRAINTS_HELPER;
                }
                if (needSolverPass2) {
                    solveLinearSystem("3rd pass");
                }
            } else {
                sizeDependentWidgetsCount = startingHeight2;
                i2 = startingWidth3;
            }
            int i4 = 0;
            while (true) {
                i = i4;
                widthSize = sizeDependentWidgetsCount2;
                if (i >= widthSize) {
                    break;
                }
                boolean containerWrapWidth2;
                widget = (ConstraintWidget) this.mVariableDimensionsWidgets.get(i);
                View startingWidth4 = (View) widget.getCompanionWidget();
                if (startingWidth4 == null) {
                    startingWidth2 = i2;
                } else {
                    startingWidth2 = i2;
                    if (!(startingWidth4.getMeasuredWidth() == widget.getWidth() && startingWidth4.getMeasuredHeight() == widget.getHeight())) {
                        if (widget.getVisibility() != 8) {
                            i2 = MeasureSpec.makeMeasureSpec(widget.getWidth(), 1073741824);
                            containerWrapWidth2 = containerWrapWidth;
                            containerWrapWidth = MeasureSpec.makeMeasureSpec(widget.getHeight(), 1073741824);
                            startingWidth4.measure(i2, containerWrapWidth);
                            int widthSpec2;
                            boolean heightSpec2;
                            if (this.mMetrics != null) {
                                Metrics metrics2 = this.mMetrics;
                                widthSpec2 = i2;
                                heightSpec2 = containerWrapWidth;
                                metrics2.additionalMeasures++;
                            } else {
                                widthSpec2 = i2;
                                heightSpec2 = containerWrapWidth;
                            }
                            REMEASURES_B++;
                        } else {
                            containerWrapWidth2 = containerWrapWidth;
                        }
                        i4 = i + 1;
                        sizeDependentWidgetsCount2 = widthSize;
                        i2 = startingWidth2;
                        containerWrapWidth = containerWrapWidth2;
                    }
                }
                containerWrapWidth2 = containerWrapWidth;
                i4 = i + 1;
                sizeDependentWidgetsCount2 = widthSize;
                i2 = startingWidth2;
                containerWrapWidth = containerWrapWidth2;
            }
        } else {
            int i5 = widthSize;
            int i6 = heightMode;
            int i7 = heightSize;
            int i8 = paddingLeft;
            paddingTop2 = paddingTop;
            startingWidth2 = startingWidth;
            paddingLeft = childState;
        }
        i = this.mLayoutWidget.getWidth() + widthPadding;
        i2 = this.mLayoutWidget.getHeight() + height;
        if (VERSION.SDK_INT >= 11) {
            heightMode = resolveSizeAndState(i2, heightMeasureSpec, paddingLeft << 16) & ViewCompat.MEASURED_SIZE_MASK;
            widthMode = Math.min(this.mMaxWidth, resolveSizeAndState(i, widthMeasureSpec, paddingLeft) & ViewCompat.MEASURED_SIZE_MASK);
            heightMode = Math.min(this.mMaxHeight, heightMode);
            if (this.mLayoutWidget.isWidthMeasuredTooSmall()) {
                widthMode |= 16777216;
            }
            if (this.mLayoutWidget.isHeightMeasuredTooSmall()) {
                heightMode |= 16777216;
            }
            setMeasuredDimension(widthMode, heightMode);
            this.mLastMeasureWidth = widthMode;
            this.mLastMeasureHeight = heightMode;
            return;
        }
        width = widthMeasureSpec;
        heightSize = heightMeasureSpec;
        setMeasuredDimension(i, i2);
        this.mLastMeasureWidth = i;
        this.mLastMeasureHeight = i2;
    }

    private void setSelfDimensionBehaviour(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightPadding = getPaddingTop() + getPaddingBottom();
        int widthPadding = getPaddingLeft() + getPaddingRight();
        DimensionBehaviour widthBehaviour = DimensionBehaviour.FIXED;
        DimensionBehaviour heightBehaviour = DimensionBehaviour.FIXED;
        int desiredWidth = 0;
        int desiredHeight = 0;
        android.view.ViewGroup.LayoutParams params = getLayoutParams();
        if (widthMode == Integer.MIN_VALUE) {
            widthBehaviour = DimensionBehaviour.WRAP_CONTENT;
            desiredWidth = widthSize;
        } else if (widthMode == 0) {
            widthBehaviour = DimensionBehaviour.WRAP_CONTENT;
        } else if (widthMode == 1073741824) {
            desiredWidth = Math.min(this.mMaxWidth, widthSize) - widthPadding;
        }
        if (heightMode == Integer.MIN_VALUE) {
            heightBehaviour = DimensionBehaviour.WRAP_CONTENT;
            desiredHeight = heightSize;
        } else if (heightMode == 0) {
            heightBehaviour = DimensionBehaviour.WRAP_CONTENT;
        } else if (heightMode == 1073741824) {
            desiredHeight = Math.min(this.mMaxHeight, heightSize) - heightPadding;
        }
        this.mLayoutWidget.setMinWidth(0);
        this.mLayoutWidget.setMinHeight(0);
        this.mLayoutWidget.setHorizontalDimensionBehaviour(widthBehaviour);
        this.mLayoutWidget.setWidth(desiredWidth);
        this.mLayoutWidget.setVerticalDimensionBehaviour(heightBehaviour);
        this.mLayoutWidget.setHeight(desiredHeight);
        this.mLayoutWidget.setMinWidth((this.mMinWidth - getPaddingLeft()) - getPaddingRight());
        this.mLayoutWidget.setMinHeight((this.mMinHeight - getPaddingTop()) - getPaddingBottom());
    }

    protected void solveLinearSystem(String reason) {
        this.mLayoutWidget.layout();
        if (this.mMetrics != null) {
            Metrics metrics = this.mMetrics;
            metrics.resolutions++;
        }
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int i;
        int widgetsCount = getChildCount();
        boolean isInEditMode = isInEditMode();
        int i2 = 0;
        for (i = 0; i < widgetsCount; i++) {
            View child = getChildAt(i);
            LayoutParams params = (LayoutParams) child.getLayoutParams();
            ConstraintWidget widget = params.widget;
            if ((child.getVisibility() != 8 || params.isGuideline || params.isHelper || isInEditMode) && !params.isInPlaceholder) {
                int l = widget.getDrawX();
                int t = widget.getDrawY();
                int r = widget.getWidth() + l;
                int b = widget.getHeight() + t;
                child.layout(l, t, r, b);
                if (child instanceof Placeholder) {
                    View content = ((Placeholder) child).getContent();
                    if (content != null) {
                        content.setVisibility(0);
                        content.layout(l, t, r, b);
                    }
                }
            }
        }
        i = this.mConstraintHelpers.size();
        if (i > 0) {
            while (i2 < i) {
                ((ConstraintHelper) this.mConstraintHelpers.get(i2)).updatePostLayout(this);
                i2++;
            }
        }
    }

    public void setOptimizationLevel(int level) {
        this.mLayoutWidget.setOptimizationLevel(level);
    }

    public int getOptimizationLevel() {
        return this.mLayoutWidget.getOptimizationLevel();
    }

    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams p) {
        return new LayoutParams(p);
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }

    public void setConstraintSet(ConstraintSet set) {
        this.mConstraintSet = set;
    }

    public View getViewById(int id) {
        return (View) this.mChildrenByIds.get(id);
    }

    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (isInEditMode()) {
            int count = getChildCount();
            float cw = (float) getWidth();
            float ch = (float) getHeight();
            float ow = 1080.0f;
            int i = 0;
            int i2 = 0;
            while (i2 < count) {
                int count2;
                float cw2;
                float ch2;
                float ow2;
                View child = getChildAt(i2);
                if (child.getVisibility() == 8) {
                    count2 = count;
                    cw2 = cw;
                    ch2 = ch;
                    ow2 = ow;
                } else {
                    String tag = child.getTag();
                    if (tag != null && (tag instanceof String)) {
                        String[] split = tag.split(",");
                        if (split.length == 4) {
                            int x = Integer.parseInt(split[i]);
                            int y = Integer.parseInt(split[1]);
                            i = (int) ((((float) x) / ow) * cw);
                            x = (int) ((((float) y) / 1920.0f) * ch);
                            y = (int) ((((float) Integer.parseInt(split[2])) / ow) * cw);
                            int h = (int) ((((float) Integer.parseInt(split[3])) / 1920.0f) * ch);
                            Paint paint = new Paint();
                            count2 = count;
                            paint.setColor(SupportMenu.CATEGORY_MASK);
                            cw2 = cw;
                            ch2 = ch;
                            ow2 = ow;
                            Canvas canvas2 = canvas;
                            Paint paint2 = paint;
                            canvas2.drawLine((float) i, (float) x, (float) (i + y), (float) x, paint2);
                            canvas2.drawLine((float) (i + y), (float) x, (float) (i + y), (float) (x + h), paint2);
                            canvas2.drawLine((float) (i + y), (float) (x + h), (float) i, (float) (x + h), paint2);
                            canvas2.drawLine((float) i, (float) (x + h), (float) i, (float) x, paint2);
                            paint.setColor(-16711936);
                            canvas2.drawLine((float) i, (float) x, (float) (i + y), (float) (x + h), paint2);
                            canvas2.drawLine((float) i, (float) (x + h), (float) (i + y), (float) x, paint2);
                        }
                    }
                    count2 = count;
                    cw2 = cw;
                    ch2 = ch;
                    ow2 = ow;
                }
                i2++;
                count = count2;
                cw = cw2;
                ch = ch2;
                ow = ow2;
                i = 0;
            }
        }
    }

    public void requestLayout() {
        super.requestLayout();
        this.mDirtyHierarchy = USE_CONSTRAINTS_HELPER;
        this.mLastMeasureWidth = -1;
        this.mLastMeasureHeight = -1;
        this.mLastMeasureWidthSize = -1;
        this.mLastMeasureHeightSize = -1;
        this.mLastMeasureWidthMode = 0;
        this.mLastMeasureHeightMode = 0;
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }
}
