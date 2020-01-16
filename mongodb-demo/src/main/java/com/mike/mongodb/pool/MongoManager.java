package com.mike.mongodb.pool;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

/*
  Author:Mike
  创建时间：2020/1/16
  描述：
*/
public class MongoManager {
    private static MongoClient mongoClient = null;

    //对mongoClient初始化
    private static void init() {
        //连接池
        MongoClientOptions.Builder builder = new MongoClientOptions.Builder();//选项构建者
        builder.connectTimeout(5000);//设置连接超时时间
        builder.socketTimeout(5000);//读取数据的超时时间
        builder.connectionsPerHost(30);//每个地址最大数
        builder.writeConcern(WriteConcern.NORMAL);//写入策略，仅抛出网络异常
        MongoClientOptions options = builder.build();
        mongoClient = new MongoClient("192.168.3.102", options);
    }

    public static MongoDatabase getDatabase() {
        if (mongoClient == null) {
            init();
        }
        return mongoClient.getDatabase("mydb");
    }
}
