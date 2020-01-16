package com.mike.rabbitmq.topic;

import com.mike.rabbitmq.commons.ConnectionUtil;
import com.mike.rabbitmq.commons.ExchangeType;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/*
  Author:Mike
  创建时间：2019/12/22
  描述：
*/
public class TopicConsumer {
    private static final String QUEUE_NAME1 = "boys";
    private static final String QUEUE_NAME2 = "girls";
    private static final String QUEUE_NAME3 = "love";

    private static Connection cnn;
    private static Channel channel;

    static {
        try {
            //创建连接对象和管道
            cnn = ConnectionUtil.getConnection();
            channel = cnn.createChannel();
            //创建交换机
            channel.exchangeDeclare(ExchangeType.TOPIC.getName(), ExchangeType.TOPIC.getType());
            //声明队列
            channel.queueDeclare(QUEUE_NAME1, false, false, true, null);
            channel.queueBind(QUEUE_NAME1, ExchangeType.TOPIC.getName(), "boys.*.*");

            channel.queueDeclare(QUEUE_NAME2, false, false, true, null);
            channel.queueBind(QUEUE_NAME2, ExchangeType.TOPIC.getName(), "*.*.girls");

            channel.queueDeclare(QUEUE_NAME3, false, false, true, null);
            channel.queueBind(QUEUE_NAME3, ExchangeType.TOPIC.getName(), "*.love.*");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }

    private static void receiveMsg() throws IOException {
        Consumer consumer1 = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body);
                System.out.println("consumer1 receive msg-->" + msg);
            }
        };

        Consumer consumer2 = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body);
                System.out.println("consumer2 receive msg-->" + msg);
            }
        };

        Consumer consumer3 = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body);
                System.out.println("consumer3 receive msg-->" + msg);
            }
        };

        /*1.队列名称；2.是否自动发送ack；3.消费者*/
        channel.basicConsume(QUEUE_NAME1, true, consumer1);
        channel.basicConsume(QUEUE_NAME2, true, consumer2);
        channel.basicConsume(QUEUE_NAME3, true, consumer3);
    }

    public static void main(String[] args) {
        try {
            receiveMsg();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
