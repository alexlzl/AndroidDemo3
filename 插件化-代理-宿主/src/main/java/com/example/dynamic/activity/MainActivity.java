package com.example.dynamic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

public class MainActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void loadActivity(View view) {
		Intent intent = new Intent(this, ProxyActivity.class);
		startActivity(intent);
	}

}
