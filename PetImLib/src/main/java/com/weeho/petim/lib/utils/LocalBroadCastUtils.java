package com.weeho.petim.lib.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

/**
 * 本地广播管理器，安全广播
 */
public class LocalBroadCastUtils {

    private static volatile LocalBroadCastUtils INSTANCE;

    public static final LocalBroadCastUtils getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LocalBroadCastUtils();
        }
        return INSTANCE;
    }

    private LocalBroadcastManager mLocalBroadcastManager;

    private LocalBroadCastUtils() {
    }

    public void sendBroadcast(Context context, Intent intent) {
        if (mLocalBroadcastManager == null) {
            mLocalBroadcastManager = LocalBroadcastManager.getInstance(context);
        }
        mLocalBroadcastManager.sendBroadcast(intent);
    }

    public void registerReceiver(Context context, BroadcastReceiver receiver,
                                 IntentFilter filter) {
        if (mLocalBroadcastManager == null) {
            mLocalBroadcastManager = LocalBroadcastManager.getInstance(context);
        }
        mLocalBroadcastManager.registerReceiver(receiver, filter);
    }

    public void unregisterReceiver(BroadcastReceiver receiver) {
        mLocalBroadcastManager.unregisterReceiver(receiver);
    }

}
