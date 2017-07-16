package com.example;

/**
 * Describe：
 * <p>
 * Author：LZL
 * <p>
 * Date：2017/1/22 上午11:46
 */

public class TestFinalizeGC {
    public static TestFinalizeGC obj = null;

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed");
        //完成自救
        TestFinalizeGC.obj = this;
    }

    public static void main(String[] args) {
        obj = new TestFinalizeGC();
        obj = null;
        //自救
        System.gc();
        String s0 = "kvill";
        String s1 = "kvill";
        String s2 = "kv" + "ill";
        System.out.println(s0 == s1);
        System.out.println(s0 == s2);
        test1();
        test2();
        test3();
        test4();
    }

    public static void test1() {
        System.out.println("==============");
        String s0 = "kvill";
        String s1 = new String("kvill");
        String s2 = "kv" + new String("ill");
        System.out.println(s0 == s1);
        System.out.println(s0 == s2);
        System.out.println(s1 == s2);
    }

    /**
     * Describe: 。String的intern()方法就是扩充常量池的一个方法；当一个String实例str调用intern()方法时，Java查找常量池中是否有相同Unicode的字符串常量，如果有，则返回其的引用，如果没有，则在常量池中增加一个Unicode等于str的字符串并返回它的引用；看例3就清楚了
     * <p>
     * Author: lzl
     * <p>
     * Time: 2017/1/22 下午3:53
     */
    public static void test2() {
        String s0 = "kvill";
        String s1 = new String("kvill");
        String s2 = new String("kvill");
        System.out.println(s0 == s1);
        System.out.println("**********");
        s1.intern();
        s2 = s2.intern(); //把常量池中“kvill”的引用赋给s2
        System.out.println(s0 == s1);
        System.out.println(s0 == s1.intern());
        System.out.println(s0 == s2);
        /**
         * 　结果为：

         false
         **********
         false //虽然执行了s1.intern(),但它的返回值没有赋给s1
         true //说明s1.intern()返回的是常量池中”kvill”的引用
         true
         */
    }

    public static void test3() {
        String s1 = new String("kvill");
        String s2 = s1.intern();
        System.out.println(s1 == s1.intern());
        System.out.println(s1 + " " + s2);
        System.out.println(s2 == s1.intern());
    }

    public static void test4() {
        System.out.println("test4=============");
        String s1 = "Monday";
        String s2 = new String("Monday");
        if (s1 == s2) {
            System.out.println("s1 == s2");
        } else {
            System.out.println("s1 != s2");
        }
        if (s1.equals(s2)) {
            System.out.println("s1 equals s2");
        } else {
            System.out.println("s1 not equals s2");
        }
    }

}
