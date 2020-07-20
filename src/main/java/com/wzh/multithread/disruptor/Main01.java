package com.wzh.multithread.disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import java.util.concurrent.Executors;

/**
 * @author wzh
 * @date 2020-07-19 23:47
 */
public class Main01 {

    public static void main(String[] args) {
        LongEventFactory factory=new LongEventFactory();
        int bufferSize=1024;

        Disruptor<LongEvent> disruptor=new Disruptor<LongEvent>(factory,bufferSize, Executors.defaultThreadFactory());

        disruptor.handleEventsWith(new LongEventHandler());

        disruptor.start();

        RingBuffer<LongEvent> ringBuffer=disruptor.getRingBuffer();

        long sequence=ringBuffer.next();
        try{
            LongEvent event=ringBuffer.get(sequence);

            event.set(8888L);
        }finally {
            ringBuffer.publish(sequence);
        }



    }
}
