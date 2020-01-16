package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;

/*
  Author:Mike
  创建时间：2019/11/3`
  描述：
*/
public interface UserDAO extends Remote {
    String addUser(String name) throws RemoteException;
}
