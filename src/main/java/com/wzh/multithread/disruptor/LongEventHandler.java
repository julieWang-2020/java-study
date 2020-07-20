package com.wzh.multithread.disruptor;


import com.lmax.disruptor.EventHandler;

/**
 * @author wzh
 * @date 2020-07-19 23:41
 */
public class LongEventHandler implements EventHandler<LongEvent> {

    public static long count=0;

    @Override
    public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
        count++;
        System.out.println("["+Thread.currentThread().getName()+"]"+longEvent+"序号"+l);
    }
}
