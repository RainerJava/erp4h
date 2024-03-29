package server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Main {
	public static void main(String[] args) throws RemoteException
	{
		try {
			LocateRegistry.createRegistry(1099);
			
			Naming.rebind("rmi://localhost:1099/user", new UserModel());
			Naming.rebind("rmi://localhost:1099/master", new MasterModel());
			Naming.rebind("rmi://localhost:1099/system", new SystemModel());
			
			System.out.println("Server start");
			
			
			// test
			//UserModel model = new UserModel();
			//model.login("nakano", "1", null);
		}
		catch (MalformedURLException ex) {
			ex.printStackTrace();
		}
	}
}
