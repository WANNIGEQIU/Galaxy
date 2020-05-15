package com.galaxy.produceconsumer.resource;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ABC {
    private int num = 1;
    private ReentrantLock lock = new ReentrantLock(false);
    private Condition a = lock.newCondition();  // a 1
    private Condition b = lock.newCondition(); // b 2
    private Condition c = lock.newCondition(); // c 3

    public void printA() {
        lock.lock();
        try {
            while (num != 1) {
                a.await();
            }
            doing(5);
            num = 2;
            b.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printB() {
        lock.lock();
        try {
            while (num != 2) {
                b.await();
            }
            doing(3);
            num = 3;
            c.signal();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printC() {
        lock.lock();
        try {
            while (num != 3) {
                c.await();
            }
            doing(2);
            num = 1;
            a.signal();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    private void doing(int temp) {
        for (int i = 0; i < temp; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t" + i);
        }
    }

}
