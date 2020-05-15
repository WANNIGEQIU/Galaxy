package com.galaxy.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReentrantLock demo
 */
public class ReentrantLockTest {
  private int ticket = 5;   // 模拟5张票
  private Lock lock = new ReentrantLock(); // 默认实现非公平锁策略

    /**
     * 卖票
     */
    public void sell() {

        try {
            lock.lock();
            if (ticket > 0) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName()+"\t 卖票->"+ticket--+"剩余"+ticket);
            }else {
                System.out.println(Thread.currentThread().getName()+"\t 票已经卖光->"+ticket);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockTest test = new ReentrantLockTest();
        ExecutorService service = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            service.execute(()->test.sell());
        }
        service.shutdown();
    }

}
