package com.example;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Describe：
 * <p>
 * Author：LZL
 * <p>
 * Date：2017/1/20 下午3:52
 */

public class MyClassLoader extends  ClassLoader {
    private String rootPath;

    public MyClassLoader(String rootPath){
        this.rootPath = rootPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //check if the class have been loaded
        Class<?> c = findLoadedClass(name);
        if(c!=null){
            return c;
        }
        //load the class
        byte[] classData = getClassData(name);
        if(classData==null){
            throw new ClassNotFoundException();
        }
        else{
            c = defineClass(name,classData, 0, classData.length);
            return c;
        }
    }

    private byte[] getClassData(String className){
        String path = rootPath+"/"+className.replace('.', '/')+".class";

        InputStream is = null;
        ByteArrayOutputStream bos = null;
        try {
            is = new FileInputStream(path);
            bos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int temp = 0;
            while((temp = is.read(buffer))!=-1){
                bos.write(buffer,0,temp);
            }
            return bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                is.close();
                bos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
