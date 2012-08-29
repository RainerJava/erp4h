package DataAccessLayer;
import java.sql.*;

public class MySQLConnect {
	String Host="";
	String UserName="";
	String Password="";
	String DataBase="";
	public String ThongBao="";
	
	Connection connect=null;
	Statement statement=null;
	ResultSet result=null;
	
	public MySQLConnect(String Host,String UserName,String Password,String DataBase)
	{
		this.Host=Host;
		this.UserName=UserName;
		this.Password=Password;
		this.DataBase=DataBase;
	}
	
	public boolean driverTest() throws Exception
	{
		try
		{
			Class.forName("org.gjt.mm.mysql.Driver");
			System.out.println("Dang nap trinh dieu khien...");
			ThongBao="Dang nap trinh dieu khien...";
			return true;
		}
		catch (java.lang.ClassNotFoundException e)
		{
			throw new Exception("Khong tim thay trinh dieu khien...");
		}
	}
	
	public Connection getConnect() throws Exception
	{
		if (this.connect==null)
		{
			driverTest();
			String url="jdbc:mysql://" + this.Host + ":3306/" + this.DataBase;
			try
			{
				this.connect=DriverManager.getConnection(url, this.UserName, this.Password);
				System.out.println("Ket noi thanh cong.");
				ThongBao="Ket noi thanh cong.";
			}
			catch(java.sql.SQLException e)
			{
				throw new Exception("Khong ket noi den DBMS: " +url+e.getMessage());
			}
		}
		return this.connect;
	}
	
	protected Statement getStatement() throws Exception
	{
		if (this.statement==null? true: this.statement.isClosed())
		{
			this.statement=getConnect().createStatement();
		}
		return this.statement;
	}
	
	public ResultSet executeQuery(String Query) throws Exception
	{
		try
		{
			this.result=getStatement().executeQuery(Query);
		}
		catch(Exception e)
		{
			throw new Exception("Error: "+e.getMessage()+" - "+Query);
		}
		return this.result;
	}
	
	public int executeUpdate(String Query) throws Exception
	{
		int res=Integer.MIN_VALUE;
		try
		{
			res=getStatement().executeUpdate(Query);
		}
		catch(Exception e)
		{
			throw new Exception("Error: "+e.getMessage()+" - "+Query);
		}
		finally
		{
			this.Close();
		}
		return res;
	}
	
	public void Close() throws SQLException
	{
		//nu re 
		if(this.result!=null && !this.result.isClosed())
		{
			this.result.close();
			this.result=null;
		}
		if(this.statement!=null && !this.statement.isClosed())
		{
			this.statement.close();
			this.statement=null;
		}
		if(this.connect!=null && !this.connect.isClosed())
		{
			this.connect.close();
			this.connect=null;
		}
	}
}
