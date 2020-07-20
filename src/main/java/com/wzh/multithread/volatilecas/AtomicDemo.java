package com.wzh.multithread.volatilecas;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author: Wangzh
 * @create: 2020-07-08 14:51
 **/
public class AtomicDemo {

    AtomicInteger count=new AtomicInteger();

    public void m(){
//        System.out.println("exec "+Thread.currentThread().getName());
        for(int i=0;i<1000;i++){
            count.incrementAndGet();
        }
    }


    public static void main(String[] args) {
        AtomicDemo d=new AtomicDemo();
        List<Thread> threads=new ArrayList<>();
        for(int i=0;i<10000;i++){
            threads.add(new Thread(d::m,"thread-"+i));
        }

        threads.forEach(o->o.start());

        // 此处加入 join ，意在等待threads 全部执行完成再输出 count
        threads.forEach(o->{
            try {
//                System.out.println("==="+o.getName());
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(d.count);
    }
}
