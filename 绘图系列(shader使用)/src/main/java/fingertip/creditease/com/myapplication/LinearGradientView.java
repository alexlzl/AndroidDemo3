package fingertip.creditease.com.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Describe：float x0 ：渐变的x坐标起点
 * float y0 ：渐变的y坐标起点
 * float x1 ：渐变的x坐标终点
 * float y1 ：渐变的y坐标终点
 * int colors[]：渐变的颜色数组
 * float positions[]：颜色的相对位置
 * TileMode tile：上面的3种模式
 *
 * x0,y0,x1,y1连接的线的方向即是渐变方向
 * <p>
 * Author：LZL
 * <p>
 * Date：2017/3/27 下午3:46
 */

public class LinearGradientView extends View {
    private Paint paint;
    private LinearGradient linearGradient;
    private LinearGradient linearGradient1;
    private LinearGradient linearGradient2;

    public LinearGradientView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init() {
        paint = new Paint();
        linearGradient1 = new LinearGradient(0, 100, 100, 100, new int[]{Color.RED, Color.GREEN, Color.BLUE}, new float[] { 0, .1F, .8F}, Shader.TileMode.REPEAT);
        linearGradient = new LinearGradient(0, 0, 100, 100, new int[]{Color.RED, Color.GREEN, Color.BLUE}, null, Shader.TileMode.REPEAT);
        linearGradient2 = new LinearGradient(400, 1000, 800, 1300, new int[]{Color.RED, Color.GREEN, Color.BLUE}, null, Shader.TileMode.CLAMP);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setShader(linearGradient);

        canvas.drawRect(0, 0, 200, 200, paint);
        paint.setShader(linearGradient1);
        canvas.drawRect(300, 300, 600, 900, paint);
        paint.setShader(linearGradient2);
        canvas.drawRect(400, 1000, 800, 1300, paint);
    }
}
