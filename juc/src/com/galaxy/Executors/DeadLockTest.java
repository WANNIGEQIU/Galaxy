package com.galaxy.Executors;

import com.sun.tools.javac.Main;

/**
 * 死锁🔒
 */
public class DeadLockTest {
    public static void main(String[] args) {
        DeadLock deadLock = new DeadLock(1, 2);
        DeadLock deadLock1 = new DeadLock(2, 1);

        Thread a = new Thread(deadLock, "a");
        Thread b = new Thread(deadLock1, "b");
        a.start();
        b.start();




    }
}

