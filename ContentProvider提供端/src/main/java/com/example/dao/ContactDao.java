package com.example.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.entity.Contact;


public class ContactDao extends SQLiteOpenHelper {

	public ContactDao(Context ctx) {
		super(ctx, "db_outman", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table t_contact (_id integer primary key autoincrement, name varchar(50), phone varchar(50))");
		
		db.execSQL("insert into t_contact (name,phone) values('phone','12345678901')");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	public Cursor findAllContacts() {
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor = db.rawQuery("select _id, name, phone from t_contact",
				null);
		return cursor;
	}
	
	public Cursor findContact(String name){
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor = db.rawQuery("select _id, name, phone from t_contact where name=?",
				new String[]{name});
		return cursor;
	}


	public boolean insertContact(Contact contact) {
		SQLiteDatabase db = getWritableDatabase();
		String sql="insert into t_contact(name,phone) values('%s','%s')";
		sql=String.format(sql, contact.getName(),contact.getPhone());
		System.out.println(sql);
		try {
			db.execSQL(sql);
		} catch (SQLException e) {
			return false;
		}
		return true;

	}


	public void deleteContact(int id) {
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL("delete from t_contact where _id=" + id);
	}
	public void deleteContact(String name) {
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL("delete from t_contact where name=?",new String[]{name});
	}
}
