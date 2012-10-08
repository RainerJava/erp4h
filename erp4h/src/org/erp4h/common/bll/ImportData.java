package org.erp4h.common.bll;

import java.net.InetAddress;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.HashMap;

import com.mysql.jdbc.ResultSetMetaData;

public class ImportData {
	static org.erp4h.dal.ConnectUtils con;

	public ImportData() {
		this.con = org.erp4h.dal.DataAccess.getDAL();
	}

	private static void getData() throws Exception {
		ResultSet rs = con.Select("tblbc1209");

		while (rs.next()) {
			System.out.println(rs.getString("MaNV"));
		}
	}

	private static void insertData() throws Exception {
		ResultSet rs = con.Select("tblbc1209");
		java.sql.ResultSetMetaData rsMeta=rs.getMetaData();
		for(int i=0;i<rsMeta.getColumnCount();i++){
			System.out.println(rsMeta.getColumnName(i+1));
			if(rsMeta.getColumnName(i+1)==null){
				
			}
		}
//		while (rs.next()) {
//			if (rs.getInt("Luong") > 0) {
//				ImportData imp = new ImportData();
//				HashMap<String, Object> hs = new HashMap<String, Object>();
//				hs.put("NhanVienID", rs.getString("MaNV"));
//				hs.put("ThuNhapID", 2);
//				hs.put("SoTien", rs.getInt("ABC"));
//				hs.put("Nam", 2012);
//				hs.put("Thang", 9);
//				InetAddress.getLocalHost().getHostName();
//				hs.put("CreatedAtPC", InetAddress.getLocalHost().getHostName());
//				// Timestamp ts=new Timestamp(new java.util.Date().getTime());
//				hs.put("CreatedDate",
//						new Timestamp(new java.util.Date().getTime()));
//				con.Insert("tblThuNhap", hs);
//			}
//		}
	}

	public static void main(String[] args) throws Exception {

		ImportData imp = new ImportData();
		getData();
		insertData();
	}
}
