package DataTranferObject;


import java.sql.Timestamp;
import java.sql.Date;

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
	private String NhanVienID;

	public UserDTO(){
		super();
	}
	public UserDTO(	
			int LoginID,
			String Password,
			String PWDLevel2,
			String UserName,
			String Email,
			Timestamp CreatedDate,
			Boolean LockedUser,
			Date LockedDate,
			String LockedReason,
			Timestamp LastLogIn,
			Timestamp LastChangedPassword,
			Date DeadlineOfUsing,
			String NhanVienID){
		this.LoginID=LoginID;
		this.Password=Password;
		this.PWDLevel2=PWDLevel2;
		this.UserName=UserName;
		this.Email=Email;
		this.CreatedDate=CreatedDate;
		this.LockedUser=LockedUser;
		this.LockedDate=LockedDate;
		this.LockedReason=LockedReason;
		this.LastLogIn=LastLogIn;
		this.LastChangedPassword=LastChangedPassword;
		this.DeadlineOfUsing=DeadlineOfUsing;
		this.NhanVienID=NhanVienID;
	}
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

	@Override
	public String toString(){
		return UserName;
	}
	public String getNhanVienID() {
		return NhanVienID;
	}
	public void setNhanVienID(String nhanVienID) {
		NhanVienID = nhanVienID;
	}
}