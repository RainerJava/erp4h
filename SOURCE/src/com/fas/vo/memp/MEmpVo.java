/************************************************************************************
*
*	会社名		： 林兼コンピューター株式会社
*
*	プロジェクト名	： fas
*
*	ファイル名		： MEmpVo.java
*
*	記述			：
*
*	作成日		：  2011/08/24  02:34:11 午後
*
*	作成者		： LENOVO-F23A3B72
*
*	備考			：
*
************************************************************************************/

package com.fas.vo.memp;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.fas.common.utils.DateUtils;
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
public class MEmpVo extends BaseVo {

	/**	 */
	private static final long serialVersionUID = 1L;

	/** 担当者コード */
	private String empCode = "";
	/** 担当者名(カナ) */
	private String empKana = "";
	/** 担当者名 */
	private String empName = "";
	/** 担当者名(短縮) */
	private String empTname = "";
	/** ユーザー */
	private String userUser = "";
	/** ユーザー名 */
	private String empUser = "";
	/** パスワード */
	private String pwd = "";
	/** 表示順 */
	private int dsporderNo = 0;
	/** 内外フラグ */
	private String inoutFlg = "";
	/** 電話番号 */
	private String telno = "";
	/** メールアドレス */
	private String mailadr = "";
	/** 携帯メールアドレス */
	private String mailadrm = "";
	/** 部門コード */
	private String sectCode = "";
	/** 部門コード1 */
	private String sect1Code = "";
	/** 部門コード2 */
	private String sect2Code = "";
	/** 部門コード3 */
	private String sect3Code = "";
	/** 部門コード4 */
	private String sect4Code = "";
	/** 担当者区分1 */
	private String emptyp1Code = "";
	/** 担当者区分2 */
	private String emptyp2Code = "";
	/** 担当者区分3 */
	private String emptyp3Code = "";
	/** PCNAME */
	private String pcid = "";
	/** ログ表示フラグ */
	private String logviewFlg = "";
	/** EXCEL出力区分 */
	private String excelout = "";
	/** PATH_NAME */
	private String pathName = "";
	/** 旧システムコード */
	private String oldCode = "";
	/** 検索INDEX */
	private String searchidx = "";
	/** 使用中フラグ */
	private String usingFlg = "";
	/** 使用可否フラグ */
	private String useFlg = "";
	/** パスワード設定日 */
	private int pwdDate = 0;
	/** PASSWORD1 */
	private String pwd1 = "";
	/** PASSWORD2 */
	private String pwd2 = "";
	/** PASSWORD3 */
	private String pwd3 = "";
	/** 登録ユーザー名 */
	private String addUser = "";
	/** 登録ＰＣ名 */
	private String addPc = "";
	/** 登録日付 */
	private int addDate = 0;
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
	/** */
	private String addUserView = "";
	/** */
	private String addDateView = "";
	/** */
	private String addTimeView = "";
	/** */
	private String lastupUserView = "";
	/** */
	private String lastupDateView = "";
	/** */
	private String lastupTimeView = "";

	/** 
	* <DL>
	*   <DT>コンストラクター記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	*/
	public MEmpVo(){
	}

	/* begin: Public Properties */
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
		return this.empCode;
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
	* @return the empKana
	*/
	public String getEmpKana() {
		return this.empKana;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param empKana
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
		return this.empName;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param empName
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
		return this.empTname;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param empTname
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
	* @return the empUser
	*/
	public String getEmpUser() {
		return this.empUser;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param empUser
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
		return this.pwd;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param pwd
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
	* @return the dsporderNo
	*/
	public int getDsporderNo() {
		return this.dsporderNo;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param dsporderNo
	*/
	public void setDsporderNo(int dsporderNo) {
		this.dsporderNo = dsporderNo;
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
		return this.inoutFlg;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param inoutFlg
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
		return this.telno;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param telno
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
		return this.mailadr;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param mailadr
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
		return this.mailadrm;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param mailadrm
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
		return this.sectCode;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param sectCode
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
		return this.sect1Code;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param sect1Code
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
		return this.sect2Code;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param sect2Code
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
		return this.sect3Code;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param sect3Code
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
		return this.sect4Code;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param sect4Code
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
		return this.emptyp1Code;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param emptyp1Code
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
		return this.emptyp2Code;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param emptyp2Code
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
		return this.emptyp3Code;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param emptyp3Code
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
	* @return the logviewFlg
	*/
	public String getLogviewFlg() {
		return this.logviewFlg;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param logviewFlg
	*/
	public void setLogviewFlg(String logviewFlg) {
		this.logviewFlg = logviewFlg;
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
		return this.excelout;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param excelout
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
	* @return the pathName
	*/
	public String getPathName() {
		return this.pathName;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param pathName
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
	* @return the oldCode
	*/
	public String getOldCode() {
		return this.oldCode;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param oldCode
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
		return this.searchidx;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param searchidx
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
	* @return the usingFlg
	*/
	public String getUsingFlg() {
		return this.usingFlg;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param usingFlg
	*/
	public void setUsingFlg(String usingFlg) {
		this.usingFlg = usingFlg;
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
	public String getUseFlg() {
		return this.useFlg;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param useFlg
	*/
	public void setUseFlg(String useFlg) {
		this.useFlg = useFlg;
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
	public int getPwdDate() {
		return this.pwdDate;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param pwdDate
	*/
	public void setPwdDate(int pwdDate) {
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
		return this.pwd1;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param pwd1
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
		return this.pwd2;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param pwd2
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
		return this.pwd3;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param pwd3
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
	* @return the addUser
	*/
	public String getAddUser() {
		return this.addUser;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param addUser
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
		return this.addPc;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param addPc
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
		return this.addDate;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param addDate
	*/
	public void setAddDate(int addDate) {
		if(addDate == 0){
			setAddDateView("");
		}else{
			setAddDateView(DateUtils.getDateWithSplit(addDate));
		}
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
		return this.addTime;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param addTime
	*/
	public void setAddTime(int addTime) {
		if(addTime == 0){
			setAddTimeView("");
		}else{
			setAddTimeView(DateUtils.getTimeWithSplit(addTime));
		}
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
		return this.lastupUser;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param lastupUser
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
		return this.lastupPc;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param lastupPc
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
		return this.lastupDate;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param lastupDate
	*/
	public void setLastupDate(int lastupDate) {
		if(lastupDate == 0){
			setLastupDateView("");
		}else{
			setLastupDateView(DateUtils.getDateWithSplit(lastupDate));
		}
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
		return this.lastupTime;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param lastupTime
	*/
	public void setLastupTime(int lastupTime) {
		if(lastupTime == 0){
			setLastupTimeView("");
		}else{
			setLastupTimeView(DateUtils.getTimeWithSplit(lastupTime));
		}
		this.lastupTime = lastupTime;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the addUserView
	 */
	public String getAddUserView() {
		return addUserView;
	}
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param addUserView the addUserView to set
	 */
	public void setAddUserView(String addUserView) {
		this.addUserView = addUserView;
	}
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the addDateView
	 */
	public String getAddDateView() {
		return addDateView;
	}
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param addDateView the addDateView to set
	 */
	public void setAddDateView(String addDateView) {
		this.addDateView = addDateView;
	}
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the addTimeView
	 */
	public String getAddTimeView() {
		return addTimeView;
	}
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param addTimeView the addTimeView to set
	 */
	public void setAddTimeView(String addTimeView) {
		this.addTimeView = addTimeView;
	}
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the lastupUserView
	 */
	public String getLastupUserView() {
		return lastupUserView;
	}
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param lastupUserView the lastupUserView to set
	 */
	public void setLastupUserView(String lastupUserView) {
		this.lastupUserView = lastupUserView;
	}
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the lastupDateView
	 */
	public String getLastupDateView() {
		return lastupDateView;
	}
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param lastupDateView the lastupDateView to set
	 */
	public void setLastupDateView(String lastupDateView) {
		this.lastupDateView = lastupDateView;
	}
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the lastupTimeView
	 */
	public String getLastupTimeView() {
		return lastupTimeView;
	}
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param lastupTimeView the lastupTimeView to set
	 */
	public void setLastupTimeView(String lastupTimeView) {
		this.lastupTimeView = lastupTimeView;
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
		return "EMP_CODE: " + this.empCode + "; ";
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
		this.setEmpCode(StringUtils.nulToString(rs.getString("EMP_CODE")));
		this.setEmpKana(StringUtils.nulToString(rs.getString("EMP_KANA")));
		this.setEmpName(StringUtils.nulToString(rs.getString("EMP_NAME")));
		this.setEmpTname(StringUtils.nulToString(rs.getString("EMP_TNAME")));
		this.setUserUser(StringUtils.nulToString(rs.getString("USER_USER")));
		this.setEmpUser(StringUtils.nulToString(rs.getString("EMP_USER")));
		this.setPwd(StringUtils.nulToString(rs.getString("PWD")));
		this.setDsporderNo(rs.getInt("DSPORDER_NO"));
		this.setInoutFlg(StringUtils.nulToString(rs.getString("INOUT_FLG")));
		this.setTelno(StringUtils.nulToString(rs.getString("TELNO")));
		this.setMailadr(StringUtils.nulToString(rs.getString("MAILADR")));
		this.setMailadrm(StringUtils.nulToString(rs.getString("MAILADRM")));
		this.setSectCode(StringUtils.nulToString(rs.getString("SECT_CODE")));
		this.setSect1Code(StringUtils.nulToString(rs.getString("SECT1_CODE")));
		this.setSect2Code(StringUtils.nulToString(rs.getString("SECT2_CODE")));
		this.setSect3Code(StringUtils.nulToString(rs.getString("SECT3_CODE")));
		this.setSect4Code(StringUtils.nulToString(rs.getString("SECT4_CODE")));
		this.setEmptyp1Code(StringUtils.nulToString(rs.getString("EMPTYP1_CODE")));
		this.setEmptyp2Code(StringUtils.nulToString(rs.getString("EMPTYP2_CODE")));
		this.setEmptyp3Code(StringUtils.nulToString(rs.getString("EMPTYP3_CODE")));
		this.setPcid(StringUtils.nulToString(rs.getString("PCID")));
		this.setLogviewFlg(StringUtils.nulToString(rs.getString("LOGVIEW_FLG")));
		this.setExcelout(StringUtils.nulToString(rs.getString("EXCELOUT")));
		this.setPathName(StringUtils.nulToString(rs.getString("PATH_NAME")));
		this.setOldCode(StringUtils.nulToString(rs.getString("OLD_CODE")));
		this.setSearchidx(StringUtils.nulToString(rs.getString("SEARCHIDX")));
		this.setUsingFlg(StringUtils.nulToString(rs.getString("USING_FLG")));
		this.setUseFlg(StringUtils.nulToString(rs.getString("USE_FLG")));
		this.setPwdDate(rs.getInt("PWD_DATE"));
		this.setPwd1(StringUtils.nulToString(rs.getString("PWD1")));
		this.setPwd2(StringUtils.nulToString(rs.getString("PWD2")));
		this.setPwd3(StringUtils.nulToString(rs.getString("PWD3")));
		this.setAddUser(StringUtils.nulToString(rs.getString("ADD_USER")));
		this.setAddPc(StringUtils.nulToString(rs.getString("ADD_PC")));
		this.setAddDate(rs.getInt("ADD_DATE"));
		this.setAddTime(rs.getInt("ADD_TIME"));
		this.setLastupUser(StringUtils.nulToString(rs.getString("LASTUP_USER")));
		this.setLastupPc(StringUtils.nulToString(rs.getString("LASTUP_PC")));
		this.setLastupDate(rs.getInt("LASTUP_DATE"));
		this.setLastupTime(rs.getInt("LASTUP_TIME"));
		this.setAddUserView(StringUtils.nulToString(rs.getString("ADD_USER_VIEW")));
		this.setLastupUserView(StringUtils.nulToString(rs.getString("LASTUP_USER_VIEW")));
	}
	
	static public boolean equal(MEmpVo dataVo1, MEmpVo dataVo2) {
		if (dataVo1 == null && dataVo2 == null) return true;
		if (dataVo1 == null || dataVo2 == null) return false;

		if(!dataVo1.getEmpCode().trim().equals(dataVo2.getEmpCode().trim()))
			return false;
		if(!dataVo1.getEmpKana().trim().equals(dataVo2.getEmpKana().trim()))
			return false;
		if(!dataVo1.getEmpName().trim().equals(dataVo2.getEmpName().trim()))
			return false;
		if(!dataVo1.getEmpTname().trim().equals(dataVo2.getEmpTname().trim()))
			return false;
		if(!dataVo1.getUserUser().trim().equals(dataVo2.getUserUser().trim()))
			return false;
		if(!dataVo1.getEmpUser().trim().equals(dataVo2.getEmpUser().trim()))
			return false;
		if(!dataVo1.getPwd().trim().equals(dataVo2.getPwd().trim()))
			return false;
		if(dataVo1.getDsporderNo() != dataVo2.getDsporderNo())
			return false;
		if(!dataVo1.getInoutFlg().trim().equals(dataVo2.getInoutFlg().trim()))
			return false;
		if(!dataVo1.getTelno().trim().equals(dataVo2.getTelno().trim()))
			return false;
		if(!dataVo1.getMailadr().trim().equals(dataVo2.getMailadr().trim()))
			return false;
		if(!dataVo1.getMailadrm().trim().equals(dataVo2.getMailadrm().trim()))
			return false;
		if(!dataVo1.getSectCode().trim().equals(dataVo2.getSectCode().trim()))
			return false;
		if(!dataVo1.getSect1Code().trim().equals(dataVo2.getSect1Code().trim()))
			return false;
		if(!dataVo1.getSect2Code().trim().equals(dataVo2.getSect2Code().trim()))
			return false;
		if(!dataVo1.getSect3Code().trim().equals(dataVo2.getSect3Code().trim()))
			return false;
		if(!dataVo1.getSect4Code().trim().equals(dataVo2.getSect4Code().trim()))
			return false;
		if(!dataVo1.getEmptyp1Code().trim().equals(dataVo2.getEmptyp1Code().trim()))
			return false;
		if(!dataVo1.getEmptyp2Code().trim().equals(dataVo2.getEmptyp2Code().trim()))
			return false;
		if(!dataVo1.getEmptyp3Code().trim().equals(dataVo2.getEmptyp3Code().trim()))
			return false;
		if(!dataVo1.getPcid().trim().equals(dataVo2.getPcid().trim()))
			return false;
		if(!dataVo1.getLogviewFlg().trim().equals(dataVo2.getLogviewFlg().trim()))
			return false;
		if(!dataVo1.getExcelout().trim().equals(dataVo2.getExcelout().trim()))
			return false;
		if(!dataVo1.getPathName().trim().equals(dataVo2.getPathName().trim()))
			return false;
		if(!dataVo1.getOldCode().trim().equals(dataVo2.getOldCode().trim()))
			return false;
		if(!dataVo1.getSearchidx().trim().equals(dataVo2.getSearchidx().trim()))
			return false;
		if(!dataVo1.getUsingFlg().trim().equals(dataVo2.getUsingFlg().trim()))
			return false;
		if(!dataVo1.getUseFlg().trim().equals(dataVo2.getUseFlg().trim()))
			return false;
		if(dataVo1.getPwdDate() != dataVo2.getPwdDate())
			return false;
		if(!dataVo1.getPwd1().trim().equals(dataVo2.getPwd1().trim()))
			return false;
		if(!dataVo1.getPwd2().trim().equals(dataVo2.getPwd2().trim()))
			return false;
		if(!dataVo1.getPwd3().trim().equals(dataVo2.getPwd3().trim()))
			return false;
		return true;
	}
}