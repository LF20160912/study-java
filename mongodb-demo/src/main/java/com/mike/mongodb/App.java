package com.mike.mongodb;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.*;
import org.bson.Document;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;

/*
  Author:Mike
  创建时间：2020/1/16
  描述：
*/
public class App {
    private static final String DB_NAME = "mydb";
    private static final String COLLECTION_NAME = "users";

    public static void main(String[] args) {
        //mongodbJdbc();
        mongodbClient();

    }

    /**
     * JDBC方式连接
     */
    private static void mongodbJdbc() {
        //创建Mongo对象
        Mongo mongo = new Mongo("192.168.3.102", 27017);
        //创建数据库
        DB db = new DB(mongo, DB_NAME);

        //获取一个数据集
        DBCollection collection = db.getCollection(COLLECTION_NAME);

        //新增

        //获取方法
        DBCursor listCurser = collection.find();

        //迭代器
        Iterator<DBObject> iterator = listCurser.iterator();

        //便利迭代器
        while (iterator.hasNext()) {
            BasicDBObject dbObject = (BasicDBObject) iterator.next();

            System.out.println(dbObject.getObjectId("_id"));
            System.out.println(dbObject.getString("name"));
            System.out.println(dbObject.getString("sex"));
            System.out.println(dbObject.getDouble("age"));
            System.out.println(dbObject.getString("address"));
            System.out.println("========================");
        }

        System.out.println(listCurser);
    }

    /**
     * Client方式
     */
    private static void mongodbClient() {
        mongodbClientAdd();
//        mongodbClientFind();
    }

    /**
     * 查询
     */
    private static void mongodbClientFind() {
        MongoClient mongoClient = MongoClients.create("mongodb://192.168.3.102:27017");
        //MongoClient mongoClient = new com.mongodb.MongoClient("192.168.3.102", 27017);
        MongoDatabase database = mongoClient.getDatabase(DB_NAME);
        MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);
        //查询所有
//        FindIterable<Document> list = collection.find();

        //条件查询
//        BasicDBObject bson = new BasicDBObject();
//        bson.put("name", "唐僧");

        //正则查询
        Pattern pattern = Pattern.compile("^.*男.*$");
        BasicDBObject bson = new BasicDBObject();
        bson.put("sex", pattern);

        //大于小于
//        BasicDBObject bson=new BasicDBObject("age", new BasicDBObject("$lt",20));

        //多条件连接
        //查询年龄大于等于20的
//        BasicDBObject bson1 = new BasicDBObject("age", new BasicDBObject("$gte", 20));
//        //查询年龄小于30的
//        BasicDBObject bson2 = new BasicDBObject("age", new BasicDBObject("$lt", 30));
//        //构建查询条件and
//        BasicDBObject bson = new BasicDBObject("$and", Arrays.asList(bson1, bson2));

        FindIterable<Document> list = collection.find(bson);

        for (Document doc : list) {//遍历集合中的文档输出数据
            System.out.println("name:" + doc.getString("name"));
            System.out.println("sex:" + doc.getString("sex"));
            System.out.println("age:" + doc.getDouble("age"));//默认为浮点型
            System.out.println("address:" + doc.getString("address"));
            System.out.println("--------------------------");
        }
    }

    /**
     * 新增
     */
    private static void mongodbClientAdd() {
        //获取连接
        com.mongodb.MongoClient client = new com.mongodb.MongoClient("192.168.3.102",27017);
//得到数据库
        MongoDatabase database = client.getDatabase(DB_NAME);
//得到集合封装对象
        MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);
//        MongoDatabase database = mongoClient.getDatabase(DB_NAME);
//        MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

        Map<String, Object> map=new HashMap();
        map.put("name", "铁扇公主");
        map.put("sex", "女");
        map.put("age", 35.0);
        map.put("address", "芭蕉洞");
        Document doc=new Document(map);
        collection.insertOne(doc);//插入一条记录
        System.out.println("新增记录成功");
    }
}
