package com.mike.rabbitmq.workqueue;

import com.mike.rabbitmq.commons.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/*
  Author:Mike
  创建时间：2019/12/22
  描述：
*/
public class Receiver1 {
    private static final String TASK_QUEUE = "task-queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection cnn = ConnectionUtil.getConnection();
        final Channel channel = cnn.createChannel();
        channel.queueDeclare(TASK_QUEUE, false, false, false, null);
        channel.basicQos(5);

        //创建消费者
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者收到的内容是：" + new String(body));
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };

        channel.basicConsume(TASK_QUEUE, false, consumer);
    }
}
