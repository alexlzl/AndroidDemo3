package weeho.com.petim.util;

import android.util.Log;

/**
 * 日志工具类
 * 
 * @author Administrator
 * 
 */

public class LogUtil {
//    private static boolean bLog = MyApplication.debugToggle ? true
//            : false;
	 private static boolean bLog = true;
    private static final String TAG = "br_log";

    public static void d(String msg) {

        if (bLog) {

            Log.d(TAG, msg);

        }

    }

    public static void d(String Tag, String msg) {

        if (bLog) {

            Log.d(Tag, msg);

        }

    }

    public static void e(String Tag, String msg) {

        if (bLog) {

            Log.d(Tag, msg);

        }

    }

    public static void i(String msg) {

        if (bLog) {

            Log.i(TAG, msg);

        }

    }

    public static void e(String msg) {

        if (bLog) {

            Log.e(TAG, msg);

        }

    }

    public static void v(String msg) {

        if (bLog) {

            Log.v(TAG, msg);

        }

    }

    public static void w(String msg) {

        if (bLog) {

            Log.w(TAG, msg);

        }

    }

}
