package com.galaxy.Executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * newFixedThreadPool
 */
public class Test1 {
    public static void main(String[] args) {
        // 固定工作线程
          ExecutorService service = Executors.newFixedThreadPool(3);
        // 一个工作线程
         // ExecutorService service = Executors.newSingleThreadExecutor();
        // 弹性线程
       // ExecutorService service = Executors.newCachedThreadPool();
        try {
            for (int i = 0; i < 3; i++) {

                service.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 工作");
                });
                TimeUnit.MICROSECONDS.sleep(600);

            }

        } catch (Exception e) {

        } finally {
            service.shutdown();

        }
    }
}
