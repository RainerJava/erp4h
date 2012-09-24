/************************************************************************************
*
*	会社名		： 林兼コンピューター株式会社
*
*	プロジェクト名	： fas
*
*	ファイル名		： MCtlVo.java
*
*	記述			：
*
*	作成日		：  2011/09/01  08:50:51 午前
*
*	作成者		： LENOVO-F23A3B72
*
*	備考			：
*
************************************************************************************/

package com.fas.vo.mctl;

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
public class MCtlVo extends BaseVo {

	/**	 */
	private static final long serialVersionUID = 1L;

	/** USERID */
	private String userid = "";
	/** KEY */
	private String cKey = "";
	/** NAME */
	private String cName = "";
	/** DATA */
	private String cData = "";
	/** HELP */
	private String cHelp = "";
	/** 入力桁数 */
	private String cBm = "";
	/** 入力桁数(小数桁) */
	private String cDecbm = "";
	/** 入力属性地 */
	private String cAttr = "";
	/** メンテフラグ */
	private String mtnFlg = "";
	/** コントロールフラグ */
	private String cntFlg = "";
	/** 変更可否フラグ */
	private String updFlg = "";
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
	public MCtlVo(){
	}

	/* begin: Public Properties */
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the userid
	*/
	public String getUserid() {
		return this.userid;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param userid
	*/
	public void setUserid(String userid) {
		this.userid = userid;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the cKey
	*/
	public String getCKey() {
		return this.cKey;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param cKey
	*/
	public void setCKey(String cKey) {
		this.cKey = cKey;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the cName
	*/
	public String getCName() {
		return this.cName;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param cName
	*/
	public void setCName(String cName) {
		this.cName = cName;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the cData
	*/
	public String getCData() {
		return this.cData;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param cData
	*/
	public void setCData(String cData) {
		this.cData = cData;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the cHelp
	*/
	public String getCHelp() {
		return this.cHelp;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param cHelp
	*/
	public void setCHelp(String cHelp) {
		this.cHelp = cHelp;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the cBm
	*/
	public String getCBm() {
		return this.cBm;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param cBm
	*/
	public void setCBm(String cBm) {
		this.cBm = cBm;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the cDecbm
	*/
	public String getCDecbm() {
		return this.cDecbm;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param cDecbm
	*/
	public void setCDecbm(String cDecbm) {
		this.cDecbm = cDecbm;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the cAttr
	*/
	public String getCAttr() {
		return this.cAttr;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param cAttr
	*/
	public void setCAttr(String cAttr) {
		this.cAttr = cAttr;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the mtnFlg
	*/
	public String getMtnFlg() {
		return this.mtnFlg;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param mtnFlg
	*/
	public void setMtnFlg(String mtnFlg) {
		this.mtnFlg = mtnFlg;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the cntFlg
	*/
	public String getCntFlg() {
		return this.cntFlg;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param cntFlg
	*/
	public void setCntFlg(String cntFlg) {
		this.cntFlg = cntFlg;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the updFlg
	*/
	public String getUpdFlg() {
		return this.updFlg;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param updFlg
	*/
	public void setUpdFlg(String updFlg) {
		this.updFlg = updFlg;
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
		return "USERID: " + this.userid + "; " + "C_KEY: " + this.cKey + "; ";
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
		this.setUserid(StringUtils.nulToString(rs.getString("USERID")));
		this.setCKey(StringUtils.nulToString(rs.getString("C_KEY")));
		this.setCName(StringUtils.nulToString(rs.getString("C_NAME")));
		this.setCData(StringUtils.nulToString(rs.getString("C_DATA")));
		this.setCHelp(StringUtils.nulToString(rs.getString("C_HELP")));
		this.setCBm(StringUtils.nulToString(rs.getString("C_BM")));
		this.setCDecbm(StringUtils.nulToString(rs.getString("C_DECBM")));
		this.setCAttr(StringUtils.nulToString(rs.getString("C_ATTR")));
		this.setMtnFlg(StringUtils.nulToString(rs.getString("MTN_FLG")));
		this.setCntFlg(StringUtils.nulToString(rs.getString("CNT_FLG")));
		this.setUpdFlg(StringUtils.nulToString(rs.getString("UPD_FLG")));
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
	
	static public boolean equal(MCtlVo dataVo1, MCtlVo dataVo2) {
		if (dataVo1 == null && dataVo2 == null) return true;
		if (dataVo1 == null || dataVo2 == null) return false;

		if(!dataVo1.getUserid().trim().equals(dataVo2.getUserid().trim()))
			return false;
		if(!dataVo1.getCKey().trim().equals(dataVo2.getCKey().trim()))
			return false;
		if(!dataVo1.getCName().trim().equals(dataVo2.getCName().trim()))
			return false;
		if(!dataVo1.getCData().trim().equals(dataVo2.getCData().trim()))
			return false;
		if(!dataVo1.getCHelp().trim().equals(dataVo2.getCHelp().trim()))
			return false;
		if(!dataVo1.getCBm().trim().equals(dataVo2.getCBm().trim()))
			return false;
		if(!dataVo1.getCDecbm().trim().equals(dataVo2.getCDecbm().trim()))
			return false;
		if(!dataVo1.getCAttr().trim().equals(dataVo2.getCAttr().trim()))
			return false;
		if(!dataVo1.getMtnFlg().trim().equals(dataVo2.getMtnFlg().trim()))
			return false;
		if(!dataVo1.getCntFlg().trim().equals(dataVo2.getCntFlg().trim()))
			return false;
		if(!dataVo1.getUpdFlg().trim().equals(dataVo2.getUpdFlg().trim()))
			return false;
		return true;
	}
}