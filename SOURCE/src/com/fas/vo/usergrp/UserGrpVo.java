package com.fas.vo.usergrp;

import com.fas.vo.base.BaseVo;

public class UserGrpVo extends BaseVo {
	
	/** */
	private static final long serialVersionUID = 1L;

	/** */
	private String userGrpId = "";
	/** */
	private String pwd = "";
	/** */
	private String userText = "";
	/** */
	private String logViewflg = "";
	/** */
	private String excelOut = "";
	/** */
	private int pwdDate = 0;
	/** */
	private String pwd1 = "";
	/** */
	private String pwd2 = "";
	/** */
	private String pwd3 = "";
	/**前回ログイン日 */
	private int lastLoginDay = 0;
	/**前回ログイン時間 */
	private int lastLoginTime = 0;
	/**使用不可フラグ */
	private String userFlg = "";
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
	/**Login PC name */
	private String loginPC = "";
	
	public UserGrpVo() {
	}

	/**
	 * @return the userGrpId
	 */
	public String getUserGrpId() {
		return userGrpId;
	}

	/**
	 * @param userGrpId the userGrpId to set
	 */
	public void setUserGrpId(String userGrpId) {
		this.userGrpId = userGrpId;
	}

	/**
	 * @return the pwd
	 */
	public String getPwd() {
		return pwd;
	}

	/**
	 * @param pwd the pwd to set
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	/**
	 * @return the userText
	 */
	public String getUserText() {
		return userText;
	}

	/**
	 * @param userText the userText to set
	 */
	public void setUserText(String userText) {
		this.userText = userText;
	}

	/**
	 * @return the logViewflg
	 */
	public String getLogViewflg() {
		return logViewflg;
	}

	/**
	 * @param logViewflg the logViewflg to set
	 */
	public void setLogViewflg(String logViewflg) {
		this.logViewflg = logViewflg;
	}

	/**
	 * @return the excelOut
	 */
	public String getExcelOut() {
		return excelOut;
	}

	/**
	 * @param excelOut the excelOut to set
	 */
	public void setExcelOut(String excelOut) {
		this.excelOut = excelOut;
	}

	/**
	 * @return the pwdDate
	 */
	public int getPwdDate() {
		return pwdDate;
	}

	/**
	 * @param pwdDate the pwdDate to set
	 */
	public void setPwdDate(int pwdDate) {
		this.pwdDate = pwdDate;
	}

	/**
	 * @return the lastLoginDay
	 */
	public int getLastLoginDay() {
		return lastLoginDay;
	}

	/**
	 * @param lastLoginDay the lastLoginDay to set
	 */
	public void setLastLoginDay(int lastLoginDay) {
		this.lastLoginDay = lastLoginDay;
	}

	/**
	 * @return the lastLoginTime
	 */
	public int getLastLoginTime() {
		return lastLoginTime;
	}

	/**
	 * @param lastLoginTime the lastLoginTime to set
	 */
	public void setLastLoginTime(int lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	/**
	 * @return the userFlg
	 */
	public String getUserFlg() {
		return userFlg;
	}

	/**
	 * @param userFlg the userFlg to set
	 */
	public void setUserFlg(String userFlg) {
		this.userFlg = userFlg;
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

	/**
	 * @return the loginPC
	 */
	public String getLoginPC() {
		return loginPC;
	}

	/**
	 * @param loginPC the loginPC to set
	 */
	public void setLoginPC(String loginPC) {
		this.loginPC = loginPC;
	}

	/**
	 * @return the pwd1
	 */
	public String getPwd1() {
		return pwd1;
	}

	/**
	 * @param pwd1 the pwd1 to set
	 */
	public void setPwd1(String pwd1) {
		this.pwd1 = pwd1;
	}

	/**
	 * @return the pwd2
	 */
	public String getPwd2() {
		return pwd2;
	}

	/**
	 * @param pwd2 the pwd2 to set
	 */
	public void setPwd2(String pwd2) {
		this.pwd2 = pwd2;
	}

	/**
	 * @return the pwd3
	 */
	public String getPwd3() {
		return pwd3;
	}

	/**
	 * @param pwd3 the pwd3 to set
	 */
	public void setPwd3(String pwd3) {
		this.pwd3 = pwd3;
	}
}
