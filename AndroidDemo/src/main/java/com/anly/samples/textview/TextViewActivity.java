package com.anly.samples.textview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.widget.TextView;

import com.anly.samples.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TextViewActivity extends AppCompatActivity {

    @BindView(R.id.text1)
    TextView mText1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view);
        ButterKnife.bind(this);

        initViews();
    }

    private void initViews() {
        String source = "Note: If you are using the new Jack compiler with version 2.2.0 or newer you do not need the 'android-apt' plugin and can instead replace apt with annotationProcessor when declaring the compiler dependency.";
        SpannableString spannableString = new SpannableString(source);
        mText1.setText(spannableString);
    }
}
