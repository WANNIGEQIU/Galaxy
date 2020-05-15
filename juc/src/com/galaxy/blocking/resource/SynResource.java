package com.galaxy.blocking.resource;

import java.util.concurrent.TimeUnit;

public class SynResource {

    private int anInt = 0;

    public synchronized void produce() {
         while (anInt != 0) {
             try {
                 System.out.println(Thread.currentThread().getName()+"----wait...");
                 super.wait();
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }
         anInt++;
        System.out.println(Thread.currentThread().getName()+"\t生产---"+anInt);
            super.notifyAll();

    }

    public synchronized void consumer() {

        while (anInt == 0) {
            try {
                System.out.println(Thread.currentThread().getName()+"----wait...");
                super.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        anInt--;
        System.out.println(Thread.currentThread().getName()+"\t消费---"+anInt);
        super.notifyAll();
    }

    public static void main(String[] args) {
        SynResource synResource = new SynResource();

        new Thread(()->{
            for (int i = 0; i < 3; i++) {
                synResource.produce();
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

         },"生产线程").start();

        new Thread(()->{
            for (int i = 0; i < 3; i++) {
                synResource.consumer();
            }

        },"消费线程").start();

    }

}
