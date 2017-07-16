package com.example.aidl_server_csdn;


import com.example.aidl.IBase;
import com.example.aidl.UserInfo;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class AIDLService extends Service{

	String info="";
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return stub;
	}

	private IBase.Stub stub=new IBase.Stub() {

		/**
		 * 基本类型的使用示例
		 */
		@Override
		public int add(int i, int j) throws RemoteException {
			// TODO Auto-generated method stub
			return i+j;
		}
		/**
		 * Parcelable类userinfo的使用示例
		 */
		@Override
		public String getUserInfo(UserInfo userinfo) throws RemoteException {
			// TODO Auto-generated method stub
			String resultString="name:"+userinfo.getName()+" "+"adress:"+userinfo.getAdress()+" "+"age:"+userinfo.getAge();
			return resultString;
		}
		/**
		 * out修饰类型的使用
		 * 表示服务端输入
		 */
		@Override
		public void getaList(String[] list) throws RemoteException {
			// TODO Auto-generated method stub
			list[0]="服务端赋值信息:"+info;
		}

		/**
		 * inout修饰类型的使用示例
		 */
		@Override
		public void gettList(String[] list) throws RemoteException {
			// TODO Auto-generated method stub
			String totalString="";
			/**
			 * 从客户端取得的信息
			 */
			String receviceFromClientString="";
			for(int i=0;i<list.length;i++)
			{
				receviceFromClientString+=list[i];
			}
			/**
			 * 从服务端返回的信息
			 */
			totalString+="从客户端收到的信息为："+receviceFromClientString+" \n在此我们新增一段返回信息:good";
			list[0]=totalString;
		}
		/**
		 * in修饰类型的使用示例
		 */
		@Override
		public void setaList(String[] list) throws RemoteException {
			// TODO Auto-generated method stub
			/**
			 * 取得客户端传入的值
			 */
			if(list.length>0)
				info=list[0];
		}
		
	};
}
