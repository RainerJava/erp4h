package interfaces;

import java.io.Serializable;

public class User implements Serializable {
	public String UserName;
	public String Password;

	public User(String u, String p) {
		UserName = u;
		Password = p;
	}
}
