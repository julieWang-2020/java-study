package com.wzh.multithread.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * @author wzh
 * @date 2020-07-19 23:41
 */
public class LongEventFactory implements EventFactory<LongEvent> {


    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
