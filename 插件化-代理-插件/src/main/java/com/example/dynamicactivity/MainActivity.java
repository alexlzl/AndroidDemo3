package com.example.dynamicactivity;

import com.example.dynamicactivity.R;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends BaseActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void onDestroy() {
		//这里需要注意的是，不能调用super.onDestroy的方法了，不然报错，原因也很简单，这个Activity实质上不是真正的Activity了，没有生命周期的概念了，调用super的方法肯定报错
		Log.i("demo", "onDestory");
	}

	@Override
	protected void onPause() {
		Log.i("demo", "onPause");
	}

	@Override
	protected void onResume() {
		Log.i("demo", "onResume");
	}

	@Override
	protected void onStart() {
		Log.i("demo", "onStart");
	}

	@Override
	protected void onStop() {
		Log.i("demo", "onStop");
	}
	
	

}
