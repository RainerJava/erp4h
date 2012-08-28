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
	
	public void Delete(UserDTO DTO_User) throws Exception{
		this.connect.Delete("tblUser", "LoginID"+DTO_User.getLoginID());		
	}
	
	public UserDTO getByID(int id) throws Exception{
		HashSet<UserDTO>HS_User=this.getUser("LoginID="+id);
		if(HS_User.size()>0)
			return HS_User.toArray(new UserDTO[HS_User.size()])[0];
		return null;
	}
	
	public HashSet<UserDTO>getUser() throws Exception{
		return getUser(null);
	}
	
	public HashSet<UserDTO>getUser(String Condition) throws Exception{
		return getUser(Condition, null);
	}
	
	public HashSet<UserDTO>getUser(String Condition, String OrderBy) throws Exception{
		ResultSet rs=this.connect.Select("tblUser", Condition, OrderBy);
		HashSet<UserDTO>HS_User=new HashSet<UserDTO>();
		while(rs.next()){
			UserDTO DTO_User=new UserDTO();
			DTO_User.setLoginID(rs.getInt("LoginID"));
			DTO_User.setPassword(rs.getString("Password"));
			DTO_User.setPWDLevel2(rs.getString("PWDLevel2"));
			DTO_User.setUserName(rs.getString("UserName"));
			DTO_User.setEmail(rs.getString("Email"));
			DTO_User.setCreatedDate(rs.getTimestamp("CreatedDate"));
			DTO_User.setLockedUser(rs.getBoolean("LockedUser"));
			DTO_User.setLockedDate(rs.getTimestamp("LockedDate"));
			DTO_User.setLockedReason(rs.getString("LockedReason"));
			DTO_User.setLastLogIn(rs.getTimestamp("LastLogIn"));
			DTO_User.setLastChangedPassword(rs.getTimestamp("LastChangedPassword"));
			DTO_User.setDeadlineOfUsing(rs.getDate("DeadlineOfUsing"));
			HS_User.add(DTO_User);
		}
		return HS_User;
	}
	
	public void Insert(HashSet<UserDTO> HS_User) throws Exception{
		for(UserDTO DTO_User:HS_User)
			this.Insert(DTO_User);
	}
	
	public void Insert(UserDTO DTO_User) throws Exception{
		HashMap<String, Object>map=new HashMap<String, Object>();
		map.put("LoginID", DTO_User.getLoginID());
		map.put("Password", DTO_User.getPassword());
		map.put("PWDLevel2", DTO_User.getPWDLevel2());
		map.put("UserName", DTO_User.getUserName());
		map.put("Email", DTO_User.getEmail());
		map.put("CreatedDate", DTO_User.getCreatedDate());
		map.put("LockedUser", DTO_User.getLockedUser());
		map.put("LockedDate", DTO_User.getLockedDate());
		map.put("LockedReason", DTO_User.getLockedReason());
		map.put("LastLogIn", DTO_User.getLastLogIn());
		map.put("LastChangedPassword", DTO_User.getLastChangedPassword());
		map.put("DeadlineOfUsing", DTO_User.getDeadlineOfUsing());
		this.connect.Insert("tblUser", map);
	}
	
	public void Update(HashSet<UserDTO>HS_User) throws Exception{
		for(UserDTO DTO_User:HS_User)
			this.Update(DTO_User);
	}
	
	public void Update(UserDTO DTO_User) throws Exception{
		HashMap<String, Object>map=new HashMap<String, Object>();
		map.put("LoginID", DTO_User.getLoginID());
		map.put("Password", DTO_User.getPassword());
		map.put("PWDLevel2", DTO_User.getPWDLevel2());
		map.put("UserName", DTO_User.getUserName());
		map.put("Email", DTO_User.getEmail());
		map.put("CreatedDate", DTO_User.getCreatedDate());
		map.put("LockedUser", DTO_User.getLockedUser());
		map.put("LockedDate", DTO_User.getLockedDate());
		map.put("LockedReason", DTO_User.getLockedReason());
		map.put("LastLogIn", DTO_User.getLastLogIn());
		map.put("LastChangedPassword", DTO_User.getLastChangedPassword());
		map.put("DeadlineOfUsing", DTO_User.getDeadlineOfUsing());
		this.connect.Update("tblUser", map, "LoginID"+DTO_User.getLoginID());
	}
}