package com.wzh.multithread.atomicsync.interview;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * @description: 淘宝面试题
 * 写一个固定容量同步容器，拥有 put 和 get 方法，以及getCount 方法
 * 能够支持 2个生产者线程以及10给消费者线程的阻塞调用
 * @author: Wangzh
 * @create: 2020-07-13 11:23
 **/
public class BlockCallSynchronized<T> {

    private final int MAX=10;
    private final LinkedList<T> list=new LinkedList<T>();
    private int count=0;

    public synchronized void put (T t){
        while (list.size()==MAX){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        list.add(t);
        ++count;
        // 叫醒等待的所有线程
        this.notifyAll();
    }

    public synchronized T get(){
        while (count==0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        T t=list.removeFirst();
        count--;
        this.notifyAll();
        return t;
    }

    public int getCount(){
        return count;
    }

    public static void main(String[] args) {
        BlockCallSynchronized<String> call=new BlockCallSynchronized();

        for(int i=0;i<10;i++){
            new Thread(()->{
                for(int j=0;j<20;j++){
                    System.out.println(call.get());
                }
            },"c"+i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(int i=0;i<2;i++){
            new Thread(()->{
                for(int j=0;j<100;j++){
                    call.put(Thread.currentThread().getName()+" "+j);
                }
            },"p"+i).start();
        }
    }
}

