package com.mike.xfire.server;

import com.mike.xfire.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

/*
  Author:Mike
  创建时间：2019/11/4
  描述：
*/
@Service("userService")
public class UserServerImpl implements UserService {
    @Override
    public void addUser(User user) {

    }

    public void delUser(Integer userId) {
        
    }

    public User getUserById(int userId) {
        return null;
    }

    public List<User> getUsers() {
        return null;
    }
}
