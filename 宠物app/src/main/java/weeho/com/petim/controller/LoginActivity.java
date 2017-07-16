package weeho.com.petim.controller;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.weeho.petim.lib.utils.StringUtil;

import weeho.com.petim.R;
import weeho.com.petim.base.BaseActivity;

/**
 * Created by wangkui on 2017/4/25.
 */

public class LoginActivity extends BaseActivity {

    private Button bt_login;
    private EditText et_code;

    @Override
    protected void onCreate() {

    }

    private void initView() {
        et_code = (EditText) findViewById(R.id.et_code);
        bt_login = (Button) findViewById(R.id.bt_login);
    }

    @Override
    protected boolean hasTitle() {
        return true;
    }

    @Override
    protected void loadChildView() {
        bt_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.bt_login:
                String code = et_code.getText().toString().trim();
                if(StringUtil.isEmpty(code)) {
//                    Toas
                    return;
                }

                break;
        }
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
        return "宠拜";
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
