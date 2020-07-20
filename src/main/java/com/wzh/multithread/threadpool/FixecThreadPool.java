package com.wzh.multithread.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 *public static ExecutorService newFixedThreadPool(int nThreads) {
 *         return new ThreadPoolExecutor(nThreads, nThreads,
 *                                       0L, TimeUnit.MILLISECONDS,
 *                                       new LinkedBlockingQueue<Runnable>());
 * @author wzh
 * @date 2020-07-19 21:18
 */
public class FixecThreadPool {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long start=System.currentTimeMillis();
        getPrime(1,200000);
        long end=System.currentTimeMillis();
        System.out.println("main "+(end-start));

        final int cpuCoreNum=4;
        ExecutorService service= Executors.newFixedThreadPool(cpuCoreNum);

        MyTask t1=new MyTask(0,80000);
        MyTask t2=new MyTask(80001,130000);
        MyTask t3=new MyTask(130001,170000);
        MyTask t4=new MyTask(170001,200000);

        Future<List<Integer>> f1=service.submit(t1);
        Future<List<Integer>> f2=service.submit(t2);
        Future<List<Integer>> f3=service.submit(t3);
        Future<List<Integer>> f4=service.submit(t4);

        start=System.currentTimeMillis();
        f1.get();
        f2.get();
        f3.get();
        f4.get();

        end=System.currentTimeMillis();
        System.out.println("fixedThreadPool "+(end-start));

        service.shutdown();
    }

    private static List getPrime(int start, int end) {
        List<Integer> result=new ArrayList<>();
        for(int i=start;i<end;i++){
            if(isPrime(i)) result.add(i);
        }
        return result;
    }

    private static boolean isPrime(int num) {
        for(int i=2;i<=num/2;i++){
            if(num % i==0) return true;
        }
        return false;
    }

    static class MyTask implements Callable<List<Integer>>{

        int start,end;

        public MyTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public List<Integer> call() throws Exception {
            List<Integer> r=getPrime(start,end);
            return r;
        }
    }

}
