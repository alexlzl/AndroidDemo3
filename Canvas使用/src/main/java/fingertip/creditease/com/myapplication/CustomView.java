package fingertip.creditease.com.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Describe：http://www.cnblogs.com/liangstudyhome/p/4143498.html
 *
 * <p>
 * Author：LZL
 * <p>
 * Date：2017/4/1 下午3:15
 */

public class CustomView extends View {
    Paint mPaint;

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
    }


    @Override
    protected void onDraw(Canvas canvas) {


//        canvas.drawColor(Color.WHITE);


        canvas.translate(10,

                10);

        mPaint.setColor(Color.RED);


        canvas.drawCircle(75,

                75,

                75,
                mPaint);


        canvas.saveLayerAlpha(0,

                0,

                200,

                200,

                0x88,
                Canvas.ALL_SAVE_FLAG);
//        canvas.saveLayer(0,
//
//                0,
//
//                200,
//
//                200,mPaint,
//
//                Canvas.ALL_SAVE_FLAG);

        mPaint.setColor(Color.BLUE);


        canvas.drawCircle(100,

                100,

                75,
                mPaint);


//        canvas.restore();
        mPaint.setColor(Color.GREEN);
        canvas.drawRect(150,150,500,500,mPaint);

    }
}
