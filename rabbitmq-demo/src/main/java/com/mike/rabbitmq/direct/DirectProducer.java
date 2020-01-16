package com.mike.rabbitmq.direct;

import com.mike.rabbitmq.commons.ExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/*
  Author:Mike
  创建时间：2019/12/21
  描述：p2p
*/
public class DirectProducer {
    private static final String QUEUE_NAME="BOYS_QN";
    private static final String QUEUE_NAME2="GIRLS_QN";

    private static ConnectionFactory factoryCnn;
    private static Connection cnn;
    private static Channel channel;

    static {

        factoryCnn=new ConnectionFactory();
        factoryCnn.setHost("192.168.3.103");
        factoryCnn.setPort(5672);
        factoryCnn.setVirtualHost("/");
        factoryCnn.setUsername("rabbit");
        factoryCnn.setPassword("rabbit");
        try {
            cnn=factoryCnn.newConnection();
            channel=cnn.createChannel();
            channel.exchangeDeclare(ExchangeType.DIRECT.getName(),ExchangeType.DIRECT.getType());
            /*1.队列名称，2.是否可持久化，3.是否排他列队，4.是否自动删除（空闲时）*/
            channel.queueDeclare(QUEUE_NAME,false,false,true,null);
            //把队列绑定到交换器
            channel.queueBind(QUEUE_NAME,ExchangeType.DIRECT.getName(),"boys");

            channel.queueDeclare(QUEUE_NAME2 ,false,false,true,null);
            channel.queueBind(QUEUE_NAME2,ExchangeType.DIRECT.getName(),"girls");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    public static void send(String msg) throws IOException, TimeoutException {
        try {
            /*1.交换机名称；2.路由关键字即routing key；3.配置信息，contentType、contentEncoding等；4.消息，字节数组格式*/
            channel.basicPublish(ExchangeType.DIRECT.getName(),"girls", MessageProperties.MINIMAL_BASIC,msg.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            channel.close();
            cnn.close();
        }
    }

    public static void main(String[] args){
        try {
            send("hello girls");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}

