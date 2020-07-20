package com.wzh.multithread.interview.twothreadprint;

/**
 * @description: 连线程交替打印比输出
 * 两个线程，第一个线程是从1到26，第二个线程是从A到一直到Z，
 * 然后要让这两个线程做到同时运行，交替输出，顺序打印
 * 结果：要求用线程顺序打印A1B2C3....Z26
 * @author: Wangzh
 * @create: 2020-07-17 15:05
 **/
public class Volatile_cas {
    enum ReadyToRun{
        T1,T2;
    }
    static volatile ReadyToRun r=ReadyToRun.T1;
    public static void main(String[] args) {
        int[] digitals=GenDataHelper.getFrom1To26Array();
        char[] letters=GenDataHelper.getFromaTozArray();

        new Thread(()->{
            for(int i=0;i<letters.length;i++){
                while (r==ReadyToRun.T2){}
                System.out.print(letters[i]+" ");
                r=ReadyToRun.T2;
            }
        },"t1").start();

        new Thread(()->{
            for(int i=0;i<digitals.length;i++){
                while (r==ReadyToRun.T1){}
                System.out.print(digitals[i]+" ");
                r=ReadyToRun.T1;
            }
        },"t2").start();

    }

}
