package com.galaxy.singleton;

/**
 * 饿汉式 直接实例化
 */
public class Singleton1 {

    private static final Singleton1 SINGLETON_1 = new Singleton1();

    private Singleton1(){
        System.out.println(Thread.currentThread().getName()+"\t 我是饿汉式");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new  Thread(()-> {
            },String.valueOf(i)).start();
        }
    }
}
