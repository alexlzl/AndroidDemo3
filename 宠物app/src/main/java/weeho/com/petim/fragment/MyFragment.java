package weeho.com.petim.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.weeho.petim.lib.activityManager.StartActivityUtil;

import weeho.com.petim.R;
import weeho.com.petim.base.BaseFragment;
import weeho.com.petim.controller.PetInfoActivity;
import weeho.com.petim.controller.UserInfoActivity;

/**
 * Created by wangkui on 2017/4/20.
 */

public class MyFragment extends BaseFragment {

    private LinearLayout userinfo;
    private LinearLayout petinfo;
    private LinearLayout petex;
    private LinearLayout aboutus;
    private Context mContext;

    @Override
    protected boolean isShowLeftIcon() {
        return false;
    }

    @Override
    protected View setContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.my, container,
                true);
        initView(rootView);
        initData();
        return rootView;
    }

    private void initData() {

    }

    private void initView(View rootView) {
        mContext = getActivity();
        userinfo = (LinearLayout) rootView.findViewById(R.id.userinfo);
        petinfo = (LinearLayout) rootView.findViewById(R.id.petinfo);
        petex = (LinearLayout) rootView.findViewById(R.id.petex);
        aboutus = (LinearLayout) rootView.findViewById(R.id.aboutus);


    }

    @Override
    protected void getNetData() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.userinfo:
                StartActivityUtil.startActivity((Activity) mContext, UserInfoActivity.class);
                break;
            case R.id.petinfo:
                StartActivityUtil.startActivity((Activity) mContext, PetInfoActivity.class);
                break;
        }
    }

    @Override
    protected void setViewListener() {
        userinfo.setOnClickListener(this);
        petinfo.setOnClickListener(this);
        petex.setOnClickListener(this);
        aboutus.setOnClickListener(this);
    }

    @Override
    protected boolean hasTitle() {
        return true;
    }

    @Override
    protected boolean hasTitleIcon() {
        return false;
    }

    @Override
    protected boolean hasDownIcon() {
        return false;
    }

    @Override
    protected void reloadCallback() {

    }

    @Override
    protected String setTitleName() {
        return "我";
    }

    @Override
    protected String setRightText() {
        return null;
    }

    @Override
    protected int setLeftImageResource() {
        return 0;
    }

    @Override
    protected int setMiddleImageResource() {
        return 0;
    }

    @Override
    protected int setRightImageResource() {
        return 0;
    }
}
