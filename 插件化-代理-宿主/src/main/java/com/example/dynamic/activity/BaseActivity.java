package com.example.dynamic.activity;

import java.lang.reflect.Method;

import android.app.Activity;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.os.Bundle;
import android.util.Log;

public class BaseActivity extends Activity{
	
	protected AssetManager mAssetManager;
	protected Resources mResources;
	protected Theme mTheme;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	protected void loadResources(String dexPath) {  
        try {  
            AssetManager assetManager = AssetManager.class.newInstance();  
            Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);  
            addAssetPath.invoke(assetManager, dexPath);  
            mAssetManager = assetManager;  
        } catch (Exception e) {  
        	Log.i("inject", "loadResource error:"+Log.getStackTraceString(e));
            e.printStackTrace();  
        }  
        Resources superRes = super.getResources();  
        superRes.getDisplayMetrics();  
        superRes.getConfiguration();  
        mResources = new Resources(mAssetManager, superRes.getDisplayMetrics(),superRes.getConfiguration());  
        mTheme = mResources.newTheme();  
        mTheme.setTo(super.getTheme());
    }  
	
	@Override  
	public AssetManager getAssets() {  
	    return mAssetManager == null ? super.getAssets() : mAssetManager;  
	}  
	
	@Override  
	public Resources getResources() {  
	    return mResources == null ? super.getResources() : mResources;  
	}  
	
	@Override  
	public Theme getTheme() {  
	    return mTheme == null ? super.getTheme() : mTheme;  
	} 

}
