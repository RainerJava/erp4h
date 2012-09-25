package org.erp4h.dal;

import java.sql.*;
import java.util.ArrayList;

public class ConnectMySQL {
	String strHost;
	String strUserName;
	String strPassWord;
	String strDataBase;

	Connection connect = null;
	Statement statement = null;
	CallableStatement cs = null;
	ResultSet resultset = null;

	public ConnectMySQL() {
	}

	public ConnectMySQL(String host, String username, String password,
			String database) {
		this.strHost = host;
		this.strUserName = username;
		this.strPassWord = password;
		this.strDataBase = database;
	}

	public void Close() throws SQLException {
		// Náº¿u Result chÆ°a Ä‘Ã³ng. Ä�Ã³ng result
		if (this.resultset != null && !this.resultset.isClosed()) {
			this.resultset.close();
			this.resultset = null;
		}
		// Náº¿u statement chÆ°a Ä‘Ã³ng. Ä�Ã³ng statement.
		if (this.statement != null && !this.statement.isClosed()) {
			this.statement.close();
			this.statement = null;
		}
		if (this.cs != null && !this.cs.isClosed()) {
			this.cs.close();
			this.cs = null;
		}
		// Náº¿u connection chÆ°a Ä‘Ã³ng. Ä�Ã³ng connection.
		if (this.connect != null && !this.connect.isClosed()) {
			this.connect.close();
			this.connect = null;
		}
	}

	/**
	 * Kiá»ƒm tra Class Name Náº¿u khÃ´ng tá»“n táº¡i thÃ¬ mÃ©m lá»—i ra ngoÃ i.
	 */
	protected void driverTest() throws Exception {
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		} catch (java.lang.ClassNotFoundException e) {
			throw new Exception(e.getMessage() + "\n"
					+ " Không tìm thấy trình đi�?u khiển ... ");
		}
	}

	/**
	 * Thực thi truy vấn nếu không thành công ném lỗi và câu lệnh truy vấn ra
	 * ngoài
	 * 
	 * @param Query
	 *            lệnh truy vấn
	 * @return resultset tương tứng với truy vấn
	 * @throws Exception
	 *             báo lỗi
	 */
	public ResultSet executeQuery(String Query) throws Exception {
		try {
			this.resultset = getStatement().executeQuery(Query);
		} catch (Exception e) {
			throw new Exception(e.getMessage() + "\n"
					+ "[executeQuery] không thực hiện được truy vấn " + " - "
					+ Query);
		}
		return this.resultset;
	}

	public ResultSet executeStore(String spName, ArrayList<Object> parameter)
			throws SQLException, Exception {
		StringBuilder strQuery = new StringBuilder("{call " + spName + "(");
		for (int i = 0; i < parameter.size(); i++) {
			strQuery.append("?, ");
			if (i == parameter.size() - 1) {
				strQuery.delete(strQuery.length() - 2, strQuery.length());
				strQuery.append(")}");
				System.out.println(strQuery);
			}
		}
		if (this.cs == null ? true : this.cs.isClosed()) {
			this.cs = this.getConnect().prepareCall(strQuery.toString());
			for (int i = 0; i < parameter.size(); i++) {
				switch (parameter.get(i).getClass().getSimpleName()) {
				case "Byte":
					cs.setByte(i + 1, (byte) parameter.get(i));
					break;
				case "Short":
					cs.setShort(i + 1, (short) parameter.get(i));
					break;
				case "Integer":
					cs.setInt(i + 1, (int) parameter.get(i));
					break;
				case "Long":
					cs.setLong(i + 1, (long) parameter.get(i));
					break;
				case "Float":
					cs.setFloat(i + 1, (float) parameter.get(i));
					break;
				case "Double":
					cs.setDouble(i + 1, (double) parameter.get(i));
					break;
				case "Boolean":
					cs.setBoolean(i + 1, (boolean) parameter.get(i));
					break;
				case "String":
					cs.setString(i+1, (String) parameter.get(i));
					break;
				case "Date":
					cs.setDate(i+1, (Date) parameter.get(i));
					break;
				}
			}
		}
		return this.resultset = cs.executeQuery();
	}

	// this.cs = this.getConnect().prepareCall(strQuery);
	// this.resultset = cs.executeQuery();
	// return resultset;
	// }

	// public ResultSet prepareCall(String storeName, ArrayList<Object>
	// parameter){
	// StringBuilder strQuery=new StringBuilder("{call "+ storeName+"(");
	// for(int i=0;i<parameter.size();i++){
	// strQuery.append("?, ");
	// if(i==parameter.size()){
	// strQuery.delete(strQuery.length()-2, strQuery.length());
	// strQuery.append(")}");
	// }
	// }

	// if (this.cs == null ? true : this.cs.isClosed()) {
	// this.cs = this.getConnect().prepareCall(
	// "{call " + storeName + "(?)}");
	// this.cs.setInt(1, PhanHeID);
	// this.resultset = cs.executeQuery();
	// }

	/**
	 * Thực thi lệnh Update, Insert, Delete, "finally" luôn đóng kết nối
	 * 
	 * @param Query
	 *            lệnh truy vấn
	 * @return số lượng mẩu tin được Update, Insert hoặc Delete
	 * @throws new
	 *             Exception
	 */
	public int executeUpdate(String Query) throws Exception {
		int res = Integer.MIN_VALUE;
		try {
			res = getStatement().executeUpdate(Query);
		} catch (Exception e) {
			throw new Exception("Error: " + e.getMessage() + " - " + Query);
		} finally {
			this.Close();
		}
		return res;
	}

	/**
	 * Tạo kết nối thông qua url, nếu không thành công thì ném lỗi ra ngoài
	 * 
	 * @return Trả v�? kết nối
	 * @throws Exception
	 *             Không thể kết nối đến máy chủ CSDL:
	 */
	protected Connection getConnect() throws Exception {
		if (this.connect == null) {
			driverTest();
			String url = "jdbc:mysql://" + this.strHost + ":3306/"
					+ this.strDataBase;
			try {
				// Tạo kết nối thông qua url
				this.connect = DriverManager.getConnection(url,
						this.strUserName, this.strPassWord);
			}
			// Nếu không thành công thì ném lỗi ra ngoài
			catch (java.sql.SQLException e) {
				throw new Exception(e.getMessage() + "\n"
						+ " Không thể kết nối đến máy chủ CSDL: " + url);
			}
		}
		// Trả v�? kết nối
		return this.connect;
	}

	protected Statement getStatement() throws Exception {
		// Kiểm tra statement nếu là null hoặc đã đóng thì tạo mới statement
		if (this.statement == null ? true : this.statement.isClosed()) {
			this.statement = this.getConnect().createStatement();
		}
		// Trả v�? statement.
		return this.statement;
	}
}
/*
 * java.util.Date today = new java.util.Date(); java.sql.Date sqlToday = new
 * java.sql.Date(today.getTime());
 * 
 * For Timestamp, it's the same idea
 * 
 * java.util.Date today = new java.util.Date(); java.sql.Timestamp now = new
 * java.sql.Timestamp(today.getTime());
 * 
 * To use a Date, Time or Timestamp in a query, you can use JDBC escape codes.
 * 
 * Date {d 'yyyy-mm-dd'} Time {t {'hh:mm:ss'} Timestamp {ts `yyyy-mm-dd
 * hh:mm:ss.f . . .'} note: the .f .... is optional
 * 
 * For example, a Statement with a Date will look like this
 * 
 * java.util.Date today = new java.util.Date(); java.sql.Date sqlToday = new
 * java.sql.Date(today.getTime());
 * 
 * String query = "select * from cust where purchase_date < { d '" +
 * sqlDate.toString() + "' }");
 * 
 * With a PreparedStatement, you don't need JDBC escape codes, the JDBC driver
 * will do the job for you.
 * 
 * java.util.Date today = new java.util.Date(); java.sql.Date sqlToday = new
 * java.sql.Date(today.getTime());
 * 
 * PreparedStatement p = theConn.prepareStatement
 * ("select * from cust where purchase_date < ?"); p.setDate(1, sqlToday);
 * ResultSet rs = p.executeQuery();
 * 
 * To INSERT
 * 
 * PreparedStatement p = theConn.prepareStatement
 * ("insert into TableWithADateColumn values(?)"); p.setDate(1, sqlToday);
 * p.executeUpdate();
 * 
 * or
 * 
 * p.executeUpdate
 * ("insert into TableWithADateColumn values( { d '1999-12-31' } )");
 */