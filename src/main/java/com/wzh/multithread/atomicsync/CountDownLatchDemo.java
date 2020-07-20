package com.wzh.multithread.atomicsync;

import java.util.concurrent.CountDownLatch;

/**
 * @description: 计数器
 * @author: Wangzh
 * @create: 2020-07-10 16:17
 **/
public class CountDownLatchDemo {

    public static void main(String[] args) {
        usingCountDownLatch();
        usingJoin();
    }


    private static void usingCountDownLatch() {
        Thread[] threads=new Thread[100];
        CountDownLatch latch=new CountDownLatch(threads.length);
        for(int i=0;i<threads.length;i++){
            threads[i]=new Thread(()->{
                int result=0;
                for(int j=0;j<10000;j++) result+=j;
                latch.countDown();
            });
        }

        for(Thread t:threads) t.start();

        try {
            // 当前线程等待，直到计数器为0
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(" end latch ");
    }


    private static void usingJoin() {
        Thread[] threads=new Thread[100];
        for(int i=0;i<threads.length;i++){
            threads[i]=new Thread(()->{
                int result=0;
                for(int j=0;j<10000;j++) result+=j;
            });
        }

        for(Thread t:threads) t.start();

        for(Thread t:threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(" end join ");

    }
}
