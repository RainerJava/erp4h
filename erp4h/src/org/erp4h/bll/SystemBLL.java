package org.erp4h.bll;

import java.sql.ResultSet;
import java.sql.Timestamp;

import org.erp4h.dal.ConnectUtils;

public class SystemBLL {
	ConnectUtils connect;
	public SystemBLL() throws Exception{
		this.connect=org.erp4h.dal.DataAccess.getDAL();
	}
	public Timestamp getServerTime() throws Exception{
		ResultSet rs=connect.SystemSelect("select current_timestamp as currenttime");
		rs.first();
		return rs.getTimestamp(1);
	}
}
