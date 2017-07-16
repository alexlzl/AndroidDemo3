package com.example;

/**
 * Describe：引用计数法
 算法思想：为对象添加一个引用计数器，每当有一个地方引用该对象时，则该引用计数器值加1,；当引用失效时，则该引用计数器值减1；最后，计数器为0的对象就是不可能再被使用的，也即所谓的“死去”的对象。
 Java中并没有使用这种算法进行GC，最主要的原因是很难解决对象之间的相互循环引用的问题。如下代码：
 * <p>
 * Author：LZL
 * <p>
 * Date：2017/1/22 上午11:24
 */

public class TestReferenceCountingGC {

    public Object obj = null;

    public static void main(String[] strings){
        testGc();
    }

    public static void testGc(){
        TestReferenceCountingGC obj1 = new TestReferenceCountingGC();
        TestReferenceCountingGC obj2 = new TestReferenceCountingGC();
        obj1.obj = obj2;
        obj2.obj = obj1;
        obj1 = null;
        obj2 = null;

        System.gc();

    }
}
