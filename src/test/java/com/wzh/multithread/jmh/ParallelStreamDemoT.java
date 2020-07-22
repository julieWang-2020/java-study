package com.wzh.multithread.jmh;

import org.openjdk.jmh.annotations.Benchmark;

/**
 * @description:
 * @author: Wangzh
 * @create: 2020-07-21 14:10
 **/
public class ParallelStreamDemoT {

    @Benchmark
    public void testForEach() {
        ParallelStreamDemo.foreach();
    }
}
