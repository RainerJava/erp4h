package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import com.fas.vo.MEmp;
import com.fas.vo.user.LoginUser;

public interface IUserModel extends Remote {
    public void login(String UserName, String Password, INotify n) throws RemoteException;
    public MEmp loginCallback() throws RemoteException;
    
    public void search(String UserName, INotify n) throws RemoteException;
    public List<User> searchCallback() throws RemoteException;
}
