package com.weeho.petim.lib.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @describe:获取网络状态
 * @author:wangkui
 * @time:2014-11-4上午10:26:19
 */

public class NetWorkState {

    /**
     * @describe:是否有活动的网络连接
     * @author:wangkui
     * @time:2014-11-4上午10:26:29
     */
    public static final boolean isNetWorkConnection(Context context) {
        if (context != null) {
            // 获取连接活动管理器
            final ConnectivityManager connectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            // 获取链接网络信息
            final NetworkInfo networkInfo = connectivityManager
                    .getActiveNetworkInfo();

            return (networkInfo != null && networkInfo.isAvailable());
        }
        return false;

    }

    /**
     * @describe:是否为wifi网络
     * @author:wangkui
     * @time:2014-11-4上午10:26:41
     */
    public static final boolean isWifiConnection(Context context) {
        final ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        // 是否有网络并且已经连接
        return (networkInfo != null && networkInfo.isConnectedOrConnecting());

    }

    /**
     * @describe:是否为移动网络
     * @author:wangkui
     * @time:2014-1-26下午1:39:12
     */

    public static final boolean isGPRSConnection(Context context) {
        // 获取活动连接管理器
        final ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        return (networkInfo != null && networkInfo.isAvailable());

    }

    /**
     * @describe:返回网络类型
     * @author:wangkui
     * @time:2014-11-4上午10:26:51
     */
    public static final int getNetWorkConnectionType(Context context) {
        final ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo wifiNetworkInfo = connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        final NetworkInfo mobileNetworkInfo = connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if (wifiNetworkInfo != null && wifiNetworkInfo.isAvailable()) {
            return ConnectivityManager.TYPE_WIFI;
        } else if (mobileNetworkInfo != null && mobileNetworkInfo.isAvailable()) {
            return ConnectivityManager.TYPE_MOBILE;
        } else {
            return -1;
        }

    }
}
