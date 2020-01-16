package com.mike.study;

import com.mike.study.Nativenal.domain.User;

import java.io.*;
import java.util.Date;

/**
 * @ProjectName: serializerDemo
 * @Auther: Mike
 * @Date: 2019/11/1 18:26
 * @Description: 使用原生方式序列化对象--不支持跨平台
 */
public class NativeSerializerDemo {
    private final static String fileName = "user-serialized";

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        serializer();
        deserializer();
    }

    //序列化
    private static void serializer() throws IOException {
        User user = new User();
        user.setBirth(new Date());
        user.setUserId(1001);
        user.setName("开车看路");

        //序列化流对象
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
        oos.writeObject(user);
        System.out.println("1、文件大小：" + new File(fileName).length());

        //再次序列化
        oos.writeObject(user);
        System.out.println("2、文件大小：" + new File(fileName).length());

//        oos.flush();
//        oos.close();

//        oos = new ObjectOutputStream(new FileOutputStream(fileName));
        User user1 = new User();
        user1.setBirth(new Date());
        user1.setUserId(20002);
        user1.setName("万圣节");

        //修改属性值后再序列化
        oos.writeObject(user1);
        System.out.println("*********修改属性后文件大小：" + new File(fileName).length());
        System.out.println("用户ID-->" + user1.getUserId());

        oos.flush();
        oos.close();
    }

    /**
     * 反序列化
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private static void deserializer() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
        User user = (User) ois.readObject();
        System.out.println("用户名-->" + user.getName());
        System.out.println("用户ID-->" + user.getUserId());
    }
}
