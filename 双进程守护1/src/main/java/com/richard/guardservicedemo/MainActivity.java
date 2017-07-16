package com.richard.guardservicedemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Intent localService = new Intent(this, LocalService.class);
		this.startService(localService);
		
		Intent remoteService = new Intent(this, RemoteService.class);
		this.startService(remoteService);
	}

}
