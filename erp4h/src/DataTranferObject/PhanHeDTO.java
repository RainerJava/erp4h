package DataTranferObject;

import java.util.HashSet;

import BusinessLogicLayer.MenuBLL;

public class PhanHeDTO {
	private int PhanHeID=0;
	private String TenPhanHe;
	private HashSet<MenuDTO> Menu;
	
	public int getPhanHeID() {
		return PhanHeID;
	}
	public void setPhanHeID(int phanHeID) {
		PhanHeID = phanHeID;
	}
	
	public String getTenPhanHe() {
		return TenPhanHe;
	}
	public void setTenPhanHe(String tenPhanHe) {
		TenPhanHe = tenPhanHe;
	}
	
	public HashSet<MenuDTO> getMenu() throws Exception {
		if(this.Menu==null){
			MenuBLL BLL_Menu=new MenuBLL();
			this.Menu=BLL_Menu.getMenuDTO("MenuID"+this.PhanHeID);
		}
		return Menu;
	}
}
