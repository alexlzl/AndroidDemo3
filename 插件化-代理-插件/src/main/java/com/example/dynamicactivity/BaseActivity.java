package com.example.dynamicactivity;

import com.example.dynamicactivity.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class BaseActivity extends Activity {
	protected Activity mProxyActivity;

	public void setProxy(Activity proxyActivity) { 
		mProxyActivity = proxyActivity;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	}

	@Override
	public void setContentView(int layoutResID) { 
		if (mProxyActivity != null && mProxyActivity instanceof Activity) {
			mProxyActivity.setContentView(layoutResID);
			mProxyActivity.findViewById(R.id.btn).setOnClickListener(
					new OnClickListener() {
						@Override
						public void onClick(View v) {
							Toast.makeText(mProxyActivity, "我是插件，你是谁!",Toast.LENGTH_LONG).show();
						}
					});
		}
	}

}
