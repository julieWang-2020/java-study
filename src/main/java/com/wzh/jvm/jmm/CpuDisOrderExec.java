package com.wzh.jvm.jmm;

/**
 * @author wzh
 * @date 2020-07-21 22:35
 * @description:
 * cpu 乱序执行
 */
public class CpuDisOrderExec {

    private static int x=0,y=0;
    private static int a=0,b=0;

    public static void main(String[] args) throws InterruptedException {
        int i=0;
        for(;;){
            x=0;y=0;
            a=0;b=0;
            Thread one=new Thread(()->{
                shortWait(100000);
                a=1;
                x=b;
            });

            Thread other=new Thread(()->{
                b=1;
                y=a;
            });

            one.start();other.start();
            one.join();other.join();
            i++;
            String result="第"+i+"次（"+x+","+y+"）";
            if(x==0 && y==0){
                System.out.println(result);
                break;
            }
        }
    }

    public static void shortWait(long interval){
        long start=System.nanoTime();
        long end;
        do{
            end=System.nanoTime();
        }while (start+interval >=end);
    }
}
