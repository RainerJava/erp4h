package org.erp4h.center;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.erp4h.dal.ConnectUtils;


public class test {
	public static void main(String[] arg) throws Exception{
//		String[] s={"UserID","UserName","tttt"};
//		String[] t={"table1 as t1","t2","t3"};
//		
////		String q=con.Select(s, null, null);
//		
//		System.out.println(Select(s, t, null));
		ConnectUtils conn=new ConnectUtils();
		conn=org.erp4h.dal.DataAccess.getDAL();
//		ResultSet rs=con.SelectFromStore("spUser_SelectUserRight");
//		while(rs.next()){
//			System.out.println(rs.getString("GroupRight"));
//		}
		
		
//		byte i = 3;
//		short j = 10;
//		int k = 1;
//		long x = 1234567L;
//		float y = 1.25f;
//		double z = 23.45d;
//		char ch = 'c';
//		boolean b = true;
//		String s = "Hello everybody";
//
//		java.util.Date today =
//		        new java.util.Date();
//		java.sql.Date sqlToday =
//				   new java.sql.Date(today.getTime());
//
		ArrayList<Object> arr=new ArrayList<Object>();
//		arr.add(i);
//		arr.add(j);
//		arr.add(k);
//		arr.add(x);
//		arr.add(y);
//		arr.add(z);
//		arr.add(ch);
//		arr.add(s);
//		arr.add(b);
//		arr.add(today);
//		arr.add(sqlToday);
		int PhanHeID=1;
		String UserID="hieulv";
		arr.add(PhanHeID);
		arr.add(UserID);
		ResultSet rs =conn.prepareCall("spUser_SelectUserRight", arr);
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
		for (int i = 1; i < tableName.length; i++) {
			query.append("inner join " + tableName[i - 1] + " on ");
		}
		query.append(";");
		return query.toString();
	}

}
