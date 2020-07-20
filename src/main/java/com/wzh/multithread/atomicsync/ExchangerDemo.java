package com.wzh.multithread.atomicsync;

import java.util.concurrent.Exchanger;

/**
 * @description: 两线程交换数据
 * @author: Wangzh
 * @create: 2020-07-10 17:31
 **/
public class ExchangerDemo {

    static Exchanger<String> exchanger=new Exchanger<>();

    public static void main(String[] args) {
        new Thread(()->{
            String s="T1";
            try {
                s=exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + s);

        },"T1").start();


        new Thread(()->{
            String s="T2";
            try {
                s=exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + s);


        },"T2").start();
    }
}
