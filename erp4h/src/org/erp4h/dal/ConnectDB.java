package org.erp4h.dal;

import java.sql.*;
import java.util.ArrayList;

public class ConnectDB {
	String strHost;
	String strUserName;
	String strPassWord;
	String strDataBase;

	Connection connect = null;
	Statement statement = null;
	CallableStatement cs = null;
	ResultSet resultset = null;

	public ConnectDB() throws Exception {
	}

	public ConnectDB(String host, String username, String password,
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
					cs.setString(i + 1, (String) parameter.get(i));
					break;
				case "Date":
					cs.setDate(i + 1, (Date) parameter.get(i));
					break;
				}
			}
		}
		return this.resultset = cs.executeQuery();
	}

	/**
	 * Thực thi lệnh Update, Insert, Delete, "finally" luôn đóng kết nối
	 * 
	 * @param Query
	 *            lệnh truy vấn
	 * @return số lượng mẩu tin được Update, Insert hoặc Delete
	 * @throws new
	 *             Exception
	 */
	public int executeUpdate(String query) throws Exception {
		int res = Integer.MIN_VALUE;
		try {
			res = getStatement().executeUpdate(query);
		} catch (Exception e) {
			throw new Exception("Error: " + e.getMessage() + " - " + query);
		} finally {
			this.Close();
		}
		return res;
	}

	/**
	 * Tạo kết nối thông qua url, nếu không thành công thì ném lỗi ra ngoài
	 * 
	 * @return Trả về kết nối đến CSDL MySQL
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
		// Trả về statement.
		return this.statement;
	}

	public static void main(String[] args) throws Exception {
		ConnectDB conDB = new ConnectDB();
		conDB.checkDriver("sun.jdbc.odbc.JdbcOdbcDriver");
		// try {
		// Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		// // set this to a MS Access DB you have on your machine
		// String filename = "d:/java/mdbTEST.mdb";
		// String database =
		// "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=";
		// database += filename.trim() + ";DriverID=22;READONLY=true}"; // add
		// // on
		// // to
		// // the
		// // end
		// // now we can get the connection from the DriverManager
		// Connection con = DriverManager.getConnection(database, "", "");
		// } catch (Exception e) {
		// System.out.println("Error: " + e);
		// }
	}

	protected void checkDriver(String driverName) throws Exception {
		try {
			Class.forName(driverName);
		} catch (java.lang.ClassNotFoundException e) {
			throw new Exception(e.getMessage() + "\n"
					+ " Không tìm thấy trình đi�?u khiển ... ");
		}
	}

	/**
	 * Tạo kết nối nâng cao, kết nối đến các loại CSDL khác nhau
	 * 
	 * @param typeRDBMS
	 *            loại CSDL
	 * @param driverName
	 *            trình điều khiển tương ứng
	 * @return connect
	 * @throws Exception
	 */
	protected Connection getConnect(int typeDBMS, String driverName)
			throws Exception {
		switch(typeDBMS){
		case 1: // coonect to Oracle
			break;
		case 2: // connect to MySQL
			break;
		case 3:
			break;
		}
		if (this.connect == null) {
			switch (typeRDBMS) {
			}
			checkDriver(driverName);
		}
		return this.connect;
	}
	protected String createUrl(){
		return null;
	}
}
