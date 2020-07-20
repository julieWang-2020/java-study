package com.wzh.multithread.atomicsync;

import java.util.concurrent.Phaser;

/**
 * @description:
 * @author: Wangzh
 * @create: 2020-07-10 16:33
 **/
public class PhaserSimpleDemo {

    public static void main(String[] args) {
        Phaser phaser=new Phaser(5);
        for(int i=0;i<10;i++){
            Task task=new Task(phaser);
            new Thread(task,"thread"+i).start();
        }
    }

    public  static  class Task implements Runnable{
        private final Phaser phaser;


        public Task(Phaser phaser) {
            this.phaser = phaser;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+" 执行任务完成，等待其他任务执行...");
            phaser.arriveAndAwaitAdvance();
            System.out.println(Thread.currentThread().getName()+" 继续执行任务");
        }
    }
}
