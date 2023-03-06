package com.example.rabbitmq.test02;

import com.example.rabbitmq.Utils.RabbitMqUtils;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @PROJECT_NAME: RabbitMQ_Test
 * @DESCRIPTION:
 * @USER: Administrator
 * @DATE: 2023/3/6 14:29
 */
public class Consumer {
    //设置队列名称
    public static String QUEUE_NAME="test02";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMqUtils.getChannel();
        DeliverCallback deliverCallback=new DeliverCallback() {
            @Override
            public void handle(String s, Delivery delivery) throws IOException {
                //沉睡10秒模拟如果某个消费者产生问题，消息是否会回归队列，如果消费者宕机，消息是否会由其他消费者处理
                //Thread.sleep(10000);
                System.out.println("消费成功"+new String(delivery.getBody()));
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
            }
        };
        CancelCallback cancelCallback=new CancelCallback() {
            @Override
            public void handle(String s) throws IOException {
                System.out.println("消费失败"+s);
            }
        };
        channel.basicConsume(QUEUE_NAME,false,deliverCallback,cancelCallback);
        System.out.println("消费者2启动");
    }
}
