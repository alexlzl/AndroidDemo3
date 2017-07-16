package com.example;

import java.io.Serializable;

/**
 * Describe：
 * <p>
 * Author：LZL
 * <p>
 * Date：2016/12/19 下午1:38
 */

public class Teacher implements Serializable{
    String name;
    int age;
    Teacher(String name,int age){
        this.name=name;
        this.age=age;
    }
}
