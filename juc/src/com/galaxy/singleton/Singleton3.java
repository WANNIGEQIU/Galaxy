package com.galaxy.singleton;

/**
 * 静态代码块
 */
public class Singleton3 {
    private static final Singleton3 SINGLETON_3 ;
    static {
        SINGLETON_3 = new Singleton3();
    }
    private Singleton3(){
        System.out.println(Thread.currentThread().getName()+"\t 我是静态饿汉式");
    }


    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                Singleton3 singleton3 = Singleton3.SINGLETON_3;
                System.out.println(singleton3);
            },String.valueOf(i)).start();
        }
    }
}
