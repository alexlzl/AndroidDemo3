package me.homeway.servicebinder;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity 
{
	private String TAG ="MainActivity";
	
	private Button startService;
	private Button stopService;
	private Button bindService;
	private Button unbindService;
	private Button sendBinder;
	private boolean IS_START_SERVICE=false;
	private boolean IS_START_BINDER=false;
	
	/* 通过Binder，实现Activity与Service通信 */
	private MainService.ServiceBinder mBinderService;
	private ServiceConnection connection = new ServiceConnection() {  
	        @Override  
	        public void onServiceDisconnected(ComponentName name) {  
	        }  
	  
	        @Override  
	        public void onServiceConnected(ComponentName name, IBinder service) {  
	            mBinderService = (MainService.ServiceBinder) service;
	            try {
	            	Log.i(TAG, "MainActivity===>>ServiceConnection onServiceConnected Success===>>线程ID:"+Thread.currentThread().getId());
					mBinderService.startDownload();
				} catch (InterruptedException e) {
					Log.i(TAG, "MainActivity===>>ServiceConnection onServiceConnected Error===>>线程ID:"+Thread.currentThread().getId());
					e.printStackTrace();
				}  
	        }  
	}; 
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        startService = (Button) findViewById(R.id.startService);
        stopService = (Button) findViewById(R.id.stopService);
        bindService = (Button) findViewById(R.id.bindService);
        unbindService = (Button) findViewById(R.id.unbindService);
        sendBinder = (Button) findViewById(R.id.sendBinder);
        
        startService.setOnClickListener(ClickHandler);
        stopService.setOnClickListener(ClickHandler);
        bindService.setOnClickListener(ClickHandler);
        unbindService.setOnClickListener(ClickHandler);
        sendBinder.setOnClickListener(ClickHandler);
    }
    
    @Override
    protected void onStop() {
//        unbindService(connection);
		super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
    
    View.OnClickListener ClickHandler = new View.OnClickListener() {

    	public void onClick(View v) {  
    		switch (v.getId()) {  
    		case R.id.startService:{
        		Intent startIntent = new Intent(MainActivity.this, MainService.class);    
        		startService(startIntent); 
        		IS_START_SERVICE=true;
        		break;
        	}
        
        	case R.id.stopService:{
        		Intent stopIntent = new Intent(MainActivity.this, MainService.class);  
            	if(IS_START_SERVICE){
            		stopService(stopIntent);
            	}
            	IS_START_SERVICE=false;
            	break;
        	}
        
        	case R.id.bindService:{
        		Intent bindIntent = new Intent(MainActivity.this, MainService.class);  
            	bindService(bindIntent, connection, BIND_AUTO_CREATE);  
            	IS_START_SERVICE=true;
            	IS_START_BINDER=true;
            	break;
        	}
        
        	case R.id.unbindService:{
        		if(IS_START_BINDER){
        			unbindService(connection);
//					Intent stopIntent = new Intent(MainActivity.this, MainService.class);
//					stopService(stopIntent);
					Toast.makeText( MainActivity.this, "Binder unbind", Toast.LENGTH_SHORT).show();
        		}
        		IS_START_BINDER=false;
        		IS_START_SERVICE=false;
        		break; 
    		}
        
        	case R.id.sendBinder:{
        		Toast.makeText( MainActivity.this, "Send Binder", Toast.LENGTH_SHORT).show();
        	}
        
    		default:{
    			break;
    		}
        
    		}
    	}
    };
}



















