package android.support.constraint.solver.widgets;

import android.support.constraint.solver.LinearSystem;

class Chain {
    private static final boolean DEBUG = false;

    Chain() {
    }

    static void applyChainConstraints(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem system, int orientation) {
        int offset;
        int chainsSize;
        ChainHead[] chainsArray;
        if (orientation == 0) {
            offset = 0;
            chainsSize = constraintWidgetContainer.mHorizontalChainsSize;
            chainsArray = constraintWidgetContainer.mHorizontalChainsArray;
        } else {
            offset = 2;
            chainsSize = constraintWidgetContainer.mVerticalChainsSize;
            chainsArray = constraintWidgetContainer.mVerticalChainsArray;
        }
        for (int i = 0; i < chainsSize; i++) {
            ChainHead first = chainsArray[i];
            first.define();
            if (!constraintWidgetContainer.optimizeFor(4)) {
                applyChainConstraints(constraintWidgetContainer, system, orientation, offset, first);
            } else if (!Optimizer.applyChainOptimized(constraintWidgetContainer, system, orientation, offset, first)) {
                applyChainConstraints(constraintWidgetContainer, system, orientation, offset, first);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:314:0x06a7  */
    /* JADX WARNING: Removed duplicated region for block: B:288:0x0624  */
    /* JADX WARNING: Removed duplicated region for block: B:285:0x0619 A:{SKIP} */
    /* JADX WARNING: Removed duplicated region for block: B:288:0x0624  */
    /* JADX WARNING: Removed duplicated region for block: B:314:0x06a7  */
    /* JADX WARNING: Removed duplicated region for block: B:285:0x0619 A:{SKIP} */
    /* JADX WARNING: Removed duplicated region for block: B:314:0x06a7  */
    /* JADX WARNING: Removed duplicated region for block: B:288:0x0624  */
    static void applyChainConstraints(android.support.constraint.solver.widgets.ConstraintWidgetContainer r60, android.support.constraint.solver.LinearSystem r61, int r62, int r63, android.support.constraint.solver.widgets.ChainHead r64) {
        /*
        r0 = r60;
        r10 = r61;
        r12 = r64;
        r13 = r12.mFirst;
        r14 = r12.mLast;
        r9 = r12.mFirstVisibleWidget;
        r8 = r12.mLastVisibleWidget;
        r7 = r12.mHead;
        r1 = r13;
        r2 = 0;
        r3 = 0;
        r4 = r12.mTotalWeight;
        r6 = r12.mFirstMatchConstraintWidget;
        r5 = r12.mLastMatchConstraintWidget;
        r15 = r1;
        r1 = r0.mListDimensionBehaviors;
        r1 = r1[r62];
        r16 = r2;
        r2 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        r17 = r3;
        if (r1 != r2) goto L_0x0028;
    L_0x0026:
        r1 = 1;
        goto L_0x0029;
    L_0x0028:
        r1 = 0;
    L_0x0029:
        r19 = r1;
        r1 = 0;
        r2 = 0;
        r20 = 0;
        if (r62 != 0) goto L_0x0052;
    L_0x0031:
        r3 = r7.mHorizontalChainStyle;
        if (r3 != 0) goto L_0x0037;
    L_0x0035:
        r3 = 1;
        goto L_0x0038;
    L_0x0037:
        r3 = 0;
    L_0x0038:
        r1 = r3;
        r3 = r7.mHorizontalChainStyle;
        r23 = r1;
        r1 = 1;
        if (r3 != r1) goto L_0x0042;
    L_0x0040:
        r1 = 1;
        goto L_0x0043;
    L_0x0042:
        r1 = 0;
    L_0x0043:
        r2 = r7.mHorizontalChainStyle;
        r3 = 2;
        if (r2 != r3) goto L_0x004a;
    L_0x0048:
        r2 = 1;
        goto L_0x004b;
    L_0x004a:
        r2 = 0;
    L_0x004b:
        r3 = r15;
        r20 = r16;
    L_0x004e:
        r16 = r1;
        r15 = r2;
        goto L_0x0072;
    L_0x0052:
        r3 = r7.mVerticalChainStyle;
        if (r3 != 0) goto L_0x0058;
    L_0x0056:
        r3 = 1;
        goto L_0x0059;
    L_0x0058:
        r3 = 0;
    L_0x0059:
        r1 = r3;
        r3 = r7.mVerticalChainStyle;
        r24 = r1;
        r1 = 1;
        if (r3 != r1) goto L_0x0063;
    L_0x0061:
        r1 = 1;
        goto L_0x0064;
    L_0x0063:
        r1 = 0;
    L_0x0064:
        r2 = r7.mVerticalChainStyle;
        r3 = 2;
        if (r2 != r3) goto L_0x006b;
    L_0x0069:
        r2 = 1;
        goto L_0x006c;
    L_0x006b:
        r2 = 0;
    L_0x006c:
        r3 = r15;
        r20 = r16;
        r23 = r24;
        goto L_0x004e;
    L_0x0072:
        r25 = r5;
        if (r17 != 0) goto L_0x0154;
    L_0x0076:
        r2 = r3.mListAnchors;
        r2 = r2[r63];
        r22 = 4;
        if (r19 != 0) goto L_0x0080;
    L_0x007e:
        if (r15 == 0) goto L_0x0082;
    L_0x0080:
        r22 = 1;
    L_0x0082:
        r24 = r2.getMargin();
        r1 = r2.mTarget;
        if (r1 == 0) goto L_0x0094;
    L_0x008a:
        if (r3 == r13) goto L_0x0094;
    L_0x008c:
        r1 = r2.mTarget;
        r1 = r1.getMargin();
        r24 = r24 + r1;
    L_0x0094:
        r1 = r24;
        if (r15 == 0) goto L_0x009f;
    L_0x0098:
        if (r3 == r13) goto L_0x009f;
    L_0x009a:
        if (r3 == r9) goto L_0x009f;
    L_0x009c:
        r22 = 6;
        goto L_0x00a5;
    L_0x009f:
        if (r23 == 0) goto L_0x00a5;
    L_0x00a1:
        if (r19 == 0) goto L_0x00a5;
    L_0x00a3:
        r22 = 4;
    L_0x00a5:
        r28 = r22;
        r5 = r2.mTarget;
        if (r5 == 0) goto L_0x00d6;
    L_0x00ab:
        if (r3 != r9) goto L_0x00bc;
    L_0x00ad:
        r5 = r2.mSolverVariable;
        r30 = r4;
        r4 = r2.mTarget;
        r4 = r4.mSolverVariable;
        r31 = r6;
        r6 = 5;
        r10.addGreaterThan(r5, r4, r1, r6);
        goto L_0x00ca;
    L_0x00bc:
        r30 = r4;
        r31 = r6;
        r4 = r2.mSolverVariable;
        r5 = r2.mTarget;
        r5 = r5.mSolverVariable;
        r6 = 6;
        r10.addGreaterThan(r4, r5, r1, r6);
    L_0x00ca:
        r4 = r2.mSolverVariable;
        r5 = r2.mTarget;
        r5 = r5.mSolverVariable;
        r6 = r28;
        r10.addEquality(r4, r5, r1, r6);
        goto L_0x00dc;
    L_0x00d6:
        r30 = r4;
        r31 = r6;
        r6 = r28;
    L_0x00dc:
        if (r19 == 0) goto L_0x011c;
    L_0x00de:
        r4 = r3.getVisibility();
        r5 = 8;
        if (r4 == r5) goto L_0x0106;
    L_0x00e6:
        r4 = r3.mListDimensionBehaviors;
        r4 = r4[r62];
        r5 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (r4 != r5) goto L_0x0106;
    L_0x00ee:
        r4 = r3.mListAnchors;
        r5 = r63 + 1;
        r4 = r4[r5];
        r4 = r4.mSolverVariable;
        r5 = r3.mListAnchors;
        r5 = r5[r63];
        r5 = r5.mSolverVariable;
        r32 = r1;
        r33 = r2;
        r1 = 0;
        r2 = 5;
        r10.addGreaterThan(r4, r5, r1, r2);
        goto L_0x010b;
    L_0x0106:
        r32 = r1;
        r33 = r2;
        r1 = 0;
    L_0x010b:
        r2 = r3.mListAnchors;
        r2 = r2[r63];
        r2 = r2.mSolverVariable;
        r4 = r0.mListAnchors;
        r4 = r4[r63];
        r4 = r4.mSolverVariable;
        r5 = 6;
        r10.addGreaterThan(r2, r4, r1, r5);
        goto L_0x0120;
    L_0x011c:
        r32 = r1;
        r33 = r2;
    L_0x0120:
        r1 = r3.mListAnchors;
        r2 = r63 + 1;
        r1 = r1[r2];
        r1 = r1.mTarget;
        if (r1 == 0) goto L_0x0140;
    L_0x012a:
        r2 = r1.mOwner;
        r4 = r2.mListAnchors;
        r4 = r4[r63];
        r4 = r4.mTarget;
        if (r4 == 0) goto L_0x013e;
    L_0x0134:
        r4 = r2.mListAnchors;
        r4 = r4[r63];
        r4 = r4.mTarget;
        r4 = r4.mOwner;
        if (r4 == r3) goto L_0x0141;
    L_0x013e:
        r2 = 0;
        goto L_0x0141;
    L_0x0140:
        r2 = 0;
    L_0x0141:
        r20 = r2;
        if (r20 == 0) goto L_0x0149;
    L_0x0145:
        r2 = r20;
        r3 = r2;
        goto L_0x014c;
    L_0x0149:
        r1 = 1;
        r17 = r1;
    L_0x014c:
        r5 = r25;
        r4 = r30;
        r6 = r31;
        goto L_0x0072;
    L_0x0154:
        r30 = r4;
        r31 = r6;
        if (r8 == 0) goto L_0x0180;
    L_0x015a:
        r1 = r14.mListAnchors;
        r2 = r63 + 1;
        r1 = r1[r2];
        r1 = r1.mTarget;
        if (r1 == 0) goto L_0x0180;
    L_0x0164:
        r1 = r8.mListAnchors;
        r2 = r63 + 1;
        r1 = r1[r2];
        r2 = r1.mSolverVariable;
        r4 = r14.mListAnchors;
        r5 = r63 + 1;
        r4 = r4[r5];
        r4 = r4.mTarget;
        r4 = r4.mSolverVariable;
        r5 = r1.getMargin();
        r5 = -r5;
        r6 = 5;
        r10.addLowerThan(r2, r4, r5, r6);
        goto L_0x0181;
    L_0x0180:
        r6 = 5;
    L_0x0181:
        if (r19 == 0) goto L_0x01a1;
    L_0x0183:
        r1 = r0.mListAnchors;
        r2 = r63 + 1;
        r1 = r1[r2];
        r1 = r1.mSolverVariable;
        r2 = r14.mListAnchors;
        r4 = r63 + 1;
        r2 = r2[r4];
        r2 = r2.mSolverVariable;
        r4 = r14.mListAnchors;
        r5 = r63 + 1;
        r4 = r4[r5];
        r4 = r4.getMargin();
        r5 = 6;
        r10.addGreaterThan(r1, r2, r4, r5);
    L_0x01a1:
        r5 = r12.mWeightedMatchConstraintsWidgets;
        if (r5 == 0) goto L_0x0272;
    L_0x01a5:
        r1 = r5.size();
        r2 = 1;
        if (r1 <= r2) goto L_0x0272;
    L_0x01ac:
        r4 = 0;
        r21 = 0;
        r2 = r12.mHasUndefinedWeights;
        if (r2 == 0) goto L_0x01bc;
    L_0x01b3:
        r2 = r12.mHasComplexMatchWeights;
        if (r2 != 0) goto L_0x01bc;
    L_0x01b7:
        r2 = r12.mWidgetsMatchCount;
        r2 = (float) r2;
        r30 = r2;
    L_0x01bc:
        r2 = 0;
    L_0x01bd:
        if (r2 >= r1) goto L_0x0272;
    L_0x01bf:
        r22 = r5.get(r2);
        r6 = r22;
        r6 = (android.support.constraint.solver.widgets.ConstraintWidget) r6;
        r0 = r6.mWeight;
        r0 = r0[r62];
        r22 = 0;
        r24 = (r0 > r22 ? 1 : (r0 == r22 ? 0 : -1));
        if (r24 >= 0) goto L_0x0200;
    L_0x01d1:
        r43 = r0;
        r0 = r12.mHasComplexMatchWeights;
        if (r0 == 0) goto L_0x01f5;
    L_0x01d7:
        r0 = r6.mListAnchors;
        r22 = r63 + 1;
        r0 = r0[r22];
        r0 = r0.mSolverVariable;
        r44 = r1;
        r1 = r6.mListAnchors;
        r1 = r1[r63];
        r1 = r1.mSolverVariable;
        r45 = r3;
        r3 = 4;
        r46 = r5;
        r5 = 0;
        r10.addEquality(r0, r1, r5, r3);
        r3 = 0;
        r5 = 6;
        goto L_0x0265;
    L_0x01f5:
        r44 = r1;
        r45 = r3;
        r46 = r5;
        r0 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r43 = r0;
        goto L_0x0208;
    L_0x0200:
        r43 = r0;
        r44 = r1;
        r45 = r3;
        r46 = r5;
    L_0x0208:
        r0 = (r43 > r22 ? 1 : (r43 == r22 ? 0 : -1));
        if (r0 != 0) goto L_0x0220;
    L_0x020c:
        r0 = r6.mListAnchors;
        r1 = r63 + 1;
        r0 = r0[r1];
        r0 = r0.mSolverVariable;
        r1 = r6.mListAnchors;
        r1 = r1[r63];
        r1 = r1.mSolverVariable;
        r3 = 0;
        r5 = 6;
        r10.addEquality(r0, r1, r3, r5);
        goto L_0x0265;
    L_0x0220:
        r3 = 0;
        r5 = 6;
        if (r4 == 0) goto L_0x025d;
    L_0x0224:
        r0 = r4.mListAnchors;
        r0 = r0[r63];
        r0 = r0.mSolverVariable;
        r1 = r4.mListAnchors;
        r18 = r63 + 1;
        r1 = r1[r18];
        r1 = r1.mSolverVariable;
        r3 = r6.mListAnchors;
        r3 = r3[r63];
        r3 = r3.mSolverVariable;
        r5 = r6.mListAnchors;
        r18 = r63 + 1;
        r5 = r5[r18];
        r5 = r5.mSolverVariable;
        r48 = r4;
        r4 = r61.createRow();
        r35 = r4;
        r36 = r21;
        r37 = r30;
        r38 = r43;
        r39 = r0;
        r40 = r1;
        r41 = r3;
        r42 = r5;
        r35.createRowEqualMatchDimensions(r36, r37, r38, r39, r40, r41, r42);
        r10.addConstraint(r4);
        goto L_0x025f;
    L_0x025d:
        r48 = r4;
    L_0x025f:
        r0 = r6;
        r1 = r43;
        r4 = r0;
        r21 = r1;
    L_0x0265:
        r2 = r2 + 1;
        r1 = r44;
        r3 = r45;
        r5 = r46;
        r0 = r60;
        r6 = 5;
        goto L_0x01bd;
    L_0x0272:
        r45 = r3;
        r46 = r5;
        if (r9 == 0) goto L_0x0318;
    L_0x0278:
        if (r9 == r8) goto L_0x0287;
    L_0x027a:
        if (r15 == 0) goto L_0x027d;
    L_0x027c:
        goto L_0x0287;
    L_0x027d:
        r35 = r7;
        r0 = r8;
        r10 = r9;
        r28 = r45;
        r32 = r46;
        goto L_0x0320;
    L_0x0287:
        r1 = r13.mListAnchors;
        r1 = r1[r63];
        r2 = r14.mListAnchors;
        r3 = r63 + 1;
        r2 = r2[r3];
        r3 = r13.mListAnchors;
        r3 = r3[r63];
        r3 = r3.mTarget;
        if (r3 == 0) goto L_0x02a2;
    L_0x0299:
        r3 = r13.mListAnchors;
        r3 = r3[r63];
        r3 = r3.mTarget;
        r3 = r3.mSolverVariable;
        goto L_0x02a3;
    L_0x02a2:
        r3 = 0;
    L_0x02a3:
        r18 = r3;
        r3 = r14.mListAnchors;
        r4 = r63 + 1;
        r3 = r3[r4];
        r3 = r3.mTarget;
        if (r3 == 0) goto L_0x02ba;
    L_0x02af:
        r3 = r14.mListAnchors;
        r4 = r63 + 1;
        r3 = r3[r4];
        r3 = r3.mTarget;
        r3 = r3.mSolverVariable;
        goto L_0x02bb;
    L_0x02ba:
        r3 = 0;
    L_0x02bb:
        r21 = r3;
        if (r9 != r8) goto L_0x02c9;
    L_0x02bf:
        r3 = r9.mListAnchors;
        r1 = r3[r63];
        r3 = r9.mListAnchors;
        r4 = r63 + 1;
        r2 = r3[r4];
    L_0x02c9:
        r6 = r1;
        r5 = r2;
        if (r18 == 0) goto L_0x030b;
    L_0x02cd:
        if (r21 == 0) goto L_0x030b;
    L_0x02cf:
        r1 = 1056964608; // 0x3f000000 float:0.5 double:5.222099017E-315;
        if (r62 != 0) goto L_0x02d8;
    L_0x02d3:
        r1 = r7.mHorizontalBiasPercent;
    L_0x02d5:
        r22 = r1;
        goto L_0x02db;
    L_0x02d8:
        r1 = r7.mVerticalBiasPercent;
        goto L_0x02d5;
    L_0x02db:
        r24 = r6.getMargin();
        r26 = r5.getMargin();
        r2 = r6.mSolverVariable;
        r4 = r5.mSolverVariable;
        r27 = 5;
        r1 = r61;
        r28 = r45;
        r3 = r18;
        r29 = r4;
        r4 = r24;
        r33 = r5;
        r32 = r46;
        r5 = r22;
        r34 = r6;
        r6 = r21;
        r35 = r7;
        r7 = r29;
        r0 = r8;
        r8 = r26;
        r10 = r9;
        r9 = r27;
        r1.addCentering(r2, r3, r4, r5, r6, r7, r8, r9);
        goto L_0x0313;
    L_0x030b:
        r35 = r7;
        r0 = r8;
        r10 = r9;
        r28 = r45;
        r32 = r46;
    L_0x0313:
        r9 = r10;
        r59 = r14;
        goto L_0x046e;
    L_0x0318:
        r35 = r7;
        r0 = r8;
        r10 = r9;
        r28 = r45;
        r32 = r46;
    L_0x0320:
        if (r23 == 0) goto L_0x0472;
    L_0x0322:
        if (r10 == 0) goto L_0x0472;
    L_0x0324:
        r1 = r10;
        r2 = r10;
        r3 = r12.mWidgetsMatchCount;
        if (r3 <= 0) goto L_0x0333;
    L_0x032a:
        r3 = r12.mWidgetsCount;
        r4 = r12.mWidgetsMatchCount;
        if (r3 != r4) goto L_0x0333;
    L_0x0330:
        r47 = 1;
        goto L_0x0335;
    L_0x0333:
        r47 = 0;
    L_0x0335:
        r9 = r1;
        r8 = r2;
    L_0x0337:
        if (r9 == 0) goto L_0x0465;
    L_0x0339:
        r1 = r9.mNextChainWidget;
        r1 = r1[r62];
        r7 = r1;
    L_0x033e:
        if (r7 == 0) goto L_0x034d;
    L_0x0340:
        r1 = r7.getVisibility();
        r2 = 8;
        if (r1 != r2) goto L_0x034f;
    L_0x0348:
        r1 = r7.mNextChainWidget;
        r7 = r1[r62];
        goto L_0x033e;
    L_0x034d:
        r2 = 8;
    L_0x034f:
        if (r7 != 0) goto L_0x0360;
    L_0x0351:
        if (r9 != r0) goto L_0x0354;
    L_0x0353:
        goto L_0x0360;
    L_0x0354:
        r34 = r7;
        r36 = r8;
        r37 = r9;
        r53 = r14;
        r14 = 8;
        goto L_0x0451;
    L_0x0360:
        r1 = r9.mListAnchors;
        r6 = r1[r63];
        r5 = r6.mSolverVariable;
        r1 = r6.mTarget;
        if (r1 == 0) goto L_0x036f;
    L_0x036a:
        r1 = r6.mTarget;
        r1 = r1.mSolverVariable;
        goto L_0x0370;
    L_0x036f:
        r1 = 0;
    L_0x0370:
        if (r8 == r9) goto L_0x037d;
    L_0x0372:
        r3 = r8.mListAnchors;
        r4 = r63 + 1;
        r3 = r3[r4];
        r1 = r3.mSolverVariable;
    L_0x037a:
        r18 = r1;
        goto L_0x0395;
    L_0x037d:
        if (r9 != r10) goto L_0x037a;
    L_0x037f:
        if (r8 != r9) goto L_0x037a;
    L_0x0381:
        r3 = r13.mListAnchors;
        r3 = r3[r63];
        r3 = r3.mTarget;
        if (r3 == 0) goto L_0x0392;
    L_0x0389:
        r3 = r13.mListAnchors;
        r3 = r3[r63];
        r3 = r3.mTarget;
        r3 = r3.mSolverVariable;
        goto L_0x0393;
    L_0x0392:
        r3 = 0;
    L_0x0393:
        r1 = r3;
        goto L_0x037a;
    L_0x0395:
        r1 = 0;
        r3 = 0;
        r4 = 0;
        r20 = r6.getMargin();
        r2 = r9.mListAnchors;
        r21 = r63 + 1;
        r2 = r2[r21];
        r2 = r2.getMargin();
        if (r7 == 0) goto L_0x03bf;
    L_0x03a8:
        r50 = r1;
        r1 = r7.mListAnchors;
        r1 = r1[r63];
        r3 = r1.mSolverVariable;
        r51 = r1;
        r1 = r9.mListAnchors;
        r21 = r63 + 1;
        r1 = r1[r21];
        r1 = r1.mSolverVariable;
        r22 = r1;
        r21 = r3;
        goto L_0x03dd;
    L_0x03bf:
        r50 = r1;
        r1 = r14.mListAnchors;
        r21 = r63 + 1;
        r1 = r1[r21];
        r1 = r1.mTarget;
        if (r1 == 0) goto L_0x03cd;
    L_0x03cb:
        r3 = r1.mSolverVariable;
    L_0x03cd:
        r52 = r1;
        r1 = r9.mListAnchors;
        r21 = r63 + 1;
        r1 = r1[r21];
        r1 = r1.mSolverVariable;
        r22 = r1;
        r21 = r3;
        r51 = r52;
    L_0x03dd:
        if (r51 == 0) goto L_0x03e4;
    L_0x03df:
        r1 = r51.getMargin();
        r2 = r2 + r1;
    L_0x03e4:
        r24 = r2;
        if (r8 == 0) goto L_0x03f4;
    L_0x03e8:
        r1 = r8.mListAnchors;
        r2 = r63 + 1;
        r1 = r1[r2];
        r1 = r1.getMargin();
        r20 = r20 + r1;
    L_0x03f4:
        if (r5 == 0) goto L_0x0447;
    L_0x03f6:
        if (r18 == 0) goto L_0x0447;
    L_0x03f8:
        if (r21 == 0) goto L_0x0447;
    L_0x03fa:
        if (r22 == 0) goto L_0x0447;
    L_0x03fc:
        r1 = r20;
        if (r9 != r10) goto L_0x0408;
    L_0x0400:
        r2 = r10.mListAnchors;
        r2 = r2[r63];
        r1 = r2.getMargin();
    L_0x0408:
        r26 = r1;
        r1 = r24;
        if (r9 != r0) goto L_0x0418;
    L_0x040e:
        r2 = r0.mListAnchors;
        r3 = r63 + 1;
        r2 = r2[r3];
        r1 = r2.getMargin();
    L_0x0418:
        r27 = r1;
        r1 = 4;
        if (r47 == 0) goto L_0x041e;
    L_0x041d:
        r1 = 6;
    L_0x041e:
        r28 = r1;
        r29 = 1056964608; // 0x3f000000 float:0.5 double:5.222099017E-315;
        r1 = r61;
        r4 = 8;
        r2 = r5;
        r3 = r18;
        r53 = r14;
        r14 = 8;
        r4 = r26;
        r33 = r5;
        r5 = r29;
        r29 = r6;
        r6 = r21;
        r34 = r7;
        r7 = r22;
        r36 = r8;
        r8 = r27;
        r37 = r9;
        r9 = r28;
        r1.addCentering(r2, r3, r4, r5, r6, r7, r8, r9);
        goto L_0x0451;
    L_0x0447:
        r34 = r7;
        r36 = r8;
        r37 = r9;
        r53 = r14;
        r14 = 8;
    L_0x0451:
        r1 = r37.getVisibility();
        if (r1 == r14) goto L_0x045b;
    L_0x0457:
        r1 = r37;
        r8 = r1;
        goto L_0x045d;
    L_0x045b:
        r8 = r36;
    L_0x045d:
        r9 = r34;
        r20 = r34;
        r14 = r53;
        goto L_0x0337;
    L_0x0465:
        r37 = r9;
        r53 = r14;
        r9 = r10;
        r28 = r37;
        r59 = r53;
    L_0x046e:
        r10 = r61;
        goto L_0x0617;
    L_0x0472:
        r53 = r14;
        r14 = 8;
        if (r16 == 0) goto L_0x0612;
    L_0x0478:
        if (r10 == 0) goto L_0x0612;
    L_0x047a:
        r1 = r10;
        r2 = r10;
        r3 = r12.mWidgetsMatchCount;
        if (r3 <= 0) goto L_0x0489;
    L_0x0480:
        r3 = r12.mWidgetsCount;
        r4 = r12.mWidgetsMatchCount;
        if (r3 != r4) goto L_0x0489;
    L_0x0486:
        r47 = 1;
        goto L_0x048b;
    L_0x0489:
        r47 = 0;
    L_0x048b:
        r9 = r1;
        r8 = r2;
    L_0x048d:
        if (r9 == 0) goto L_0x0574;
    L_0x048f:
        r1 = r9.mNextChainWidget;
        r1 = r1[r62];
    L_0x0493:
        if (r1 == 0) goto L_0x04a0;
    L_0x0495:
        r2 = r1.getVisibility();
        if (r2 != r14) goto L_0x04a0;
    L_0x049b:
        r2 = r1.mNextChainWidget;
        r1 = r2[r62];
        goto L_0x0493;
    L_0x04a0:
        if (r9 == r10) goto L_0x0559;
    L_0x04a2:
        if (r9 == r0) goto L_0x0559;
    L_0x04a4:
        if (r1 == 0) goto L_0x0559;
    L_0x04a6:
        if (r1 != r0) goto L_0x04a9;
    L_0x04a8:
        r1 = 0;
    L_0x04a9:
        r7 = r1;
        r1 = r9.mListAnchors;
        r6 = r1[r63];
        r5 = r6.mSolverVariable;
        r1 = r6.mTarget;
        if (r1 == 0) goto L_0x04b9;
    L_0x04b4:
        r1 = r6.mTarget;
        r1 = r1.mSolverVariable;
        goto L_0x04ba;
    L_0x04b9:
        r1 = 0;
    L_0x04ba:
        r2 = r8.mListAnchors;
        r3 = r63 + 1;
        r2 = r2[r3];
        r4 = r2.mSolverVariable;
        r1 = 0;
        r2 = 0;
        r3 = 0;
        r18 = r6.getMargin();
        r14 = r9.mListAnchors;
        r20 = r63 + 1;
        r14 = r14[r20];
        r14 = r14.getMargin();
        if (r7 == 0) goto L_0x04ee;
    L_0x04d5:
        r54 = r1;
        r1 = r7.mListAnchors;
        r1 = r1[r63];
        r2 = r1.mSolverVariable;
        r55 = r2;
        r2 = r1.mTarget;
        if (r2 == 0) goto L_0x04e8;
    L_0x04e3:
        r2 = r1.mTarget;
        r2 = r2.mSolverVariable;
        goto L_0x04e9;
    L_0x04e8:
        r2 = 0;
    L_0x04e9:
        r56 = r1;
        r20 = r2;
        goto L_0x050a;
    L_0x04ee:
        r54 = r1;
        r1 = r9.mListAnchors;
        r20 = r63 + 1;
        r1 = r1[r20];
        r1 = r1.mTarget;
        if (r1 == 0) goto L_0x04fc;
    L_0x04fa:
        r2 = r1.mSolverVariable;
    L_0x04fc:
        r56 = r1;
        r1 = r9.mListAnchors;
        r20 = r63 + 1;
        r1 = r1[r20];
        r1 = r1.mSolverVariable;
        r20 = r1;
        r55 = r2;
    L_0x050a:
        if (r56 == 0) goto L_0x0511;
    L_0x050c:
        r1 = r56.getMargin();
        r14 = r14 + r1;
    L_0x0511:
        if (r8 == 0) goto L_0x051f;
    L_0x0513:
        r1 = r8.mListAnchors;
        r2 = r63 + 1;
        r1 = r1[r2];
        r1 = r1.getMargin();
        r18 = r18 + r1;
    L_0x051f:
        r1 = 4;
        if (r47 == 0) goto L_0x0523;
    L_0x0522:
        r1 = 6;
    L_0x0523:
        r21 = r1;
        if (r5 == 0) goto L_0x054f;
    L_0x0527:
        if (r4 == 0) goto L_0x054f;
    L_0x0529:
        if (r55 == 0) goto L_0x054f;
    L_0x052b:
        if (r20 == 0) goto L_0x054f;
    L_0x052d:
        r22 = 1056964608; // 0x3f000000 float:0.5 double:5.222099017E-315;
        r1 = r61;
        r2 = r5;
        r3 = r4;
        r24 = r4;
        r4 = r18;
        r26 = r5;
        r11 = 5;
        r5 = r22;
        r22 = r6;
        r6 = r55;
        r27 = r7;
        r7 = r20;
        r28 = r8;
        r8 = r14;
        r29 = r9;
        r9 = r21;
        r1.addCentering(r2, r3, r4, r5, r6, r7, r8, r9);
        goto L_0x0556;
    L_0x054f:
        r27 = r7;
        r28 = r8;
        r29 = r9;
        r11 = 5;
    L_0x0556:
        r20 = r27;
        goto L_0x0560;
    L_0x0559:
        r28 = r8;
        r29 = r9;
        r11 = 5;
        r20 = r1;
    L_0x0560:
        r1 = r29.getVisibility();
        r2 = 8;
        if (r1 == r2) goto L_0x056c;
    L_0x0568:
        r1 = r29;
        r8 = r1;
        goto L_0x056e;
    L_0x056c:
        r8 = r28;
    L_0x056e:
        r9 = r20;
        r14 = 8;
        goto L_0x048d;
    L_0x0574:
        r28 = r8;
        r29 = r9;
        r11 = 5;
        r1 = r10.mListAnchors;
        r14 = r1[r63];
        r1 = r13.mListAnchors;
        r1 = r1[r63];
        r9 = r1.mTarget;
        r1 = r0.mListAnchors;
        r2 = r63 + 1;
        r8 = r1[r2];
        r7 = r53;
        r1 = r7.mListAnchors;
        r2 = r63 + 1;
        r1 = r1[r2];
        r6 = r1.mTarget;
        if (r9 == 0) goto L_0x05ec;
    L_0x0595:
        if (r10 == r0) goto L_0x05af;
    L_0x0597:
        r1 = r14.mSolverVariable;
        r2 = r9.mSolverVariable;
        r3 = r14.getMargin();
        r5 = r10;
        r10 = r61;
        r10.addEquality(r1, r2, r3, r11);
        r57 = r5;
        r58 = r6;
        r59 = r7;
        r11 = r8;
        r18 = r9;
        goto L_0x05f7;
    L_0x05af:
        r5 = r10;
        r10 = r61;
        if (r6 == 0) goto L_0x05e2;
    L_0x05b4:
        r2 = r14.mSolverVariable;
        r3 = r9.mSolverVariable;
        r4 = r14.getMargin();
        r18 = 1056964608; // 0x3f000000 float:0.5 double:5.222099017E-315;
        r1 = r8.mSolverVariable;
        r11 = r6.mSolverVariable;
        r21 = r8.getMargin();
        r22 = 5;
        r24 = r1;
        r1 = r61;
        r57 = r5;
        r5 = r18;
        r58 = r6;
        r6 = r24;
        r59 = r7;
        r7 = r11;
        r11 = r8;
        r8 = r21;
        r18 = r9;
        r9 = r22;
        r1.addCentering(r2, r3, r4, r5, r6, r7, r8, r9);
        goto L_0x05f7;
    L_0x05e2:
        r57 = r5;
        r58 = r6;
        r59 = r7;
        r11 = r8;
        r18 = r9;
        goto L_0x05f7;
    L_0x05ec:
        r58 = r6;
        r59 = r7;
        r11 = r8;
        r18 = r9;
        r57 = r10;
        r10 = r61;
    L_0x05f7:
        r1 = r58;
        if (r1 == 0) goto L_0x060d;
    L_0x05fb:
        r9 = r57;
        if (r9 == r0) goto L_0x060f;
    L_0x05ff:
        r2 = r11.mSolverVariable;
        r3 = r1.mSolverVariable;
        r4 = r11.getMargin();
        r4 = -r4;
        r5 = 5;
        r10.addEquality(r2, r3, r4, r5);
        goto L_0x060f;
    L_0x060d:
        r9 = r57;
    L_0x060f:
        r28 = r29;
        goto L_0x0617;
    L_0x0612:
        r9 = r10;
        r59 = r53;
        r10 = r61;
    L_0x0617:
        if (r23 != 0) goto L_0x0622;
    L_0x0619:
        if (r16 == 0) goto L_0x061c;
    L_0x061b:
        goto L_0x0622;
    L_0x061c:
        r33 = r9;
        r14 = r59;
        goto L_0x06ab;
    L_0x0622:
        if (r9 == 0) goto L_0x06a7;
    L_0x0624:
        r1 = r9.mListAnchors;
        r1 = r1[r63];
        r2 = r0.mListAnchors;
        r3 = r63 + 1;
        r2 = r2[r3];
        r3 = r1.mTarget;
        if (r3 == 0) goto L_0x0637;
    L_0x0632:
        r3 = r1.mTarget;
        r3 = r3.mSolverVariable;
        goto L_0x0638;
    L_0x0637:
        r3 = 0;
    L_0x0638:
        r11 = r3;
        r3 = r2.mTarget;
        if (r3 == 0) goto L_0x0642;
    L_0x063d:
        r3 = r2.mTarget;
        r3 = r3.mSolverVariable;
        goto L_0x0643;
    L_0x0642:
        r3 = 0;
    L_0x0643:
        r14 = r59;
        if (r14 == r0) goto L_0x065c;
    L_0x0647:
        r4 = r14.mListAnchors;
        r5 = r63 + 1;
        r4 = r4[r5];
        r5 = r4.mTarget;
        if (r5 == 0) goto L_0x0658;
    L_0x0651:
        r5 = r4.mTarget;
        r5 = r5.mSolverVariable;
        r49 = r5;
        goto L_0x065a;
    L_0x0658:
        r49 = 0;
    L_0x065a:
        r3 = r49;
    L_0x065c:
        r18 = r3;
        if (r9 != r0) goto L_0x066a;
    L_0x0660:
        r3 = r9.mListAnchors;
        r1 = r3[r63];
        r3 = r9.mListAnchors;
        r4 = r63 + 1;
        r2 = r3[r4];
    L_0x066a:
        r8 = r1;
        r7 = r2;
        if (r11 == 0) goto L_0x06a4;
    L_0x066e:
        if (r18 == 0) goto L_0x06a4;
    L_0x0670:
        r21 = 1056964608; // 0x3f000000 float:0.5 double:5.222099017E-315;
        r22 = r8.getMargin();
        if (r0 != 0) goto L_0x0679;
    L_0x0678:
        r0 = r14;
    L_0x0679:
        r1 = r0.mListAnchors;
        r2 = r63 + 1;
        r1 = r1[r2];
        r24 = r1.getMargin();
        r2 = r8.mSolverVariable;
        r6 = r7.mSolverVariable;
        r26 = 5;
        r1 = r61;
        r3 = r11;
        r4 = r22;
        r5 = r21;
        r27 = r6;
        r6 = r18;
        r29 = r7;
        r7 = r27;
        r27 = r8;
        r8 = r24;
        r33 = r9;
        r9 = r26;
        r1.addCentering(r2, r3, r4, r5, r6, r7, r8, r9);
        goto L_0x06ab;
    L_0x06a4:
        r33 = r9;
        goto L_0x06ab;
    L_0x06a7:
        r33 = r9;
        r14 = r59;
    L_0x06ab:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.Chain.applyChainConstraints(android.support.constraint.solver.widgets.ConstraintWidgetContainer, android.support.constraint.solver.LinearSystem, int, int, android.support.constraint.solver.widgets.ChainHead):void");
    }
}
