/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：LoginUser.java
*
*     記述				：
*     
*     作成日			：2009/10/05   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/

package com.fas.vo.user;

import com.fas.vo.base.BaseVo;

/**
 * <DL>
 *   <DT>クラス記述：</DT>
 * 	<DD></DD>
 * <BR>
 *   <DT>変更歴史：</DT>
 * 	<DD>著作者: PC13</DD><BR>
 * 	<DD></DD>
 * </DL>
 */

public class LoginUser extends BaseVo {

	/** */
	private static final long serialVersionUID = 1L;

	/** */
	private String empCode = "";
	/** */
	private String empKana = "";
	/** */
	private String empName = "";
	/** */
	private String empTname = "";
	/** */
	private String userUser = "";
	/** */
	private String empUser = "";
	/** */
	private String pwd = "";
	/** */
	private String inoutFlg = "";
	/** */
	private String telno = "";
	/** */
	private String mailadr = "";
	/** */
	private String mailadrm = "";
	/** */
	private String sectCode = "";
	/** */
	private String sect1Code = "";
	/** */
	private String sect2Code = "";
	/** */
	private String sect3Code = "";
	/** */
	private String sect4Code = "";
	/** */
	private String emptyp1Code = "";
	/** */
	private String emptyp2Code = "";
	/** */
	private String emptyp3Code = "";
	/** */
	private String pcid = "";
	/** */
	private String logview_flg = "";
	/** */
	private String excelout = "";
	/** */
	private String path_name = "";
	/** */
	private String oldCode = "";
	/** */
	private String searchidx = "";
	/** */
	private String pwdDate = "";
	/** */
	private String pwd1 = "";
	/** */
	private String pwd2 = "";
	/** */
	private String pwd3 = "";
	/** */
	private String sltdata = "";
	/**ユーザーID */
	private String userId = "";
	/**ユーザー名 */
	private String userName = "";
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
	/** 使用可否フラグ */
	private boolean useFlg = false;
	/** */
	private String logViewFlg = "";
	/** */
	private String exelOut = "";
	/** */
	private boolean isGroup = false;
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public LoginUser() {
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
	 * @param empCode the empCode to set
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
	 * @return the empKana
	 */
	public String getEmpKana() {
		return empKana;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param empKana the empKana to set
	 */
	public void setEmpKana(String empKana) {
		this.empKana = empKana;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the empName
	 */
	public String getEmpName() {
		return empName;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param empName the empName to set
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the empTname
	 */
	public String getEmpTname() {
		return empTname;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param empTname the empTname to set
	 */
	public void setEmpTname(String empTname) {
		this.empTname = empTname;
	}

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
		return userUser;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param userUser the userUser to set
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
	 * @return the empUser
	 */
	public String getEmpUser() {
		return empUser;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param empUser the empUser to set
	 */
	public void setEmpUser(String empUser) {
		this.empUser = empUser;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the pwd
	 */
	public String getPwd() {
		return pwd;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param pwd the pwd to set
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the inoutFlg
	 */
	public String getInoutFlg() {
		return inoutFlg;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param inoutFlg the inoutFlg to set
	 */
	public void setInoutFlg(String inoutFlg) {
		this.inoutFlg = inoutFlg;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the telno
	 */
	public String getTelno() {
		return telno;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param telno the telno to set
	 */
	public void setTelno(String telno) {
		this.telno = telno;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the mailadr
	 */
	public String getMailadr() {
		return mailadr;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param mailadr the mailadr to set
	 */
	public void setMailadr(String mailadr) {
		this.mailadr = mailadr;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the mailadrm
	 */
	public String getMailadrm() {
		return mailadrm;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param mailadrm the mailadrm to set
	 */
	public void setMailadrm(String mailadrm) {
		this.mailadrm = mailadrm;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the sectCode
	 */
	public String getSectCode() {
		return sectCode;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param sectCode the sectCode to set
	 */
	public void setSectCode(String sectCode) {
		this.sectCode = sectCode;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the sect1Code
	 */
	public String getSect1Code() {
		return sect1Code;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param sect1Code the sect1Code to set
	 */
	public void setSect1Code(String sect1Code) {
		this.sect1Code = sect1Code;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the sect2Code
	 */
	public String getSect2Code() {
		return sect2Code;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param sect2Code the sect2Code to set
	 */
	public void setSect2Code(String sect2Code) {
		this.sect2Code = sect2Code;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the sect3Code
	 */
	public String getSect3Code() {
		return sect3Code;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param sect3Code the sect3Code to set
	 */
	public void setSect3Code(String sect3Code) {
		this.sect3Code = sect3Code;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the sect4Code
	 */
	public String getSect4Code() {
		return sect4Code;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param sect4Code the sect4Code to set
	 */
	public void setSect4Code(String sect4Code) {
		this.sect4Code = sect4Code;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the emptyp1Code
	 */
	public String getEmptyp1Code() {
		return emptyp1Code;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param emptyp1Code the emptyp1Code to set
	 */
	public void setEmptyp1Code(String emptyp1Code) {
		this.emptyp1Code = emptyp1Code;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the emptyp2Code
	 */
	public String getEmptyp2Code() {
		return emptyp2Code;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param emptyp2Code the emptyp2Code to set
	 */
	public void setEmptyp2Code(String emptyp2Code) {
		this.emptyp2Code = emptyp2Code;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the emptyp3Code
	 */
	public String getEmptyp3Code() {
		return emptyp3Code;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param emptyp3Code the emptyp3Code to set
	 */
	public void setEmptyp3Code(String emptyp3Code) {
		this.emptyp3Code = emptyp3Code;
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
		return pcid;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param pcid the pcid to set
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
	 * @return the logview_flg
	 */
	public String getLogview_flg() {
		return logview_flg;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param logview_flg the logview_flg to set
	 */
	public void setLogview_flg(String logview_flg) {
		this.logview_flg = logview_flg;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the excelout
	 */
	public String getExcelout() {
		return excelout;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param excelout the excelout to set
	 */
	public void setExcelout(String excelout) {
		this.excelout = excelout;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the path_name
	 */
	public String getPath_name() {
		return path_name;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param path_name the path_name to set
	 */
	public void setPath_name(String path_name) {
		this.path_name = path_name;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the oldCode
	 */
	public String getOldCode() {
		return oldCode;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param oldCode the oldCode to set
	 */
	public void setOldCode(String oldCode) {
		this.oldCode = oldCode;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the searchidx
	 */
	public String getSearchidx() {
		return searchidx;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param searchidx the searchidx to set
	 */
	public void setSearchidx(String searchidx) {
		this.searchidx = searchidx;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the pwdDate
	 */
	public String getPwdDate() {
		return pwdDate;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param pwdDate the pwdDate to set
	 */
	public void setPwdDate(String pwdDate) {
		this.pwdDate = pwdDate;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the pwd1
	 */
	public String getPwd1() {
		return pwd1;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param pwd1 the pwd1 to set
	 */
	public void setPwd1(String pwd1) {
		this.pwd1 = pwd1;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the pwd2
	 */
	public String getPwd2() {
		return pwd2;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param pwd2 the pwd2 to set
	 */
	public void setPwd2(String pwd2) {
		this.pwd2 = pwd2;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the pwd3
	 */
	public String getPwd3() {
		return pwd3;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param pwd3 the pwd3 to set
	 */
	public void setPwd3(String pwd3) {
		this.pwd3 = pwd3;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the sltdata
	 */
	public String getSltdata() {
		return sltdata;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param sltdata the sltdata to set
	 */
	public void setSltdata(String sltdata) {
		this.sltdata = sltdata;
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
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the lastLoginDay
	 */
	public int getLastLoginDay() {
		return lastLoginDay;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param lastLoginDay the lastLoginDay to set
	 */
	public void setLastLoginDay(int lastLoginDay) {
		this.lastLoginDay = lastLoginDay;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the lastLoginTime
	 */
	public int getLastLoginTime() {
		return lastLoginTime;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param lastLoginTime the lastLoginTime to set
	 */
	public void setLastLoginTime(int lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the userFlg
	 */
	public String getUserFlg() {
		return userFlg;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param userFlg the userFlg to set
	 */
	public void setUserFlg(String userFlg) {
		this.userFlg = userFlg;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the addUser
	 */
	public String getAddUser() {
		return addUser;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param addUser the addUser to set
	 */
	public void setAddUser(String addUser) {
		this.addUser = addUser;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the addPc
	 */
	public String getAddPc() {
		return addPc;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param addPc the addPc to set
	 */
	public void setAddPc(String addPc) {
		this.addPc = addPc;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the addDate
	 */
	public int getAddDate() {
		return addDate;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param addDate the addDate to set
	 */
	public void setAddDate(int addDate) {
		this.addDate = addDate;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the addTime
	 */
	public int getAddTime() {
		return addTime;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param addTime the addTime to set
	 */
	public void setAddTime(int addTime) {
		this.addTime = addTime;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the lastupUser
	 */
	public String getLastupUser() {
		return lastupUser;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param lastupUser the lastupUser to set
	 */
	public void setLastupUser(String lastupUser) {
		this.lastupUser = lastupUser;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the lastupPc
	 */
	public String getLastupPc() {
		return lastupPc;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param lastupPc the lastupPc to set
	 */
	public void setLastupPc(String lastupPc) {
		this.lastupPc = lastupPc;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the lastupDate
	 */
	public int getLastupDate() {
		return lastupDate;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param lastupDate the lastupDate to set
	 */
	public void setLastupDate(int lastupDate) {
		this.lastupDate = lastupDate;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the lastupTime
	 */
	public int getLastupTime() {
		return lastupTime;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param lastupTime the lastupTime to set
	 */
	public void setLastupTime(int lastupTime) {
		this.lastupTime = lastupTime;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the loginPC
	 */
	public String getLoginPC() {
		return loginPC;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param loginPC the loginPC to set
	 */
	public void setLoginPC(String loginPC) {
		this.loginPC = loginPC;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the useFlg
	 */
	public boolean isUseFlg() {
		return useFlg;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param useFlg the useFlg to set
	 */
	public void setUseFlg(boolean useFlg) {
		this.useFlg = useFlg;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the logViewFlg
	 */
	public String getLogViewFlg() {
		return logViewFlg;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param logViewFlg the logViewFlg to set
	 */
	public void setLogViewFlg(String logViewFlg) {
		this.logViewFlg = logViewFlg;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the exelOut
	 */
	public String getExelOut() {
		return exelOut;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param exelOut the exelOut to set
	 */
	public void setExelOut(String exelOut) {
		this.exelOut = exelOut;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the isGroup
	 */
	public boolean isGroup() {
		return isGroup;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param isGroup the isGroup to set
	 */
	public void setGroup(boolean isGroup) {
		this.isGroup = isGroup;
	}	


}

