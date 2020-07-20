package com.wzh.multithread.queue;

import java.io.IOException;
import java.util.PriorityQueue;
import java.util.concurrent.*;

/**
 * @author wzh
 * @date 2020-07-16 23:00
 */
public class QueueDemo {
    static BlockingQueue<MyTask> queue=new DelayQueue<>();

    static class MyTask implements Delayed{
        String name;
        long runningTime;

        public MyTask(String name, long runningTime) {
            this.name = name;
            this.runningTime = runningTime;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(runningTime -System.currentTimeMillis(),TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            if(this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS)) return 1;
            if(this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS)) return -1;
            return 0;
        }

        @Override
        public String toString() {
            return name+" "+runningTime;
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        long now=System.currentTimeMillis();
        MyTask t1=new MyTask("t1",now+1000);
        MyTask t2=new MyTask("t2",now+2000);
        MyTask t3=new MyTask("t3",now+1500);
        MyTask t4=new MyTask("t4",now+3000);
        MyTask t5=new MyTask("t5",now+500);

        queue.add(t1);
        queue.add(t2);
        queue.add(t3);
        queue.add(t4);
        queue.add(t5);

        for(int i=0,l= queue.size();i<l;i++){
            try {
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        System.out.println("-----------------------------------");
        PriorityQueue<String> priorityQueue=new PriorityQueue();
        priorityQueue.add("w");
        priorityQueue.add("d");
        priorityQueue.add("b");
        priorityQueue.add("c");
        priorityQueue.add("a");
        for(int i=0,l= priorityQueue.size();i<l;i++){
            System.out.println(priorityQueue.poll());
        }


        System.out.println("-----------------------------------");
        BlockingQueue<String> synchronousQueue=new SynchronousQueue();
        new Thread(()->{
            try {
                System.out.println(synchronousQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"sq1").start();
        new Thread(()->{
            try {
                System.out.println(synchronousQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"sq2").start();

        synchronousQueue.put("test SynchronousQueue");
        synchronousQueue.put("test SynchronousQueue1");
        System.out.println("synchronousQueue.size: "+synchronousQueue.size());


        System.out.println("-----------------------------------");
        LinkedTransferQueue<String> transferQueue=new LinkedTransferQueue<>();
        new Thread(()->{

            try {
                System.out.println(transferQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"tq1").start();
//        new Thread(()->{
//            try {
//                System.out.println(transferQueue.take());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        },"tq2").start();
        transferQueue.transfer("test transferQueue1");
        transferQueue.transfer("test transferQueue2");
        transferQueue.transfer("test transferQueue3");

        System.in.read();


    }
}
