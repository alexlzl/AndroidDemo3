package com.example;

public class MyClass {
    int a;
    int c= a+1;

    public static void main(String[] strings) throws CloneNotSupportedException {
        System.out.print("test");
        Person1 p = new Person1(23, "zhang");
        Person1 p1 = p;

        System.out.println(p);
        System.out.println(p1);

        Person1 p2 = new Person1(23, "zhang");
        Person1 p3 = (Person1) p2.clone();

        System.out.println("p2==="+p2);
        System.out.println("p3==="+p3);
        System.out.println(p2.equals(p3));
        System.out.println(p2.getAge()==p3.getAge());
        System.out.println(p2.getName().equals(p3.getName()));

    }
}
