/*package com.weeho.petim.lib.utils;


import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.weeho.petim.lib.R;
import com.weeho.petim.lib.activityManager.StartActivityUtil;

*//**
 * 自定义Toast
 *//*
public class ToastUtil {

    private static final int TOAST_REPEAT_DURATION = 1000 * 3;

    private static String mLastString = "";

    private static long mStartTime = -1;

    private static Toast mToast;

    public static void showToast(Context context, CharSequence text, int duration) {
        if (System.currentTimeMillis() - mStartTime > TOAST_REPEAT_DURATION) {
            if (mToast == null) {
                mToast = new Toast(context);
                LayoutInflater inflate = (LayoutInflater)
                        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View v = inflate.inflate(R.layout.toast, null);
                TextView tv = (TextView) v.findViewById(android.R.id.message);
                tv.setText(text);
                mToast.setView(v);
            } else {
                mToast.setText(text);
            }
            mToast.setDuration(duration);
            mToast.show();
            mStartTime = System.currentTimeMillis();
        }
        //3s内重复按下
        else {
            if (!mLastString.equals(text)) {
                mToast.setText(text);
                mToast.setDuration(duration);
                mToast.show();
                mStartTime = System.currentTimeMillis();
                mLastString = text.toString();
            } else {
                //拒绝加入队列
            }
        }
    }

    public static void showToast(Context context, int resId, int duration)
            throws Resources.NotFoundException {
        showToast(context, context.getResources().getText(resId), duration);
    }

    public static void showToast(Context context, CharSequence text) {
        showToast(context, text, Toast.LENGTH_SHORT);
    }

    public static void showToast(Context context, int resId) {
        showToast(context, context.getResources().getText(resId));
    }

}
*/