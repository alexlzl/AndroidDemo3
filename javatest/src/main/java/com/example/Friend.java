package com.example;

/**
 * Describe：
 * <p>
 * Author：LZL
 * <p>
 * Date：2016/12/19 上午11:10
 */

public class Friend implements Cloneable {

    int age;
    String name;//           StringBuffer name;
    public Friend(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public Object clone () throws CloneNotSupportedException {
        return super.clone();
    }
}
