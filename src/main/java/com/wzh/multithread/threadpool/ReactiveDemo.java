package com.wzh.multithread.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author wzh
 * @date 2020-07-19 22:30
 */
public class ReactiveDemo {

    public static void main(String[] args) {
        List<Integer> nums=new ArrayList<>();
        Random r=new Random();
        for(int i=0;i<10000;i++) nums.add(1000000+r.nextInt(1000000));

        long start=System.currentTimeMillis();
        nums.forEach(v -> isPrime(v));
        long end=System.currentTimeMillis();
        System.out.println("lambda "+(end-start));

        start=System.currentTimeMillis();
        nums.parallelStream().forEach(ReactiveDemo::isPrime);
        end=System.currentTimeMillis();
        System.out.println("parallel stream "+(end-start));


    }


    private static boolean isPrime(int num) {
        for(int i=2;i<=num/2;i++){
            if(num % i==0) return true;
        }
        return false;
    }
}
