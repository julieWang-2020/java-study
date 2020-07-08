package com.wzh.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @description:
 * @author: Wangzh
 * @create: 2020-07-07 15:41
 **/
public class HowToCreateThread {

    private static class MyThread extends Thread{
        @Override
        public void run() {
            System.out.println("MyThread run");
        }
    }

    private static class MyRunnable implements Runnable{
        @Override
        public void run() {
            System.out.println("MyRunnable run");
        }
    }


    private static class MyCallable implements Callable<String> {

        @Override
        public String call() throws Exception {
            System.out.println("MyCallable run");
            return "success";
        }
    }

    public static void main(String[] args) {
        new MyThread().start();

        new Thread(new MyRunnable()).start();

        new Thread(()-> {
            System.out.println("lambda run");
        }).start();

        Thread call=new Thread(new FutureTask<String>(new MyCallable()));
        call.start();

        ExecutorService service=Executors.newCachedThreadPool();
        service.execute(()->{
            System.out.println("ExecutorService run");
        });
        service.shutdown();
    }
}
