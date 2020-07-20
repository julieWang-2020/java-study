package com.wzh.multithread.atomicsync;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @description: 信号灯，限流使用
 * @author: Wangzh
 * @create: 2020-07-10 17:27
 **/
public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore=new Semaphore(1,true);

        new Thread(()->{
            try {
                semaphore.acquire();
                System.out.println("T1 running ...");
                TimeUnit.SECONDS.sleep(2);
                System.out.println("T1 running ...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphore.release();
            }

        }).start();


        new Thread(()->{
            try {
                semaphore.acquire();
                System.out.println("T2 running ...");
                TimeUnit.SECONDS.sleep(2);
                System.out.println("T2 running ...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphore.release();
            }

        }).start();
    }
}
