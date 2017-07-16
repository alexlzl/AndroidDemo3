package com.weeho.petim.lib.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

/**
 * DESCRIBE:解决Scrollview中EditText滑动冲突
 * 
 * AUTHOR:wangkui
 * 
 * TIME:2014-3-13下午2:08:41
 */

public class ScrollviewEditText extends EditText {

    public ScrollviewEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    public ScrollviewEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public ScrollviewEditText(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub

        switch (event.getAction()) {
        case MotionEvent.ACTION_DOWN:
            break;
        case MotionEvent.ACTION_MOVE:
            this.getParent().requestDisallowInterceptTouchEvent(true);
            break;
        case MotionEvent.ACTION_UP:
            break;
        case MotionEvent.ACTION_CANCEL:
            this.getParent().requestDisallowInterceptTouchEvent(false);
            break;
        }
        return super.onTouchEvent(event);
    }

}
