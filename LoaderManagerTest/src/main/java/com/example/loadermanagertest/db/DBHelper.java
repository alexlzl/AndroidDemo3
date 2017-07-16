package com.example.loadermanagertest.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static String name = "mydb.db";
    private static int version = 1;

    public DBHelper(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "create table person(_id integer primary key autoincrement,name varchar(64),address varchar(64))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        String sql = "alter table person add sex varchar(8)";
        db.execSQL(sql);
    }

}
