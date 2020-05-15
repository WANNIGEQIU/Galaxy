package com.galaxy.blocking;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * SynchronousQueue demo
 */
public class BlockingQueueTest {

    public static void main(String[] args) {

        BlockingQueue<Object> objects = new SynchronousQueue<>();
        new Thread(() -> {
            try {
                objects.put(1);
                System.out.println(Thread.currentThread().getName() + "\tput1");
                TimeUnit.SECONDS.sleep(1);
                objects.put(2);
                System.out.println(Thread.currentThread().getName() + "\tput2");
               objects.add(3);
                System.out.println(Thread.currentThread().getName() + "\tadd3");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "线程-a").start();


        new Thread(() -> {
            try {
                Object take = objects.take();
                System.out.println(Thread.currentThread().getName() + "\t get" + take);

                Object take1 = objects.take();
                System.out.println(Thread.currentThread().getName() + "\t get" + take1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "线程-b").start();


    }


}
