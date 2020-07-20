package com.wzh.multithread.atomicsync;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: Wangzh
 * @create: 2020-07-10 16:33
 **/
public class PhaserDemo {

    static MarriagePhaser phaser=new MarriagePhaser();
    static Random r = new Random();
    public static void main(String[] args) {
        phaser.bulkRegister(7);
        for(int i=0;i<5;i++){
            new Thread(new Person("p"+i),"thread"+i).start();
        }
        new Thread(new Person("新郎")).start();
        new Thread(new Person("新娘")).start();
    }

    static void milliSleep(int milli) { try {
        TimeUnit.MILLISECONDS.sleep(milli); } catch (InterruptedException e) { e.printStackTrace(); }
    }
    public  static  class Person implements Runnable{
        private final String name;


        public Person(String name) {
            this.name = name;
        }

        public void arrive(){
            milliSleep(r.nextInt(1000));
            System.out.printf("%s 到达现场 ",name);
            phaser.arriveAndAwaitAdvance();
        }

        public void eat(){
            milliSleep(r.nextInt(1000));
            System.out.printf("%s 吃完 ",name);
            phaser.arriveAndAwaitAdvance();
        }

        public void leave(){
            milliSleep(r.nextInt(1000));
            System.out.printf("%s 离开 ",name);
            phaser.arriveAndAwaitAdvance();
        }

        public void hug(){
            if("新郎".equals(name)|| "新娘".equals(name)){
                milliSleep(r.nextInt(1000));
                System.out.printf("%s 洞房 ",name);
                phaser.arriveAndAwaitAdvance();
            }else {
                phaser.arriveAndDeregister();
            }

        }

        @Override
        public void run() {
            arrive();
            eat();
            leave();
            hug();
        }
    }

    public static class MarriagePhaser extends Phaser{
        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            switch (phase){
                case 0:
                    System.out.println("所有人都到齐了！"+registeredParties);
                    return false;
                case 1:
                    System.out.println("所有人都吃完了！"+registeredParties);
                    return false;
                case 2:
                    System.out.println("所有人都离开了！"+registeredParties);
                    return false;
                case 3:
                    System.out.println("婚礼结束，新娘新郎拥抱！"+registeredParties);
                    return true;
                default:
                    return true;
            }
        }
    }

}
