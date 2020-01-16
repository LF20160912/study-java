package com.mike.xfire.server;

import com.mike.xfire.domain.User;

/*
  Author:Mike
  创建时间：2019/11/4
  描述：
*/
public interface UserService {
    User addUser(User user);
    User get(int userid);
}
