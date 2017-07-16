package me.homeway.servicebinder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class BroadcastTest extends BroadcastReceiver{
	
	public void onReceive(Context context, Intent intent) {
		Bundle extras = intent.getExtras();
		if (extras != null) {
			if(extras.containsKey("value")){
				
				Toast.makeText( context, "收到广播 => "+extras.get("value"), Toast.LENGTH_SHORT).show();
				System.out.println("Value is:"+extras.get("value"));
			}
		}
	}
}