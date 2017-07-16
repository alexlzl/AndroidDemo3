package com.yanzhenjie.defineview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.btn_scroll_method).setOnClickListener(mOnClickListener);
        findViewById(R.id.btn_scroll_layout).setOnClickListener(mOnClickListener);
        findViewById(R.id.btn_scroll_pager).setOnClickListener(mOnClickListener);
    }

    private View.OnClickListener mOnClickListener = v -> {
        int id = v.getId();
        switch (id) {
            case R.id.btn_scroll_method: {
                startActivity(new Intent(this, ScrollMethodActivity.class));
                break;
            }
            case R.id.btn_scroll_layout: {
                startActivity(new Intent(this, ScrollLayoutActivity.class));
                break;
            }
            case R.id.btn_scroll_pager: {
                startActivity(new Intent(this, ScrollPagerActivity.class));
                break;
            }
        }
    };
}
