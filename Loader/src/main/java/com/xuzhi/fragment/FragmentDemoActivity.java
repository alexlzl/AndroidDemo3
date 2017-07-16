package com.xuzhi.fragment;

import java.io.File;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.AsyncTaskLoader;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.Loader;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.SearchView.OnQueryTextListener;

public class FragmentDemoActivity extends Activity {

	public static String[] array = { "text1,", "text2", "text3", "text4",
			"text5,", "text6", "text7", "text8" };

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		FragmentManager fm = getFragmentManager();
//
//        // Create the list fragment and add it as our sole content.
//        if (fm.findFragmentById(android.R.id.content) == null) {
//            DetailsFragment list = new DetailsFragment();
//            fm.beginTransaction().add(android.R.id.content, list).commit();
//        }
		setContentView(R.layout.main);
	}

	public static class TitlesFragment extends ListFragment {

		boolean mDualPane;
		int mCurCheckPosition = 0;

		@Override
		public void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			System.out.println("Fragment-->onCreate");
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			System.out.println("Fragment-->onCreateView");
			return super.onCreateView(inflater, container, savedInstanceState);
		}

		@Override
		public void onPause() {
			// TODO Auto-generated method stub
			super.onPause();
			System.out.println("Fragment-->onPause");
		}

		@Override
		public void onStop() {
			// TODO Auto-generated method stub
			super.onStop();

			System.out.println("Fragment-->onStop");
		}

		@Override
		public void onAttach(Activity activity) {
			// TODO Auto-generated method stub
			super.onAttach(activity);
			System.out.println("Fragment-->onAttach");
		}

		@Override
		public void onStart() {
			// TODO Auto-generated method stub
			super.onStart();
			System.out.println("Fragment-->onStart");
		}

		@Override
		public void onResume() {
			// TODO Auto-generated method stub
			super.onResume();
			System.out.println("Fragment-->onResume");
		}

		@Override
		public void onDestroy() {
			// TODO Auto-generated method stub
			super.onDestroy();
			System.out.println("Fragment-->onDestroy");
		}

		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onActivityCreated(savedInstanceState);
			System.out.println("Fragment-->onActivityCreted");
			setListAdapter(new ArrayAdapter<String>(getActivity(),
					android.R.layout.simple_list_item_1, array));

			View detailsFrame = getActivity().findViewById(R.id.details);

			mDualPane = detailsFrame != null
					&& detailsFrame.getVisibility() == View.VISIBLE;

			if (savedInstanceState != null) {
				mCurCheckPosition = savedInstanceState.getInt("curChoice", 0); // 从保存的状态中取出数据
			}

			if (mDualPane) {
				getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);

				showDetails(mCurCheckPosition);
			}
		}

		@Override
		public void onSaveInstanceState(Bundle outState) {
			// TODO Auto-generated method stub
			super.onSaveInstanceState(outState);

			outState.putInt("curChoice", mCurCheckPosition);// 保存当前的下标
		}

		@Override
		public void onListItemClick(ListView l, View v, int position, long id) {
			// TODO Auto-generated method stub
			super.onListItemClick(l, v, position, id);
			showDetails(position);
		}

		void showDetails(int index) {
			mCurCheckPosition = index;
			if (mDualPane) {
				getListView().setItemChecked(index, true);
				DetailsFragment details = (DetailsFragment) getFragmentManager()
						.findFragmentById(R.id.details);
				if (details == null || details.getShownIndex() != index) {
					details = DetailsFragment.newInstance(mCurCheckPosition);

					// 得到一个fragment 事务（类似sqlite的操作）
					FragmentTransaction ft = getFragmentManager()
							.beginTransaction();
					ft.replace(R.id.details, details);// 将得到的fragment
					// 替换当前的viewGroup内容，add则不替换会依次累加
					ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);// 设置动画效果
					ft.commit();// 提交
				}
			} else {
				new AlertDialog.Builder(getActivity()).setTitle(
						android.R.string.dialog_alert_title).setMessage(
						array[index]).setPositiveButton(android.R.string.ok,
						null).show();
			}
		}
	}

	/**
	 * @author terry
	 * 
	 */
	public static class DetailsFragment extends ListFragment implements OnQueryTextListener,LoaderCallbacks<List<AppEntry>>{

		 AppListAdapter mAdapter;
		  String mCurFilter;
		@Override
		public void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setHasOptionsMenu(true);
			
		}
		
		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onActivityCreated(savedInstanceState);
			mAdapter = new AppListAdapter(getActivity());
            setListAdapter(mAdapter);
            
            // Start out with a progress indicator.
            setListShown(false);

            // Prepare the loader.  Either re-connect with an existing one,
            // or start a new one.
            getLoaderManager().initLoader(0, null, this);
		}

		public static DetailsFragment newInstance(int index) {
			DetailsFragment details = new DetailsFragment();
			Bundle args = new Bundle();
			args.putInt("index", index);
			details.setArguments(args);
			return details;
		}

		public int getShownIndex() {
			return getArguments().getInt("index", 0);
		}

//		@Override
//		public View onCreateView(LayoutInflater inflater, ViewGroup container,
//				Bundle savedInstanceState) {
//			// TODO Auto-generated method stub
//			if (container == null)
//				return null;
//
//			ScrollView scroller = new ScrollView(getActivity());
//			TextView text = new TextView(getActivity());
//
//			int padding = (int) TypedValue.applyDimension(
//					TypedValue.COMPLEX_UNIT_DIP, 4, getActivity()
//							.getResources().getDisplayMetrics());
//			text.setPadding(padding, padding, padding, padding);
//			scroller.addView(text);
//
//			text.setText(array[getShownIndex()]);
//			return scroller;
//		}

		@Override
		public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
			// TODO Auto-generated method stub
			super.onCreateOptionsMenu(menu, inflater);
			menu.add("Menu 1a")
					.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
			menu.add("Menu 1b")
					.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		}

		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
			// TODO Auto-generated method stub
		 
			return super.onOptionsItemSelected(item);
		}

		public boolean onQueryTextChange(String newText) {
			// TODO Auto-generated method stub
			return false;
		}

		public boolean onQueryTextSubmit(String query) {
			// TODO Auto-generated method stub
			return false;
		}

		public Loader<List<com.xuzhi.fragment.FragmentDemoActivity.AppEntry>> onCreateLoader(
				int id, Bundle args) {
			// TODO Auto-generated method stub
			return new AppListLoader(getActivity());
		}

		/**
		 * Load 完成后
		 */
		public void onLoadFinished(
				Loader<List<com.xuzhi.fragment.FragmentDemoActivity.AppEntry>> arg0,
				List<com.xuzhi.fragment.FragmentDemoActivity.AppEntry> arg1) {
			// TODO Auto-generated method stub
			mAdapter.setData(arg1);
			  if (isResumed()) {
	                setListShown(true);
	            } else {
	                setListShownNoAnimation(true);
	            }
		}

		/**
		 * Loader 重置时
		 */
		public void onLoaderReset(
				Loader<List<com.xuzhi.fragment.FragmentDemoActivity.AppEntry>> arg0) {
			// TODO Auto-generated method stub
			 mAdapter.setData(null);
		}
	}

	/**
	 * java bean
	 * @author terry
	 *
	 */
	public static class AppEntry {
		private final AppListLoader mLoader;
		private final ApplicationInfo mInfo;
		private final File mApkFile;
		private String mLable;
		private Drawable mIcon;
		private boolean mMounted;

		public AppEntry(AppListLoader loader, ApplicationInfo info) {
			mLoader = loader;
			mInfo = info;
			mApkFile = new File(info.sourceDir);
		}

		public ApplicationInfo getApplicationInfo() {
			return mInfo;
		}

		public String getLable() {
			return mLable;
		}

		public Drawable getIcon() {
			if (mIcon == null) {
				if (mApkFile.exists()) {
					mIcon = mInfo.loadIcon(mLoader.mPm);
					return mIcon;
				} else {
					mMounted = false;
				}
			} else if (!mMounted) {
				if (mApkFile.exists()) {
					mMounted = true;
					mIcon = mInfo.loadIcon(mLoader.mPm);
					return mIcon;
				}
			} else {
				return mIcon;
			}
			return mLoader.getContext().getResources().getDrawable(
					android.R.drawable.sym_def_app_icon);
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return mLable.toString();
		}

		void loadLable(Context mContext) {
			if (mLable == null || !mMounted) {
				if (!mApkFile.exists()) {
					mMounted = false;
					mLable = mInfo.packageName;
				} else {
					mMounted = true;
					CharSequence lable = mInfo.loadLabel(mContext
							.getPackageManager());
					mLable = lable != null ? lable.toString()
							: mInfo.packageName;
				}
			}
		}
	}

	/**
	 * 继承asyncTaskLoader
	 * @author terry
	 *
	 */
	public static class AppListLoader extends AsyncTaskLoader<List<AppEntry>> {

		final InterestingConfigChanges mLastConfig = new InterestingConfigChanges();
		final PackageManager mPm;

		List<AppEntry> mApps;
		packageIntentReceiver mPackageObserver;

		public AppListLoader(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
			mPm = getContext().getPackageManager();
		}

		@Override
		public List<AppEntry> loadInBackground() {
			// TODO Auto-generated method stub
			List<ApplicationInfo> apps = mPm
					.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES
							| PackageManager.GET_DISABLED_COMPONENTS);
			if (apps == null)
				apps = new ArrayList<ApplicationInfo>();
			final Context mContext = getContext();
			List<AppEntry> entries = new ArrayList<AppEntry>(apps.size());
			for (ApplicationInfo info : apps) {
				AppEntry entry = new AppEntry(this, info);
				entry.loadLable(mContext);
				entries.add(entry);
			}
			Collections.sort(entries, ALPHA_COMPARATOR);
			return entries;
		}

		@Override
		public void deliverResult(
				List<com.xuzhi.fragment.FragmentDemoActivity.AppEntry> data) {
			// TODO Auto-generated method stub
			if (isReset()) {
				if (data != null) {
					//释放资源处理
				}
			}
			
			List<AppEntry> oladApps=data;
			mApps=data;
			if(isStarted()){
				super.deliverResult(data);
			}
			
			
			if(oladApps!=null){
				//释放资源
			}
		}
		
		@Override
		public void onStartLoading() {
			if(mApps!=null)
				deliverResult(mApps);
			
			if(mPackageObserver==null)
				mPackageObserver=new packageIntentReceiver(this);
			
			boolean configChange=mLastConfig.applyNewConfig(getContext().getResources());
			
			if(takeContentChanged()|| mApps== null || configChange){
				forceLoad();
			}
		};
		
		@Override
		public void onCanceled(
				List<com.xuzhi.fragment.FragmentDemoActivity.AppEntry> data) {
			// TODO Auto-generated method stub
			super.onCanceled(data);
			cancelLoad();
		}
		
		@Override
		protected void onReset() {
			// TODO Auto-generated method stub
			super.onReset();
			onStopLoading();
			
			if(mApps!=null){
				//释放资源
				mApps=null;
			}
			
			if(mPackageObserver!=null){
				getContext().unregisterReceiver(mPackageObserver);
				mPackageObserver=null;
			}
		}

	}

	public static class InterestingConfigChanges {
		final Configuration mConfiguration = new Configuration();
		int mLastDensity;

		boolean applyNewConfig(Resources res) {
			int configChanges = mConfiguration.updateFrom(res
					.getConfiguration());
			boolean desityChange = mLastDensity != res.getDisplayMetrics().densityDpi;
			if (desityChange
					|| (configChanges & (ActivityInfo.CONFIG_LOCALE
							| ActivityInfo.CONFIG_UI_MODE | ActivityInfo.CONFIG_SCREEN_LAYOUT)) != 0) {
				mLastDensity = res.getDisplayMetrics().densityDpi;
				return true;
			}
			return false;
		}
	}

	public static final Comparator<AppEntry> ALPHA_COMPARATOR = new Comparator<AppEntry>() {
		private final Collator sCollator = Collator.getInstance();

		public int compare(AppEntry object1, AppEntry object2) {
			return sCollator.compare(object1.getLable(), object2.getLable());
		}
	};

	/**
	 * 广播监听应用程序变化
	 * @author terry
	 *
	 */
	public static class packageIntentReceiver extends BroadcastReceiver {

		final AppListLoader mLoader;

		public packageIntentReceiver(AppListLoader loader) {
			mLoader = loader;
			IntentFilter filter = new IntentFilter(Intent.ACTION_PACKAGE_ADDED);
			filter.addAction(Intent.ACTION_PACKAGE_REMOVED);
			filter.addAction(Intent.ACTION_PACKAGE_CHANGED);
			filter.addDataScheme("package");
			mLoader.getContext().registerReceiver(this, filter);
			// Register for events related to sdcard installation.
			IntentFilter sdFilter = new IntentFilter();
			sdFilter.addAction(Intent.ACTION_EXTERNAL_APPLICATIONS_AVAILABLE);
			sdFilter.addAction(Intent.ACTION_EXTERNAL_APPLICATIONS_UNAVAILABLE);
			mLoader.getContext().registerReceiver(this, sdFilter);
		}

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			//当发生变化时通知loader 自动刷新数据
			mLoader.onContentChanged();
		}

	}
	
	/**
	 * 做数据源
	 * @author terry
	 *
	 */
	public static class AppListAdapter extends ArrayAdapter<AppEntry>{

		
		private LayoutInflater mInflater;
		public AppListAdapter(Context context) { 
			// TODO Auto-generated constructor stub
			super(context, android.R.layout.simple_list_item_2);
			mInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
		}
		
		
		public void setData(List<AppEntry> data){
			clear();
			if(data!=null){
				addAll(data);
			}
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View view;
			if(convertView==null){
				view=mInflater.inflate(R.layout.list_item_icon_text, parent,false);
			}else{
				view=convertView;
			}
			
			AppEntry item=getItem(position);
			((ImageView)view.findViewById(R.id.icon)).setImageDrawable(item.getIcon());
			((TextView)view.findViewById(R.id.text)).setText(item.getLable());
			return view;
		}
		
	}
}