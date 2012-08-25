package DataTranferObject;

import java.util.HashSet;

/**
 * @author HieuLV
 *
 */
public class GroupDTO {
	private int GroupID;
	private String GroupName;
	private String Note;
	private boolean IsAdmin;
	private HashSet<UserDTO> User;

	int getGroupID() {
		return GroupID;
	}
	void setGroupID(int groupID) {
		GroupID = groupID;
	}
	
	String getGroupName() {
		return GroupName;
	}
	void setGroupName(String groupName) {
		GroupName = groupName;
	}
	
	String getNote() {
		return Note;
	}
	void setNote(String note) {
		Note = note;
	}
	
	boolean isIsAdmin() {
		return IsAdmin;
	}
	void setIsAdmin(boolean isAdmin) {
		IsAdmin = isAdmin;
	}
//-------------------------------------
	HashSet<UserDTO> getUser() {
		if(User==null)
			User=new HashSet<UserDTO>();
		return User;
	}
	void setUser(HashSet<UserDTO> user) {
		User = user;
	}
//-------------------------------------
}