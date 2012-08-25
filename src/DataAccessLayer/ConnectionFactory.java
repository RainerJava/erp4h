package DataAccessLayer;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
    private static Connection con;

    private ConnectionFactory()throws Exception{
    	String url="";//tùy vào cơ sở dữ liệu
        Class.forName(url);
        String dburl="";//tùy vào cơ sở dữ liệu
        String username="";//tên người dùng của bạn trên cơ sở dữ liệu
        String password="";//mật khẩu người dùng
        con=DriverManager.getConnection(dburl,username,password);
    }
    public static ConnectionFactory getInstance()throws Exception{
        return new ConnectionFactory();
    }

    public Connection getConnection(){
        return con;
    }
    public void closeConnection()throws Exception{
        if(con!=null)
            con.close();
    }
}