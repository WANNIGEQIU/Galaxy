package com.galaxy.thread.pojo;

public class MyDeadLock implements Runnable {
    private Object lock1;
    private Object lock2;

    public MyDeadLock(Object a, Object b) {
        this.lock1 = a;
        this.lock2 = b;
    }

    @Override
    public void run() {
        synchronized (lock1) {
            System.out.println(Thread.currentThread().getName() + "\t get--" + lock1);

            System.out.println(Thread.currentThread().getName() + "\ttry..........get--" + lock2);

            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName() + "\t get -" + lock2);

            }
        }

    }
}
