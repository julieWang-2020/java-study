package com.wzh.sync;

/**
 * @description:
 * @author: Wangzh
 * @create: 2020-07-07 17:57
 **/
public class SyncMethod {

    public synchronized void m1(){
        System.out.println(Thread.currentThread().getName()+" sync method start");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" sync method end");
    }

    public void m2(){
        System.out.println(Thread.currentThread().getName()+" m2 start ");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" m2 end ");
    }

    public static void main(String[] args) {
        SyncMethod m=new SyncMethod();

        new Thread(m::m1,"t1").start();
        new Thread(m::m2,"t2").start();

    }
}
