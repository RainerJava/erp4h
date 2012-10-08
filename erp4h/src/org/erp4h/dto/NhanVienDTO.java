package org.erp4h.dto;

import java.sql.Date;

public class NhanVienDTO {
	private int GioiTinh;
	private String HoTen;
	private String KhoaPhongID;
	private String NgaySinh;
	private String NhanVienID;
	private String SoATM;
	private String SoBHXH;
	private String SoCMND;
	
	public int getGioiTinh() {
		return GioiTinh;
	}
	public String getHoTen() {
		return HoTen;
	}
	
	public String getKhoaPhongID() {
		return KhoaPhongID;
	}
	public String getNgaySinh() {
		return NgaySinh;
	}
	
	public String getNhanVienID() {
		return NhanVienID;
	}
	public String getSoATM() {
		return SoATM;
	}
	
	public String getSoBHXH() {
		return SoBHXH;
	}
	public String getSoCMND() {
		return SoCMND;
	}
	
	public void setGioiTinh(int gioiTinh) {
		GioiTinh = gioiTinh;
	}
	public void setHoTen(String hoTen) {
		HoTen = hoTen;
	}
	
	public void setKhoaPhongID(String khoaPhongID) {
		KhoaPhongID = khoaPhongID;
	}
	public void setNgaySinh(String ngaySinh) {
		NgaySinh = ngaySinh;
	}
	
	public void setNhanVienID(String nhanVienID) {
		NhanVienID = nhanVienID;
	}
	public void setSoATM(String soATM) {
		SoATM = soATM;
	}
	
	public void setSoBHXH(String soBHXH) {
		SoBHXH = soBHXH;
	}
	public void setSoCMND(String soCMND) {
		SoCMND = soCMND;
	}
	public String tosString(){
		return getHoTen();
	}
}
