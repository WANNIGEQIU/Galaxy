package com.galaxy.lock;

import com.galaxy.lock.pojo.Phone2;

/**
 * 可重入 ReentrantLock
 */
public class LockTest2 {
    public static void main(String[] args) {
        Phone2 phone2 = new Phone2();
        new Thread(phone2).start();
        new Thread(phone2).start();

    }
}
