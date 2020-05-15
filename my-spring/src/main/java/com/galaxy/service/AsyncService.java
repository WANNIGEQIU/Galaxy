package com.galaxy.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class AsyncService {


    @Async("threadPool")
    public void sendMessage() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + "--发送短信");
        TimeUnit.SECONDS.sleep(1);
        System.out.println(Thread.currentThread().getName() + "--发送完毕");
    }

    @Async("threadPool")
    public void sendStockMsg() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + "--发送消息通知物流");
        TimeUnit.SECONDS.sleep(1);
        System.out.println(Thread.currentThread().getName() + "--通知物流完成");
    }

}
