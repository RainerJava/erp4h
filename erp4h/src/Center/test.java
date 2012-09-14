package Center;

import java.sql.ResultSet;

import DataAccessLayer.MySQLConnectUnit;

public class test {
	public static void main(String[] arg) throws Exception{
//		String[] s={"UserID","UserName","tttt"};
//		String[] t={"table1 as t1","t2","t3"};
//		
////		String q=con.Select(s, null, null);
//		
//		System.out.println(Select(s, t, null));
		MySQLConnectUnit con=new MySQLConnectUnit();
		con=DataAccessLayer.DataAccess.getDAL();
		ResultSet rs=con.SelectFromStore("spUser_SelectUserRight");
		while(rs.next()){
			System.out.println(rs.getString("GroupRight"));
		}
	}
	public static String Select(String[] fieldName, String[] tableName,
			String[] condition) {
		StringBuilder query = new StringBuilder("select ");
		for (int i = 0; i < fieldName.length; i++) {
			query.append(fieldName[i] + ", ");
		}
		query.delete(query.length() - 2, query.length() + 100);
		query.append(" from " + tableName[0]);
		for(int i=1;i<tableName.length;i++){
			query.append("inner join "+tableName[i-1]+" on ");
		}
		query.append(";");
		return query.toString();
	}
	 
}
