package com.example.helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
 
import com.example.dao.ContactDao;
import com.example.entity.Contact;

public class AddContactActivity extends Activity {

	EditText name;
	EditText phone;
	Button btnOk;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_contact);
		
		name=(EditText)findViewById(R.id.et_add_contact_name);
		phone=(EditText)findViewById(R.id.et_add_contact_phone);
		
		btnOk=(Button)findViewById(R.id.btn_add_contact_ok);
		btnOk.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(checkOk()){
					ContactDao contactDao=new ContactDao(AddContactActivity.this);
					boolean ok = contactDao.insertContact(new Contact(name.getText().toString(),phone.getText().toString()));
					if(ok){
						Toast.makeText(AddContactActivity.this, "save success", 2000).show();
					}
					else{
						Toast.makeText(AddContactActivity.this, "save error", 2000).show();
					}
				}
			}
		});
	}
	private boolean checkOk(){
		if(name.toString()==null||name.toString().length()==0){
			return false;
		}
		if(phone.toString()==null||phone.toString().length()==0){
			return false;
		}
		return true;
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_add_contact, menu);
		return true;
	}

}
