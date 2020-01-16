package com.mike.study.Nativenal.domain;

import java.io.Serializable;
import java.util.Date;

/*
  Author:Mike
  创建时间：2019/11/1
  描述：用户类
*/
public class User implements Serializable {

    private Integer userId;

    private String name;

    private Date birth;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", birth=" + birth +
                '}';
    }
}
