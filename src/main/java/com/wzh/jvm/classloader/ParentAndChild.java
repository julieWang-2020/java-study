package com.wzh.jvm.classloader;

/**
 * @author: Wangzh
 * @create: 2020-07-21 17:21
 * @description: 类加载器
 * 代码指定双亲委派关系
 * AppClassLoader.parent=ExtClassLoader
 * ExtClassLoader.parent=Bootstrap
 **/
public class ParentAndChild {

    public static void main(String[] args) {
        System.out.println(ParentAndChild.class.getClassLoader());
        System.out.println(ParentAndChild.class.getClassLoader().getClass().getClassLoader());

        System.out.println(ParentAndChild.class.getClassLoader().getParent());
        System.out.println(ParentAndChild.class.getClassLoader().getParent().getParent());
    }
}
