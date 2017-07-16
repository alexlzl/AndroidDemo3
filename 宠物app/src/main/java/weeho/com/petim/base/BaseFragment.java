

package weeho.com.petim.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import weeho.com.petim.R;



/**
 * 
 * Describe:基础FRAGMENT
 * 
 * Date:2017-4-18
 * 
 * Author:wangkui
 */


public abstract class BaseFragment extends Fragment implements OnClickListener {
	protected Activity baseActivity;
	protected static LayoutInflater baseInflate;
	private RelativeLayout rlReload;
	private RelativeLayout rlReload_main;
	// 异常页面
	protected View loadFialView, noDataView, noNetWorkView, noOrderDataView,
			searchNodataView, noCouponDataView;
	protected LinearLayout llParent;
	private TextView tvNoDataContent, tvNoOrderContent;
	protected ImageView ivBack, ivTitleIcon, ivdownIcon;
	protected RelativeLayout rlTitlePrent;
	protected TextView tvTitleName;
	// 标题栏上右侧三个区域的图标控件ivTitleMiddle
	protected ImageView ivTitleLeft, ivTitleMiddle, ivTitleRight;
	// 右侧文本
	protected TextView tvRight;
	protected ImageView ivNoDataImageView;
//	private LoadingView loadingDialog;
	private LinearLayout linear_back;
	public LinearLayout linear_titlename;
	protected View viewEmptyShopcart, viewEmptyAddress;
	private TextView tvGoBuy;
	protected Button tvAddAddress;


/**
	 * 
	 * Describe:是否显示左侧控件
	 * 
	 * Date:2015-9-22
	 * 
	 * Author:wangkui
	 */


	protected abstract boolean isShowLeftIcon();



/**
	 * 
	 * Describe:初始化页面视图
	 * 
	 * Date:2015-9-22
	 * 
	 * Author:wangkui
	 */


	protected abstract View setContentView(LayoutInflater inflater,
                                           ViewGroup container, Bundle savedInstanceState);


/**
	 * 
	 * Describe:加载数据
	 * 
	 * Date:2015-9-22
	 * 
	 * Author:wangkui
	 */


	protected abstract void getNetData();



/**
	 * 
	 * Describe:设置控件监听
	 * 
	 * Date:2015-9-22
	 * 
	 * Author:wangkui
	 */


	protected abstract void setViewListener();



/**
	 * 
	 * Describe:控制标题栏显示
	 * 
	 * Date:2015-9-22
	 * 
	 * Author:wangkui
	 */


	protected abstract boolean hasTitle();



/**
	 * 
	 * Describe:控制标题栏图标显示
	 * 
	 * Date:2015-9-22
	 * 
	 * Author:wangkui
	 */

	protected abstract boolean hasTitleIcon();


/**
	 * 
	 * Describe:控制标题栏图标显示
	 * 
	 * Date:2015-9-22
	 * 
	 * Author:wangkui
	 */



	protected abstract boolean hasDownIcon();



/**
	 * 
	 * Describe:重新加载页面的回调
	 * 
	 * Date:2015-9-22
	 * 
	 * Author:wangkui
	 */


	protected abstract void reloadCallback();



/**
	 * 
	 * Describe:设置标题名称
	 * 
	 * Date:2015-9-22
	 * 
	 * Author:wangkui
	 */


	protected abstract String setTitleName();



/**
	 * 
	 * Describe:设置右侧文本
	 * 
	 * Date:2015-9-22
	 * 
	 * Author:wangkui
	 */


	protected abstract String setRightText();



/**
	 * 
	 * Describe:设置左侧的视图资源
	 * 
	 * Date:2015-9-22
	 * 
	 * Author:wangkui
	 */


	protected abstract int setLeftImageResource();


/**
	 * 
	 * Describe:设置中间控件视图资源
	 * 
	 * Date:2015-9-22
	 * 
	 * Author:wangkui
	 */


	protected abstract int setMiddleImageResource();



/**
	 * 
	 * Describe:设置右侧控件视图资源
	 * 
	 * Date:2015-9-22
	 * 
	 * Author:wangkui
	 */


	protected abstract int setRightImageResource();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		baseActivity = getActivity();
	}

	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		baseInflate = getActivity().getLayoutInflater();
		container = (ViewGroup) inflater.inflate(R.layout.base_fragment, null);
		initParentView(container);
		setParentViewData();
		setParentViewListener();
		return setContentView(inflater, container, savedInstanceState);
	}



/**
	 * 
	 * Describe:初始化父类控件
	 * 
	 * Date:2015-9-22
	 * 
	 * Author:wangkui
	 */


	private void initParentView(View rootView) {
		// TODO Auto-generated method stub
		tvAddAddress = (Button) rootView
				.findViewById(R.id.base_activity_no_address_add);
		viewEmptyAddress = rootView
				.findViewById(R.id.base_fragment_address_nodata);
		tvGoBuy = (TextView) rootView
				.findViewById(R.id.base_activity_no_shopcart_buy);
		viewEmptyShopcart = rootView
				.findViewById(R.id.base_fragment_shopcart_nodata);
		searchNodataView = rootView
				.findViewById(R.id.base_activity_search_nodata);
		llParent = (LinearLayout) rootView
				.findViewById(R.id.base_fragment_rootview);
		loadFialView = rootView.findViewById(R.id.base_activity_loadfail);
		noDataView = rootView.findViewById(R.id.base_activity_no_data);
		noNetWorkView = rootView.findViewById(R.id.base_activity_no_network);
		ivNoDataImageView = (ImageView) rootView.findViewById(R.id.no_order_iv);
		ivBack = (ImageView) rootView
				.findViewById(R.id.base_activity_title_backicon);
		rlTitlePrent = (RelativeLayout) rootView
				.findViewById(R.id.base_activity_title_parent);
		tvTitleName = (TextView) rootView
				.findViewById(R.id.base_activity_title_titlename);
		linear_titlename = (LinearLayout) rootView
				.findViewById(R.id.linear_titlename);
		ivTitleIcon = (ImageView) rootView
				.findViewById(R.id.base_activity_title_titleIcon);
		ivdownIcon = (ImageView) rootView
				.findViewById(R.id.base_activity_title_downicon);
		ivTitleLeft = (ImageView) rootView
				.findViewById(R.id.base_activity_title_right_lefticon);
		ivTitleMiddle = (ImageView) rootView
				.findViewById(R.id.base_activity_title_right_middleicon);
		ivTitleRight = (ImageView) rootView
				.findViewById(R.id.base_activity_title_right_righticon);
		tvRight = (TextView) rootView
				.findViewById(R.id.base_activity_title_right_righttv);
		rlReload = (RelativeLayout) rootView
				.findViewById(R.id.base_activity_load_fail_reload);
		rlReload_main = (RelativeLayout) rootView
				.findViewById(R.id.base_activity_load_fail_main);
		linear_back = (LinearLayout) (rootView.findViewById(R.id.linear_back));
		tvNoDataContent = (TextView) rootView
				.findViewById(R.id.base_activity_no_data_content);

	}



/**
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
		if (hasTitleIcon()) {
			ivTitleIcon.setVisibility(View.VISIBLE);
		} else {
			ivTitleIcon.setVisibility(View.GONE);
		}
		tvTitleName.setText(setTitleName());
		if (rlTitlePrent.getBackground() != null) {
			rlTitlePrent.getBackground().setAlpha(230);
		}
		if (setRightText() != null) {
			tvRight.setVisibility(View.VISIBLE);
			tvRight.setText(setRightText());
		}
		if (isShowLeftIcon()) {
			ivBack.setVisibility(View.VISIBLE);
		} else {
			ivBack.setVisibility(View.GONE);
		}
//		if (hasDownIcon()) {
//			ivdownIcon.setVisibility(View.VISIBLE);
//		} else {
//			ivdownIcon.setVisibility(View.GONE);
//		}
	}



/**
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
		rlReload.setOnClickListener(this);
		rlReload_main.setOnClickListener(this);
		noNetWorkView.setOnClickListener(this);
		linear_back.setOnClickListener(this);
		tvAddAddress.setOnClickListener(this);
		tvGoBuy.setOnClickListener(this);
	}



/**
	 * 事件响应
	 */


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.linear_back:
			// 返回键处理
			onBackPressed();
			break;
		case R.id.base_activity_title_backicon:
			// 返回键处理
			onBackPressed();
			break;

	}}



/**
	 * 处理返回界面
	 */


	public void onBackPressed() {
		// TODO Auto-generated method stub
		InputMethodManager imm0 = (InputMethodManager) getActivity()
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm0.hideSoftInputFromWindow(ivBack.getWindowToken(), 0);
		// super.onBackPressed();

//		ActivityManagerUtil.getActivityManager().finishActivity();
		 getActivity().finish();}




/**
	 * 
	 * Describe:设置加载失败页面
	 * 
	 * Date:2015-9-22
	 * 
	 * Author:wangkui
	 */

//	protected void showLoadFailView() {
//		loadFialView.setVisibility(View.VISIBLE);
//	}


/**
	 * 
	 * Describe:设置数据为空页面
	 * 
	 * Date:2015-9-22
	 * 
	 * Author:wangkui
	 *//*
*/


	protected void showNoDataView(String content) {
		tvNoDataContent.setText(content);
		noDataView.setVisibility(View.VISIBLE);
	}


/**
	 * 
	 * Describe:无订单视图
	 * 
	 * Date:2015-9-22
	 * 
	 * Author:wangkui
	 */



	protected void showNoOrderView(String content, int resId) {
		tvNoOrderContent.setText(content);
		ivNoDataImageView.setImageResource(resId);
		noOrderDataView.setVisibility(View.VISIBLE);
	}



/**
	 * 
	 * Describe:展示购物车空白页面
	 * 
	 * Date:2015-10-30
	 * 
	 * Author:wangkui
	 */

	protected void showShopCartEmpty() {
		viewEmptyShopcart.setVisibility(View.VISIBLE);

	}

	public void hideShopCartEmpty() {
		viewEmptyShopcart.setVisibility(View.GONE);

	}
/**
	 * 
	 * Describe:设置没有网络页面
	 * 
	 * Date:2015-9-22
	 * 
	 * Author:wangkui
	 */


	protected void showNoNetWork() {
		noNetWorkView.setVisibility(View.VISIBLE);
	}

/**
	 * 
	 * Describe:显示搜索没有数据页面
	 * 
	 * Date:2015-9-22
	 * 
	 * Author:wangkui
	 */
/*

	protected void showSearchNoData() {
		searchNodataView.setVisibility(View.VISIBLE);
	}

	*//*

*/
/**
	 * 
	 * Describe:显示空地址页面
	 * 
	 * Date:2015-10-30
	 * 
	 * Author:wangkui
	 *//*
*/
/*

	protected void showNoAddressView() {
		viewEmptyAddress.setVisibility(View.VISIBLE);

	}

	*//*

*/
/**
	 * 
	 * Describe:隐藏返回键
	 * 
	 * Date:2015-9-22
	 * 
	 * Author:wangkui
	 *//*
*/
/*

	protected void hideBack() {
		ivBack.setVisibility(View.GONE);
	}

	*//*

*/
/**
	 * 
	 * Describe:隐藏异常界面
	 * 
	 * Date:2015-9-22
	 * 
	 * Author:wangkui
	 *//*
*/
/*

	protected void showReloadView() {
		loadFialView.setVisibility(View.GONE);
		searchNodataView.setVisibility(View.GONE);
		noNetWorkView.setVisibility(View.GONE);
		noOrderDataView.setVisibility(View.GONE);
		noDataView.setVisibility(View.GONE);
		loadFialView.setVisibility(View.GONE);
	}

	*//*

*/
/**
	 * 
	 * Describe:显示返回键
	 * 
	 * Date:2015-11-2
	 * 
	 * Author:wangkui
	 *//*
*/
/*

	protected void showBack() {
		ivBack.setVisibility(View.VISIBLE);

	}

	*//*

*/
/**
	 * 
	 * Describe:重新设置标题
	 * 
	 * Date:2016-3-11
	 * 
	 * Author:wangkui
	 *//*
*/
/*

	protected void ResetTitle(String text) {
		tvTitleName.setText(text);
	}

	*//*

*/
/**
	 * 
	 * Describe:显示对话框
	 * 
	 * Date:2015-9-22
	 * 
	 * Author:wangkui
	 *//*
*/
/*

//	protected void showLoadingDialog() {
//		if (loadingDialog == null && baseActivity!=null) {
//			loadingDialog = new LoadingView(baseActivity);
//			loadingDialog.show();
//		} else {
//			if (loadingDialog != null && !loadingDialog.isShowing() && baseActivity!=null) {
//				loadingDialog = new LoadingView(baseActivity);
//				loadingDialog.show();
//			}
//		}
//
//	}

	*//*

*/
/**
	 * 
	 * Describe:取消对话框
	 * 
	 * Date:2015-9-22
	 * 
	 * Author:wangkui
	 */


//	protected void cancleLoadingDialog() {
//		if (loadingDialog != null) {
//			if (loadingDialog.isShowing()) {
//				loadingDialog.dismiss();
//			}
//		}
//
//	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setViewListener();
		getNetData();
	}
	@Override
	public void onStop() {
	// TODO Auto-generated method stub
	super.onStop();
	//关闭当前dialog
	}}

