package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface INotify extends Remote {
	public void done(String funcCode) throws RemoteException;
}
