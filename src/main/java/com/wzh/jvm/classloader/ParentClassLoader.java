package com.wzh.jvm.classloader;

/**
 * @author wzh
 * @date 2020-07-21 21:50
 */
public class ParentClassLoader {

    private static MyClassLoader parent=new MyClassLoader();

    private static class MyLoader extends ClassLoader{
        public MyLoader(){
            super(parent);
        }
    }

    public static void main(String[] args) {
        MyLoader myLoader=new MyLoader();
        System.out.println(myLoader.getClass().getClassLoader());
    }
}
