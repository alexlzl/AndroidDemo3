package me.homeway.servicebinder;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MainService extends Service {

    private String TAG = "MainService";
    public ServiceBinder mBinder = new ServiceBinder(); /* 数据通信的桥梁 */


    @Override
    public void onCreate() {
        Toast.makeText(MainService.this, "Service Create, 2秒后发送广播...", Toast.LENGTH_SHORT).show();
        try {
            Thread.sleep(2);
            SendServiceBroadCast();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.e(TAG, "ServiceThread===>>onCreatre===>>线程ID:" + Thread.currentThread().getId());
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand:==================");
//       new Thread(new Runnable() {
//           @Override
//           public void run() {
//               for(int i=0;i<20;i++){
//
//                   Log.e(TAG, "onStartCommand:==================");
//                   try {
//                     Thread.sleep(1000);
//                   } catch (InterruptedException e) {
//                       e.printStackTrace();
//                   }
//               }
//           }
//       }).start();

        Toast.makeText(MainService.this, "Service StartCommand", Toast.LENGTH_SHORT).show();

        return super.onStartCommand(intent, flags, startId);
    }

    /* 重写Binder的onBind函数，返回派生类 */
    @Override
    public IBinder onBind(Intent arg0) {
        Log.e(TAG, "onBind:==================");
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG, "onUnbind:==================");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.e(TAG, "onRebind:==================");
        super.onRebind(intent);
    }

    @Override
    public void onDestroy() {

        Toast.makeText(MainService.this, "Service Destroy", Toast.LENGTH_SHORT).show();
        Log.e(TAG, "ServiceThread===>>onDestroy===>>线程ID:" + Thread.currentThread().getId());
    }

    /* 第一种模式通信：Binder */
    public class ServiceBinder extends Binder {

        /* 返回当前类的一个函数 */
        MainService getService() {
            return MainService.this;
        }

        public void startDownload() throws InterruptedException {
            Log.d(TAG, "ServiceThread===>>startDownload() executed===>>线程ID:" + Thread.currentThread().getId());
            /* 模拟下载，休眠2秒 */
            Toast.makeText(MainService.this, "模拟下载2秒钟,开始下载...", Toast.LENGTH_SHORT).show();
            Thread.sleep(2);
            Toast.makeText(MainService.this, "下载结束...", Toast.LENGTH_SHORT).show();
        }
    }

    /* 第二种模式通信：Broadcast广播 */
    public void SendServiceBroadCast() throws InterruptedException {
        Log.d(TAG, "ServiceThread===>>startDownload() executed===>>线程ID:" + Thread.currentThread().getId());
        Toast.makeText(MainService.this, "Send BroadCast now...", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.setAction("me.homeway.servicebinder.BroadcastTest");
        intent.putExtra("value", 1000);
        sendBroadcast(intent);
        Toast.makeText(MainService.this, "Sent! Did you receive?", Toast.LENGTH_SHORT).show();
    }


}