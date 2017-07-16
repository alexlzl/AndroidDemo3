package com.anly.samples.textview;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.anly.samples.R;
import com.jakewharton.rxbinding.view.RxView;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;

public class TextInputLayoutActivity extends AppCompatActivity {

    @BindView(R.id.username_input)
    EditText mUsernameInput;
    @BindView(R.id.username_input_layout)
    TextInputLayout mUsernameInputLayout;
    @BindView(R.id.password_input)
    TextInputEditText mPasswordInput;
    @BindView(R.id.password_input_layout)
    TextInputLayout mPasswordInputLayout;
    @BindView(R.id.check_btn)
    Button mCheckBtn;
    @BindView(R.id.error_btn)
    Button mErrorBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_input_layout);
        ButterKnife.bind(this);

        initViews();
    }

    private void initViews() {
        RxView.clicks(mErrorBtn)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        mUsernameInput.setError("User name could be wrong");
                    }
                });
    }
}
