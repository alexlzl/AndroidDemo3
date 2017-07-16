package com.weeho.petim.lib.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 中间带横线的TextView
 */
public class MiddleLineView extends TextView {

    private Paint mPaint;

    public MiddleLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setStrokeWidth(1.0f);
        mPaint.setColor(Color.parseColor("#bababa"));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(0f, this.getHeight()/2, this.getWidth(),
                this.getHeight()/2, mPaint);
    }
}
