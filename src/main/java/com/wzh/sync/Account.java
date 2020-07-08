package com.wzh.sync;

import java.util.concurrent.TimeUnit;

/**
 * 模拟银行账户，
 * 对业务写方法加锁
 * 对业务读方法不加锁
 * @author wzh
 * @date 2020-07-07 20:49
 */
public class Account {

    String name;
    double balance;

    public synchronized void set(String name,double balance){
        this.name=name;

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.balance=balance;
    }

    public synchronized double getBalance(String name){
        return this.balance;
    }


    public static void main(String[] args) {
        Account a=new Account();
        new Thread(()->a.set("zhangsan",100)).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(a.getBalance("zhangsan"));
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(a.getBalance("zhangsan"));

    }
}
