package rmi;

import dao.UserDAO;
import dao.UserDAOImpl;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/*
  Author:Mike
  创建时间：2019/11/3
  描述：
*/
public class RmiServer {
    public static void main(String[] args) throws InterruptedException, RemoteException {
        UserDAO userDAO = new UserDAOImpl();
        try {
            LocateRegistry.createRegistry(8888);
            Naming.bind("rmi://localhost:8888/user", userDAO);
            System.out.println("Server starting……");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}
