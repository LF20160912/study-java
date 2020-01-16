package com.mike.rabbitmq.workqueue;

import com.mike.rabbitmq.commons.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/*
  Author:Mike
  创建时间：2019/12/22
  描述：
*/
public class Sender {
    private static String TASK_QUEUE ="task-queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection cnn = ConnectionUtil.getConnection();
        Channel channel = cnn.createChannel();
        channel.queueDeclare(TASK_QUEUE,false,false,false,null);

        //发送内容
        for (int i = 0; i < 100; i++) {
            channel.basicPublish("",TASK_QUEUE,null,("订单信息"+i).getBytes());
        }
        channel.close();
        cnn.close();
    }
}
