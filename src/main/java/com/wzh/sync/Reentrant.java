package com.wzh.sync;

import java.util.concurrent.TimeUnit;

/**
 * synchronized 可重入
 * 一个同步方法可以调用另外一个同步方法，一个线程已经拥有某个对象的锁，
 * 再次申请的时候仍然会得到该对象的锁
 * @author wzh
 * @date 2020-07-07 21:09
 */
public class Reentrant {

    private synchronized void m1(){
        System.out.println("m1 start");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        m2();
        System.out.println("m1 end ");
    }


    private synchronized void m2(){
        System.out.println("m2 start");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("m2 end ");
    }

    public static void main(String[] args) {
        new Reentrant().m1();
    }
}
