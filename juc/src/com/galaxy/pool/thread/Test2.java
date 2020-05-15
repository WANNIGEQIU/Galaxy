package com.galaxy.pool.thread;

import java.util.concurrent.*;

public class Test2 {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4,
                1L, TimeUnit.SECONDS,new ArrayBlockingQueue<>(3),Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
       for (int i = 0; i < 7; i++) {
            int finalI = i;
            threadPoolExecutor.execute(()->{
                System.out.println(Thread.currentThread().getName()+"---doing...."+ finalI);
                try {
                    TimeUnit.MICROSECONDS.sleep(666);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        threadPoolExecutor.shutdown();
    }
}
