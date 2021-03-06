package com.wzh.multithread.interview.twothreadprint;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

/**
 * @description: 连线程交替打印比输出
 * 两个线程，第一个线程是从1到26，第二个线程是从A到一直到Z，
 * 然后要让这两个线程做到同时运行，交替输出，顺序打印
 * 结果：要求用线程顺序打印A1B2C3....Z26
 * @author: Wangzh
 * @create: 2020-07-17 16:16
 **/
public class TransferQueue_take {

    public static void main(String[] args) {
        int[] digitals=GenDataHelper.getFrom1To26Array();
        char[] letters=GenDataHelper.getFromaTozArray();

        TransferQueue transferQueue=new LinkedTransferQueue();

        new Thread(()->{
            for(int i=0;i<letters.length;i++){
                try {
                    transferQueue.transfer(letters[i]);
                    System.out.print(transferQueue.take()+" ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t1").start();

        new Thread(()->{
            for(int i=0;i<digitals.length;i++){
                try {
                    System.out.print(transferQueue.take()+" ");
                    transferQueue.transfer(digitals[i]);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t2").start();
    }
}
