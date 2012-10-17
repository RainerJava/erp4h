package org.erp4h.common.bll;

import java.net.InetAddress;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;

public class ImportData extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static org.erp4h.dal.ConnectUtils con;
	private JTextField tfMonth;
	private JTextField tfYear;

	public ImportData() {
		setTitle("Import Tax Data");
		this.con = org.erp4h.dal.DataAccess.getDataAccess();
		getContentPane().setLayout(null);
		
		tfMonth = new JTextField();
		tfMonth.setBounds(155, 28, 86, 20);
		getContentPane().add(tfMonth);
		tfMonth.setColumns(10);
		
		tfYear = new JTextField();
		tfYear.setBounds(155, 59, 86, 20);
		getContentPane().add(tfYear);
		tfYear.setColumns(10);
		
		JButton btnClose = new JButton("Close");
		btnClose.setBounds(341, 239, 91, 23);
		getContentPane().add(btnClose);
		
		JButton btnImport = new JButton("Import");
		btnImport.setBounds(240, 239, 91, 23);
		getContentPane().add(btnImport);
		
		JLabel lblMonth = new JLabel("Thang");
		lblMonth.setBounds(99, 31, 46, 14);
		getContentPane().add(lblMonth);
		
		JLabel lblYear = new JLabel("Nam");
		lblYear.setBounds(99, 62, 46, 14);
		getContentPane().add(lblYear);
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
	
	private static void insertMultiRowData() throws Exception{
		ResultSet rs1 = con.Select("tblLoaiThuNhap", "not isnull(MapField)");
		ResultSet rs2 = con.Select("tblbc1209","luong>0");
		
		String tableName="tblThuNhap";
		String[] columnName=new String[]{"nam","thang","nhanvienid","thunhapid","sotien"};
		Object[][] value;
		
		rs2.last();
		value=new Object[rs2.getRow()][columnName.length];
		rs2.beforeFirst();
		while(rs1.next()){
			
		}
		int i=0;
		while(rs2.next()){
			value[i][0]=2012;
			value[i][1]=9;
			value[i][2]=rs2.getString("MaNV");
			value[i][3]=1;
			value[i][4]=rs2.getInt("luong");
			i++;
		}
		con.InsertMultiRow("tblThuNhap", columnName, value);
		System.out.println("Done!");
	}
	
	public static void main(String[] args) throws Exception {
		ImportData imp = new ImportData();
//		getData();
//		insertData();
		insertMultiRowData();
	}
}
