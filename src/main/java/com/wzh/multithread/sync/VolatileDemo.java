package com.wzh.multithread.sync;

/**
 * @description:
 * @author: Wangzh
 * @create: 2020-07-07 17:25
 **/
public class VolatileDemo implements Runnable{

    private volatile int count=1000;
    @Override
    public void run() {
        count--;
//        System.out.println(Thread.currentThread().getName()+" count ="+count);
    }

    public static void main(String[] args) {
        VolatileDemo t = new VolatileDemo();

        for(int count=0;count<50;count++){

            t.count=1000;
            for(int i=0; i<1000; i++) {
                new Thread(t, "thread " + i).start();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(t.count);
        }
    }
}
