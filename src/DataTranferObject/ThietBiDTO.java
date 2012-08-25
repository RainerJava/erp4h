/**
 * dac ta thong tin ve thiet bi tin hoc
 */
package DataTranferObject;

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
	
	public int getThietBiID() {
		return ThietBiID;
	}
	public void setThietBiID(int thietBiID) {
		ThietBiID = thietBiID;
	}
	public String getTenThietBi() {
		return TenThietBi;
	}
	public void setTenThietBi(String tenThietBi) {
		TenThietBi = tenThietBi;
	}
	public String getCauHinh() {
		return CauHinh;
	}
	public void setCauHinh(String cauHinh) {
		CauHinh = cauHinh;
	}
	public int getLoaiThietBiID() {
		return LoaiThietBiID;
	}
	public void setLoaiThietBiID(int loaiThietBiID) {
		LoaiThietBiID = loaiThietBiID;
	}
	public int getKhoaPhongID() {
		return KhoaPhongID;
	}
	public void setKhoaPhongID(int khoaPhongID) {
		KhoaPhongID = khoaPhongID;
	}
	public Date getNgayMua() {
		return NgayMua;
	}
	public void setNgayMua(Date ngayMua) {
		NgayMua = ngayMua;
	}
	public Date getNgaySuDung() {
		return NgaySuDung;
	}
	public void setNgaySuDung(Date ngaySuDung) {
		NgaySuDung = ngaySuDung;
	}
	public int getBaoHanh() {
		return BaoHanh;
	}
	public void setBaoHanh(int baoHanh) {
		BaoHanh = baoHanh;
	}
	public String getSoHopDong() {
		return SoHopDong;
	}
	public void setSoHopDong(String soHopDong) {
		SoHopDong = soHopDong;
	}
}
