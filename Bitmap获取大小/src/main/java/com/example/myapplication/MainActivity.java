package com.example.myapplication;
/**
 * 其实，通过前面的代码跟踪，我们就不难知道，Bitmap 在内存当中占用的大小其实取决于：

 色彩格式，前面我们已经提到，如果是 ARGB8888 那么就是一个像素4个字节，如果是 RGB565 那就是2个字节
 原始文件存放的资源目录（是 hdpi 还是 xxhdpi 可不能傻傻分不清楚哈）
 目标屏幕的密度（所以同等条件下，红米在资源方面消耗的内存肯定是要小于三星S6的）

 https://dev.qq.com/topic/591d61f56793d26660901b4e
 */

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    /**
     * 今天做图像缓存需要计算Bitmap的所占的内存空间，于是研究了下Bitmap关于内存占用的API



     1、getRowBytes：Since API Level 1，用于计算位图每一行所占用的内存字节数。



     2、getByteCount：Since API Level 12，用于计算位图所占用的内存字节数。



     经实测发现：getByteCount() = getRowBytes() * getHeight()，也就是说位图所占用的内存空间数等于位图的每一行所占用的空间数乘以位图的行数。



     因为getByteCount要求的API版本较高，因此对于使用较低版本的开发者，在计算位图所占空间时上面的方法或许有帮助。
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         ImageView img = (ImageView) findViewById(R.id.test);
        Drawable drawable = img.getDrawable();
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitDrawable = (BitmapDrawable) drawable;
            Bitmap bit = bitDrawable.getBitmap();
            int rowBytes = bit.getRowBytes();
            int height = bit.getHeight();
            long memSize = rowBytes * height;
            Log.d("ANDROID_LAB", "memSize =" + memSize );//drawable目录70560000字节 drawable-xxxhdpi4412800字节

            Log.d("ANDROID_LAB", "getbytecount =" + bit.getByteCount() );
        }
    }
}
