package org.erp4h.dal;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
    private static Connection con;

    public static ConnectionFactory getInstance()throws Exception{
        return new ConnectionFactory();
    }
    private ConnectionFactory()throws Exception{
    	String url="";//tùy vào cơ sở dữ liệu
        Class.forName(url);
        String dburl="";//tùy vào cơ sở dữ liệu
        String username="";//tên ngư�?i dùng của bạn trên cơ sở dữ liệu
        String password="";//mật khẩu ngư�?i dùng
        con=DriverManager.getConnection(dburl,username,password);
    }

    public void closeConnection()throws Exception{
        if(con!=null)
            con.close();
    }
    public Connection getConnection(){
        return con;
    }
}