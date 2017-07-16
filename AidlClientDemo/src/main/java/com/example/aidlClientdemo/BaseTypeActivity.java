package com.example.aidlClientdemo;

import com.example.aidlservicedemo.domain.IDog;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class BaseTypeActivity extends Activity {
	private Button btn_startService, btn_endService,btn_getServiceData;
	private IDog dogService;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_service);

		btn_startService = (Button) findViewById(R.id.btn_startService);
		btn_endService = (Button) findViewById(R.id.btn_endService);
		btn_getServiceData = (Button) findViewById(R.id.btn_getServiceData);

		btn_startService.setOnClickListener(click);
		btn_endService.setOnClickListener(click);
		btn_getServiceData.setOnClickListener(click);
	}

	private View.OnClickListener click = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_startService:
				startService();
				break;
			case R.id.btn_endService:
				endService();
				break;
			case R.id.btn_getServiceData:
				getServiceDate();
				break;
			}
		}				
	};
	/*
	 * 获取数据
	 */
	private void getServiceDate() {
		try {
			if(dogService!=null){
				StringBuilder sBuilder=new StringBuilder();
				sBuilder.append("name:"+dogService.getName());
				sBuilder.append("\nage:"+dogService.getAge());
				Toast.makeText(BaseTypeActivity.this, sBuilder.toString(), Toast.LENGTH_SHORT).show();
			}
			else
			{
				Toast.makeText(BaseTypeActivity.this, "请先绑定服务", Toast.LENGTH_SHORT).show();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	private ServiceConnection connBase=new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			dogService=null;
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// IDog.Stub.asInterface，获取接口
			dogService=IDog.Stub.asInterface(service);
		}
	};
	
	/**
	 * 开始服务
	 */
	private void startService(){
		Intent intent=new Intent();
		intent.setAction("cn.bgxt.Service.BASE_TYPE_SERVICE");
		bindService(intent, connBase, BIND_AUTO_CREATE);
		Toast.makeText(BaseTypeActivity.this, "开始绑定服务", Toast.LENGTH_SHORT).show();
	}
	/**
	 * 停止服务
	 */
	private void endService(){
		if(connBase!=null)
		{
			unbindService(connBase);
			Toast.makeText(BaseTypeActivity.this, "服务解除绑定", Toast.LENGTH_SHORT).show();
		}
	}	
}
