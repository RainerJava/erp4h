package DataTranferObject;

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
	private String UserID;
	
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
			String UserID
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
		this.setUserID(UserID);
	}

	public int getMenuID() {
		return MenuID;
	}
	public void setMenuID(int menuID) {
		MenuID = menuID;
	}
	public int getMenuPosition() {
		return MenuPosition;
	}
	public void setMenuPosition(int menuPosition) {
		MenuPosition = menuPosition;
	}
	public String getMenuValue() {
		return MenuValue;
	}
	public void setMenuValue(String menuValue) {
		MenuValue = menuValue;
	}
	public int getMenuFiliationID() {
		return MenuFiliationID;
	}
	public void setMenuFiliationID(int menuFiliationID) {
		MenuFiliationID = menuFiliationID;
	}
	public String getFormName() {
		return FormName;
	}
	public void setFormName(String formName) {
		FormName = formName;
	}
	public String getMenuAction() {
		return MenuAction;
	}
	public void setMenuAction(String menuAction) {
		MenuAction = menuAction;
	}
	public byte getMenuActionTypeID() {
		return MenuActionTypeID;
	}
	public void setMenuActionTypeID(byte menuActionTypeID) {
		MenuActionTypeID = menuActionTypeID;
	}
	public int getMenuEnable() {
		return MenuEnable;
	}
	public void setMenuEnable(int menuEnable) {
		MenuEnable = menuEnable;
	}
	public String getMenuHotKey() {
		return MenuHotKey;
	}
	public void setMenuHotKey(String menuHotKey) {
		MenuHotKey = menuHotKey;
	}
	public String getMenuIcon() {
		return MenuIcon;
	}
	public void setMenuIcon(String menuIcon) {
		MenuIcon = menuIcon;
	}
	public int getMenuTypeID() {
		return MenuTypeID;
	}

	public void setMenuTypeID(int menuTypeID) {
		MenuTypeID = menuTypeID;
	}
	public int getMenuStatus() {
		return MenuStatus;
	}
	public void setMenuStatus(int menuStatus) {
		MenuStatus = menuStatus;
	}
	public String getMnemonic() {
		return Mnemonic;
	}
	public void setMnemonic(String mnemonic) {
		Mnemonic = mnemonic;
	}
	public int getPhanHeID() {
		return PhanHeID;
	}
	public void setPhanHeID(int phanHeID) {
		PhanHeID = phanHeID;
	}
	public Timestamp getCreatedDate() {
		return CreatedDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		CreatedDate = createdDate;
	}
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}

	@Override
	public String toString(){
		return getMenuValue();
	}


}
