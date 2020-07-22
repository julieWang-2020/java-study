package com.wzh.jvm.jmm;

/**
 * @author wzh
 * @date 2020-07-21 22:19
 * @description: 填充缓存行
 */
public class CacheLinePadding_02 {

    private static class Padding{
        public volatile long p1,p2,p3,p4,p5,p6,p7;
    }

    private static class T extends Padding{
        public volatile long x=0L;
    }

    public static T[] arr=new T[2];

    static {
        arr[0]=new T();
        arr[1]=new T();
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(()->{
           for(long i=0;i<1000_0000L;i++){
               arr[0].x=i;
           }
        });

        Thread t2=new Thread(()->{
            for(long i=0;i<1000_0000L;i++){
                arr[1].x=i;
            }
        });

        long start=System.nanoTime();
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println((System.nanoTime()-start)/1000_0000);


    }

}
