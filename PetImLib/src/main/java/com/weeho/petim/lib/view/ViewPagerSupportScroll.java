package com.weeho.petim.lib.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 
 * 
 * @describe:Scrollview ViewPager冲突解决
 * 
 * @author:wangkui
 * 
 * @time:2014-11-4上午10:52:48
 * 
 */
public class ViewPagerSupportScroll extends ViewPager {
	public ViewPagerSupportScroll(Context context) {
		super(context);
	}

	public ViewPagerSupportScroll(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		try {
			return super.onInterceptTouchEvent(ev);
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		// TODO Auto-generated method stub
		try {
			switch (arg0.getAction()) {
			case MotionEvent.ACTION_DOWN:
				break;
			case MotionEvent.ACTION_MOVE:
				requestDisallowInterceptTouchEvent(true);
				break;
			case MotionEvent.ACTION_UP:
				break;
			case MotionEvent.ACTION_CANCEL:
				requestDisallowInterceptTouchEvent(false);
				break;
			}
			return super.onTouchEvent(arg0);
		} catch (IllegalArgumentException ex) {
			return false;
		}
	}

}
