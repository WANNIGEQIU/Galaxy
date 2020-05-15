package com.galaxy.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "MQ_B")
public class Receive1 {

    @RabbitHandler
    public void consumer(String s) {
        System.out.println("re2----> "+s);
    }
}
