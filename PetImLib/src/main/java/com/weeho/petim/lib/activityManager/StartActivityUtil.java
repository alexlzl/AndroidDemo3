package com.weeho.petim.lib.activityManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.weeho.petim.lib.R;

/**
 * Describe:启动Activity管理
 * <p/>
 * Date:2015-6-26
 * <p/>
 * Author:wangkui
 */
public class StartActivityUtil {

    /**
     * activity跳转，回调menu事件，从右至左滑入动画效果
     *
     * @param to            跳转到的Activity
     * @param iMenuListener 标题菜单监听器, 传MainActivity
     */
    public static void startActivity1(Activity context, Class<?> to) {
        Intent intent = new Intent(context, to);
        context.startActivity(intent);
        context.overridePendingTransition(R.anim.activity_in_from_right,
                R.anim.activity_out_to_left);
    }

    /**
     * Describe:从右至左滑入动画效果
     * <p/>
     * Date:2015-6-26
     * <p/>
     * Author:wangkui
     */
    public static void startActivity(Activity context, Intent intent) {
        context.startActivity(intent);
//        context.overridePendingTransition(R.anim.activity_in_from_right,
//                R.anim.activity_out_to_left);
        context.overridePendingTransition(R.anim.push_left_in,
				R.anim.push_left_out);
    }

    /**
     * Describe:从右至左滑入动画效果
     * <p/>
     * Date:2015-6-26
     * <p/>
     * Author:wangkui
     */
    public static void startActivity(Activity context, Class<?> to) {
        Intent intent = new Intent(context, to);
        context.startActivity(intent);
        context.overridePendingTransition(R.anim.activity_in_from_right,
                R.anim.activity_out_to_left);
    }
    //从右侧滑入
    public static void startActivityFromRight(Activity context, Intent intent) {
    	 context.startActivity(intent);
         context.overridePendingTransition(R.anim.activity_in_from_right,
                R.anim.activity_out_to_left);
    }
    /**
     * Describe:从下至上滑入动画效果
     * <p/>
     * Date:2015-6-26
     * <p/>
     * Author:wangkui
     */
    public static void startActivityFromBottom(Activity context, Intent intent) {
        context.startActivity(intent);
        context.overridePendingTransition(R.anim.activity_in_from_bottom,
                R.anim.activity_out_to_top);
    }

    /**
     * Describe:从右至左滑入动画效果
     * <p/>
     * Date:2015-6-26
     * <p/>
     * Author:wangkui
     */
    public static void startActivity(Activity context, Class<?> to,
                                     Bundle bundle) {
        Intent intent = new Intent(context, to);
        intent.putExtras(bundle);
        context.overridePendingTransition(R.anim.activity_in_from_right,
                R.anim.activity_out_to_left);
    }

    /**
     * Describe:从右至左滑入动画效果
     * <p/>
     * Date:2015-6-26
     * <p/>
     * Author:wangkui
     */
    public static void startActivityForResult(Activity context, Class<?> to,
                                              Bundle bundle, int requestCode) {
        Intent intent = new Intent(context, to);
        intent.putExtras(bundle);
        context.startActivityForResult(intent, requestCode);
        context.overridePendingTransition(R.anim.activity_in_from_right,
                R.anim.activity_out_to_left);
    }

    /**
     * Describe:从右至左滑入动画效果
     * <p/>
     * Date:2015-6-26
     * <p/>
     * Author:wangkui
     */
    public static void startActivityForResultFromBottom(Activity context,
                                                        Class<?> to, Bundle bundle, int requestCode) {
        Intent intent = new Intent(context, to);
        intent.putExtras(bundle);
        context.startActivityForResult(intent, requestCode);
        context.overridePendingTransition(R.anim.activity_in_from_bottom,
                R.anim.activity_out_to_top);
    }

    /**
     * Describe:从右至左滑入动画效果
     * <p/>
     * Date:2015-6-26
     * <p/>
     * Author:wangkui
     */
    public static void startActivityForResult(Activity context, Intent intent,
                                              int requestCode) {
        context.startActivityForResult(intent, requestCode);
        context.overridePendingTransition(R.anim.activity_in_from_right,
                R.anim.activity_out_to_left);
    }

    /**
     * Describe:从下至上滑入动画效果
     * <p/>
     * Date:2015-6-26
     * <p/>
     * Author:wangkui
     */
    public static void startActivityForResultFromBottom(Activity context,
                                                        Intent intent, int requestCode) {
        context.startActivityForResult(intent, requestCode);
        context.overridePendingTransition(R.anim.activity_in_from_bottom,
                R.anim.activity_out_to_top);
    }

    /**
     * Describe:从右至左滑入动画效果
     * <p/>
     * Date:2015-6-26
     * <p/>
     * Author:wangkui
     */
    public static void startActivityForResult(Activity context, Class<?> to,
                                              int requestCode) {
        Intent intent = new Intent(context, to);
        context.startActivityForResult(intent, requestCode);
        context.overridePendingTransition(R.anim.activity_in_from_right,
                R.anim.activity_out_to_left);
    }

    /**
     * Describe:activity关闭，从左至右滑出动画效果
     * <p/>
     * Date:2015-6-26
     * <p/>
     * Author:wangkui
     */
    public static void finishActivity(Activity context) {
        context.finish();
        context.overridePendingTransition(R.anim.activity_in_from_left,
                R.anim.activity_out_to_right);
    }

    /**
     * Describe:activity关闭，从上至下滑出动画效果
     * <p/>
     * Date:2015-6-26
     * <p/>
     * Author:wangkui
     */
    public static void finishActivityToBottom(Activity context) {
        context.finish();
        context.overridePendingTransition(R.anim.activity_in_from_top,
                R.anim.activity_out_to_bottom);
    }

}
