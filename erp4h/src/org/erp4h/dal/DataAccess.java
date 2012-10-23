package org.erp4h.dal;

import java.sql.ResultSet;

import org.erp4h.dal.ConnectUtils;

public class DataAccess {
	public static ConnectUtils getDataAccess() {
		DatabaseDTO dtoDB = new DatabaseDTO("MySQL");
		return new ConnectUtils(dtoDB.getDbDriver(), dtoDB.getDbHostName(),
				dtoDB.getDbPortNumber(), dtoDB.getDbName(),
				dtoDB.getDbUserName(), dtoDB.getDbPassword(), dtoDB.getDbUrl());
	}

	public static ConnectUtils getDataAccess(String dbType) {
		DatabaseDTO dtoDB = new DatabaseDTO(dbType);
		return new ConnectUtils(dtoDB.getDbType(), dtoDB.getDbDriver(),
				dtoDB.getDbHostName(), dtoDB.getDbPortNumber(),
				dtoDB.getDbName(), dtoDB.getDbUserName(),
				dtoDB.getDbPassword(), dtoDB.getDbUrl());
	}

	static ConnectUtils connect;

	public static void main(String[] args) throws Exception {
		connect = org.erp4h.dal.DataAccess.getDataAccess("dfgfdg");
		ResultSet rs = connect.Select("tblUser");
		while (rs.next()) {
			System.out.println(rs.getString("UserID"));
		}
	}
}