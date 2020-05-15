package com.galaxy.lock;

import com.galaxy.lock.pojo.MyCache;

/**
 * 读写锁
 */
public class LockTest4 {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        // write
        for (int i = 0; i < 3; i++) {
            final int x =i;
           new Thread(() -> {
           myCache.write(String.valueOf(x),x);
           }, "线程-"+x).start();
        }

        // read

        for (int i = 0; i < 3; i++) {
            final int x =i;
            new Thread(() -> {
                myCache.read(String.valueOf(x));
            }, "线程-"+x).start();
        }

    }
}
