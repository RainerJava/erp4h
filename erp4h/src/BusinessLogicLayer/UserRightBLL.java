package BusinessLogicLayer;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;

import DataAccessLayer.MySQLConnectUnit;
import DataTranferObject.UserRightDTO;

public class UserRightBLL {
	MySQLConnectUnit connect;
	
	public UserRightBLL() {
		super();
		this.connect=DataAccessLayer.DataAccess.getDAL();
	}
	
	public ArrayList<UserRightDTO> getUserRightArray(String Condition, String OrderBy) throws Exception{
		ResultSet rs=connect.Select("tblUserRight", Condition, OrderBy);
		ArrayList<UserRightDTO> arrUserRight=new ArrayList<UserRightDTO>();
		while(rs.next()){
			UserRightDTO dtoUserRight=new UserRightDTO();
			dtoUserRight.setPhanHeID(rs.getInt("PhanHeID"));
			dtoUserRight.setUserID(rs.getString("UserID"));
			dtoUserRight.setUserRight(rs.getString("UserRight"));
			dtoUserRight.setCreateDate(rs.getTimestamp("CreateDate"));
			dtoUserRight.setOwner(rs.getString("Owner"));
			arrUserRight.add(dtoUserRight);
		}
		return arrUserRight;
	}
	public ArrayList<UserRightDTO> getUserRightArray(String Condition) throws Exception{
		return getUserRightArray(Condition, null);
	}
	public ArrayList<UserRightDTO> getUserRightArray() throws Exception{
		return getUserRightArray(null);
	}
	
	public HashSet<UserRightDTO> getUserRightDTO(String Condition, String OrderBy) throws Exception{
		ResultSet rs=connect.Select("tblUserRight", Condition, OrderBy);
		HashSet<UserRightDTO> hsUserRight=new HashSet<UserRightDTO>();
		while(rs.next()){
			UserRightDTO dtoUserRight=new UserRightDTO();
			dtoUserRight.setPhanHeID(rs.getInt("PhanHeID"));
			dtoUserRight.setUserID(rs.getString("UserID"));
			dtoUserRight.setUserRight(rs.getString("UserRight"));
			dtoUserRight.setCreateDate(rs.getTimestamp("CreateDate"));
			dtoUserRight.setOwner(rs.getString("Owner"));
			hsUserRight.add(dtoUserRight);
		}
		return hsUserRight;
	}
	public HashSet<UserRightDTO> getUserRightDTO(String Condition) throws Exception{
		return getUserRightDTO(Condition, null);
	}
	public HashSet<UserRightDTO> getUserRightDTO() throws Exception{
		return getUserRightDTO(null);
	}
	
	public UserRightDTO getByID(String PhanHeID) throws Exception{
		HashSet<UserRightDTO> hsUserRight=this.getUserRightDTO(PhanHeID);
		if(hsUserRight.size()==1){
			return hsUserRight.toArray(new UserRightDTO[hsUserRight.size()])[0];
		}
		return null;
	}
}