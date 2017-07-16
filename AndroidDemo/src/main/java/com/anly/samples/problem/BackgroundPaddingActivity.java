package com.anly.samples.problem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.anly.samples.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BackgroundPaddingActivity extends AppCompatActivity {

    @BindView(R.id.text_view1)
    TextView mTextView1;

    @BindView(R.id.text_view2)
    TextView mTextView2;
    @BindView(R.id.text_view3)
    TextView mTextView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_background_padding);
        ButterKnife.bind(this);

        mTextView1.setBackgroundResource(R.drawable.bg);

        mTextView2.setBackgroundResource(R.drawable.patch9_bg);

        mTextView3.setBackgroundResource(R.drawable.patch9_bg_no_padding);
    }

}
