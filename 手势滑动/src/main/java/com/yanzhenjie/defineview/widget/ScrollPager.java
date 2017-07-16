/*
 * Copyright © Yan Zhenjie. All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yanzhenjie.defineview.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by Yan Zhenjie on 2016/11/5.
 */
public class ScrollPager extends ViewGroup {

    private Scroller mScroller;

    // 手指每次移动时需要更新xy，记录上次手指所处的坐标。
    private float mLastX;

    public ScrollPager(Context context) {
        this(context, null, 0);
    }

    public ScrollPager(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new Scroller(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getRawX();

        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (!mScroller.isFinished()) { // 如果上次的调用没有执行完就取消。
                    mScroller.abortAnimation();
                }
                mLastX = x;
                return true;
            case MotionEvent.ACTION_MOVE:
                int dxMove = (int) (mLastX - x);
                scrollBy(dxMove, 0);
                mLastX = x;
                return true;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL: {
                // 当手指抬起时，第几屏占的比例大就去第几屏。(这里在除的时候+view一半宽是因为滑动到0.6的时候，不到1，结果就是0
                // 其实按照惯性应该是1，所以我们给它补上一般的屏，这样相当于4设5入。)
                int sonIndex = (getScrollX() + getWidth() / 2) / getWidth();

                // 如果滑动页面超过当前页面数，那么把屏index定为最大页面数的index。
                int childCount = getChildCount();
                if (sonIndex >= childCount)
                    sonIndex = childCount - 1;

                // 现在滑动的相对距离。
                int dx = sonIndex * getWidth() - getScrollX();
                // Y方向不变，X方向到目的地。
                mScroller.startScroll(getScrollX(), 0, dx, 0, 500);
                invalidate();
                break;
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        // 在Layout 子view之前测量子view大小，在onLayout的时候才能调用getMeasuredWidth()和getMeasuredHeight()。
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childView = getChildAt(i);
                int childW = childView.getMeasuredWidth();

                // 把所有子view放在水平方向，依次排开。
                // left:  0, w, 2w, 3w..
                // top:   0...
                // right: w, 2w, 3w...
                // topL   h...
                childView.layout(i * childW, 0, childW * i + childW, childView.getMeasuredHeight());
            }
        }
    }
}
