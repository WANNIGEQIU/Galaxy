package com.galaxy.produceconsumer;

import com.galaxy.produceconsumer.resource.MyResource;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class BlockDemo {
    public static void main(String[] args) throws InterruptedException {
        MyResource myResource = new MyResource(new SynchronousQueue<>());

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "生产者线程启动");
            try {
                myResource.produce();
            } catch (Exception e) {

            }
        }, "produce").start();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "消费者线程启动");
            try {
                myResource.consumer();
            } catch (Exception e) {

            }
        }, "consumer").start();


        TimeUnit.SECONDS.sleep(5);
        System.out.println("关闭服务");
        myResource.stop();
    }
}
