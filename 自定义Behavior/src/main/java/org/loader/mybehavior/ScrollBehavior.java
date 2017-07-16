package org.loader.mybehavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.View;

/**
 * 我们做一个总结，Behavior可以代理哪些行为呢？

 1.Measure和Layout的布局行为。

 2.onTouchEvent和onInterceptTouchEvent的触摸行为。比如design包中的SwipeDismissBehavior就是通过这样的方式完成的。

 3.嵌套滑动行为(NestedScrollingParent和NestedScrollingChild中的逻辑)。

 4.子View间的依赖行为。


 */
public class ScrollBehavior extends CoordinatorLayout.Behavior<View> {

    public ScrollBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * //
     * 来定义你所感兴趣的嵌套滚动（方向）。你将收到滚动的轴（比如横向或者纵向－让它可以轻易的忽略某个方向上的滚动）并且为了接收那个方向上的后续滚动事件必须返回true。
     * 1.判断滑动的方向 我们需要垂直滑动
     * @param coordinatorLayout
     * @param child
     * @param directTargetChild
     * @param target
     * @param nestedScrollAxes
     * @return
     */
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    /**
     * 会在scrolling View获得滚动事件前调用，它允许你消费部分或者全部的事件信息
     * @param coordinatorLayout
     * @param child
     * @param target
     * @param dx
     * @param dy
     * @param consumed
     */

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        int leftScrolled = target.getScrollY();
        child.setScrollY(leftScrolled);
    }

    /**
     * 会在scrolling View做完滚动后调用，通过回调可以知道scrolling view滚动了多少和它没有消耗的滚动事件。
     * @param coordinatorLayout
     * @param child
     * @param target
     * @param dxConsumed
     * @param dyConsumed
     * @param dxUnconsumed
     * @param dyUnconsumed
     */
    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    }

    /**
     * 当嵌套滚动（或者flinging）结束，你将得到一个onStopNestedScroll()回调。这标志着滚动的结束 － 迎接在下一个滚动之前的onStartNestedScroll() 调用。
     * @param coordinatorLayout
     * @param child
     * @param target
     */

    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target) {
        super.onStopNestedScroll(coordinatorLayout, child, target);
    }

    @Override
    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, View child, View target, float velocityX, float velocityY, boolean consumed) {
        ((NestedScrollView) child).fling((int) velocityY);
        return true;
    }

}