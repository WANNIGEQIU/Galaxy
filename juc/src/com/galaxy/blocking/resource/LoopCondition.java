package com.galaxy.blocking.resource;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程按顺序调用 a->b->c
 * a 线程打印 3次 b线程打印 2次 c线程打印5次
 * 精准唤醒
 */
public class LoopCondition {
    private int flag = 1;  // 标志位 1-a, 2-b, 3-c
    private static final Lock LOCK = new ReentrantLock();
    private static final Condition a = LOCK.newCondition();
    private static final Condition b = LOCK.newCondition();
    private static final Condition c = LOCK.newCondition();


    public void printA() {
        LOCK.lock();
        try {
            while (flag != 1) {
                a.await();
            }
            TimeUnit.MILLISECONDS.sleep(600);
            loopPrint(3);
            this.flag = 2;
            b.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            LOCK.unlock();
        }
    }

    public void printB() {
        LOCK.lock();
        try {
            while (flag != 2) {
                b.await();
            }
            TimeUnit.MILLISECONDS.sleep(600);
            loopPrint(2);
            this.flag = 3;
            c.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            LOCK.unlock();
        }
    }

    public void printC() {
        LOCK.lock();
        try {
            while (flag != 3) {
                c.await();
            }
            TimeUnit.MILLISECONDS.sleep(600);
            loopPrint(5);
            this.flag = 1;
            a.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            LOCK.unlock();
        }
    }

    private void loopPrint(int temp) {
        for (int i = 0; i < temp; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(700);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t" + i);
        }
    }


    public static void main(String[] args) {

        LoopCondition loopCondition = new LoopCondition();
        ExecutorService service = Executors.newFixedThreadPool(4);
        service.execute(() -> loopCondition.printA());
        service.execute(() -> loopCondition.printA());
        service.execute(() -> loopCondition.printB());
        service.execute(() -> loopCondition.printC());
        service.shutdown();


    }
}
