package com.anly.samples.pref.leak;

import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;

import com.anly.samples.R;
import com.anly.samples.pref.BigObject;

import java.lang.ref.WeakReference;

public class CursorLeakActivity extends AppCompatActivity {

    private BigObject mBigObject = new BigObject();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_leak);

        Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

        try {
            if (cursor != null) {
                cursor.moveToFirst();

                // do something.
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (cursor != null) {
                cursor.close();
                cursor = null;
            }
        }
    }
}
