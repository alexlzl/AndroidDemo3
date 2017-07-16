package fingertip.creditease.com.test;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Describe：
 * <p>
 * Author：LZL
 * <p>
 * Date：2017/3/15 下午6:06
 */

public class CustomLinearlayout extends LinearLayout {
    private static final String TAG = "TAG";

    public CustomLinearlayout(Context context) {
        super(context);
    }

    public CustomLinearlayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomLinearlayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomLinearlayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * Describe: 如果是一个View，重写onMeasure时要注意：
     * 如果在使用自定义view时，用了wrap_content。那么在onMeasure中就要调用setMeasuredDimension，
     * 来指定view的宽高。如果使用的fill_parent或者一个具体的dp值。那么直接使用super.onMeasure即可。
     * <p>
     * 如果是一个ViewGroup，重写onMeasure时要注意：
     * 首先，结合上面两条，来测量自身的宽高。
     * 然后，需要测量子View的宽高。
     * 测量子view的方式有：
     * getChildAt(int index).可以拿到index上的子view。
     * 通过getChildCount得到子view的数目，再循环遍历出子view。
     * 接着，subView.measure(int wSpec, int hSpec); //使用子view自身的测量方法
     * 或者调用viewGroup的测量子view的方法：
     * //某一个子view，多宽，多高, 内部加上了viewGroup的padding值
     * measureChild(subView, int wSpec, int hSpec);
     * //所有子view 都是 多宽，多高, 内部调用了measureChild方法
     * measureChildren(int wSpec, int hSpec);
     * //某一个子view，多宽，多高, 内部加上了viewGroup的padding值、margin值和传入的宽高wUsed、hUsed
     * measureChildWithMargins(subView, intwSpec, int wUsed, int hSpec, int hUsed);
     * <p>
     * Author: lzl
     * <p>
     * Time: 2017/3/16 下午2:31
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e(TAG, "CustomLinearlayout===onMeasure");
    }



    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        Log.e(TAG, "CustomLinearlayout====onLayout");
    }



    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e(TAG, "CustomLinearlayout===dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
//        return true;

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e(TAG, "CustomLinearlayout====onInterceptTouchEvent");
        return super.onInterceptTouchEvent(ev);
//        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "CustomLinearlayout===onTouchEvent");
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
