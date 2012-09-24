package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IMasterModel extends Remote {

	/* Name */
	public void getNameList(String UserName, String Password, INotify n)
			throws RemoteException;

	public List<String> getNameListCallback() throws RemoteException;
}
