package com.wzh.multithread.atomicsync;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: Wangzh
 * @create: 2020-07-10 15:48
 **/
public class ReentrantLockInterrupt {


    public static void main(String[] args) {
        Lock lock=new ReentrantLock();

        Thread t1=new Thread(()->{
           try {
               lock.lock();
               System.out.println(" t1 start ");
               TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
               System.out.println(" t1 end ");
           } catch (InterruptedException e) {
               System.out.println("t1 interrupted!");
           } finally {
               lock.unlock();
           }
        });

        t1.start();
        Thread t2=new Thread(()->{
            boolean locked=false;
            try {
                System.out.println(" t2 start ");
                locked=lock.tryLock(3,TimeUnit.SECONDS);
//                lock.lock();
                if(!locked) lock.lockInterruptibly();

                TimeUnit.SECONDS.sleep(5);

                System.out.println(" t2 end ");

            } catch (InterruptedException e) {
                System.out.println("t2 interrupted!");
            } finally {
                if(locked) lock.unlock();
            }
        });

        t2.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.interrupt();

    }
}
