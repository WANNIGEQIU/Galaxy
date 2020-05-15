package com.galaxy.mq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Send {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send1() {
        String s = "hello aa";
        System.out.println("send1----> "+s);
        this.rabbitTemplate.convertAndSend("EXC","msg.a",s );
    }

    public void send2() {
        String s = "hello ###";
        System.out.println("send1----> "+s);
        this.rabbitTemplate.convertAndSend("EXC","msg.#",s );
    }

}
