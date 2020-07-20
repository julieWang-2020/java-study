package com.wzh.multithread.interview.twothreadprint;

/**
 * @description:
 * @author: Wangzh
 * @create: 2020-07-17 15:17
 **/
public class GenDataHelper {


    private GenDataHelper(){}

    /**
     * 获取元素个数为： 1 到 26 数组
     * @return
     */
    public static int[] getFrom1To26Array(){
        int[] a=new int[26];
        for(int i=0;i<a.length;i++){
            a[i]=(i+1);
        }
        return a;
    }

    /**
     * 获取元素个数为： a 到 z 数组
     * @return
     */
    public static char[] getFromaTozArray(){
        char[] a=new char[26];
        char begin_letter='a';
        int count=0;
        for (int i=(int)begin_letter;i<begin_letter+26;i++){
            a[count++]=(char)i;
        }
        return a;
    }

}
