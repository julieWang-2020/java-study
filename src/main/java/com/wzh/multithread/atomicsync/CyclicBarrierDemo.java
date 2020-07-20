package com.wzh.multithread.atomicsync;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @description: 循环栅栏
 * @author: Wangzh
 * @create: 2020-07-10 16:29
 **/
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier barrier=new CyclicBarrier(20,()->{
            System.out.println("20 满 ");
        });

        for(int i=0;i<100;i++){
            new Thread(()->{
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
