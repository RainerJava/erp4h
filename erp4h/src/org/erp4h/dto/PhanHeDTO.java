package org.erp4h.dto;

import java.util.HashSet;

import org.erp4h.bll.MenuBLL;
import org.erp4h.system.dto.MenuDTO;


public class PhanHeDTO {
	private int PhanHeID=0;
	private String TenPhanHe;
	private HashSet<MenuDTO> Menu;
	
	public HashSet<MenuDTO> getMenu() throws Exception {
		if(this.Menu==null){
			MenuBLL BLL_Menu=new MenuBLL();
			this.Menu=BLL_Menu.getMenuDTO("MenuID"+this.PhanHeID);
		}
		return Menu;
	}
	public int getPhanHeID() {
		return PhanHeID;
	}
	
	public String getTenPhanHe() {
		return TenPhanHe;
	}
	public void setPhanHeID(int phanHeID) {
		PhanHeID = phanHeID;
	}
	
	public void setTenPhanHe(String tenPhanHe) {
		TenPhanHe = tenPhanHe;
	}
}
