package DataAccessLayer;
import DataAccessLayer.MySQLConnectUnit;

public class DataAccess 
{
	public static MySQLConnectUnit getDAL()
	{
		return new MySQLConnectUnit("localhost","root","352007","erp4h");
	}
}