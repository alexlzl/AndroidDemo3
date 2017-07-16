package com.example.liuwangshu.myprogress;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import java.util.List;

/**
 * Created by Moon on 2015/12/22.
 */
public class MyApplication extends Application {
    private static final String TAG = "wangshu";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "MyApplication======" +"onCreate");
        String processName = getProcessName(this, android.os.Process.myPid());
        if (!TextUtils.isEmpty(processName) && processName.equals(this.getPackageName())) {//判断进程名，保证只有主进程运行
            //主进程初始化逻辑
            Log.i(TAG, "主进程名称======" +processName);

        }

//        int pid = android.os.Process.myPid();
//        Log.i(TAG, "MyApplication is oncreate====" + "pid=" + pid);
//        String processNameString = "";
//        ActivityManager mActivityManager = (ActivityManager) this.getSystemService(getApplicationContext().ACTIVITY_SERVICE);
//        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager.getRunningAppProcesses()) {
//            if (appProcess.pid == pid) {
//                processNameString = appProcess.processName;
//                Log.i(TAG, "processName=" + processNameString);
//            }
//            Log.i(TAG, "进程名称=" + appProcess.processName+"\n"+"进程id=="+appProcess.pid);
//        }
//        if ("com.example.liuwangshu.myprogress".equals(processNameString)) {
//            Log.i(TAG, "processName=" + processNameString + "-----work");
//        } else {
//            Log.i(TAG, "processName=" + processNameString + "-----work");
//        }
//
   }

    /**
     * 获取当前运行进程的名称：
     *
     * 方法2
     * public static String getProcessName() {
     try {
     File file = new File("/proc/" + android.os.Process.myPid() + "/" + "cmdline");
     BufferedReader mBufferedReader = new BufferedReader(new FileReader(file));
     String processName = mBufferedReader.readLine().trim();
     mBufferedReader.close();
     return processName;
     } catch (Exception e) {
     e.printStackTrace();
     return null;
     }
     }

     * @param cxt
     * @param pid
     * @return
     */
    public static String getProcessName(Context cxt, int pid) {
        ActivityManager am = (ActivityManager) cxt.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo appProcess : runningApps) {
            Log.i(TAG, "进程名称=" + appProcess.processName+"\n"+"进程id=="+appProcess.pid);
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

}
