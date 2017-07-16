package com.weeho.petim.lib.view;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.widget.TextView;

import com.weeho.petim.lib.R;

/**
 * 
 * 
 * @describe:自定义TextView 实现画垂直虚线
 * 
 * @author:wangkui
 * 
 * @time:2014-11-4上午10:48:40
 * 
 */

public class DrawVerticalLineTextView extends TextView {

	public DrawVerticalLineTextView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public DrawVerticalLineTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public DrawVerticalLineTextView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub

		super.onDraw(canvas);

		Paint paint = new Paint();
		paint.setStyle(Paint.Style.STROKE);
		paint.setColor(getResources().getColor(R.color.color_999999));
		Path path = new Path();
		path.moveTo(0, 0);
		path.lineTo(1, getHeight());
		PathEffect effects = new DashPathEffect(new float[] { 5, 5, 5, 5 }, 1);
		paint.setPathEffect(effects);
		canvas.drawPath(path, paint);
		// canvas.rotate(90, getMeasuredWidth() / 3, getMeasuredHeight() / 3);
	}

}
