package com.anly.samples.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.anly.samples.R;
import com.anly.samples.base.TraceActivity;

public class BActivity extends TraceActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        getSupportActionBar().setTitle("B-Activity");

        findViewById(R.id.btn_a).setOnClickListener(this);
        findViewById(R.id.btn_c).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_a:
                startActivity(new Intent(BActivity.this, AActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP));
                break;

            case R.id.btn_c:
                startActivity(new Intent(BActivity.this, CActivity.class));
                break;

            default:
                break;
        }
    }
}
