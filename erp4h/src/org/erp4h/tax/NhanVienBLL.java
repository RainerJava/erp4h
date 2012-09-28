package org.erp4h.tax;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.erp4h.dal.ConnectUtils;
import org.erp4h.dto.NhanVienDTO;

public class NhanVienBLL {
	private ConnectUtils connect;

	public NhanVienBLL() {
		this.connect = org.erp4h.dal.DataAccess.getDAL();
	}

	public ArrayList<NhanVienDTO> getArrNhanVien() {
		return getArrNhanVien(null);
	}

	public ArrayList<NhanVienDTO> getArrNhanVien(String condition) {
		return getArrNhanVien(condition, null);
	}

	public ArrayList<NhanVienDTO> getArrNhanVien(String condition,
			String orderBy) {
		ArrayList<NhanVienDTO> arrNhanVien = new ArrayList<NhanVienDTO>();
		try {
			ResultSet rs = this.connect.Select("tblNhanVien", condition,
					orderBy);
			if(rs!=null){
				while (rs.next()) {
					NhanVienDTO dtoNhanVien = new NhanVienDTO();
					dtoNhanVien.setNhanVienID(rs.getString("NhanVienID"));
					dtoNhanVien.setKhoaPhongID(rs.getString("KhoaPhongID"));
					dtoNhanVien.setHoTen(rs.getString("HoTen"));
					dtoNhanVien.setSoBHXH(rs.getString("SoBHXH"));
					dtoNhanVien.setSoCMND(rs.getString("SoCMND"));
					dtoNhanVien.setSoATM(rs.getString("SoATM"));
					dtoNhanVien.setNgaySinh(rs.getDate("NgaySinh"));
					dtoNhanVien.setGioiTinh(rs.getInt("GioiTinh"));
					arrNhanVien.add(dtoNhanVien);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Neu khong co nhan vien phu hop voi dieu kien thi ...
		return arrNhanVien;
	}

}
