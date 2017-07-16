package com.weeho.petim.lib.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 
 * 
 * @describe:解决ListView 嵌套在ScrollView中显示不全的冲突
 * 
 * @author:wangkui
 * 
 * @time:2014-11-4上午10:49:50
 * 
 */

public class ScrollViewListView extends ListView {

	public ScrollViewListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public ScrollViewListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public ScrollViewListView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub

		int height = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, height);
	}

}
