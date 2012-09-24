/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：MenuExeVo.java
*
*     記述				：
*     
*     作成日			：2010/01/25   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/
package com.fas.vo.menuexe;

import com.fas.common.utils.StringUtils;
import com.fas.vo.base.BaseVo;

/**
 * @author PC13
 *
 */
public class MenuExeVo extends BaseVo {

	/** */
	private static final long serialVersionUID = 1L;
	
	/** */
	private String userId = "";
	/** */
	private String menugrpCode = "";
	/** */
	private String menuexeCode = "";
	/** */
	private String menuexeName = "";
	/** */
	private String menuexeHelp = "";
	/** */
	private String funType = "";
	/** */
	private String className = "";
	/** */
	private String pathName = "";
	/** */
	private String menuexeSeq = "";
	/** */
	private String controlType;
	/**登録ユーザー名 */
	private String addUser = "";
	/**登録PC名 */
	private String addPc = "";
	/**登録日 */
	private int addDate = 0;
	/**登録時間 */
	private int addTime = 0;
	/**更新ユーザー名 */
	private String lastupUser = "";
	/**更新PC名 */
	private String lastupPc = "";
	/**更新日 */
	private int lastupDate = 0;
	/**更新時間 */
	private int lastupTime = 0;
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public MenuExeVo() {
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
		return menugrpCode;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param menugrpCode the menugrpCode to set
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
		return menuexeCode;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param menuexeCode the menuexeCode to set
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
	 * @return the menuexeName
	 */
	public String getMenuexeName() {
		return menuexeName;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param menuexeName the menuexeName to set
	 */
	public void setMenuexeName(String menuexeName) {
		this.menuexeName = menuexeName;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the menuexeHelp
	 */
	public String getMenuexeHelp() {
		return menuexeHelp;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param menuexeHelp the menuexeHelp to set
	 */
	public void setMenuexeHelp(String menuexeHelp) {
		this.menuexeHelp = menuexeHelp;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the funType
	 */
	public String getFunType() {
		return funType;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param funType the funType to set
	 */
	public void setFunType(String funType) {
		this.funType = funType;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param className the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the pathName
	 */
	public String getPathName() {
		return pathName;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param pathName the pathName to set
	 */
	public void setPathName(String pathName) {
		this.pathName = pathName;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the menuexeSeq
	 */
	public String getMenuexeSeq() {
		return menuexeSeq;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param menuexeSeq the menuexeSeq to set
	 */
	public void setMenuexeSeq(String menuexeSeq) {
		this.menuexeSeq = menuexeSeq;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param mnExeVo
	 * @return
	 */
	public int sequenceCompare(MenuExeVo mnExeVo) {
		
		if (mnExeVo == null) return 1;
		
		if (StringUtils.emptyIfNull(menugrpCode).compareTo(mnExeVo.getMenugrpCode()) == 0) {
			return 0 - StringUtils.emptyIfNull(menuexeSeq).compareTo(mnExeVo.getMenuexeSeq());
		} else {
			return 0 - StringUtils.emptyIfNull(menugrpCode).compareTo(mnExeVo.getMenuexeSeq());
		}
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param mnExeVo
	 * @return
	 */
	public boolean isThesameMenuExe(MenuExeVo mnExeVo) {
		
		if (mnExeVo == null) return true;
		
		if (StringUtils.emptyIfNull(menuexeCode).equalsIgnoreCase(mnExeVo.getMenuexeCode()) &&
				StringUtils.emptyIfNull(menugrpCode).equalsIgnoreCase(mnExeVo.getMenugrpCode())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @return the controlType
	 */
	public String getControlType() {
		return controlType;
	}

	/**
	 * @param controlType the controlType to set
	 */
	public void setControlType(String controlType) {
		this.controlType = controlType;
	}

	/**
	 * @return the addUser
	 */
	public String getAddUser() {
		return addUser;
	}

	/**
	 * @param addUser the addUser to set
	 */
	public void setAddUser(String addUser) {
		this.addUser = addUser;
	}

	/**
	 * @return the addPc
	 */
	public String getAddPc() {
		return addPc;
	}

	/**
	 * @param addPc the addPc to set
	 */
	public void setAddPc(String addPc) {
		this.addPc = addPc;
	}

	/**
	 * @return the addDate
	 */
	public int getAddDate() {
		return addDate;
	}

	/**
	 * @param addDate the addDate to set
	 */
	public void setAddDate(int addDate) {
		this.addDate = addDate;
	}

	/**
	 * @return the addTime
	 */
	public int getAddTime() {
		return addTime;
	}

	/**
	 * @param addTime the addTime to set
	 */
	public void setAddTime(int addTime) {
		this.addTime = addTime;
	}

	/**
	 * @return the lastupUser
	 */
	public String getLastupUser() {
		return lastupUser;
	}

	/**
	 * @param lastupUser the lastupUser to set
	 */
	public void setLastupUser(String lastupUser) {
		this.lastupUser = lastupUser;
	}

	/**
	 * @return the lastupPc
	 */
	public String getLastupPc() {
		return lastupPc;
	}

	/**
	 * @param lastupPc the lastupPc to set
	 */
	public void setLastupPc(String lastupPc) {
		this.lastupPc = lastupPc;
	}

	/**
	 * @return the lastupDate
	 */
	public int getLastupDate() {
		return lastupDate;
	}

	/**
	 * @param lastupDate the lastupDate to set
	 */
	public void setLastupDate(int lastupDate) {
		this.lastupDate = lastupDate;
	}

	/**
	 * @return the lastupTime
	 */
	public int getLastupTime() {
		return lastupTime;
	}

	/**
	 * @param lastupTime the lastupTime to set
	 */
	public void setLastupTime(int lastupTime) {
		this.lastupTime = lastupTime;
	}
}
