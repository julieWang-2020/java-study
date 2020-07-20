package com.wzh.multithread.interview.twothreadprint;

import java.util.concurrent.locks.LockSupport;

/**
 * @description: 连线程交替打印比输出
 * 两个线程，第一个线程是从1到26，第二个线程是从A到一直到Z，
 * 然后要让这两个线程做到同时运行，交替输出，顺序打印
 * 结果：要求用线程顺序打印A1B2C3....Z26
 * @author: Wangzh
 * @create: 2020-07-17 15:44
 **/
public class LockSupport_park_unpark {
    static Thread t1=null,t2=null;
    public static void main(String[] args) {
        int[] digitals=GenDataHelper.getFrom1To26Array();
        char[] letters=GenDataHelper.getFromaTozArray();



        t1=new Thread(()->{
            for(int i=0;i<letters.length;i++){
                System.out.print(letters[i]+" ");
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        },"t1");

        t2=new Thread(()->{
            for(int i=0;i<digitals.length;i++){
                LockSupport.park();
                System.out.print(digitals[i]+" ");
                LockSupport.unpark(t1);
            }
        },"t2");

        t1.start();
        t2.start();
    }
}
