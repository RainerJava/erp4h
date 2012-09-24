/**
 * 
 */
package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import interfaces.INotify;
import interfaces.ISystemModel;

/**
 * @author dntuan
 * 
 */
public class SystemModel extends UnicastRemoteObject implements ISystemModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2149564495540834870L;

	protected SystemModel() throws RemoteException {
		super();
	}

	/*
	 * find M_CTL by USERID and MTN_FLG
	 * 
	 * @see interfaces.ISystemModel#getMctlVoList(java.lang.String,
	 * java.lang.String, interfaces.INotify)
	 */
	@Override
	public void getMctlVoList(String userid, String mtnFlg, INotify n)
			throws RemoteException {

	}

}
