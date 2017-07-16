package com.weeho.petim.lib.utils;

import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * 配置工具类
 */
public class SharedPreferencesUtil {
    public static final String APP_EXCEPTION = "AppException";
    public static final String APP_EXCEPTION_KEY = "app_exception_key";
    public static final String APP_EXCEPTION_EXIST = "AppExceptionExist";
    public static final String APP_EXCEPTION_EXIST_KEY = "app_exception_exist_key";

    public static void writeSharedPreferencesBoolean(Context context,
                                                     String spName, String key, boolean value) {
        if (null == context || null == spName || null == key) {
            return;
        }
        SharedPreferences user = context.getSharedPreferences(spName,
                Context.MODE_PRIVATE);
        Editor editor = user.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static boolean getSharedPreferencesBoolean(Context context,
                                                      String spName, String key, boolean defValue) {
        if (null == context || null == spName || null == key) {
            return defValue;
        }
        SharedPreferences user = context.getSharedPreferences(spName,
                Context.MODE_PRIVATE);
        return user.getBoolean(key, defValue);
    }

    public static void writeSharedPreferencesString(Context context,
                                                    String spName, String key, String value) {
        if (null == context || null == spName || null == key) {
            return;
        }
        SharedPreferences user = context.getSharedPreferences(spName,
                Context.MODE_PRIVATE);
        Editor editor = user.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getSharedPreferencesString(Context context,
                                                    String spName, String key, String defValue) {
        if (null == context || null == spName || null == key) {
            return defValue;
        }
        SharedPreferences user = context.getSharedPreferences(spName,
                Context.MODE_PRIVATE);
        return user.getString(key, defValue);
    }

    public static void writeSharedPreferencesInt(Context context,
                                                 String spName, String key, int value) {
        if (null == context || null == spName || null == key) {
            return;
        }
        SharedPreferences user = context.getSharedPreferences(spName,
                Context.MODE_PRIVATE);
        Editor editor = user.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static void writeSharedPreferencesDouble(Context context,
                                                    String spName, String key, double value) {
        if (null == context || null == spName || null == key) {
            return;
        }
        SharedPreferences user = context.getSharedPreferences(spName,
                Context.MODE_PRIVATE);
        Editor editor = user.edit();
        editor.putFloat(key, (float) value);
        editor.commit();
    }

    public static double getSharedPreferencesDouble(Context context,
                                                    String spName, String key, double value) {
        if (null == context || null == spName || null == key) {
            return (float) value;
        }
        SharedPreferences user = context.getSharedPreferences(spName,
                Context.MODE_PRIVATE);
        return user.getFloat(key, (float) value);
    }

    public static int getSharedPreferencesInt(Context context, String spName,
                                              String key, int value) {
        if (null == context || null == spName || null == key) {
            return value;
        }
        SharedPreferences user = context.getSharedPreferences(spName,
                Context.MODE_PRIVATE);
        return user.getInt(key, value);
    }

    public static void ClearSharedPreferences(Context context, String spName) {
        if (null == context || null == spName) {
            return;
        }
        SharedPreferences user = context.getSharedPreferences(spName,
                Context.MODE_PRIVATE);
        Editor editor = user.edit();
        editor.clear();
        editor.commit();
    }

    public static void writeSharedPreferencesMap(Context context,
                                                 String spName, Map<String, String> map) {
        if (null == context || null == spName || null == map) {
            return;
        }
        SharedPreferences user = context.getSharedPreferences(spName,
                Context.MODE_PRIVATE);
        Editor editor = user.edit();

        for (String key : map.keySet()) {
            editor.putString(key, map.get(key));
        }
        editor.commit();
    }

}
