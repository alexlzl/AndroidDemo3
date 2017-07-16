package com.example.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import com.example.dao.ContactDao;
import com.example.entity.Contact;

public class ContactProvider extends ContentProvider {
	
    private static 	UriMatcher matcher=new UriMatcher(UriMatcher.NO_MATCH);
    private static final int  INSERT=1;
    private static final int  DELETE=2;
    private static final int  QUERY=3;
    private static final int  UPDATE=4;
    private static final int QUERYONE=5;
    
    private ContactDao contactDao;
    static{   	
    	matcher.addURI("com.example.provider.ContactProvider", "insert", INSERT);
    	matcher.addURI("com.example.provider.ContactProvider", "delete", DELETE);
    	matcher.addURI("com.example.provider.ContactProvider", "query", QUERY);
    	matcher.addURI("com.example.provider.ContactProvider", "update", UPDATE);
    	matcher.addURI("com.example.provider.ContactProvider", "query/#", QUERYONE);
    }
	
	@Override
	public boolean onCreate() {
		contactDao =new ContactDao(getContext());
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		if(matcher.match(uri)==QUERY){
			return contactDao.findAllContacts();
		}
		else if(matcher.match(uri)==QUERYONE){
			Long id=ContentUris.parseId(uri);
			return contactDao.findContact(id+"");
		}
		else{
			throw new IllegalArgumentException("·����ƥ��    ���ܽ��в�ѯ����!!!!!");
		}
	}

	@Override
	public String getType(Uri uri) {
		if(matcher.match(uri)==QUERY){
			return "com.example.provider.ContactProvider/queryall";
		}
		else{
			if(matcher.match(uri)==QUERYONE){
				return "com.example.provider.ContactProvider/queryone";
			}
		}
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		if(matcher.match(uri)==INSERT){
			Contact contact=new Contact();
			contact.setName(values.get("name").toString());
			contact.setPhone(values.get("phone").toString());
			contactDao.insertContact(contact);
		}
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
	 
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
