package com.mike.xfire.domain;

/*
  Author:Mike
  创建时间：2019/11/4
  描述：
*/
public class User {
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private int userId;
    private String userName;
}
