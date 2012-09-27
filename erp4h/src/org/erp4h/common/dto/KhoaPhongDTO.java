package org.erp4h.common.dto;

public class KhoaPhongDTO {
	private int KhoaPhongID;
	private String TenKhoaPhong;
	
	public KhoaPhongDTO(){
		super();
	}

	public KhoaPhongDTO(int KhoaPhongID, String TenKhoaPhong) {
		setKhoaPhongID(KhoaPhongID);
		setTenKhoaPhong(TenKhoaPhong);
	}

	public int getKhoaPhongID() {
		return KhoaPhongID;
	}
	public String getTenKhoaPhong() {
		return TenKhoaPhong;
	}

	public void setKhoaPhongID(int khoaPhongID) {
		KhoaPhongID = khoaPhongID;
	}
	public void setTenKhoaPhong(String tenKhoaPhong) {
		TenKhoaPhong = tenKhoaPhong;
	}
	
	@Override
	public String toString(){
		return TenKhoaPhong+" ["+KhoaPhongID+"]";
	}
}
