package com.galaxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class MySpringApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(MySpringApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(MySpringApplication.class, args);
        LOGGER.info("服务启动成功");

    }




}
