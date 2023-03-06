package com.example.rabbitmq.test02;

import com.example.rabbitmq.Utils.RabbitMqUtils;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

/**
 * @PROJECT_NAME: RabbitMQ_Test
 * @DESCRIPTION:
 * @USER: Administrator
 * @DATE: 2023/3/6 14:21
 */
public class Procducer {
    //设置队列名称
    public static String QUEUE_NAME="test02";

    public static void main(String[] args) throws IOException, TimeoutException {
        //调用工具类获取信道
        Channel channel = RabbitMqUtils.getChannel();
        //创建一个队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //控制台输入信息
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext()){
            String message = scanner.next();
            channel.basicPublish("",QUEUE_NAME,null,message.getBytes(StandardCharsets.UTF_8));
            System.out.println("发送的消息为"+message);
        }
    }
}
