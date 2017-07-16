package com.example.bitmaptest;

import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.load:
                onLoad(false);
                break;
            case R.id.exact_load:
                onLoad(true);
                break;
        }
    }

    private void onLoad(boolean exactable) {
        try {
            int bmSize = 0;
            TypedArray idArray = getResources().obtainTypedArray(
                    R.array.big_pic_array);
            TypedArray viewArray = getResources().obtainTypedArray(
                    R.array.image_view_id_array);
            for (int i = 0; i < idArray.length(); i++) {
                int picId = idArray.getResourceId(i, -1);
                int viewId = viewArray.getResourceId(i, -1);
                ImageView iv = (ImageView) findViewById(viewId);
                Bitmap bm = null;
                if (exactable) {
                    bm = BitmapUtils.decodeSampledBitmapFromResource(
                            getResources(), picId, dip2px(75), dip2px(50));

                } else {
                    bm = BitmapFactory.decodeResource(getResources(), picId);
                }
                iv.setImageBitmap(bm);
                bmSize += bm.getByteCount();
            }
            int kb = bmSize / 1024;
            int mb = kb / 1024;
            Toast.makeText(this, "bitmap size = " + mb + "MB" +  kb + "KB",
                    Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            if (exactable) {
                Toast.makeText(this, "exactable load failed", Toast.LENGTH_LONG)
                        .show();
            } else {
                Toast.makeText(this, "load failed", Toast.LENGTH_LONG).show();
            }
        }
    }

    public int dip2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
