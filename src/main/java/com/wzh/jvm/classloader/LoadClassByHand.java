package com.wzh.jvm.classloader;

/**
 * @author: Wangzh
 * @create: 2020-07-21 17:39
 * @description: 手动加载
 **/
public class LoadClassByHand {

    public static void main(String[] args) throws ClassNotFoundException {
        Class clazz = LoadClassByHand.class.getClassLoader().loadClass("com.wzh.jvm.classloader.ClassLoaderLevel");
        System.out.println(clazz.getName());
        System.out.println(clazz.getClassLoader());
    }
}
