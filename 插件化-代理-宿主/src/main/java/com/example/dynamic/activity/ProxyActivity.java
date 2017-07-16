package com.example.dynamic.activity;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import dalvik.system.DexClassLoader;

public class ProxyActivity extends BaseActivity {
	
	private Object pluginActivity;
	private Class<?> pluginClass;
	
	private HashMap<String, Method> methodMap = new HashMap<String,Method>();

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			DexClassLoader loader = initClassLoader();
			
			//动态加载插件Activity
			pluginClass = loader.loadClass("com.example.dynamicactivity.MainActivity");
			Constructor<?> localConstructor = pluginClass.getConstructor(new Class[] {});
			pluginActivity = localConstructor.newInstance(new Object[] {});

			//将代理对象设置给插件Activity
			Method setProxy = pluginClass.getMethod("setProxy",new Class[] { Activity.class }); 
			setProxy.setAccessible(true);
			setProxy.invoke(pluginActivity, new Object[] { this });
			
			initMethodMap();

			//调用它的onCreate方法
			Method onCreate = pluginClass.getDeclaredMethod("onCreate",
					new Class[] { Bundle.class });
			onCreate.setAccessible(true);
			onCreate.invoke(pluginActivity, new Object[] { new Bundle() });
			
		} catch (Exception e) {
			Log.i("demo", "load activity error:"+Log.getStackTraceString(e));
		}
	}
	
	/**
	 * 存储每个生命周期的方法
	 */
	private void initMethodMap(){
		methodMap.put("onPause", null);
		methodMap.put("onResume", null);
		methodMap.put("onStart", null);
		methodMap.put("onStop", null);
		methodMap.put("onDestroy", null);
		
		for(String key : methodMap.keySet()){
			try{
				Method method = pluginClass.getDeclaredMethod(key);
				method.setAccessible(true);
				methodMap.put(key, method);
			}catch(Exception e){
				Log.i("demo", "get method error:"+Log.getStackTraceString(e));
			}
		}
		
	}
	
	@SuppressLint("NewApi")
	private DexClassLoader initClassLoader(){
		String filesDir = this.getCacheDir().getAbsolutePath();
        String libPath = filesDir + File.separator +"PluginActivity2.apk";
        Log.i("inject", "fileexist:"+new File(libPath).exists());
        loadResources(libPath);
		DexClassLoader loader = new DexClassLoader(libPath, filesDir,null , getClass().getClassLoader());
		return loader;
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i("demo", "proxy onDestroy");
		try{
			methodMap.get("onDestroy").invoke(pluginActivity, new Object[]{});
		}catch(Exception e){
			Log.i("demo", "run destroy error:"+Log.getStackTraceString(e));
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.i("demo", "proxy onPause");
		try{
			methodMap.get("onPause").invoke(pluginActivity, new Object[]{});
		}catch(Exception e){
			Log.i("demo", "run pause error:"+Log.getStackTraceString(e));
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.i("demo", "proxy onResume");
		try{
			methodMap.get("onResume").invoke(pluginActivity, new Object[]{});
		}catch(Exception e){
			Log.i("demo", "run resume error:"+Log.getStackTraceString(e));
		}
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.i("demo", "proxy onStart");
		try{
			methodMap.get("onStart").invoke(pluginActivity, new Object[]{});
		}catch(Exception e){
			Log.i("demo", "run start error:"+Log.getStackTraceString(e));
		}
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.i("demo", "proxy onStop");
		try{
			methodMap.get("onStop").invoke(pluginActivity, new Object[]{});
		}catch(Exception e){
			Log.i("demo", "run stop error:"+Log.getStackTraceString(e));
		}
	}

}
