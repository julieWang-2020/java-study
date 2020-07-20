package com.wzh.multithread.thread;

/**
 * @description:
 * @author: Wangzh
 * @create: 2020-07-07 17:01
 **/
public class ThreadState {

    private static class MyThread extends Thread{
        @Override
        public void run() {
            System.out.println(this.getState());
            for(int i=0;i<10;i++){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                System.out.println("T1 "+i+" = "+this.getState());
            }
        }
    }

    public static void main(String[] args) {
        MyThread t=new MyThread();

        System.out.println(t.getState());

        t.start();

        try {
            t.join();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        //然后join之后，结束了是一个Timenated状态
        System.out.println(t.getState());
    }
}
