package com.kale.searchdialogtest;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author:Jack Tony
 * @description :真正执行搜索和结果展示的Activity 一旦用户在search dialog中执行search操作,
 *              系统将启动SearchableActivity 并向其传送ACTION_SEARCH intent.
 * @date :2015年1月15日
 * 
 * 参考自：http://zhouyunan2010.iteye.com/blog/1134147
 */
public class SearchActivity extends Activity {

	protected void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_activity);

		// Get the intent, verify the action and get the query
		Intent intent = getIntent();
		if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
			String query = intent.getStringExtra(SearchManager.QUERY);

			doMySearch(query);
		}

		// 获得额外递送过来的值
		Bundle appData = intent.getBundleExtra(SearchManager.APP_DATA);
		if (appData != null) {
			String testValue = appData.getString("KEY");
			System.out.println("extra data = " + testValue);
		}

	}

	private void doMySearch(String query) {
		// TODO 自动生成的方法存根
		TextView textView = (TextView) findViewById(R.id.search_result_textView);
		textView.setText(query);
		Toast.makeText(this, "do search", 0).show();
	}
}
