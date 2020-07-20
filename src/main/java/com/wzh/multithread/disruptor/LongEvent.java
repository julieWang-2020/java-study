package com.wzh.multithread.disruptor;

/**
 * @author wzh
 * @date 2020-07-19 23:41
 */
public class LongEvent{

    private long value;


    public void set(long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "LongEvent{" +
                "value=" + value +
                '}';
    }
}
