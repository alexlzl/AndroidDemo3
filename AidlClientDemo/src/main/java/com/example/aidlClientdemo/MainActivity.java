package com.example.aidlClientdemo;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener  {
	private Button btn_sendBaseType,btn_sendCustomType;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn_sendBaseType=(Button) findViewById(R.id.btn_sendBaseType);
		btn_sendCustomType=(Button) findViewById(R.id.btn_sendCustomType);
		btn_sendBaseType.setOnClickListener(this);
		btn_sendCustomType.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent=null;
		switch(v.getId()){
		case R.id.btn_sendBaseType:
			intent=new Intent(MainActivity.this, BaseTypeActivity.class);
			startActivity(intent);
			break;
			
		case R.id.btn_sendCustomType:
			intent=new Intent(MainActivity.this, CustomTypeActivity.class);
			startActivity(intent);
			break;
		}
	}

}
