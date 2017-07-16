package com.lanzx.customprogressbar;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity {

	private int progress=0;
    private Message message;
    CustomProgressBar bar;
	
	private Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			int p=msg.what;
			//mPbar.setProgress(p);
			bar.setProgress(p);
		}
    	
    };
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        bar = (CustomProgressBar) findViewById(R.id.item_progress_SeekBar);
		bar.setMax(100);
		bar.setProgress(60);
		new Thread(yuanlirunnable).start();
    }

    Runnable yuanlirunnable=new Runnable() {
		
		@Override
		public void run() {
			message=handler.obtainMessage();
			
			try {
				for (int i = 0; i <= 100; i++) {
					int x=++progress;
					message.what=x;
					handler.sendEmptyMessage(message.what);
					Thread.sleep(100);
				}
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
