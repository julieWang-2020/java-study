package com.wzh.jvm.classloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author: Wangzh
 * @create: 2020-07-21 18:06
 * @description:
 * 打破双亲委派
 * 重写 LoadClass
 **/
public class ClassReloading2 {

    private static class MyLoader extends ClassLoader{
        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException {
            File f = new File("E:\\workspace\\gitspace\\java-study\\target\\classes\\", name.replace(".", "/").concat(".class"));

            if(!f.exists()) return super.loadClass(name);

            try {

                InputStream is = new FileInputStream(f);

                byte[] b = new byte[is.available()];
                is.read(b);
                return defineClass(name, b, 0, b.length);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return super.loadClass(name);
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        MyLoader myLoader=new MyLoader();
        Class clazz=myLoader.loadClass("com.wzh.jvm.classloader.Hello");

        myLoader=new MyLoader();
        Class clazzNew=myLoader.loadClass("com.wzh.jvm.classloader.Hello");
        System.out.println(clazz==clazzNew);
    }
}
