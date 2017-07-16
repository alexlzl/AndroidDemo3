package com.example.lzl.testfragment;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Created by liuzhouliang on 2017/6/13.
 */

public class CustomTextview extends android.support.v7.widget.AppCompatTextView {
    public CustomTextview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.e("TAG", "CustomTextview===dispatchTouchEvent");
//        return super.dispatchTouchEvent(event);
        return false;
    }
}
