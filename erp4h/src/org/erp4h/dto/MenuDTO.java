package org.erp4h.dto;

import java.sql.Timestamp;

public class MenuDTO {
	private int MenuID;
	private int MenuPosition;
	private String MenuValue;
	private int MenuFiliationID;
	private String FormName;
	private String MenuAction;
	private byte MenuActionTypeID;
	private int MenuEnable;
	private String MenuHotKey;
	private String MenuIcon;
	private int MenuTypeID;
	private int MenuStatus;
	private String Mnemonic;
	private int PhanHeID;
	private Timestamp CreatedDate;
	private String Owner;
	
	public MenuDTO(){
		super();
	}

	public MenuDTO(	
			int MenuID,
			int MenuPosition,
			String MenuValue,
			int MenuFiliationID,
			String FormName,
			int MenuEnable,
			String MenuHotKey,
			String MenuIcon,
			int MenuTypeID,
			int MenuStatus,
			String Mnemonic,
			int PhanHeID,
			Timestamp CreatedDate,
			String Owner
			){
		this.setMenuID(MenuID);
		this.setMenuPosition(MenuPosition);
		this.setMenuValue(MenuValue);
		this.setMenuFiliationID(MenuFiliationID);
		this.setFormName(FormName);
		this.setMenuEnable(MenuEnable);
		this.setMenuHotKey(MenuHotKey);
		this.setMenuIcon(MenuIcon);
		this.setMenuTypeID(MenuTypeID);
		this.setMenuStatus(MenuStatus);
		this.setMnemonic(Mnemonic);
		this.setPhanHeID(PhanHeID);
		this.setCreatedDate(CreatedDate);
		this.setOwner(Owner);
	}

	public Timestamp getCreatedDate() {
		return CreatedDate;
	}
	public String getFormName() {
		return FormName;
	}
	public String getMenuAction() {
		return MenuAction;
	}
	public byte getMenuActionTypeID() {
		return MenuActionTypeID;
	}
	public int getMenuEnable() {
		return MenuEnable;
	}
	public int getMenuFiliationID() {
		return MenuFiliationID;
	}
	public String getMenuHotKey() {
		return MenuHotKey;
	}
	public String getMenuIcon() {
		return MenuIcon;
	}
	public int getMenuID() {
		return MenuID;
	}
	public int getMenuPosition() {
		return MenuPosition;
	}
	public int getMenuStatus() {
		return MenuStatus;
	}
	public int getMenuTypeID() {
		return MenuTypeID;
	}
	public String getMenuValue() {
		return MenuValue;
	}
	public String getMnemonic() {
		return Mnemonic;
	}
	public String getOwner() {
		return Owner;
	}
	public int getPhanHeID() {
		return PhanHeID;
	}
	public void setCreatedDate(Timestamp createdDate) {
		CreatedDate = createdDate;
	}
	public void setFormName(String formName) {
		FormName = formName;
	}
	public void setMenuAction(String menuAction) {
		MenuAction = menuAction;
	}
	public void setMenuActionTypeID(byte menuActionTypeID) {
		MenuActionTypeID = menuActionTypeID;
	}
	public void setMenuEnable(int menuEnable) {
		MenuEnable = menuEnable;
	}

	public void setMenuFiliationID(int menuFiliationID) {
		MenuFiliationID = menuFiliationID;
	}
	public void setMenuHotKey(String menuHotKey) {
		MenuHotKey = menuHotKey;
	}
	public void setMenuIcon(String menuIcon) {
		MenuIcon = menuIcon;
	}
	public void setMenuID(int menuID) {
		MenuID = menuID;
	}
	public void setMenuPosition(int menuPosition) {
		MenuPosition = menuPosition;
	}
	public void setMenuStatus(int menuStatus) {
		MenuStatus = menuStatus;
	}
	public void setMenuTypeID(int menuTypeID) {
		MenuTypeID = menuTypeID;
	}
	public void setMenuValue(String menuValue) {
		MenuValue = menuValue;
	}
	public void setMnemonic(String mnemonic) {
		Mnemonic = mnemonic;
	}


	public void setOwner(String owner) {
		Owner = owner;
	}

	public void setPhanHeID(int phanHeID) {
		PhanHeID = phanHeID;
	}

	@Override
	public String toString(){
		return getMenuValue();
	}


}
