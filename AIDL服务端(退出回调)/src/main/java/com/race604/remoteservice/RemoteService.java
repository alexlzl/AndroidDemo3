package com.race604.remoteservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

import com.race604.servicelib.IParticipateCallback;
import com.race604.servicelib.IRemoteService;

import java.util.ArrayList;
import java.util.List;

public class RemoteService extends Service {

    private static final String TAG = "TAG";

    private List<Client> mClients = new ArrayList<>();
    /**
     * RemoteCallbackList（远程接口回调）
     客户端通过ServiceConnection对象获取返回的IBinder对象，从而调用远程Service的方法。

     Service通过客户端注册的回调来调用客户端的方法。RemoteCallbackList就是用来保存注册进来的回调实例，它自动处理了Link-To-Death的问题，当客户端意外退出时，自动删掉对应的实例。
     *

     */

    private RemoteCallbackList<IParticipateCallback> mCallbacks = new RemoteCallbackList<>();

    private final IRemoteService.Stub mBinder = new IRemoteService.Stub() {
        @Override
        public int someOperate(int a, int b) throws RemoteException {
            Log.e(TAG, "called RemoteService someOperate()");
            return a + b;
        }

        @Override
        public void join(IBinder token, String name) throws RemoteException {
            int idx = findClient(token);
            if (idx >= 0) {
                Log.e(TAG, "already joined");
                return;
            }

            Client client = new Client(token, name);

            // 注册客户端死掉的通知
            token.linkToDeath(client, 0);
            mClients.add(client);

            // 通知client加入
            notifyParticipate(client.mName, true);
        }

        @Override
        public void leave(IBinder token) throws RemoteException {
            int idx = findClient(token);
            if (idx < 0) {
                Log.e(TAG, "already left");
                return;
            }

            Client client = mClients.get(idx);
            mClients.remove(client);

            // 取消注册
            client.mToken.unlinkToDeath(client, 0);

            // 通知client离开
            notifyParticipate(client.mName, false);
        }

        @Override
        public List<String> getParticipators() throws RemoteException {
            ArrayList<String> names = new ArrayList<>();
            for (Client client : mClients) {
                names.add(client.mName);
            }
            return names;
        }

        @Override
        public void registerParticipateCallback(IParticipateCallback cb) throws RemoteException {
            mCallbacks.register(cb);
        }

        @Override
        public void unregisterParticipateCallback(IParticipateCallback cb) throws RemoteException {
            mCallbacks.unregister(cb);
        }
    };

    public RemoteService() {
    }

    private int findClient(IBinder token) {
        for (int i = 0; i < mClients.size(); i++) {
            if (mClients.get(i).mToken == token) {
                return i;
            }
        }
        return -1;
    }



    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "service===onDestroy " );
        // 取消掉所有的回调
        mCallbacks.kill();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "service===onCreate " );
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.e(TAG, "service===onLowMemory " );
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.e(TAG, "service===onRebind " );
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "service===onStartCommand " );
        return super.onStartCommand(intent, flags, startId);
    }

    private final class Client implements IBinder.DeathRecipient {
        public final IBinder mToken;
        public final String mName;

        public Client(IBinder token, String name) {
            mToken = token;
            mName = name;
        }

        @Override
        public void binderDied() {
            // 客户端死掉，执行此回调
            int index = mClients.indexOf(this);
            if (index < 0) {
                return;
            }

            Log.e(TAG, "client died: " + mName);
            mClients.remove(this);

            // 通知client离开
            notifyParticipate(mName, false);
        }
    }

    private void notifyParticipate(String name, boolean joinOrLeave) {
        final int len = mCallbacks.beginBroadcast();
        for (int i = 0; i < len; i++) {
            try {
                // 通知回调
                mCallbacks.getBroadcastItem(i).onParticipate(name, joinOrLeave);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        mCallbacks.finishBroadcast();
    }
}
