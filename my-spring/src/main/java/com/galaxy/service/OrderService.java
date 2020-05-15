package com.galaxy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private AsyncService asyncService;

    public void order_finish() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + "--订单完成...");
        asyncService.sendMessage();
        asyncService.sendStockMsg();
    }

}
