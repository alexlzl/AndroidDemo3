package weeho.com.petim.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.weeho.petim.lib.activityManager.StartActivityUtil;

import weeho.com.petim.R;
import weeho.com.petim.base.BaseFragment;
import weeho.com.petim.controller.UserInfoActivity;
import weeho.com.petim.hxim.ChatActivity;
import weeho.com.petim.hxim.Constant;

/**
 * Created by wangkui on 2017/4/25.
 */

public class UserInfoFragment extends BaseFragment {



    @Override
    protected boolean isShowLeftIcon() {
        return false;
    }

    @Override
    protected View setContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.my, container,
                true);
        initView(rootView);
        return rootView;
    }

    private void initView(View rootView) {


    }

    @Override
    protected void getNetData() {

    }

    @Override
    protected void setViewListener() {

    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
//            case R.id.userinfo:
//                Intent intent = new Intent(getActivity(), UserInfoActivity.class);
////                intent.putExtra("userId", "wangku");
////                intent.putExtra("chatType", Constant.CHATTYPE_SINGLE);
//                startActivity(intent);
//                break;
//            case R.id.aboutus:
//                Toast.makeText(getActivity(),"999",Toast.LENGTH_LONG).show();
//                break;
        }
    }

    @Override
    protected boolean hasTitle() {
        return false;
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
        return null;
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
