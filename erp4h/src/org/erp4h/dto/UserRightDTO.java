package org.erp4h.dto;

import java.sql.Timestamp;

public class UserRightDTO {
	private int PhanHeID;
	private String UserID;
	private String UserRight;
	private Timestamp CreateDate;
	private String Owner;

	public UserRightDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public UserRightDTO(int phanHeID, String userID, String userRight,
			Timestamp createDate, String owner) {
		super();
		PhanHeID = phanHeID;
		UserID = userID;
		UserRight = userRight;
		CreateDate = createDate;
		Owner = owner;
	}

	public Timestamp getCreateDate() {
		return CreateDate;
	}
	public String getOwner() {
		return Owner;
	}
	public int getPhanHeID() {
		return PhanHeID;
	}
	public String getUserID() {
		return UserID;
	}
	public String getUserRight() {
		return UserRight;
	}
	public void setCreateDate(Timestamp createDate) {
		CreateDate = createDate;
	}
	public void setOwner(String owner) {
		Owner = owner;
	}
	public void setPhanHeID(int phanHeID) {
		PhanHeID = phanHeID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	public void setUserRight(String userRight) {
		UserRight = userRight;
	}

	@Override
	public String toString() {
		return UserRight;
	}
}
