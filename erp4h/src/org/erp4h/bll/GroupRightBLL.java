package org.erp4h.bll;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;

import org.erp4h.dal.ConnectUtils;
import org.erp4h.system.dto.GroupRightDTO;

public class GroupRightBLL {
	ConnectUtils connect;

	public GroupRightBLL() {
		super();
		this.connect = org.erp4h.dal.DataAccess.getDataAccess();
	}

	public ArrayList<GroupRightDTO> getArray() throws Exception {
		return getArray(null);
	}

	public ArrayList<GroupRightDTO> getArray(String Condition)
			throws Exception {
		return getArray(Condition, null);
	}

	public ArrayList<GroupRightDTO> getArray(String Condition,
			String OrderBy) throws Exception {
		ResultSet rs = connect.Select("tblGroupRight", Condition, OrderBy);
		ArrayList<GroupRightDTO> arrGroupRight = new ArrayList<GroupRightDTO>();
		while (rs.next()) {
			GroupRightDTO dtoGroupRight = new GroupRightDTO();
			dtoGroupRight.setPhanHeID(rs.getInt("PhanHeID"));
			dtoGroupRight.setGroupID(rs.getInt("GroupID"));
			dtoGroupRight.setGroupRight(rs.getString("GroupRight"));
			dtoGroupRight.setCreateDate(rs.getTimestamp("CreateDate"));
			dtoGroupRight.setOwner(rs.getString("Owner"));
			arrGroupRight.add(dtoGroupRight);
		}
		return arrGroupRight;
	}

	public GroupRightDTO getByID(String PhanHeID) throws Exception {
		HashSet<GroupRightDTO> hsGroupRight = this.getHashSet(PhanHeID);
		if (hsGroupRight.size() == 1) {
			return hsGroupRight.toArray(new GroupRightDTO[hsGroupRight.size()])[0];
		}
		return null;
	}

	public HashSet<GroupRightDTO> getHashSet() throws Exception {
		return getHashSet(null);
	}

	public HashSet<GroupRightDTO> getHashSet(String Condition)
			throws Exception {
		return getHashSet(Condition, null);
	}

	public HashSet<GroupRightDTO> getHashSet(String Condition,
			String OrderBy) throws Exception {
		ResultSet rs = connect.Select("tblGroupRight", Condition, OrderBy);
		HashSet<GroupRightDTO> hsGroupRight = new HashSet<GroupRightDTO>();
		while (rs.next()) {
			GroupRightDTO dtoGroupRight = new GroupRightDTO();
			dtoGroupRight.setPhanHeID(rs.getInt("PhanHeID"));
			dtoGroupRight.setGroupID(rs.getInt("GroupID"));
			dtoGroupRight.setGroupRight(rs.getString("GroupRight"));
			dtoGroupRight.setCreateDate(rs.getTimestamp("CreateDate"));
			dtoGroupRight.setOwner(rs.getString("Owner"));
			hsGroupRight.add(dtoGroupRight);
		}
		return hsGroupRight;
	}

	public ArrayList<String> getRight(String Condition, String OrderBy)
			throws Exception {
		ResultSet rs = connect.Select("tblGroupRight", Condition, OrderBy);
		ArrayList<String> arrGroupRight=new ArrayList<String>();
		while(rs.next()){
			arrGroupRight.add(rs.getString("GroupRight"));
			
		}
		return arrGroupRight;
	}
}
