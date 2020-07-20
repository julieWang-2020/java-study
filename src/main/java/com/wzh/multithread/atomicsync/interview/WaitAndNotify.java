package com.wzh.multithread.atomicsync.interview;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @description: 面试题：
 * 实现一个容器，提供两个方法，add,size
 * 写两个线程，线程1添加10个元素到容器中，
 * 线程2 实现监控元素个数，当个数到5个时，线程2 给出提示并结束
 * @author: Wangzh
 * @create: 2020-07-13 10:25
 **/
public class WaitAndNotify {

    List list=new ArrayList<>();
//    volatile List list= Collections.synchronizedList(new LinkedList<>());

    public void add(Object o){
        list.add(o);
    }
    public int size(){
        return list.size();
    }

    public static void main(String[] args) {
        WaitAndNotify w=new WaitAndNotify();
        final Object lock=new Object();

        new Thread(()->{
            synchronized (lock){
                System.out.println("t2 开始 ");
                if(w.size()!=5){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t2 结束 ");
                lock.notify();
            }
        },"t2").start();


        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            synchronized (lock){
                System.out.println("t1 开始 ");
                for(int i=0;i<10;i++){
                    w.add(new Object());
                    System.out.println("add "+i);
                    if(w.size()==5){
                        lock.notify();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                System.out.println("t1 结束");
            }
        },"t1").start();

    }

}
