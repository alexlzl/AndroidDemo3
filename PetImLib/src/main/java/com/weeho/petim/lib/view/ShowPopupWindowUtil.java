package com.weeho.petim.lib.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RelativeLayout.LayoutParams;

/**
 * 
 * 
 * @describe:自定义窗口
 * 
 * @author:wangkui
 * 
 * @time:2014-11-4上午10:50:08
 * 
 */
public class ShowPopupWindowUtil {

	public static PopupWindow showPopupWindow(Activity activity, View position,
			int layoutId) {
		PopupWindow mPopupWindow = null;
		View mPopMenuView = null;
		mPopMenuView = LayoutInflater.from(activity).inflate(layoutId, null);

		mPopupWindow = new PopupWindow(mPopMenuView,
				ViewUtil.getScreenWith(activity), LayoutParams.MATCH_PARENT,
				false);
		mPopupWindow
				.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		mPopupWindow.setFocusable(true);
		mPopupWindow.setTouchable(true);
		mPopupWindow.setOutsideTouchable(false);
		mPopupWindow.showAsDropDown(position,
				ViewUtil.getScreenWith((Context) activity), 0);
		mPopMenuView.getBackground().setAlpha(178);
		return mPopupWindow;
	}

	public static PopupWindow showPopupWindow1(Activity activity,
			View position, int layoutId, int width) {
		PopupWindow mPopupWindow = null;
		View mPopMenuView = null;
		mPopMenuView = LayoutInflater.from(activity).inflate(layoutId, null);

		mPopupWindow = new PopupWindow(mPopMenuView, width,
				LayoutParams.MATCH_PARENT, false);
		mPopupWindow
				.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		mPopupWindow.setFocusable(true);
		mPopupWindow.setTouchable(true);
		mPopupWindow.setOutsideTouchable(false);
		mPopupWindow.showAsDropDown(position,
				ViewUtil.getScreenWith((Context) activity) / 2, 0);
		// mPopMenuView.getBackground().setAlpha(178);
		return mPopupWindow;
	}

	public static PopupWindow showAtWindowTop(Activity activity, View position,
			int layoutId) {
		PopupWindow mPopupWindow = null;
		View mPopMenuView = null;
		mPopMenuView = LayoutInflater.from(activity).inflate(layoutId, null);

		mPopupWindow = new PopupWindow(mPopMenuView,
				ViewUtil.getScreenWith((Context) activity),
				LayoutParams.MATCH_PARENT, false);
		mPopupWindow
				.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		mPopupWindow.setFocusable(true);
		mPopupWindow.setTouchable(true);
		mPopupWindow.setOutsideTouchable(false);
		mPopupWindow.showAsDropDown(position,
				ViewUtil.getScreenWith((Context) activity) / 2, 0);
		// mPopupWindow.showAtLocation(position, Gravity.START, 0,
		// ViewUtil.dip2px(activity, 30));
		mPopMenuView.getBackground().setAlpha(178);
		return mPopupWindow;
	}

}
