package com.example.helloworld;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	ListView lv;
	Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			
			
		};
	};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        lv=(ListView)findViewById(R.id.main_lv_menu);
        String[]array=getResources().getStringArray(R.array.main_lv_menu_strings);
        ListAdapter adapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,array);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int index,
					long arg3) {
				if(adapter.getItemAtPosition(index).toString().equals("phone")){
					Intent intent=new Intent(MainActivity.this,ContactActivity.class);
					startActivity(intent);
				}
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
}
