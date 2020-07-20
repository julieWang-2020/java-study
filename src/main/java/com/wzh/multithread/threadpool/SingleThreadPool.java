package com.wzh.multithread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 为什么会有单线程线程池：
 *  1、有任务队列
 *  2、线程池提供线程生命周期管理
 * 单线程线程池用途: 保证任务顺序执行
 * public static ExecutorService newSingleThreadExecutor() {
 *         return new FinalizableDelegatedExecutorService
 *             (new ThreadPoolExecutor(1, 1,
 *                                     0L, TimeUnit.MILLISECONDS,
 *                                     new LinkedBlockingQueue<Runnable>()));
 * @author wzh
 * @date 2020-07-19 21:18
 */
public class SingleThreadPool {

    public static void main(String[] args) {
        ExecutorService service= Executors.newSingleThreadExecutor();
        for(int i=0;i<5;i++){
            final int j=i;
            service.execute(()->{
                System.out.println(j+" "+Thread.currentThread().getName());
            });
        }
        service.shutdown();
    }

}
