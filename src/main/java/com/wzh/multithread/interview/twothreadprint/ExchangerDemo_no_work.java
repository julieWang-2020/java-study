package com.wzh.multithread.interview.twothreadprint;

import java.util.concurrent.Exchanger;

/**
 * @description: 连线程交替打印比输出
 * 两个线程，第一个线程是从1到26，第二个线程是从A到一直到Z，
 * 然后要让这两个线程做到同时运行，交替输出，顺序打印
 * 结果：要求用线程顺序打印A1B2C3....Z26
 *
 * 注意：解决不了问题，不能保证交替执行
 * a 1 2 b c 3 4 d e 5 6 f g 7 8 h i 9 10 j k 11 l 12 13 m n 14 15 o p 16 17 q r 18 19 s t 20 21 u v 22 23 w x 24 25 y z 26
 *
 * @author: Wangzh
 * @create: 2020-07-17 16:10
 **/
public class ExchangerDemo_no_work {

    public static void main(String[] args) {
        int[] digitals=GenDataHelper.getFrom1To26Array();
        char[] letters=GenDataHelper.getFromaTozArray();
        Exchanger<String> exchanger=new Exchanger<>();

        new Thread(()->{
            for(int i=0;i<digitals.length;i++){
                try {
                    String str=exchanger.exchange(digitals[i]+"");
                    System.out.print(str+" ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        },"t1").start();

        new Thread(()->{
            for(int i=0;i<letters.length;i++){
                try {
                    String str=exchanger.exchange(letters[i]+"");
                    System.out.print(str+" ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        },"t2").start();
    }
}
