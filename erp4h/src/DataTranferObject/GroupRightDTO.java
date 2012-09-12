package DataTranferObject;

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

	public int getPhanHeID() {
		return PhanHeID;
	}

	public void setPhanHeID(int phanHeID) {
		PhanHeID = phanHeID;
	}

	public int getGroupID() {
		return GroupID;
	}

	public void setGroupID(int groupID) {
		GroupID = groupID;
	}

	public String getGroupRight() {
		return GroupRight;
	}

	public void setGroupRight(String groupRight) {
		GroupRight = groupRight;
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
	
	public String toString(){
		return GroupRight;
	}
}
