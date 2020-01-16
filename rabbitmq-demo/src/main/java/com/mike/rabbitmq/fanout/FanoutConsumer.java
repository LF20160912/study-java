package com.mike.rabbitmq.fanout;

import com.rabbitmq.client.*;

import java.io.IOException;

/*
  Author:Mike
  创建时间：2019/12/21
  描述：fanout模式消费者
*/
public class FanoutConsumer {
    private static final String EXCHANGE_NAME = "logs";
    private static final String QUEUE_NAME = "fanout_queue";

    public static void main(String[] args) throws Exception {
        ConnectionFactory cnnFactory = new ConnectionFactory();
        //rabbitmq监听接口
        cnnFactory.setHost("192.168.3.103");
        //rabbitmq监听端口
        cnnFactory.setPort(5672);
        cnnFactory.setVirtualHost("my_vhost");
        //访问用户和密码
        cnnFactory.setUsername("admin");
        cnnFactory.setPassword("admin");


        Connection cnn = cnnFactory.newConnection();
        Channel channel = cnn.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        //获取随机队列名称,不能通过queueDeclare().getQueue()取得，因为会取到amq.开通的队列名，是不允许的。
        //String queueName = channel.queueDeclare().getQueue();
        //创建队列
        channel.queueDeclare(QUEUE_NAME, false, false, true, null);//不能声明队列
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "");

        System.out.println("Waiting for msg ……");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, "UTF-8");
                System.out.println("Receive msg is :" + msg);
            }
        };

        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}
