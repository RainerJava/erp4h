package BusinessLogicLayer;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import Utilities.StringUtil;

import DataAccessLayer.MySQLConnectUnit;
import DataTranferObject.UserDTO;

public class UserBLL {
	MySQLConnectUnit connect;

	public ArrayList<Class<?>> arrColumnClass;

	// khoi tao mot ket noi den co so du lieu
	public UserBLL() throws Exception {
		this.connect = DataAccessLayer.DataAccess.getDAL();
	}

	// ---------------------------------------------------------------------------------
	public HashSet<UserDTO> getUser(String Condition, String OrderBy)
			throws Exception {
		ResultSet rs = this.connect.Select("tblUser", Condition, OrderBy);
		HashSet<UserDTO> hsUser = new HashSet<UserDTO>();
		while (rs.next()) {
			UserDTO dtoUser = new UserDTO();
			dtoUser.setUserID(rs.getString("UserID"));

			dtoUser.setPassword(rs.getString("Password"));
			dtoUser.setPWDLevel2(rs.getString("PWDLevel2"));
			dtoUser.setUserName(rs.getString("UserName"));
			dtoUser.setEmail(rs.getString("Email"));
			dtoUser.setLockedUser(rs.getBoolean("LockedUser"));
			dtoUser.setLockedDate(rs.getTimestamp("LockedDate"));
			dtoUser.setLockedReason(rs.getString("LockedReason"));
			dtoUser.setLastLogIn(rs.getTimestamp("LastLogIn"));
			dtoUser.setLastChangedPassword(rs
					.getTimestamp("LastChangedPassword"));
			dtoUser.setDeadlineOfUsing(rs.getTimestamp("DeadlineOfUsing"));
			dtoUser.setDelegate(rs.getBoolean("Delegate"));
			dtoUser.setNhanVienID(rs.getString("NhanVienID"));
			dtoUser.setCreatedDate(rs.getTimestamp("CreatedDate"));
			dtoUser.setOwner(rs.getString("Owner"));
			hsUser.add(dtoUser);
		}
		return hsUser;
	}

	public HashSet<UserDTO> getUser(String Condition) throws Exception {
		return getUser(Condition, null);
	}

	public HashSet<UserDTO> getUser() throws Exception {
		return getUser(null);
	}

	public void Insert(HashSet<UserDTO> HS_User) throws Exception {
		for (UserDTO DTO_User : HS_User)
			this.Insert(DTO_User);
	}

	public ArrayList<UserDTO> getUserArray(String Condition, String OrderBy)
			throws Exception {
		ResultSet rs = this.connect.Select("tblUser", Condition, OrderBy);
		ArrayList<UserDTO> arrUser = new ArrayList<UserDTO>();
		while (rs.next()) {
			UserDTO dtoUser = new UserDTO();
			dtoUser.setUserID(rs.getString("UserID"));

			dtoUser.setPassword(rs.getString("Password"));
			dtoUser.setPWDLevel2(rs.getString("PWDLevel2"));
			dtoUser.setUserName(rs.getString("UserName"));
			dtoUser.setEmail(rs.getString("Email"));
			dtoUser.setLockedUser(rs.getBoolean("LockedUser"));
			dtoUser.setLockedDate(rs.getTimestamp("LockedDate"));
			dtoUser.setLockedReason(rs.getString("LockedReason"));
			dtoUser.setLastLogIn(rs.getTimestamp("LastLogIn"));
			dtoUser.setLastChangedPassword(rs
					.getTimestamp("LastChangedPassword"));
			dtoUser.setDeadlineOfUsing(rs.getTimestamp("DeadlineOfUsing"));
			dtoUser.setDelegate(rs.getBoolean("Delegate"));
			dtoUser.setNhanVienID(rs.getString("NhanVienID"));
			dtoUser.setCreatedDate(rs.getTimestamp("CreatedDate"));
			dtoUser.setOwner(rs.getString("Owner"));
			arrUser.add(dtoUser);
		}
		return arrUser;
	}

	public ArrayList<UserDTO> getUserArray(String Condition) throws Exception {
		return getUserArray(Condition, null);
	}

	public ArrayList<UserDTO> getUserArray() throws Exception {
		return getUserArray(null);
	}

	// Tao mang
	public Object[][] getUserObj(String Condition, String OrderBy)
			throws Exception {
		int rowCount = 0;
		ResultSet rs = this.connect.Select("tblUser", Condition, OrderBy);
		while (rs.next()) {
			rowCount++;
		}
		Object[][] UserObj = new Object[rowCount][];
		rs.beforeFirst();
		int j = 0;
		while (rs.next()) {
			Object[] cellObject = new Object[rs.getMetaData().getColumnCount()];
			for (int i = 0; i < cellObject.length; i++) {
				cellObject[i] = rs.getObject(i + 1);
			}
			UserObj[j++] = cellObject;
		}
		rs.close();
		return UserObj;
	}

	public Object[][] getUserObj(String Condition) throws Exception {
		return getUserObj(Condition, null);
	}

	public Object[][] getUserObj() throws Exception {
		return getUserObj(null);
	}

	// ---------------------------------------------------------
	public void Insert(UserDTO dtoUser) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("UserID", dtoUser.getUserID());

		map.put("Password", dtoUser.getPassword());
		map.put("PWDLevel2", dtoUser.getPWDLevel2());
		map.put("UserName", dtoUser.getUserName());
		map.put("Email", dtoUser.getEmail());
		map.put("LockedUser", dtoUser.isLockedUser());
		map.put("LockedDate", dtoUser.getLockedDate());
		map.put("LockedReason", dtoUser.getLockedReason());
		map.put("LastLogIn", dtoUser.getLastLogIn());
		map.put("LastChangedPassword", dtoUser.getLastChangedPassword());
		map.put("DeadlineOfUsing", dtoUser.getDeadlineOfUsing());
		map.put("Delegate", dtoUser.isDelegate());
		map.put("NhanVienID", dtoUser.getNhanVienID());
		map.put("CreatedDate", dtoUser.getCreatedDate());
		map.put("Owner", dtoUser.getOwner());
		this.connect.Insert("tblUser", map);
	}

	public void Update(HashSet<UserDTO> HS_User) throws Exception {
		for (UserDTO DTO_User : HS_User)
			this.Update(DTO_User);
	}

	public void Update(UserDTO dtoUser) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("UserID", dtoUser.getUserID());

		map.put("Password", dtoUser.getPassword());
		map.put("PWDLevel2", dtoUser.getPWDLevel2());
		map.put("UserName", dtoUser.getUserName());
		map.put("Email", dtoUser.getEmail());
		map.put("LockedUser", dtoUser.isLockedUser());
		map.put("LockedDate", dtoUser.getLockedDate());
		map.put("LockedReason", dtoUser.getLockedReason());
		map.put("LastLogIn", dtoUser.getLastLogIn());
		map.put("LastChangedPassword", dtoUser.getLastChangedPassword());
		map.put("DeadlineOfUsing", dtoUser.getDeadlineOfUsing());
		map.put("Delegate", dtoUser.isDelegate());
		map.put("NhanVienID", dtoUser.getNhanVienID());
		map.put("CreatedDate", dtoUser.getCreatedDate());
		map.put("Owner", dtoUser.getOwner());
		this.connect.Update("tblUser", map, "LoginID" + dtoUser.getUserID());
	}

	public void Delete(HashSet<UserDTO> HS_User) throws Exception {
		for (UserDTO DTO_User : HS_User)
			this.Delete(DTO_User);
	}

	public void Delete(UserDTO dtoUser) throws Exception {
		this.connect.Delete("tblUser", "UserID" + dtoUser.getUserID());
	}

	public UserDTO getByID(String UserID) throws Exception {
		HashSet<UserDTO> hsUser;
		hsUser = this.getUser("UserID=" + UserID);
		if (hsUser.size() > 0)
			return hsUser.toArray(new UserDTO[hsUser.size()])[0];
		else {
			throw new Exception();
		}
	}

	// trả về tổng số cột (field) của truy vấn rs
	public int getColumnCount() throws Exception {
		ResultSet rs = this.connect.Select("tblUser");
		int ColumnCount = rs.getMetaData().getColumnCount();
		return ColumnCount;
	}

	// trả về tên của từng cột (field)
	public String[] getColumnName() throws Exception {
		ResultSet rs = this.connect.Select("tblUser");
		ResultSetMetaData rsMeta = rs.getMetaData();
		String[] ColumnName = new String[rs.getMetaData().getColumnCount()];
		for (int i = 0; i < ColumnName.length; i++) {
			switch (rsMeta.getColumnName(i + 1)) {
			case "UserID":
				ColumnName[i] = "Tai khoan";
				break;
			case "Password":
				ColumnName[i] = "Mat Khau";
				break;
			case "PWDLevel2":
				ColumnName[i] = "Mat khau cap 2";
				break;
			case "UserName":
				ColumnName[i] = "Ten nguoi dung";
				break;
			case "Email":
				ColumnName[i] = "Dia chi Email";
				break;
			case "LockedUser":
				ColumnName[i] = "Tai khoan bi khoa";
				break;
			case "LockedDate":
				ColumnName[i] = "Ngay khoa";
				break;
			case "LockedReason":
				ColumnName[i] = "Ly do Khoa";
				break;
			case "LastLogIn":
				ColumnName[i] = "Dang nhap lan cuoi";
				break;
			case "LastChangedPassword":
				ColumnName[i] = "Doi mat khau lan cuoi";
				break;
			case "DeadlineOfUsing":
				ColumnName[i] = "Ngay het han dung";
				break;
			case "NhanVienID":
				ColumnName[i] = "Ma nhan vien";
				break;
			case "CreatedDate":
				ColumnName[i] = "Ngay tao";
				break;
			case "Owner":
				ColumnName[i] = "Nguoi tao";
				break;
			}
		}
		return ColumnName;
	}

	public ArrayList<Class<?>> getColumnClass() throws Exception {
		ResultSet rs = this.connect.Select("tblUser");
		ResultSetMetaData rsMeta = rs.getMetaData();
		arrColumnClass = new ArrayList<Class<?>>();
		for (int i = 0; i < rsMeta.getColumnCount(); i++) {
			switch (rsMeta.getColumnType(i + 1)) {
			case Types.INTEGER:
				arrColumnClass.add(Integer.class);
				break;
			case Types.FLOAT:
				arrColumnClass.add(Float.class);
				break;
			case Types.DOUBLE:
			case Types.REAL:
				arrColumnClass.add(Double.class);
				break;
			case Types.DATE:
			case Types.TIME:
			case Types.TIMESTAMP:
				arrColumnClass.add(Date.class);
				break;
			default:
				arrColumnClass.add(String.class);
				break;
			}
		}
		return arrColumnClass;
	}

	public int getRowCount() throws Exception {
		int RowCount = 0;
		ResultSet rs = this.connect.Select("tblUser");
		if (!rs.isBeforeFirst()) {
			rs.beforeFirst();
			while (rs.next()) {
				++RowCount;
			}
		}
		return RowCount;
	}
	
	//Tham so truyen vao cho PhanHeID, UserID
	public ArrayList<Integer> getUserRight(ArrayList<Object> parameter) throws Exception{
		ResultSet rs=connect.prepareCall("spUser_SelectUserRight", parameter);
		ArrayList<Integer> arr=new ArrayList<Integer>();
		
		while(rs.next()){
			ArrayList<Integer> s=new StringUtil().getIntArray(rs.getString("GroupRight"));
			
		}
		return null;
	}
}