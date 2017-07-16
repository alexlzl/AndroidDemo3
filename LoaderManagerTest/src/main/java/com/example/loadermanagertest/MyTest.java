package com.example.loadermanagertest;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.test.AndroidTestCase;
import android.util.Log;

public class MyTest extends AndroidTestCase {

    public MyTest() {
        // TODO Auto-generated constructor stub

    }

    public void calltest() {
        ContentResolver contentResolver = getContext().getContentResolver();
        Uri uri = Uri
                .parse("content://com.example.loadermanagertest.PersonContentProvider/person");
        Bundle bundle = contentResolver.call(uri, "method", null, null);
        String returnCall = bundle.getString("returnCall");
        Log.i("main", "-------------->" + returnCall);
    }

    public void insert() {
        ContentResolver contentResolver = getContext().getContentResolver();
        ContentValues values = new ContentValues();
        values.put("name", "SS");
        values.put("address", "RR");
        // content://authorities/person
        // http://
        Uri uri = Uri
                .parse("content://com.example.loadermanagertest.PersonContentProvider/person");
        contentResolver.insert(uri, values);
    }

    public void delete() {
        ContentResolver contentResolver = getContext().getContentResolver();
        Uri uri = Uri
                .parse("content://com.example.loadermanagertest.PersonContentProvider/person/2");
        contentResolver.delete(uri, null, null);
    }

    public void deletes() {
        ContentResolver contentResolver = getContext().getContentResolver();
        Uri uri = Uri
                .parse("content://com.example.loadermanagertest.PersonContentProvider/person");
        String where = "address=?";
        String[] where_args = {"HK"};
        contentResolver.delete(uri, where, where_args);
    }

    public void update() {
        ContentResolver contentResolver = getContext().getContentResolver();
        Uri uri = Uri
                .parse("content://com.example.loadermanagertest.PersonContentProvider/person/2");
        ContentValues values = new ContentValues();
        values.put("name", "TT");
        values.put("address", "66");
        contentResolver.update(uri, values, null, null);
    }

    public void updates() {
        ContentResolver contentResolver = getContext().getContentResolver();
        Uri uri = Uri
                .parse("content://com.example.loadermanagertest.PersonContentProvider/person/student");
        ContentValues values = new ContentValues();
        values.put("name", "WW");
        values.put("address", "RR");
        String where = "address=?";
        String[] where_args = {"beijing"};
        contentResolver.update(uri, values, where, where_args);
    }

    public void query() {
        ContentResolver contentResolver = getContext().getContentResolver();
        Uri uri = Uri
                .parse("content://com.example.loadermanagertest.PersonContentProvider/person");
        Cursor cursor = contentResolver.query(uri, null, null, null, null);
        while (cursor.moveToNext()) {
            Log.i("MyTest",
                    "--->>"
                            + cursor.getString(cursor.getColumnIndex("name")));
        }
    }

    public void querys() {
        ContentResolver contentResolver = getContext().getContentResolver();
        Uri uri = Uri
                .parse("content://com.example.loadermanagertest.PersonContentProvider/person");
        String where = "address=?";
        String[] where_args = {"22"};
        Cursor cursor = contentResolver.query(uri, null, where, where_args,
                null);
        while (cursor.moveToNext()) {
            Log.i("main",
                    "-------------->"
                            + cursor.getString(cursor.getColumnIndex("name")));
        }
    }

}
