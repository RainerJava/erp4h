package DataTranferObject;


import java.sql.Timestamp;
import java.util.Date;

public class UserDTO {
	private int LoginID;
	private String Password;
	private String PWDLevel2;
	private String UserName;
	private String Email;
	private Timestamp CreatedDate;
	private Boolean LockedUser;
	private Date LockedDate;
	private String LockedReason;
	private Timestamp LastLogIn;
	private Timestamp LastChangedPassword;
	private Date DeadlineOfUsing;
	private GroupDTO Group;
	private PhanHeDTO PhanHe;
	
	public int getLoginID() {
		return LoginID;
	}
	public void setLoginID(int loginID) {
		LoginID = loginID;
	}
	
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	
	public String getPWDLevel2() {
		return PWDLevel2;
	}
	public void setPWDLevel2(String pWDLevel2) {
		PWDLevel2 = pWDLevel2;
	}
	
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	
	public Timestamp getCreatedDate() {
		return CreatedDate;
	}
	public void setCreatedDate(java.sql.Timestamp createdDate) {
		CreatedDate = createdDate;
	}
	
	public Boolean getLockedUser() {
		return LockedUser;
	}
	public void setLockedUser(Boolean lockedUser) {
		LockedUser = lockedUser;
	}
	
	public Date getLockedDate() {
		return LockedDate;
	}
	public void setLockedDate(Date lockedDate) {
		LockedDate = lockedDate;
	}
	
	public String getLockedReason() {
		return LockedReason;
	}
	public void setLockedReason(String lockedReason) {
		LockedReason = lockedReason;
	}
	
	public Timestamp getLastLogIn() {
		return LastLogIn;
	}
	public void setLastLogIn(Timestamp lastLogIn) {
		LastLogIn = lastLogIn;
	}
	
	public Timestamp getLastChangedPassword() {
		return LastChangedPassword;
	}
	public void setLastChangedPassword(Timestamp lastChangedPassword) {
		LastChangedPassword = lastChangedPassword;
	}
	
	public Date getDeadlineOfUsing() {
		return DeadlineOfUsing;
	}
	public void setDeadlineOfUsing(Date deadlineOfUsing) {
		DeadlineOfUsing = deadlineOfUsing;
	}
	
	public GroupDTO getGroup() {
		return Group;
	}
	public void setGroup(GroupDTO Group) {
		this.Group = Group;
	}
	public PhanHeDTO getPhanHe() {
		return PhanHe;
	}
	public void setPhanHe(PhanHeDTO phanHe) {
		PhanHe = phanHe;
	}
}