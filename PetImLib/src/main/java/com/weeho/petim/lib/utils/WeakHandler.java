package com.weeho.petim.lib.utils;

import android.os.Handler;

import java.lang.ref.WeakReference;

/**
 * 自定义Handler，防止内存泄露
 */
public class WeakHandler<T> extends Handler {

    private WeakReference<T> mWeakReference;

    public WeakHandler(T reference) {
        super();
        mWeakReference = new WeakReference<T>(reference);
    }

    public T getReference() {
        return mWeakReference.get();
    }
    
}
