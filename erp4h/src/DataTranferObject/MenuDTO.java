package DataTranferObject;

import java.sql.Timestamp;

public class MenuDTO {
	private int MenuID;
	private int MenuPosition;
	private String MenuValue;
	private int MenuFiliationID;
	private String FormName;
	private int MenuEnable;
	private String ShortcutKey;
	private String MenuIcon;
	private int MenuType;
	private int MenuStatus;
	private String Mnemonic;
	private int PhanHeID;
	private Timestamp CreatedDate;
	private String UserID;
	
	//Khởi tạo class không tham số
	public MenuDTO(){
		super();
	}
	//Khởi tạo class có tham số đầu vào
	public MenuDTO(	
			int MenuID,
			int MenuPosition,
			String MenuValue,
			int MenuFiliationID,
			String FormName,
			int MenuEnable,
			String ShortcutKey,
			String MenuIcon,
			int MenuType,
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
		this.setShortcutKey(ShortcutKey);
		this.setMenuIcon(MenuIcon);
		this.setMenuType(MenuType);
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
	public int getMenuEnable() {
		return MenuEnable;
	}
	public void setMenuEnable(int menuEnable) {
		MenuEnable = menuEnable;
	}
	public String getShortcutKey() {
		return ShortcutKey;
	}
	public void setShortcutKey(String shortcutKey) {
		ShortcutKey = shortcutKey;
	}
	public String getMenuIcon() {
		return MenuIcon;
	}
	public void setMenuIcon(String menuIcon) {
		MenuIcon = menuIcon;
	}
	public int getMenuType() {
		return MenuType;
	}
	public void setMenuType(int menuType) {
		MenuType = menuType;
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
