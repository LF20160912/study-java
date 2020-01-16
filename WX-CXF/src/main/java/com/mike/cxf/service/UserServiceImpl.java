package com.mike.cxf.service;

import com.mike.cxf.domain.User;
import com.mike.cxf.result.Response;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/*
  Author:Mike
  创建时间：2019/11/5
  描述：
*/
public class UserServiceImpl implements UserService {

    private static List<User> users=null;

    static {
        users= new CopyOnWriteArrayList();
        users.add(new User(1001,"zhan",12,new Date()));
        users.add(new User(1002,"中山",10,new Date()));
        users.add(new User(1003,"珠海",121,new Date()));
    }

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public Response delete(int id) {
        return null;
    }

    @Override
    public User getUser(int id) {
        return null;
    }

    @Override
    public Response add(User user) {
        return null;
    }

    @Override
    public Response update(User user) {
        return null;
    }
}
