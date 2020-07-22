package com.wzh.jvm.classloader;

/**
 * @author: Wangzh
 * @create: 2020-07-21 17:49
 * @description: 懒初始化问题
 **/
public class LazyLoading {

    public static void main(String[] args) throws ClassNotFoundException {
//        P p;

        // 初始化子类必先初始化父类
//        X x=new X();
        // final 值不会初始化类
//        System.out.println(P.i);
        // 非final 值不会初始化类
        System.out.println(P.j);

//        Class.forName("com.wzh.jvm.classloader.LazyLoading$P");
    }

    public static class P {
        final static int i=8;
        static int j=9;
        static {
            System.out.println("P");
        }
    }

    public static class X extends P{
        static {
            System.out.println("X");
        }
    }
}
