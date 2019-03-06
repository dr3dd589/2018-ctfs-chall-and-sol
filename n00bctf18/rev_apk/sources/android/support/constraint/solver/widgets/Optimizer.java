package android.support.constraint.solver.widgets;

import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour;

public class Optimizer {
    static final int FLAG_CHAIN_DANGLING = 1;
    static final int FLAG_RECOMPUTE_BOUNDS = 2;
    static final int FLAG_USE_OPTIMIZE = 0;
    public static final int OPTIMIZATION_BARRIER = 2;
    public static final int OPTIMIZATION_CHAIN = 4;
    public static final int OPTIMIZATION_DIMENSIONS = 8;
    public static final int OPTIMIZATION_DIRECT = 1;
    public static final int OPTIMIZATION_GROUPS = 32;
    public static final int OPTIMIZATION_NONE = 0;
    public static final int OPTIMIZATION_RATIO = 16;
    public static final int OPTIMIZATION_STANDARD = 7;
    static boolean[] flags = new boolean[3];

    static void checkMatchParent(ConstraintWidgetContainer container, LinearSystem system, ConstraintWidget widget) {
        int left;
        int right;
        if (container.mListDimensionBehaviors[0] != DimensionBehaviour.WRAP_CONTENT && widget.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_PARENT) {
            left = widget.mLeft.mMargin;
            right = container.getWidth() - widget.mRight.mMargin;
            widget.mLeft.mSolverVariable = system.createObjectVariable(widget.mLeft);
            widget.mRight.mSolverVariable = system.createObjectVariable(widget.mRight);
            system.addEquality(widget.mLeft.mSolverVariable, left);
            system.addEquality(widget.mRight.mSolverVariable, right);
            widget.mHorizontalResolution = 2;
            widget.setHorizontalDimension(left, right);
        }
        if (container.mListDimensionBehaviors[1] != DimensionBehaviour.WRAP_CONTENT && widget.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_PARENT) {
            left = widget.mTop.mMargin;
            right = container.getHeight() - widget.mBottom.mMargin;
            widget.mTop.mSolverVariable = system.createObjectVariable(widget.mTop);
            widget.mBottom.mSolverVariable = system.createObjectVariable(widget.mBottom);
            system.addEquality(widget.mTop.mSolverVariable, left);
            system.addEquality(widget.mBottom.mSolverVariable, right);
            if (widget.mBaselineDistance > 0 || widget.getVisibility() == 8) {
                widget.mBaseline.mSolverVariable = system.createObjectVariable(widget.mBaseline);
                system.addEquality(widget.mBaseline.mSolverVariable, widget.mBaselineDistance + left);
            }
            widget.mVerticalResolution = 2;
            widget.setVerticalDimension(left, right);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x003e A:{RETURN} */
    private static boolean optimizableMatchConstraint(android.support.constraint.solver.widgets.ConstraintWidget r4, int r5) {
        /*
        r0 = r4.mListDimensionBehaviors;
        r0 = r0[r5];
        r1 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        r2 = 0;
        if (r0 == r1) goto L_0x000a;
    L_0x0009:
        return r2;
    L_0x000a:
        r0 = r4.mDimensionRatio;
        r1 = 0;
        r3 = 1;
        r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1));
        if (r0 == 0) goto L_0x0020;
    L_0x0012:
        r0 = r4.mListDimensionBehaviors;
        if (r5 != 0) goto L_0x0017;
    L_0x0016:
        goto L_0x0018;
    L_0x0017:
        r3 = 0;
    L_0x0018:
        r0 = r0[r3];
        r1 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (r0 != r1) goto L_0x001f;
    L_0x001e:
        return r2;
    L_0x001f:
        return r2;
    L_0x0020:
        if (r5 != 0) goto L_0x0030;
    L_0x0022:
        r0 = r4.mMatchConstraintDefaultWidth;
        if (r0 == 0) goto L_0x0027;
    L_0x0026:
        return r2;
    L_0x0027:
        r0 = r4.mMatchConstraintMinWidth;
        if (r0 != 0) goto L_0x002f;
    L_0x002b:
        r0 = r4.mMatchConstraintMaxWidth;
        if (r0 == 0) goto L_0x003e;
    L_0x002f:
        return r2;
    L_0x0030:
        r0 = r4.mMatchConstraintDefaultHeight;
        if (r0 == 0) goto L_0x0035;
    L_0x0034:
        return r2;
    L_0x0035:
        r0 = r4.mMatchConstraintMinHeight;
        if (r0 != 0) goto L_0x003f;
    L_0x0039:
        r0 = r4.mMatchConstraintMaxHeight;
        if (r0 == 0) goto L_0x003e;
    L_0x003d:
        goto L_0x003f;
    L_0x003e:
        return r3;
    L_0x003f:
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.Optimizer.optimizableMatchConstraint(android.support.constraint.solver.widgets.ConstraintWidget, int):boolean");
    }

    static void analyze(int optimisationLevel, ConstraintWidget widget) {
        ConstraintWidget constraintWidget = widget;
        widget.updateResolutionNodes();
        ResolutionAnchor leftNode = constraintWidget.mLeft.getResolutionNode();
        ResolutionAnchor topNode = constraintWidget.mTop.getResolutionNode();
        ResolutionAnchor rightNode = constraintWidget.mRight.getResolutionNode();
        ResolutionAnchor bottomNode = constraintWidget.mBottom.getResolutionNode();
        boolean optimiseDimensions = (optimisationLevel & 8) == 8;
        boolean isOptimizableHorizontalMatch = constraintWidget.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT && optimizableMatchConstraint(constraintWidget, 0);
        if (!(leftNode.type == 4 || rightNode.type == 4)) {
            if (constraintWidget.mListDimensionBehaviors[0] == DimensionBehaviour.FIXED || (isOptimizableHorizontalMatch && widget.getVisibility() == 8)) {
                if (constraintWidget.mLeft.mTarget == null && constraintWidget.mRight.mTarget == null) {
                    leftNode.setType(1);
                    rightNode.setType(1);
                    if (optimiseDimensions) {
                        rightNode.dependsOn(leftNode, 1, widget.getResolutionWidth());
                    } else {
                        rightNode.dependsOn(leftNode, widget.getWidth());
                    }
                } else if (constraintWidget.mLeft.mTarget != null && constraintWidget.mRight.mTarget == null) {
                    leftNode.setType(1);
                    rightNode.setType(1);
                    if (optimiseDimensions) {
                        rightNode.dependsOn(leftNode, 1, widget.getResolutionWidth());
                    } else {
                        rightNode.dependsOn(leftNode, widget.getWidth());
                    }
                } else if (constraintWidget.mLeft.mTarget == null && constraintWidget.mRight.mTarget != null) {
                    leftNode.setType(1);
                    rightNode.setType(1);
                    leftNode.dependsOn(rightNode, -widget.getWidth());
                    if (optimiseDimensions) {
                        leftNode.dependsOn(rightNode, -1, widget.getResolutionWidth());
                    } else {
                        leftNode.dependsOn(rightNode, -widget.getWidth());
                    }
                } else if (!(constraintWidget.mLeft.mTarget == null || constraintWidget.mRight.mTarget == null)) {
                    leftNode.setType(2);
                    rightNode.setType(2);
                    if (optimiseDimensions) {
                        widget.getResolutionWidth().addDependent(leftNode);
                        widget.getResolutionWidth().addDependent(rightNode);
                        leftNode.setOpposite(rightNode, -1, widget.getResolutionWidth());
                        rightNode.setOpposite(leftNode, 1, widget.getResolutionWidth());
                    } else {
                        leftNode.setOpposite(rightNode, (float) (-widget.getWidth()));
                        rightNode.setOpposite(leftNode, (float) widget.getWidth());
                    }
                }
            } else if (isOptimizableHorizontalMatch) {
                int width = widget.getWidth();
                leftNode.setType(1);
                rightNode.setType(1);
                if (constraintWidget.mLeft.mTarget == null && constraintWidget.mRight.mTarget == null) {
                    if (optimiseDimensions) {
                        rightNode.dependsOn(leftNode, 1, widget.getResolutionWidth());
                    } else {
                        rightNode.dependsOn(leftNode, width);
                    }
                } else if (constraintWidget.mLeft.mTarget == null || constraintWidget.mRight.mTarget != null) {
                    if (constraintWidget.mLeft.mTarget != null || constraintWidget.mRight.mTarget == null) {
                        if (!(constraintWidget.mLeft.mTarget == null || constraintWidget.mRight.mTarget == null)) {
                            if (optimiseDimensions) {
                                widget.getResolutionWidth().addDependent(leftNode);
                                widget.getResolutionWidth().addDependent(rightNode);
                            }
                            if (constraintWidget.mDimensionRatio == 0.0f) {
                                leftNode.setType(3);
                                rightNode.setType(3);
                                leftNode.setOpposite(rightNode, 0.0f);
                                rightNode.setOpposite(leftNode, 0.0f);
                            } else {
                                leftNode.setType(2);
                                rightNode.setType(2);
                                leftNode.setOpposite(rightNode, (float) (-width));
                                rightNode.setOpposite(leftNode, (float) width);
                                constraintWidget.setWidth(width);
                            }
                        }
                    } else if (optimiseDimensions) {
                        leftNode.dependsOn(rightNode, -1, widget.getResolutionWidth());
                    } else {
                        leftNode.dependsOn(rightNode, -width);
                    }
                } else if (optimiseDimensions) {
                    rightNode.dependsOn(leftNode, 1, widget.getResolutionWidth());
                } else {
                    rightNode.dependsOn(leftNode, width);
                }
            }
        }
        boolean z = constraintWidget.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT && optimizableMatchConstraint(constraintWidget, 1);
        boolean isOptimizableVerticalMatch = z;
        if (topNode.type != 4 && bottomNode.type != 4) {
            if (constraintWidget.mListDimensionBehaviors[1] == DimensionBehaviour.FIXED || (isOptimizableVerticalMatch && widget.getVisibility() == 8)) {
                if (constraintWidget.mTop.mTarget == null && constraintWidget.mBottom.mTarget == null) {
                    topNode.setType(1);
                    bottomNode.setType(1);
                    if (optimiseDimensions) {
                        bottomNode.dependsOn(topNode, 1, widget.getResolutionHeight());
                    } else {
                        bottomNode.dependsOn(topNode, widget.getHeight());
                    }
                    if (constraintWidget.mBaseline.mTarget != null) {
                        constraintWidget.mBaseline.getResolutionNode().setType(1);
                        topNode.dependsOn(1, constraintWidget.mBaseline.getResolutionNode(), -constraintWidget.mBaselineDistance);
                    }
                } else if (constraintWidget.mTop.mTarget != null && constraintWidget.mBottom.mTarget == null) {
                    topNode.setType(1);
                    bottomNode.setType(1);
                    if (optimiseDimensions) {
                        bottomNode.dependsOn(topNode, 1, widget.getResolutionHeight());
                    } else {
                        bottomNode.dependsOn(topNode, widget.getHeight());
                    }
                    if (constraintWidget.mBaselineDistance > 0) {
                        constraintWidget.mBaseline.getResolutionNode().dependsOn(1, topNode, constraintWidget.mBaselineDistance);
                    }
                } else if (constraintWidget.mTop.mTarget == null && constraintWidget.mBottom.mTarget != null) {
                    topNode.setType(1);
                    bottomNode.setType(1);
                    if (optimiseDimensions) {
                        topNode.dependsOn(bottomNode, -1, widget.getResolutionHeight());
                    } else {
                        topNode.dependsOn(bottomNode, -widget.getHeight());
                    }
                    if (constraintWidget.mBaselineDistance > 0) {
                        constraintWidget.mBaseline.getResolutionNode().dependsOn(1, topNode, constraintWidget.mBaselineDistance);
                    }
                } else if (constraintWidget.mTop.mTarget != null && constraintWidget.mBottom.mTarget != null) {
                    topNode.setType(2);
                    bottomNode.setType(2);
                    if (optimiseDimensions) {
                        topNode.setOpposite(bottomNode, -1, widget.getResolutionHeight());
                        bottomNode.setOpposite(topNode, 1, widget.getResolutionHeight());
                        widget.getResolutionHeight().addDependent(topNode);
                        widget.getResolutionWidth().addDependent(bottomNode);
                    } else {
                        topNode.setOpposite(bottomNode, (float) (-widget.getHeight()));
                        bottomNode.setOpposite(topNode, (float) widget.getHeight());
                    }
                    if (constraintWidget.mBaselineDistance > 0) {
                        constraintWidget.mBaseline.getResolutionNode().dependsOn(1, topNode, constraintWidget.mBaselineDistance);
                    }
                }
            } else if (isOptimizableVerticalMatch) {
                int height = widget.getHeight();
                topNode.setType(1);
                bottomNode.setType(1);
                if (constraintWidget.mTop.mTarget == null && constraintWidget.mBottom.mTarget == null) {
                    if (optimiseDimensions) {
                        bottomNode.dependsOn(topNode, 1, widget.getResolutionHeight());
                    } else {
                        bottomNode.dependsOn(topNode, height);
                    }
                } else if (constraintWidget.mTop.mTarget == null || constraintWidget.mBottom.mTarget != null) {
                    if (constraintWidget.mTop.mTarget != null || constraintWidget.mBottom.mTarget == null) {
                        if (constraintWidget.mTop.mTarget != null && constraintWidget.mBottom.mTarget != null) {
                            if (optimiseDimensions) {
                                widget.getResolutionHeight().addDependent(topNode);
                                widget.getResolutionWidth().addDependent(bottomNode);
                            }
                            if (constraintWidget.mDimensionRatio == 0.0f) {
                                topNode.setType(3);
                                bottomNode.setType(3);
                                topNode.setOpposite(bottomNode, 0.0f);
                                bottomNode.setOpposite(topNode, 0.0f);
                                return;
                            }
                            topNode.setType(2);
                            bottomNode.setType(2);
                            topNode.setOpposite(bottomNode, (float) (-height));
                            bottomNode.setOpposite(topNode, (float) height);
                            constraintWidget.setHeight(height);
                            if (constraintWidget.mBaselineDistance > 0) {
                                constraintWidget.mBaseline.getResolutionNode().dependsOn(1, topNode, constraintWidget.mBaselineDistance);
                            }
                        }
                    } else if (optimiseDimensions) {
                        topNode.dependsOn(bottomNode, -1, widget.getResolutionHeight());
                    } else {
                        topNode.dependsOn(bottomNode, -height);
                    }
                } else if (optimiseDimensions) {
                    bottomNode.dependsOn(topNode, 1, widget.getResolutionHeight());
                } else {
                    bottomNode.dependsOn(topNode, height);
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:82:0x0139  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0133  */
    static boolean applyChainOptimized(android.support.constraint.solver.widgets.ConstraintWidgetContainer r39, android.support.constraint.solver.LinearSystem r40, int r41, int r42, android.support.constraint.solver.widgets.ChainHead r43) {
        /*
        r0 = r40;
        r1 = r41;
        r2 = r43;
        r3 = r2.mFirst;
        r4 = r2.mLast;
        r5 = r2.mFirstVisibleWidget;
        r6 = r2.mLastVisibleWidget;
        r7 = r2.mHead;
        r8 = r3;
        r9 = 0;
        r10 = 0;
        r11 = 0;
        r12 = r2.mTotalWeight;
        r13 = r2.mFirstMatchConstraintWidget;
        r14 = r2.mLastMatchConstraintWidget;
        r2 = r39;
        r15 = r8;
        r8 = r2.mListDimensionBehaviors;
        r8 = r8[r1];
        r2 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        r16 = 0;
        r17 = r9;
        if (r8 != r2) goto L_0x002b;
    L_0x0029:
        r2 = 1;
        goto L_0x002c;
    L_0x002b:
        r2 = 0;
    L_0x002c:
        r8 = 0;
        r18 = 0;
        r19 = 0;
        if (r1 != 0) goto L_0x0050;
    L_0x0033:
        r9 = r7.mHorizontalChainStyle;
        if (r9 != 0) goto L_0x0039;
    L_0x0037:
        r9 = 1;
        goto L_0x003a;
    L_0x0039:
        r9 = 0;
    L_0x003a:
        r8 = r9;
        r9 = r7.mHorizontalChainStyle;
        r21 = r2;
        r2 = 1;
        if (r9 != r2) goto L_0x0044;
    L_0x0042:
        r2 = 1;
        goto L_0x0045;
    L_0x0044:
        r2 = 0;
    L_0x0045:
        r9 = r7.mHorizontalChainStyle;
        r22 = r2;
        r2 = 2;
        if (r9 != r2) goto L_0x004e;
    L_0x004c:
        r2 = 1;
        goto L_0x004f;
    L_0x004e:
        r2 = 0;
    L_0x004f:
        goto L_0x006e;
    L_0x0050:
        r21 = r2;
        r2 = r7.mVerticalChainStyle;
        if (r2 != 0) goto L_0x0058;
    L_0x0056:
        r2 = 1;
        goto L_0x0059;
    L_0x0058:
        r2 = 0;
    L_0x0059:
        r8 = r2;
        r2 = r7.mVerticalChainStyle;
        r9 = 1;
        if (r2 != r9) goto L_0x0061;
    L_0x005f:
        r2 = 1;
        goto L_0x0062;
    L_0x0061:
        r2 = 0;
    L_0x0062:
        r9 = r7.mVerticalChainStyle;
        r23 = r2;
        r2 = 2;
        if (r9 != r2) goto L_0x006b;
    L_0x0069:
        r2 = 1;
        goto L_0x006c;
    L_0x006b:
        r2 = 0;
    L_0x006c:
        r22 = r23;
    L_0x006e:
        r9 = 0;
        r18 = 0;
        r24 = r7;
        r7 = r11;
        r11 = r15;
        r15 = r9;
        r9 = 0;
    L_0x0077:
        r19 = 0;
        r25 = r13;
        r13 = 8;
        if (r10 != 0) goto L_0x0143;
    L_0x007f:
        r26 = r10;
        r10 = r11.getVisibility();
        if (r10 == r13) goto L_0x00ca;
    L_0x0087:
        r9 = r9 + 1;
        if (r1 != 0) goto L_0x0092;
    L_0x008b:
        r10 = r11.getWidth();
        r10 = (float) r10;
        r15 = r15 + r10;
        goto L_0x0098;
    L_0x0092:
        r10 = r11.getHeight();
        r10 = (float) r10;
        r15 = r15 + r10;
    L_0x0098:
        if (r11 == r5) goto L_0x00a4;
    L_0x009a:
        r10 = r11.mListAnchors;
        r10 = r10[r42];
        r10 = r10.getMargin();
        r10 = (float) r10;
        r15 = r15 + r10;
    L_0x00a4:
        if (r11 == r6) goto L_0x00b2;
    L_0x00a6:
        r10 = r11.mListAnchors;
        r20 = r42 + 1;
        r10 = r10[r20];
        r10 = r10.getMargin();
        r10 = (float) r10;
        r15 = r15 + r10;
    L_0x00b2:
        r10 = r11.mListAnchors;
        r10 = r10[r42];
        r10 = r10.getMargin();
        r10 = (float) r10;
        r18 = r18 + r10;
        r10 = r11.mListAnchors;
        r20 = r42 + 1;
        r10 = r10[r20];
        r10 = r10.getMargin();
        r10 = (float) r10;
        r18 = r18 + r10;
    L_0x00ca:
        r10 = r11.mListAnchors;
        r10 = r10[r42];
        r27 = r9;
        r9 = r11.getVisibility();
        if (r9 == r13) goto L_0x0106;
    L_0x00d6:
        r9 = r11.mListDimensionBehaviors;
        r9 = r9[r1];
        r13 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (r9 != r13) goto L_0x0106;
    L_0x00de:
        r7 = r7 + 1;
        if (r1 != 0) goto L_0x00f0;
    L_0x00e2:
        r9 = r11.mMatchConstraintDefaultWidth;
        if (r9 == 0) goto L_0x00e7;
    L_0x00e6:
        return r16;
    L_0x00e7:
        r9 = r11.mMatchConstraintMinWidth;
        if (r9 != 0) goto L_0x00ef;
    L_0x00eb:
        r9 = r11.mMatchConstraintMaxWidth;
        if (r9 == 0) goto L_0x00fe;
    L_0x00ef:
        return r16;
    L_0x00f0:
        r9 = r11.mMatchConstraintDefaultHeight;
        if (r9 == 0) goto L_0x00f5;
    L_0x00f4:
        return r16;
    L_0x00f5:
        r9 = r11.mMatchConstraintMinHeight;
        if (r9 != 0) goto L_0x0105;
    L_0x00f9:
        r9 = r11.mMatchConstraintMaxHeight;
        if (r9 == 0) goto L_0x00fe;
    L_0x00fd:
        goto L_0x0105;
    L_0x00fe:
        r9 = r11.mDimensionRatio;
        r9 = (r9 > r19 ? 1 : (r9 == r19 ? 0 : -1));
        if (r9 == 0) goto L_0x0106;
    L_0x0104:
        return r16;
    L_0x0105:
        return r16;
    L_0x0106:
        r9 = r11.mListAnchors;
        r13 = r42 + 1;
        r9 = r9[r13];
        r9 = r9.mTarget;
        if (r9 == 0) goto L_0x012c;
    L_0x0110:
        r13 = r9.mOwner;
        r28 = r7;
        r7 = r13.mListAnchors;
        r7 = r7[r42];
        r7 = r7.mTarget;
        if (r7 == 0) goto L_0x012a;
    L_0x011c:
        r7 = r13.mListAnchors;
        r7 = r7[r42];
        r7 = r7.mTarget;
        r7 = r7.mOwner;
        if (r7 == r11) goto L_0x0127;
    L_0x0126:
        goto L_0x012a;
    L_0x0127:
        r17 = r13;
        goto L_0x0131;
    L_0x012a:
        r7 = 0;
        goto L_0x012f;
    L_0x012c:
        r28 = r7;
        r7 = 0;
    L_0x012f:
        r17 = r7;
    L_0x0131:
        if (r17 == 0) goto L_0x0139;
    L_0x0133:
        r7 = r17;
        r11 = r7;
        r10 = r26;
        goto L_0x013b;
    L_0x0139:
        r7 = 1;
        r10 = r7;
    L_0x013b:
        r13 = r25;
        r9 = r27;
        r7 = r28;
        goto L_0x0077;
    L_0x0143:
        r26 = r10;
        r10 = r3.mListAnchors;
        r10 = r10[r42];
        r10 = r10.getResolutionNode();
        r13 = r4.mListAnchors;
        r20 = r42 + 1;
        r13 = r13[r20];
        r13 = r13.getResolutionNode();
        r29 = r14;
        r14 = r10.target;
        if (r14 == 0) goto L_0x0480;
    L_0x015d:
        r14 = r13.target;
        if (r14 != 0) goto L_0x0170;
    L_0x0161:
        r30 = r2;
        r32 = r6;
        r37 = r7;
        r33 = r8;
        r38 = r9;
        r35 = r13;
        r13 = r0;
        goto L_0x048d;
    L_0x0170:
        r14 = r10.target;
        r14 = r14.state;
        r0 = 1;
        if (r14 != r0) goto L_0x0471;
    L_0x0177:
        r14 = r13.target;
        r14 = r14.state;
        if (r14 == r0) goto L_0x018d;
    L_0x017d:
        r30 = r2;
        r32 = r6;
        r37 = r7;
        r33 = r8;
        r38 = r9;
        r35 = r13;
        r13 = r40;
        goto L_0x047f;
    L_0x018d:
        if (r7 <= 0) goto L_0x0192;
    L_0x018f:
        if (r7 == r9) goto L_0x0192;
    L_0x0191:
        return r16;
    L_0x0192:
        r0 = 0;
        if (r2 != 0) goto L_0x0199;
    L_0x0195:
        if (r8 != 0) goto L_0x0199;
    L_0x0197:
        if (r22 == 0) goto L_0x01b2;
    L_0x0199:
        if (r5 == 0) goto L_0x01a4;
    L_0x019b:
        r14 = r5.mListAnchors;
        r14 = r14[r42];
        r14 = r14.getMargin();
        r0 = (float) r14;
    L_0x01a4:
        if (r6 == 0) goto L_0x01b2;
    L_0x01a6:
        r14 = r6.mListAnchors;
        r20 = r42 + 1;
        r14 = r14[r20];
        r14 = r14.getMargin();
        r14 = (float) r14;
        r0 = r0 + r14;
    L_0x01b2:
        r14 = r10.target;
        r14 = r14.resolvedOffset;
        r30 = r2;
        r2 = r13.target;
        r2 = r2.resolvedOffset;
        r20 = 0;
        r23 = (r14 > r2 ? 1 : (r14 == r2 ? 0 : -1));
        if (r23 >= 0) goto L_0x01c7;
    L_0x01c2:
        r23 = r2 - r14;
        r23 = r23 - r15;
        goto L_0x01cb;
    L_0x01c7:
        r23 = r14 - r2;
        r23 = r23 - r15;
    L_0x01cb:
        r27 = 1;
        if (r7 <= 0) goto L_0x02b9;
    L_0x01cf:
        if (r7 != r9) goto L_0x02b9;
    L_0x01d1:
        r20 = r11.getParent();
        if (r20 == 0) goto L_0x01e8;
    L_0x01d7:
        r31 = r2;
        r2 = r11.getParent();
        r2 = r2.mListDimensionBehaviors;
        r2 = r2[r1];
        r32 = r6;
        r6 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (r2 != r6) goto L_0x01ec;
    L_0x01e7:
        return r16;
    L_0x01e8:
        r31 = r2;
        r32 = r6;
    L_0x01ec:
        r23 = r23 + r15;
        r23 = r23 - r18;
        r2 = r3;
        r6 = r2;
        r2 = r14;
    L_0x01f3:
        if (r6 == 0) goto L_0x02ad;
    L_0x01f5:
        r11 = android.support.constraint.solver.LinearSystem.sMetrics;
        if (r11 == 0) goto L_0x021a;
    L_0x01f9:
        r11 = android.support.constraint.solver.LinearSystem.sMetrics;
        r33 = r8;
        r34 = r9;
        r8 = r11.nonresolvedWidgets;
        r8 = r8 - r27;
        r11.nonresolvedWidgets = r8;
        r8 = android.support.constraint.solver.LinearSystem.sMetrics;
        r35 = r13;
        r36 = r14;
        r13 = r8.resolvedWidgets;
        r13 = r13 + r27;
        r8.resolvedWidgets = r13;
        r8 = android.support.constraint.solver.LinearSystem.sMetrics;
        r13 = r8.chainConnectionResolved;
        r13 = r13 + r27;
        r8.chainConnectionResolved = r13;
        goto L_0x0222;
    L_0x021a:
        r33 = r8;
        r34 = r9;
        r35 = r13;
        r36 = r14;
    L_0x0222:
        r8 = r6.mNextChainWidget;
        r17 = r8[r1];
        if (r17 != 0) goto L_0x022e;
    L_0x0228:
        if (r6 != r4) goto L_0x022b;
    L_0x022a:
        goto L_0x022e;
    L_0x022b:
        r13 = r40;
        goto L_0x02a1;
    L_0x022e:
        r8 = (float) r7;
        r8 = r23 / r8;
        r9 = (r12 > r19 ? 1 : (r12 == r19 ? 0 : -1));
        if (r9 <= 0) goto L_0x0249;
    L_0x0235:
        r9 = r6.mWeight;
        r9 = r9[r1];
        r11 = -1082130432; // 0xffffffffbf800000 float:-1.0 double:NaN;
        r9 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1));
        if (r9 != 0) goto L_0x0241;
    L_0x023f:
        r8 = 0;
        goto L_0x0249;
    L_0x0241:
        r9 = r6.mWeight;
        r9 = r9[r1];
        r9 = r9 * r23;
        r8 = r9 / r12;
    L_0x0249:
        r9 = r6.getVisibility();
        r11 = 8;
        if (r9 != r11) goto L_0x0252;
    L_0x0251:
        r8 = 0;
    L_0x0252:
        r9 = r6.mListAnchors;
        r9 = r9[r42];
        r9 = r9.getMargin();
        r9 = (float) r9;
        r2 = r2 + r9;
        r9 = r6.mListAnchors;
        r9 = r9[r42];
        r9 = r9.getResolutionNode();
        r11 = r10.resolvedTarget;
        r9.resolve(r11, r2);
        r9 = r6.mListAnchors;
        r11 = r42 + 1;
        r9 = r9[r11];
        r9 = r9.getResolutionNode();
        r11 = r10.resolvedTarget;
        r13 = r2 + r8;
        r9.resolve(r11, r13);
        r9 = r6.mListAnchors;
        r9 = r9[r42];
        r9 = r9.getResolutionNode();
        r13 = r40;
        r9.addResolvedValue(r13);
        r9 = r6.mListAnchors;
        r11 = r42 + 1;
        r9 = r9[r11];
        r9 = r9.getResolutionNode();
        r9.addResolvedValue(r13);
        r2 = r2 + r8;
        r9 = r6.mListAnchors;
        r11 = r42 + 1;
        r9 = r9[r11];
        r9 = r9.getMargin();
        r9 = (float) r9;
        r2 = r2 + r9;
    L_0x02a1:
        r6 = r17;
        r8 = r33;
        r9 = r34;
        r13 = r35;
        r14 = r36;
        goto L_0x01f3;
    L_0x02ad:
        r33 = r8;
        r34 = r9;
        r35 = r13;
        r36 = r14;
        r13 = r40;
        r8 = 1;
        return r8;
    L_0x02b9:
        r31 = r2;
        r32 = r6;
        r33 = r8;
        r34 = r9;
        r35 = r13;
        r36 = r14;
        r13 = r40;
        r2 = (r23 > r19 ? 1 : (r23 == r19 ? 0 : -1));
        if (r2 >= 0) goto L_0x02d3;
    L_0x02cb:
        r8 = 0;
        r22 = 0;
        r2 = 1;
        r30 = r2;
        r33 = r8;
    L_0x02d3:
        if (r30 == 0) goto L_0x0370;
    L_0x02d5:
        r23 = r23 - r0;
        r2 = r3;
        r6 = r3.getBiasPercent(r1);
        r6 = r6 * r23;
        r14 = r36 + r6;
        r11 = r2;
        r23 = r14;
    L_0x02e3:
        if (r11 == 0) goto L_0x036a;
    L_0x02e5:
        r2 = android.support.constraint.solver.LinearSystem.sMetrics;
        if (r2 == 0) goto L_0x0301;
    L_0x02e9:
        r2 = android.support.constraint.solver.LinearSystem.sMetrics;
        r8 = r2.nonresolvedWidgets;
        r8 = r8 - r27;
        r2.nonresolvedWidgets = r8;
        r2 = android.support.constraint.solver.LinearSystem.sMetrics;
        r8 = r2.resolvedWidgets;
        r8 = r8 + r27;
        r2.resolvedWidgets = r8;
        r2 = android.support.constraint.solver.LinearSystem.sMetrics;
        r8 = r2.chainConnectionResolved;
        r8 = r8 + r27;
        r2.chainConnectionResolved = r8;
    L_0x0301:
        r2 = r11.mNextChainWidget;
        r17 = r2[r1];
        if (r17 != 0) goto L_0x0309;
    L_0x0307:
        if (r11 != r4) goto L_0x0366;
    L_0x0309:
        r2 = 0;
        if (r1 != 0) goto L_0x0312;
    L_0x030c:
        r6 = r11.getWidth();
        r2 = (float) r6;
        goto L_0x0317;
    L_0x0312:
        r6 = r11.getHeight();
        r2 = (float) r6;
    L_0x0317:
        r6 = r11.mListAnchors;
        r6 = r6[r42];
        r6 = r6.getMargin();
        r6 = (float) r6;
        r6 = r23 + r6;
        r8 = r11.mListAnchors;
        r8 = r8[r42];
        r8 = r8.getResolutionNode();
        r9 = r10.resolvedTarget;
        r8.resolve(r9, r6);
        r8 = r11.mListAnchors;
        r9 = r42 + 1;
        r8 = r8[r9];
        r8 = r8.getResolutionNode();
        r9 = r10.resolvedTarget;
        r14 = r6 + r2;
        r8.resolve(r9, r14);
        r8 = r11.mListAnchors;
        r8 = r8[r42];
        r8 = r8.getResolutionNode();
        r8.addResolvedValue(r13);
        r8 = r11.mListAnchors;
        r9 = r42 + 1;
        r8 = r8[r9];
        r8 = r8.getResolutionNode();
        r8.addResolvedValue(r13);
        r6 = r6 + r2;
        r8 = r11.mListAnchors;
        r9 = r42 + 1;
        r8 = r8[r9];
        r8 = r8.getMargin();
        r8 = (float) r8;
        r23 = r6 + r8;
    L_0x0366:
        r11 = r17;
        goto L_0x02e3;
    L_0x036a:
        r37 = r7;
        r38 = r34;
        goto L_0x046f;
    L_0x0370:
        if (r33 != 0) goto L_0x0374;
    L_0x0372:
        if (r22 == 0) goto L_0x036a;
    L_0x0374:
        if (r33 == 0) goto L_0x0379;
    L_0x0376:
        r23 = r23 - r0;
        goto L_0x037d;
    L_0x0379:
        if (r22 == 0) goto L_0x037d;
    L_0x037b:
        r23 = r23 - r0;
    L_0x037d:
        r2 = r3;
        r9 = r34 + 1;
        r6 = (float) r9;
        r6 = r23 / r6;
        if (r22 == 0) goto L_0x0395;
    L_0x0385:
        r8 = r34;
        r9 = 1;
        if (r8 <= r9) goto L_0x0390;
    L_0x038a:
        r9 = r8 + -1;
        r9 = (float) r9;
        r6 = r23 / r9;
        goto L_0x0397;
    L_0x0390:
        r9 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r6 = r23 / r9;
        goto L_0x0397;
    L_0x0395:
        r8 = r34;
    L_0x0397:
        r9 = r36;
        r11 = r3.getVisibility();
        r14 = 8;
        if (r11 == r14) goto L_0x03a2;
    L_0x03a1:
        r9 = r9 + r6;
    L_0x03a2:
        if (r22 == 0) goto L_0x03b2;
    L_0x03a4:
        r11 = 1;
        if (r8 <= r11) goto L_0x03b2;
    L_0x03a7:
        r11 = r5.mListAnchors;
        r11 = r11[r42];
        r11 = r11.getMargin();
        r11 = (float) r11;
        r9 = r36 + r11;
    L_0x03b2:
        if (r33 == 0) goto L_0x03c0;
    L_0x03b4:
        if (r5 == 0) goto L_0x03c0;
    L_0x03b6:
        r11 = r5.mListAnchors;
        r11 = r11[r42];
        r11 = r11.getMargin();
        r11 = (float) r11;
        r9 = r9 + r11;
    L_0x03c0:
        r11 = r2;
        r23 = r9;
    L_0x03c3:
        if (r11 == 0) goto L_0x046b;
    L_0x03c5:
        r2 = android.support.constraint.solver.LinearSystem.sMetrics;
        if (r2 == 0) goto L_0x03e6;
    L_0x03c9:
        r2 = android.support.constraint.solver.LinearSystem.sMetrics;
        r37 = r7;
        r38 = r8;
        r7 = r2.nonresolvedWidgets;
        r7 = r7 - r27;
        r2.nonresolvedWidgets = r7;
        r2 = android.support.constraint.solver.LinearSystem.sMetrics;
        r7 = r2.resolvedWidgets;
        r7 = r7 + r27;
        r2.resolvedWidgets = r7;
        r2 = android.support.constraint.solver.LinearSystem.sMetrics;
        r7 = r2.chainConnectionResolved;
        r7 = r7 + r27;
        r2.chainConnectionResolved = r7;
        goto L_0x03ea;
    L_0x03e6:
        r37 = r7;
        r38 = r8;
    L_0x03ea:
        r2 = r11.mNextChainWidget;
        r17 = r2[r1];
        if (r17 != 0) goto L_0x03f6;
    L_0x03f0:
        if (r11 != r4) goto L_0x03f3;
    L_0x03f2:
        goto L_0x03f6;
    L_0x03f3:
        r8 = 8;
        goto L_0x0463;
    L_0x03f6:
        r2 = 0;
        if (r1 != 0) goto L_0x03ff;
    L_0x03f9:
        r7 = r11.getWidth();
        r2 = (float) r7;
        goto L_0x0404;
    L_0x03ff:
        r7 = r11.getHeight();
        r2 = (float) r7;
    L_0x0404:
        if (r11 == r5) goto L_0x0411;
    L_0x0406:
        r7 = r11.mListAnchors;
        r7 = r7[r42];
        r7 = r7.getMargin();
        r7 = (float) r7;
        r23 = r23 + r7;
    L_0x0411:
        r7 = r23;
        r8 = r11.mListAnchors;
        r8 = r8[r42];
        r8 = r8.getResolutionNode();
        r9 = r10.resolvedTarget;
        r8.resolve(r9, r7);
        r8 = r11.mListAnchors;
        r9 = r42 + 1;
        r8 = r8[r9];
        r8 = r8.getResolutionNode();
        r9 = r10.resolvedTarget;
        r14 = r7 + r2;
        r8.resolve(r9, r14);
        r8 = r11.mListAnchors;
        r8 = r8[r42];
        r8 = r8.getResolutionNode();
        r8.addResolvedValue(r13);
        r8 = r11.mListAnchors;
        r9 = r42 + 1;
        r8 = r8[r9];
        r8 = r8.getResolutionNode();
        r8.addResolvedValue(r13);
        r8 = r11.mListAnchors;
        r9 = r42 + 1;
        r8 = r8[r9];
        r8 = r8.getMargin();
        r8 = (float) r8;
        r8 = r8 + r2;
        r23 = r7 + r8;
        if (r17 == 0) goto L_0x03f3;
    L_0x0459:
        r7 = r17.getVisibility();
        r8 = 8;
        if (r7 == r8) goto L_0x0463;
    L_0x0461:
        r23 = r23 + r6;
    L_0x0463:
        r11 = r17;
        r7 = r37;
        r8 = r38;
        goto L_0x03c3;
    L_0x046b:
        r37 = r7;
        r38 = r8;
    L_0x046f:
        r2 = 1;
        return r2;
    L_0x0471:
        r30 = r2;
        r32 = r6;
        r37 = r7;
        r33 = r8;
        r38 = r9;
        r35 = r13;
        r13 = r40;
    L_0x047f:
        return r16;
    L_0x0480:
        r30 = r2;
        r32 = r6;
        r37 = r7;
        r33 = r8;
        r38 = r9;
        r35 = r13;
        r13 = r0;
    L_0x048d:
        return r16;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.Optimizer.applyChainOptimized(android.support.constraint.solver.widgets.ConstraintWidgetContainer, android.support.constraint.solver.LinearSystem, int, int, android.support.constraint.solver.widgets.ChainHead):boolean");
    }

    static void setOptimizedWidget(ConstraintWidget widget, int orientation, int resolvedOffset) {
        int startOffset = orientation * 2;
        int endOffset = startOffset + 1;
        widget.mListAnchors[startOffset].getResolutionNode().resolvedTarget = widget.getParent().mLeft.getResolutionNode();
        widget.mListAnchors[startOffset].getResolutionNode().resolvedOffset = (float) resolvedOffset;
        widget.mListAnchors[startOffset].getResolutionNode().state = 1;
        widget.mListAnchors[endOffset].getResolutionNode().resolvedTarget = widget.mListAnchors[startOffset].getResolutionNode();
        widget.mListAnchors[endOffset].getResolutionNode().resolvedOffset = (float) widget.getLength(orientation);
        widget.mListAnchors[endOffset].getResolutionNode().state = 1;
    }
}
