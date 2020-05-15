package com.galaxy;


import com.galaxy.api.enums.CountryEnum;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.galaxy.api.enums.CountryEnum.ONE;

public class Air {

    private AtomicInteger i = new AtomicInteger();

    public  int getI() {
        return i.get();
    }
    public  void setI(){
        i.incrementAndGet();
    }


    public static void main(String[] args) throws InterruptedException {
        Air air = new Air();
        CountDownLatch countDownLatch = new CountDownLatch(1000);

        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            service.execute(()->air.setI());
            countDownLatch.countDown();
        }

        countDownLatch.await();
        service.shutdown();
        System.out.println(air.getI());

    }



}