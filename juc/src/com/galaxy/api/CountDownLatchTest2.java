package com.galaxy.api;

import com.galaxy.api.enums.CountryEnum;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch demo2
 * 一统六国 枚举
 */
public class CountDownLatchTest2 {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(6); // 模拟6国
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"被灭");
                latch.countDown(); // 被灭1国
            }, CountryEnum.countryFor(i+1).getName()).start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread thread = Thread.currentThread();
        thread.setName("秦国");
        System.out.println(thread.getName()+"一统天下");
    }
}
