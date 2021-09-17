package com.mike.morphia;

import com.mike.morphia.common.MongodbManager;
import com.mike.morphia.domain.Address;
import com.mike.morphia.domain.User;
import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.Morphia;

/*
  Author:Mike
  创建时间：2020/1/18
  描述：
*/
public class App {
    public static void main(String[] args) {
        MongoClient mongoClient = MongodbManager.createMongodbClient();
        Morphia morphia = new Morphia();

        //连接mongodb的mydb数据库
        Datastore datastore = morphia.createDatastore(mongoClient, "mydb");
        //创建用户对象
        User user = new User();
        user.setUserName("夜蒲");
        user.setAccount("111@163.com");
        //地址对象
        Address address = new Address();
        address.setCountry("中国");
        address.setProvince("广东");
        address.setCity("广州");
        user.setAddress(address);
        Key<User> save = datastore.save(user);

        System.out.println(save.toString());
        /*效果
        * {
              "_id" : ObjectId("5e21f4076793620208b7b5b4"),
              "account" : "111@163.com",
              "user_name" : "夜蒲",
              "address" : {
                "country" : "中国",
                "province" : "广东",
                "city" : "广州"
              }
            }
        * */
    }
}
