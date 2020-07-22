package com.wzh.jvm.classformat;

/**
 * @author: Wangzh
 * @create: 2020-07-21 14:36
 * @description:
 **/
public class ByteCode_02 {
    /*
    0 iconst_0
    1 istore_1
    2 iload_1
    3 iinc 1 by 1
    6 istore_2
    7 return
     */
    void m() {
        int i=0;
        int j = i++;
    }
}
