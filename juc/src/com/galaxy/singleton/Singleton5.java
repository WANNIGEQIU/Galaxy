package com.galaxy.singleton;

import java.util.concurrent.TimeUnit;

public class Singleton5 {

    private static volatile Singleton5 Singleton5;
    private Singleton5(){
        System.out.println(Thread.currentThread().getName()+"\t 我是安全的 懒汉式");
    }
    public static Singleton5 getSingleton5() throws InterruptedException {
        if (Singleton5 == null) {
           synchronized (Singleton5.class){
               if (Singleton5 == null) {
                   TimeUnit.SECONDS.sleep(1);
                   Singleton5 = new Singleton5();
               }
           }
        }
        return Singleton5;
    }
}
