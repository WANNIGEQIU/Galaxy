package com.galaxy.produceconsumer.resource;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MyResource {
    // 消费生产 默认开启
    private volatile boolean FLAG = true;
    private AtomicInteger atomicInteger = new AtomicInteger();
    private BlockingQueue<String> blockingQueue = null;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        // 记录实际实现类
        System.out.println(blockingQueue.getClass().getName());
    }

    // 生产
    public void produce() throws InterruptedException {
        String data = null;
        boolean b;
        while (FLAG) {
            data = atomicInteger.incrementAndGet() + "";
            b = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            if (b) {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列" + data + "成功");
            } else {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列" + data + "失败");
            }
            TimeUnit.SECONDS.sleep(1L);
        }
        System.out.println(Thread.currentThread().getName() + "\t服务关闭--停止生产 FLAG=" + FLAG);
    }

    // 消费
    public void consumer() throws InterruptedException {
        String poll = null;
        while (FLAG) {
            poll = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if (null == poll || poll.equalsIgnoreCase("")) {
                FLAG = false;
                System.out.println(Thread.currentThread().getName() + "\t 超出等待时间，消费退出");
                return;

            }
            System.out.println(Thread.currentThread().getName() + "\t 消费队列" + poll + "成功");

        }
    }

    public void stop() {
        this.FLAG = false;
    }


}
