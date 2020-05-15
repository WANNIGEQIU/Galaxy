package com.galaxy.testvolatile;


import java.util.concurrent.TimeUnit;

/**
 * 可见性 demo
 */
public class VolatileTest {

    public static void main(String[] args) {

        MyData data = new MyData();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "开始");
            try {
                data.add();
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t end" + data.getNum());
        }, "galaxy").start();
        while (data.getNum() == 0) {

        }
        System.out.println(" main thread " + data.getNum());


    }


}


