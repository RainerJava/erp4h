package org.erp4h.dto;

import java.sql.Timestamp;

public class GroupUserDTO {
	int GroupID;
	String UserID;
	Timestamp CreateDate;
	String Owner;
	boolean Status;

	public GroupUserDTO() {
		super();
	}

	public GroupUserDTO(int GroupID, String UserID, Timestamp CreateDate,
			String Owner, boolean Status) {
		super();
		this.GroupID=GroupID;
		this.UserID=UserID;
		this.CreateDate=CreateDate;
		this.Owner=Owner;
		this.Status=Status;
	}

	public Timestamp getCreateDate() {
		return CreateDate;
	}

	public int getGroupID() {
		return GroupID;
	}

	public String getOwner() {
		return Owner;
	}

	public String getUserID() {
		return UserID;
	}

	public boolean isStatus() {
		return Status;
	}

	public void setCreateDate(Timestamp createDate) {
		CreateDate = createDate;
	}

	public void setGroupID(int groupID) {
		GroupID = groupID;
	}

	public void setOwner(String owner) {
		Owner = owner;
	}

	public void setStatus(boolean status) {
		Status = status;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}
}
