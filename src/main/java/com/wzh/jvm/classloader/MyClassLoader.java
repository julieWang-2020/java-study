package com.wzh.jvm.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * @author: Wangzh
 * @create: 2020-07-21 17:41
 * @description: 自定义类加载器
 **/
public class MyClassLoader extends ClassLoader{

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File f = new File("E:\\workspace\\gitspace\\java-study\\target\\classes\\", name.replace(".", "/").concat(".class"));
        try {
            FileInputStream fis = new FileInputStream(f);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int b = 0;

            while ((b=fis.read()) !=0) {
                baos.write(b);
            }

            byte[] bytes = baos.toByteArray();
            baos.close();
            fis.close();//可以写的更加严谨

            return defineClass(name, bytes, 0, bytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.findClass(name);
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader myClassLoader=new MyClassLoader();
        Class c1=myClassLoader.loadClass("com.wzh.jvm.classloader.Hello");
        Class c2=myClassLoader.loadClass("com.wzh.jvm.classloader.Hello");
        System.out.println(c1==c2);

        Hello o= (Hello) c1.newInstance();
        o.m();

        System.out.println(myClassLoader.getClass().getClassLoader());
        System.out.println(myClassLoader.getParent());

        System.out.println(getSystemClassLoader());

    }
}
