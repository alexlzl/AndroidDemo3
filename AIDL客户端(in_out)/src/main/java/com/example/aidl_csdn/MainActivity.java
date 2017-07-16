package com.example.aidl_csdn;

import com.example.aidl.IBase;
import com.example.aidl.UserInfo;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.R.integer;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;

public class MainActivity extends Activity implements OnClickListener{

	IBase iBase;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button btn=(Button)findViewById(R.id.btn);
		btn.setOnClickListener(this);
		Button btn1=(Button)findViewById(R.id.btn1);
		btn1.setOnClickListener(this);
		Button btn2=(Button)findViewById(R.id.btn2);
		btn2.setOnClickListener(this);
		Button btn3=(Button)findViewById(R.id.btn3);
		btn3.setOnClickListener(this);
		Button btn4=(Button)findViewById(R.id.btn4);
		btn4.setOnClickListener(this);
		Button btn5=(Button)findViewById(R.id.btn5);
		btn5.setOnClickListener(this);
	}

	/**
	 * 连接AIDL
	 */
	public void Connect()
	{
		bindService(new Intent("com.service.use"), serviceConnection, Context.BIND_AUTO_CREATE);
	}
	/**
	 * 连接类实现
	 */
	ServiceConnection serviceConnection=new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			iBase=null;
			Toast.makeText(MainActivity.this, "连接断开", Toast.LENGTH_SHORT).show();
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			iBase=IBase.Stub.asInterface(service);
			Toast.makeText(MainActivity.this, "连接成功", Toast.LENGTH_SHORT).show();
		}
	};
	
	/**
	 * 基础类型相加
	 * @return
	 * @throws RemoteException
	 */
	public  int  sum() {
		if(iBase!=null)
		{
			int result=0;
			try {
				result = iBase.add(7, 8);
				Toast.makeText(getApplicationContext(), "基础类型相加结果:"+result, Toast.LENGTH_SHORT).show();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		}
		return 0;
	}
	/**
	 * in型传值到服务端
	 */
	public void setaList()
	{
		if(iBase!=null)
		{
			try {
				iBase.setaList(new String[]{"战国剑"});
				Toast.makeText(getApplicationContext(), "传值'战国剑'到服务端", Toast.LENGTH_SHORT).show();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * out型取服务端返回值
	 */
	public void getaList()
	{
		if(iBase!=null)
		{
			String[] list =new String[1];
			try {
				iBase.getaList(list);
				Toast.makeText(getApplicationContext(), "服务端返回内容："+list[0], Toast.LENGTH_SHORT).show();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * Parcelable类的传入
	 */
	public void ParcelableUse()
	{
		if(iBase==null)
			return;
		UserInfo userInfo=new UserInfo();
		userInfo.setName("战国剑");
		userInfo.setAdress("中国");
		userInfo.setAge(18);
		 try {
			 String resultString=iBase.getUserInfo(userInfo);
			Toast.makeText(getApplicationContext(), "服务端返回内容："+resultString, Toast.LENGTH_SHORT).show();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * inout类型修饰的使用
	 */
	public void inoutUse()
	{
		if(iBase==null)
			return;
		try {
			String[] inStrings={"inout中in的传入"};
			iBase.gettList(inStrings);
			Toast.makeText(getApplicationContext(), "inout服务端返回内容："+inStrings[0], Toast.LENGTH_SHORT).show();
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn:
		Connect();
			break;
		case R.id.btn1:
			sum();
			break;
		case R.id.btn2:
			ParcelableUse();
			break;
		case R.id.btn3:
			setaList();
			break;
		case R.id.btn4:
			getaList();
			break;
		case R.id.btn5:
			inoutUse();
			break;

		default:
			break;
		}
	}

}
