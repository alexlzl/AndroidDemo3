package com.example.loadermanagertest;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.example.loadermanagertest.dao.PersonDao;

public class PersonContentProvider extends ContentProvider {

    private final String TAG = "PersonContentProvider";
    private PersonDao personDao = null;
    private static final UriMatcher URI_MATCHER = new UriMatcher(
            UriMatcher.NO_MATCH);
    private static final int PERSON = 1;
    private static final int PERSONS = 2;

    static {
        URI_MATCHER.addURI(
                "com.example.loadermanagertest.PersonContentProvider",
                "person", PERSONS);
        URI_MATCHER.addURI(
                "com.example.loadermanagertest.PersonContentProvider",
                "person/#", PERSON);
    }

    public PersonContentProvider() {

    }

    @Override
    public boolean onCreate() {
        personDao = new PersonDao(getContext());
        return true;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Uri resultUri = null;
        int flag = URI_MATCHER.match(uri);
        switch (flag) {
            case PERSONS:
                long id = personDao.insertPerson(values);
                resultUri = ContentUris.withAppendedId(uri, id);
                System.out.println("insert success");
                break;
        }
        return resultUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = -1;
        try {
            int flag = URI_MATCHER.match(uri);
            switch (flag) {
                case PERSON:
                    // delete from student where id=?
                    long id = ContentUris.parseId(uri);
                    String where_value = "id = ?";
                    String[] args = {String.valueOf(id)};
                    count = personDao.deletePerson(where_value, args);
                    break;
                case PERSONS:
                    count = personDao.deletePerson(selection, selectionArgs);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int count = -1;
        try {
            int flag = URI_MATCHER.match(uri);
            switch (flag) {
                case PERSON:
                    long id = ContentUris.parseId(uri);
                    String where_value = " id = ?";
                    String[] args = {String.valueOf(id)};
                    count = personDao.updatePerson(values, where_value, args);
                    break;
                case PERSONS:
                    count = personDao
                            .updatePerson(values, selection, selectionArgs);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Cursor cursor = null;
        try {
            int flag = URI_MATCHER.match(uri);
            switch (flag) {
                case PERSON:
                    long id = ContentUris.parseId(uri);
                    String where_value = " id = ?";
                    String[] args = {String.valueOf(id)};
                    cursor = personDao.queryPersons(where_value, args);
                    break;
                case PERSONS:
                    cursor = personDao.queryPersons(selection, selectionArgs);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        int flag = URI_MATCHER.match(uri);
        switch (flag) {
            case PERSON:
                return "vnd.android.cursor.item/person";
            // + path

            case PERSONS:
                return "vnd.android.cursor.dir/persons";
            // + path
        }
        return null;
    }

    @Override
    public Bundle call(String method, String arg, Bundle extras) {
        Log.i(TAG, "--->>" + method);
        Bundle bundle = new Bundle();
        bundle.putString("returnCall", "DD");
        return bundle;
    }
}
