package com.anly.samples.pref.leak;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.anly.samples.R;
import com.anly.samples.pref.BigObject;

import java.lang.ref.WeakReference;

public class HandlerLeakActivity extends AppCompatActivity {

    private BigObject mBigObject = new BigObject();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_leak);

        new DemoHandler(this).sendEmptyMessageDelayed(1, 60 * 1000);

    }

    private static class DemoHandler extends Handler {

        private final WeakReference<HandlerLeakActivity> mActivity;


        private DemoHandler(HandlerLeakActivity activity) {
            this.mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            HandlerLeakActivity activity = mActivity.get();
            if (activity != null) {
                activity.doSomething();
            }
        }
    }

    private void doSomething() {

    }
}
