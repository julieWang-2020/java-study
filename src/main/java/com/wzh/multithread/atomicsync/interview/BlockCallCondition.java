package com.wzh.multithread.atomicsync.interview;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: 淘宝面试题
 * 写一个固定容量同步容器，拥有 put 和 get 方法，以及getCount 方法
 * 能够支持 2个生产者线程以及10给消费者线程的阻塞调用
 * @author: Wangzh
 * @create: 2020-07-13 11:23
 **/
public class BlockCallCondition<T> {

    private final int MAX=10;
    private final LinkedList<T> list=new LinkedList<T>();
    private int count=0;

    private Lock lock=new ReentrantLock();
    private Condition producer=lock.newCondition();
    private Condition customer=lock.newCondition();

    public void put (T t){
        try{
            lock.lock();
            while (list.size()==MAX){
                producer.await();
            }
            list.add(t);
            ++count;
            // 叫醒等待的所有线程
            customer.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public synchronized T get(){
        T t=null;
        try{
            lock.lock();
            while (count==0){
                customer.await();
            }
            t=list.removeFirst();
            count--;
            // 叫醒等待的所有线程
            producer.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return t;
    }

    public int getCount(){
        return count;
    }

    public static void main(String[] args) {
        BlockCallCondition<String> call=new BlockCallCondition();

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

