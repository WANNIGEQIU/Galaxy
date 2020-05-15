package com.galaxy.api;

import java.util.concurrent.*;

/**
 *
 */
public class CyclicBarrierTest1 {
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,
                ()->System.out.println("收集完成")); // 所有线程达到屏障点执行此线程任务

        for (int i = 0; i < 7; i++) {
            int finalI = i;
            new Thread(()->{

                System.out.println(Thread.currentThread().getName()+"\t 收集到"+(finalI +1));
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }


    }
}
