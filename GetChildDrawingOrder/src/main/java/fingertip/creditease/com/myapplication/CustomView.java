package fingertip.creditease.com.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Describe：http://blog.csdn.net/coderinchina/article/details/51344970
 * <p>
 * Author：LZL
 * <p>
 * Date：2017/4/1 下午6:33
 */

public class CustomView extends ViewGroup {
    private Paint mPaint;
    private Bitmap bitmap;

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
    }

    public CustomView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, 100, 100, mPaint);
    }

    /**
     * Describe: 前面一个是坐标点(100,100)后一个坐标点是(400,400) 说明这二个方法都可以把一个资源通过canvas画上去,而dispatchDraw通常是用在容器view中的,所谓的容器view就是能有addView()方法,比如RelativeLayout,LinearLayout,ListView,GridView等,但是像上面的焦点框是咋么画上去的呢?是一个view还是一个其他的东西呢?如果是view的画就要再自定义一个view,当然也可以不用view,用Drawable也行,看下view的继承或者实现关系：
     * <p>
     * Author: lzl
     * <p>
     * Time: 2017/4/1 下午6:56
     */
    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        canvas.drawBitmap(bitmap, 400, 400, mPaint);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
