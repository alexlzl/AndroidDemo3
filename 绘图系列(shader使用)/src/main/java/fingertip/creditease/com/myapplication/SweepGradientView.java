package fingertip.creditease.com.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

/**
 * Describe：cx，cy：扫描中心的x，y坐标
 * colors：渐变的数组颜色
 * positions：渐变的相对位置
 * <p>
 * <p>
 * Author：LZL
 * <p>
 * Date：2017/3/27 下午5:45
 */

public class SweepGradientView extends View {
    private Shader mSweepGradient = null;
    private Paint mPaint;

    public SweepGradientView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSweepGradient = new SweepGradient(400, 400, new int[]{Color.YELLOW,
                Color.RED, Color.BLUE, Color.GREEN}, new float[]{0, .2F,
                .6F, .9F});
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setShader(mSweepGradient);
        canvas.drawCircle(400, 400, 300, mPaint);
    }
}
