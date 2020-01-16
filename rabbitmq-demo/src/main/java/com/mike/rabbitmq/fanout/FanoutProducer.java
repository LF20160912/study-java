package com.mike.rabbitmq.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/*
  Author:Mike
  创建时间：2019/12/21
  描述：fanout模式消息生产者
*/
public class FanoutProducer {
    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args) throws Exception{
        ConnectionFactory cnnFactory = new ConnectionFactory();
        //rabbitmq监听ip
        cnnFactory.setHost("192.168.3.103");
        //rabbitmq监听端口
        cnnFactory.setPort(5672);
        cnnFactory.setVirtualHost("my_vhost");
        //设置访问的用户
        cnnFactory.setUsername("admin");
        cnnFactory.setPassword("admin");

        Connection cnn = cnnFactory.newConnection();
        Channel channel = cnn.createChannel();

        //声明路由名字和类型
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        String msg = makeMsg(args);

        channel.basicPublish(EXCHANGE_NAME, "", null, msg.getBytes());
        System.out.println("set msg is：" + msg);
        channel.close();
        cnn.close();
    }

    private static String makeMsg(String[] msgs) {
        if (msgs.length < 1) {
            return "这是fanout模式生产的消息";
        } else {
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < msgs.length; i++) {
                buffer.append(msgs[i]);
            }
            return buffer.toString();
        }
    }
}
