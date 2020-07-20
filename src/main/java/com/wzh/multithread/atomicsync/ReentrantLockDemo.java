package com.wzh.multithread.atomicsync;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: Wangzh
 * @create: 2020-07-10 15:19
 **/
public class ReentrantLockDemo {

    Lock lock=new ReentrantLock();

    void m1(){
        try{
            lock.lock();
            for(int i=0;i<10;i++){
                TimeUnit.SECONDS.sleep(1);
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    void m2(){
        boolean locked=false;
        try{
            locked=lock.tryLock(5,TimeUnit.SECONDS);
            System.out.println(" m2 ... "+locked);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(locked) lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockDemo demo=new ReentrantLockDemo();
        new Thread(demo::m1).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(demo::m2).start();
    }
}
