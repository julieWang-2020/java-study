package com.wzh.multithread.atomicsync;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @description: AtomicInteger,synchronized,LongAddr 性能比较
 * AtomicInteger: 内部实现 cas
 * synchronized:内部实现 锁升级
 * LongAddr:内部实现 分段表
 * @author: Wangzh
 * @create: 2020-07-10 14:24
 **/
public class AccumulateCompare {

    private static AtomicLong count1=new AtomicLong();
    private static long count2;
    private static LongAdder count3=new LongAdder();


    public static void main(String[] args) throws InterruptedException {
        int countSize=10000;
        int len=1000;
        int testTimes = 100000;
        for(int m=0;m<testTimes;m++){
            Thread[] threads=new Thread[len];
            for(int i=0;i<len;i++){
                threads[i]=new Thread(()->{
                    for(int j=0;j<countSize;j++){
                        count1.incrementAndGet();
                    }
                });
            }

            long start=System.currentTimeMillis();

            for(Thread t:threads){
                t.start();
            }

            for(Thread t:threads){
                t.join();
            }

            long end=System.currentTimeMillis();

            System.out.println("Atomic:"+count1.get()+" time "+(end-start));
            //---------------------------------------------------------------------

            Object lock=new Object();
            for(int i=0;i<len;i++){
                threads[i]=new Thread(()->{
                    for(int j=0;j<countSize;j++){
                        synchronized (lock){
                            count2++;
                        }
                    }
                });
            }

            long start1=System.currentTimeMillis();

            for(Thread t:threads){
                t.start();
            }

            for(Thread t:threads){
                t.join();
            }

            long end1=System.currentTimeMillis();
            System.out.println("Synchronized:"+count2+" time "+(end1-start1));
            //---------------------------------------------------------------------

            for(int i=0;i<len;i++){
                threads[i]=new Thread(()->{
                    for(int j=0;j<countSize;j++){
                        count3.increment();
                    }
                });
            }

            long start2=System.currentTimeMillis();

            for(Thread t:threads){
                t.start();
            }

            for(Thread t:threads){
                t.join();
            }

            long end2=System.currentTimeMillis();
            System.out.println("LongAddr:"+count3.longValue()+" time "+(end2-start2));

            System.out.println("------------------------------");
        }

    }


}
