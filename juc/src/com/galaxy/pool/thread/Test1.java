package com.galaxy.pool.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test1 {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(3);
        ExecutorService service1 = Executors.newSingleThreadExecutor();
        ExecutorService service2 = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            service2.execute(()->{
                for (int i1 = 0; i1 < 3; i1++) {
                    System.out.println(Thread.currentThread().getName()+"\t"+i1);
                }
            });
        }
        service2.shutdown();
    }
}
