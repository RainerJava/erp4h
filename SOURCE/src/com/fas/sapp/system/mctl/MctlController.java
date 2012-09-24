/**
 * 
 */
package com.fas.sapp.system.mctl;

import interfaces.INotify;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author dntuan
 */
public class MctlController extends UnicastRemoteObject implements INotify {

	/**
	 * 
	 */
	private static final long serialVersionUID = -703134328979114831L;

	protected MctlController() throws RemoteException {
		super();
	}

	@Override
	public void done(String funcCode) throws RemoteException {
	}

}
