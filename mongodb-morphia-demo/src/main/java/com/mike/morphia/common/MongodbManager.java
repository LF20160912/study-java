package com.mike.morphia.common;

import com.mongodb.MongoClient;

/*
  Author:Mike
  创建时间：2020/1/18
  描述：
*/
public class MongodbManager {
    private static MongoClient client;

    /**
     * 创建mongodb客户端
     * @return
     */
    public static MongoClient createMongodbClient() {
        if (client == null) {
            client = new MongoClient("192.168.3.102", 27017);
        }
        return client;
    }
}
