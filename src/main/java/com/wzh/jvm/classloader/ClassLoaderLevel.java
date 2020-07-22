package com.wzh.jvm.classloader;

/**
 * @author: Wangzh
 * @create: 2020-07-20 17:34
 * @description: 类加载
 * 类加载器：
 * AppClassLoader：加载classpath 指定内容
 * ExtClassLoader：加载扩展包 jre/lib/ext/*.jar / -Djava.ext.dirs 指定
 * Bootstrap：加载lib/rt.jar charset.jar等核心类，native方法
 **/
public class ClassLoaderLevel {
    public static void main(String[] args) {

        System.out.println(String.class.getClassLoader());
        System.out.println(sun.awt.HKSCS.class.getClassLoader());

        System.out.println(sun.net.spi.nameservice.dns.DNSNameService.class.getClassLoader());
        System.out.println(ClassLoaderLevel.class.getClassLoader());

        System.out.println(sun.net.spi.nameservice.dns.DNSNameService.class.getClassLoader().getClass().getClassLoader());
        System.out.println(ClassLoaderLevel.class.getClassLoader().getClass().getClassLoader());

    }
}
