package com.anly.samples.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.anly.samples.R;
import com.anly.samples.base.TraceActivity;

public class AActivity extends TraceActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        getSupportActionBar().setTitle("A-Activity");

        findViewById(R.id.btn_a).setOnClickListener(this);
        findViewById(R.id.btn_b).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_a:
                startActivity(new Intent(AActivity.this, AActivity.class));
                break;

            case R.id.btn_b:
                startActivity(new Intent(AActivity.this, BActivity.class));
                break;

            default:
                break;
        }
    }
}
