package com.wzh.multithread.jmh;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @description:
 * @author: Wangzh
 * @create: 2020-07-21 14:09
 **/
public class ParallelStreamDemo {

    static List<Integer> nums = new ArrayList<>();
    static {
        Random r = new Random();
        for (int i = 0; i < 10000; i++) nums.add(1000000 + r.nextInt(1000000));
    }

    static void foreach() {
        nums.forEach(v->isPrime(v));
    }

    static void parallel() {
        nums.parallelStream().forEach(ParallelStreamDemo::isPrime);
    }

    static boolean isPrime(int num) {
        for(int i=2; i<=num/2; i++) {
            if(num % i == 0) return false;
        }
        return true;
    }

}
