package com.galaxy.produceconsumer.resource;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyNumber {
    private int anInt = 0;
    private ReentrantLock lock = new ReentrantLock(false);
    private Condition condition = lock.newCondition();

    public void Subtraction() {
        lock.lock();
        try {
            while (anInt == 0) {
                condition.await();
            }
            anInt--;
            System.out.println(Thread.currentThread().getName() + "\t" + anInt);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void addition() {
        lock.lock();
        try {
            while (anInt != 0) {
                condition.await();
            }
            anInt++;
            System.out.println(Thread.currentThread().getName() + "\t" + anInt);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
