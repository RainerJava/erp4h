package DataAccessLayer;
import java.sql.*;

public class ConnectMySQL 
{
    String strHost;
    String strUserName;
    String strPassWord;
    String strDataBase;
    
    Connection connect = null;
    Statement statement = null;
    ResultSet resultset = null;
    
    public ConnectMySQL(){}
    
    public ConnectMySQL(String host, String username, String password, String database) {    
        this.strHost = host;
        this.strUserName = username;
        this.strPassWord = password;
        this.strDataBase = database;
    }
    
    /*
     * Kiểm tra Class Name
     * Nếu không tồn tại thì mém lỗi ra ngoài.
     */
    protected void driverTest () throws Exception{
        try {
        	Class.forName("org.gjt.mm.mysql.Driver");
        }
        catch (java.lang.ClassNotFoundException e)
        {
        	throw new Exception("Oracle JDBC Driver not found ... ");
        } 
    }
    /*
     * tao ket noi
     */
    protected Connection getConnect() throws Exception{
        if(this.connect == null)
        {
            driverTest();
            String url = "jdbc:mysql://"+ this.strHost+":3306/"+this.strDataBase;
            try 
            {
                // Tạo Connection thông qua Url
                this.connect = DriverManager.getConnection(url, this.strUserName, this.strPassWord);
                System.out.print("Kết nối thành công !\n");
            }
            // Nếu không thành công ném lỗi ra ngoài.
            catch (java.sql.SQLException e) {
                throw new Exception("Không thể kết nối đến DataBase Server: " + url + e.getMessage());
            }
       }
        // Trả connection ra ngoài.
        return this.connect;
    }
    
    protected Statement getStatement() throws Exception 
    {
        // Kiểm tra statement nếu = null hoặc đã đóng.
        if(this.statement == null ? true: this.statement.isClosed())
        {
            // Khởi tạo một statement mới.
            this.statement = this.getConnect().createStatement();
        }
        // Trả staement ra ngoài.
        return this.statement;
    }
    
    public ResultSet executeQuery(String Query) throws Exception
    {
        try{
            // Thực thi câu lệnh.
            this.resultset = getStatement().executeQuery(Query);
            System.out.print("Thuc thi query thanh cong !\n");
        }
        // Nếu không thành công ném lỗi ra ngoài.
        catch(Exception e){
            throw new Exception("Error : " +e.getMessage() +" - "+Query);
        }
        // Trả kết quả ra ngoài.
        return this.resultset;
    }
    
    // Thức thi các câu lệnh Inser, Update, Delete
    public int executeUpdate(String Query) throws Exception
    {
        //Khai báo biến int lưu trữ kết quả tình trạng thực thi câu lệnh Query.
        int res =Integer.MIN_VALUE;
        try{
            //Thực thi câu lệnh.
            res = getStatement().executeUpdate(Query);
        }
        //Nếu không thành công ném lỗi ra ngoài.
        catch(Exception e){
            throw new Exception("Error: " +e.getMessage() +" - "+Query);}
        finally{
            //Đóng kết nối.
            this.Close();
        }
        //Trả kết quả ra ngoài.
        return res;
    }
    
    public void Close() throws SQLException
    {
        // Nếu Result chưa đóng. Đóng result
        if(this.resultset!=null && ! this.resultset.isClosed())
        {
            this.resultset.close();
            this.resultset = null;
        }
        // Nếu statement chưa đóng. Đóng statement.
        if(this.statement!=null && !this.statement.isClosed())
        {
            this.statement.close();
            this.statement = null;
        }
        // Nếu connection chưa đóng. Đóng connection.
        if(this.connect!=null && !this.connect.isClosed())
        {
            this.connect.close();
            this.connect =null;
        }
    }
}