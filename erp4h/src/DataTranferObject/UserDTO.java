package DataTranferObject;


import java.sql.Timestamp;

public class UserDTO {
	private String UserID;
	private String Password;
	private String PWDLevel2;
	private String UserName;
	private String Email;
	private Boolean LockedUser;
	private Timestamp LockedDate;
	private String LockedReason;
	private Timestamp LastLogIn;
	private Timestamp LastChangedPassword;
	private Timestamp DeadlineOfUsing;
	private String NhanVienID;
	private Timestamp CreatedDate;
	private String Owner;
	//List PhanHeID;
	public UserDTO(){
		super();
	}
	public UserDTO(	
			String UserID,
			String Password,
			String PWDLevel2,
			String UserName,
			String Email,
			Timestamp CreatedDate,
			Boolean LockedUser,
			Timestamp LockedDate,
			String LockedReason,
			Timestamp LastLogIn,
			Timestamp LastChangedPassword,
			Timestamp DeadlineOfUsing,
			String NhanVienID){
		this.UserID=UserID;
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
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String loginID) {
		UserID = loginID;
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
	
	public Timestamp getLockedDate() {
		return LockedDate;
	}
	public void setLockedDate(java.sql.Timestamp lockedDate) {
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
	
	public Timestamp getDeadlineOfUsing() {
		return DeadlineOfUsing;
	}
	public void setDeadlineOfUsing(Timestamp deadlineOfUsing) {
		DeadlineOfUsing = deadlineOfUsing;
	}
	public String getNhanVienID() {
		return NhanVienID;
	}
	public void setNhanVienID(String nhanVienID) {
		NhanVienID = nhanVienID;
	}
	public String getOwner() {
		return Owner;
	}
	public void setOwner(String owner) {
		Owner = owner;
	}
	
	@Override
	public String toString(){
		return UserName;
	}
}