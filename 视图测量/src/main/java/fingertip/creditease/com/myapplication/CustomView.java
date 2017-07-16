package fingertip.creditease.com.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Describe：
 * <p>
 * Author：LZL
 * <p>
 * Date：2017/3/31 下午5:57
 */

public class CustomView extends View {
    private static String TAG = "TAG";
    private int modew;
    private int sizew;
    private int modeh;
    private int sizeh;

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Log.e(TAG, "child====构造函数");
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.e(TAG, "child====onFinishInflate");
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.e(TAG, "child====onAttachedToWindow");
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e(TAG, "child====onSizeChanged");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(widthMeasureSpec,heightMeasureSpec);
        Log.e(TAG, "child====onMeasure");
        modew = MeasureSpec.getMode(widthMeasureSpec);
        sizew = MeasureSpec.getSize(widthMeasureSpec);
        modeh = MeasureSpec.getMode(heightMeasureSpec);
        sizeh = MeasureSpec.getSize(heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.e(TAG, "child====onLayout");
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        Log.e(TAG, "child====onWindowFocusChanged"+"width=="+getWidth()+"height=="+getHeight());
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e(TAG, "child====onDraw");
    }

    public void setTextView(TextView textView) {
        String namew = null;
        if (modew == MeasureSpec.UNSPECIFIED) {
            namew = "UNSPECIFIED";
        } else if (modew == MeasureSpec.EXACTLY) {
            namew = "EXACTLY";
        } else if (modew == MeasureSpec.AT_MOST) {
            namew = "AT_MOST";
        }
        String nameh = null;
        if (modeh == MeasureSpec.UNSPECIFIED) {
            nameh = "UNSPECIFIED";
        } else if (modeh == MeasureSpec.EXACTLY) {
            nameh = "EXACTLY";
        } else if (modeh == MeasureSpec.AT_MOST) {
            nameh = "AT_MOST";
        }
        textView.setText("测量宽度=" + "模式==" + namew + "宽度==" + sizew + "测量高度=" + "模式==" + nameh + "高度==" + sizeh);
    }
}
