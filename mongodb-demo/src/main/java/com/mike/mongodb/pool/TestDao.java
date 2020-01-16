package com.mike.mongodb.pool;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Date;

/*
  Author:Mike
  创建时间：2020/1/16
  描述：
*/
public class TestDao {
    public void save(String name, String sex, double age, String address) {
        MongoDatabase database = MongoManager.getDatabase();
        MongoCollection<Document> collection = database.getCollection("testCollection");
        Document docment = new Document();
        docment.put("name", name);
        docment.put("sex", sex);
        docment.put("age", age);
        docment.put("address", address);
        collection.insertOne(docment);
    }


    public static void main(String[] args) {
        long startTime = new Date().getTime();//开始时间

        TestDao testDao = new TestDao();
        for (int i = 0; i < 50000; i++) {
            testDao.save("测试" + i, "女", 25.0, "测试地址" + i);
        }
        long endTime = new Date().getTime();//完成时间
        System.out.println("普通完成时间：" + (endTime - startTime) + "毫秒");//普通完成时间：9322毫秒,连接池完成时间：2911毫秒
    }
}