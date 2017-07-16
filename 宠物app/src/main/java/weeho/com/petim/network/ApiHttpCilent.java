package weeho.com.petim.network;

import android.app.Activity;
import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import weeho.com.petim.modle.BaseBean;


/**
 * @author 作者 E-mail:wk
 * @version 创建时间：2015-9-29 上午10:00:22 类说明
 * @param网络连接工具类
 */
public class ApiHttpCilent {
	private static ApiHttpCilent mApi;
	private static Context context;

	private static String TAG = "PetIm";

	public static ApiHttpCilent getInstance(Context _context) {
		context = _context;
		if (mApi == null) {
			mApi = new ApiHttpCilent();
		}
		return mApi;
	}

	/**
	 * 调取元数据接口获取版本数据
	 * 
	 * @return
	 */
	public void initVersiondata(Context baseContext, String android,
                                BaseJsonHttpResponseHandler<BaseBean> callback) {
		RequestParams paramsIn = new RequestParams();
		paramsIn.put("type", android);
//		saveCookie(BasicHttpClient.getInstance(baseContext).asyncHttpClient, baseContext);
		BasicHttpClient.getInstance(baseContext).post(baseContext, paramsIn,
				UrlsUtil.initdata, callback);
	}

}
