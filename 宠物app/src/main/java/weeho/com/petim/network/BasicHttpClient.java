package weeho.com.petim.network;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.MySSLSocketFactory;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;

import org.apache.http.Header;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.client.CookieStore;
import weeho.com.petim.application.MyApplication;
import weeho.com.petim.util.LogUtil;

//import com.heheys.ec.utils.CheckUpdateServer;

/**
 * 
 * Describe:网络请求工具类
 * 
 * Date:2015-9-22
 * 
 * Author:liuzhouliang
 */
public class BasicHttpClient {
	private final static String TAG = BasicHttpClient.class.getName();

	private static BasicHttpClient basicHttpClient = null;
	private static Header[] defaultHeaders;
	    AsyncHttpClient asyncHttpClient = null;
	    private static final String CLIENT_AGREEMENT = "TLS";//使用协议
	    private static final String CLIENT_KEY_MANAGER = "X509";//密钥管理器
	    private static final String CLIENT_TRUST_MANAGER = "X509";//
	    private static final String CLIENT_TRUST_KEYSTORE = "BKS";//
	private BasicHttpClient(Context mContext) {
		asyncHttpClient = new AsyncHttpClient(true, 80, 443);
//		init(mContext,asyncHttpClient);
		
		if (Utils.getCookies() != null) {//每次请求都要带上cookie  
	        BasicCookieStore bcs = new BasicCookieStore();
	        bcs.addCookies(Utils.getCookies().toArray(  
	                new Cookie[Utils.getCookies().size()]));
	        asyncHttpClient.setCookieStore((CookieStore) bcs);
	    } 
		asyncHttpClient.setTimeout(10 * 1000);
//		asyncHttpClient.setSSLSocketFactory(getSocketFactory(mContext));
	}
	 
	public static BasicHttpClient getInstance(Context mContext) {
		if (basicHttpClient == null) {
			basicHttpClient = new BasicHttpClient(mContext);
		}
		return basicHttpClient;
	}
	private static final String KEY_STORE_TYPE_BKS = "bks";
	private static final String KEY_STORE_TYPE_P12 = "PKCS12";
	private static final String KEY_STORE_PASSWORD = "123456";
	private static final String KEY_STORE_TRUST_PASSWORD = "123456";
//	private SSLSocketFactory getSocketFactory(Context mContext) {
//        // TODO Auto-generated method stub
//        SSLSocketFactory sslFactory = null;
//        try {
//            KeyStore keyStore = KeyStore.getInstance("BKS");
//            InputStream instream = mContext.getResources().getAssets().open("keyStore.bks");
//            keyStore.load(instream, "cB@eHOL4YD5X".toCharArray());
//            sslFactory = new MySSLSocketFactory(keyStore);
//        } catch (KeyStoreException e1) {
//            // TODO Auto-generated catch block
//            e1.printStackTrace();
//        } catch (NoSuchAlgorithmException e1) {
//            // TODO Auto-generated catch block
//            e1.printStackTrace();
//        } catch (CertificateException e1) {
//            // TODO Auto-generated catch block
//            e1.printStackTrace();
//        } catch (IOException e1) {
//            // TODO Auto-generated catch block
//            e1.printStackTrace();
//        } catch (UnrecoverableKeyException e1) {
//            // TODO Auto-generated catch block
//            e1.printStackTrace();
//        } catch (KeyManagementException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return sslFactory;
//    }

//	 public void init(Context mContext,final AsyncHttpClient asyncHttpClient) {  
//	        try {  
//	            //取得SSL的SSLContext实例  
////	            final SSLContext sslContext = SSLContext.getInstance(CLIENT_AGREEMENT);  
//	            //取得KeyManagerFactory和TrustManagerFactory的X509密钥管理器实例  
////	            KeyManagerFactory keyManager = KeyManagerFactory.getInstance(CLIENT_KEY_MANAGER);  
////	            TrustManagerFactory trustManager = TrustManagerFactory.getInstance(CLIENT_TRUST_MANAGER);  
//	            //取得BKS密库实例  
//	            final KeyStore tks = KeyStore.getInstance(CLIENT_TRUST_KEYSTORE);  
//
//	            //加客户端载证书和私钥,通过读取资源文件的方式读取密钥和信任证书  
//	            InputStream ins = null;
////	            ins = mContext.getResources().openRawResource(R.drawable.hehe_server_pfx);
//	            CertificateFactory cerFactory = CertificateFactory.getInstance("X.509","BC");
//	            Certificate cer = cerFactory.generateCertificate(ins);
//	            final KeyStore keyStore = KeyStore.getInstance(CLIENT_TRUST_KEYSTORE);
//	            keyStore.load(null, null);
//	            keyStore.setCertificateEntry("trust", cer);	            
//	            /* ====================== */
//	            //初始化密钥管理器  
////	            trustManager.init(tks);  
//	            //初始化SSLContext  
////	            sslContext.init(null, trustManager.getTrustManagers(),null);  
//	            //生成SSLSocket  
//	            new Thread(new Runnable() {
//					@Override
//					public void run() {
//						try {
//							org.apache.http.conn.ssl.SSLSocketFactory sslFactory = new  org.apache.http.conn.ssl.SSLSocketFactory(keyStore);			
//							sslFactory.setHostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
//							asyncHttpClient.setSSLSocketFactory(sslFactory);
//						} catch (Exception e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}  
//					}
//				}).start();
//	        } catch (Exception e) {  
////	            LogUtil.e("MySSLSocket",e.getMessage());  
//	        	e.printStackTrace();
//	        }  
//	    } 
	 
	public AsyncHttpClient getAsyncHttpClient() {
		return asyncHttpClient;
	}

	public void setAsyncHttpClient(AsyncHttpClient asyncHttpClient) {
		this.asyncHttpClient = asyncHttpClient;
	}

	  
	/**
	 * 
	 * JSONObject转String
	 * 
	 * @param params
	 * @return
	 */
	private static String makeString(JSONObject params) {
		return params.toString();
	}

	/**
	 * String转JSONObject
	 * 
	 * @return
	 */
	private static JSONObject makeJson(String string) {
		try {
			return new JSONObject(string);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Get请求，无参数
	 * 
	 * @param context
	 * @param method
	 * @param responseHandler
	 */
	public void get(Context context, String method,
                    ResponseHandlerInterface responseHandler) {
		asyncHttpClient.get(context, RequestConfiguration.BASE_URL_TEST
				+ method, responseHandler);
		LogUtil.d("json", RequestConfiguration.BASE_URL_TEST + method);
	}

	/**
	 * Get请求，有参数
	 * 
	 * @param context
	 * @param params
	 * @param method
	 * @param responseHandler
	 */
	public void get(Context context, RequestParams params, String method,
                    ResponseHandlerInterface responseHandler) {
		asyncHttpClient.get(context, RequestConfiguration.BASE_URL_TEST
				+ method, params, responseHandler);
	}

	/**
	 * 网络get请求(有参),设置header
	 * 
	 * @param context
	 * @param params
	 * @param method
	 * @param headers
	 * @param responseHandler
	 */
	public void get(Context context, RequestParams params, String method,
                    Header[] headers, ResponseHandlerInterface responseHandler) {
//		asyncHttpClient.get(context, RequestConfiguration.BASE_URL_TEST
//				+ method, headers, params, responseHandler);
	}

	/**
	 * 网络post请求(HttpEntity参数)
	 * 
	 * @param context
	 * @param entity
	 * @param method
	 * @param responseHandler
	 */
	public void post(Context context, HttpEntity entity, String method,
                     ResponseHandlerInterface responseHandler) {
		asyncHttpClient.post(context, RequestConfiguration.BASE_URL_TEST
				+ method, entity, RequestConfiguration.CONTENT_TYPE,
				responseHandler);
	}

	/**
	 * 网络post请求(HttpEntity参数),设置header
	 * 
	 * @param context
	 * @param headers
	 * @param entity
	 * @param method
	 * @param responseHandler
	 */
	public void post(Context context, Header[] headers, HttpEntity entity,
                     String method, ResponseHandlerInterface responseHandler) {
//		asyncHttpClient.post(context, RequestConfiguration.BASE_URL_TEST
//				+ method, headers, entity, RequestConfiguration.CONTENT_TYPE,
//				responseHandler);
	}

	/**
	 * 网络post请求(HttpEntity参数),设置header
	 * 
	 * @param context
	 * @param method
	 * @param headers
	 * @param params
	 * @param responseHandler
	 */
	public void post(Context context, String method, Header[] headers,
                     RequestParams params, ResponseHandlerInterface responseHandler) {
//		asyncHttpClient.post(context, RequestConfiguration.BASE_URL_TEST
//				+ method, headers, params, RequestConfiguration.CONTENT_TYPE,
//				responseHandler);
	}

	/**
	 * 
	 * Describe:post请求调用
	 * 
	 * Date:2015-9-22
	 * 
	 * Author:liuzhouliang
	 */
	public void post(Context context, RequestParams param, String method,
                     ResponseHandlerInterface responseHandler) {

		String url;
		if (MyApplication.debugToggle) {
			/**
			 * 正式状态
			 */
			url = RequestConfiguration.BASE_URL_TEST + method; 
		} else {
			/**
			 * 调试状态
			 */
			url = RequestConfiguration.BASE_URL_TEST_LACALHOST + method;
//			url = "";
		}
		param.put("version", Utils.getCurrentAppVersionCode((Activity) context));//versionCode
		param.put("os", "android");
		param.put("factory", android.os.Build.MODEL);
		param.put("os_version", android.os.Build.VERSION.RELEASE);
		asyncHttpClient.post(context, url, param, responseHandler);
		
 		LogUtil.d(TAG, "请求地址" + url  + param);
	}

	public void postPay(Context context, RequestParams param, String method,
                        ResponseHandlerInterface responseHandler) {
		String url = method;
		// if (MyApplication.debugToggle) {
		// /**
		// * 调试状态
		// */
		// url = RequestConfiguration.BASE_URL_TEST_LACALHOST + method;
		// } else {
		// /**
		// * 正式状态
		// */
		// url = RequestConfiguration.BASE_URL_TEST + method;
		// }
//		param.put("version", DeviceUtil.getVersionInfo(context));
		param.put("os", "andorid");
		param.put("factory", android.os.Build.MODEL);
		param.put("os_version", android.os.Build.VERSION.RELEASE);
		asyncHttpClient.post(context, url, param, responseHandler);
		
		LogUtil.d(TAG, "请求地址" + url + "param==" + param);
	}

	// 支付测试
	public void postpay(Context context, RequestParams param, String method,
                        ResponseHandlerInterface responseHandler) {
		asyncHttpClient.post(context, method, param, responseHandler);
		LogUtil.d(TAG, "请求地址" + method + "param==" + param);
	}

	public void post1(Context context, RequestParams params, String url,
                      ResponseHandlerInterface responseHandler) {
		asyncHttpClient.post(context, url, params, responseHandler);
	}

	public void postWeather(String url, AsyncHttpResponseHandler responseHandler) {
		asyncHttpClient.get(url, responseHandler);

	}

	/**
	 * 取消当前页面所有请求
	 * 
	 * @param context
	 */
	public void cancleRequest(Context context) {
		asyncHttpClient.cancelRequests(context, true);
	}

	/**
	 * 获取默认header
	 * 
	 * @return
	 */
	public Header[] getDefaultHeaders() {
		return defaultHeaders;
	}

}
