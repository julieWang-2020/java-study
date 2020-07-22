package com.wzh.jvm.classloader;

/**
 * @author wzh
 * @date 2020-07-21 22:00
 * java classLoad 加载顺序
 */
public class ClassLoaderProcedure {

    public static void main(String[] args) {
        /**
         * 1、count 在上是 3，
         *      当调用 T 先把 T Classload 进内存进行Verification；
         *      再进行 Preparation 赋默认值，这时候 count=0;继续进行 Resolution。
         *      再进行 initialization 初始化 为变量赋值，count=2; 最后调用 count++
         *
         * 2、count 在下是 2，
         *      当调用 T 先把 T Classload 进内存进行Verification；
         *      再进行 Preparation 赋默认值，这时候 count=0;继续进行 Resolution。
         *      再进行 initialization 初始化，调用 New T,count++;在调用 count=2;
         */
        System.out.println(T.count);
    }
}
class T{
    public static T t=new T();
    public static int count=2;
    public T(){
        count++;
    }

}
