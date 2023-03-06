package com.example.rabbitmq.test01;

import com.example.rabbitmq.Utils.RabbitMqUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @PROJECT_NAME: RabbitMQ_Test
 * @DESCRIPTION:
 * @USER: Administrator
 * @DATE: 2023/3/5 16:39
 */
public class Consumer {
    //队列的名称
    public  static  final  String QUEUE_NAME="hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        //使用工具类,建立连接,创建信道
        Channel channel = RabbitMqUtils.getChannel();

        DeliverCallback deliverCallback=new DeliverCallback() {
            @Override
            public void handle(String s, Delivery delivery) throws IOException {
                System.out.println("消费成功的回调"+ new String(delivery.getBody()));
            }
        };
        CancelCallback cancelCallback=new CancelCallback() {
            @Override
            public void handle(String s) throws IOException {
                System.out.println("取消消费的回调"+s);
            }
        };
        //消费者消费消息
        /**
         * 参数内容
         * 1.消费哪个队列
         * 2.消费之后是否要自动应答（T代表自动/F代表手动）
         * 3.消费者成功消费的回调
         * 4.消费者取消消费的回调
         */

        channel.basicConsume(QUEUE_NAME,true,deliverCallback,cancelCallback);


    }
}
