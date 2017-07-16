package com.weeho.petim.lib.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;

/**
 * Describe:获取手机的硬件信息
 * <p/>
 * Date:2015-6-25
 * <p/>
 * Author:wangkui
 */
public class DeviceUtil {
    /**
     * Describe:判断应用是否已安装
     * <p/>
     * Date:2015-6-25
     * <p/>
     * Author:wangkui
     */
    public static boolean isAppInstalled(Context context, String packageName) {
        final PackageManager packageManager = context.getPackageManager();
        // 获取所有已安装程序的包信息
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        for (int i = 0; i < pinfo.size(); i++) {
            if (pinfo.get(i).packageName.equalsIgnoreCase(packageName))
                return true;
        }
        return false;
    }

    /**
     * Describe:获取手机的硬件信息
     * <p/>
     * Date:2015-6-25
     * <p/>
     * Author:wangkui
     */
    public static String getMobileInfo() {
        StringBuffer sb = new StringBuffer();
        // 通过反射获取系统的硬件信息
        try {
            sb.append("OCCUR_TIME"
                    + "="
                    + TimeUtil.changeTimeStempToString((int) (System
                    .currentTimeMillis() / 1000)));
            sb.append("\n");
            Field[] fields = Build.class.getDeclaredFields();
            for (Field field : fields) {
                try {
                    field.setAccessible(true);
                    String name = field.getName();
                    String value = field.get(null).toString();
                    if ("TIME".equals(name)) {
                        continue;
                    }
                    sb.append(name + "=" + value);
                    sb.append("\n");
                } catch (Exception e) {
                    e.printStackTrace();
                    continue;
                }
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Describe:获取应用的版本信息
     * <p/>
     * Date:2015-6-25
     * <p/>
     * Author:wangkui
     */
    public static String getVersionInfo(Context context) {
        if (null == context) {
            return "";
        }
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo info = pm.getPackageInfo(context.getPackageName(), 0);
            return info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Describe:获取设备唯一标识
     * <p/>
     * Date:2015-6-25
     * <p/>
     * Author:wangkui
     */
    public static String getDeviceUniqueId(Context context) {
        String deviceId = "";
        if (null != context) {
            TelephonyManager tm = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            /**
             * 唯一编号（IMEI, MEID, ESN, IMSI） 缺点 Android设备要具有电话功能 其工作不是很可靠 序列号
             * 当其工作时，该值保留了设备的重置信息（“恢复出厂设置”），从而可以消除当客户删除自己设备上的信息，并把设备转另一个人时发生的错误。
             */
            deviceId = tm.getDeviceId();
            if (!StringUtil.isEmpty(deviceId)) {
                return deviceId;
            }

            deviceId = tm.getSubscriberId();
            if (!StringUtil.isEmpty(deviceId)) {
                return deviceId;
            }
        }

        // 序列号 缺点序列号无法在所有Android设备上使用
        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class, String.class);
            deviceId = (String) (get.invoke(c, "ro.serialno", "unknown"));
            if (!StringUtil.isEmpty(deviceId)) {
                return deviceId;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * ANDROID_ID 缺点 对于Android 2.2（“Froyo”）之前的设备不是100％的可靠
         * 此外，在主流制造商的畅销手机中至少存在一个众所周知的错误，每一个实例都具有相同的ANDROID_ID。
         */
        if (null != context) {
            deviceId = Settings.Secure.getString(context.getContentResolver(),
                    Settings.Secure.ANDROID_ID);
            if (!StringUtil.isEmpty(deviceId)) {
                return deviceId;
            }
        }

        if (StringUtil.isEmpty(deviceId)) {
            deviceId = System.currentTimeMillis() + "";
        }
        return deviceId;

    }

    @SuppressWarnings("deprecation")
    public static void wakeUpAndUnlock(Context context) {
        KeyguardManager km = (KeyguardManager) context
                .getSystemService(Context.KEYGUARD_SERVICE);
        if (km.inKeyguardRestrictedInputMode()) {
            KeyguardManager.KeyguardLock kl = km.newKeyguardLock("unLock");
            // 解锁
            kl.disableKeyguard();
            // 获取电源管理器对象
            PowerManager pm = (PowerManager) context
                    .getSystemService(Context.POWER_SERVICE);
            // 获取PowerManager.WakeLock对象,后面的参数|表示同时传入两个值,最后的是LogCat里用的Tag
            PowerManager.WakeLock wl = pm.newWakeLock(
                    PowerManager.ACQUIRE_CAUSES_WAKEUP
                            | PowerManager.SCREEN_DIM_WAKE_LOCK, "bright");
            // 点亮屏幕
            wl.acquire();
            // 释放
            wl.release();

        }

    }
}
