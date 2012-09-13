package DataTranferObject;

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

	public int getGroupID() {
		return GroupID;
	}

	public void setGroupID(int groupID) {
		GroupID = groupID;
	}

	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}

	public Timestamp getCreateDate() {
		return CreateDate;
	}

	public void setCreateDate(Timestamp createDate) {
		CreateDate = createDate;
	}

	public String getOwner() {
		return Owner;
	}

	public void setOwner(String owner) {
		Owner = owner;
	}

	public boolean isStatus() {
		return Status;
	}

	public void setStatus(boolean status) {
		Status = status;
	}
}
