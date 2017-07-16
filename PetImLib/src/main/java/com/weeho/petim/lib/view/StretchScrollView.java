package com.weeho.petim.lib.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

/**
 * @describe:阻尼效果的ScrollView
 * @author:wangkui
 * @time:2014-11-4上午10:53:53
 */
public class StretchScrollView extends ScrollView {
    // ScrollView的子布局
    private View childView;
    private static final int DEFAULT_POSITION = -1;
    // 点击Y轴的坐标
    private float yDown = DEFAULT_POSITION;
    // 定义一个矩形，来存储布局正常时候的位置
    private Rect originalLayout = new Rect();
    int distanceY;

    public StretchScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        if (getChildCount() > 0) {
            childView = getChildAt(0);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (childView == null) {
            return super.onTouchEvent(ev);
        } else {
            commOnTouchEvent(ev);
        }

        return super.onTouchEvent(ev);
    }

    /**
     * @describe:处理触摸事件
     * @author:wangkui
     */
    public void commOnTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                /**
                 * 按下的时候，获取Y轴的出发坐标
                 */
                yDown = ev.getY();
                break;
            case MotionEvent.ACTION_UP:

                if (isNeedAnimation()) {
                    animation();
                }

                yDown = DEFAULT_POSITION;
                break;

            case MotionEvent.ACTION_MOVE:
                float nowY = 0;
                float preY = yDown;
                nowY = ev.getY();
                if (isDefaultPosition(yDown)) {
                    preY = nowY;
                }
                distanceY = (int) (preY - nowY);
                yDown = nowY;
                /**
                 * 判断是否需要移动
                 */
                if (isNeedMove()) {
                    if (originalLayout.isEmpty()) {
                        // 保存正常的布局位置
                        originalLayout.set(childView.getLeft(), childView.getTop(),
                                childView.getRight(), childView.getBottom());
                    }
                    // 移动布局
                    childView.layout(childView.getLeft(), childView.getTop()
                                    - distanceY / 2, childView.getRight(),
                            childView.getBottom() - distanceY / 2);
                }
                break;
            default:
                break;
        }
    }

    /**
     * @describe:使用移动动画
     * @author:wangkui
     */

    public void animation() {
        // 开启移动动画
        TranslateAnimation ta = new TranslateAnimation(0, 0,
                childView.getTop(), originalLayout.top);
        ta.setDuration(200);
        ta.setInterpolator(new AccelerateDecelerateInterpolator());
        ta.setFillAfter(true);
        childView.startAnimation(ta);
        // 设置回到正常的布局位置
        childView.layout(originalLayout.left, originalLayout.top,
                originalLayout.right, originalLayout.bottom);
        originalLayout.setEmpty();
    }

    /**
     * @describe:判断是否需要开启动画
     * @author:wangkui
     * @time:2014-9-3下午4:49:26
     */
    public boolean isNeedAnimation() {
        return !originalLayout.isEmpty();
    }

    /**
     * @describe:判断是否需要移动
     * @author:wangkui
     */
    public boolean isNeedMove() {
        int offset = childView.getMeasuredHeight() - getHeight();
        int scrollY = getScrollY();
        if (scrollY == 0 || scrollY == offset) {
            return true;
        }
        return false;
    }

    /**
     * @describe:检测Y坐标是否处于默认位置
     * @author:wangkui
     */
    private boolean isDefaultPosition(float position) {
        return position == DEFAULT_POSITION;
    }

}
