package BusinessLogicLayer;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.HashSet;

import DataAccessLayer.MySQLConnectUnit;
import DataTranferObject.UserDTO;

public class UserBLL {
	MySQLConnectUnit connect;
	
	public UserBLL(){
		this.connect=DataAccessLayer.DataAccess.getDAL();
	}
	
	public void Delete(HashSet<UserDTO>HS_User) throws Exception{
		for(UserDTO DTO_User:HS_User)
			this.Delete(DTO_User);		
	}
	
	public void Delete(UserDTO dtoUser) throws Exception{
		this.connect.Delete("tblUser", "UserID"+dtoUser.getUserID());		
	}
	
	public UserDTO getByID(String UserID) throws Exception{
		HashSet<UserDTO> hsUser=this.getUser("UserID="+UserID);
		if(hsUser.size()>0)
			return hsUser.toArray(new UserDTO[hsUser.size()])[0];
		return null;
	}
	
	public HashSet<UserDTO>getUser() throws Exception{
		return getUser(null);
	}
	
	public HashSet<UserDTO>getUser(String Condition) throws Exception{
		return getUser(Condition, null);
	}
	
	public HashSet<UserDTO> getUser(String Condition, String OrderBy) throws Exception{
		ResultSet rs=this.connect.Select("tblUser", Condition, OrderBy);
		HashSet<UserDTO>hsUser=new HashSet<UserDTO>();
		while(rs.next()){
			UserDTO dtoUser=new UserDTO();
			dtoUser.setUserID(rs.getString("UserID"));
			dtoUser.setPassword(rs.getString("Password"));
			dtoUser.setPWDLevel2(rs.getString("PWDLevel2"));
			dtoUser.setUserName(rs.getString("UserName"));
			dtoUser.setEmail(rs.getString("Email"));
			dtoUser.setCreatedDate(rs.getTimestamp("CreatedDate"));
			dtoUser.setLockedUser(rs.getBoolean("LockedUser"));
			dtoUser.setLockedDate(rs.getTimestamp("LockedDate"));
			dtoUser.setLockedReason(rs.getString("LockedReason"));
			dtoUser.setLastLogIn(rs.getTimestamp("LastLogIn"));
			dtoUser.setLastChangedPassword(rs.getTimestamp("LastChangedPassword"));
			dtoUser.setDeadlineOfUsing(rs.getTimestamp("DeadlineOfUsing"));
			dtoUser.setNhanVienID(rs.getString("NhanVienID"));
			dtoUser.setOwner(rs.getString("Owner"));
			hsUser.add(dtoUser);
		}
		return hsUser;
	}
	
	public void Insert(HashSet<UserDTO> HS_User) throws Exception{
		for(UserDTO DTO_User:HS_User)
			this.Insert(DTO_User);
	}
	
	public void Insert(UserDTO dtoUser) throws Exception{
		HashMap<String, Object>map=new HashMap<String, Object>();
		map.put("LoginID", dtoUser.getUserID());
		map.put("Password", dtoUser.getPassword());
		map.put("PWDLevel2", dtoUser.getPWDLevel2());
		map.put("UserName", dtoUser.getUserName());
		map.put("Email", dtoUser.getEmail());
		map.put("CreatedDate", dtoUser.getCreatedDate());
		map.put("LockedUser", dtoUser.getLockedUser());
		map.put("LockedDate", dtoUser.getLockedDate());
		map.put("LockedReason", dtoUser.getLockedReason());
		map.put("LastLogIn", dtoUser.getLastLogIn());
		map.put("LastChangedPassword", dtoUser.getLastChangedPassword());
		map.put("DeadlineOfUsing", dtoUser.getDeadlineOfUsing());
		this.connect.Insert("tblUser", map);
	}
	
	public void Update(HashSet<UserDTO>HS_User) throws Exception{
		for(UserDTO DTO_User:HS_User)
			this.Update(DTO_User);
	}
	
	public void Update(UserDTO dtoUser) throws Exception{
		HashMap<String, Object>map=new HashMap<String, Object>();
		map.put("LoginID", dtoUser.getUserID());
		map.put("Password", dtoUser.getPassword());
		map.put("PWDLevel2", dtoUser.getPWDLevel2());
		map.put("UserName", dtoUser.getUserName());
		map.put("Email", dtoUser.getEmail());
		map.put("CreatedDate", dtoUser.getCreatedDate());
		map.put("LockedUser", dtoUser.getLockedUser());
		map.put("LockedDate", dtoUser.getLockedDate());
		map.put("LockedReason", dtoUser.getLockedReason());
		map.put("LastLogIn", dtoUser.getLastLogIn());
		map.put("LastChangedPassword", dtoUser.getLastChangedPassword());
		map.put("DeadlineOfUsing", dtoUser.getDeadlineOfUsing());
		this.connect.Update("tblUser", map, "LoginID"+dtoUser.getUserID());
	}
}

