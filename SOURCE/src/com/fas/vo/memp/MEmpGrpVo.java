/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：MEmpGrpVo.java
*
*     記述				：
*     
*     作成日			：2009/12/03
*
*     作成者			：PC12
*
*     備考				：
*
**************************************************************************************/

package com.fas.vo.memp;

/**
 * @author PC12
 *
 */
public class MEmpGrpVo {
	/** USERID */
	private String userid = "";

	/** 担当者Ｇコード */
	private String empgrpCode = "";

	/** 担当者コード */
	private String empCode = "";

	/** 登録ユーザー名 */
	private String addUser = "";

	/** 登録ＰＣ名 */
	private String addPc = "";

	/** 登録日付 */
	private int addDate = 0;

	
	public MEmpGrpVo(){
		
	}
	
	/**
	 * @return the userid
	 */
	public String getUserid() {
		return userid;
	}

	/**
	 * @param userid the userid to set
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}

	/**
	 * @return the empgrpCode
	 */
	public String getEmpgrpCode() {
		return empgrpCode;
	}

	/**
	 * @param empgrpCode the empgrpCode to set
	 */
	public void setEmpgrpCode(String empgrpCode) {
		this.empgrpCode = empgrpCode;
	}

	/**
	 * @return the empCode
	 */
	public String getEmpCode() {
		return empCode;
	}

	/**
	 * @param empCode the empCode to set
	 */
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
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

	/** 登録時刻 */
	private int addTime = 0;

	/** 最終更新ユーザー名 */
	private String lastupUser = "";

	/** 最終更新ＰＣ名 */
	private String lastupPc = "";

	/** 最終更新日付 */
	private int lastupDate = 0;

	/** 最終更新時刻 */
	private int lastupTime = 0;

}
