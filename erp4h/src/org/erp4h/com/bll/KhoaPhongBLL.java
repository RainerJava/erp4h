package org.erp4h.com.bll;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.HashSet;

import org.erp4h.dal.ConnectUtils;
import org.erp4h.dto.KhoaPhongDTO;


public class KhoaPhongBLL {
	ConnectUtils connect;
	
	public KhoaPhongBLL(){
		this.connect=org.erp4h.dal.DataAccess.getDAL();
	}
	
	public void Delete(HashSet<KhoaPhongDTO> HS_KhoaPhong) throws Exception{
		for(KhoaPhongDTO DTO_KhoaPhong:HS_KhoaPhong)
			this.Delete(DTO_KhoaPhong);
	}
	
	public void Delete(KhoaPhongDTO DTO_KhoaPhong) throws Exception{
		this.connect.Delete("tblKhoaPhong", "KhoaPhongID"+DTO_KhoaPhong.getKhoaPhongID());		
	}
	
	public KhoaPhongDTO getByID(int id) throws Exception{
		HashSet<KhoaPhongDTO>HS_KhoaPhong=this.getKhoaPhong("KhoaPhongID="+id);
		if(HS_KhoaPhong.size()>0)
			return HS_KhoaPhong.toArray(new KhoaPhongDTO[HS_KhoaPhong.size()])[0];
		return null;
	}
	
	public String[]getColumnName() throws Exception
	{
		ResultSet rs=this.connect.Select("tblKhoaPhong");
		ResultSetMetaData rsMetaData=rs.getMetaData();
		int ColumnCount=rsMetaData.getColumnCount();
		String[]list=new String[ColumnCount];
		for(int i=0;i<ColumnCount;i++){
			list[i]=rsMetaData.getColumnName(i+1);
		}
		return list;
	}
	
	public HashSet<KhoaPhongDTO>getKhoaPhong() throws Exception{
		return getKhoaPhong(null);
	}
	
	public HashSet<KhoaPhongDTO>getKhoaPhong(String Condition) throws Exception{
		return getKhoaPhong(Condition, null);
	}
	
	public HashSet<KhoaPhongDTO>getKhoaPhong(String Condition, String OrderBy) throws Exception{
		ResultSet rs=this.connect.Select("tblKhoaPhong", Condition, OrderBy);
		HashSet<KhoaPhongDTO>HS_KhoaPhong=new HashSet<KhoaPhongDTO>();
		while(rs.next()){
			KhoaPhongDTO DTO_KhoaPhong=new KhoaPhongDTO();
			DTO_KhoaPhong.setKhoaPhongID(rs.getInt("KhoaPhongID"));
			DTO_KhoaPhong.setTenKhoaPhong(rs.getString("TenKhoaPhong"));
			HS_KhoaPhong.add(DTO_KhoaPhong);
		}
		return HS_KhoaPhong;
	}
	
	public void Insert(HashSet<KhoaPhongDTO>HS_KhoaPhong) throws Exception{
		for(KhoaPhongDTO DTO_KhoaPhong:HS_KhoaPhong)
			this.Insert(DTO_KhoaPhong);
	}
	
	public void Insert(KhoaPhongDTO DTO_KhoaPhong) throws Exception{
		HashMap<String, Object>map=new HashMap<String, Object>();
		map.put("KhoaPhongID", DTO_KhoaPhong.getKhoaPhongID());
		map.put("TenKhoaPhong", DTO_KhoaPhong.getTenKhoaPhong());
		this.connect.Insert("tblKhoaPhong", map);
	}
	
	public void Update(HashSet<KhoaPhongDTO> HS_KhoaPhong) throws Exception{
		for(KhoaPhongDTO DTO_KhoaPhong:HS_KhoaPhong)
			this.Update(DTO_KhoaPhong);
	}
	
	public void Update(KhoaPhongDTO DTO_KhoaPhong) throws Exception{
		HashMap<String, Object>map=new HashMap<String, Object>();
		map.put("KhoaPhongID", DTO_KhoaPhong.getKhoaPhongID());
		map.put("TenKhoaPhong", DTO_KhoaPhong.getTenKhoaPhong());
		this.connect.Update("tblKhoaPhong", map, "KhoaPhongID"+DTO_KhoaPhong.getKhoaPhongID());
	}
}