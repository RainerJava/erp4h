package BusinessLogicLayer;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.HashSet;

import DataAccessLayer.MySQLConnectUnit;
import DataTranferObject.ThietBiDTO;

/**
 * @author: hieulv
 */
public class ThietBiBLL {
	MySQLConnectUnit connect;
	
	public ThietBiBLL(){
		this.connect=DataAccessLayer.DataAccess.getDAL();
	}
	/**
	 * lay mang du lieu
	 * @return tra ve mot mang du lieu thiet bi
	 */
	public HashSet<ThietBiDTO>getThietBi(String Condition, String OrderBy) throws Exception{
		ResultSet rs=this.connect.Select("tblThietBi", Condition, OrderBy);
		HashSet<ThietBiDTO>HS_ThietBi=new HashSet<ThietBiDTO>();
		while(rs.next()){
			ThietBiDTO DTO_ThietBi=new ThietBiDTO();
			DTO_ThietBi.setThietBiID(rs.getInt("ThietBiID"));
			DTO_ThietBi.setTenThietBi(rs.getString("TenThietBi"));
			DTO_ThietBi.setCauHinh(rs.getString("CauHinh"));
			DTO_ThietBi.setLoaiThietBiID(rs.getInt("LoaiThietBiID"));
			DTO_ThietBi.setKhoaPhongID(rs.getInt("KhoaPhongID"));
			DTO_ThietBi.setNgayMua(rs.getDate("NgayMua"));
			DTO_ThietBi.setNgaySuDung(rs.getDate("NgaySuDung"));
			DTO_ThietBi.setBaoHanh(rs.getInt("BaoHanh"));
			DTO_ThietBi.setSoHopDong(rs.getString("SoHopDong"));
			HS_ThietBi.add(DTO_ThietBi);
		}
		return HS_ThietBi;
	}
	
	public HashSet<ThietBiDTO>getThietBi(String Condition) throws Exception{
		return getThietBi(Condition, null);
	}
	
	public HashSet<ThietBiDTO>getThietBi() throws Exception{
		return getThietBi(null);
	}
	
	public ThietBiDTO getByID(int id) throws Exception{
		HashSet<ThietBiDTO>HS_ThietBi=this.getThietBi("ThietBiID="+id);
		if(HS_ThietBi.size()>0)
			return HS_ThietBi.toArray(new ThietBiDTO[HS_ThietBi.size()])[0];
		return null;
	}
	
	public void Insert(ThietBiDTO DTO_ThietBi) throws Exception{
		HashMap<String, Object>map=new HashMap<String, Object>();
		map.put("ThietBiID", DTO_ThietBi.getThietBiID());
		map.put("TenThietBi", DTO_ThietBi.getTenThietBi());
		map.put("CauHinh", DTO_ThietBi.getCauHinh());
		map.put("LoaiThietBiID", DTO_ThietBi.getLoaiThietBiID());
		map.put("KhoaPhongID", DTO_ThietBi.getKhoaPhongID());
		map.put("NgayMua", DTO_ThietBi.getNgayMua());
		map.put("NgaySuDung", DTO_ThietBi.getNgaySuDung());
		map.put("BaoHanh", DTO_ThietBi.getBaoHanh());
		map.put("SoHopDong", DTO_ThietBi.getSoHopDong());
		this.connect.Insert("tblThietBi", map);
	}
	
	public void Insert(HashSet<ThietBiDTO>HS_ThietBi) throws Exception{
		for(ThietBiDTO DTO_ThietBi:HS_ThietBi)
			this.Insert(DTO_ThietBi);
	}
	
	public void Update(ThietBiDTO DTO_ThietBi) throws Exception{
		HashMap<String, Object>map=new HashMap<String, Object>();
		map.put("ThietBiID", DTO_ThietBi.getThietBiID());
		map.put("TenThietBi", DTO_ThietBi.getTenThietBi());
		map.put("CauHinh", DTO_ThietBi.getCauHinh());
		map.put("LoaiThietBiID", DTO_ThietBi.getLoaiThietBiID());
		map.put("KhoaPhongID", DTO_ThietBi.getKhoaPhongID());
		map.put("NgayMua", DTO_ThietBi.getNgayMua());
		map.put("NgaySuDung", DTO_ThietBi.getNgaySuDung());
		map.put("BaoHanh", DTO_ThietBi.getBaoHanh());
		map.put("SoHopDong", DTO_ThietBi.getSoHopDong());
		this.connect.Update("tblKhoaPhong", map, "KhoaPhongID"+DTO_ThietBi.getThietBiID());
	}
	
	public void Update(HashSet<ThietBiDTO> HS_ThietBi) throws Exception{
		for(ThietBiDTO DTO_ThietBi:HS_ThietBi)
			this.Update(DTO_ThietBi);
	}
	
	public void Delete(ThietBiDTO DTO_ThietBi) throws Exception{
		this.connect.Delete("tblThietBi", "ThietBiID"+DTO_ThietBi.getThietBiID());		
	}
	
	public void Delete(HashSet<ThietBiDTO> HS_ThietBi) throws Exception{
		for(ThietBiDTO DTO_ThietBi:HS_ThietBi)
			this.Delete(DTO_ThietBi);
	}
	
	public String[]getColumnName() throws Exception
	{
		ResultSet rs=this.connect.Select("tblThietBi");
		ResultSetMetaData rsMetaData=rs.getMetaData();
		int ColumnCount=rsMetaData.getColumnCount();
		String[]list=new String[ColumnCount];
		for(int i=0;i<ColumnCount;i++){
			list[i]=rsMetaData.getColumnName(i+1);
		}
		return list;
	}
	
	public int getColumnCount() throws Exception{
		ResultSet rs=this.connect.Select("tblThietBi");
		int count=rs.getMetaData().getColumnCount();
		return count;
	}

	public int getRowCount() throws Exception{
		int count=0;
		for(int i=0;i<this.getColumnCount();i++){
			++count;
		}
		return count;
	}
}