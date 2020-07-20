package com.wzh.multithread.atomicsync.reference;

import java.lang.ref.SoftReference;
import java.util.concurrent.TimeUnit;

/**
 * @description: 软引用
 * 内存不够的时候，gc 会回收软引用
 * 使用场景：可做缓存使用
 * -Xms20M -Xmx20M
 * @author: Wangzh
 * @create: 2020-07-13 17:55
 **/
public class SoftReferenceDemo {

    public static void main(String[] args) {
        // 1024*1024*10 10M
        SoftReference<byte[]> m=new SoftReference<>(new byte[1024*1024*10]);
        System.out.println(m.get());
        System.gc();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(m.get());

        byte[] b=new byte[1024*1024*15];
        System.out.println(m.get());
    }
}
