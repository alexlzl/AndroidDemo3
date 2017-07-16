package com.searchview.demo;

import android.os.Bundle;
import android.app.Activity;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.view.Menu;
import android.widget.Button;
import android.widget.SearchView;

public class SearchActivity extends Activity {
	ContentResolver resolver;
	Button button;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// provider=new DataBaseProvider();
		resolver = getContentResolver();
		resolver.query(Contacts.CONTENT_URI, new String[] { Contacts.NAME }, null, null, null);
		insertContacts();
	}

	private void insertContacts() {
		ContentValues values = new ContentValues();
		values.put(Contacts.NAME, "zhangsan");
		resolver.insert(Contacts.CONTENT_URI, values);
		values.clear();
		values.put(Contacts.NAME, "lisi");
		resolver.insert(Contacts.CONTENT_URI, values);
		values.clear();
		values.put(Contacts.NAME, "wangwu");
		resolver.insert(Contacts.CONTENT_URI, values);
		values.clear();
		values.put(Contacts.NAME, "chenliu");
		resolver.insert(Contacts.CONTENT_URI, values);
		values.clear();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.options_menu, menu);
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
		SearchableInfo info = searchManager.getSearchableInfo(getComponentName());
		searchView.setSearchableInfo(info);

		searchView.setIconifiedByDefault(false);
		return true;
	}

}
