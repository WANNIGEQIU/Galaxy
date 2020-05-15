package com.galaxy.Executors;

import java.util.concurrent.TimeUnit;

public class DeadLock implements Runnable {
    private Object lockA;
    private Object lockB;

    public DeadLock(Object o, Object b) {
        this.lockA = o;
        this.lockB = b;
    }

    @Override
    public void run() {

        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + "\t get " + lockA);
            try {
                TimeUnit.MICROSECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t try get " + lockB);


            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "\t  get " + lockB);

            }


        }

    }

}
