package com.wzh.multithread.threadpool;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author wzh
 * @date 2020-07-19 22:11
 */
public class WorkStrealingPool {

    public static void main(String[] args) throws IOException {
        ExecutorService service= Executors.newWorkStealingPool();
        System.out.println(Runtime.getRuntime().availableProcessors());

        service.execute(new R(1000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(2000));

        System.out.println(Runtime.getRuntime().availableProcessors());

        System.in.read();
    }

    static class R implements Runnable{

        int time;

        public R(int time) {
            this.time = time;
        }

        @Override
        public void run() {
            try {
                TimeUnit.MILLISECONDS.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(time+" "+Thread.currentThread().getName());
        }
    }
}
