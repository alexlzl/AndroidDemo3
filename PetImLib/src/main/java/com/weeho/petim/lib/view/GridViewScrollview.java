package com.weeho.petim.lib.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * @describe:支持嵌套Scrollview的视图
 * @author:wangkui
 * @time:2014-11-24下午5:07:31
 */
public class GridViewScrollview extends GridView {

    public GridViewScrollview(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    public GridViewScrollview(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public GridViewScrollview(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
