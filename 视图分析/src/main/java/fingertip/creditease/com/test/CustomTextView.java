package fingertip.creditease.com.test;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Describe：
 * <p>
 * Author：LZL
 * <p>
 * Date：2017/3/15 下午3:55
 */

public class CustomTextView extends TextView {
    private static final String TAG="TAG";
//    public CustomTextView(Context context) {
//        super(context);
//    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

//    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//    }
//
//    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.e(TAG,"CustomTextView==dispatchTouchEvent");
//        return super.dispatchTouchEvent(event);

        return   false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG,"CustomTextView===onTouchEvent");
        return super.onTouchEvent(event);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    public void layout(@Px int l, @Px int t, @Px int r, @Px int b) {
        super.layout(l, t, r, b);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
