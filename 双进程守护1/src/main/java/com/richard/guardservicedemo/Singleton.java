package com.richard.guardservicedemo;

/**
 * Describe：
 * <p>
 * Author：LZL
 * <p>
 * Date：2017/2/19 下午8:17
 */
public class Singleton {
    private static Singleton ourInstance = new Singleton();

    public static Singleton getInstance() {
        return ourInstance;
    }

    private Singleton() {
    }
}
