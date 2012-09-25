/**
 * dac ta thong tin ve thiet bi tin hoc
 */
package org.erp4h.dto;

import java.sql.Date;

/**
 * @author HieuLV
 * dac ta thong tin ve thiet bi tin hoc
 */
public class ThietBiDTO {
	private int ThietBiID;
	private String TenThietBi;
	private String CauHinh;
	private int LoaiThietBiID;
	private int KhoaPhongID;
	private Date NgayMua;
	private Date NgaySuDung;
	private int BaoHanh;
	private String SoHopDong;
	
	public int getBaoHanh() {
		return BaoHanh;
	}
	public String getCauHinh() {
		return CauHinh;
	}
	public int getKhoaPhongID() {
		return KhoaPhongID;
	}
	public int getLoaiThietBiID() {
		return LoaiThietBiID;
	}
	public Date getNgayMua() {
		return NgayMua;
	}
	public Date getNgaySuDung() {
		return NgaySuDung;
	}
	public String getSoHopDong() {
		return SoHopDong;
	}
	public String getTenThietBi() {
		return TenThietBi;
	}
	public int getThietBiID() {
		return ThietBiID;
	}
	public void setBaoHanh(int baoHanh) {
		BaoHanh = baoHanh;
	}
	public void setCauHinh(String cauHinh) {
		CauHinh = cauHinh;
	}
	public void setKhoaPhongID(int khoaPhongID) {
		KhoaPhongID = khoaPhongID;
	}
	public void setLoaiThietBiID(int loaiThietBiID) {
		LoaiThietBiID = loaiThietBiID;
	}
	public void setNgayMua(Date ngayMua) {
		NgayMua = ngayMua;
	}
	public void setNgaySuDung(Date ngaySuDung) {
		NgaySuDung = ngaySuDung;
	}
	public void setSoHopDong(String soHopDong) {
		SoHopDong = soHopDong;
	}
	public void setTenThietBi(String tenThietBi) {
		TenThietBi = tenThietBi;
	}
	public void setThietBiID(int thietBiID) {
		ThietBiID = thietBiID;
	}
}
