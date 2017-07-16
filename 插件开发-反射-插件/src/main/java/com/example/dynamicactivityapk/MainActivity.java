package com.example.dynamicactivityapk;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private static View parentView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(parentView == null){
			setContentView(R.layout.activity_main);
		}else{
			setContentView(parentView);
		}
		
		findViewById(R.id.btn).setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Toast.makeText(getApplicationContext(), "I came from ²å¼þ~~", Toast.LENGTH_LONG).show();
			}});
		
	}
	
	public static void setLayoutView(View view){
		parentView = view;
	}

}
