package com.wzh.multithread.threadpool;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: Wangzh
 * @create: 2020-07-17 16:45
 **/
public class CompletableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start=System.currentTimeMillis();

        CompletableFuture<Double> futureTM=CompletableFuture.supplyAsync(()->priceTM());
        CompletableFuture<Double> futureTB=CompletableFuture.supplyAsync(()->priceTB());
        CompletableFuture<Double> futureJD=CompletableFuture.supplyAsync(()->priceJD());

        CompletableFuture.allOf(futureTM,futureTB,futureJD).join();
//        CompletableFuture.supplyAsync(()->priceTM())
//                .thenApply(String::valueOf)
//                .thenApply(str ->"TM price "+str)
//                .thenAccept(System.out::println);
        double tmP=futureTM.get();
        double tbP=futureTM.get();
        double jdP=futureJD.get();
        System.out.println("TM price "+tmP);
        System.out.println("TB price "+tbP);
        System.out.println("JD price "+jdP);
        long end = System.currentTimeMillis();
        System.out.println("use completable future! "+(end-start));
//        try {
//            System.in.read();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }



    public static double priceTM(){
        delay();
        return 100;
    }


    public static double priceTB(){
        delay();
        return 80;
    }


    public static double priceJD(){
        delay();
        return 110;
    }

    private static void delay() {
        int time=new Random().nextInt(500);
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("After %s sleep!\n",time);

    }

}
