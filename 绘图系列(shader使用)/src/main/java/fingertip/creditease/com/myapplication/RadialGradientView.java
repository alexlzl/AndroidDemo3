package fingertip.creditease.com.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Describe：x：渐变的中心x坐标
 * y：渐变的中心y坐标
 * radius：渐变的半径
 * colors：梯度渐变的颜色数组
 * positions：和LinearGradient类似，用来指定颜色数组的相对位置
 * <p>
 * Author：LZL
 * <p>
 * Date：2017/3/27 下午4:25
 */

public class RadialGradientView extends View {
    private Paint paint;
    private RadialGradient radialGradient;

    public RadialGradientView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init() {
        paint = new Paint();
//        int w = MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
//        int h = MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
//        measure(w, h);
//        int height = getMeasuredHeight();
//        int width = getMeasuredWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        radialGradient = new RadialGradient(getWidth() / 2, getHeight() / 2, 100, new int[]{Color.RED, Color.GREEN, Color.BLUE}, new float[]{0.2f, 0.5f, 0.8f}, Shader.TileMode.REPEAT);
        paint.setShader(radialGradient);
        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        radialGradient = new RadialGradient(getWidth() / 2, getHeight() / 2, 100, new int[]{Color.RED, Color.GREEN, Color.BLUE}, new float[]{0.2f, 0.5f, 0.8f}, Shader.TileMode.REPEAT);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
//        radialGradient = new RadialGradient(getWidth() / 2, getHeight() / 2, 100, new int[]{Color.RED, Color.GREEN, Color.BLUE}, new float[]{0.2f, 0.5f, 0.8f}, Shader.TileMode.REPEAT);
    }
}
