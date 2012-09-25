package org.erp4h.dal;
import org.erp4h.dal.ConnectUtils;

public class DataAccess 
{
	public static ConnectUtils getDAL()
	{
		return new ConnectUtils("localhost","root","352007","erp4h");
	}
}
