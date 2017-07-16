package com.kale.searchdialogtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button btn = (Button) findViewById(R.id.show_dialog_button);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				onSearchRequested();

			}
		});

	}
	
	@Override
	protected void onNewIntent(Intent intent) {
		// TODO 自动生成的方法存根
		super.onNewIntent(intent);
	}

	// 重写onSearchRequested方法
	@Override
	public boolean onSearchRequested() {
		// 除了输入查询的值，还可额外绑定一些数据
		Bundle appSearchData = new Bundle();
		appSearchData.putString("KEY", "text");

		startSearch(null, false, appSearchData, false);
		// 必须返回true。否则绑定的数据作废
		return true;
	}



}
