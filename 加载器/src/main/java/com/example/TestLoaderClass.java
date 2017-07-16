package com.example;

/**
 * Describe：2.JVM中类加载器的树状层次结构
 * Java 中的类加载器大致可以分成两类，一类是系统提供的，另外一类则是由 Java 应用开发人员编写的。
 * <p>
 * 引导类加载器（bootstrap class loader）：
 * <p>
 * 它用来加载 Java 的核心库(jre/lib/rt.jar)，是用原生C++代码来实现的，并不继承自java.lang.ClassLoader。
 * <p>
 * 加载扩展类和应用程序类加载器，并指定他们的父类加载器，在java中获取不到。
 * <p>
 * 扩展类加载器（extensions class loader）：
 * <p>
 * 它用来加载 Java 的扩展库(jre/ext/*.jar)。Java 虚拟机的实现会提供一个扩展库目录。该类加载器在此目录里面查找并加载 Java 类。
 * <p>
 * 系统类加载器（system class loader）：
 * <p>
 * 它根据 Java 应用的类路径（CLASSPATH）来加载 Java 类。一般来说，Java 应用的类都是由它来完成加载的。可以通过 ClassLoader.getSystemClassLoader()来获取它。
 * <p>
 * 自定义类加载器（custom class loader）：
 * <p>
 * 除了系统提供的类加载器以外，开发人员可以通过继承 java.lang.ClassLoader类的方式实现自己的类加载器，以满足一些特殊的需求。
 * <p>
 * <p>
 * <p>
 * 类加载器的加载顺序有两种，一种是父类优先策略，一种是是自己优先策略，父类优先策略是比较一般的情况（如JDK采用的就是这种方式），在这种策略下，类在加载某个Java类之前，会尝试代理给其父类加载器，只有当父类加载器找不到时，才尝试自己去加载。自己优先的策略与父类优先相反，它会首先尝试子经济加载，找不到的时候才要父类加载器去加载，这种在web容器（如tomcat）中比较常见。
 * <p>
 * Author：LZL
 * <p>
 * Date：2017/1/20 下午2:47
 */

public class TestLoaderClass {

    public static void main(String[] args) {
        ClassLoader loader = TestLoaderClass.class.getClassLoader();
        while (loader != null) {
            System.out.println(loader.toString());
            loader = loader.getParent();
            if (loader != null) {
                System.out.println(loader.toString());
            } else {
                System.out.println("loader==null");
            }

        }
        test();
    }

    public void testl() throws Exception {
        MyClassLoader loader = new MyClassLoader("D:");
        Class<?> c = loader.loadClass("com.example.HelloWorld");
        System.out.println(c.getClassLoader());
    }

    public static void test() {
        //application class loader
        System.out.println(ClassLoader.getSystemClassLoader());
        //extensions class loader
        System.out.println(ClassLoader.getSystemClassLoader().getParent());
        //bootstrap class loader
        System.out.println(ClassLoader.getSystemClassLoader().getParent().getParent());
    }
}
