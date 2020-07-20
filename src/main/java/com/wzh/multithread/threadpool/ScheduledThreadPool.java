package com.wzh.multithread.threadpool;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * SynchronousQueue：手递手容量为空的Queue
 * public ScheduledThreadPoolExecutor(int corePoolSize) {
 *         super(corePoolSize, Integer.MAX_VALUE, 0, NANOSECONDS,
 *               new DelayedWorkQueue());
 *     }
 * @author wzh
 * @date 2020-07-19 21:18
 */
public class ScheduledThreadPool {

    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService service= Executors.newScheduledThreadPool(4);
        service.scheduleAtFixedRate(()->{
            try {
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        },0,500,TimeUnit.MILLISECONDS);

    }

}
