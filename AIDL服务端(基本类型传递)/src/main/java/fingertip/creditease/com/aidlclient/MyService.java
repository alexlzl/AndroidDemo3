package fingertip.creditease.com.aidlclient;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import fingertip.creditease.com.aidl.IMyAidlInterface;

/**
 * Describe：
 * <p>
 * Author：LZL
 * <p>
 * Date：2017/1/6 下午5:47
 */

public class MyService extends Service {
    public MyService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("TAG", "MyService===onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("TAG", "MyService===onDestroy");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.e("TAG", "MyService===onLowMemory");
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.e("TAG", "MyService===onRebind");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(" ", "MyService===onStartCommand");
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
//        return  null;
    }

    class MyBinder extends IMyAidlInterface.Stub {

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public String getName() throws RemoteException {
            return "test";
        }
    }
}
