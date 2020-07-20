package com.wzh.multithread.atomicsync;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @description:
 * @author: Wangzh
 * @create: 2020-07-10 17:16
 **/
public class ReadWriteLockDemo {

    private static Lock lock=new ReentrantLock();
    private static int value;
    private static ReentrantReadWriteLock readWriteLock=new ReentrantReadWriteLock();
    private static Lock readLock=readWriteLock.readLock();
    private static Lock writeLock=readWriteLock.writeLock();

    public static void  read(Lock lock){
        try {
            lock.lock();
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getName()+" read over! ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public static void  wirte(Lock lock,int v){
        try {
            lock.lock();
            TimeUnit.SECONDS.sleep(1);
            value=v;
            System.out.println(Thread.currentThread().getName()+" write over! ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Runnable readR1=()->read(lock);
        Runnable readR2=()->read(readLock);

        Runnable write1=()->wirte(lock,new Random().nextInt());
        Runnable write2=()->wirte(writeLock,new Random().nextInt());

        for(int i=0;i<18;i++) new Thread(readR1,"lock"+i).start();
        for(int i=0;i<2;i++) new Thread(write1,"lock"+i).start();


//        for(int i=0;i<18;i++) new Thread(readR2,"readLockThread"+i).start();
//        for(int i=0;i<2;i++) new Thread(write2,"writeLockThread"+i).start();
    }
}
