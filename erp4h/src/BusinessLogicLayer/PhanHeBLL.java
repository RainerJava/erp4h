package BusinessLogicLayer;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.HashSet;

import DataAccessLayer.MySQLConnectUnit;
import DataTranferObject.PhanHeDTO;

public class PhanHeBLL {
	MySQLConnectUnit connect;
	
	public PhanHeBLL(){
		this.connect=DataAccessLayer.DataAccess.getDAL();
	}
	
	public HashSet<PhanHeDTO>getPhanHe(String Condition, String OrderBy) throws Exception{
		ResultSet rs=this.connect.Select("tblPhanHe", Condition, OrderBy);
		HashSet<PhanHeDTO> hsPhanHe=new HashSet<PhanHeDTO>();
		while(rs.next()){
			PhanHeDTO PhanHe=new PhanHeDTO();
			PhanHe.setPhanHeID(rs.getInt("PhanHeID"));
			PhanHe.setTenPhanHe(rs.getString("TenPhanHe"));
			hsPhanHe.add(PhanHe);
		}
		return hsPhanHe;
	}
	
	public HashSet<PhanHeDTO>getPhanHe(String Condition) throws Exception{
		return getPhanHe(Condition, null);
	}
	
	public HashSet<PhanHeDTO>getPhanHe() throws Exception{
		return getPhanHe(null);
	}
	
	public PhanHeDTO getByID(int id) throws Exception{
		HashSet<PhanHeDTO>hsPhanHe=this.getPhanHe("PhanHeID="+id);
		if(hsPhanHe.size()>0)
			return hsPhanHe.toArray(new PhanHeDTO[hsPhanHe.size()])[0];
		return null;
	}
	
	public void Insert(PhanHeDTO PhanHe) throws Exception{
		HashMap<String, Object>map=new HashMap<String, Object>();
		map.put("PhanHeID", PhanHe.getPhanHeID());
		map.put("TenPhanHe", PhanHe.getTenPhanHe());
		this.connect.Insert("tblPhanHe", map);
	}
	
	public void Insert(HashSet<PhanHeDTO> hsPhanHe) throws Exception{
		for(PhanHeDTO PhanheDTO:hsPhanHe)
			this.Insert(PhanheDTO);
	}
	
	public void Update(PhanHeDTO PhanHe) throws Exception{
		HashMap<String, Object>map=new HashMap<String, Object>();
		map.put("TenPhanHe", PhanHe.getTenPhanHe());
		this.connect.Update("tblPhanHe", map, "PhanHeID"+PhanHe.getPhanHeID());
	}
	
	public void Update(HashSet<PhanHeDTO> hsPhanHe) throws Exception{
		for(PhanHeDTO DTO_PhanHe:hsPhanHe)
			this.Update(DTO_PhanHe);
	}
	
	public void Delete(PhanHeDTO PhanHe) throws Exception{
		this.connect.Delete("tblPhanHe", "PhanHeID"+PhanHe.getPhanHeID());		
	}
	
	public void Delete(HashSet<PhanHeDTO> hsPhanHe) throws Exception{
		for(PhanHeDTO PhanheDTO:hsPhanHe)
			this.Delete(PhanheDTO);		
	}
}
