package com.mike.cxf.domain;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/*
  Author:Mike
  创建时间：2019/11/5
  描述：bean对象
*/
@XmlRootElement//如果需要返回xml，必须次注解
public class User {
    private Integer id;
    private String name;
    private int age;
    private Date birth;

    public  User(){}

    public User(Integer id, String name, int age, Date birth) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.birth = birth;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }
}
