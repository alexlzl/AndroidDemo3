package com.example;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Describe：
 * <p>
 * Author：LZL
 * <p>
 * Date：2016/12/19 下午1:38
 */

public class 深度克隆流 implements Serializable{
    String name;//常量对象
    int age;
    Teacher t;//学生1和学生2的引用值都是一样的。
    深度克隆流(String name, int age, Teacher t){
        this.name=name;
        this.age=age;
        this.t=t;
    }
    public <T> Object   deepClone(T t1) throws IOException,
            ClassNotFoundException {//将对象写到流里
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oo = new ObjectOutputStream(bo);
        oo.writeObject(t1);//从流里读出来
        ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
        ObjectInputStream oi = new ObjectInputStream(bi);
        return (oi.readObject());
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Teacher t=new Teacher("tangliang",30);
        深度克隆流 s1=new 深度克隆流("zhangsan",18,t);
        深度克隆流 s2=(深度克隆流)s1.deepClone(s1);
        s2.t.name="tony";
        s2.t.age=40;
        System.out.println("name="+s1.t.name+","+"age="+s1.t.age);//学生1的老师不改变
    }
}
