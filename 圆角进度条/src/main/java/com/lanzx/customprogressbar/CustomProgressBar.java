package com.lanzx.customprogressbar;

import android.content.Context;
import android.graphics.RectF;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.util.AttributeSet;
import android.widget.ProgressBar;

public class CustomProgressBar extends ProgressBar {

	public CustomProgressBar(Context context) {
		super(context);
	}

	public CustomProgressBar(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CustomProgressBar(Context context, AttributeSet attrs, int defStyle) {
		this(context, attrs, defStyle,0);

	}

	public CustomProgressBar(Context context, AttributeSet attrs, int defStyle, int styleRes) {
        
		super(context, attrs, defStyle);
	}

	@Override
	protected synchronized void onMeasure(int widthMeasureSpec,
			int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	public final int roundCorners = 15;
	Shape getDrawableShape() {
        final float[] roundedCorners = new float[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		RectF inset = new RectF(100, 100, 50, 50);
		float[] innerRadii = new float[] { 20, 20, 20, 20, 20, 20, 20, 20 };
		for(int i=0;i<roundedCorners.length;i++){
        	roundedCorners[i] = dp2px(getContext(), roundCorners);
        }
        return new RoundRectShape(roundedCorners, inset, innerRadii);
    }

	/**dpתpx*/
	public static float dp2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return dp * scale;
    }
}
