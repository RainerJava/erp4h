package server;

import interfaces.IMasterModel;
import interfaces.INotify;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class MasterModel extends UnicastRemoteObject implements IMasterModel {

	protected MasterModel() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	List<String> nameList;
	@Override
	public void getNameList(String UserName, String Password, INotify n)
			throws RemoteException {
		// TODO Auto-generated method stub
		nameList = new ArrayList<String>();
		nameList.add("abc");
		
		n.done("getNameList");
	}

	@Override
	public List<String> getNameListCallback() throws RemoteException {
		// TODO Auto-generated method stub
		return nameList;
	}

}
