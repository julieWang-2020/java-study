package com.wzh.multithread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * SynchronousQueue：手递手容量为空的Queue
 * public static ExecutorService newCachedThreadPool() {
 *         return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
 *                                       60L, TimeUnit.SECONDS,
 *                                       new SynchronousQueue<Runnable>());
 * @author wzh
 * @date 2020-07-19 21:18
 */
public class CachedThreadPool {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service= Executors.newCachedThreadPool();
        for(int i=0;i<5;i++){
            final int j=i;
            service.execute(()->{
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(j+" "+Thread.currentThread().getName());
            });
        }
        System.out.println(service);
        TimeUnit.SECONDS.sleep(80);
        System.out.println(service);

    }

}
