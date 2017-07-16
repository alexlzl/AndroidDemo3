package com.example;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Describe：总结
 * <p>
 * 1、基本数据类型能自动实现深度clone。
 * 2、String是一个例外。
 * 但对于我们编程来说可以和操作基本数据类型一样做，基本没影响。大大方便了我们的编程。
 * <p>
 * String类型的变量clone后的表现好象也实现了深度clone，但其实只是一个假象。
 * 因为执行 p1.name = "new";语句时，它作用相当于生成了一个新的string类型，然后又赋回给p1.name。
 * 这是因为string被sun公司的工程师写成了一个不可更改的类（immutable class），在所有string类中的函数都不能更改自身的值。
 * <p>
 * ==> 这告诉我们支持更方便实现克隆的一种途径：将自己定义的类编写为不可更改。
 * <p>
 * 3、StringBuffer需要做特殊处理
 * String和StringBuffer有区别。
 * 可以借鉴类似技巧对StringBuffer型的变量实现克隆效果：sb=new StringBuffer(sb.toString());
 * <p>
 * Author：LZL
 * <p>
 * Date：2016/12/19 上午11:12
 */

public class 克隆 {
    public static void main(String[] args) throws CloneNotSupportedException {
        Person p = new Person(4, "num1", new Friend(5, "haha"));//new StringBuffer("haha")));
        Person p1 = (Person) p.clone();
        p1.name = "new";       //看似类似于基本类型，其实暗藏玄机
        p1.age = 10;
        p1.f.name = "hehe";
        //p1.f.name = new StringBuffer("hehe");//新产生了一个引用，覆给了它
        p1.f.age = 56;
        System.out.println(p);//+"——  age"+p.age +" name "+p.name );
        System.out.println(p1);//+"——  age"+p1.age +" name "+p1.name );

        ArrayList testList = new ArrayList();
        testList.add(p);
        testList.add(p1);

        System.out.println("befor testList:" + testList);

        //ArrayList的克隆
        ArrayList newList = (ArrayList) testList.clone();
        //要一一走访ArrayList/HashMap所指的每一个对象，并逐一克隆。
        for (int i = 0; i < newList.size(); i++)
            newList.set(i, ((Person) newList.get(i)).clone());
        for (Iterator e = newList.iterator(); e.hasNext(); )
            ((Person) e.next()).age += 1;

        System.out.println("after: testList" + testList);
        System.out.println("after: newList" + newList);

    }
}
