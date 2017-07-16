package com.example.aidlservicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.example.aidlservicedemo.domain.IDog.Stub;

import java.util.Random;

public class BaseTypeService extends Service {
	private final String TAG="main";
	private DogBinder binder=null;
	private String[] names=new String[]{"bb","gg","ll"};
	private int[] ages=new int[]{1,2,3};

	public class DogBinder extends Stub{

		@Override
		public String getName() throws RemoteException {
			Random random=new Random();
			int nextInt = random.nextInt(2);			
			return names[nextInt];
		}

		@Override
		public int getAge() throws RemoteException {
			Random random=new Random();
			int nextInt = random.nextInt(2);			
			return ages[nextInt];
		}		
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		binder=new DogBinder();
	}

	@Override
	public IBinder onBind(Intent intent) {
		return binder;
	}
}
