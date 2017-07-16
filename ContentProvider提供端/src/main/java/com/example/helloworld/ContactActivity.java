package com.example.helloworld;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dao.ContactDao;
import com.example.util.Action;

public class ContactActivity extends Activity {

	private ContactDao contactDao;
	private ListView lv;
	private Cursor cursor;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact);

		lv=(ListView) findViewById(R.id.lv_contact_contacts);
		contactDao = new ContactDao(ContactActivity.this);
		cursor = contactDao.findAllContacts();

		lv.setAdapter(new SimpleCursorAdapter(ContactActivity.this,
				R.layout.contact_lv_contacts_layout, cursor,
				new String[] { "name", "phone" }, new int[] {
				R.id.tv_lv_contact_name, R.id.tv_lv_contact_phone }));

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					final int position, long id) {
				Builder builder = new AlertDialog.Builder(ContactActivity.this);
				builder.setTitle("dd");
				builder.setItems(new String[] { "d", "f" },
						new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog,
							int which) {
						if (which == 0) {
							// Update
						} else if (which == 1) {
							// Delete
							new DeleteAction(position).execute();
						}
					}
				});
				builder.create().show();
			}
		});
	}
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		if(cursor==null){
			return;
		}
		cursor.requery();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add("ww");
		menu.add("dd");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getTitle().equals("dd")){
			Intent intent=new Intent(ContactActivity.this,AddContactActivity.class);
			startActivity(intent);
		}
		if(item.getTitle().equals("ff")){

		}
		return true;
	}
	private class DeleteAction implements Action {
		private int position;

		DeleteAction(int position) {
			this.position = position;
		}

		@Override
		public void execute() {
			Builder builder = new AlertDialog.Builder(ContactActivity.this);
			builder.setTitle("ee");
			builder.setMessage("ee");
			builder.setPositiveButton("ee", new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					if (cursor.moveToPosition(position)) {
						int id = cursor.getInt(0);
						contactDao.deleteContact(id);
						cursor.requery();
					}
				}
			});
			builder.setNegativeButton("ww", new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
				}
			});
			builder.create().show();
		}

	}
}
