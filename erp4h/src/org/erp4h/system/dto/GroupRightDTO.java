package org.erp4h.system.dto;

import java.sql.Timestamp;

public class GroupRightDTO {
	int PhanHeID;
	int GroupID;
	String GroupRight;
	Timestamp CreateDate;
	String Owner;

	public GroupRightDTO() {
		super();
	}

	public GroupRightDTO(int PhanHeID, int GroupID, String GroupRight,
			Timestamp CreateDate, String Owner) {
		this.PhanHeID=PhanHeID;
		this.GroupID=GroupID;
		this.GroupRight=GroupRight;
		this.CreateDate=CreateDate;
		this.Owner=Owner;
	}

	public Timestamp getCreateDate() {
		return CreateDate;
	}

	public int getGroupID() {
		return GroupID;
	}

	public String getGroupRight() {
		return GroupRight;
	}

	public String getOwner() {
		return Owner;
	}

	public int getPhanHeID() {
		return PhanHeID;
	}

	public void setCreateDate(Timestamp createDate) {
		CreateDate = createDate;
	}

	public void setGroupID(int groupID) {
		GroupID = groupID;
	}

	public void setGroupRight(String groupRight) {
		GroupRight = groupRight;
	}

	public void setOwner(String owner) {
		Owner = owner;
	}

	public void setPhanHeID(int phanHeID) {
		PhanHeID = phanHeID;
	}
	
	@Override
	public String toString(){
		return GroupRight;
	}
}
