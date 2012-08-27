package DataTranferObject;

import java.sql.Timestamp;

public class MenuDTO {
	private int MenuID;
	private int MenuPosition;
	private String MenuValue;
	private int MenuFiliationID;
	private String FormName;
	private int PhanHeID;
	private String ShortcutKey;
	private Timestamp CreatedDate;
	private String UserID;
	
	
	//Khởi tạo class không tham số
	public MenuDTO(){
		super();
	}
	//Khởi tạo class có tham số đầu vào
	public MenuDTO(int MenuID,
			int MenuPosition,
			String MenuValue,
			int MenuFiliationID,
			String FormName,
			int PhanHeID,
			String ShortcutKey,
			Timestamp CreatedDate,
			String UserID){
		this.MenuID=MenuID;
		this.MenuPosition=MenuPosition;
		this.MenuValue=MenuValue;
		this.MenuFiliationID=MenuFiliationID;
		this.FormName=FormName;
		this.PhanHeID=PhanHeID;
		this.ShortcutKey=ShortcutKey;
		this.CreatedDate=CreatedDate;
		this.UserID=UserID;
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
	
	public int getPhanHeID() {
		return PhanHeID;
	}
	public void setPhanHeID(int phanHeID) {
		PhanHeID = phanHeID;
	}
	
	String getShortcutKey() {
		return ShortcutKey;
	}
	void setShortcutKey(String shortcutKey) {
		ShortcutKey = shortcutKey;
	}

	Timestamp getCreatedDate() {
		return CreatedDate;
	}
	void setCreatedDate(Timestamp createdDate) {
		CreatedDate = createdDate;
	}
	
	String getUserID() {
		return UserID;
	}
	void setUserID(String userID) {
		UserID = userID;
	}
	
	@Override
	public String toString(){
		return MenuValue;
	}
}
