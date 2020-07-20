package com.wzh.multithread.atomicsync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: Wangzh
 * @create: 2020-07-10 16:13
 **/
public class FairLock extends Thread{

    static Lock lock=new ReentrantLock(true);

    @Override
    public void run() {
        for(int i=0;i<100;i++){
            try{
                lock.lock();
                System.out.println(Thread.currentThread().getName()+" 获得锁");
            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        FairLock fairLock=new FairLock();
        Thread t1=new Thread(fairLock);
        Thread t2=new Thread(fairLock);
        t1.start();
        t2.start();
    }
}
