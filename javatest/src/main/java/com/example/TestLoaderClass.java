package com.example;

/**
 * Describe：
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
            if(loader!=null){
                System.out.println(loader.toString());
            }else{
                System.out.println("loader==null");
            }

        }
    }
}
