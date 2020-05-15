package com.galaxy.testcas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁 自制
 */
public class MyLock {
    private  AtomicReference atomicReference;

    public MyLock() {

        this.atomicReference = new AtomicReference<>();
    }

    public void lock() {
        System.out.println(Thread.currentThread().getName() + "\t 进入方法");
        while (!atomicReference.compareAndSet(null, Thread.currentThread())) {
          //  System.out.println(Thread.currentThread().getName() + "\t 重新获取锁...");
        }
    }

    public void unlock() {
        while (atomicReference.compareAndSet(Thread.currentThread(), null)) {
            System.out.println(Thread.currentThread().getName() + "\t 释放锁");
        }
    }


    public static void main(String[] args) throws InterruptedException {

        MyLock myLock = new MyLock();

        System.out.println("start............");

        new Thread(() -> {
            try {
                myLock.lock();
                System.out.println(Thread.currentThread().getName() + "\t" + "业务操作.......");
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "\t" + "业务操作完成.......");

            } catch (Exception e) {
            }
                myLock.unlock();

        }).start();
        /**
         * 让 Thread-0 先获取锁
         */
        TimeUnit.MILLISECONDS.sleep(200);
        new Thread(() -> {

            myLock.lock();
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t" + "休息.......");


            myLock.unlock();

        }).start();
    }
}
