package com.gyzhong.viewpagerindicator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by gyzhong on 15/2/13.
 */
public class ViewPagerAdapter extends BaseViewPagerAdapter<Integer> {
    
    protected ViewPagerAdapter(Context context, List<Integer> mDataList) {
        super(context, mDataList);
    }

    @Override
    public void setViewData(View view, Integer id) {
        ImageView imageView = (ImageView) view.findViewById(R.id.id_view_pager_img);
        imageView.setImageResource(id);
    }

    @Override
    public View createView(LayoutInflater layoutInflater) {
        View view = layoutInflater.inflate(R.layout.view_pager_item,null) ;
        return view;
    }
}
