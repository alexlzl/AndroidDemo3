package com.weeho.petim.lib.view;

import com.weeho.petim.lib.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * 
 * Describe:自定义带刷新状态的ListView
 * 
 * Date:2015-9-24
 * 
 * Author:wangkui
 */
public class RefreshListView extends ListView implements OnScrollListener {
	private final static int RELEASE_To_REFRESH = 0;
	private final static int PULL_To_REFRESH = 1;
	private final static int REFRESHING = 2;
	private final static int DONE = 3;
	private final static int LOADING = 4;
	// 实际的padding的距离与界面上偏移距离的比例
	private final static int RATIO = 3;
	private LayoutInflater inflater;
	private LinearLayout headView;
	private boolean isRecored;
	private int headContentHeight;
	private int startY;

	private boolean isBack;
	private OnRefreshListener refreshListener;
	private OnMoreListener loadMoreListener;
	private boolean isRefreshable;
	private View moreView;
	// 刷新状态
	private int state;
	// 第一个可见的item的索引
	private int firstItemIndex;
	// 标记是否可以分页加载:显示内容超过一页可以进行分页加载
	private boolean isCanLoadMore = true;
	// 是否滑动到顶部回调事件
	private ToTopCallBack toToplistener;

	// 下拉刷新进度框
	private ProgressBar refreshPb;
	// 加载更多进度框
	private ProgressBar loadmorePb;
	// 下来刷新提示语
	private TextView refreshNote;
	// 分页加载提示语
	private TextView loadMoreNote;

	public RefreshListView(Context context) {
		super(context);
		initView(context);
		setViewListener();
	}

	public RefreshListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
		setViewListener();
	}

	/**
	 * 
	 * 
	 * @describe:初始化
	 * 
	 * @author:wangkui
	 * 
	 * @time:2014-12-15下午2:34:34
	 * 
	 */
	private void initView(Context context) {
		/**
		 * 加载头部视图
		 */
		inflater = LayoutInflater.from(context);
		headView = (LinearLayout) inflater
				.inflate(R.layout.listview_head, null);
		refreshPb = (ProgressBar) headView.findViewById(R.id.listview_head_pb);
		refreshNote = (TextView) headView.findViewById(R.id.listview_head_note);
		measureHeadView(headView);
		headContentHeight = headView.getMeasuredHeight();
		// 设置间距隐藏头部视图
		headView.setPadding(0, -1 * headContentHeight, 0, 0);
		headView.invalidate();
		addHeaderView(headView, null, false);
		state = DONE;
		isRefreshable = false;
		/**
		 * 加载底部更多视图
		 */
		moreView = LayoutInflater.from(context).inflate(R.layout.listview_foot,
				null);
		loadmorePb = (ProgressBar) moreView.findViewById(R.id.listview_foot_pb);
		loadMoreNote = (TextView) moreView.findViewById(R.id.listview_foot_tv);
		addFooterView(moreView);
	}

	/**
	 * 
	 * Describe:设置头部视图布局
	 * 
	 * Date:2015-11-5
	 * 
	 * Author:wangkui
	 */
	private void measureHeadView(View headView) {
		ViewGroup.LayoutParams p = headView.getLayoutParams();
		if (p == null) {
			p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
		}
		int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0, p.width);
		int lpHeight = p.height;
		int childHeightSpec;
		if (lpHeight > 0) {
			childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight,
					MeasureSpec.EXACTLY);
		} else {
			childHeightSpec = MeasureSpec.makeMeasureSpec(0,
					MeasureSpec.UNSPECIFIED);
		}
		headView.measure(childWidthSpec, childHeightSpec);
	}

	/**
	 * 
	 * Describe:设置监听事件
	 * 
	 * Date:2015-11-5
	 * 
	 * Author:wangkui
	 */
	private void setViewListener() {
		setOnScrollListener(this);
	}

	/**
	 * 滚动回调事件
	 */
	@Override
	public void onScroll(AbsListView arg0, int firstVisiableItem,
			int visibleItemCount, int totalItemCount) {
		if (visibleItemCount == totalItemCount) {
			/**
			 * 数据未超过一页，禁止分页加载
			 */
			isCanLoadMore = false;
			if (toToplistener != null) {
				toToplistener.hide();
			}

		} else {
			if (visibleItemCount < totalItemCount) {
				/**
				 * 数据超过一页，可以进行分页
				 */
				isCanLoadMore = true;
				if (toToplistener != null) {
					toToplistener.show();
				}

			}
		}
		firstItemIndex = firstVisiableItem;
		if (visibleItemCount < totalItemCount
				&& (firstItemIndex + visibleItemCount) == totalItemCount
				&& loadMoreNote != null) {
			// if
			// (getResources().getString(R.string.app_list_footer_more).equals(
			// loadMoreNote.getText())) {
			// onLoadMore();
			// }
			// onLoadMore();
		}
	}

	/**
	 * 滑动监听
	 */
	public void onScrollStateChanged(AbsListView arg0, int scrollState) {
		switch (scrollState) {

		// 当不滚动时
		case OnScrollListener.SCROLL_STATE_IDLE:
			// 判断滚动到底部
			if (this.getLastVisiblePosition() == (this.getCount() - 1)) {
				if (loadMoreListener != null) {
					if (isCanLoadMore) {
						loadMoreListener.onLoadMore();
						moreView.setVisibility(View.VISIBLE);
						loadmorePb.setVisibility(View.VISIBLE);
						loadMoreNote.setText("努力加载中");

					}
				}
			}
			// 判断滚动到顶部

			if (this.getFirstVisiblePosition() == 0) {
			}

			break;
		}
	}

	/**
	 * 滑动事件监听
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		if (isRefreshable) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				if (firstItemIndex == 0 && !isRecored) {
					isRecored = true;
					startY = (int) event.getY();
				}
				break;

			case MotionEvent.ACTION_UP:
				if (state != REFRESHING && state != LOADING) {
					if (state == DONE) {

					}
					if (state == PULL_To_REFRESH) {
						state = DONE;
						changeHeaderViewByState();
					}
					if (state == RELEASE_To_REFRESH) {
						state = REFRESHING;
						changeHeaderViewByState();
						onRefresh();
					}
				}

				isRecored = false;
				isBack = false;

				break;

			case MotionEvent.ACTION_MOVE:
				int tempY = (int) event.getY();

				if (!isRecored && firstItemIndex == 0) {
					isRecored = true;
					startY = tempY;
				}

				if (state != REFRESHING && isRecored && state != LOADING) {

					// 保证在设置padding的过程中，当前的位置一直是在head，否则如果当列表超出屏幕的话，当在上推的时候，列表会同时进行滚动
					// 可以松手去刷新了
					if (state == RELEASE_To_REFRESH) {
						/**
						 * 释放刷新============================
						 */

						setSelection(0);

						// 往上推了，推到了屏幕足够掩盖head的程度，但是还没有推到全部掩盖的地步
						if (((tempY - startY) / RATIO < headContentHeight)
								&& (tempY - startY) > 0) {
							state = PULL_To_REFRESH;
							changeHeaderViewByState();
						}
						// 一下子推到顶了
						else if (tempY - startY <= 0) {
							state = DONE;
							changeHeaderViewByState();
						}
						// 往下拉了，或者还没有上推到屏幕顶部掩盖head的地步
					}
					// 还没有到达显示松开刷新的时候,DONE或者是PULL_To_REFRESH状态
					if (state == PULL_To_REFRESH) {
						/**
						 * 处于下拉刷新状态================
						 */

						setSelection(0);

						// 下拉到可以进入RELEASE_TO_REFRESH的状态
						if ((tempY - startY) / RATIO >= headContentHeight) {
							state = RELEASE_To_REFRESH;
							isBack = true;
							changeHeaderViewByState();
						} else if (tempY - startY <= 0) {
							state = DONE;
							changeHeaderViewByState();
						}
					}

					if (state == DONE) {
						if (tempY - startY > 0) {
							state = PULL_To_REFRESH;
							changeHeaderViewByState();
						}
					}

					if (state == PULL_To_REFRESH) {
						headView.setPadding(0, -1 * headContentHeight
								+ (tempY - startY) / RATIO, 0, 0);

					}

					if (state == RELEASE_To_REFRESH) {
						headView.setPadding(0, (tempY - startY) / RATIO
								- headContentHeight, 0, 0);
					}

				}

				break;
			}
		}

		return super.onTouchEvent(event);
	}

	/**
	 * 
	 * 
	 * @describe:当状态改变时候，调用该方法，以更新界面
	 * 
	 * @author:wangkui
	 * 
	 * @time:2014-12-15下午3:07:20
	 * 
	 */
	private void changeHeaderViewByState() {
		switch (state) {
		case RELEASE_To_REFRESH:
			/**
			 * 松开刷新状态，显示动画
			 */
			refreshNote.setText("松开刷新");
			refreshPb.setVisibility(View.VISIBLE);
			break;
		case PULL_To_REFRESH:
			/**
			 * 下拉刷新状态，显示默认图片
			 */
			// 是由RELEASE_To_REFRESH状态转变来的
			if (isBack) {
				isBack = false;
				refreshNote.setText("下拉刷新");
			} else {
				refreshNote.setText("下拉刷新");
			}
			refreshPb.setVisibility(View.VISIBLE);
			break;

		case REFRESHING:
			/**
			 * 正在刷新状态，显示圆形进度框
			 */
			headView.setPadding(0, 0, 0, 0);
			refreshNote.setText("正在刷新...");
			refreshPb.setVisibility(View.VISIBLE);
			break;
		case DONE:
			/**
			 * 刷新完成状态，隐藏头部视图
			 */
			headView.setPadding(0, -1 * headContentHeight, 0, 0);
			refreshPb.setVisibility(View.GONE);
			refreshNote.setText("下拉刷新");
			break;
		}
	}

	public void setonRefreshListener(OnRefreshListener refreshListener) {
		this.refreshListener = refreshListener;
		isRefreshable = true;
	}

	public void setonLoadListener(OnMoreListener loadListener) {
		this.loadMoreListener = loadListener;
	}

	/**
	 * 
	 * 
	 * @describe:刷新监听
	 * 
	 * @author:wangkui
	 * 
	 * @time:2014-12-15下午3:34:52
	 * 
	 */
	public interface OnRefreshListener {
		public void onRefresh();
	}

	/**
	 * 
	 * 
	 * @describe:加载更多监听
	 * 
	 * @author:wangkui
	 * 
	 * @time:2014-12-15下午3:35:10
	 * 
	 */
	public interface OnMoreListener {
		public void onLoadMore();
	}

	/**
	 * 
	 * 
	 * @describe:刷新完成
	 * 
	 * @author:wangkui
	 * 
	 * @time:2014-12-15下午3:34:30
	 * 
	 */
	public void onRefreshComplete() {
		state = DONE;
		changeHeaderViewByState();
	}

	/**
	 * 
	 * 
	 * @describe:加载更多
	 * 
	 * @author:wangkui
	 * 
	 * @time:2014-12-15下午3:34:19
	 * 
	 */
	private void onLoadMore() {
		if (loadMoreListener != null) {
			loadmorePb.setVisibility(View.VISIBLE);
			loadMoreNote.setText(R.string.app_list_footer_loading);
			loadMoreListener.onLoadMore();
		}
	}

	/**
	 * 
	 * 
	 * @describe:加载更多完成
	 * 
	 * @author:wangkui
	 * 
	 * @time:2014-12-15下午3:33:09
	 * 
	 */
	public void onLoadComplete() {
		moreView.setVisibility(View.GONE);
		loadmorePb.setVisibility(View.GONE);
	}

	/**
	 * 
	 * 
	 * @describe:刷新回调
	 * 
	 * @author:wangkui
	 * 
	 * @time:2014-12-15下午3:32:59
	 * 
	 */
	private void onRefresh() {
		if (refreshListener != null) {
			refreshListener.onRefresh();
		}
	}

	/**
	 * 
	 * 
	 * @describe:没有更多数据回调
	 * 
	 * @author:wangkui
	 * 
	 * @time:2014-12-15下午3:32:38
	 * 
	 */
	public void onNoData() {
		loadmorePb.setVisibility(View.GONE);
		loadMoreNote.setText("亲，已经到底了~~");
	}

	/**
	 * 
	 * 
	 * @describe:设置到达顶部回调
	 * 
	 * @author:wangkui
	 * 
	 * @time:2014-12-15下午3:31:10
	 * 
	 */
	public void setTopCallBack(ToTopCallBack listener) {
		this.toToplistener = listener;
	}

	/**
	 * 
	 * Describe:滚动到顶部事件回调
	 * 
	 * Date:2015-11-5
	 * 
	 * Author:wangkui
	 */
	public interface ToTopCallBack {
		public void show();

		public void hide();
	}
}
