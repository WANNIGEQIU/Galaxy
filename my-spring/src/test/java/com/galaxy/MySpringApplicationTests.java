package com.galaxy;

import com.galaxy.mq.Send;
import com.galaxy.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MySpringApplicationTests {

    @Autowired
    private OrderService orderService;

    @Autowired
    Send send;

    @Test
    void contextLoads() throws InterruptedException {
        send.send1();
        new Thread(()->{
            send.send2();
        }).start();
    }

}
