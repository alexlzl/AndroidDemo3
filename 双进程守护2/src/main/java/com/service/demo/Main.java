package com.service.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.servicetest2.R;
/**
 * Service双进程，一键清理后复活
 *
 *
 */
public class Main extends Activity implements OnClickListener {

	private Button btn1,btn2;
	//AIDL,此处用于bindService
	private String TAG = getClass().getName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		btn1 = (Button) findViewById(R.id.button1);
		btn2 = (Button) findViewById(R.id.button2);
		
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
	
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		
		case R.id.button1:
			Intent i1 = new Intent(Main.this,Service1.class);
			startService(i1);

			Intent i2 = new Intent(Main.this,Service2.class);
			startService(i2);
			break;

		case R.id.button2:
			//关闭Activity
			this.finish();
			break;
		}
	}

}
