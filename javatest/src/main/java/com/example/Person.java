package com.example;

/**
 * Describe：
 * <p>
 * Author：LZL
 * <p>
 * Date：2016/12/19 上午11:11
 */

public class Person implements Cloneable {
    int age;
    /* *
     *String 类型特殊，因为他为引用型，而且他指向的值为常量，克隆出来的对象改变他的值
     *实际上是改变了克隆出来对象String类型成员的指向，不会影响被克隆对象的值及其指向。
    */
    String name;

    Friend f;

    public Person(int age, String name, Friend f) {
        this.age = age;
        this.name = name;
        this.f = f;
    }

    //组合对象的克隆
    public Object clone () throws CloneNotSupportedException {
        Person p = (Person)super.clone(); //需要对每一个成员对象实现clone()
        p.f = (Friend)p.f.clone();
        return p;
    }

    public String toString(){
        StringBuffer sb = new StringBuffer();
        return super.toString()+sb.append("  age=").append(age).
                append(",name=").append(name).
                append("  friend=").append("f.name=").
                append(f.name).append("f.age=").append(f.age).toString();
    }
}
