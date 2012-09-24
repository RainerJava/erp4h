/************************************************************************************
*
*	会社名		： 林兼コンピューター株式会社
*
*	プロジェクト名	： fas
*
*	ファイル名		： FLogVo.java
*
*	記述			：
*
*	作成日		：  2011/09/01  02:44:41 午後
*
*	作成者		： LENOVO-F23A3B72
*
*	備考			：
*
************************************************************************************/

package com.fas.vo.flog;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.fas.common.utils.StringUtils;
import com.fas.vo.base.BaseVo;

/**
 * <DL>
 *   <DT>クラス記述：</DT>
 * 	 <DD></DD>
 * <BR>
 *   <DT>変更歴史：</DT>
 * 	 <DD>著作者: LENOVO-F23A3B72 </DD><BR>
 * 	 <DD></DD>
 * </DL>
**/
public class FLogVo extends BaseVo {

	/**	 */
	private static final long serialVersionUID = 1L;

	/** USER_USER */
	private String userUser = "";
	/** ACT_DATE */
	private int actDate = 0;
	/** ACT_TIME */
	private int actTime = 0;
	/** MENUGRP_CODE */
	private String menugrpCode = "";
	/** MENUEXE_CODE */
	private String menuexeCode = "";
	/** ACTION_TYPE */
	private String actionType = "";
	/** TEXT */
	private String text = "";
	/** PCID */
	private String pcid = "";
	/** EMP_CODE */
	private String empCode = "";
	/** USER_TYPE */
	private String userType = "";

	/** 
	* <DL>
	*   <DT>コンストラクター記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	*/
	public FLogVo(){
	}

	/* begin: Public Properties */
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the userUser
	*/
	public String getUserUser() {
		return this.userUser;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param userUser
	*/
	public void setUserUser(String userUser) {
		this.userUser = userUser;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the actDate
	*/
	public int getActDate() {
		return this.actDate;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param actDate
	*/
	public void setActDate(int actDate) {
		this.actDate = actDate;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the actTime
	*/
	public int getActTime() {
		return this.actTime;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param actTime
	*/
	public void setActTime(int actTime) {
		this.actTime = actTime;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the menugrpCode
	*/
	public String getMenugrpCode() {
		return this.menugrpCode;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param menugrpCode
	*/
	public void setMenugrpCode(String menugrpCode) {
		this.menugrpCode = menugrpCode;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the menuexeCode
	*/
	public String getMenuexeCode() {
		return this.menuexeCode;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param menuexeCode
	*/
	public void setMenuexeCode(String menuexeCode) {
		this.menuexeCode = menuexeCode;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the actionType
	*/
	public String getActionType() {
		return this.actionType;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param actionType
	*/
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the text
	*/
	public String getText() {
		return this.text;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param text
	*/
	public void setText(String text) {
		this.text = text;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the pcid
	*/
	public String getPcid() {
		return this.pcid;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param pcid
	*/
	public void setPcid(String pcid) {
		this.pcid = pcid;
	}
		
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the empCode
	*/
	public String getEmpCode() {
		return empCode;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param empCode
	*/
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the userType
	*/
	public String getUserType() {
		return userType;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param userType
	*/
	public void setUserType(String userType) {
		this.userType = userType;
	}
	/* end: Public Properties */
	

	/**
	* <DL>
	*   <DT>メソッド記述： get list key to log</DT>
	* 	 <DD></DD>
	* <BR>
	*
	* </DL>	 
	* @return String
	*/
	public String getKeyToLog() {
		return "USER_USER: " + this.userUser + "; " + "ACT_DATE: " + this.actDate + "; " + "ACT_TIME: " + this.actTime + "; " + "MENUGRP_CODE: " + this.menugrpCode + "; " + "MENUEXE_CODE: " + this.menuexeCode + "; " + "ACTION_TYPE: " + this.actionType + "; " + "TEXT: " + this.text + "; ";
	}

	/**
	* <DL>
	*   <DT>メソッド記述</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param rs
	* @return
	*/
	public void getFromSQLServerResultSet(ResultSet rs) throws SQLException {
		this.setUserUser(StringUtils.nulToString(rs.getString("USER_USER")));
		this.setActDate(rs.getInt("ACT_DATE"));
		this.setActTime(rs.getInt("ACT_TIME"));
		this.setMenugrpCode(StringUtils.nulToString(rs.getString("MENUGRP_CODE")));
		this.setMenuexeCode(StringUtils.nulToString(rs.getString("MENUEXE_CODE")));
		this.setActionType(StringUtils.nulToString(rs.getString("ACTION_TYPE")));
		this.setText(StringUtils.nulToString(rs.getString("TEXT")));
		this.setPcid(StringUtils.nulToString(rs.getString("PCID")));
	}
	
	static public boolean equal(FLogVo dataVo1, FLogVo dataVo2) {
		if (dataVo1 == null && dataVo2 == null) return true;
		if (dataVo1 == null || dataVo2 == null) return false;

		if(!dataVo1.getUserUser().trim().equals(dataVo2.getUserUser().trim()))
			return false;
		if(dataVo1.getActDate() != dataVo2.getActDate())
			return false;
		if(dataVo1.getActTime() != dataVo2.getActTime())
			return false;
		if(!dataVo1.getMenugrpCode().trim().equals(dataVo2.getMenugrpCode().trim()))
			return false;
		if(!dataVo1.getMenuexeCode().trim().equals(dataVo2.getMenuexeCode().trim()))
			return false;
		if(!dataVo1.getActionType().trim().equals(dataVo2.getActionType().trim()))
			return false;
		if(!dataVo1.getText().trim().equals(dataVo2.getText().trim()))
			return false;
		if(!dataVo1.getPcid().trim().equals(dataVo2.getPcid().trim()))
			return false;
		return true;
	}
}