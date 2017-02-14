package core.base;

import android.content.Context;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class ScrollScaleFABBehavior extends FloatingActionButton.Behavior {

    private OnStateChangedListener mOnStateChangedListener;

    public ScrollScaleFABBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(final CoordinatorLayout coordinatorLayout, final FloatingActionButton child,
                                       final View directTargetChild, final View target, final int nestedScrollAxes) {
        // Ensure we react to vertical scrolling
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL
                || super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }

    @Override
    public void onNestedScroll(final CoordinatorLayout coordinatorLayout, final FloatingActionButton child,
                               final View target, final int dxConsumed, final int dyConsumed,
                               final int dxUnconsumed, final int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        if (dyConsumed > 0 && child.getVisibility() == View.VISIBLE) {
            // User scrolled down and the FAB is currently visible -> hide the FAB
            child.hide();
            if (null != mOnStateChangedListener)
                mOnStateChangedListener.onChanged(false);
        } else if (dyConsumed < 0 && child.getVisibility() != View.VISIBLE) {
            // User scrolled up and the FAB is currently not visible -> show the FAB
            child.show();
            if (null != mOnStateChangedListener)
                mOnStateChangedListener.onChanged(true);
        }
    }

    /**
     * A utility function to get the {@link BottomSheetBehavior} associated with the {@code view}.
     *
     * @param view The {@link View} with {@link BottomSheetBehavior}.
     * @return The {@link BottomSheetBehavior} associated with the {@code view}.
     */
    @SuppressWarnings("unchecked")
    public static <V extends View> ScrollScaleFABBehavior from(V view) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (!(params instanceof CoordinatorLayout.LayoutParams)) {
            throw new IllegalArgumentException("The view is not PtrRefreshListenner child of CoordinatorLayout");
        }
        CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) params).getBehavior();
        if (!(behavior instanceof ScrollScaleFABBehavior)) {
            throw new IllegalArgumentException("The view is not associated with ScrollScaleFABBehavior");
        }
        return (ScrollScaleFABBehavior) behavior;
    }

    public void setOnStateChangedListener(OnStateChangedListener mOnStateChangedListener) {
        this.mOnStateChangedListener = mOnStateChangedListener;
    }

    public interface OnStateChangedListener {
        void onChanged(boolean isShow);
    }

}