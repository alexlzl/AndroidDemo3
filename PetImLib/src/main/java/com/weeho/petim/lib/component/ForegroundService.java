package com.weeho.petim.lib.component;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * 自定义前台服务，提升生存等级
 */

public class ForegroundService extends Service {
    public static final String ACTION_FOREGROUND = "com.bluerhion.library.service.FOREGROUND";
    public static final String ACTION_BACKGROUND = "com.bluerhion.library.service.BACKGROUND";
    public static final String KEY_NOTIFICATION = "notification";
    public static final String KEY_NOTIFY_ID = "notify_id";

    private static final Class<?>[] mSetForegroundSignature = new Class[]{
            boolean.class};
    private static final Class<?>[] mStartForegroundSignature = new Class[]{
            int.class, Notification.class};
    private static final Class<?>[] mStopForegroundSignature = new Class[]{
            boolean.class};

    private NotificationManager mNM;
    private Method mSetForeground;
    private Method mStartForeground;
    private Method mStopForeground;
    private Object[] mSetForegroundArgs = new Object[1];
    private Object[] mStartForegroundArgs = new Object[2];
    private Object[] mStopForegroundArgs = new Object[1];

    private Notification mNotification;
    private int mNotifyID;

    void invokeMethod(Method method, Object[] args) {
        try {
            method.invoke(this, args);
        } catch (InvocationTargetException e) {
        } catch (IllegalAccessException e) {
        }
    }

    /**
     * This is a wrapper around the new startForeground method, using the older
     * APIs if it is not available.
     */
    void startForegroundCompat(int id, Notification notification) {
        // If we have the new startForeground API, then use it.
        if (mStartForeground != null) {
            mStartForegroundArgs[0] = Integer.valueOf(id);
            mStartForegroundArgs[1] = notification;
            invokeMethod(mStartForeground, mStartForegroundArgs);
            return;
        }

        mSetForegroundArgs[0] = Boolean.TRUE;
        invokeMethod(mSetForeground, mSetForegroundArgs);

        mNM.notify(id, notification);
    }

    /**
     * This is a wrapper around the new stopForeground method, using the older
     * APIs if it is not available.
     */
    void stopForegroundCompat(int id) {
        // If we have the new stopForeground API, then use it.
        if (mStopForeground != null) {
            mStopForegroundArgs[0] = Boolean.TRUE;
            invokeMethod(mStopForeground, mStopForegroundArgs);
            return;
        }

        mNM.cancel(id);
        mSetForegroundArgs[0] = Boolean.FALSE;
        invokeMethod(mSetForeground, mSetForegroundArgs);
    }

    void stopForegroundCompat() {
        stopForegroundCompat(mNotifyID);
    }

    protected void stopService() {
        stopForegroundCompat();
        stopSelf();
    }

    @Override
    public void onCreate() {
        mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        try {
            mStartForeground = getClass().getMethod("startForeground",
                    mStartForegroundSignature);
            mStopForeground = getClass().getMethod("stopForeground",
                    mStopForegroundSignature);
            return;
        } catch (NoSuchMethodException e) {
            // Running on an older platform.
            mStartForeground = mStopForeground = null;
        }
        try {
            mSetForeground = getClass().getMethod("setForeground",
                    mSetForegroundSignature);
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException(
                    "OS doesn't have Service.startForeground OR Service.setForeground!");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Make sure our notification is gone.
        stopForegroundCompat(mNotifyID);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        handleCommand(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        handleCommand(intent);
        return START_STICKY;
    }

    protected void handleCommand(Intent intent) {
        if (null == intent) {
            return;
        }

        mNotifyID = intent.getIntExtra(KEY_NOTIFY_ID, 0);
        if (intent.hasExtra(KEY_NOTIFICATION)) {
            mNotification = intent.getParcelableExtra(KEY_NOTIFICATION);
        } else {
            mNotification = new Notification();
        }

        if (TextUtils.equals(ACTION_FOREGROUND, intent.getAction())) {
            startForegroundCompat(mNotifyID, mNotification);
        } else if (TextUtils.equals(ACTION_BACKGROUND, intent.getAction())) {
            // stopForegroundCompat(mNotifyID);
            stopForegroundCompat();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
