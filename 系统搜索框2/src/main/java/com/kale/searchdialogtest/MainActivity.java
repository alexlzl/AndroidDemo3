package com.kale.searchdialogtest;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * @author:Jack Tony
 * @description :
 * 
 *              当系统调用onNewIntent(Intent)的时候，表示activity并不是新建的, 所以getIntent()返回的还是
 *              在onCreate()中接受到的intent.
 *              因此你必须在onNewIntent(Intent)调用setIntent(Intent)来
 *              (这样保存的intent才被更新，之后你可以同过getIntent()来取得它).
 * 
 * @date :2015年1月15日
 */
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
		setIntent(intent);
		handleIntent(intent);
	}

	private void handleIntent(Intent intent) {
		if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
			String query = intent.getStringExtra(SearchManager.QUERY);
			doMySearch(query);
		}
	}

	private void doMySearch(String query) {
		// TODO 自动生成的方法存根
		Toast.makeText(this, "do search " + query, 0).show();
	}

}
