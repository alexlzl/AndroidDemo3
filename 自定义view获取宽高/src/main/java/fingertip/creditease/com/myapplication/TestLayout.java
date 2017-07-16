package fingertip.creditease.com.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Describe：
 * <p>
 * Author：LZL
 * <p>
 * Date：2017/3/28 下午6:20
 */

public class TestLayout extends FrameLayout {
    private static final String TAG = TestLayout.class.getSimpleName();
    private View mFistPart, mSecondPart;
    private int mFistHeight, mSecondHeight;


    public TestLayout(Context context) {
        super(context);
//        this(context, null);
        Log.e(TAG, "----------执行1个参数Constructor-------------");
        init();
    }

    public TestLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
//        this(context, attrs, 0);
        Log.e(TAG, "----------执行2个参数Constructor-------------");
        init();
    }

    public TestLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.e(TAG, "----------执行3个参数Constructor-------------");
        init();
    }

    private void init() {

//        System.out.println("----------Constructor-------------");
        mFistPart = getChildAt(0);
        mSecondPart = getChildAt(1);
//        System.out.println("mFistPart:" + mFistPart + " mSecondPart:" + mSecondPart);
        Log.e(TAG, "mFistPart:" + mFistPart + " mSecondPart:" + mSecondPart);
        setWillNotDraw(false);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mFistPart = getChildAt(0);
        mSecondPart = getChildAt(1);
        mFistHeight = mFistPart.getMeasuredHeight();
        mSecondHeight = mSecondPart.getMeasuredHeight();
//        System.out.println("----------onFinishInflate-------------");
        Log.e(TAG, "----------执行onFinishInflate-------------");
        Log.e(TAG, "mFistPart:" + mFistPart + " mSecondPart:" + mSecondPart);
        Log.e(TAG, "mFistPart.getMeasuredHeight():" + mFistHeight + " mSecondPart.getMeasuredHeight():" + mSecondHeight);
        Log.e(TAG, "mFistPart.getHeight():" + mFistPart.getHeight() + " mSecondPart.getHeight():" + mSecondPart.getHeight());
//        mFistPart.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
//            @Override
//            public void onGlobalLayout() {
//                System.out.println("----------addOnGlobalLayoutListener-------------");
//                mFistPart.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                System.out.println("mFistPart.getMeasuredHeight():" + mFistPart.getMeasuredHeight() + "mFistPart.getHeight():" + mFistPart.getMeasuredHeight());
//            }
//        });
//        mSecondPart.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
//            @Override
//            public void onGlobalLayout() {
//                System.out.println("----------addOnGlobalLayoutListener-------------");
//                mSecondPart.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                System.out.println("mFistPart.getMeasuredHeight():" + mSecondPart.getMeasuredHeight() + "mFistPart.getHeight():" + mSecondPart.getMeasuredHeight());
//            }
//        });
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.e(TAG, "----------执行onAttachedToWindow-------------");
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mFistPart = getChildAt(0);
        mSecondPart = getChildAt(1);
        mFistHeight = mFistPart.getMeasuredHeight();
        mSecondHeight = mSecondPart.getMeasuredHeight();
        Log.e(TAG, "----------执行onMeasure-------------");
        Log.e(TAG, "mFistPart:" + mFistPart + " mSecondPart:" + mSecondPart);
        Log.e(TAG, "mFistPart.getMeasuredHeight():" + mFistHeight + " mSecondPart.getMeasuredHeight():" + mSecondHeight);
        Log.e(TAG, "mFistPart.getHeight():" + mFistPart.getHeight() + " mSecondPart.getHeight():" + mSecondPart.getHeight());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mFistPart = getChildAt(0);
        mSecondPart = getChildAt(1);
        mFistHeight = mFistPart.getMeasuredHeight();
        mSecondHeight = mSecondPart.getMeasuredHeight();
        Log.e(TAG, "----------执行onSizeChanged-------------");
        Log.e(TAG, "mFistPart:" + mFistPart + " mSecondPart:" + mSecondPart);
        Log.e(TAG, "mFistPart.getMeasuredHeight():" + mFistHeight + " mSecondPart.getMeasuredHeight():" + mSecondHeight);
        Log.e(TAG, "mFistPart.getHeight():" + mFistPart.getHeight() + " mSecondPart.getHeight():" + mSecondPart.getHeight());
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mFistPart = getChildAt(0);
        mSecondPart = getChildAt(1);
        mFistHeight = mFistPart.getMeasuredHeight();
        mSecondHeight = mSecondPart.getMeasuredHeight();
        Log.e(TAG, "----------执行onLayout-------------");
        Log.e(TAG, "mFistPart:" + mFistPart + " mSecondPart:" + mSecondPart);
        Log.e(TAG, "mFistPart.getMeasuredHeight():" + mFistHeight + " mSecondPart.getMeasuredHeight():" + mSecondHeight);
        Log.e(TAG, "mFistPart.getHeight():" + mFistPart.getHeight() + " mSecondPart.getHeight():" + mSecondPart.getHeight());
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        mFistPart = getChildAt(0);
        mSecondPart = getChildAt(1);
        mFistHeight = mFistPart.getMeasuredHeight();
        mSecondHeight = mSecondPart.getMeasuredHeight();
        Log.e(TAG, "----------执行onWindowFocusChanged-------------");
        Log.e(TAG, "mFistPart:" + mFistPart + " mSecondPart:" + mSecondPart + " hasWindowFocus:" + hasWindowFocus);
        Log.e(TAG, "mFistPart.getMeasuredHeight():" + mFistHeight + " mSecondPart.getMeasuredHeight():" + mSecondHeight);
        Log.e(TAG, "mFistPart.getHeight():" + mFistPart.getHeight() + " mSecondPart.getHeight():" + mSecondPart.getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e(TAG, "----------执行onDraw-------------");
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        Log.e(TAG, "----------执行onWindowVisibilityChanged-------------");
    }

    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        Log.e(TAG, "----------onVisibilityChanged-------------");
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.e(TAG, "----------执行onDetachedFromWindow-------------");
    }
}
