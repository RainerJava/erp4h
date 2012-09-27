package org.erp4h.bll;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;

import org.erp4h.commom.utils.StringUtil;
import org.erp4h.dal.ConnectUtils;
import org.erp4h.system.dto.UserRightDTO;



public class UserRightBLL {
	ConnectUtils connect;
	
	public UserRightBLL() {
		super();
		this.connect=org.erp4h.dal.DataAccess.getDAL();
	}
	
	public ArrayList<UserRightDTO> getArray() throws Exception{
		return getArray(null);
	}
	public ArrayList<UserRightDTO> getArray(String Condition) throws Exception{
		return getArray(Condition, null);
	}
	public ArrayList<UserRightDTO> getArray(String Condition, String OrderBy) throws Exception{
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
	
	public UserRightDTO getByID(String PhanHeID) throws Exception{
		HashSet<UserRightDTO> hsUserRight=this.getHashSet(PhanHeID);
		if(hsUserRight.size()==1){
			return hsUserRight.toArray(new UserRightDTO[hsUserRight.size()])[0];
		}
		return null;
	}
	public HashSet<UserRightDTO> getHashSet() throws Exception{
		return getHashSet(null);
	}
	public HashSet<UserRightDTO> getHashSet(String Condition) throws Exception{
		return getHashSet(Condition, null);
	}
	
	public HashSet<UserRightDTO> getHashSet(String Condition, String OrderBy) throws Exception{
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
	
	public ArrayList<Integer> getRight(String Condition, String OrderBy) throws Exception{
		ResultSet rs=connect.Select("tblUserRight", Condition, OrderBy);
		rs.first();
		return new StringUtil().getIntArray(rs.getString("UserRight"));
	}
}
