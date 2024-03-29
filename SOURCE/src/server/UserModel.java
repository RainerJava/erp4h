package server;

import interfaces.INotify;
import interfaces.IUserModel;
import interfaces.User;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fas.dao.MEmpDao;
import com.fas.vo.MEmp;

public class UserModel extends UnicastRemoteObject implements IUserModel {

	/**
* 
*/
	private static final long serialVersionUID = -5996768756787927385L;

	protected UserModel() throws RemoteException {
		super();
		System.out.println(new Date());
	}

	MEmp obj;
	List<User> userList;

	@Override
	public void login(String UserName, String Password, INotify n)
			throws RemoteException {
		System.out.println("check user " + UserName);
		MEmpDao dao = new MEmpDao();
		obj = dao.findUniqueBy("empUser", UserName);
		if (obj != null && !obj.getPwd().trim().equals(Password))
		{
			System.out.print(UserName + " login failt!");
			obj = null;
		}
		if (n != null)
			n.done("login");
		else
			System.out.print(obj);
	}

	@Override
	public MEmp loginCallback() throws RemoteException {
		// TODO Auto-generated method stub
		return obj;
	}

	@Override
	public void search(String UserName, INotify n) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			userList = new ArrayList<User>();
			userList.add(new User("a", "x"));
			userList.add(new User("b", "y"));
			Thread.sleep(300);
			n.done("search");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<User> searchCallback() throws RemoteException {
		// TODO Auto-generated method stub
		return userList;
	}

}
