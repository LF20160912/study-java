package com.mike.rabbitmq.direct;

import com.mike.rabbitmq.commons.ExchangeType;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/*
  Author:Mike
  创建时间：2019/12/21
  描述：p2p
*/
public class DirectConsumer {
    private static final String QUEUE_NAME = "BOYS_QN";
    private static final String QUEUE_NAME2 = "GIRLS_QN";

    private static ConnectionFactory factoryCnn;
    private static Connection cnn;
    private static Channel channel;

    static {

        factoryCnn = new ConnectionFactory();
        factoryCnn.setHost("192.168.3.102");
        factoryCnn.setPort(5672);
        factoryCnn.setVirtualHost("/");
        factoryCnn.setUsername("rabbit");
        factoryCnn.setPassword("rabbit");
        try {
            cnn = factoryCnn.newConnection();
            channel = cnn.createChannel();
            channel.exchangeDeclare(ExchangeType.DIRECT.getName(), ExchangeType.DIRECT.getType());
            /*1.队列名称，2.是否可持久化，3.是否排他列队，4.是否自动删除（空闲时）*/
            channel.queueDeclare(QUEUE_NAME, false, false, true, null);
            //把队列绑定到交换器
            channel.queueBind(QUEUE_NAME, ExchangeType.DIRECT.getName(), "boys");
            channel.queueDeclare(QUEUE_NAME2, false, false, true, null);
            channel.queueBind(QUEUE_NAME2, ExchangeType.DIRECT.getName(), "girls");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    /**
     * 消费消息
     *
     * @param msg
     * @throws IOException
     * @throws TimeoutException
     */
    public static void receive() throws IOException, TimeoutException {

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body);
                System.out.println("消费端1获取到消息：" + msg);
            }
        };
        Consumer consumer2 = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body);
                System.out.println("消费端2获取到消息：" + msg);
            }
        };
        /*1.队列名称；2.是否自动发送ack；3.消费者*/
        channel.basicConsume(QUEUE_NAME, true, consumer);
        channel.basicConsume(QUEUE_NAME2, true, consumer2);
    }

    public static void main(String[] args) {
        try {
            receive();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}

