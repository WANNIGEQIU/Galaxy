package com.galaxy.lock;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 手写自旋锁 demo
 */
public class LockTest3 {
    // 原子引用
    AtomicReference<Thread> atomicReference = new AtomicReference<>();


    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "\t" + " try get lock....");
        while (!atomicReference.compareAndSet(null, thread)) {
            System.out.println(thread.getName() + "\t 重新获取锁中。。。。。。");
        }

    }

    public void unMyLock() {

        Thread thread = Thread.currentThread();
        while (atomicReference.compareAndSet(thread, null)) {
            System.out.println(thread.getName() + "\t" + "  release lock....");

        }
    }


    public static void main(String[] args) throws InterruptedException {
        LockTest3 test3 = new LockTest3();


        new Thread(() -> {
            test3.myLock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t" + "  do sth....");
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                test3.unMyLock();
                System.out.println(Thread.currentThread().getName() + "\t" + "  bye....");
            }
        }, "线程-a").start();


        new Thread(() -> {
            test3.myLock();
            System.out.println(Thread.currentThread().getName() + "\t" + "  run....");
            test3.unMyLock();
        }, "线程-b").start();

    }
}
