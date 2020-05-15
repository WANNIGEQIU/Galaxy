package com.galaxy.controller;

import com.galaxy.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping
    public String finish() throws InterruptedException {
        orderService.order_finish();
        return "订单完成";
    }

}
