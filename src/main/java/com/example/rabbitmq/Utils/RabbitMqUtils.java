package com.example.rabbitmq.Utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @PROJECT_NAME: RabbitMQ_Test
 * @DESCRIPTION:
 * @USER: Administrator
 * @DATE: 2023/3/5 17:14
 */
public class RabbitMqUtils {
    public static Channel getChannel() throws IOException, TimeoutException {
        //创建连接工厂,建立连接
        ConnectionFactory connectionFactory=new ConnectionFactory();
        connectionFactory.setHost("192.168.153.100");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("zxy902");
        Connection connection = connectionFactory.newConnection();
        //创建信道
        return connection.createChannel();
    }
}
