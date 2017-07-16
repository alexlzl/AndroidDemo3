package weeho.com.petim.controller;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.NoCopySpan;
import android.widget.TextView;

import weeho.com.petim.R;
import weeho.com.petim.base.BaseActivity;

/**
 * Created by wangkui on 2017/4/25.
 */

public class PetInfoActivity extends FragmentActivity {

    private TextView tv_title;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.petinfo);
        initView();
//        initData();
    }

    private void initView() {
        tv_title = (TextView) findViewById(R.id.base_activity_title_titlename);
        tv_title.setText("宠物信息");
    }
}
