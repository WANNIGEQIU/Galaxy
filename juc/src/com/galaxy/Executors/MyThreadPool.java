package com.galaxy.Executors;

import java.util.concurrent.*;

public class MyThreadPool {

    private final static int CORE_POOL_SIZE = 2;
    private final static int MAXSIZE = 3;
    private final static long ACTIVE_TIME = 2L;
    private final static TimeUnit TIME_UNIT = TimeUnit.SECONDS;
    private final static BlockingQueue BLOCKING_QUEUE = new LinkedBlockingDeque(2);


    public static void main(String[] args) {
        ExecutorService executor = new ThreadPoolExecutor(
                CORE_POOL_SIZE, MAXSIZE, ACTIVE_TIME, TIME_UNIT, BLOCKING_QUEUE,
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
        try {
            for (int i = 0; i < 5; i++) {
                executor.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t处理业务");
                });

                TimeUnit.MICROSECONDS.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }

    }
}
