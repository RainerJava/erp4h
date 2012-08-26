package DataTranferObject;

import java.sql.Timestamp;

import BusinessLogicLayer.PhanHeBLL;



public class MenuDTO {
	private int MenuID;
	private int MenuPosition;
	private String MenuValue;
	private int MenuFiliationID;
	private String FormName;
	private int PhanHeID;
	private PhanHeDTO DTO_PhanHe;
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
			PhanHeDTO DTO_PhanHe,
			String ShortcutKey,
			Timestamp CreatedDate,
			String UserID){
		this.MenuID=MenuID;
		this.MenuPosition=MenuPosition;
		this.MenuValue=MenuValue;
		this.MenuFiliationID=MenuFiliationID;
		this.FormName=FormName;
		this.PhanHeID=PhanHeID;
		this.DTO_PhanHe=DTO_PhanHe;
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
	
	public PhanHeDTO getDTO_PhanHe() throws Exception {
		if(this.DTO_PhanHe==null)
		{
			PhanHeBLL BLL_PhanHe=new PhanHeBLL();
			this.DTO_PhanHe=BLL_PhanHe.getByID(PhanHeID);
		}
		return DTO_PhanHe;
	}
	public void setDTO_PhanHe(PhanHeDTO dTO_PhanHe) {
		DTO_PhanHe = dTO_PhanHe;
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
