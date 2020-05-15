package com.galaxy.api;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch demo
 * 模拟准备材料齐全后才可以制作
 */
public class CountDownLatchTest {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(3); // 需要准备三种材料
        for (int i = 0; i < 3; i++) {
            final int x = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 准备完成");
                latch.countDown(); // 准备完一种材料
            }, "材料-" + x).start();
        }
        try {
            latch.await(); //  主线程等待
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "\t 开始制作");
    }
}
