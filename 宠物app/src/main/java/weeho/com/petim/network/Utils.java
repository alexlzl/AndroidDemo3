package weeho.com.petim.network;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;


import org.apache.http.cookie.Cookie;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;


/** 
 * @author 作者 E-mail: wk
 * @version 创建时间：2015-10-26 上午11:37:36 
 * 类说明 
 * @param存储cookies
 */
public class Utils {

	private static List<Cookie> cookies;
    public static String process_name = "com.heheys.ec:heart";
	public static List<Cookie> getCookies() {
		return cookies != null ? cookies : new ArrayList<Cookie>();
	}

	public static void setCookies(List<Cookie> cookies) {
		Utils.cookies = cookies;
	}
	public static int getCurrentAppVersionCode(Activity baseActivity) {
		int intVersionCodeCur = 0;
		try {
			intVersionCodeCur = baseActivity.getPackageManager().getPackageInfo(
					baseActivity.getPackageName(), 0).versionCode;
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
		return intVersionCodeCur;
	}
	public static Bitmap getImage(String url) {
		Bitmap bitmap = null;
		try {
			URL iconurl = new URL(url);
			URLConnection conn = iconurl.openConnection();
			HttpURLConnection http = (HttpURLConnection) conn;
			int length = http.getContentLength();
			conn.connect();

			InputStream in = conn.getInputStream();
			BufferedInputStream buffer = new BufferedInputStream(in, length);
			bitmap = BitmapFactory.decodeStream(buffer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bitmap;
	}
	// 处理成2位小数点
		public static float DoPrice(float ft) {
			int scale = 2;// 设置位数
			int roundingMode = 4;// 表示四舍五入，可以选择其他舍值方式，例如去尾，等等.
			BigDecimal bd = new BigDecimal((double) ft);
			bd = bd.setScale(scale, roundingMode);
			ft = bd.floatValue();
			return ft;
		}
		public static String DoPriceInt(int ft) {
			java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");
			return  df.format(ft);
		}

	/**
	 *  返回true 表示可以使用  返回false表示不可以使用
	 */
	public static boolean cameraIsCanUse() {
		boolean isCanUse = true;
		Camera mCamera = null;
		try {
			mCamera = Camera.open();
			Camera.Parameters mParameters = mCamera.getParameters(); //针对魅族手机
			mCamera.setParameters(mParameters);
		} catch (Exception e) {
			isCanUse = false;
		}

		if (mCamera != null) {
			try {
				mCamera.release();
			} catch (Exception e) {
				e.printStackTrace();
				return isCanUse;
			}
		}
		return isCanUse;
	}
	// 测试方法 读取本地json
		public static String ReadJson(Context mContext, String path) {
			try {
				InputStream in = mContext.getResources().getAssets().open(path);
				byte buffer[] = new byte[in.available()];
				in.read(buffer);
				String json = new String(buffer, "UTF-8");
				return json;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		 public static void setViewHeightBasedOnChildren(View view) {
		      // 获取ListView对应的Adapter
		            int totalHeight = 0;
		                 // 计算子项View 的宽高
			 			view.measure(0, 0);
		                // 统计所有子项的总高度
		                 totalHeight += view.getMeasuredHeight();
		       ViewGroup.LayoutParams params = view.getLayoutParams();
		       params.height = totalHeight;
		       view.setLayoutParams(params);
		     }
		 public static void setListViewHeightBasedOnChildren(ListView listView) {
		      // 获取ListView对应的Adapter
		             ListAdapter listAdapter = listView.getAdapter();
		            if (listAdapter == null) {
		            	return;
		            	}
		            int totalHeight = 0;
		            for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
		                  // listAdapter.getCount()返回数据项的数目
		                 View listItem = listAdapter.getView(i, null, listView);
		                 // 计算子项View 的宽高
		                 listItem.measure(0, 0);
		                // 统计所有子项的总高度
		                 totalHeight += listItem.getMeasuredHeight();
		        }
		       ViewGroup.LayoutParams params = listView.getLayoutParams();
		       params.height = totalHeight+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		       listView.setLayoutParams(params);
		     }
		 /**
		     * 获取解密信息
		     * @param c
		     * @return
		     */
		    public static String getEncryptProperty(Context c) {

		        InputStream is = null;
		        ByteArrayOutputStream outStream = null;
		        try {

		            is = c.getAssets().open("config.properties");

		            outStream = new ByteArrayOutputStream();
		            byte[] data = new byte[1024];
		            int count = -1;
		            while ((count = is.read(data, 0, 1024)) != -1) {
		                outStream.write(data, 0, count);
		            }
		            Log.d("TAG","load file encode start...");
		            String encode = new String(outStream.toByteArray(), "UTF-8");
		            //获取解密字符串
		            String stringNative = getEString(encode);
		            Log.d("TAG","load file encode end..."+stringNative);
		            return stringNative;
		        } catch (IOException e) {
		            Log.d("TAG","load file encode end..."+e.toString());
		            e.printStackTrace();
		        } finally {
		            try {
		                if (is != null) {
		                    is.close();
		                    is = null;
		                }
		                if (outStream != null) {
		                    outStream.close();
		                    outStream = null;
		                }
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
		        }
		        return null;
		        
		       
		    }
		    public  static String getEString(String eCode){
		        StringBuilder builder = new StringBuilder();
		        long lenth = eCode.length();
		        for (int i = 0; i < lenth; i++){
		            builder.append((char)(eCode.charAt(i)- i%5));
		        }
		        return builder.toString();
		    }


		public static void getText(Context Mcontext){
//			最后我们对getEString再通过如下获取各个键-值
			String[] split = getEncryptProperty(Mcontext).split('\n' + "");
			 for (String sp : split) {
			     String[] str = sp.split("=");
			     String value = str[1].trim();
			     if(sp.contains("YB_APP_ID")){
			         android.util.Log.d("aa","YB_APP_ID:"+value);
			     }else if(sp.contains("NB_SEX_PARTNERID")){
			         android.util.Log.d("aa","NB_SEX_PARTNERID:"+value);
			     }else if(sp.contains("WY_NOTI_KEY")){
			         android.util.Log.d("aa","NB_SEX_PARTNERID:"+value);
			     }
			 }
		}
		
//		public static void StartLogin(Activity baseActivity, boolean isNeedLogin){
//			 Intent intent = new Intent(baseActivity,LoginActivity.class);
//       	     intent.putExtra("userCenter", isNeedLogin);
//			 intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//关掉所要到的界面中间的activity
//			 StartActivityUtil.startActivity(baseActivity, intent);
//			 if(isNeedLogin)
//				 baseActivity.finish();
//			 MyApplication.getInstance().isLogin = false;
//			 SharedPreferencesUtil.writeSharedPreferencesBoolean(baseActivity,"login", "islogin", false);
//		}
		//Edited by mythou
		//http://www.cnblogs.com/mythou/
		//要转换的地址或字符串,可以是中文
		private int QR_WIDTH = 200;
		private int QR_HEIGHT = 200;
//		    public void createQRImage(String url, ImageView sweepIV)
//		    {
//		        try
//		        {
//		            //判断URL合法性
//		            if (url == null || "".equals(url) || url.length() < 1)
//		            {
//		                return;
//		            }
//		            Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
//		            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
//		            //图像数据转换，使用了矩阵转换
//		            BitMatrix bitMatrix = new QRCodeWriter().encode(url, BarcodeFormat.QR_CODE, QR_WIDTH, QR_HEIGHT, hints);
//		            int[] pixels = new int[QR_WIDTH * QR_HEIGHT];
//		            //下面这里按照二维码的算法，逐个生成二维码的图片，
//		            //两个for循环是图片横列扫描的结果
//		            for (int y = 0; y < QR_HEIGHT; y++)
//		            {
//		                for (int x = 0; x < QR_WIDTH; x++)
//		                {
//		                    if (bitMatrix.get(x, y))
//		                    {
//		                        pixels[y * QR_WIDTH + x] = 0xff000000;
//		                    }
//		                    else
//		                    {
//		                        pixels[y * QR_WIDTH + x] = 0xffffffff;
//		                    }
//		                }
//		            }
//		            //生成二维码图片的格式，使用ARGB_8888
//		            Bitmap bitmap = Bitmap.createBitmap(QR_WIDTH, QR_HEIGHT, Bitmap.Config.ARGB_8888);
//		            bitmap.setPixels(pixels, 0, QR_WIDTH, 0, 0, QR_WIDTH, QR_HEIGHT);
//		            //显示到一个ImageView上面
//		            sweepIV.setImageBitmap(bitmap);
//		        }
//		        catch (WriterException e)
//		        {
//		            e.printStackTrace();
//		        }
//		    }
          /**
          * 获取当前进程是否在运行
          * */
	public static boolean isProessRunning(Context context, String proessName) {

		boolean isRunning = false;
		ActivityManager am = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);

		List<ActivityManager.RunningAppProcessInfo> lists = am.getRunningAppProcesses();
		for (ActivityManager.RunningAppProcessInfo info : lists) {
			if (info.processName.equals(proessName)) {
				isRunning = true;
				break;
			}
		}

		return isRunning;
	}
		    
}
 