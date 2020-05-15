package com.galaxy.testvolatile;

import java.util.concurrent.TimeUnit;

/**
 * 不具有原子性demo
 */
public class VolatileTest2 {
    public static void main(String[] args) {
//        MyData data = new MyData();
//        for (int i = 0; i < 10; i++) {
//            new Thread(()-> {
//                for (int i1 = 0; i1 < 100; i1++) {
//                    data.addNum();
//                    data.setAtomicInteger();
//
//                }
//            }, String.valueOf(i)).start();
//        }
//
//        while (Thread.activeCount() > 2) {
//            Thread.yield();
//
//
//        }
//        System.out.println(Thread.currentThread().getName() +"\t"+data.getNum());
//        System.out.println(Thread.currentThread().getName() +"\t"+data.atomicInteger);


        MyData myData = new MyData();

        /**
         * 10个线程进行累加
         */
             for (int i = 0; i < 10; i++) {

                    new Thread(()->{
                        for (int i1 = 0; i1 < 100; i1++) {
                            myData.addNum();
                            myData.setAtomicInteger();
                        }
                    }).start();
                }
        System.out.println(Thread.currentThread().getName()+"\t num-"+myData.getNum());
        System.out.println(Thread.currentThread().getName()+"\t AtomicInteger-"+myData.atomicInteger);
             }




}
