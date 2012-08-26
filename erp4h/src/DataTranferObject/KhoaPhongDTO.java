package DataTranferObject;

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
	public void setKhoaPhongID(int khoaPhongID) {
		KhoaPhongID = khoaPhongID;
	}

	public String getTenKhoaPhong() {
		return TenKhoaPhong;
	}
	public void setTenKhoaPhong(String tenKhoaPhong) {
		TenKhoaPhong = tenKhoaPhong;
	}
	
	@Override
	public String toString(){
		return TenKhoaPhong+" ["+KhoaPhongID+"]";
	}
}
