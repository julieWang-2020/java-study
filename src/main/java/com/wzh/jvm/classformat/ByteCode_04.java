package com.wzh.jvm.classformat;

/**
 * @author: Wangzh
 * @create: 2020-07-21 16:40
 * @description:
 **/
public class ByteCode_04 {
    int i=0;
    String s="Hello ByteCode!";

    /*
        0 aload_0
        1 invokespecial #1 <java/lang/Object.<init>>
        4 aload_0
        5 iconst_0
        6 putfield #2 <com/wzh/jvm/classformat/ByteCode_03.i>
        9 aload_0
        10 ldc #3 <Hello ByteCode!>
        12 putfield #4 <com/wzh/jvm/classformat/ByteCode_03.s>
        15 aload_0
        16 iload_1
        17 putfield #2 <com/wzh/jvm/classformat/ByteCode_03.i>
        20 aload_0
        21 aload_2
        22 putfield #4 <com/wzh/jvm/classformat/ByteCode_03.s>
        25 return
     */
    public ByteCode_04(int i, String s) {
        this.i = i;
        this.s = s;
    }

    public void m() {}
}
