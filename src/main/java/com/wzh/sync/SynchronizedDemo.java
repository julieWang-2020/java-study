package com.wzh.sync;

/**
 * @description:
 * @author: Wangzh
 * @create: 2020-07-07 17:05
 **/
public class SynchronizedDemo {

    private int count=10;
    private Object o=new Object();

    private static int sum=10;

    private volatile int len=10;

    public void m1(){
        synchronized (o){
            count--;
            System.out.println(Thread.currentThread().getName()+" count ="+count);
        }
    }

    /**
     * synchronized(this) yu  synchronized 方法等值
     */
    public void m2(){
        synchronized (this){
            count--;
            System.out.println(Thread.currentThread().getName()+" count ="+count);
        }
    }

    public synchronized void m3(){
        count--;
        System.out.println(Thread.currentThread().getName()+" count ="+count);
    }


    public static void m4(){
        synchronized (SynchronizedDemo.class){
            sum--;
            System.out.println(Thread.currentThread().getName()+" sum ="+sum);
        }
    }

    public void m5(){
        len--;
        System.out.println(Thread.currentThread().getName()+" len ="+len);
    }

    public static void main(String[] args) {
        SynchronizedDemo demo=new SynchronizedDemo();
//        for(int i=0;i<10;i++){
//            new Thread(()->{
//                demo.m1();
//            },"m1 thread "+i).start();
//        }
//        demo.count=10;
//        for(int i=0;i<10;i++){
//            new Thread(()->{
//                demo.m2();
//            },"m2 thread "+i).start();
//        }
//        demo.count=10;
//        for(int i=0;i<10;i++){
//            new Thread(()->{
//                demo.m3();
//            },"m3 thread "+i).start();
//        }
//
//        for(int i=0;i<10;i++){
//            new Thread(()->{
//                m4();
//            },"m4 thread "+i).start();
//        }
        demo.len=1000;
        for(int i=0;i<1000;i++){
            new Thread(()->{
                demo.m5();
            },"m5 thread "+i).start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(demo.len);
    }
}
