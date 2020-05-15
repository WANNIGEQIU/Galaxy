package com.galaxy.api;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * CyclicBarrier
 */
public class CyclicBarrierTest {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> {
            System.out.println(Thread.currentThread().getName() + "finish");
        });

        for (int i = 0; i < 3; i++) {
            final int x = i;
            new Thread(() -> {

                try {
                    System.out.println(Thread.currentThread().getName()+"计算完成");
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, "线程-"+x).start();
        }
    }

}
