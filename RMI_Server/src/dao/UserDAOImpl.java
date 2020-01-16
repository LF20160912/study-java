package dao;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/*
  Author:Mike
  创建时间：2019/11/3
  描述：
*/
public class UserDAOImpl extends UnicastRemoteObject implements  UserDAO {

    public UserDAOImpl() throws RemoteException {
    }

    @Override
    public String addUser(String name) throws RemoteException {
        return "添加的用户的名称为："+name;
    }
}
