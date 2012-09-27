package org.erp4h.bll;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;

import org.erp4h.dal.ConnectUtils;
import org.erp4h.system.dto.GroupUserDTO;


public class GroupUserBLL {
	ConnectUtils connect;

	public GroupUserBLL() {
		super();
		this.connect = org.erp4h.dal.DataAccess.getDAL();
	}

	public ArrayList<GroupUserDTO> getArray() throws Exception {
		return getArray(null);
	}

	public ArrayList<GroupUserDTO> getArray(String Condition) throws Exception {
		return getArray(Condition, null);
	}

	public ArrayList<GroupUserDTO> getArray(String Condition, String OrderBy)
			throws Exception {
		ResultSet rs = connect.Select("tblGroupUser", Condition, OrderBy);
		ArrayList<GroupUserDTO> arrGroupUser = new ArrayList<GroupUserDTO>();
		while (rs.next()) {
			GroupUserDTO dtoGroupUser = new GroupUserDTO();
			dtoGroupUser.setGroupID(rs.getInt("GroupID"));
			dtoGroupUser.setUserID(rs.getString("UserID"));
			dtoGroupUser.setCreateDate(rs.getTimestamp("CreateDate"));
			dtoGroupUser.setOwner(rs.getString("Owner"));
			dtoGroupUser.setStatus(rs.getBoolean("Status"));
			arrGroupUser.add(dtoGroupUser);
		}
		return arrGroupUser;
	}

	public GroupUserDTO getByID(String UserID) throws Exception {
		HashSet<GroupUserDTO> hsGroupUser = this.getHashSet(UserID);
		if (hsGroupUser.size() == 1) {
			return hsGroupUser.toArray(new GroupUserDTO[hsGroupUser.size()])[0];
		}
		return null;
	}

	public ArrayList<Integer> getGroup(String Condition, String OrderBy)
			throws Exception {
		ResultSet rs = connect.Select("tblGroupUser", Condition, OrderBy);
		ArrayList<Integer> arrGroup = new ArrayList<Integer>();
		while (rs.next()) {
			arrGroup.add(rs.getInt("GroupID"));
		}
		return arrGroup;
	}

	public HashSet<GroupUserDTO> getHashSet() throws Exception {
		return getHashSet(null);
	}

	public HashSet<GroupUserDTO> getHashSet(String Condition) throws Exception {
		return getHashSet(Condition, null);
	}

	public HashSet<GroupUserDTO> getHashSet(String Condition, String OrderBy)
			throws Exception {
		ResultSet rs = connect.Select("tblUserRight", Condition, OrderBy);
		HashSet<GroupUserDTO> hsGroupUser = new HashSet<GroupUserDTO>();
		while (rs.next()) {
			GroupUserDTO dtoGroupUser = new GroupUserDTO();
			dtoGroupUser.setGroupID(rs.getInt("GroupID"));
			dtoGroupUser.setUserID(rs.getString("UserID"));
			dtoGroupUser.setCreateDate(rs.getTimestamp("CreateDate"));
			dtoGroupUser.setOwner(rs.getString("Owner"));
			dtoGroupUser.setStatus(rs.getBoolean("Status"));
			hsGroupUser.add(dtoGroupUser);
		}
		return hsGroupUser;
	}
}
