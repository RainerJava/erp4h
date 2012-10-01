package org.erp4h.dal;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class ConnectUtils {
	private ConnectDB connect;

	public ConnectUtils() {
		super();
	}

	// Tao ket noi
	public ConnectUtils(String Host, String UserName, String Password,
			String Database) {
		this.connect = new ConnectDB(Host, UserName, Password, Database);
	}

	private void AddCondition(StringBuilder query, String Condition) {
		if (Condition != null)
			query.append(" where " + Condition);
	}

	private void AddOrderBy(StringBuilder query, String OrderBy) {
		if (OrderBy != null)
			query.append(" order by " + OrderBy);
	}

	public void Close() throws SQLException {
		this.connect.Close();
	}

	public boolean Delete(String TableName, String Condition) throws Exception {
		StringBuilder query = new StringBuilder("delete from " + TableName);
		this.AddCondition(query, Condition);
		query.append(";");
		return this.connect.executeUpdate(query.toString()) > 0;
	}

	public int getColumnCount(ResultSet result) throws SQLException {
		return result.getMetaData().getColumnCount();
	}

	public String[] getColumnName(ResultSet result) throws SQLException {
		ResultSetMetaData rsMetaData = result.getMetaData();
		int ColumnCount = rsMetaData.getColumnCount();
		String[] list = new String[ColumnCount];
		for (int i = 0; i < ColumnCount; i++) {
			list[i] = rsMetaData.getColumnName(i + 1);
		}
		return list;
	}

	public boolean Insert(String TableName, HashMap<String, Object> ColumnValues)
			throws Exception {
		StringBuilder query = new StringBuilder("insert into " + TableName);
		StringBuilder valueInsert = new StringBuilder();
		query.append("(");
		for (String key : ColumnValues.keySet()) {
			query.append(key + ", ");
			valueInsert.append("'" + ColumnValues.get(key).toString() + "', ");
		}
		query = query.delete(query.length() - 1, query.length());
		valueInsert = valueInsert.delete(valueInsert.length() - 1,
				valueInsert.length());
		query.append(") values (" + valueInsert.toString() + ")");
		return this.connect.executeUpdate(query.toString()) > 0;
	}

	//
	public ResultSet prepareCall(String spName, ArrayList<Object> parameter) throws Exception{
		
		return this.connect.executeStore(spName,parameter);
	}

	public ResultSet SystemSelect(String query) throws Exception{
		return connect.executeQuery(query);
	}
	public ResultSet Select(String TableName) throws Exception {
		return this.Select(TableName, null);
	}

	public ResultSet Select(String TableName, String Condition)
			throws Exception {
		return this.Select(TableName, Condition, null);
	}

	public ResultSet Select(String TableName, String Condition, String OrderBy)
			throws Exception {
		StringBuilder query = new StringBuilder("select * from " + TableName);
		this.AddCondition(query, Condition);
		this.AddOrderBy(query, OrderBy);
		query.append(";");
		System.out.println(query.toString());
		return connect.executeQuery(query.toString());
	}

	public String Select(String[] fieldName, String[] tableName,
			String[] condition) {
		StringBuilder query = new StringBuilder("select ");
		for (int i = 0; i < fieldName.length; i++) {
			query.append(fieldName[i] + ", ");
		}
		query.delete(query.length() - 2, query.length() + 100);
		query.append(" from " + tableName[0]);
		query.append(";");
		return query.toString();
	}

	public boolean Update(String TableName,
			HashMap<String, Object> ColumnValues, String Condition)
			throws Exception {
		StringBuilder query = new StringBuilder("update " + TableName + " set ");
		for (String key : ColumnValues.keySet()) {
			query.append(key + "='" + ColumnValues.get(key).toString() + "',");
		}
		query = query.delete(query.length() - 1, query.length());
		this.AddCondition(query, Condition);
		query.append(";");
		return this.connect.executeUpdate(query.toString()) > 0;
	}
}