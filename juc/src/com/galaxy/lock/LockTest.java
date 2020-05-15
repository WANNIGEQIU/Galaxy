package com.galaxy.lock;

import com.galaxy.lock.pojo.Phone;

import java.util.concurrent.TimeUnit;

/**
 * 可重入 synchronized
 */
public class LockTest {

    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
                phone.msg();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"线程--1").start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
                phone.msg();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"线程--2").start();
    }
}
