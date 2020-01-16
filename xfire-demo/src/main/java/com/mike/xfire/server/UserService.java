package com.mike.xfire.server;

import com.mike.xfire.domain.User;

import java.util.List;

/*
  Author:Mike
  创建时间：2019/11/4
  描述：
*/
public interface UserService {
    void addUser(User user);

    void delUser(Integer userId);

    User getUserById(int userId);

    List<User> getUsers();
}
