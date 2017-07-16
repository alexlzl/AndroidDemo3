package fingertip.creditease.com.test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

/**
 * Describe：
 * <p>
 * Author：LZL
 * <p>
 * Date：2017/2/9 下午3:17
 */

public class RaderView extends View {
    private Paint mPaintCircleLine;//绘制圆边
    private Paint mPaintCircle;
    private int width;
    private int height;
    private Matrix matrix;
    private int start;
    private Handler handler = new Handler();
    private Runnable run = new Runnable() {
        @Override
        public void run() {
            start += 1;
            matrix = new Matrix();
            matrix.postRotate(start, width / 2, height / 2);
            RaderView.this.invalidate();//刷新重绘
            handler.postDelayed(run,30);
        }
    };

    public RaderView(Context context) {
        this(context, null);
    }

    public RaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
        //得到当前屏幕的像素宽高
        width = context.getResources().getDisplayMetrics().widthPixels;
        height = context.getResources().getDisplayMetrics().heightPixels;
        matrix = new Matrix();
        handler.post(run);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(width, height);//设置该控件的宽高为当前屏幕的宽高
    }

    private void initPaint() {
        mPaintCircleLine = new Paint();
        mPaintCircleLine.setColor(Color.parseColor("#a2a2a2"));
        mPaintCircleLine.setAntiAlias(true);//抗锯齿
        mPaintCircleLine.setStyle(Paint.Style.STROKE);//设置实心
        mPaintCircleLine.setStrokeWidth(2);//画笔宽度

        mPaintCircle = new Paint();
        mPaintCircle.setColor(Color.parseColor("#99a2a2a2"));
        mPaintCircle.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //分别绘制四个圆
        canvas.drawCircle(width / 2, height / 2, height / 7, mPaintCircleLine);
        canvas.drawCircle(width / 2, height / 2, height / 4, mPaintCircleLine);
        canvas.drawCircle(width / 2, height / 2, height / 3, mPaintCircleLine);
        canvas.drawCircle(width / 2, height / 2, 3 * height / 7, mPaintCircleLine);
        //设置颜色渐变从透明到不透明
        Shader shader = new SweepGradient(width / 2, height / 2, Color.TRANSPARENT, Color.parseColor("#50aaaaaa"));
        mPaintCircle.setShader(shader);
        canvas.concat(matrix);
        canvas.drawCircle(width / 2, height / 2, 3 * height / 7, mPaintCircle);
    }
}
