package fingertip.creditease.com.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

/**
 * Describe：
 * <p>
 * Author：LZL
 * <p>
 * Date：2017/3/31 下午6:19
 */

public class CustomViewGroup extends LinearLayout {
    private static String TAG = "TAG";

    public CustomViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
//        setWillNotDraw(false);
//        setChildrenDrawingOrderEnabled(true);
        Log.e(TAG, "parent====构造函数");
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.e(TAG, "parent====onFinishInflate");
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.e(TAG, "parent====onAttachedToWindow");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e(TAG, "parent====onMeasure");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e(TAG, "parent====onSizeChanged");
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        Log.e(TAG, "parent====onLayout");
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        Log.e(TAG, "parent====onWindowFocusChanged");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e(TAG, "parent====onDraw");
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        Log.e(TAG, "parent====dispatchDraw");
    }

    /**
     * Describe: 　protected int getChildDrawingOrder (int childCount, int i)
     * <p>
     * 　　返回迭代的绘制子类索引。如果你想改变子类的绘制顺序就要重写该方法。默认返回 i 值。
     * <p>
     * 　　提示：为了能够调用该方法，你必须首先调用setChildrenDrawingOrderEnabled(boolean)来允许子类排序。
     * <p>
     * 　　参数
     * <p>
     * 　　childCount        子类个数
     * <p>
     * 　　i        当前迭代顺序
     * <p>
     * 　　返回值
     * <p>
     * 　　绘制该迭代子类的索引
     * <p>
     * 上面 是官方 给出的说明， 在调用draw（）方法时，将会调用getChildDrawingOrder（int childCount ，int i）方法，ViewGroup 中默认是从上绘制到下，
     * 如果有需要改变绘制的 先后的顺序，就可以从写改方法，（注意，我说的是顺序而不是位置 ）
     * 在onmeasure，onLayout 方法中，位置和大小早就确定了， 所以说是不可能改变的。
     * <p>
     * Author: lzl
     * <p>
     * Time: 2017/4/1 下午2:00
     */
    @Override
    protected int getChildDrawingOrder(int childCount, int i) {
//        return super.getChildDrawingOrder(childCount, i);
        return childCount - i - 1;//倒序
    }
}
