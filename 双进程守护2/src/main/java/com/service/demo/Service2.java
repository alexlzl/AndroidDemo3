package com.service.demo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.Toast;

public class Service2 extends Service {
	private String TAG = getClass().getName();
	private String Process_Name = "com.example.servicetest2:service1";

	/**
	 *启动Service1 
	 */
	private StrongService startS1 = new StrongService.Stub() {

		@Override
		public void stopService() throws RemoteException {
			Intent i = new Intent(getBaseContext(), Service1.class);
			getBaseContext().stopService(i);
		}

		@Override
		public void startService() throws RemoteException {
			Intent i = new Intent(getBaseContext(), Service1.class);
			getBaseContext().startService(i);
			
		}
	};
	
	@Override
	public void onTrimMemory(int level){
		Toast.makeText(getBaseContext(), "Service2 onTrimMemory..."+level, Toast.LENGTH_SHORT)
		.show();
		keepService1();
	}
	
	public void onCreate() {
		Toast.makeText(Service2.this, "Service2 onCreate...", Toast.LENGTH_SHORT).show();
		keepService1();
	}

	/**
	 * 判断Service1是否还在运行，如果不是则启动Service1
	 */
	private  void keepService1(){
		boolean isRun = Utils.isProessRunning(Service2.this, Process_Name);
		if (isRun == false) {
			try {
				Toast.makeText(getBaseContext(), "重新启动 Service1", Toast.LENGTH_SHORT).show();
				startS1.startService();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return START_STICKY;
	}

	@Override
	public IBinder onBind(Intent intent) {
		return (IBinder) startS1;
	}

}
