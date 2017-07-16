package com.richard.guardservicedemo;

import com.richard.guardservicedemo.aidl.GuardAidl;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

/**
 * 本地服务
 * @author Richard
 *
 */
public class LocalService extends Service{
	
	private MyBilder mBilder;
	
	@Override
	public IBinder onBind(Intent intent) {
		if (mBilder == null)
			mBilder = new MyBilder();
		
		return mBilder;
		
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		//在LocalService运行后,我们对RemoteService进行绑定。 把优先级提升为前台优先级
		this.bindService(new Intent(LocalService.this, RemoteService.class), 
				connection, Context.BIND_ABOVE_CLIENT);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		return super.onStartCommand(intent, flags, startId);
	}
	
	private class MyBilder extends GuardAidl.Stub{

		@Override
		public void doSomething() throws RemoteException {
			Log.i("TAG", "绑定成功!");
			Intent localService = new Intent(LocalService.this, RemoteService.class);
			LocalService.this.startService(localService);
			LocalService.this.bindService(new Intent(LocalService.this, RemoteService.class), 
					connection, Context.BIND_ABOVE_CLIENT);
		}
		
	}
	
	private ServiceConnection connection = new ServiceConnection() {
		
		/**
		 * 在终止后调用,我们在杀死服务的时候就会引起意外终止,就会调用onServiceDisconnected
		 * 则我们就得里面启动被杀死的服务,然后进行绑定
		 */
		@Override
		public void onServiceDisconnected(ComponentName name) {
			Log.i("TAG", "RemoteService被杀死了");
			Intent localService = new Intent(LocalService.this, RemoteService.class);
			LocalService.this.startService(localService);
			LocalService.this.bindService(new Intent(LocalService.this, RemoteService.class), 
					connection, Context.BIND_ABOVE_CLIENT);
			Toast.makeText(LocalService.this, "RemoteService被杀死!", Toast.LENGTH_SHORT).show();
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			Log.i("TAG", "RemoteService链接成功!");
			try {
				if(mBilder != null)
					mBilder.doSomething();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};


}
