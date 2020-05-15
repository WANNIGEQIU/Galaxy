package com.galaxy.synchronize;

import com.galaxy.synchronize.base.Person;

import java.util.concurrent.*;

public class Test {
    public static void main(String[] args) {
        /**
         * main
         */
        // 不建议的使用线程池方式
        ExecutorService service = Executors.newFixedThreadPool(3);
        Person person = new Person();
        //service.execute(()->person.test());         // 同步方法
       service.execute(()->Person.testSta());    // 静态同步方法
        //service.execute(()->person.aVoid());      //  synchronized(this)
        service.execute(()->person.aVoids());       // synchronized(Class)
        service.shutdown();





    }
}
