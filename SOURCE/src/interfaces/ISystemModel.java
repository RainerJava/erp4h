/**
 * 
 */
package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author dntuan
 * 
 */
public interface ISystemModel extends Remote {
	public void getMctlVoList(String userid, String mtnFlg, INotify n) throws RemoteException;
}
