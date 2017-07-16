package com.example;

/**
 * Describe：
 * <p>
 * Author：LZL
 * <p>
 * Date：2017/1/19 下午1:47
 */
public class TestS {
    private static TestS ourInstance = new TestS();

    public static TestS getInstance() {
        return ourInstance;
    }

    private TestS() {
    }
}
