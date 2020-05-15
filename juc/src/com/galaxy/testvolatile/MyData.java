package com.galaxy.testvolatile;

import java.util.concurrent.atomic.AtomicInteger;

    public class MyData {

    private volatile int num;

    public synchronized void add() {
        this.num = 20;
    }

    public  void addNum() {
        this.num++;
    }

        /**
         * 原子操作
         */
     AtomicInteger atomicInteger = new AtomicInteger();

        /**
         * 自增 相当于num++
         */
    public void setAtomicInteger() {
        atomicInteger.getAndIncrement();
    }












    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
