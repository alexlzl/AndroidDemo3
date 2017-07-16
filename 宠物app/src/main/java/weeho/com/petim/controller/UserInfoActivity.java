package weeho.com.petim.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.weeho.petim.lib.activityManager.StartActivityUtil;

import weeho.com.petim.R;
import weeho.com.petim.base.BaseActivity;

/**
 * Created by wangkui on 2017/4/25.
 */

public class UserInfoActivity extends BaseActivity implements View.OnClickListener{

    private RelativeLayout nick_relative;
    private RelativeLayout sex_relative;
    private RelativeLayout wechat_relative;
    private RelativeLayout phone_relative;
    private RelativeLayout area_relative;
    private TextView tv_area;
    private TextView tv_phone;
    private TextView tv_wechat;
    private TextView tv_sex;
    private TextView tv_nick;
    private String titleName = "";
    private String nickName;
    private String sexName;
    private String phoneName;
    private String areaName;
    private TextView tv_title;
    private ImageView ivbaxk;
    private TextView tv_title_left;

    @Override
    protected void onCreate() {
        setBaseContentView(R.layout.userinfo);
    }

    @Override
    protected boolean hasTitle() {
        return false;
    }

    @Override
    protected void loadChildView() {

    }

    @Override
    protected void getNetData() {

    }

    @Override
    protected void reloadCallback() {

    }

    @Override
    protected void setChildViewListener() {

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

//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.userinfo);
//        initView();
//        initData();
//    }


    private void initView() {
        tv_title = (TextView) findViewById(R.id.base_activity_title_titlename);
        ivbaxk = (ImageView) findViewById(R.id.base_activity_title_backicon);
        tv_title_left = (TextView) findViewById(R.id.base_activity_title_left);
        nick_relative = (RelativeLayout) findViewById(R.id.nick_relative);
        sex_relative = (RelativeLayout) findViewById(R.id.sex_relative);
        wechat_relative = (RelativeLayout) findViewById(R.id.wechat_relative);
        phone_relative = (RelativeLayout) findViewById(R.id.phone_relative);
        area_relative = (RelativeLayout) findViewById(R.id.area_relative);


        tv_area = (TextView) findViewById(R.id.tv_area);
        tv_phone = (TextView) findViewById(R.id.tv_phone);
        tv_wechat = (TextView) findViewById(R.id.tv_wechat);
        tv_sex = (TextView) findViewById(R.id.tv_sex);
        tv_nick = (TextView) findViewById(R.id.tv_nick);
        tv_title.setText("个人信息");
        ivbaxk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        setLeftText("个人信息");
//        ShowLetfText();
    }
//    @Override
//    protected void setChildViewListener() {
//        nick_relative.setOnClickListener(this);
//        sex_relative.setOnClickListener(this);
//        wechat_relative.setOnClickListener(this);
//        phone_relative.setOnClickListener(this);
//        area_relative.setOnClickListener(this);
//    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.nick_relative:
                nickName = tv_nick.getText().toString().trim();
                titleName = "昵称";
                intent.putExtra("name", nickName);
                break;
            case R.id.sex_relative:
                sexName = tv_sex.getText().toString().trim();
                titleName = "性别";
                intent.putExtra("name", sexName);
                break;
            case R.id.phone_relative:
                phoneName = tv_sex.getText().toString().trim();
                titleName = "性别";
                intent.putExtra("name", phoneName);
                break;
            case R.id.area_relative:
                areaName = tv_sex.getText().toString().trim();
                titleName = "地区";
                intent.putExtra("name", phoneName);
                break;
        }
        intent.putExtra("title", titleName);
        StartActivityUtil.startActivity(this,UpdateUserInfoActivity.class);
    }

    private void initData() {

    }
}
