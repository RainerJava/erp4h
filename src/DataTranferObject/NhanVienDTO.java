package DataTranferObject;

import java.sql.Date;

public class NhanVienDTO {
	private String NhanVienID;
	private String KhoaPhongID;
	private String HoTen;
	private String SoBHXH;
	private String SoCMND;
	private String SoATM;
	private Date NgaySinh;
	private int GioiTinh;
	
	public String getNhanVienID() {
		return NhanVienID;
	}
	public void setNhanVienID(String nhanVienID) {
		NhanVienID = nhanVienID;
	}
	
	public String getKhoaPhongID() {
		return KhoaPhongID;
	}
	public void setKhoaPhongID(String khoaPhongID) {
		KhoaPhongID = khoaPhongID;
	}
	
	public String getHoTen() {
		return HoTen;
	}
	public void setHoTen(String hoTen) {
		HoTen = hoTen;
	}
	
	public String getSoBHXH() {
		return SoBHXH;
	}
	public void setSoBHXH(String soBHXH) {
		SoBHXH = soBHXH;
	}
	
	public String getSoCMND() {
		return SoCMND;
	}
	public void setSoCMND(String soCMND) {
		SoCMND = soCMND;
	}
	
	public String getSoATM() {
		return SoATM;
	}
	public void setSoATM(String soATM) {
		SoATM = soATM;
	}
	
	public Date getNgaySinh() {
		return NgaySinh;
	}
	public void setNgaySinh(Date ngaySinh) {
		NgaySinh = ngaySinh;
	}
	
	public int getGioiTinh() {
		return GioiTinh;
	}
	public void setGioiTinh(int gioiTinh) {
		GioiTinh = gioiTinh;
	}
}
