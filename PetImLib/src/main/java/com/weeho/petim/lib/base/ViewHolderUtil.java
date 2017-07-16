package com.weeho.petim.lib.base;

import android.util.SparseArray;
import android.view.View;

/**
 * 
 * 
 * @describe:获取子视图的工具类
 * 
 * @author:wangkui
 * 
 * @time:2014-11-3上午10:41:46
 * 
 */
public class ViewHolderUtil {

	@SuppressWarnings("unchecked")
	public static View getItemView(View convertView, int viewId) {
		if (convertView == null) {
			return null;
		}
		SparseArray<View> viewHold = (SparseArray<View>) convertView.getTag();
		if (viewHold == null) {
			viewHold = new SparseArray<View>();
			convertView.setTag(viewHold);
		}
		View childView = (View) viewHold.get(viewId);
		if (childView == null) {
			childView = convertView.findViewById(viewId);
			viewHold.put(viewId, childView);
		}

		return childView;

	}

}
