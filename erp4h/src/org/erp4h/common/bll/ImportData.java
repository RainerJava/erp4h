package org.erp4h.common.bll;

import java.net.InetAddress;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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

	private static void getColumnData(String colName, int ThuNhapID)
			throws Exception {
//		ImportData imp = new ImportData();
		ResultSet rs = con.Select("tblbc1209");
		int i=0;
		while (rs.next()) {
			if (rs.getInt(colName) > 0) {
				ImportData imp = new ImportData();
				HashMap<String, Object> hs = new HashMap<String, Object>();
				hs.put("NhanVienID", rs.getString("MaNV"));
				hs.put("ThuNhapID", ThuNhapID);
				hs.put("SoTien", rs.getInt(colName));
				hs.put("Nam", 2012);
				hs.put("Thang", 9);
				InetAddress.getLocalHost().getHostName();
				hs.put("CreatedAtPC", InetAddress.getLocalHost().getHostName());
				hs.put("CreatedDate",
						new Timestamp(new java.util.Date().getTime()));
				con.Insert("tblThuNhap", hs);
				i++;
			}
		}
		rs.beforeFirst();
		System.out.println(i);
		
	}

	private static void insertData() throws Exception {
		ResultSet rs = con.Select("tblLoaiThuNhap", "not isnull(MapField)");
		HashMap<Integer , Object> hm=new HashMap<Integer , Object>();
		
		while (rs.next()) {
			hm.put(rs.getInt("ThuNhapID"), rs.getString("MapField"));
		}
		Iterator iter = hm.entrySet().iterator();
		 
		while (iter.hasNext()) {
			Map.Entry mEntry = (Map.Entry) iter.next();
			System.out.println(mEntry.getKey() + " : " + mEntry.getValue());
			getColumnData(mEntry.getValue().toString(),(int)mEntry.getKey());
		}
//		for(int i=0;i<hm.size();i++)
//			getColumnData(hm.));

	}

	public static void main(String[] args) throws Exception {
		ImportData imp = new ImportData();
		getData();
		insertData();
	}
}
