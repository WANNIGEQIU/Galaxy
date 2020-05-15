package com.galaxy.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class BaseData {
    AtomicInteger atomicInteger = new AtomicInteger();
    // 使用 AtomicInteger 可以不用加锁,也可以保证线程安全
    public void setAtomicInteger() {
        this.atomicInteger.incrementAndGet();


    }
    public int get() {
        return atomicInteger.get();
    }

    public static void main(String[] args) {
        BaseData baseData = new BaseData();
        System.out.println(baseData.get());
        for (int i = 0; i < 10; i++) {
            baseData.setAtomicInteger();
        }
        System.out.println(baseData.get());

    }

}
