package com.example.testprovider;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void queryall(View view){
		ContentResolver resolver=getContentResolver();
		Uri uri=Uri.parse("content://com.example.provider.ContactProvider/query");
		Cursor cursor=resolver.query(uri,null, null, null, null);
		while(cursor.moveToNext()){
			String name=cursor.getString(cursor.getColumnIndex("name"));
			String phone=cursor.getString(cursor.getColumnIndex("phone"));
			Log.i("msg","name:"+name+"   phone:"+phone);
		}
		cursor.close();
	}
	
	public void query(View view){
		ContentResolver resolver=getContentResolver();
		Uri uri=Uri.parse("content://com.example.provider.ContactProvider/query/123");
		Cursor cursor=resolver.query(uri,null, null, null, null);
		while(cursor.moveToNext()){
			String name=cursor.getString(cursor.getColumnIndex("name"));
			String phone=cursor.getString(cursor.getColumnIndex("phone"));
			Log.i("msg","name:"+name+"   phone:"+phone);
		}
		cursor.close();
	}
	
	public void insert(View view){
		ContentResolver resolver=getContentResolver();
		Uri uri=Uri.parse("content://com.example.provider.ContactProvider/insert");
		ContentValues values=new ContentValues();
		values.put("name", "zhansannnnnn");
		values.put("phone", "123131233213213232");
		resolver.insert(uri, values);
	}
}
