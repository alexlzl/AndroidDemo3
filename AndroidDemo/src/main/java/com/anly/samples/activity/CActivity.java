package com.anly.samples.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.anly.samples.R;
import com.anly.samples.base.TraceActivity;

public class CActivity extends TraceActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);
        getSupportActionBar().setTitle("C-Activity");

        findViewById(R.id.btn_a).setOnClickListener(this);
        findViewById(R.id.btn_b).setOnClickListener(this);
        findViewById(R.id.btn_c).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_a:
                startActivity(new Intent(CActivity.this, AActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                break;

            case R.id.btn_b:
                startActivity(new Intent(CActivity.this, BActivity.class));
                break;

            case R.id.btn_c:
                startActivity(new Intent(CActivity.this, CActivity.class));
                break;

            default:
                break;
        }
    }
}
