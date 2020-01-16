package rmi;

import dao.UserDAO;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/*
  Author:Mike
  创建时间：2019/11/3
  描述：
*/
public class RmiClient {
    public static void main(String[] args) {
        try {
            UserDAO dao= (UserDAO) Naming.lookup("rmi://localhost:8888/user");
            System.out.println(dao.addUser("Mike"));
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
