package com.mike.rabbitmq.commons;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/*
  Author:Mike
  创建时间：2019/12/22
  描述：
*/
public class ConnectionUtil {

    /**
     * 获取连接对象
     *
     * @return 连接对象
     * @throws IOException
     * @throws TimeoutException
     */
    public static Connection getConnection() throws IOException, TimeoutException {
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
        return cnn;
    }
}
