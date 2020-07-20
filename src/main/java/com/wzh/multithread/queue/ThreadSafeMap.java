package com.wzh.multithread.queue;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @description:比较 HashTable,SynchronizedMap,HashMap性能
 * @author: Wangzh
 * @create: 2020-07-16 17:16
 **/
public class ThreadSafeMap {

    public static final  int COUNT=1000000;
    public static final  int THREAD_COUNT=100;

    static Hashtable<UUID,UUID> hashtable=new Hashtable<>();
    static Map<UUID,UUID> hashMap= Collections.synchronizedMap(new HashMap<>());
    static ConcurrentHashMap<UUID,UUID> concurrentHashMap=new ConcurrentHashMap();
    static ConcurrentSkipListMap<UUID,UUID> concurrentSkipListMap=new ConcurrentSkipListMap<>();

    static UUID[] KEYS=new UUID[COUNT];
    static UUID[] VALUES=new UUID[COUNT];

    static {
        for(int i=0;i<COUNT;i++){
            KEYS[i]=UUID.randomUUID();
            VALUES[i]=UUID.randomUUID();
        }
    }

    static class MyHashTableThread extends Thread{
        int start=0;
        int gap=COUNT/THREAD_COUNT;

        public MyHashTableThread(int start){
            this.start=start;
        }

        @Override
        public void run() {
            for(int i=start;i<start+gap;i++){
                hashtable.put(KEYS[i],VALUES[i]);
            }
        }
    }

    static class MyHashMapThread extends Thread{
        int start=0;
        int gap=COUNT/THREAD_COUNT;

        public MyHashMapThread(int start){
            this.start=start;
        }

        @Override
        public void run() {
            for(int i=start;i<start+gap;i++){
                hashMap.put(KEYS[i],VALUES[i]);
            }
        }
    }
    static class MyConcurrentHashMapThread extends Thread{
        int start=0;
        int gap=COUNT/THREAD_COUNT;

        public MyConcurrentHashMapThread(int start){
            this.start=start;
        }

        @Override
        public void run() {
            for(int i=start;i<start+gap;i++){
                concurrentHashMap.put(KEYS[i],VALUES[i]);
            }
        }
    }

    static class MyConcurrentSkipListMapThread extends Thread{
        int start=0;
        int gap=COUNT/THREAD_COUNT;

        public MyConcurrentSkipListMapThread(int start){
            this.start=start;
        }

        @Override
        public void run() {
            for(int i=start;i<start+gap;i++){
                concurrentSkipListMap.put(KEYS[i],VALUES[i]);
            }
        }
    }

    public static void main(String[] args) {
        long htStart=System.currentTimeMillis();

        Thread[] htThreads=new Thread[THREAD_COUNT];
        for(int i=0;i<THREAD_COUNT;i++){
            htThreads[i]=new MyHashTableThread(i*(COUNT/THREAD_COUNT));
        }

        for(Thread thread:htThreads){
            thread.start();
        }

        for(Thread thread:htThreads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long htEnd=System.currentTimeMillis();
        System.out.println("HashTable put "+hashtable.size()+" items, time "+(htEnd-htStart));

        // ----------------------------------------------
        long hmStart=System.currentTimeMillis();

        Thread[] hmThreads=new Thread[THREAD_COUNT];
        for(int i=0;i<THREAD_COUNT;i++){
            hmThreads[i]=new MyHashMapThread(i*(COUNT/THREAD_COUNT));
        }

        for(Thread thread:hmThreads){
            thread.start();
        }

        for(Thread thread:hmThreads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long hmEnd=System.currentTimeMillis();
        System.out.println("HashMap put "+hashMap.size()+" items, time "+(hmEnd-hmStart));

        // ----------------------------------------------
        long chmStart=System.currentTimeMillis();

        Thread[] chmThreads=new Thread[THREAD_COUNT];
        for(int i=0;i<THREAD_COUNT;i++){
            chmThreads[i]=new MyConcurrentHashMapThread(i*(COUNT/THREAD_COUNT));
        }

        for(Thread thread:chmThreads){
            thread.start();
        }

        for(Thread thread:chmThreads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long chmEnd=System.currentTimeMillis();
        System.out.println("concurrentHashMap put "+concurrentHashMap.size()+" items, time "+(chmEnd-chmStart));


        // ----------------------------------------------
        long cslmStart=System.currentTimeMillis();

        Thread[] cslmThreads=new Thread[THREAD_COUNT];
        for(int i=0;i<THREAD_COUNT;i++){
            cslmThreads[i]=new MyConcurrentSkipListMapThread(i*(COUNT/THREAD_COUNT));
        }

        for(Thread thread:cslmThreads){
            thread.start();
        }

        for(Thread thread:cslmThreads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long cslmEnd=System.currentTimeMillis();
        System.out.println("concurrentSkipListMap put "+concurrentSkipListMap.size()+" items, time "+(cslmEnd-cslmStart));
    }
}
