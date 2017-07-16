package com.anly.samples;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by mingjun on 16/10/31.
 */

public class SampleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        LeakCanary.install(this);
    }
}
