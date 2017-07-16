package com.gyzhong.viewpagerindicator;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private ViewPager mViewPager ;
    private ViewPagerAdapter mViewPagerAdapter ;
    private List<Integer> mIdRes;
    private IndicatorView mIndicatorView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIdRes = new ArrayList<>() ;
        mIdRes.add(R.mipmap.t_01);
        mIdRes.add(R.mipmap.t_02);
        mIdRes.add(R.mipmap.t_03);
        mIdRes.add(R.mipmap.t_04);
        mViewPager = (ViewPager) findViewById(R.id.id_view_pager);
        mViewPagerAdapter = new ViewPagerAdapter(this,mIdRes) ;
        mViewPager.setAdapter(mViewPagerAdapter);
        mIndicatorView = (IndicatorView) findViewById(R.id.id_indicator) ;
        mIndicatorView.setViewPager(mViewPager);
    }

}
