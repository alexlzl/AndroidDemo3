package com.anly.samples.pref;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Created by mingjun on 16/10/31.
 */

public class BigObject {

    private ArrayList<Bitmap> bitmaps = new ArrayList<>();
    private String[] values = new String[1000];

    public BigObject() {

        for (int i = 0; i < 20; i++) {
            bitmaps.add(Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888));
        }

        for (int i = 0; i < 1000; i++) {
            values[i] = "value:" + i;
        }
    }
}
