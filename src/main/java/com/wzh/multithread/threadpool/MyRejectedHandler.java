package com.wzh.multithread.threadpool;

import java.util.concurrent.*;

/**
 * @author wzh
 * @date 2020-07-19 21:45
 */
public class MyRejectedHandler {

    public static void main(String[] args) {
        ExecutorService service=new ThreadPoolExecutor(4,4,
                0, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(6),
                Executors.defaultThreadFactory(),
                new MyHandler());

        for(int i=0;i<18;i++){
            service.execute(new HelloThreadPool.Task(i));
        }

    }



    static class MyHandler implements RejectedExecutionHandler{

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println("开始拒绝处理 "+r);
            // try 3 times
            boolean flag=true;
            for(int i=0;i<3;i++){
                if(executor.getQueue().size()<6){
                    System.out.println("再试一次");
                    flag=flag;
                    break;
                }
            }
            if(flag){
                System.out.println("记录拒绝任务 "+r);
            }

        }
    }
}
