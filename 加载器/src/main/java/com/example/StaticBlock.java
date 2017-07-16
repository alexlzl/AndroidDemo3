package com.example;

/**
 * Describe：
 * <p>
 * Author：LZL
 * <p>
 * Date：2017/1/20 下午6:07
 */

public class StaticBlock {
//    static final int c = 3;
//
//    static final int d;
//
//    static int e = 5;
    static {
//        System.out.println("c==="+c);
        d = 5;
        e = 10;
        System.out.println("static===Initializing");
    }

    {
        System.out.println("Initializing");

    }
    static final int c = 3;

    static final int d;

    static int e = 5;
    StaticBlock() {
        System.out.println("Building");
    }
}
