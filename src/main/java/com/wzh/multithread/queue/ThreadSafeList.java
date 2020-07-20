package com.wzh.multithread.queue;

import java.util.Vector;

/**
 * @description:
 * 有N张火车票，每张票都有一个编号
 * 同时有10个窗口对外售票
 * 请写一个模拟程序
 * @author: Wangzh
 * @create: 2020-07-16 17:16
 **/
public class ThreadSafeList {

    public static final  int COUNT=1000000;
    public static final  int THREAD_COUNT=100;

    static Vector<String> vector=new Vector<>();

    static {
//        for(int i=0;i<COUNT;i++){
//            KEYS[i]=UUID.randomUUID();
//            VALUES[i]=UUID.randomUUID();
//        }
    }

    static class MyHashTableThread extends Thread{
//        int start=0;
//        int gap=COUNT/THREAD_COUNT;
//
//        public MyHashTableThread(int start){
//            this.start=start;
//        }
//
//        @Override
//        public void run() {
//            for(int i=start;i<start+gap;i++){
//                hashtable.put(KEYS[i],VALUES[i]);
//            }
//        }
    }

    public static void main(String[] args) {
        long htStart=System.currentTimeMillis();

        Thread[] htThreads=new Thread[THREAD_COUNT];
        for(int i=0;i<THREAD_COUNT;i++){
//            htThreads[i]=new MyHashTableThread(i*(COUNT/THREAD_COUNT));
        }

        for(Thread thread:htThreads){
            thread.start();
        }

        for(Thread thread:htThreads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long htEnd=System.currentTimeMillis();
//        System.out.println("HashTable put "+hashtable.size()+" items, time "+(htEnd-htStart));

    }
}
