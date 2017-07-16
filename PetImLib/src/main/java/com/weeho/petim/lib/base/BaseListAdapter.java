package com.weeho.petim.lib.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 
 * 
 * @describe:基础数据适配器
 * 
 * @author:wangkui
 * 
 * @time:2014-11-3上午10:41:28
 * 
 */
public abstract class BaseListAdapter<E> extends BaseAdapter {
	public List<E> dataList;
	protected Context mcontext;
	protected LayoutInflater baseInflater;

	public BaseListAdapter(List<E> data, Context context) {
		super();
		// TODO Auto-generated constructor stub
		this.mcontext = context;
		this.dataList = data;
		baseInflater = LayoutInflater.from(mcontext);
	}

	public BaseListAdapter(List<E> data, Object obj, Context context) {
		super();
		this.mcontext = context;
		this.dataList = data;
		baseInflater = LayoutInflater.from(mcontext);
	}

	/**
	 * 
	 * @describe:用于数据刷新
	 * 
	 * @author:wangkui
	 * 
	 * @time:2014年8月1日上午11:00:56
	 * 
	 */
	public void setNewData(List<E> newData) {
		this.dataList = newData;
		notifyDataSetChanged();

	}

	/**
	 * 
	 * @describe:用于数据源中加入集合
	 * 
	 * @author:wangkui
	 * 
	 * @time:2014年8月1日上午11:05:37
	 * 
	 */
	public void addListData(List<E> addList) {
		this.dataList.addAll(addList);
		notifyDataSetChanged();
	}

	/**
	 * 
	 * @describe:插入单个数据
	 * 
	 * @author:wangkui
	 * 
	 * @time:2014年8月1日上午11:09:45
	 * 
	 */
	public void addItemData(E itemData) {
		this.dataList.add(itemData);
		notifyDataSetChanged();

	}

	@Override
	public int getCount() {
		return dataList == null ? 0 : dataList.size();
	}

	@Override
	public Object getItem(int position) {
		return dataList == null ? null : dataList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = bindView(position, convertView, parent);

		return convertView;
	}

	/**
	 * 
	 * @describe:用于子类实现各自数据的绑定
	 * 
	 * @author:wangkui
	 * 
	 * @time:2014年8月1日上午10:48:33
	 * 
	 */
	public abstract View bindView(int position, View convertView,
			ViewGroup parent);
}
