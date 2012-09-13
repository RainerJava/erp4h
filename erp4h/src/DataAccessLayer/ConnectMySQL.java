package DataAccessLayer;

import java.sql.*;

public class ConnectMySQL {
	String strHost;
	String strUserName;
	String strPassWord;
	String strDataBase;

	Connection connect = null;
	Statement statement = null;
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

	/*
	 * Kiá»ƒm tra Class Name Náº¿u khÃ´ng tá»“n táº¡i thÃ¬ mÃ©m lá»—i ra ngoÃ i.
	 */
	protected void driverTest() throws Exception {
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		} catch (java.lang.ClassNotFoundException e) {
			throw new Exception("Không tìm thấy trình điều khiển ... ");
		}
	}

	/*
	 * tao ket noi
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
						+ "[getConnect] Không thể kết nối đến máy chủ CSDL: "
						+ url);
			}
		}
		// Trả về kết nối
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

	/**
	 * Thực thi lệnh Update, Insert, Delete, "finally" luôn đóng kết nối
	 * 
	 * @param Query
	 *            lệnh truy vấn
	 * @return số lượng mẩu tin được Update, Insert hoặc Delete
	 * @throws Exception
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
		// Náº¿u connection chÆ°a Ä‘Ã³ng. Ä�Ã³ng connection.
		if (this.connect != null && !this.connect.isClosed()) {
			this.connect.close();
			this.connect = null;
		}
	}
}