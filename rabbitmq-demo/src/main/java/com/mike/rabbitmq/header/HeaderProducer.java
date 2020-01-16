package com.mike.rabbitmq.header;

import com.mike.rabbitmq.commons.ConnectionUtil;
import com.mike.rabbitmq.commons.ExchangeType;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

/*
  Author:Mike
  创建时间：2019/12/22
  描述：
*/
public class HeaderProducer {
    private final static String EXCHANGE_NAME = "header-Exchage";
    private final static String QUEUE_NAME = "header-queue";

    public static void main(String[] args) throws Exception {
        Connection cnn = ConnectionUtil.getConnection();
        Channel channel = cnn.createChannel();
        //声明转发器和类型
        channel.exchangeDeclare(EXCHANGE_NAME, ExchangeType.HEADERS.getName(), false, true, null);
        channel.queueDeclare(QUEUE_NAME, false, false, true, null);

        String msg = new Date().toLocaleString() + ":log something";

        Map<String, Object> headers = new Hashtable<String, Object>();
        headers.put("aa", 123456);
        headers.put("bb", "abcd");
        AMQP.BasicProperties.Builder properties = new AMQP.BasicProperties.Builder();
        properties.headers(headers);

        // 为转发器指定队列，设置binding 绑定header键值对
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "", headers);
        // 指定消息发送到的转发器,绑定键值对headers键值对
        channel.basicPublish(EXCHANGE_NAME, "", properties.build(), msg.getBytes());

        System.out.println("sent massage -->" + msg);
        channel.close();
        cnn.close();
    }
}
