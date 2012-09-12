package BusinessLogicLayer;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;

import DataAccessLayer.MySQLConnectUnit;
import DataTranferObject.GroupRightDTO;

import Utilities.StringUtil;

public class GroupRightBLL {
	MySQLConnectUnit connect;

	public GroupRightBLL() {
		super();
		this.connect = DataAccessLayer.DataAccess.getDAL();
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

	public ArrayList<GroupRightDTO> getArray(String Condition)
			throws Exception {
		return getArray(Condition, null);
	}

	public ArrayList<GroupRightDTO> getArray() throws Exception {
		return getArray(null);
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

	public HashSet<GroupRightDTO> getHashSet(String Condition)
			throws Exception {
		return getHashSet(Condition, null);
	}

	public HashSet<GroupRightDTO> getHashSet() throws Exception {
		return getHashSet(null);
	}

	public GroupRightDTO getByID(String PhanHeID) throws Exception {
		HashSet<GroupRightDTO> hsGroupRight = this.getHashSet(PhanHeID);
		if (hsGroupRight.size() == 1) {
			return hsGroupRight.toArray(new GroupRightDTO[hsGroupRight.size()])[0];
		}
		return null;
	}

	public ArrayList<Integer> getRight(String Condition, String OrderBy)
			throws Exception {
		ResultSet rs = connect.Select("tblGroupRight", Condition, OrderBy);
		rs.first();
		return new StringUtil().getIntArray(rs.getString("GroupRight"));
	}
}
