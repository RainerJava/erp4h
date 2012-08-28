package BusinessLogicLayer;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import DataAccessLayer.MySQLConnectUnit;
import DataTranferObject.MenuDTO;

public class MenuBLL {
	MySQLConnectUnit connect;
	
	public MenuBLL(){
		this.connect=DataAccessLayer.DataAccess.getDAL();
	}
	
	public HashSet<MenuDTO>getMenuDTO(String Condition, String OrderBy) throws Exception{
		ResultSet rs=this.connect.Select("tblMenu", Condition, OrderBy);
		HashSet<MenuDTO>hsMenu=new HashSet<MenuDTO>();
		while(rs.next()){
			MenuDTO dtoMenu=new MenuDTO();
			dtoMenu.setMenuID(rs.getInt("MenuID"));
			dtoMenu.setMenuPosition(rs.getInt("MenuPosition"));
			dtoMenu.setMenuValue(rs.getString("MenuValue"));
			dtoMenu.setMenuFiliationID(rs.getInt("MenuFiliationID"));
			dtoMenu.setFormName(rs.getString("FormName"));
			dtoMenu.setMenuEnable(rs.getInt("MenuEnable"));
			dtoMenu.setShortcutKey(rs.getString("ShortcutKey"));
			dtoMenu.setMenuIcon(rs.getString("MenuIcon"));
			dtoMenu.setMenuType(rs.getInt("MenuType"));
			dtoMenu.setMenuStatus(rs.getInt("MenuStatus"));
			dtoMenu.setMnemonic(rs.getString("Mnemonic"));
			dtoMenu.setPhanHeID(rs.getInt("PhanHeID"));
			dtoMenu.setCreatedDate(rs.getTimestamp("CreatedDate"));
			dtoMenu.setUserID(rs.getString("UserID"));
			hsMenu.add(dtoMenu);
		}
		return hsMenu;
	}
	
	public HashSet<MenuDTO>getMenuDTO(String Condition) throws Exception{
		return getMenuDTO(Condition, null);
	}
	
	public HashSet<MenuDTO>getMenuDTO() throws Exception{
		return getMenuDTO(null);
	}
	
	public MenuDTO getByID(int id) throws Exception{
		HashSet<MenuDTO>HS_Menu=this.getMenuDTO("MenuID="+id);
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
	
	public ArrayList<MenuDTO> getMenuArray(String Condition, String OrderBy) throws Exception{
		ResultSet rs=this.connect.Select("tblMenu", Condition, OrderBy);
		ArrayList<MenuDTO>arrMenu=new ArrayList<MenuDTO>();
		while(rs.next()){
			MenuDTO dtoMenu=new MenuDTO();
			dtoMenu.setMenuID(rs.getInt("MenuID"));
			dtoMenu.setMenuPosition(rs.getInt("MenuPosition"));
			dtoMenu.setMenuValue(rs.getString("MenuValue"));
			dtoMenu.setMenuFiliationID(rs.getInt("MenuFiliationID"));
			dtoMenu.setFormName(rs.getString("FormName"));
			dtoMenu.setMenuEnable(rs.getInt("MenuEnable"));
			dtoMenu.setShortcutKey(rs.getString("ShortcutKey"));
			dtoMenu.setMenuIcon(rs.getString("MenuIcon"));
			dtoMenu.setMenuType(rs.getInt("MenuType"));
			dtoMenu.setMenuStatus(rs.getInt("MenuStatus"));
			dtoMenu.setMnemonic(rs.getString("Mnemonic"));
			dtoMenu.setPhanHeID(rs.getInt("PhanHeID"));
			dtoMenu.setCreatedDate(rs.getTimestamp("CreatedDate"));
			dtoMenu.setUserID(rs.getString("UserID"));
			arrMenu.add(dtoMenu);
		}
		return arrMenu;
	}
	
	public ArrayList<MenuDTO> getMenuArray(String Condition) throws Exception{
		return getMenuArray(Condition, null);
	}
	
	public ArrayList<MenuDTO> getMenuArray() throws Exception{
		return getMenuArray(null);
	}
}
