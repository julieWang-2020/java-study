package com.wzh.multithread.threadpool;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: Wangzh
 * @create: 2020-07-17 17:40
 **/
public class HelloThreadPool {

    static class Task implements Runnable{
        int i;
        public Task(int i){
            this.i=i;
        }
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+" Task"+i);
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return "Task{" +
                    "i=" + i +
                    '}';
        }
    }

    public static void main(String[] args) {
        ThreadPoolExecutor executor=new ThreadPoolExecutor(2,4,60,
                TimeUnit.SECONDS,new ArrayBlockingQueue<>(4)
                , Executors.defaultThreadFactory()
                ,new ThreadPoolExecutor.CallerRunsPolicy());

        for(int i=0;i<8;i++){
            executor.execute(new Task(i));
        }

        System.out.println(executor.getQueue());
        executor.execute(new Task(100));
        System.out.println(executor.getQueue());
        executor.shutdown();
    }
}
