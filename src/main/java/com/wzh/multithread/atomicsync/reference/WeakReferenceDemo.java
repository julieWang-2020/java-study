package com.wzh.multithread.atomicsync.reference;

import java.lang.ref.WeakReference;

/**
 * @description: 弱引用
 * 只要 gc 就会回收弱引用
 * 使用场景：一般用在容器
 * WeakHashMap
 * 强 -> 软 -> 弱 -> 虚
 * @author: Wangzh
 * @create: 2020-07-13 17:44
 **/
public class WeakReferenceDemo {

    public static void main(String[] args) {
        WeakReference<M> m=new WeakReference<>(new M());
        System.out.println(m.get());
        System.gc();
        System.out.println(m.get());

        ThreadLocal<M> tl=new ThreadLocal<>();
        tl.set(new M());
        tl.remove();
    }
}


class  M{
    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize");
    }
}
