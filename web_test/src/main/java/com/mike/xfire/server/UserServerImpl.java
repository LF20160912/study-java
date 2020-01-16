package com.mike.xfire.server;

import com.mike.xfire.domain.User;
import org.springframework.stereotype.Service;

/*
  Author:Mike
  创建时间：2019/11/4
  描述：
*/
@Service("userService")
public class UserServerImpl implements UserService {
    @Override
    public User addUser(User user) {
        user.setUserName("新增->" + user.getUserName());
        return user;
    }

    @Override
    public User get(int userid) {
        User user = new User();
        user.setUserId(userid);
        user.setUserName("如约而至");
        return user;
    }
}
