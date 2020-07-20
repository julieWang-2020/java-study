package com.wzh.multithread.atomicsync;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @description:
 * @author: Wangzh
 * @create: 2020-07-13 10:14
 **/
public class LockSupportDemo {
    public static void main(String[] args) {
        Thread t=new Thread(()->{
            for(int i=0;i<10;i++){
                System.out.println(i);
                if(i==5) LockSupport.park();
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t.start();

        try {
            TimeUnit.SECONDS.sleep(8);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("after 8 seconds ");
        LockSupport.unpark(t);

    }
}
