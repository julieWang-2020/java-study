package com.wzh.jvm.classloader;

/**
 * @author: Wangzh
 * @create: 2020-07-21 18:01
 * @description: 执行方式
 * -Xmixed 混合模式，默认
 * -Xint 解释模式
 * -Xcomp 编译模式
 **/
public class WayToRun {
    public static void main(String[] args) {
        for(int i=0; i<10_0000; i++)
            m();

        long start = System.currentTimeMillis();
        for(int i=0; i<10_0000; i++) {
            m();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public static void m() {
        for(long i=0; i<10_0000L; i++) {
            long j = i%3;
        }
    }
}
