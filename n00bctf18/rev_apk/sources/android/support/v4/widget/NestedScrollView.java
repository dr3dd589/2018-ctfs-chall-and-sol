package android.support.v4.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.NestedScrollingChild2;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.ScrollingView;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityRecordCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.AnimationUtils;
import android.widget.EdgeEffect;
import android.widget.FrameLayout;
import android.widget.OverScroller;
import android.widget.ScrollView;
import java.util.List;

public class NestedScrollView extends FrameLayout implements NestedScrollingParent, NestedScrollingChild2, ScrollingView {
    private static final AccessibilityDelegate ACCESSIBILITY_DELEGATE = new AccessibilityDelegate();
    static final int ANIMATED_SCROLL_GAP = 250;
    private static final int INVALID_POINTER = -1;
    static final float MAX_SCROLL_FACTOR = 0.5f;
    private static final int[] SCROLLVIEW_STYLEABLE = new int[]{16843130};
    private static final String TAG = "NestedScrollView";
    private int mActivePointerId;
    private final NestedScrollingChildHelper mChildHelper;
    private View mChildToScrollTo;
    private EdgeEffect mEdgeGlowBottom;
    private EdgeEffect mEdgeGlowTop;
    private boolean mFillViewport;
    private boolean mIsBeingDragged;
    private boolean mIsLaidOut;
    private boolean mIsLayoutDirty;
    private int mLastMotionY;
    private long mLastScroll;
    private int mLastScrollerY;
    private int mMaximumVelocity;
    private int mMinimumVelocity;
    private int mNestedYOffset;
    private OnScrollChangeListener mOnScrollChangeListener;
    private final NestedScrollingParentHelper mParentHelper;
    private SavedState mSavedState;
    private final int[] mScrollConsumed;
    private final int[] mScrollOffset;
    private OverScroller mScroller;
    private boolean mSmoothScrollingEnabled;
    private final Rect mTempRect;
    private int mTouchSlop;
    private VelocityTracker mVelocityTracker;
    private float mVerticalScrollFactor;

    public interface OnScrollChangeListener {
        void onScrollChange(NestedScrollView nestedScrollView, int i, int i2, int i3, int i4);
    }

    static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
        public int scrollPosition;

        SavedState(Parcelable superState) {
            super(superState);
        }

        SavedState(Parcel source) {
            super(source);
            this.scrollPosition = source.readInt();
        }

        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeInt(this.scrollPosition);
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("HorizontalScrollView.SavedState{");
            stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
            stringBuilder.append(" scrollPosition=");
            stringBuilder.append(this.scrollPosition);
            stringBuilder.append("}");
            return stringBuilder.toString();
        }
    }

    static class AccessibilityDelegate extends AccessibilityDelegateCompat {
        AccessibilityDelegate() {
        }

        public boolean performAccessibilityAction(View host, int action, Bundle arguments) {
            if (super.performAccessibilityAction(host, action, arguments)) {
                return true;
            }
            NestedScrollView nsvHost = (NestedScrollView) host;
            if (!nsvHost.isEnabled()) {
                return false;
            }
            int targetScrollY;
            if (action == 4096) {
                targetScrollY = Math.min(nsvHost.getScrollY() + ((nsvHost.getHeight() - nsvHost.getPaddingBottom()) - nsvHost.getPaddingTop()), nsvHost.getScrollRange());
                if (targetScrollY == nsvHost.getScrollY()) {
                    return false;
                }
                nsvHost.smoothScrollTo(0, targetScrollY);
                return true;
            } else if (action != 8192) {
                return false;
            } else {
                targetScrollY = Math.max(nsvHost.getScrollY() - ((nsvHost.getHeight() - nsvHost.getPaddingBottom()) - nsvHost.getPaddingTop()), 0);
                if (targetScrollY == nsvHost.getScrollY()) {
                    return false;
                }
                nsvHost.smoothScrollTo(0, targetScrollY);
                return true;
            }
        }

        public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat info) {
            super.onInitializeAccessibilityNodeInfo(host, info);
            NestedScrollView nsvHost = (NestedScrollView) host;
            info.setClassName(ScrollView.class.getName());
            if (nsvHost.isEnabled()) {
                int scrollRange = nsvHost.getScrollRange();
                if (scrollRange > 0) {
                    info.setScrollable(true);
                    if (nsvHost.getScrollY() > 0) {
                        info.addAction(8192);
                    }
                    if (nsvHost.getScrollY() < scrollRange) {
                        info.addAction(4096);
                    }
                }
            }
        }

        public void onInitializeAccessibilityEvent(View host, AccessibilityEvent event) {
            super.onInitializeAccessibilityEvent(host, event);
            NestedScrollView nsvHost = (NestedScrollView) host;
            event.setClassName(ScrollView.class.getName());
            event.setScrollable(nsvHost.getScrollRange() > 0);
            event.setScrollX(nsvHost.getScrollX());
            event.setScrollY(nsvHost.getScrollY());
            AccessibilityRecordCompat.setMaxScrollX(event, nsvHost.getScrollX());
            AccessibilityRecordCompat.setMaxScrollY(event, nsvHost.getScrollRange());
        }
    }

    public NestedScrollView(Context context) {
        this(context, null);
    }

    public NestedScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NestedScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mTempRect = new Rect();
        this.mIsLayoutDirty = true;
        this.mIsLaidOut = false;
        this.mChildToScrollTo = null;
        this.mIsBeingDragged = false;
        this.mSmoothScrollingEnabled = true;
        this.mActivePointerId = -1;
        this.mScrollOffset = new int[2];
        this.mScrollConsumed = new int[2];
        initScrollView();
        TypedArray a = context.obtainStyledAttributes(attrs, SCROLLVIEW_STYLEABLE, defStyleAttr, 0);
        setFillViewport(a.getBoolean(0, false));
        a.recycle();
        this.mParentHelper = new NestedScrollingParentHelper(this);
        this.mChildHelper = new NestedScrollingChildHelper(this);
        setNestedScrollingEnabled(true);
        ViewCompat.setAccessibilityDelegate(this, ACCESSIBILITY_DELEGATE);
    }

    public void setNestedScrollingEnabled(boolean enabled) {
        this.mChildHelper.setNestedScrollingEnabled(enabled);
    }

    public boolean isNestedScrollingEnabled() {
        return this.mChildHelper.isNestedScrollingEnabled();
    }

    public boolean startNestedScroll(int axes) {
        return this.mChildHelper.startNestedScroll(axes);
    }

    public boolean startNestedScroll(int axes, int type) {
        return this.mChildHelper.startNestedScroll(axes, type);
    }

    public void stopNestedScroll() {
        this.mChildHelper.stopNestedScroll();
    }

    public void stopNestedScroll(int type) {
        this.mChildHelper.stopNestedScroll(type);
    }

    public boolean hasNestedScrollingParent() {
        return this.mChildHelper.hasNestedScrollingParent();
    }

    public boolean hasNestedScrollingParent(int type) {
        return this.mChildHelper.hasNestedScrollingParent(type);
    }

    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int[] offsetInWindow) {
        return this.mChildHelper.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow);
    }

    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int[] offsetInWindow, int type) {
        return this.mChildHelper.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow, type);
    }

    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow) {
        return this.mChildHelper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
    }

    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow, int type) {
        return this.mChildHelper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow, type);
    }

    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        return this.mChildHelper.dispatchNestedFling(velocityX, velocityY, consumed);
    }

    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        return this.mChildHelper.dispatchNestedPreFling(velocityX, velocityY);
    }

    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return (nestedScrollAxes & 2) != 0;
    }

    public void onNestedScrollAccepted(View child, View target, int nestedScrollAxes) {
        this.mParentHelper.onNestedScrollAccepted(child, target, nestedScrollAxes);
        startNestedScroll(2);
    }

    public void onStopNestedScroll(View target) {
        this.mParentHelper.onStopNestedScroll(target);
        stopNestedScroll();
    }

    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        int oldScrollY = getScrollY();
        scrollBy(0, dyUnconsumed);
        int myConsumed = getScrollY() - oldScrollY;
        dispatchNestedScroll(0, myConsumed, 0, dyUnconsumed - myConsumed, null);
    }

    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        dispatchNestedPreScroll(dx, dy, consumed, null);
    }

    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        if (consumed) {
            return false;
        }
        flingWithNestedDispatch((int) velocityY);
        return true;
    }

    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        return dispatchNestedPreFling(velocityX, velocityY);
    }

    public int getNestedScrollAxes() {
        return this.mParentHelper.getNestedScrollAxes();
    }

    public boolean shouldDelayChildPressedState() {
        return true;
    }

    protected float getTopFadingEdgeStrength() {
        if (getChildCount() == 0) {
            return 0.0f;
        }
        int length = getVerticalFadingEdgeLength();
        int scrollY = getScrollY();
        if (scrollY < length) {
            return ((float) scrollY) / ((float) length);
        }
        return 1.0f;
    }

    protected float getBottomFadingEdgeStrength() {
        if (getChildCount() == 0) {
            return 0.0f;
        }
        int length = getVerticalFadingEdgeLength();
        int span = (getChildAt(0).getBottom() - getScrollY()) - (getHeight() - getPaddingBottom());
        if (span < length) {
            return ((float) span) / ((float) length);
        }
        return 1.0f;
    }

    public int getMaxScrollAmount() {
        return (int) (((float) getHeight()) * MAX_SCROLL_FACTOR);
    }

    private void initScrollView() {
        this.mScroller = new OverScroller(getContext());
        setFocusable(true);
        setDescendantFocusability(262144);
        setWillNotDraw(false);
        ViewConfiguration configuration = ViewConfiguration.get(getContext());
        this.mTouchSlop = configuration.getScaledTouchSlop();
        this.mMinimumVelocity = configuration.getScaledMinimumFlingVelocity();
        this.mMaximumVelocity = configuration.getScaledMaximumFlingVelocity();
    }

    public void addView(View child) {
        if (getChildCount() <= 0) {
            super.addView(child);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    public void addView(View child, int index) {
        if (getChildCount() <= 0) {
            super.addView(child, index);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    public void addView(View child, LayoutParams params) {
        if (getChildCount() <= 0) {
            super.addView(child, params);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    public void addView(View child, int index, LayoutParams params) {
        if (getChildCount() <= 0) {
            super.addView(child, index, params);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    public void setOnScrollChangeListener(OnScrollChangeListener l) {
        this.mOnScrollChangeListener = l;
    }

    private boolean canScroll() {
        boolean z = false;
        View child = getChildAt(0);
        if (child == null) {
            return false;
        }
        if (getHeight() < (getPaddingTop() + child.getHeight()) + getPaddingBottom()) {
            z = true;
        }
        return z;
    }

    public boolean isFillViewport() {
        return this.mFillViewport;
    }

    public void setFillViewport(boolean fillViewport) {
        if (fillViewport != this.mFillViewport) {
            this.mFillViewport = fillViewport;
            requestLayout();
        }
    }

    public boolean isSmoothScrollingEnabled() {
        return this.mSmoothScrollingEnabled;
    }

    public void setSmoothScrollingEnabled(boolean smoothScrollingEnabled) {
        this.mSmoothScrollingEnabled = smoothScrollingEnabled;
    }

    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (this.mOnScrollChangeListener != null) {
            this.mOnScrollChangeListener.onScrollChange(this, l, t, oldl, oldt);
        }
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (this.mFillViewport && MeasureSpec.getMode(heightMeasureSpec) != 0 && getChildCount() > 0) {
            View child = getChildAt(null);
            int height = getMeasuredHeight();
            if (child.getMeasuredHeight() < height) {
                child.measure(getChildMeasureSpec(widthMeasureSpec, getPaddingLeft() + getPaddingRight(), ((FrameLayout.LayoutParams) child.getLayoutParams()).width), MeasureSpec.makeMeasureSpec((height - getPaddingTop()) - getPaddingBottom(), 1073741824));
            }
        }
    }

    public boolean dispatchKeyEvent(KeyEvent event) {
        return super.dispatchKeyEvent(event) || executeKeyEvent(event);
    }

    public boolean executeKeyEvent(KeyEvent event) {
        this.mTempRect.setEmpty();
        int i = 130;
        if (canScroll()) {
            boolean handled = false;
            if (event.getAction() == 0) {
                int keyCode = event.getKeyCode();
                if (keyCode != 62) {
                    switch (keyCode) {
                        case 19:
                            if (!event.isAltPressed()) {
                                handled = arrowScroll(33);
                                break;
                            }
                            handled = fullScroll(33);
                            break;
                        case 20:
                            if (!event.isAltPressed()) {
                                handled = arrowScroll(130);
                                break;
                            }
                            handled = fullScroll(130);
                            break;
                    }
                }
                if (event.isShiftPressed()) {
                    i = 33;
                }
                pageScroll(i);
            }
            return handled;
        }
        boolean z = false;
        if (!isFocused() || event.getKeyCode() == 4) {
            return false;
        }
        View currentFocused = findFocus();
        if (currentFocused == this) {
            currentFocused = null;
        }
        View nextFocused = FocusFinder.getInstance().findNextFocus(this, currentFocused, 130);
        if (!(nextFocused == null || nextFocused == this || !nextFocused.requestFocus(130))) {
            z = true;
        }
        return z;
    }

    private boolean inChild(int x, int y) {
        boolean z = false;
        if (getChildCount() <= 0) {
            return false;
        }
        int scrollY = getScrollY();
        View child = getChildAt(0);
        if (y >= child.getTop() - scrollY && y < child.getBottom() - scrollY && x >= child.getLeft() && x < child.getRight()) {
            z = true;
        }
        return z;
    }

    private void initOrResetVelocityTracker() {
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        } else {
            this.mVelocityTracker.clear();
        }
    }

    private void initVelocityTrackerIfNotExists() {
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
    }

    private void recycleVelocityTracker() {
        if (this.mVelocityTracker != null) {
            this.mVelocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        if (disallowIntercept) {
            recycleVelocityTracker();
        }
        super.requestDisallowInterceptTouchEvent(disallowIntercept);
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        MotionEvent motionEvent = ev;
        int action = ev.getAction();
        if (action == 2 && this.mIsBeingDragged) {
            return true;
        }
        int i = action & 255;
        if (i != 6) {
            switch (i) {
                case 0:
                    i = (int) ev.getY();
                    if (!inChild((int) ev.getX(), i)) {
                        this.mIsBeingDragged = false;
                        recycleVelocityTracker();
                        break;
                    }
                    this.mLastMotionY = i;
                    this.mActivePointerId = motionEvent.getPointerId(0);
                    initOrResetVelocityTracker();
                    this.mVelocityTracker.addMovement(motionEvent);
                    this.mScroller.computeScrollOffset();
                    this.mIsBeingDragged = true ^ this.mScroller.isFinished();
                    startNestedScroll(2, 0);
                    break;
                case 1:
                case 3:
                    this.mIsBeingDragged = false;
                    this.mActivePointerId = -1;
                    recycleVelocityTracker();
                    if (this.mScroller.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange())) {
                        ViewCompat.postInvalidateOnAnimation(this);
                    }
                    stopNestedScroll(0);
                    break;
                case 2:
                    i = this.mActivePointerId;
                    if (i != -1) {
                        int pointerIndex = motionEvent.findPointerIndex(i);
                        if (pointerIndex != -1) {
                            int y = (int) motionEvent.getY(pointerIndex);
                            if (Math.abs(y - this.mLastMotionY) > this.mTouchSlop && (2 & getNestedScrollAxes()) == 0) {
                                this.mIsBeingDragged = true;
                                this.mLastMotionY = y;
                                initVelocityTrackerIfNotExists();
                                this.mVelocityTracker.addMovement(motionEvent);
                                this.mNestedYOffset = 0;
                                ViewParent parent = getParent();
                                if (parent != null) {
                                    parent.requestDisallowInterceptTouchEvent(true);
                                    break;
                                }
                            }
                        }
                        String str = TAG;
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("Invalid pointerId=");
                        stringBuilder.append(i);
                        stringBuilder.append(" in onInterceptTouchEvent");
                        Log.e(str, stringBuilder.toString());
                        break;
                    }
                    break;
            }
        }
        onSecondaryPointerUp(ev);
        return this.mIsBeingDragged;
    }

    public boolean onTouchEvent(android.view.MotionEvent r27) {
        /*
        r26 = this;
        r10 = r26;
        r11 = r27;
        r26.initVelocityTrackerIfNotExists();
        r12 = android.view.MotionEvent.obtain(r27);
        r13 = r27.getActionMasked();
        r14 = 0;
        if (r13 != 0) goto L_0x0014;
    L_0x0012:
        r10.mNestedYOffset = r14;
    L_0x0014:
        r0 = r10.mNestedYOffset;
        r0 = (float) r0;
        r15 = 0;
        r12.offsetLocation(r15, r0);
        r0 = -1;
        r9 = 1;
        switch(r13) {
            case 0: goto L_0x0232;
            case 1: goto L_0x01f0;
            case 2: goto L_0x0070;
            case 3: goto L_0x0046;
            case 4: goto L_0x0020;
            case 5: goto L_0x0034;
            case 6: goto L_0x0023;
            default: goto L_0x0020;
        };
    L_0x0020:
        r2 = 1;
        goto L_0x026d;
    L_0x0023:
        r26.onSecondaryPointerUp(r27);
        r0 = r10.mActivePointerId;
        r0 = r11.findPointerIndex(r0);
        r0 = r11.getY(r0);
        r0 = (int) r0;
        r10.mLastMotionY = r0;
        goto L_0x0020;
    L_0x0034:
        r0 = r27.getActionIndex();
        r1 = r11.getY(r0);
        r1 = (int) r1;
        r10.mLastMotionY = r1;
        r1 = r11.getPointerId(r0);
        r10.mActivePointerId = r1;
        goto L_0x0020;
    L_0x0046:
        r1 = r10.mIsBeingDragged;
        if (r1 == 0) goto L_0x006a;
    L_0x004a:
        r1 = r26.getChildCount();
        if (r1 <= 0) goto L_0x006a;
    L_0x0050:
        r2 = r10.mScroller;
        r3 = r26.getScrollX();
        r4 = r26.getScrollY();
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = r26.getScrollRange();
        r1 = r2.springBack(r3, r4, r5, r6, r7, r8);
        if (r1 == 0) goto L_0x006a;
    L_0x0067:
        android.support.v4.view.ViewCompat.postInvalidateOnAnimation(r26);
    L_0x006a:
        r10.mActivePointerId = r0;
        r26.endDrag();
        goto L_0x0020;
    L_0x0070:
        r1 = r10.mActivePointerId;
        r8 = r11.findPointerIndex(r1);
        if (r8 != r0) goto L_0x0096;
    L_0x0078:
        r0 = "NestedScrollView";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Invalid pointerId=";
        r1.append(r2);
        r2 = r10.mActivePointerId;
        r1.append(r2);
        r2 = " in onTouchEvent";
        r1.append(r2);
        r1 = r1.toString();
        android.util.Log.e(r0, r1);
        goto L_0x0020;
    L_0x0096:
        r0 = r11.getY(r8);
        r7 = (int) r0;
        r0 = r10.mLastMotionY;
        r6 = r0 - r7;
        r1 = 0;
        r3 = r10.mScrollConsumed;
        r4 = r10.mScrollOffset;
        r5 = 0;
        r0 = r26;
        r2 = r6;
        r0 = r0.dispatchNestedPreScroll(r1, r2, r3, r4, r5);
        if (r0 == 0) goto L_0x00c4;
    L_0x00ae:
        r0 = r10.mScrollConsumed;
        r0 = r0[r9];
        r6 = r6 - r0;
        r0 = r10.mScrollOffset;
        r0 = r0[r9];
        r0 = (float) r0;
        r12.offsetLocation(r15, r0);
        r0 = r10.mNestedYOffset;
        r1 = r10.mScrollOffset;
        r1 = r1[r9];
        r0 = r0 + r1;
        r10.mNestedYOffset = r0;
    L_0x00c4:
        r0 = r10.mIsBeingDragged;
        if (r0 != 0) goto L_0x00e4;
    L_0x00c8:
        r0 = java.lang.Math.abs(r6);
        r1 = r10.mTouchSlop;
        if (r0 <= r1) goto L_0x00e4;
    L_0x00d0:
        r0 = r26.getParent();
        if (r0 == 0) goto L_0x00d9;
    L_0x00d6:
        r0.requestDisallowInterceptTouchEvent(r9);
    L_0x00d9:
        r10.mIsBeingDragged = r9;
        if (r6 <= 0) goto L_0x00e1;
    L_0x00dd:
        r1 = r10.mTouchSlop;
        r6 = r6 - r1;
        goto L_0x00e4;
    L_0x00e1:
        r1 = r10.mTouchSlop;
        r6 = r6 + r1;
    L_0x00e4:
        r0 = r10.mIsBeingDragged;
        if (r0 == 0) goto L_0x0020;
    L_0x00e8:
        r0 = r10.mScrollOffset;
        r0 = r0[r9];
        r0 = r7 - r0;
        r10.mLastMotionY = r0;
        r16 = r26.getScrollY();
        r5 = r26.getScrollRange();
        r4 = r26.getOverScrollMode();
        if (r4 == 0) goto L_0x0105;
    L_0x00fe:
        if (r4 != r9) goto L_0x0103;
    L_0x0100:
        if (r5 <= 0) goto L_0x0103;
    L_0x0102:
        goto L_0x0105;
    L_0x0103:
        r0 = 0;
        goto L_0x0106;
    L_0x0105:
        r0 = 1;
    L_0x0106:
        r17 = r0;
        r1 = 0;
        r3 = 0;
        r18 = r26.getScrollY();
        r19 = 0;
        r20 = 0;
        r21 = 0;
        r22 = 1;
        r0 = r26;
        r2 = r6;
        r23 = r4;
        r4 = r18;
        r24 = r5;
        r5 = r19;
        r15 = r6;
        r6 = r24;
        r18 = r7;
        r7 = r20;
        r25 = r8;
        r8 = r21;
        r9 = r22;
        r0 = r0.overScrollByCompat(r1, r2, r3, r4, r5, r6, r7, r8, r9);
        if (r0 == 0) goto L_0x013f;
    L_0x0134:
        r0 = r10.hasNestedScrollingParent(r14);
        if (r0 != 0) goto L_0x013f;
    L_0x013a:
        r0 = r10.mVelocityTracker;
        r0.clear();
    L_0x013f:
        r0 = r26.getScrollY();
        r7 = r0 - r16;
        r8 = r15 - r7;
        r1 = 0;
        r3 = 0;
        r5 = r10.mScrollOffset;
        r6 = 0;
        r0 = r26;
        r2 = r7;
        r4 = r8;
        r0 = r0.dispatchNestedScroll(r1, r2, r3, r4, r5, r6);
        if (r0 == 0) goto L_0x0176;
    L_0x0156:
        r0 = r10.mLastMotionY;
        r1 = r10.mScrollOffset;
        r2 = 1;
        r1 = r1[r2];
        r0 = r0 - r1;
        r10.mLastMotionY = r0;
        r0 = r10.mScrollOffset;
        r0 = r0[r2];
        r0 = (float) r0;
        r1 = 0;
        r12.offsetLocation(r1, r0);
        r0 = r10.mNestedYOffset;
        r1 = r10.mScrollOffset;
        r1 = r1[r2];
        r0 = r0 + r1;
        r10.mNestedYOffset = r0;
        r3 = r25;
        goto L_0x01ee;
    L_0x0176:
        r2 = 1;
        if (r17 == 0) goto L_0x01ec;
    L_0x0179:
        r26.ensureGlows();
        r6 = r16 + r15;
        if (r6 >= 0) goto L_0x01a8;
    L_0x0180:
        r0 = r10.mEdgeGlowTop;
        r1 = (float) r15;
        r3 = r26.getHeight();
        r3 = (float) r3;
        r1 = r1 / r3;
        r3 = r25;
        r4 = r11.getX(r3);
        r5 = r26.getWidth();
        r5 = (float) r5;
        r4 = r4 / r5;
        android.support.v4.widget.EdgeEffectCompat.onPull(r0, r1, r4);
        r0 = r10.mEdgeGlowBottom;
        r0 = r0.isFinished();
        if (r0 != 0) goto L_0x01a5;
    L_0x01a0:
        r0 = r10.mEdgeGlowBottom;
        r0.onRelease();
    L_0x01a5:
        r0 = r24;
        goto L_0x01d4;
    L_0x01a8:
        r3 = r25;
        r0 = r24;
        if (r6 <= r0) goto L_0x01d4;
    L_0x01ae:
        r1 = r10.mEdgeGlowBottom;
        r4 = (float) r15;
        r5 = r26.getHeight();
        r5 = (float) r5;
        r4 = r4 / r5;
        r5 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r9 = r11.getX(r3);
        r14 = r26.getWidth();
        r14 = (float) r14;
        r9 = r9 / r14;
        r5 = r5 - r9;
        android.support.v4.widget.EdgeEffectCompat.onPull(r1, r4, r5);
        r1 = r10.mEdgeGlowTop;
        r1 = r1.isFinished();
        if (r1 != 0) goto L_0x01d4;
    L_0x01cf:
        r1 = r10.mEdgeGlowTop;
        r1.onRelease();
    L_0x01d4:
        r1 = r10.mEdgeGlowTop;
        if (r1 == 0) goto L_0x01ee;
    L_0x01d8:
        r1 = r10.mEdgeGlowTop;
        r1 = r1.isFinished();
        if (r1 == 0) goto L_0x01e8;
    L_0x01e0:
        r1 = r10.mEdgeGlowBottom;
        r1 = r1.isFinished();
        if (r1 != 0) goto L_0x01ee;
    L_0x01e8:
        android.support.v4.view.ViewCompat.postInvalidateOnAnimation(r26);
        goto L_0x01ee;
    L_0x01ec:
        r3 = r25;
    L_0x01ee:
        goto L_0x026d;
    L_0x01f0:
        r2 = 1;
        r1 = r10.mVelocityTracker;
        r3 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r4 = r10.mMaximumVelocity;
        r4 = (float) r4;
        r1.computeCurrentVelocity(r3, r4);
        r3 = r10.mActivePointerId;
        r3 = r1.getYVelocity(r3);
        r3 = (int) r3;
        r4 = java.lang.Math.abs(r3);
        r5 = r10.mMinimumVelocity;
        if (r4 <= r5) goto L_0x020f;
    L_0x020a:
        r4 = -r3;
        r10.flingWithNestedDispatch(r4);
        goto L_0x022c;
    L_0x020f:
        r14 = r10.mScroller;
        r15 = r26.getScrollX();
        r16 = r26.getScrollY();
        r17 = 0;
        r18 = 0;
        r19 = 0;
        r20 = r26.getScrollRange();
        r4 = r14.springBack(r15, r16, r17, r18, r19, r20);
        if (r4 == 0) goto L_0x022c;
    L_0x0229:
        android.support.v4.view.ViewCompat.postInvalidateOnAnimation(r26);
    L_0x022c:
        r10.mActivePointerId = r0;
        r26.endDrag();
        goto L_0x026d;
    L_0x0232:
        r2 = 1;
        r0 = r26.getChildCount();
        if (r0 != 0) goto L_0x023a;
    L_0x0239:
        return r14;
    L_0x023a:
        r0 = r10.mScroller;
        r0 = r0.isFinished();
        r0 = r0 ^ r2;
        r10.mIsBeingDragged = r0;
        if (r0 == 0) goto L_0x024e;
    L_0x0245:
        r0 = r26.getParent();
        if (r0 == 0) goto L_0x024e;
    L_0x024b:
        r0.requestDisallowInterceptTouchEvent(r2);
    L_0x024e:
        r0 = r10.mScroller;
        r0 = r0.isFinished();
        if (r0 != 0) goto L_0x025b;
    L_0x0256:
        r0 = r10.mScroller;
        r0.abortAnimation();
    L_0x025b:
        r0 = r27.getY();
        r0 = (int) r0;
        r10.mLastMotionY = r0;
        r0 = r11.getPointerId(r14);
        r10.mActivePointerId = r0;
        r0 = 2;
        r10.startNestedScroll(r0, r14);
    L_0x026d:
        r0 = r10.mVelocityTracker;
        if (r0 == 0) goto L_0x0276;
    L_0x0271:
        r0 = r10.mVelocityTracker;
        r0.addMovement(r12);
    L_0x0276:
        r12.recycle();
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.widget.NestedScrollView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    private void onSecondaryPointerUp(MotionEvent ev) {
        int pointerIndex = ev.getActionIndex();
        if (ev.getPointerId(pointerIndex) == this.mActivePointerId) {
            int newPointerIndex = pointerIndex == 0 ? 1 : 0;
            this.mLastMotionY = (int) ev.getY(newPointerIndex);
            this.mActivePointerId = ev.getPointerId(newPointerIndex);
            if (this.mVelocityTracker != null) {
                this.mVelocityTracker.clear();
            }
        }
    }

    public boolean onGenericMotionEvent(MotionEvent event) {
        if (!((event.getSource() & 2) == 0 || event.getAction() != 8 || this.mIsBeingDragged)) {
            float vscroll = event.getAxisValue(1.3E-44f);
            if (vscroll != 0.0f) {
                int delta = (int) (getVerticalScrollFactorCompat() * vscroll);
                int range = getScrollRange();
                int oldScrollY = getScrollY();
                int newScrollY = oldScrollY - delta;
                if (newScrollY < 0) {
                    newScrollY = 0;
                } else if (newScrollY > range) {
                    newScrollY = range;
                }
                if (newScrollY != oldScrollY) {
                    super.scrollTo(getScrollX(), newScrollY);
                    return true;
                }
            }
        }
        return false;
    }

    private float getVerticalScrollFactorCompat() {
        if (this.mVerticalScrollFactor == 0.0f) {
            TypedValue outValue = new TypedValue();
            Context context = getContext();
            if (context.getTheme().resolveAttribute(16842829, outValue, true)) {
                this.mVerticalScrollFactor = outValue.getDimension(context.getResources().getDisplayMetrics());
            } else {
                throw new IllegalStateException("Expected theme to define listPreferredItemHeight.");
            }
        }
        return this.mVerticalScrollFactor;
    }

    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        super.scrollTo(scrollX, scrollY);
    }

    boolean overScrollByCompat(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        int maxOverScrollX2;
        int maxOverScrollY2;
        int overScrollMode = getOverScrollMode();
        boolean canScrollHorizontal = computeHorizontalScrollRange() > computeHorizontalScrollExtent();
        boolean clampedX = computeVerticalScrollRange() > computeVerticalScrollExtent();
        boolean overScrollHorizontal = overScrollMode == 0 || (overScrollMode == 1 && canScrollHorizontal);
        boolean overScrollVertical = overScrollMode == 0 || (overScrollMode == 1 && clampedX);
        int newScrollX = scrollX + deltaX;
        if (overScrollHorizontal) {
            maxOverScrollX2 = maxOverScrollX;
        } else {
            maxOverScrollX2 = 0;
        }
        int newScrollY = scrollY + deltaY;
        if (overScrollVertical) {
            maxOverScrollY2 = maxOverScrollY;
        } else {
            maxOverScrollY2 = 0;
        }
        int left = -maxOverScrollX2;
        int right = maxOverScrollX2 + scrollRangeX;
        overScrollMode = -maxOverScrollY2;
        int bottom = maxOverScrollY2 + scrollRangeY;
        boolean clampedX2 = false;
        if (newScrollX > right) {
            newScrollX = right;
            clampedX2 = true;
        } else if (newScrollX < left) {
            newScrollX = left;
            clampedX2 = true;
        }
        boolean canScrollVertical = clampedX;
        clampedX = clampedX2;
        clampedX2 = false;
        if (newScrollY > bottom) {
            newScrollY = bottom;
            clampedX2 = true;
        } else if (newScrollY < overScrollMode) {
            newScrollY = overScrollMode;
            clampedX2 = true;
        }
        int newScrollY2 = newScrollY;
        boolean clampedY = clampedX2;
        if (clampedY) {
            if (!hasNestedScrollingParent(1)) {
                this.mScroller.springBack(newScrollX, newScrollY2, 0, 0, 0, getScrollRange());
            }
        }
        onOverScrolled(newScrollX, newScrollY2, clampedX, clampedY);
        return clampedX || clampedY;
    }

    int getScrollRange() {
        if (getChildCount() > 0) {
            return Math.max(0, getChildAt(0).getHeight() - ((getHeight() - getPaddingBottom()) - getPaddingTop()));
        }
        return 0;
    }

    private View findFocusableViewInBounds(boolean topFocus, int top, int bottom) {
        List<View> focusables = getFocusables(2);
        int count = focusables.size();
        boolean foundFullyContainedFocusable = false;
        View focusCandidate = null;
        for (int i = 0; i < count; i++) {
            View view = (View) focusables.get(i);
            int viewTop = view.getTop();
            int viewBottom = view.getBottom();
            if (top < viewBottom && viewTop < bottom) {
                boolean viewIsCloserToBoundary = true;
                boolean viewIsFullyContained = top < viewTop && viewBottom < bottom;
                if (focusCandidate == null) {
                    focusCandidate = view;
                    foundFullyContainedFocusable = viewIsFullyContained;
                } else {
                    if ((!topFocus || viewTop >= focusCandidate.getTop()) && (topFocus || viewBottom <= focusCandidate.getBottom())) {
                        viewIsCloserToBoundary = false;
                    }
                    if (foundFullyContainedFocusable) {
                        if (viewIsFullyContained && viewIsCloserToBoundary) {
                            focusCandidate = view;
                        }
                    } else if (viewIsFullyContained) {
                        focusCandidate = view;
                        foundFullyContainedFocusable = true;
                    } else if (viewIsCloserToBoundary) {
                        focusCandidate = view;
                    }
                }
            }
        }
        return focusCandidate;
    }

    public boolean pageScroll(int direction) {
        boolean down = direction == 130;
        int height = getHeight();
        if (down) {
            this.mTempRect.top = getScrollY() + height;
            int count = getChildCount();
            if (count > 0) {
                View view = getChildAt(count - 1);
                if (this.mTempRect.top + height > view.getBottom()) {
                    this.mTempRect.top = view.getBottom() - height;
                }
            }
        } else {
            this.mTempRect.top = getScrollY() - height;
            if (this.mTempRect.top < 0) {
                this.mTempRect.top = 0;
            }
        }
        this.mTempRect.bottom = this.mTempRect.top + height;
        return scrollAndFocus(direction, this.mTempRect.top, this.mTempRect.bottom);
    }

    public boolean fullScroll(int direction) {
        boolean down = direction == 130;
        int height = getHeight();
        this.mTempRect.top = 0;
        this.mTempRect.bottom = height;
        if (down) {
            int count = getChildCount();
            if (count > 0) {
                this.mTempRect.bottom = getChildAt(count - 1).getBottom() + getPaddingBottom();
                this.mTempRect.top = this.mTempRect.bottom - height;
            }
        }
        return scrollAndFocus(direction, this.mTempRect.top, this.mTempRect.bottom);
    }

    private boolean scrollAndFocus(int direction, int top, int bottom) {
        boolean handled = true;
        int height = getHeight();
        int containerTop = getScrollY();
        int containerBottom = containerTop + height;
        boolean up = direction == 33;
        View newFocused = findFocusableViewInBounds(up, top, bottom);
        if (newFocused == null) {
            newFocused = this;
        }
        if (top < containerTop || bottom > containerBottom) {
            doScrollY(up ? top - containerTop : bottom - containerBottom);
        } else {
            handled = false;
        }
        if (newFocused != findFocus()) {
            newFocused.requestFocus(direction);
        }
        return handled;
    }

    public boolean arrowScroll(int direction) {
        int scrollDelta;
        View currentFocused = findFocus();
        if (currentFocused == this) {
            currentFocused = null;
        }
        View nextFocused = FocusFinder.getInstance().findNextFocus(this, currentFocused, direction);
        int maxJump = getMaxScrollAmount();
        if (nextFocused == null || !isWithinDeltaOfScreen(nextFocused, maxJump, getHeight())) {
            scrollDelta = maxJump;
            if (direction == 33 && getScrollY() < scrollDelta) {
                scrollDelta = getScrollY();
            } else if (direction == 130 && getChildCount() > 0) {
                int daBottom = getChildAt(0).getBottom();
                int screenBottom = (getScrollY() + getHeight()) - getPaddingBottom();
                if (daBottom - screenBottom < maxJump) {
                    scrollDelta = daBottom - screenBottom;
                }
            }
            if (scrollDelta == 0) {
                return false;
            }
            doScrollY(direction == 130 ? scrollDelta : -scrollDelta);
        } else {
            nextFocused.getDrawingRect(this.mTempRect);
            offsetDescendantRectToMyCoords(nextFocused, this.mTempRect);
            doScrollY(computeScrollDeltaToGetChildRectOnScreen(this.mTempRect));
            nextFocused.requestFocus(direction);
        }
        if (currentFocused != null && currentFocused.isFocused() && isOffScreen(currentFocused)) {
            scrollDelta = getDescendantFocusability();
            setDescendantFocusability(131072);
            requestFocus();
            setDescendantFocusability(scrollDelta);
        }
        return true;
    }

    private boolean isOffScreen(View descendant) {
        return isWithinDeltaOfScreen(descendant, 0, getHeight()) ^ 1;
    }

    private boolean isWithinDeltaOfScreen(View descendant, int delta, int height) {
        descendant.getDrawingRect(this.mTempRect);
        offsetDescendantRectToMyCoords(descendant, this.mTempRect);
        return this.mTempRect.bottom + delta >= getScrollY() && this.mTempRect.top - delta <= getScrollY() + height;
    }

    private void doScrollY(int delta) {
        if (delta == 0) {
            return;
        }
        if (this.mSmoothScrollingEnabled) {
            smoothScrollBy(0, delta);
        } else {
            scrollBy(0, delta);
        }
    }

    public final void smoothScrollBy(int dx, int dy) {
        if (getChildCount() != 0) {
            if (AnimationUtils.currentAnimationTimeMillis() - this.mLastScroll > 250) {
                int maxY = Math.max(0, getChildAt(0).getHeight() - ((getHeight() - getPaddingBottom()) - getPaddingTop()));
                int scrollY = getScrollY();
                this.mScroller.startScroll(getScrollX(), scrollY, 0, Math.max(0, Math.min(scrollY + dy, maxY)) - scrollY);
                ViewCompat.postInvalidateOnAnimation(this);
            } else {
                if (!this.mScroller.isFinished()) {
                    this.mScroller.abortAnimation();
                }
                scrollBy(dx, dy);
            }
            this.mLastScroll = AnimationUtils.currentAnimationTimeMillis();
        }
    }

    public final void smoothScrollTo(int x, int y) {
        smoothScrollBy(x - getScrollX(), y - getScrollY());
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public int computeVerticalScrollRange() {
        int contentHeight = (getHeight() - getPaddingBottom()) - getPaddingTop();
        if (getChildCount() == 0) {
            return contentHeight;
        }
        int scrollRange = getChildAt(0).getBottom();
        int scrollY = getScrollY();
        int overscrollBottom = Math.max(0, scrollRange - contentHeight);
        if (scrollY < 0) {
            scrollRange -= scrollY;
        } else if (scrollY > overscrollBottom) {
            scrollRange += scrollY - overscrollBottom;
        }
        return scrollRange;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public int computeVerticalScrollOffset() {
        return Math.max(0, super.computeVerticalScrollOffset());
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public int computeVerticalScrollExtent() {
        return super.computeVerticalScrollExtent();
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public int computeHorizontalScrollRange() {
        return super.computeHorizontalScrollRange();
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public int computeHorizontalScrollOffset() {
        return super.computeHorizontalScrollOffset();
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public int computeHorizontalScrollExtent() {
        return super.computeHorizontalScrollExtent();
    }

    protected void measureChild(View child, int parentWidthMeasureSpec, int parentHeightMeasureSpec) {
        child.measure(getChildMeasureSpec(parentWidthMeasureSpec, getPaddingLeft() + getPaddingRight(), child.getLayoutParams().width), MeasureSpec.makeMeasureSpec(0, 0));
    }

    protected void measureChildWithMargins(View child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
        MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
        child.measure(getChildMeasureSpec(parentWidthMeasureSpec, (((getPaddingLeft() + getPaddingRight()) + lp.leftMargin) + lp.rightMargin) + widthUsed, lp.width), MeasureSpec.makeMeasureSpec(lp.topMargin + lp.bottomMargin, 0));
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0082  */
    /* JADX WARNING: Missing block: B:13:0x0072, code:
            if (r1 > 0) goto L_0x007c;
     */
    public void computeScroll() {
        /*
        r20 = this;
        r10 = r20;
        r0 = r10.mScroller;
        r0 = r0.computeScrollOffset();
        r12 = 1;
        if (r0 == 0) goto L_0x00ac;
    L_0x000b:
        r0 = r10.mScroller;
        r13 = r0.getCurrX();
        r0 = r10.mScroller;
        r14 = r0.getCurrY();
        r0 = r10.mLastScrollerY;
        r6 = r14 - r0;
        r1 = 0;
        r3 = r10.mScrollConsumed;
        r4 = 0;
        r5 = 1;
        r0 = r20;
        r2 = r6;
        r0 = r0.dispatchNestedPreScroll(r1, r2, r3, r4, r5);
        if (r0 == 0) goto L_0x002e;
    L_0x0029:
        r0 = r10.mScrollConsumed;
        r0 = r0[r12];
        r6 = r6 - r0;
    L_0x002e:
        r15 = r6;
        if (r15 == 0) goto L_0x00a6;
    L_0x0031:
        r9 = r20.getScrollRange();
        r8 = r20.getScrollY();
        r1 = 0;
        r3 = r20.getScrollX();
        r5 = 0;
        r7 = 0;
        r16 = 0;
        r17 = 0;
        r0 = r20;
        r2 = r15;
        r4 = r8;
        r6 = r9;
        r11 = r8;
        r8 = r16;
        r19 = r9;
        r9 = r17;
        r0.overScrollByCompat(r1, r2, r3, r4, r5, r6, r7, r8, r9);
        r0 = r20.getScrollY();
        r7 = r0 - r11;
        r8 = r15 - r7;
        r3 = 0;
        r5 = 0;
        r6 = 1;
        r0 = r20;
        r2 = r7;
        r4 = r8;
        r0 = r0.dispatchNestedScroll(r1, r2, r3, r4, r5, r6);
        if (r0 != 0) goto L_0x00a6;
    L_0x0068:
        r0 = r20.getOverScrollMode();
        if (r0 == 0) goto L_0x007a;
    L_0x006e:
        if (r0 != r12) goto L_0x0075;
    L_0x0070:
        r1 = r19;
        if (r1 <= 0) goto L_0x0077;
    L_0x0074:
        goto L_0x007c;
    L_0x0075:
        r1 = r19;
    L_0x0077:
        r18 = 0;
        goto L_0x007e;
    L_0x007a:
        r1 = r19;
    L_0x007c:
        r18 = 1;
    L_0x007e:
        r2 = r18;
        if (r2 == 0) goto L_0x00a6;
    L_0x0082:
        r20.ensureGlows();
        if (r14 > 0) goto L_0x0096;
    L_0x0087:
        if (r11 <= 0) goto L_0x0096;
    L_0x0089:
        r3 = r10.mEdgeGlowTop;
        r4 = r10.mScroller;
        r4 = r4.getCurrVelocity();
        r4 = (int) r4;
        r3.onAbsorb(r4);
        goto L_0x00a6;
    L_0x0096:
        if (r14 < r1) goto L_0x00a6;
    L_0x0098:
        if (r11 >= r1) goto L_0x00a6;
    L_0x009a:
        r3 = r10.mEdgeGlowBottom;
        r4 = r10.mScroller;
        r4 = r4.getCurrVelocity();
        r4 = (int) r4;
        r3.onAbsorb(r4);
    L_0x00a6:
        r10.mLastScrollerY = r14;
        android.support.v4.view.ViewCompat.postInvalidateOnAnimation(r20);
        goto L_0x00b8;
    L_0x00ac:
        r0 = r10.hasNestedScrollingParent(r12);
        if (r0 == 0) goto L_0x00b5;
    L_0x00b2:
        r10.stopNestedScroll(r12);
    L_0x00b5:
        r0 = 0;
        r10.mLastScrollerY = r0;
    L_0x00b8:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.widget.NestedScrollView.computeScroll():void");
    }

    private void scrollToChild(View child) {
        child.getDrawingRect(this.mTempRect);
        offsetDescendantRectToMyCoords(child, this.mTempRect);
        int scrollDelta = computeScrollDeltaToGetChildRectOnScreen(this.mTempRect);
        if (scrollDelta != 0) {
            scrollBy(0, scrollDelta);
        }
    }

    private boolean scrollToChildRect(Rect rect, boolean immediate) {
        int delta = computeScrollDeltaToGetChildRectOnScreen(rect);
        boolean scroll = delta != 0;
        if (scroll) {
            if (immediate) {
                scrollBy(0, delta);
            } else {
                smoothScrollBy(0, delta);
            }
        }
        return scroll;
    }

    protected int computeScrollDeltaToGetChildRectOnScreen(Rect rect) {
        if (getChildCount() == 0) {
            return 0;
        }
        int height = getHeight();
        int screenTop = getScrollY();
        int screenBottom = screenTop + height;
        int fadingEdge = getVerticalFadingEdgeLength();
        if (rect.top > 0) {
            screenTop += fadingEdge;
        }
        if (rect.bottom < getChildAt(0).getHeight()) {
            screenBottom -= fadingEdge;
        }
        int scrollYDelta = 0;
        if (rect.bottom > screenBottom && rect.top > screenTop) {
            if (rect.height() > height) {
                scrollYDelta = 0 + (rect.top - screenTop);
            } else {
                scrollYDelta = 0 + (rect.bottom - screenBottom);
            }
            scrollYDelta = Math.min(scrollYDelta, getChildAt(0).getBottom() - screenBottom);
        } else if (rect.top < screenTop && rect.bottom < screenBottom) {
            if (rect.height() > height) {
                scrollYDelta = 0 - (screenBottom - rect.bottom);
            } else {
                scrollYDelta = 0 - (screenTop - rect.top);
            }
            scrollYDelta = Math.max(scrollYDelta, -getScrollY());
        }
        return scrollYDelta;
    }

    public void requestChildFocus(View child, View focused) {
        if (this.mIsLayoutDirty) {
            this.mChildToScrollTo = focused;
        } else {
            scrollToChild(focused);
        }
        super.requestChildFocus(child, focused);
    }

    protected boolean onRequestFocusInDescendants(int direction, Rect previouslyFocusedRect) {
        View nextFocus;
        if (direction == 2) {
            direction = 130;
        } else if (direction == 1) {
            direction = 33;
        }
        if (previouslyFocusedRect == null) {
            nextFocus = FocusFinder.getInstance().findNextFocus(this, null, direction);
        } else {
            nextFocus = FocusFinder.getInstance().findNextFocusFromRect(this, previouslyFocusedRect, direction);
        }
        if (nextFocus == null || isOffScreen(nextFocus)) {
            return false;
        }
        return nextFocus.requestFocus(direction, previouslyFocusedRect);
    }

    public boolean requestChildRectangleOnScreen(View child, Rect rectangle, boolean immediate) {
        rectangle.offset(child.getLeft() - child.getScrollX(), child.getTop() - child.getScrollY());
        return scrollToChildRect(rectangle, immediate);
    }

    public void requestLayout() {
        this.mIsLayoutDirty = true;
        super.requestLayout();
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        this.mIsLayoutDirty = false;
        if (this.mChildToScrollTo != null && isViewDescendantOf(this.mChildToScrollTo, this)) {
            scrollToChild(this.mChildToScrollTo);
        }
        this.mChildToScrollTo = null;
        if (!this.mIsLaidOut) {
            if (this.mSavedState != null) {
                scrollTo(getScrollX(), this.mSavedState.scrollPosition);
                this.mSavedState = null;
            }
            int scrollRange = Math.max(0, (getChildCount() > 0 ? getChildAt(0).getMeasuredHeight() : 0) - (((b - t) - getPaddingBottom()) - getPaddingTop()));
            if (getScrollY() > scrollRange) {
                scrollTo(getScrollX(), scrollRange);
            } else if (getScrollY() < 0) {
                scrollTo(getScrollX(), 0);
            }
        }
        scrollTo(getScrollX(), getScrollY());
        this.mIsLaidOut = true;
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mIsLaidOut = false;
    }

    /* JADX WARNING: Missing block: B:7:0x0027, code:
            return;
     */
    protected void onSizeChanged(int r3, int r4, int r5, int r6) {
        /*
        r2 = this;
        super.onSizeChanged(r3, r4, r5, r6);
        r0 = r2.findFocus();
        if (r0 == 0) goto L_0x0027;
    L_0x0009:
        if (r2 != r0) goto L_0x000c;
    L_0x000b:
        goto L_0x0027;
    L_0x000c:
        r1 = 0;
        r1 = r2.isWithinDeltaOfScreen(r0, r1, r6);
        if (r1 == 0) goto L_0x0026;
    L_0x0013:
        r1 = r2.mTempRect;
        r0.getDrawingRect(r1);
        r1 = r2.mTempRect;
        r2.offsetDescendantRectToMyCoords(r0, r1);
        r1 = r2.mTempRect;
        r1 = r2.computeScrollDeltaToGetChildRectOnScreen(r1);
        r2.doScrollY(r1);
    L_0x0026:
        return;
    L_0x0027:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.widget.NestedScrollView.onSizeChanged(int, int, int, int):void");
    }

    private static boolean isViewDescendantOf(View child, View parent) {
        boolean z = true;
        if (child == parent) {
            return true;
        }
        ViewParent theParent = child.getParent();
        if (!((theParent instanceof ViewGroup) && isViewDescendantOf((View) theParent, parent))) {
            z = false;
        }
        return z;
    }

    public void fling(int velocityY) {
        if (getChildCount() > 0) {
            startNestedScroll(2, 1);
            this.mScroller.fling(getScrollX(), getScrollY(), 0, velocityY, 0, 0, Integer.MIN_VALUE, ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, 0, 0);
            this.mLastScrollerY = getScrollY();
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    private void flingWithNestedDispatch(int velocityY) {
        int scrollY = getScrollY();
        boolean canFling = (scrollY > 0 || velocityY > 0) && (scrollY < getScrollRange() || velocityY < 0);
        if (!dispatchNestedPreFling(0.0f, (float) velocityY)) {
            dispatchNestedFling(0.0f, (float) velocityY, canFling);
            fling(velocityY);
        }
    }

    private void endDrag() {
        this.mIsBeingDragged = false;
        recycleVelocityTracker();
        stopNestedScroll(0);
        if (this.mEdgeGlowTop != null) {
            this.mEdgeGlowTop.onRelease();
            this.mEdgeGlowBottom.onRelease();
        }
    }

    public void scrollTo(int x, int y) {
        if (getChildCount() > 0) {
            View child = getChildAt(null);
            x = clamp(x, (getWidth() - getPaddingRight()) - getPaddingLeft(), child.getWidth());
            y = clamp(y, (getHeight() - getPaddingBottom()) - getPaddingTop(), child.getHeight());
            if (x != getScrollX() || y != getScrollY()) {
                super.scrollTo(x, y);
            }
        }
    }

    private void ensureGlows() {
        if (getOverScrollMode() == 2) {
            this.mEdgeGlowTop = null;
            this.mEdgeGlowBottom = null;
        } else if (this.mEdgeGlowTop == null) {
            Context context = getContext();
            this.mEdgeGlowTop = new EdgeEffect(context);
            this.mEdgeGlowBottom = new EdgeEffect(context);
        }
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.mEdgeGlowTop != null) {
            int restoreCount;
            int width;
            int scrollY = getScrollY();
            if (!this.mEdgeGlowTop.isFinished()) {
                restoreCount = canvas.save();
                width = (getWidth() - getPaddingLeft()) - getPaddingRight();
                canvas.translate((float) getPaddingLeft(), (float) Math.min(0, scrollY));
                this.mEdgeGlowTop.setSize(width, getHeight());
                if (this.mEdgeGlowTop.draw(canvas)) {
                    ViewCompat.postInvalidateOnAnimation(this);
                }
                canvas.restoreToCount(restoreCount);
            }
            if (!this.mEdgeGlowBottom.isFinished()) {
                restoreCount = canvas.save();
                width = (getWidth() - getPaddingLeft()) - getPaddingRight();
                int height = getHeight();
                canvas.translate((float) ((-width) + getPaddingLeft()), (float) (Math.max(getScrollRange(), scrollY) + height));
                canvas.rotate(180.0f, (float) width, 0.0f);
                this.mEdgeGlowBottom.setSize(width, height);
                if (this.mEdgeGlowBottom.draw(canvas)) {
                    ViewCompat.postInvalidateOnAnimation(this);
                }
                canvas.restoreToCount(restoreCount);
            }
        }
    }

    private static int clamp(int n, int my, int child) {
        if (my >= child || n < 0) {
            return 0;
        }
        if (my + n > child) {
            return child - my;
        }
        return n;
    }

    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof SavedState) {
            SavedState ss = (SavedState) state;
            super.onRestoreInstanceState(ss.getSuperState());
            this.mSavedState = ss;
            requestLayout();
            return;
        }
        super.onRestoreInstanceState(state);
    }

    protected Parcelable onSaveInstanceState() {
        SavedState ss = new SavedState(super.onSaveInstanceState());
        ss.scrollPosition = getScrollY();
        return ss;
    }
}
