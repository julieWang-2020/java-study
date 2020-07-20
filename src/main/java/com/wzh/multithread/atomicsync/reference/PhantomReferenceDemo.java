package com.wzh.multithread.atomicsync.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 虚引用
 * gc 时，虚引用被回收时，会收到一个消息，消息放入队列中，通知后续处理
 * 应用场景：JVM 堆外内存回收，
 * @author wzh
 * @date 2020-07-13 20:52
 */
public class PhantomReferenceDemo {

    private static final List<Object> LIST=new LinkedList<>();
    private static final ReferenceQueue<M> QUEUE=new ReferenceQueue<>();

    public static void main(String[] args) {

        PhantomReference<M> phantomReference=new PhantomReference<>(new M(),QUEUE);

        new Thread(()->{
            while (true){
                LIST.add(new byte[1024*1024]);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
                System.out.println(phantomReference.get());
            }
        }).start();

        new Thread(()->{
            while (true){
                Reference<? extends M> poll= QUEUE.poll();
                if(poll!=null){
                    System.out.println("---- 虚引用对象jvm回收了.. "+poll);
                }
            }
        }).start();

    }
}
