package com.wzh.jvm.classloader;

/**
 * @author: Wangzh
 * @create: 2020-07-21 18:06
 * @description:
 **/
public class ClassReloading1 {

    public static void main(String[] args) throws ClassNotFoundException {
        MyClassLoader myClassLoader=new MyClassLoader();
        Class clazz=myClassLoader.loadClass("com.wzh.jvm.classloader.Hello");
        myClassLoader=null;
        System.out.println(clazz.hashCode());
        myClassLoader=null;

        myClassLoader=new MyClassLoader();
        Class clazz1=myClassLoader.loadClass("com.wzh.jvm.classloader.Hello");
        System.out.println(clazz1.hashCode());
        System.out.println(clazz==clazz1);
    }
}
