package com.damon.vectordrawabledemo;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
/**
 *
 * Describe: http://www.jianshu.com/p/d6c39f2dd5e7
 *
 * Author: lzl
 *
 * Time: 2017/1/16 下午3:02
 *
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = (ImageView) findViewById(R.id.image_view);
        Drawable drawable = imageView.getDrawable();
        //AnimatedVectorDrawableCompat实现了Animatable接口
        if (drawable instanceof Animatable){
            ((Animatable) drawable).start();
        }
    }
}
