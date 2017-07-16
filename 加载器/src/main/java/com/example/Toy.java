package com.example;

/**
 * Describe：
 * <p>
 * Author：LZL
 * <p>
 * Date：2017/1/20 下午4:32
 */

public class Toy {
    static int a;

//    final  int b;

    static final  int c=100;

    static final  int d;
    static {
        d=5;
    }
    static {
        System.out.println("Initializing");// 静态子句，只在类第一次被加载并初始化时执行一次，而且只执行一次
    }

    Toy() {
        System.out.println("Building");// 构造方法，在每次声明新对象时加载
    }
}
