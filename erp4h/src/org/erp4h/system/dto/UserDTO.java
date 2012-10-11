/**
 * Mo ta: 
 */
package org.erp4h.system.dto;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 
 * @author hieulv
 * @@Careate_date 01/07/2012
 */
public class UserDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String UserID;
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

	public UserDTO() {
		super();
	}

	public UserDTO(String UserID, String Password, String PWDLevel2,
			String UserName, String Email, boolean LockedUser,
			Timestamp LockedDate, String LockedReason, Timestamp LastLogIn,
			Timestamp LastChangedPassword, Timestamp DeadlineOfUsing,
			boolean Delegate, String NhanVienID, Timestamp CreatedDate,
			String Owner) {
		this.UserID = UserID;
		this.Password = Password;
		this.PWDLevel2 = PWDLevel2;
		this.UserName = UserName;
		this.Email = Email;
		this.LockedUser = LockedUser;
		this.LockedDate = LockedDate;
		this.LockedReason = LockedReason;
		this.LastLogIn = LastLogIn;
		this.LastChangedPassword = LastChangedPassword;
		this.DeadlineOfUsing = DeadlineOfUsing;
		this.Delegate = Delegate;
		this.NhanVienID = NhanVienID;
		this.CreatedDate = CreatedDate;
		this.Owner = Owner;
	}

	public Timestamp getCreatedDate() {
		return CreatedDate;
	}

	public Timestamp getDeadlineOfUsing() {
		return DeadlineOfUsing;
	}

	public String getEmail() {
		return Email;
	}

	public Timestamp getLastChangedPassword() {
		return LastChangedPassword;
	}

	public Timestamp getLastLogIn() {
		return LastLogIn;
	}

	public Timestamp getLockedDate() {
		return LockedDate;
	}

	public String getLockedReason() {
		return LockedReason;
	}

	public String getNhanVienID() {
		return NhanVienID;
	}

	public String getOwner() {
		return Owner;
	}

	public String getPassword() {
		return Password;
	}

	public String getPWDLevel2() {
		return PWDLevel2;
	}

	public String getUserID() {
		return UserID;
	}

	public String getUserName() {
		return UserName;
	}

	public boolean isDelegate() {
		return Delegate;
	}

	public boolean isLockedUser() {
		return LockedUser;
	}

	public void setCreatedDate(Timestamp createdDate) {
		CreatedDate = createdDate;
	}

	public void setDeadlineOfUsing(Timestamp deadlineOfUsing) {
		DeadlineOfUsing = deadlineOfUsing;
	}

	public void setDelegate(boolean delegate) {
		Delegate = delegate;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public void setLastChangedPassword(Timestamp lastChangedPassword) {
		LastChangedPassword = lastChangedPassword;
	}

	public void setLastLogIn(Timestamp lastLogIn) {
		LastLogIn = lastLogIn;
	}

	public void setLockedDate(Timestamp lockedDate) {
		LockedDate = lockedDate;
	}

	public void setLockedReason(String lockedReason) {
		LockedReason = lockedReason;
	}

	public void setLockedUser(boolean lockedUser) {
		LockedUser = lockedUser;
	}

	public void setNhanVienID(String nhanVienID) {
		NhanVienID = nhanVienID;
	}

	public void setOwner(String owner) {
		Owner = owner;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public void setPWDLevel2(String pWDLevel2) {
		PWDLevel2 = pWDLevel2;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public boolean equals(Object other) {
		return (other instanceof UserDTO) && (UserID != null)
	             ? UserID.equals(((UserDTO) other).UserID)
	             : (other == this);
	}
	public int hashCode() {
        return (UserID != null) 
             ? (this.getClass().hashCode() + UserID.hashCode()) 
             : super.hashCode();
    }
	@Override
	public String toString() {
		return UserName;
	}

}