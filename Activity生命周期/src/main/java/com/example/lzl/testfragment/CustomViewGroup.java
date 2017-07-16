package com.example.lzl.testfragment;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by liuzhouliang on 2017/6/13.
 */

public class CustomViewGroup extends RelativeLayout {

    public CustomViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("TAG", "CustomViewGroup===dispatchTouchEvent");
        if(ev.getAction()==MotionEvent.ACTION_MOVE){
            Log.e("TAG", "CustomViewGroup=dispatchTouchEvent===ACTION_MOVE");
        }else if(ev.getAction()==MotionEvent.ACTION_UP){
            Log.e("TAG", "CustomViewGroup=dispatchTouchEvent===ACTION_UP");
        }else if(ev.getAction()==MotionEvent.ACTION_DOWN){
            Log.e("TAG", "CustomViewGroup=dispatchTouchEvent===ACTION_DOWN");
        }
        return super.dispatchTouchEvent(ev);
//        return false;
//        return true;
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e("TAG", "CustomViewGroup===onInterceptTouchEvent");
        if(ev.getAction()==MotionEvent.ACTION_MOVE){
            Log.e("TAG", "CustomViewGroup===ACTION_MOVE");
        }else if(ev.getAction()==MotionEvent.ACTION_UP){
            Log.e("TAG", "CustomViewGroup===ACTION_UP");
        }else if(ev.getAction()==MotionEvent.ACTION_DOWN){
            Log.e("TAG", "CustomViewGroup===ACTION_DOWN");
        }
        return super.onInterceptTouchEvent(ev);
//        return true;
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("TAG", "CustomViewGroup===onTouchEvent");
//        return super.onTouchEvent(event);
        return false;
    }

    //    @Override
//    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//
//    }
}
