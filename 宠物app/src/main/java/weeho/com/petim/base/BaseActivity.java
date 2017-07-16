package weeho.com.petim.base;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.weeho.petim.lib.utils.WeakHandler;

import weeho.com.petim.R;


/*
 *
 *
 * Describe:ACTIVITY基础类
 *
 * Date:2015-9-21
 *
 * Author:wangkui
 */

public abstract class BaseActivity extends FragmentActivity implements
		View.OnClickListener {
	protected Context baseContext;
	protected Activity baseActivity;
	protected static LayoutInflater baseLayoutInflater;
	private RelativeLayout rlReload;
	// 异常页面
	protected View loadFialView, noDataView, noNetWorkView, noOrderDataView,
			searchNodataView, noCouponDataView, businesscardNodataView;
	protected LinearLayout llParents;
	private TextView tvNoDataContent, tvNoOrderContent;
	protected ImageView ivBack;
	protected TextView base_activity_title_notice;
	protected ImageView ivBack_close;
	protected RelativeLayout rlTitlePrent,base_menu;
	protected TextView tvTitleName;
	protected TextView menu_shoppingnum;
	protected ImageView base_tel_main;
	// 标题栏上右侧三个区域的图标控件ivTitleMiddle
	protected ImageView ivTitleLeft, ivTitleMiddle, ivTitleRight;
	//menu显示箭头
	protected ImageView button_hidded,iv_order,imageView_one;
	// 右侧文本
	protected TextView tvRight;
//	public List<OnBackListener> onBackListenerList = new ArrayList<OnBackListener>();
//	private LoadingView loadingDialog;
	private ImageView ivTitledownIcon;
	protected LinearLayout linear_back, tvTitleName_linear;
	protected ImageView ivTitleArrow;
	protected ImageView base_shopping_mian;
	// 空地址视图
	protected View viewNoAddress, viewNoGroupBuy, viewNoSalon, viewNoSalonMy,
			viewNoClient, viewNoDrinksDemand;
	private TextView tvAddAddress, tvSalonMySee, tvAddClent;
	private TextView busAddCard;
	private Button btDrinksDemandAdd;
	Animation animation,animationbutton;
	private FatherHandler handler = new FatherHandler(this);
	protected LinearLayout linear_menu,button_hidded_linear;
	private ObjectAnimator objectAnimator;


    /*
	 *
	 * Describe:初始化容器
	 *
	 * Date:2015-9-22
	 *
	 * Author:wangkui
	 * */


	protected abstract void onCreate();


/*
	 *
	 * Describe:控制标题栏显示
	 *
	 * Date:2015-9-22
	 *
	 * Author:wangkui
	 */

	protected abstract boolean hasTitle();

/*
	 *
	 * Describe:加载控件
	 *
	 * Date:2015-9-22
	 *
	 * Author:wangkui
	 */

	protected abstract void loadChildView();

/*
	 *
	 * Describe:获取网络数据
	 *
	 * Date:2015-9-22
	 *
	 * Author:wangkui
	 */

	protected abstract void getNetData();

/*
	 *
	 * Describe:重新加载页面的回调
	 *
	 * Date:2015-9-22
	 *
	 * Author:wangkui
	 */

	protected abstract void reloadCallback();

/*
	 *
	 * Describe:子视图监听
	 *
	 * Date:2015-9-22
	 *
	 * Author:wangkui
*/


	protected abstract void setChildViewListener();
/*
*
	 *
	 * Describe:设置标题名称
	 *
	 * Date:2015-9-22
	 *
	 * Author:wangkui
	 */

	protected abstract String setTitleName();

/*
	 *
	 * Describe:设置右侧文本
	 *
	 * Date:2015-9-22
	 *
	 * Author:wangkui
	 */

	protected abstract String setRightText();

/*
	 *
	 * Describe:设置左侧的视图资源
	 *
	 * Date:2015-9-22
	 *
	 * Author:wangkui
*/

	protected abstract int setLeftImageResource();

/*
	 *
	 * Describe:设置中间控件视图资源
	 *
	 * Date:2015-9-22
	 *
	 * Author:wangkui
*/

	protected abstract int setMiddleImageResource();

/*
	 *
	 * Describe:设置右侧控件视图资源
	 *
	 * Date:2015-9-22
	 *
	 * Author:wangkui
*/

	protected abstract int setRightImageResource();

/*
	 *
	 * Describe:设置登录失效
	 *
	 * Date:2016-5-16
	 *
	 * Author:wangkui
*/

//	protected abstract boolean setLoginFailure();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.base_activity);
		// 获取父类外层容器
		llParents = (LinearLayout) findViewById(R.id.base_activity_rootview);
		init();
	}



/*
	 *
	 * Describe:初始化
	 *
	 * Date:2015-9-21
	 *
	 * Author:wangkui
*/

	private void init() {
//		ActivityManagerUtil.getActivityManager().addActivity(this);
		baseLayoutInflater = getLayoutInflater();
		baseContext = this;
		baseActivity = this;
		onCreate();
	}

/*
	 *
	 * Describe:设置布局内容
	 *
	 * Date:2015-9-21
	 *
	 * Author:wangkui
*/

	protected void setBaseContentView(int layoutResID) {
//		super.setContentView(R.layout.base_activity);
		// 加载子类控件资源
		View childMainView = baseLayoutInflater.inflate(layoutResID, null);
		LayoutParams ll = new LayoutParams(
				LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		llParents.addView(childMainView, 0, ll);
		initParentView();
//		setParentViewListener();
//		setParentViewData();
/*
		 * 子视图控制
*/

		loadChildView();
//		getNetData();
//		setChildViewListener();
//		ActivityManager.addActivity(this);
	}




	public class FatherHandler extends WeakHandler<BaseActivity> {

		public FatherHandler(BaseActivity reference) {
			super(reference);
			// TODO Auto-generated constructor stub
		}

	}


	private void initParentView() {
//		// TODO Auto-generated method stub

//		btDrinksDemandAdd = (Button) findViewById(R.id.base_activity_no_drink_demand_add);
//		tvAddClent = (TextView) findViewById(R.id.base_activity_no_client_add);
//		viewNoClient = findViewById(R.id.base_activity_no_client);
//		tvSalonMySee = (TextView) findViewById(R.id.base_activity_no_salon_see);
//		viewNoSalonMy = findViewById(R.id.base_activity_no_salon_my);
//		viewNoSalon = findViewById(R.id.base_activity_no_salon);
//		viewNoGroupBuy = findViewById(R.id.base_activity_group_buy_nodata);
//		viewNoAddress = findViewById(R.id.base_activity_address_nodata);
//		searchNodataView = findViewById(R.id.base_activity_search_nodata);
//		businesscardNodataView = findViewById(R.id.base_activity_no_businesscard);
//		busAddCard = (TextView) findViewById(R.id.base_activity_no_business_add);
//		loadFialView = findViewById(R.id.base_activity_loadfail);
//		noDataView = findViewById(R.id.base_activity_no_data);
//		noNetWorkView = findViewById(R.id.base_activity_no_network);
//
//
		base_activity_title_notice = (TextView) findViewById(R.id.base_activity_title_left);
		ivBack = (ImageView) findViewById(R.id.base_activity_title_backicon);
//		ivBack_close = (ImageView) findViewById(R.id.base_activity_title_close);
		rlTitlePrent = (RelativeLayout) findViewById(R.id.base_activity_title_parent);
//		tvTitleName_linear = (LinearLayout) findViewById(R.id.linear_titlename);
//		tvTitleName = (TextView) findViewById(R.id.base_activity_title_titlename);
		ivTitleLeft = (ImageView) findViewById(R.id.base_activity_title_right_lefticon);
//		ivTitledownIcon = (ImageView) findViewById(R.id.base_activity_title_downicon);
		ivTitleMiddle = (ImageView) findViewById(R.id.base_activity_title_right_middleicon);
//		ivTitleArrow = (ImageView) findViewById(R.id.base_activity_title_titleIcon);
		ivTitleRight = (ImageView) findViewById(R.id.base_activity_title_right_righticon);
		tvRight = (TextView) findViewById(R.id.base_activity_title_right_righttv);
//		rlReload = (RelativeLayout) findViewById(R.id.base_activity_load_fail_reload);
//		linear_back = (LinearLayout) findViewById(R.id.linear_back);
//		base_menu = (RelativeLayout) findViewById(R.id.base_menu);
//		tvNoDataContent = (TextView) findViewById(R.id.base_activity_no_data_content);
//		base_tel_main = (ImageView) findViewById(R.id.base_tal_main);
//		button_hidded = (ImageView) findViewById(R.id.button_hidded);
//		iv_order = (ImageView) findViewById(R.id.iv_order);
//		imageView_one = (ImageView) findViewById(R.id.imageView_one);
//		base_shopping_mian = (ImageView) findViewById(R.id.base_shopping_mian);
//		menu_shoppingnum = (TextView)findViewById(R.id.menu_shoppingNum);
//		linear_menu = (LinearLayout) findViewById(R.id.linear_menu);
//		button_hidded_linear = (LinearLayout) findViewById(R.id.button_hidded_linear);
//


//		// 添加地址
//		tvAddAddress = (TextView) findViewById(R.id.base_activity_no_address_add);
//		viewNoDrinksDemand = findViewById(R.id.base_activity_no_drink_demand);
//		NoMenu();
//		NoTelMenu();
//
////		objectAnimator = ObjectAnimator.ofFloat(base_tel_main,"rotation",-30,0,30);
////		objectAnimator.setInterpolator(new LinearInterpolator());
////		objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
////		objectAnimator.setDuration(1000);
////		objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
////		objectAnimator.start();
	}

protected void hindLetfText(){
	base_activity_title_notice.setVisibility(View.GONE);
}
protected void ShowLetfText(){
	base_activity_title_notice.setVisibility(View.VISIBLE);
}

	protected void setLeftText(String text){
	base_activity_title_notice.setText(text);
}

/*
*
	 *
	 * Describe:绑定控件资源
	 *
	 * Date:2015-9-21
	 *
	 * Author:wangkui
*/

	private void setParentViewData() {
		// TODO Auto-generated method stub
		if (setLeftImageResource() != 0) {
			ivTitleLeft.setVisibility(View.VISIBLE);
			ivTitleLeft.setImageResource(setLeftImageResource());
		}
		if (setMiddleImageResource() != 0) {
			ivTitleMiddle.setVisibility(View.VISIBLE);
			ivTitleMiddle.setImageResource(setMiddleImageResource());
		}
		if (setRightImageResource() != 0) {
			ivTitleRight.setVisibility(View.VISIBLE);
			ivTitleRight.setImageResource(setRightImageResource());
		}
		if (hasTitle()) {
			rlTitlePrent.setVisibility(View.VISIBLE);
		} else {
			rlTitlePrent.setVisibility(View.GONE);
		}
		tvTitleName.setText(setTitleName());
		if (rlTitlePrent.getBackground() != null) {
			rlTitlePrent.getBackground().setAlpha(230);
		}
		if (setRightText() != null) {
			tvRight.setVisibility(View.VISIBLE);
			tvRight.setText(setRightText());
		}
	}

	protected void setBackIcon(int id) {
		ivBack.setImageResource(id);

	}

/*
	 *
	 * Describe:设置控件监听
	 *
	 * Date:2015-9-21
	 *
	 * Author:wangkui
*/

	private void setParentViewListener() {
		// TODO Auto-generated method stub
		ivBack.setOnClickListener(this);
//		ivBack_close.setOnClickListener(this);
//		rlReload.setOnClickListener(this);
//		noNetWorkView.setOnClickListener(this);
//		linear_back.setOnClickListener(this);
//		tvAddAddress.setOnClickListener(this);
//		tvSalonMySee.setOnClickListener(this);
//		tvAddClent.setOnClickListener(this);
//		busAddCard.setOnClickListener(this);
//		btDrinksDemandAdd.setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
//		case R.id.linear_back:
//			// 返回键处理
//			onBackPressed();
//			break;
//		case R.id.base_activity_title_backicon:
//			// 返回键处理
//			onBackPressed();
//			break;
//
//			case R.id.base_activity_load_fail_reload:
//

//				 * 数据加载失败重新加载数据
//

//				showReloadView();
//				reloadCallback();
//				break;
//
//			case R.id.base_activity_no_data:
//

//				 * 网络异常页面事件
//

//				showReloadView();
//				reloadCallback();
//				break;
//
//			case R.id.base_activity_no_network:
//
//				 * 网络不给力，点击屏幕刷新
//

//				showReloadView();
//				reloadCallback();
//				break;
		}
	}

/*
	 *
	 * Describe:重新加载页面，设置内容视图
	 *
	 * Date:2015-9-21
	 *
	 * Author:wangkui
*/

	protected void setReloadContent(int layoutResID) {
		super.setContentView(R.layout.base_activity);
		// 获取父类外层容器
		llParents = (LinearLayout) findViewById(R.id.base_activity_rootview);
		// 加载子类控件资源
		View childMainView = baseLayoutInflater.inflate(layoutResID, null);
		LayoutParams ll = new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		llParents.addView(childMainView, 1, ll);
//		initParentView();
		setParentViewListener();
		setParentViewData();


		loadChildView();
		setChildViewListener();
	}
/*
*
	 *
	 * Describe:设置加载失败页面
	 *
	 * Date:2015-9-22
	 *
	 * Author:wangkui
*/
	protected void showLoadFailView() {
		// if (llParent != null) {
		// llParent.removeViewAt(1);
		// }
		llParents.setVisibility(View.GONE);
		loadFialView.setVisibility(View.VISIBLE);
	}

	protected void showReloadView() {
		llParents.setVisibility(View.VISIBLE);
		loadFialView.setVisibility(View.GONE);
	}

/*
	 *
	 * Describe:设置数据为空页面
	 *
	 * Date:2015-9-22
	 *
	 * Author:wangkui
*/

	protected void showNoDataView(String content) {
		// if (llParent != null) {
		// llParent.removeViewAt(1);
		// }
		llParents.setVisibility(View.GONE);
		tvNoDataContent.setText(content);
		noDataView.setVisibility(View.VISIBLE);
	}

/*
	 *
	 * Describe:无订单视图
	 *
	 * Date:2015-9-22
	 *
	 * Author:wangkui
*/

	protected void showNoOrderView(String content, int resId) {
		// if (llParent != null) {
		// llParent.removeViewAt(1);
		// }
		llParents.setVisibility(View.GONE);
		tvNoOrderContent.setText(content);
		((ImageView) findViewById(R.id.no_order_iv)).setImageResource(resId);
		noOrderDataView.setVisibility(View.VISIBLE);
	}

/*
	 *
	 * Describe:设置没有网络页面
	 *
	 * Date:2015-9-22
	 *
	 * Author:wangkui
*/

	protected void showNoNetWork() {

		// if (llParent != null) {
		// llParent.removeViewAt(1);
		// }
		llParents.setVisibility(View.GONE);
		noNetWorkView.setVisibility(View.VISIBLE);
	}



/*
	 *
	 * Describe:隐藏返回键
	 *
	 * Date:2015-9-22
	 *
	 * Author:wangkui
*/

	protected void hideBack() {
		ivBack.setVisibility(View.GONE);
	}

/*
	 *
	 * Describe:显示返回键
	 *
	 * Date:2015-11-2
	 *
	 * Author:wangkui
*/

	protected void showBack() {
		ivBack.setVisibility(View.VISIBLE);
	}



	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		InputMethodManager imm0 = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm0.hideSoftInputFromWindow(ivBack.getWindowToken(), 0);
		super.onBackPressed();

//		ActivityManagerUtil.getActivityManager().finishActivity(this);
//		this.overridePendingTransition(R.anim.back_left_into,
//				R.anim.back_right_out);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		// 结束Activity从堆栈中移除
//		ActivityManagerUtil.getActivityManager().finishActivity(this);
	}


//	protected void cancleLoadingDialog() {
//		if (loadingDialog.isShowing()) {
//			loadingDialog.dismiss();
//		}
//	}
/*
	 *
	 * Describe:重新设置标题
	 *
	 * Date:2016-3-11
	 *
	 * Author:wangkui
	 * */


	protected void ResetTitle(String text) {
		tvTitleName.setText(text);
	}
/*
	 *
	 * Describe:隐藏标题
	 *
	 * Date:2016-4-27
	 *
	 * Author:wangkui
*/

	protected void Hindtitle() {
		rlTitlePrent.setVisibility(View.GONE);
	}

	//关闭当前dialog
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}


}
