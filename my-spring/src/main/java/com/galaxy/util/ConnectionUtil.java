package com.galaxy.util;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * RabbitMq 连接工具类
 */
public class ConnectionUtil {

    public static Connection getConnection() throws IOException, TimeoutException {
        // 创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        // 设置地址
        factory.setHost("localhost");
        // 端口号
        factory.setPort(5672);
        // 设置账号信息
        factory.setUsername("guest");
        factory.setUsername("guest");
        factory.setVirtualHost("/");
        // 获取连接
        Connection connection = factory.newConnection();
        return connection;
    }

}
