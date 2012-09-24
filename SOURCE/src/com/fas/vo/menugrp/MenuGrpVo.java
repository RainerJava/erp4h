/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：MenuGrpVo.java
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
package com.fas.vo.menugrp;

import com.fas.vo.base.BaseVo;

/**
 * @author PC13
 *
 */
public class MenuGrpVo extends BaseVo {

	/** */
	private static final long serialVersionUID = 1L;
	/** */
	private String menugrpCode = "";
	/** */
	private String menugrpName = "";
	/** */
	private String menugrpHelp = "";
	/** */
	private String menugrpSeq = "";
	/** */
	private String userId = "";
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
	public MenuGrpVo() {
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
	 * @return the menugrpName
	 */
	public String getMenugrpName() {
		return menugrpName;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param menugrpName the menugrpName to set
	 */
	public void setMenugrpName(String menugrpName) {
		this.menugrpName = menugrpName;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the menugrpHelp
	 */
	public String getMenugrpHelp() {
		return menugrpHelp;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param menugrpHelp the menugrpHelp to set
	 */
	public void setMenugrpHelp(String menugrpHelp) {
		this.menugrpHelp = menugrpHelp;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the menugrpSeq
	 */
	public String getMenugrpSeq() {
		return menugrpSeq;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param menugrpSeq the menugrpSeq to set
	 */
	public void setMenugrpSeq(String menugrpSeq) {
		this.menugrpSeq = menugrpSeq;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
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
