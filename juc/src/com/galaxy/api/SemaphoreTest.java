package com.galaxy.api;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Semaphore 银行窗口业务
 */
public class SemaphoreTest {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2); // 模拟俩个窗口
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire(); // 抢占资源,若无资源则等待
                    if (semaphore.availablePermits() >= 0) {
                        System.out.println(Thread.currentThread().getName() + "\t 排到号");
                    }

                    TimeUnit.SECONDS.sleep(2); // 模拟办理业务
                    System.out.println(Thread.currentThread().getName() + "\t 业务办理完成");
                    semaphore.release(); // 释放资源

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "客户" + i).start();
        }
    }
}
