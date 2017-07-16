package com.example;

/**
 * Describe：所谓享元模式，就是共用会经常被创建销毁的对象，这样可以节省比较琐碎的创建对象的开支。在Java中，Integer中从-2^7到2^7-1都是以享元形式实现的。所以，所有的-128到127之间的Integer，都是可以用==来判断相等的，因为相等的必定是指向同一个对象的引用。而如果是大于127的Integer，那就不能用==来判断了，这时候没有享元可用了。

 至于Integer为什么要把-2^7到2^7-1以享元的方式来实现呢，这肯定是考虑到值比较小的对象是会比较经常的创建与销毁的。Integer是一个类，所以通过这种方式，在值比较小的时候去进行运算或之类的，不需要频繁的创建对象，确实是有好处的，但这就导致了同样的==操作符上，在不同大小的Integer上会有不一致的返回。

 Integer是这样的，然而int的表现是不一样的，至于为什么不一样，猜测可能跟基本类型的存储有关系，暂时不知道了，留待学习。因为在Java中没办法打印对象的内存地址，也没必要打印对象的内存地址，所以在处理这些问题的时候就挺麻烦的，不知道到底是什么原因。

 感觉Java有很多奇怪的设定，除了这里的享元设定，比较让我困惑的是ArrayList的default size是10，甚至不是一个二进制数字。按道理来讲，定为8或者16，无论是缓存命中还是平均拷贝次数，应该都是会有比10更好的表现。可能有我没有考虑到的东西吧，之后再继续看看。
 * <p>
 * Author：LZL
 * <p>
 * Date：2017/1/22 下午5:28
 */

public class TestInterfaceTest {
    public static void main(String[] args) {
        Integer testCase1_1 = -129;
        Integer testCase1_2 = -129;
        System.out.println(testCase1_1 == testCase1_2); // false

        Integer testCase2_1 = -128;
        Integer testCase2_2 = -128;
        System.out.println(testCase2_1 == testCase2_2); // true

        Integer testCase3_1 = 127;
        Integer testCase3_2 = 127;
        System.out.println(testCase3_1 == testCase3_2); // true

        Integer testCase4_1 = 128;
        Integer testCase4_2 = 128;
        System.out.println(testCase4_1 == testCase4_2); // false

        int testCase5_1 = 128;
        int testCase5_2 = 128;
        System.out.println(testCase5_1 == testCase5_2); // true
    }
}
