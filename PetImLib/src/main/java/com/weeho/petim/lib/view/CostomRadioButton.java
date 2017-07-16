package com.weeho.petim.lib.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RadioButton;

/** 
 * @author 作者 E-mail: wk
 * @version 创建时间：2015-9-28 下午8:06:00 
 * 类说明 让图片和文字都居中显示
 * @param
 */
public class CostomRadioButton extends RadioButton {
	/**
	 * drawableRight与文本一起居中显示
	 * 
	 * 
	 */
		public CostomRadioButton(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		}

		public CostomRadioButton(Context context, AttributeSet attrs,
		int defStyle) {
			super(context, attrs, defStyle);
		}

		public CostomRadioButton(Context context, AttributeSet attrs) {
			super(context, attrs);
		}


		@Override
		protected void onDraw(Canvas canvas) {
			Drawable[] drawables = getCompoundDrawables();
			if (drawables != null) {
				Drawable drawableLeft = drawables[2];
					if (drawableLeft != null) {
				
					float textWidth = getPaint().measureText(getText().toString());
					int drawablePadding = getCompoundDrawablePadding();
					int drawableWidth = 0;
					drawableWidth = drawableLeft.getIntrinsicWidth();
					float bodyWidth = textWidth + drawableWidth + drawablePadding;
					setPadding(0, 0, (int)(getWidth() - bodyWidth), 0);
					canvas.translate((getWidth() - bodyWidth) / 2, 0);
				}
			}
			super.onDraw(canvas);
		}
}
 