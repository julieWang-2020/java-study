package com.wzh.sync;

import java.util.concurrent.TimeUnit;

/**
 * @author wzh
 * @date 2020-07-07 21:19
 */
public class Parent {

    public synchronized void m(){
        System.out.println("parent m start");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("parent m end");
    }

    public static void main(String[] args) {
        new Child().m();
    }
}

class  Child extends Parent{

    @Override
    public synchronized void m() {
        System.out.println("child m start");
        super.m();
        System.out.println("child m end");
    }
}
