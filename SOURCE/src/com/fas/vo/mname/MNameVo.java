/************************************************************************************
*
*	会社名		： 林兼コンピューター株式会社
*
*	プロジェクト名	： fas
*
*	ファイル名		： MNameVo.java
*
*	記述			：
*
*	作成日		：  2011/09/02  10:18:48 午前
*
*	作成者		： LENOVO-F23A3B72
*
*	備考			：
*
************************************************************************************/

package com.fas.vo.mname;

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
public class MNameVo extends BaseVo {

	/**	 */
	private static final long serialVersionUID = 1L;

	/** NAMECLS_CODE */
	private String nameclsCode = "";
	/** NAME_CODE */
	private String nameCode = "";
	/** NAME_NAME */
	private String nameName = "";
	/** NAME_RNAME */
	private String nameRname = "";
	/** NAME_PNAME */
	private String namePname = "";
	/** NAME_TNAME */
	private String nameTname = "";
	/** DEFAULT_TYPE */
	private String defaultType = "";
	/** DSP_FLG */
	private String dspFlg = "";
	/** DSPORDER_NO */
	private int dsporderNo = 0;
	/** NAMETYPE */
	private String nametype = "";
	/** ADD_USER */
	private String addUser = "";
	/** ADD_PC */
	private String addPc = "";
	/** ADD_DATE */
	private int addDate = 0;
	/** ADD_TIME */
	private int addTime = 0;
	/** LASTUP_USER */
	private String lastupUser = "";
	/** LASTUP_PC */
	private String lastupPc = "";
	/** LASTUP_DATE */
	private int lastupDate = 0;
	/** LASTUP_TIME */
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
	/** */
	private String nameclsName ="";
	/** 
	* <DL>
	*   <DT>コンストラクター記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	*/
	public MNameVo(){
	}

	/* begin: Public Properties */
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the nameclsCode
	*/
	public String getNameclsCode() {
		return this.nameclsCode;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param nameclsCode
	*/
	public void setNameclsCode(String nameclsCode) {
		this.nameclsCode = nameclsCode;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the nameCode
	*/
	public String getNameCode() {
		return this.nameCode;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param nameCode
	*/
	public void setNameCode(String nameCode) {
		this.nameCode = nameCode;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the nameName
	*/
	public String getNameName() {
		return this.nameName;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param nameName
	*/
	public void setNameName(String nameName) {
		this.nameName = nameName;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the nameRname
	*/
	public String getNameRname() {
		return this.nameRname;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param nameRname
	*/
	public void setNameRname(String nameRname) {
		this.nameRname = nameRname;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the namePname
	*/
	public String getNamePname() {
		return this.namePname;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param namePname
	*/
	public void setNamePname(String namePname) {
		this.namePname = namePname;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the nameTname
	*/
	public String getNameTname() {
		return this.nameTname;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param nameTname
	*/
	public void setNameTname(String nameTname) {
		this.nameTname = nameTname;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the defaultType
	*/
	public String getDefaultType() {
		return this.defaultType;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param defaultType
	*/
	public void setDefaultType(String defaultType) {
		this.defaultType = defaultType;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the dspFlg
	*/
	public String getDspFlg() {
		return this.dspFlg;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param dspFlg
	*/
	public void setDspFlg(String dspFlg) {
		this.dspFlg = dspFlg;
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
	* @return the nametype
	*/
	public String getNametype() {
		return this.nametype;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param nametype
	*/
	public void setNametype(String nametype) {
		this.nametype = nametype;
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
		
    /**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the nameclsName
	 */
	public String getNameclsName() {
		return nameclsName;
	}
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param nameclsName the nameclsName to set
	 */
	public void setNameclsName(String nameclsName) {
		this.nameclsName = nameclsName;
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
		return "NAMECLS_CODE: " + this.nameclsCode + "; " + "NAME_CODE: " + this.nameCode + "; ";
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
		this.setNameclsCode(StringUtils.nulToString(rs.getString("NAMECLS_CODE")));
		this.setNameCode(StringUtils.nulToString(rs.getString("NAME_CODE")));
		this.setNameName(StringUtils.nulToString(rs.getString("NAME_NAME")));
		this.setNameRname(StringUtils.nulToString(rs.getString("NAME_RNAME")));
		this.setNamePname(StringUtils.nulToString(rs.getString("NAME_PNAME")));
		this.setNameTname(StringUtils.nulToString(rs.getString("NAME_TNAME")));
		this.setDefaultType(StringUtils.nulToString(rs.getString("DEFAULT_TYPE")));
		this.setDspFlg(StringUtils.nulToString(rs.getString("DSP_FLG")));
		this.setDsporderNo(rs.getInt("DSPORDER_NO"));
		this.setNametype(StringUtils.nulToString(rs.getString("NAMETYPE")));
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
	
	static public boolean equal(MNameVo dataVo1, MNameVo dataVo2) {
		if (dataVo1 == null && dataVo2 == null) return true;
		if (dataVo1 == null || dataVo2 == null) return false;

		if(!dataVo1.getNameclsCode().trim().equals(dataVo2.getNameclsCode().trim()))
			return false;
		if(!dataVo1.getNameCode().trim().equals(dataVo2.getNameCode().trim()))
			return false;
		if(!dataVo1.getNameName().trim().equals(dataVo2.getNameName().trim()))
			return false;
		if(!dataVo1.getNameRname().trim().equals(dataVo2.getNameRname().trim()))
			return false;
		if(!dataVo1.getNamePname().trim().equals(dataVo2.getNamePname().trim()))
			return false;
		if(!dataVo1.getNameTname().trim().equals(dataVo2.getNameTname().trim()))
			return false;
		if(!dataVo1.getDefaultType().trim().equals(dataVo2.getDefaultType().trim()))
			return false;
		if(!dataVo1.getDspFlg().trim().equals(dataVo2.getDspFlg().trim()))
			return false;
		if(dataVo1.getDsporderNo() != dataVo2.getDsporderNo())
			return false;
		if(!dataVo1.getNametype().trim().equals(dataVo2.getNametype().trim()))
			return false;
		return true;
	}
}
