package com.weeho.petim.lib.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 
 * 
 * @describe:提供工具方法从assets目录下安装apk
 * 
 * @author:wangkui
 * 
 * @time:2014-11-4上午10:24:10
 * 
 */

public class InstallAPK {

	/**
	 * 
	 * 
	 * @describe:安装APK的方法
	 * 
	 * @author:wangkui
	 * 
	 * @time:2014-11-4上午10:24:23
	 * 
	 */
	public static void installAPK(final Context context, final String apkName,
			final Handler handler) {
		if (copyApkFromAssets(context, apkName, Environment
				.getExternalStorageDirectory().getAbsolutePath()
				+ "/"
				+ apkName)) {
			// Builder m = new AlertDialog.Builder(context)
			// .setIcon(R.drawable.logo).setMessage("安装插件").setTitle("提示")
			// .setIcon(R.drawable.logo)
			// .setNegativeButton("不安装", new OnClickListener() {
			//
			// @Override
			// public void onClick(DialogInterface dialog, int which) {
			// // TODO Auto-generated method stub
			// }
			// }).setPositiveButton("安装", new OnClickListener() {
			// @Override
			// public void onClick(DialogInterface dialog, int which) {
			// Intent intent = new Intent(Intent.ACTION_VIEW);
			// intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			// intent.setDataAndType(Uri.parse("file://"
			// + Environment.getExternalStorageDirectory
			//
			// ().getAbsolutePath() + "/" + apkName),
			// "application/vnd.android.package-archive");
			// context.startActivity(intent);
			// Message msg = handler.obtainMessage();
			// msg.what = Unionpay.INSTALLOVER;
			// handler.sendMessage(msg);
			//
			// }
			// });
			// m.show();
			Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.setDataAndType(
					Uri.parse("file://"
							+ Environment.getExternalStorageDirectory

							().getAbsolutePath() + "/" + apkName),
					"application/vnd.android.package-archive");
			context.startActivity(intent);
			Message msg = handler.obtainMessage();
			// msg.what = Unionpay.INSTALLOVER;
			handler.sendMessage(msg);
		}
	}

	public static boolean copyApkFromAssets(Context context, String fileName,
			String path) {
		boolean copyIsFinish = false;
		try {
			InputStream is = context.getAssets().open(fileName);
			File file = new File(path);
			file.createNewFile();
			FileOutputStream fos = new FileOutputStream(file);
			byte[] temp = new byte[1024];
			int i = 0;
			while ((i = is.read(temp)) > 0) {
				fos.write(temp, 0, i);
			}
			fos.close();
			is.close();
			copyIsFinish = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return copyIsFinish;
	}
}
