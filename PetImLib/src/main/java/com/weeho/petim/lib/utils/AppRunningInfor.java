package com.weeho.petim.lib.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import java.util.List;

/**
 * Describe:获取APP运行信息
 * <p/>
 * Date:2015-7-8
 * <p/>
 * Author:wangkui
 */
public class AppRunningInfor {

    /**
     * Describe:应用是否运行在前台
     * <p/>
     * Date:2015-7-8
     * <p/>
     * Author:wangkui
     */
    @SuppressWarnings("deprecation")
	public boolean isAPPRunningForeground(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ComponentName cn = am.getRunningTasks(100).get(0).topActivity;
        String currentPackageName = cn.getPackageName();
        if (currentPackageName != null && currentPackageName.equals(context.getPackageName())) {
            return true;
        }
        return false;
    }

    /**
     * Describe:应用是否运行在前台
     * <p/>
     * Date:2015-7-8
     * <p/>
     * Author:wangkui
     */
    public static boolean isAPPRunningForeground(String packageName, Context context) {
        ActivityManager __am = (ActivityManager) context.getApplicationContext()
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningAppProcessInfo> __list = __am.getRunningAppProcesses();
        // LogUtil.d(TAG, " __list.size() == "+__list.size());
        if (__list.size() == 0)

            return false;
        for (RunningAppProcessInfo __process : __list) {
            if (__process.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND
                    && __process.processName.equals(packageName)) {
                // LogUtil.d(TAG, " __process.processName=="+
                // __process.processName);
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("deprecation")
	public static boolean isRunningForeground(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
        String currentPackageName = cn.getPackageName();
        if (!TextUtils.isEmpty(currentPackageName) && currentPackageName.equals(context.getPackageName())) {
            return true;
        }
        return false;
    }

    /**
     * Describe:ACTIVITY是否运行在前台
     * <p/>
     * Date:2015-7-8
     * <p/>
     * Author:wangkui
     */
    @SuppressWarnings("deprecation")
	public static boolean isActivityRunningForeground(Activity name) {
        ActivityManager am = (ActivityManager) name.getSystemService(Context.ACTIVITY_SERVICE);
        ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
        if (!name.equals(cn.getPackageName())
                || !"com.math.speedPractice.MathSpeedPractice".equals(cn.getClassName())) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Describe:判断ACTIVITY是否运行在前台
     * <p/>
     * Date:2015-7-23
     * <p/>
     * Author:wangkui
     */
    @SuppressWarnings("deprecation")
	public static boolean isActivityRunningForeground(Context context, String className) {
        if (context == null || TextUtils.isEmpty(className)) {
            return false;
        }

        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningTaskInfo> list = am.getRunningTasks(1);
        if (list != null && list.size() > 0) {
            ComponentName cpn = list.get(0).topActivity;
            if (className.equals(cpn.getClassName())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }

    /**
     * Describe:判断APP是否前台在运行
     * <p/>
     * Date:2015-7-23
     * <p/>
     * Author:wangkui
     */
    @SuppressWarnings("deprecation")
	public static boolean isAppRunning(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningTaskInfo> list = am.getRunningTasks(100);
        boolean isAppRunning = false;
        String MY_PKG_NAME = context.getPackageName();
        for (RunningTaskInfo info : list) {
            if (info.topActivity.getPackageName().equals(MY_PKG_NAME)
                    || info.baseActivity.getPackageName().equals(MY_PKG_NAME)) {
                isAppRunning = true;
                break;

            }
        }

        return isAppRunning;
    }

    /**
     * @return null may be returned if the specified process not found
     */
    public static String getProcessName(Context cxt, int pid) {
        ActivityManager am = (ActivityManager) cxt.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }
        for (RunningAppProcessInfo procInfo : runningApps) {
            if (procInfo.pid == pid) {
                return procInfo.processName;
            }
        }
        return null;
    }

    /**
     * @describe:获取渠道号
     * @author:wangkui
     * @time:2015-4-8上午10:39:29
     */
    public static String getMetaDataValue(Context context, String channelName,
                                          String defChannelName) {
        String value = getMetaDataValue(context, channelName);
        return (value == null) ? defChannelName : value;
    }

    private static String getMetaDataValue(Context context, String name) {
        Object value = null;
        PackageManager packageManager = context.getPackageManager();
        ApplicationInfo applicationInfo;
        try {
            applicationInfo = packageManager.getApplicationInfo(
                    context.getPackageName(), 128);
            if (applicationInfo != null && applicationInfo.metaData != null) {
                value = applicationInfo.metaData.get(name);
            }
        } catch (Exception e) {
        }

        if (value == null) {
        }
        return value.toString();
    }

}
