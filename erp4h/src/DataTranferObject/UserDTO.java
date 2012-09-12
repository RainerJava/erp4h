/**
 * Mo ta: 
 */
package DataTranferObject;
import java.sql.Timestamp;
/**
 * 
 * @author hieulv
 * @@Careate_date 01/07/2012
 */
public class UserDTO {
	  String UserID;
	  int GroupID;
	  String Password;
	  String PWDLevel2;
	  String UserName;
	  String Email;
	  boolean LockedUser;
	  Timestamp LockedDate;
	  String LockedReason;
	  Timestamp LastLogIn;
	  Timestamp LastChangedPassword;
	  Timestamp DeadlineOfUsing;
	  boolean Delegate;
	  String NhanVienID;
	  Timestamp CreatedDate;
	  String Owner;
	public UserDTO(){
		super();
	}
	
	public UserDTO(	
			  String UserID,
			  int GroupID,
			  String Password,
			  String PWDLevel2,
			  String UserName,
			  String Email,
			  boolean LockedUser,
			  Timestamp LockedDate,
			  String LockedReason,
			  Timestamp LastLogIn,
			  Timestamp LastChangedPassword,
			  Timestamp DeadlineOfUsing,
			  boolean Delegate,
			  String NhanVienID,
			  Timestamp CreatedDate,
			  String Owner)	{
		  this.UserID=UserID;
		  this.GroupID=GroupID;
		  this.Password=Password;
		  this.PWDLevel2=PWDLevel2;
		  this.UserName=UserName;
		  this.Email=Email;
		  this.LockedUser=LockedUser;
		  this.LockedDate=LockedDate;
		  this.LockedReason=LockedReason;
		  this.LastLogIn=LastLogIn;
		  this.LastChangedPassword=LastChangedPassword;
		  this.DeadlineOfUsing=DeadlineOfUsing;
		  this.Delegate=Delegate;
		  this.NhanVienID=NhanVienID;
		  this.CreatedDate=CreatedDate;
		  this.Owner=Owner;
	}
	
	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}

	public int getGroupID() {
		return GroupID;
	}

	public void setGroupID(int groupID) {
		GroupID = groupID;
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

	public boolean isLockedUser() {
		return LockedUser;
	}

	public void setLockedUser(boolean lockedUser) {
		LockedUser = lockedUser;
	}

	public Timestamp getLockedDate() {
		return LockedDate;
	}

	public void setLockedDate(Timestamp lockedDate) {
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

	public boolean isDelegate() {
		return Delegate;
	}

	public void setDelegate(boolean delegate) {
		Delegate = delegate;
	}

	public String getNhanVienID() {
		return NhanVienID;
	}

	public void setNhanVienID(String nhanVienID) {
		NhanVienID = nhanVienID;
	}

	public Timestamp getCreatedDate() {
		return CreatedDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		CreatedDate = createdDate;
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