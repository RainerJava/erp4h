package org.erp4h.dal;

import java.sql.*;
import java.util.ArrayList;

public class ConnectDB {
	String dbType;
	String dbDriver;
	String dbHostName;
	String dbPortNumber;
	String dbName;
	String dbUserName;
	String dbPassword;
	String dbUrl;

	Connection connect = null;
	Statement statement = null;
	CallableStatement cs = null;
	ResultSet resultset = null;

	public ConnectDB() throws Exception {
	}

	public ConnectDB(String dbDriver, String dbHostName, String dbPortNumber,
			String dbName, String dbUserName, String dbPassWord, String dbUrl) {
		this.dbDriver = dbDriver;
		this.dbHostName = dbHostName;
		this.dbPortNumber = dbPortNumber;
		this.dbName = dbName;
		this.dbUserName = dbUserName;
		this.dbPassword = dbPassWord;
		this.dbUrl = dbUrl;
	}

	public ConnectDB(String dbType, String dbDriver, String dbHostName,
			String dbPortNumber, String dbName, String dbUserName,
			String dbPassWord, String dbUrl) {
		this.dbType = dbType;
		this.dbDriver = dbDriver;
		this.dbHostName = dbHostName;
		this.dbPortNumber = dbPortNumber;
		this.dbName = dbName;
		this.dbUserName = dbUserName;
		this.dbPassword = dbPassWord;
		this.dbUrl = dbUrl;
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
	protected void driverTest(String driver) throws Exception {
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
			driverTest(this.dbDriver);
			try {
				// Tạo kết nối thông qua url
				this.connect = DriverManager.getConnection(this.dbUrl,
						this.dbUserName, this.dbPassword);
			}
			// Nếu không thành công thì ném lỗi ra ngoài
			catch (java.sql.SQLException e) {
				throw new Exception(e.getMessage() + "\n"
						+ " Không thể kết nối đến máy chủ CSDL: " + this.dbUrl);
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

	protected void checkDriver(String driverName) throws Exception {
		try {
			Class.forName(driverName);
		} catch (java.lang.ClassNotFoundException e) {
			throw new Exception(e.getMessage() + "\n"
					+ " Không tìm thấy trình đi�?u khiển ... ");
		}
	}
}
