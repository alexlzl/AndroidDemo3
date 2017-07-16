package com.example;

/**
 * Describe：根据java虚拟机规范，所有java虚拟机实现必须在每个类或接口被java程序首次主动使用时才初始化。

 主动使用有以下6种：
 1)	创建类的实例
 2)	访问某个类或者接口的静态变量，或者对该静态变量赋值（如果访问静态编译时常量(即编译时可以确定值的常量)不会导致类的初始化）
 3)	调用类的静态方法
 4)	反射（Class.forName(xxx.xxx.xxx)）
 5)	初始化一个类的子类（相当于对父类的主动使用），不过直接通过子类引用父类元素，不会引起子类的初始化（参见示例6）
 6)	Java虚拟机被标明为启动类的类（包含main方法的）

 类与接口的初始化不同，如果一个类被初始化，则其父类或父接口也会被初始化，但如果一个接口初始化，则不会引起其父接口的初始化。
 * <p>
 * Author：LZL
 * <p>
 * Date：2017/1/20 下午4:31
 */

public class TestToy {
    public static void main(String[] args) {
         try {
         Class c = Class.forName("com.example.Toy");
         } catch (ClassNotFoundException e) {
         e.printStackTrace();
         }
//        Class c = Toy.class; // 不会输出任何值
    }
}
