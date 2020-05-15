package com.galaxy.singleton;

/**
 * 懒汉式 low
 */
public class Singleton4 {

    private static  Singleton4 singleton4;
    private Singleton4(){
        System.out.println(Thread.currentThread().getName()+"\t 我是low 懒汉式");
    }
    public static Singleton4 getSingleton4() {
        if (singleton4 == null) {
            singleton4 = new Singleton4();
        }
        return singleton4;
    }

}
