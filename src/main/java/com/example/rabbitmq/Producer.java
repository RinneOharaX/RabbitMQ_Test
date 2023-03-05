package com.example.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * @PROJECT_NAME: RabbitMQ_Test
 * @DESCRIPTION:
 * @USER: Administrator
 * @DATE: 2023/3/5 15:56
 */
public class Producer {
    public  static  final  String QUEUE_NAME="hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        //调用工具类，建立连接，得到信道
        Channel channel = GetChannel.getChannel();
        /**创建一个队列，省略交换机
         * 参数为
         * 1.队列名称
         * 2.队列里的消息是否持久化（T/F）
         * 3.该队列是否只供一个消费者消费（T允许多个/F不允许多个）
         * 4.是否自动删除 最后一个消费者断开连接以后，该队列是否自动删除 （T删除/F不删除）
         * 5.其他参数
         */
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        String messeage="Hello,RabbitMQ";
        /**
         * 发送一格消息
         * 参数为
         * 1.发送到哪个交换机
         * 2.路由的Key值是哪个，本次是队列的名称
         * 3.其他参数消息
         * 4.发送的消息
         */
        channel.basicPublish("",QUEUE_NAME,null,messeage.getBytes(StandardCharsets.UTF_8));
        System.out.println("消息发送完毕");
    }
}
