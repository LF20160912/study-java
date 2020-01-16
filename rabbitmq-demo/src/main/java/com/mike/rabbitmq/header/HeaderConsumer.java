package com.mike.rabbitmq.header;

import com.mike.rabbitmq.commons.ConnectionUtil;
import com.mike.rabbitmq.commons.ExchangeType;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/*
  Author:Mike
  创建时间：2019/12/22
  描述：Header模式消费者
*/
public class HeaderConsumer {
    private static final  String EXCHANGE_NAME= "header-Exchage";
    private static final String QUEUE_NAME="header-queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection cnn = ConnectionUtil.getConnection();
        final Channel channel = cnn.createChannel();

        //声明转发器和类型
        channel.exchangeDeclare(EXCHANGE_NAME, ExchangeType.HEADERS.getName(),false,true,null);
        channel.queueDeclare(QUEUE_NAME,false,false,true,null);

        Map<String,Object> headers= new Hashtable<String, Object>();
        headers.put("x-match","any");
        headers.put("aa", 123456);
        headers.put("bb", "abcd");
        //为转发器指定队列，设置binding和绑定header键值对
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"",headers);
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("收到的消息为："+new String(body));
                //手动确认消息， false代表接收消息， true代表拒绝消息
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };
        channel.basicConsume(QUEUE_NAME,false,consumer);
    }
}
