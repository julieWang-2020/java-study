package com.wzh.jvm.classloader;

/**
 * @author: Wangzh
 * @create: 2020-07-21 17:16
 * @description:
 **/
public class ClassLoaderScope {
    public static void main(String[] args) {
        //D:\soft\java\jdk1.8\jre\lib\resources.jar
        //D:\soft\java\jdk1.8\jre\lib\rt.jar
        //D:\soft\java\jdk1.8\jre\lib\sunrsasign.jar
        //D:\soft\java\jdk1.8\jre\lib\jsse.jar
        //D:\soft\java\jdk1.8\jre\lib\jce.jar
        //D:\soft\java\jdk1.8\jre\lib\charsets.jar
        //D:\soft\java\jdk1.8\jre\lib\jfr.jar
        //D:\soft\java\jdk1.8\jre\classes
        String pathBoot=System.getProperty("sun.boot.class.path");
        System.out.println(pathBoot.replaceAll(";",System.lineSeparator()));

        //D:\soft\java\jdk1.8\jre\lib\ext
        //C:\WINDOWS\Sun\Java\lib\ext
        System.out.println("-------------------------");
        String pathExt=System.getProperty("java.ext.dirs");
        System.out.println(pathExt.replaceAll(";",System.lineSeparator()));

        System.out.println("-------------------------");
        String pathApp=System.getProperty("java.class.path");
        System.out.println(pathApp.replaceAll(";",System.lineSeparator()));
    }
}
