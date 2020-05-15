package com.galaxy.thread.test;

import com.galaxy.thread.pojo.MyDeadLock;

/**
 * 🔐 死锁
 */
public class DeadLock {
    public static void main(String[] args) {
        Object o1 = new Object();
        Object o2 = new Object();
        MyDeadLock myDeadLock = new MyDeadLock(o1, o2);
        MyDeadLock myDeadLock1 = new MyDeadLock(o2, o1);
        new Thread(myDeadLock,"线程哈哈").start();
        new Thread(myDeadLock1,"线程呵呵").start();

        Thread thread = new Thread();
        System.out.println(thread.isInterrupted());


    }
}
