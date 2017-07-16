package com.example;

/**
 * Describe：
 * <p>
 * Author：LZL
 * <p>
 * Date：2016/12/19 上午10:05
 */

public class Person1 implements Cloneable {
    private int age ;
    private String name;

    public Person1(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public Person1() {}

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return (Person1)super.clone();
    }


    //    @Override
//    protected Object clone() throws CloneNotSupportedException {
//        return (Person1)super.clone();
//    }
}
