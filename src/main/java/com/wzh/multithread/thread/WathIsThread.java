package com.wzh.multithread.thread;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: Wangzh
 * @create: 2020-07-07 15:37
 **/
public class WathIsThread {

    public static void main(String[] args) {
        new T1().start();
        for(int i=0;i<10;i++){
            try {
                TimeUnit.MICROSECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("main");
        }
    }

    private static class T1 extends Thread{
        @Override
        public void run() {
            for(int i=0;i<10;i++){
                try {
                    TimeUnit.MICROSECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("T1");
            }
        }
    }
}
