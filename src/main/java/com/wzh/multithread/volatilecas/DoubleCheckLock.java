package com.wzh.multithread.volatilecas;

/**
 * @description: 懒汉式单例，双重检查
 * INSTANCE = new DoubleCheckLock()经过编译器编译之后指令分成三步
 * 1.给指令申请内存
 * 2.给成员变量初始化
 * 3.是把这块内存的内容赋值给INSTANCE。
 * volatile 禁止指令重排序
 * @author: Wangzh
 * @create: 2020-07-08 14:41
 **/
public class DoubleCheckLock {

    private static volatile DoubleCheckLock instance=null;

    private DoubleCheckLock(){}

    public static DoubleCheckLock getInstance() {
        if(instance==null){
            synchronized (DoubleCheckLock.class){
                if(instance==null){
                    instance=new DoubleCheckLock();
                }
            }
        }
        return instance;
    }
}
