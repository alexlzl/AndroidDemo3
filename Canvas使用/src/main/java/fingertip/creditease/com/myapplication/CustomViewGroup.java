package fingertip.creditease.com.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

/**
 * Describe：View组件的绘制会调用draw(Canvas canvas)方法，draw过程中主要是先画Drawable背景，对 drawable调用setBounds()然后是draw(Canvas c)方法.有点注意的是背景drawable的实际大小会影响view组件的大小，drawable的实际大小通过getIntrinsicWidth()和getIntrinsicHeight()获取，当背景比较大时view组件大小等于背景drawable的大小画完背景后，draw过程会调用onDraw(Canvas canvas)方法，然后就是dispatchDraw(Canvas canvas)方法, dispatchDraw()主要是分发给子组件进行绘制，我们通常定制组件的时候重写的是onDraw()方法。值得注意的是ViewGroup容器组件的绘制，当它没有背景时直接调用的是dispatchDraw()方法, 而绕过了draw()方法，当它有背景的时候就调用draw()方法，而draw()方法里包含了dispatchDraw()方法的调用。因此要在ViewGroup上绘制东西的时候往往重写的是dispatchDraw()方法而不是onDraw()方法，或者自定制一个Drawable，重写它的draw(Canvas c)和 getIntrinsicWidth(),getIntrinsicHeight()方法，然后设为背景。
 * <p>
 * Author：LZL
 * <p>
 * Date：2017/4/1 下午5:00
 */

public class CustomViewGroup extends LinearLayout {
    private String TAG = "CustomViewGroup";
    private Paint mpaint;

    public CustomViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.e(TAG, "CustomViewGroup");
//        setWillNotDraw(false);
        mpaint = new Paint();
        mpaint.setColor(Color.GREEN);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.RED);
        Log.e(TAG, "onDraw");
        canvas.drawText("test", 100, 100, mpaint);

    }

    /**
     * Describe: 值得注意的是ViewGroup容器组件的绘制，当它没有背景时直接调用的是dispatchDraw()方法, 而绕过了draw()方法，当它有背景的时候就调用draw()方法，而draw()方法里包含了dispatchDraw()方法的调用。因此要在ViewGroup上绘制东西的时候往往重写的是dispatchDraw()方法而不是onDraw()方法，或者自定制一个Drawable，重写它的draw(Canvas c)和 getIntrinsicWidth(),getIntrinsicHeight()方法，然后设为背景
     * <p>
     * Author: lzl
     * <p>
     * Time: 2017/4/1 下午5:27
     */
    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        Log.e(TAG, "dispatchDraw");
    }
}
