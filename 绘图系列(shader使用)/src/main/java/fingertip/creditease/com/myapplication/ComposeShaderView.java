package fingertip.creditease.com.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

/**
 * Describe：这个是组合的Shader
 * <p>
 * Author：LZL
 * <p>
 * Date：2017/3/27 下午5:51
 */

public class ComposeShaderView extends View {
    private Shader mSweepGradient = null;
    private Shader mBitmapShader = null;
    private Shader mComposeShader = null;
    private Paint mPaint;
    private Bitmap mBitmap;

    public ComposeShaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.REPEAT,
                Shader.TileMode.MIRROR);
        mSweepGradient = new SweepGradient(mBitmap.getWidth() * 2,
                mBitmap.getWidth() * 2, new int[] { Color.YELLOW,
                Color.RED, Color.BLUE, Color.GREEN }, new float[] { 0, .2F,
                .6F, .9F });
        mComposeShader = new ComposeShader(mBitmapShader, mSweepGradient,
                PorterDuff.Mode.DARKEN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setShader(mComposeShader);
        canvas.drawRect(0, 0, mBitmap.getWidth() * 4, mBitmap.getHeight() * 4,
                mPaint);
    }
}
