package org.erp4h.dal;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.linuxense.javadbf.DBFException;
import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFReader;

public class ConnectUtils {
	private ConnectDB connect;

	public ConnectUtils() {
		super();
	}

	// Tao ket noi
	public ConnectUtils(String dbDriver, String dbHostName,
			String dbPortNumber, String dbName, String dbUserName,
			String dbPassword, String dbUrl) {
		this.connect = new ConnectDB(dbDriver, dbHostName,
				dbPortNumber, dbName, dbUserName,
				dbPassword, dbUrl);
	}

	public ConnectUtils(String dbType, String dbDriver, String dbHostName,
			String dbPortNumber, String dbName, String dbUserName,
			String dbPassword, String dbUrl) {
		this.connect = new ConnectDB(dbType, dbDriver, dbHostName,
				dbPortNumber, dbName, dbUserName, dbPassword, dbUrl);
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

	public boolean Insert(String tableName, HashMap<String, Object> columnValues)
			throws Exception {

		StringBuilder query = new StringBuilder("insert into " + tableName);
		StringBuilder valueInsert = new StringBuilder();
		query.append("(");
		for (String key : columnValues.keySet()) {
			query.append(key + ", ");
			valueInsert.append("'" + columnValues.get(key).toString() + "', ");
		}
		query = query.delete(query.length() - 2, query.length());
		valueInsert = valueInsert.delete(valueInsert.length() - 2,
				valueInsert.length());
		query.append(") values (" + valueInsert.toString() + ")");
		System.out.println(query);
		return this.connect.executeUpdate(query.toString()) > 0;
	}

	public boolean InsertMultiRow(String tableName, String[] columnName,
			Object[][] value) throws Exception {

		StringBuilder valueInsert = new StringBuilder();
		for (int i = 0; i < value.length; i++) {
			valueInsert.append("(");
			for (int j = 0; j < value[i].length; j++) {
				valueInsert.append("'" + value[i][j] + "', ");
			}
			valueInsert.delete(valueInsert.length() - 2, valueInsert.length());
			valueInsert.append("),");
		}
		valueInsert.delete(valueInsert.length() - 1, valueInsert.length());
		valueInsert.append(";");

		StringBuilder query = new StringBuilder("insert into " + tableName);
		query.append("(");
		for (int i = 0; i < columnName.length; i++)
			query.append(columnName[i] + ", ");
		query.delete(query.length() - 2, query.length());
		query.append(") values " + valueInsert.toString());
		System.out.println(query);
		return this.connect.executeUpdate(query.toString()) > 0;
	}

	//
	public ResultSet prepareCall(String spName, ArrayList<Object> parameter)
			throws Exception {

		return this.connect.executeStore(spName, parameter);
	}

	public ResultSet SystemSelect(String query) throws Exception {
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
		// query.append(";");
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

	public static void main(String args[]) {

		try {

			// create a DBFReader object
			// take dbf file as program argument
			InputStream inputStream = new FileInputStream("Y:\\BL_0701.DBF");
			DBFReader reader = new DBFReader(inputStream);

			// get the field count if you want for some reasons like the
			// following
			//
			int numberOfFields = reader.getFieldCount();

			// use this count to fetch all field information
			// if required
			//
			for (int i = 0; i < numberOfFields; i++) {

				DBFField field = reader.getField(i);

				// do something with it if you want
				// refer the JavaDoc API reference for more details
				//
				System.out.println(field.getName());
			}

			// Now, lets us start reading the rows
			//
			Object[] rowObjects;

			while ((rowObjects = reader.nextRecord()) != null) {
				System.out.println(rowObjects[1]);
				// for (int i = 0; i < rowObjects.length; i++) {
				// System.out.println(rowObjects[i]);
				// }
			}

			// By now, we have itereated through all of the rows

			inputStream.close();
		} catch (DBFException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}