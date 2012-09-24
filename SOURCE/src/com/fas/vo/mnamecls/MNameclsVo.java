/************************************************************************************
*
*	会社名		： 林兼コンピューター株式会社
*
*	プロジェクト名	： fas
*
*	ファイル名		： MNameclsVo.java
*
*	記述			：
*
*	作成日		：  2011/09/02  10:22:43 午前
*
*	作成者		： LENOVO-F23A3B72
*
*	備考			：
*
************************************************************************************/

package com.fas.vo.mnamecls;

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
public class MNameclsVo extends BaseVo {

	/**	 */
	private static final long serialVersionUID = 1L;

	/** 名称種別コード */
	private String nameclsCode = "";
	/** 名称種別名 */
	private String nameclsName = "";
	/** ヘルプ */
	private String help = "";
	/** 使用可否フラグ */
	private String useFlg = "";
	/** 表示 SEQ */
	private String dspSeq = "";	

	/** 
	* <DL>
	*   <DT>コンストラクター記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	*/
	public MNameclsVo(){
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
	* @return the nameclsName
	*/
	public String getNameclsName() {
		return this.nameclsName;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param nameclsName
	*/
	public void setNameclsName(String nameclsName) {
		this.nameclsName = nameclsName;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the help
	*/
	public String getHelp() {
		return this.help;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param help
	*/
	public void setHelp(String help) {
		this.help = help;
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
	* @return the dspSeq
	*/
	public String getDspSeq() {
		return this.dspSeq;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param dspSeq
	*/
	public void setDspSeq(String dspSeq) {
		this.dspSeq = dspSeq;
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
		return "NAMECLS_CODE: " + this.nameclsCode + "; ";
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
		this.setNameclsName(StringUtils.nulToString(rs.getString("NAMECLS_NAME")));
		this.setHelp(StringUtils.nulToString(rs.getString("HELP")));
		this.setUseFlg(StringUtils.nulToString(rs.getString("USE_FLG")));
		this.setDspSeq(StringUtils.nulToString(rs.getString("DSP_SEQ")));		
	}
	
	static public boolean equal(MNameclsVo dataVo1, MNameclsVo dataVo2) {
		if (dataVo1 == null && dataVo2 == null) return true;
		if (dataVo1 == null || dataVo2 == null) return false;

		if(!dataVo1.getNameclsCode().trim().equals(dataVo2.getNameclsCode().trim()))
			return false;
		if(!dataVo1.getNameclsName().trim().equals(dataVo2.getNameclsName().trim()))
			return false;
		if(!dataVo1.getHelp().trim().equals(dataVo2.getHelp().trim()))
			return false;
		if(!dataVo1.getUseFlg().trim().equals(dataVo2.getUseFlg().trim()))
			return false;
		if(!dataVo1.getDspSeq().trim().equals(dataVo2.getDspSeq().trim()))
			return false;
		return true;
	}
}