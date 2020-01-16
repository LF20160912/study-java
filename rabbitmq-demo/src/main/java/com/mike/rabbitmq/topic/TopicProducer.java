package com.mike.rabbitmq.topic;

import com.mike.rabbitmq.commons.ConnectionUtil;
import com.mike.rabbitmq.commons.ExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/*
  Author:Mike
  创建时间：2019/12/22
  描述：Topic模式
*/
public class TopicProducer {
    private static final String QUEUE_NAME1 = "boys";
    private static final String QUEUE_NAME2 = "girls";

    private static Connection cnn;
    private static Channel channel;

    static {
        try {
            //创建连接对象和管道
            cnn = ConnectionUtil.getConnection();
            channel = cnn.createChannel();

            //声明交换机
            channel.exchangeDeclare(ExchangeType.TOPIC.getName(), ExchangeType.TOPIC.getType());
            //声明队列匹配"boys.*.*"的队列
            channel.queueDeclare(QUEUE_NAME1, false, false, true, null);
            channel.queueBind(QUEUE_NAME1, ExchangeType.TOPIC.getName(), "boys.#");
            //声明队列匹配"*.*.grils"的队列
            channel.queueDeclare(QUEUE_NAME2, false, false, true, null);
            channel.queueBind(QUEUE_NAME2, ExchangeType.TOPIC.getName(), "*.*.girls");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    private static void sentMsg(String routingKey, String msg) throws IOException {
        /*1.交换机名称；2.路由关键字即routing key；3；4.消息，字节数组格式*/
        channel.basicPublish(ExchangeType.TOPIC.getName(), routingKey, null, msg.getBytes());
    }

    public static void main(String[] args) {
        try {
            sentMsg("boys.like.girls", "routing key =boys.#-->" + "测试#1");
            //sentMsg("dirty.a.girls", "routing key =dirty.a.girls-->" + "I am girl");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
