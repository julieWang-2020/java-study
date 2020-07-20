package com.wzh.multithread.interview.twothreadprint;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @description: 连线程交替打印比输出
 * 两个线程，第一个线程是从1到26，第二个线程是从A到一直到Z，
 * 然后要让这两个线程做到同时运行，交替输出，顺序打印
 * 结果：要求用线程顺序打印A1B2C3....Z26
 * @author: Wangzh
 * @create: 2020-07-17 15:05
 **/
public class BlockingQueue_take_put {

    public static void main(String[] args) {
        int[] digitals=GenDataHelper.getFrom1To26Array();
        char[] letters=GenDataHelper.getFromaTozArray();
        BlockingQueue<String> q1=new ArrayBlockingQueue<>(1);
        BlockingQueue<String> q2=new ArrayBlockingQueue<>(1);
        new Thread(()->{
            for(int i=0;i<letters.length;i++){
                System.out.print(letters[i]+" ");
                try {
                    q2.put("ok");
                    q1.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t1").start();

        new Thread(()->{
            for(int i=0;i<digitals.length;i++){
                try {
                    q2.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print(digitals[i]+" ");
                try {
                    q1.put("ok");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t2").start();

    }

}
