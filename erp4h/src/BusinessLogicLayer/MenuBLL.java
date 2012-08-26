package BusinessLogicLayer;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.HashSet;

import DataAccessLayer.MySQLConnectUnit;
import DataTranferObject.MenuDTO;

public class MenuBLL {
	MySQLConnectUnit connect;
	
	public MenuBLL(){
		this.connect=DataAccessLayer.DataAccess.getDAL();
	}
	
	public HashSet<MenuDTO>getMenu(String Condition, String OrderBy) throws Exception{
		ResultSet rs=this.connect.Select("tblMenu", Condition, OrderBy);
		HashSet<MenuDTO>HS_Menu=new HashSet<MenuDTO>();
		while(rs.next()){
			MenuDTO DTO_Menu=new MenuDTO();
			DTO_Menu.setMenuID(rs.getInt("MenuID"));
			DTO_Menu.setMenuPosition(rs.getInt("MenuPosition"));
			DTO_Menu.setMenuValue(rs.getString("MenuValue"));
			DTO_Menu.setMenuFiliationID(rs.getInt("MenuFiliationID"));
			DTO_Menu.setFormName(rs.getString("FormName"));
			DTO_Menu.setPhanHeID(rs.getInt("PhanHeID"));
			HS_Menu.add(DTO_Menu);
		}
		return HS_Menu;
	}
	
	public HashSet<MenuDTO>getMenu(String Condition) throws Exception{
		return getMenu(Condition, null);
	}
	
	public HashSet<MenuDTO>getMenu() throws Exception{
		return getMenu(null);
	}
	
	public MenuDTO getByID(int id) throws Exception{
		HashSet<MenuDTO>HS_Menu=this.getMenu("MenuID="+id);
		if(HS_Menu.size()>0)
			return HS_Menu.toArray(new MenuDTO[HS_Menu.size()])[0];
		return null;
	}
	
	public void Insert(MenuDTO DTO_Menu) throws Exception{
		HashMap<String, Object>map=new HashMap<String, Object>();
		map.put("MenuID", DTO_Menu.getMenuID());
		map.put("MenuPosition", DTO_Menu.getMenuPosition());
		map.put("MenuValue", DTO_Menu.getMenuValue());
		map.put("MenuFiliationID", DTO_Menu.getMenuFiliationID());
		map.put("FormName", DTO_Menu.getFormName());
		map.put("PhanHeID", DTO_Menu.getPhanHeID());
		this.connect.Insert("tblMenu", map);
	}
	
	public void Insert(HashSet<MenuDTO> hsMenu) throws Exception{
		for(MenuDTO DTO_Menu:hsMenu)
			this.Insert(DTO_Menu);
	}
	
	public void Update(MenuDTO DTO_Menu) throws Exception{
		HashMap<String, Object>map=new HashMap<String, Object>();
		map.put("MenuID", DTO_Menu.getMenuID());
		map.put("MenuPosition", DTO_Menu.getMenuPosition());
		map.put("MenuValue", DTO_Menu.getMenuValue());
		map.put("MenuFiliationID", DTO_Menu.getMenuFiliationID());
		map.put("FormName", DTO_Menu.getFormName());
		map.put("PhanHeID", DTO_Menu.getPhanHeID());	
		this.connect.Update("tblMenu", map, "MenuID"+DTO_Menu.getMenuID());
	}
	
	public void Update(HashSet<MenuDTO> HS_Menu) throws Exception{
		for(MenuDTO DTO_PhanHe:HS_Menu)
			this.Update(DTO_PhanHe);
	}
	
	public void Delete(MenuDTO DTO_Menu) throws Exception{
		this.connect.Delete("tblMenu", "MenuID"+DTO_Menu.getMenuID());		
	}
	
	public void Delete(HashSet<MenuDTO> HS_Menu) throws Exception{
		for(MenuDTO DTO_Menu:HS_Menu)
			this.Delete(DTO_Menu);		
	}
}
