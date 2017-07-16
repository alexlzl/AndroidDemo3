package com.anly.samples.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.anly.samples.IRemoteService;

/**
 * Created by mingjun on 2016/11/28.
 */

public class RemoteService extends Service {


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private IRemoteService.Stub mBinder = new IRemoteService.Stub() {

        @Override
        public int signing(int price) throws RemoteException {
            int signingPrice = price - 10;
            Log.d("mingjun", "signing: " + signingPrice);
            return signingPrice;
        }
    };
}
