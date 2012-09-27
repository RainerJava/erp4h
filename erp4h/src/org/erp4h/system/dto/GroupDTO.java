package org.erp4h.system.dto;

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
	String getGroupName() {
		return GroupName;
	}
	
	String getNote() {
		return Note;
	}
	//-------------------------------------
	HashSet<UserDTO> getUser() {
		if(User==null)
			User=new HashSet<UserDTO>();
		return User;
	}
	
	boolean isIsAdmin() {
		return IsAdmin;
	}
	void setGroupID(int groupID) {
		GroupID = groupID;
	}
	
	void setGroupName(String groupName) {
		GroupName = groupName;
	}
	void setIsAdmin(boolean isAdmin) {
		IsAdmin = isAdmin;
	}
void setNote(String note) {
		Note = note;
	}
	void setUser(HashSet<UserDTO> user) {
		User = user;
	}
//-------------------------------------
}