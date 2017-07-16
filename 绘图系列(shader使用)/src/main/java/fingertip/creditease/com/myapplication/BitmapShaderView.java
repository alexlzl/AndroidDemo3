package fingertip.creditease.com.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Describe：Shader中有一个TileMode，共有3种模式，
 * CLAMP：当图片小于绘制尺寸时要进行边界拉伸来填充
 * REPEAT：当图片小于绘制尺寸时重复平铺
 * MIRROR：当图片小于绘制尺寸时镜像平铺
 * <p>
 * Author：LZL
 * <p>
 * Date：2017/3/27 下午3:04
 */

public class BitmapShaderView extends View {
    private Paint paint;
    private Bitmap bitmap;
    private BitmapShader bitmapShader;

    public BitmapShaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init() {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        bitmapShader = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.MIRROR);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setShader(bitmapShader);
        canvas.drawRect(0, 0, bitmap.getWidth() * 5, bitmap.getHeight() * 5, paint);
    }
}
