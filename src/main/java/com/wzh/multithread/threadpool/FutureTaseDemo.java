package com.wzh.multithread.threadpool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: Wangzh
 * @create: 2020-07-17 16:41
 **/
public class FutureTaseDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> task=new FutureTask<Integer>(()->{
            TimeUnit.MILLISECONDS.sleep(500);
            return 100;
        });

        new Thread(task).start();
        System.out.println(task.get());
    }
}
