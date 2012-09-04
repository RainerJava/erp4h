package DataTranferObject;

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

	public int getPhanHeID() {
		return PhanHeID;
	}
	public void setPhanHeID(int phanHeID) {
		PhanHeID = phanHeID;
	}
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	public String getUserRight() {
		return UserRight;
	}
	public void setUserRight(String userRight) {
		UserRight = userRight;
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

	@Override
	public String toString() {
		return UserRight;
	}
}
