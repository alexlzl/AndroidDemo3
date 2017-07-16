package com.anly.samples.pref;

import com.anly.samples.R;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MemoryLeakActivity extends AppCompatActivity implements SampleListener {

    private BigObject mBigObject = new BigObject();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_leak);
        Log.d("MemoryLeakActivity","onCreat size:"+ListenerManager.getInstance().getListenerSize());
        ListenerManager.getInstance().addListener(this);
    }

    @Override
    public void doSomething() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        int size = ListenerManager.getInstance().getListenerSize();
        Log.d("MemoryLeakActivity","size:"+size);
        //TODO 修复内存泄漏方法
        //ListenerManager.getInstance().removeListener(this);
    }
}
