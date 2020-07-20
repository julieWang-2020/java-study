package com.wzh.multithread.atomicsync.reference;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author wzh
 * -verbose:gc -Xmx20M -Xms20M -Xmn10M -XX:SoftRefLRUPolicyMSPerMB=0 -XX:+PrintGCDetails -XX:SurvivorRatio=8
 * @date 2020-07-13 21:20
 */
public class WeakHashMapDemo implements Runnable{
    WeakHashMap<String,byte[]> map=new WeakHashMap();
    @Override
    public void run() {
        try {
            map.put(Thread.currentThread().getName(),new byte[1024*1024*3]);
            TimeUnit.SECONDS.sleep(3);
            if(map.get(Thread.currentThread().getName())!=null){
                System.out.println("a====1111");
            }else {
                System.out.println("a====null");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        for(int i=0;i<5;i++){
            WeakHashMapDemo demo=new WeakHashMapDemo();
            new Thread(demo).start();
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        byte[] c=new byte[1024*1024*2];
        System.out.println("--c--");

    }
}
