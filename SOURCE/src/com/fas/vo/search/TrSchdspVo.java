/************************************************************************************
*
*	会社名		： 林兼コンピューター株式会社
*
*	プロジェクト名	： fas
*
*	ファイル名		： MSchdspVo.java
*
*	記述			：
*
*	作成日		：  2011/09/06  10:24:09 午前
*
*	作成者		： LENOVO-F23A3B72
*
*	備考			：
*
************************************************************************************/

package com.fas.vo.search;

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
public class TrSchdspVo extends BaseVo {

	/**	 */
	private static final long serialVersionUID = 1L;

	/** DSP_CODE */
	private String dspCode = "";
	/** DSP_NAME */
	private String dspName = "";
	/** SCH_CTL */
	private String schCtl = "";
	/** TBL_NAME */
	private String tblName = "";
	
	/** SQL_ID: SQLID get from XML */
	private String sqlId = "";
	
	/** TTL_NAME1: Line 1 Label 1 */
	private String ttlName1 = "";
	
	/** TTL_NAME2: Line 2 Label 1 */
	private String ttlName2 = "";
	
	/** KBN_GRP */
	private String kbnGrp = "";
	/** CMB_ITEM1: Line 1 Cmb 2 */
	private String cbbItem1 = "";
	/** TXT_ITEM1: Line 2 Txt 2 */
	private String txtItem1 = "";
	
	//SPD_COL: this Column is Visible, Invisible. If 1 Invisible, If 0 Visible
	/** SPD_COL1 */
	private String spdCol1 = "";
	/** SPD_COL2 */
	private String spdCol2 = "";
	/** SPD_COL3 */
	private String spdCol3 = "";
	/** SPD_COL4 */
	private String spdCol4 = "";
	/** SPD_COL5 */
	private String spdCol5 = "";
	/** SPD_COL6 */
	private String spdCol6 = "";
	/** SPD_COL7 */
	private String spdCol7 = "";
	/** SPD_COL8 */
	private String spdCol8 = "";
	/** SPD_COL9 */
	private String spdCol9 = "";
	/** SPD_COL10 */
	private String spdCol10 = "";
	/** SPD_COL11 */
	private String spdCol11 = "";
	/** SPD_COL12 */
	private String spdCol12 = "";
	
	//SPD_COL: this Column HeaderTitle
	/** SPD_COLNM1 */
	private String spdColnm1 = "";
	/** SPD_COLNM2 */
	private String spdColnm2 = "";
	/** SPD_COLNM3 */
	private String spdColnm3 = "";
	/** SPD_COLNM4 */
	private String spdColnm4 = "";
	/** SPD_COLNM5 */
	private String spdColnm5 = "";
	/** SPD_COLNM6 */
	private String spdColnm6 = "";
	/** SPD_COLNM7 */
	private String spdColnm7 = "";
	/** SPD_COLNM8 */
	private String spdColnm8 = "";
	/** SPD_COLNM9 */
	private String spdColnm9 = "";
	/** SPD_COLNM10 */
	private String spdColnm10 = "";
	/** SPD_COLNM11 */
	private String spdColnm11 = "";
	/** SPD_COLNM12 */
	private String spdColnm12 = "";
	
	//SPD_COLWD: this Column Width
	/** SPD_COLWD1 */
	private int spdColwd1 = 0;
	/** SPD_COLWD2 */
	private int spdColwd2 = 0;
	/** SPD_COLWD3 */
	private int spdColwd3 = 0;
	/** SPD_COLWD4 */
	private int spdColwd4 = 0;
	/** SPD_COLWD5 */
	private int spdColwd5 = 0;
	/** SPD_COLWD6 */
	private int spdColwd6 = 0;
	/** SPD_COLWD7 */
	private int spdColwd7 = 0;
	/** SPD_COLWD8 */
	private int spdColwd8 = 0;
	/** SPD_COLWD9 */
	private int spdColwd9 = 0;
	/** SPD_COLWD10 */
	private int spdColwd10 = 0;
	/** SPD_COLWD11 */
	private int spdColwd11 = 0;
	/** SPD_COLWD12 */
	private int spdColwd12 = 0;
	
	
	/** SPD_COLUSE */
	private int spdColuse = 0;
	/** SPD_COLWDC */
	private int spdColwdc = 0;
	/** SPD_COLHID */
	private int spdColhid = 0;
	
	
	//SCH_ITEM: this Item Visible, Invisible. If 1 Invisible, If 0 Visible
	/** SCH_ITEM1 */
	private String schItem1 = "";
	/** SCH_ITEM2 */
	private String schItem2 = "";
	/** SCH_ITEM3 */
	private String schItem3 = "";
	/** SCH_ITEM4 */
	private String schItem4 = "";
	/** SCH_ITEM5 */
	private String schItem5 = "";
	/** SCH_ITEM6 */
	private String schItem6 = "";
	
	//SCH_NAME: this Item Title
	/** SCH_NAME1 */
	private String schName1 = "";
	/** SCH_NAME2 */
	private String schName2 = "";
	/** SCH_NAME3 */
	private String schName3 = "";
	/** SCH_NAME4 */
	private String schName4 = "";
	/** SCH_NAME5 */
	private String schName5 = "";
	/** SCH_NAME6 */
	private String schName6 = "";
	
	//CHK_USE: CheckUse Visible, Invisible
	/** CHK_USE */
	private String chkUse = "";
	//KANA_ITEM: ListKanaItem Visible, Invisible
	/** KANA_ITEM */
	private String kanaItem = "";
	//HELP_HTML: Help file
	/** HELP_HTML */
	private String helpHtml = "";
	/** USE_FLG */
	private String useFlg = "";	

	/** 
	* <DL>
	*   <DT>コンストラクター記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	*/
	public TrSchdspVo(){
	}

	/* begin: Public Properties */	
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
		return "DSP_CODE: " + this.dspCode + "; ";
	}

	/**
	 * @return the dspCode
	 */
	public String getDspCode() {
		return dspCode;
	}

	/**
	 * @param dspCode the dspCode to set
	 */
	public void setDspCode(String dspCode) {
		this.dspCode = dspCode;
	}

	/**
	 * @return the dspName
	 */
	public String getDspName() {
		return dspName;
	}

	/**
	 * @param dspName the dspName to set
	 */
	public void setDspName(String dspName) {
		this.dspName = dspName;
	}

	/**
	 * @return the schCtl
	 */
	public String getSchCtl() {
		return schCtl;
	}

	/**
	 * @param schCtl the schCtl to set
	 */
	public void setSchCtl(String schCtl) {
		this.schCtl = schCtl;
	}

	/**
	 * @return the tblName
	 */
	public String getTblName() {
		return tblName;
	}

	/**
	 * @param tblName the tblName to set
	 */
	public void setTblName(String tblName) {
		this.tblName = tblName;
	}

	/**
	 * @return the sqlId
	 */
	public String getSqlId() {
		return sqlId;
	}

	/**
	 * @param sqlId the sqlId to set
	 */
	public void setSqlId(String sqlId) {
		this.sqlId = sqlId;
	}

	/**
	 * @return the ttlName1
	 */
	public String getTtlName1() {
		return ttlName1;
	}

	/**
	 * @param ttlName1 the ttlName1 to set
	 */
	public void setTtlName1(String ttlName1) {
		this.ttlName1 = ttlName1;
	}

	/**
	 * @return the ttlName2
	 */
	public String getTtlName2() {
		return ttlName2;
	}

	/**
	 * @param ttlName2 the ttlName2 to set
	 */
	public void setTtlName2(String ttlName2) {
		this.ttlName2 = ttlName2;
	}

	/**
	 * @return the kbnGrp
	 */
	public String getKbnGrp() {
		return kbnGrp;
	}

	/**
	 * @param kbnGrp the kbnGrp to set
	 */
	public void setKbnGrp(String kbnGrp) {
		this.kbnGrp = kbnGrp;
	}

	/**
	 * @return the cbbItem1
	 */
	public String getCmbItem1() {
		return cbbItem1;
	}

	/**
	 * @param cbbItem1 the cbbItem1 to set
	 */
	public void setCmbItem1(String cbbItem1) {
		this.cbbItem1 = cbbItem1;
	}

	/**
	 * @return the txtItem1
	 */
	public String getTxtItem1() {
		return txtItem1;
	}

	/**
	 * @param txtItem1 the txtItem1 to set
	 */
	public void setTxtItem1(String txtItem1) {
		this.txtItem1 = txtItem1;
	}

	/**
	 * @return the spdCol1
	 */
	public String getSpdCol1() {
		return spdCol1;
	}

	/**
	 * @param spdCol1 the spdCol1 to set
	 */
	public void setSpdCol1(String spdCol1) {
		this.spdCol1 = spdCol1;
	}

	/**
	 * @return the spdCol2
	 */
	public String getSpdCol2() {
		return spdCol2;
	}

	/**
	 * @param spdCol2 the spdCol2 to set
	 */
	public void setSpdCol2(String spdCol2) {
		this.spdCol2 = spdCol2;
	}

	/**
	 * @return the spdCol3
	 */
	public String getSpdCol3() {
		return spdCol3;
	}

	/**
	 * @param spdCol3 the spdCol3 to set
	 */
	public void setSpdCol3(String spdCol3) {
		this.spdCol3 = spdCol3;
	}

	/**
	 * @return the spdCol4
	 */
	public String getSpdCol4() {
		return spdCol4;
	}

	/**
	 * @param spdCol4 the spdCol4 to set
	 */
	public void setSpdCol4(String spdCol4) {
		this.spdCol4 = spdCol4;
	}

	/**
	 * @return the spdCol5
	 */
	public String getSpdCol5() {
		return spdCol5;
	}

	/**
	 * @param spdCol5 the spdCol5 to set
	 */
	public void setSpdCol5(String spdCol5) {
		this.spdCol5 = spdCol5;
	}

	/**
	 * @return the spdCol6
	 */
	public String getSpdCol6() {
		return spdCol6;
	}

	/**
	 * @param spdCol6 the spdCol6 to set
	 */
	public void setSpdCol6(String spdCol6) {
		this.spdCol6 = spdCol6;
	}

	/**
	 * @return the spdCol7
	 */
	public String getSpdCol7() {
		return spdCol7;
	}

	/**
	 * @param spdCol7 the spdCol7 to set
	 */
	public void setSpdCol7(String spdCol7) {
		this.spdCol7 = spdCol7;
	}

	/**
	 * @return the spdCol8
	 */
	public String getSpdCol8() {
		return spdCol8;
	}

	/**
	 * @param spdCol8 the spdCol8 to set
	 */
	public void setSpdCol8(String spdCol8) {
		this.spdCol8 = spdCol8;
	}

	/**
	 * @return the spdCol9
	 */
	public String getSpdCol9() {
		return spdCol9;
	}

	/**
	 * @param spdCol9 the spdCol9 to set
	 */
	public void setSpdCol9(String spdCol9) {
		this.spdCol9 = spdCol9;
	}

	/**
	 * @return the spdCol10
	 */
	public String getSpdCol10() {
		return spdCol10;
	}

	/**
	 * @param spdCol10 the spdCol10 to set
	 */
	public void setSpdCol10(String spdCol10) {
		this.spdCol10 = spdCol10;
	}

	/**
	 * @return the spdCol11
	 */
	public String getSpdCol11() {
		return spdCol11;
	}

	/**
	 * @param spdCol11 the spdCol11 to set
	 */
	public void setSpdCol11(String spdCol11) {
		this.spdCol11 = spdCol11;
	}

	/**
	 * @return the spdCol12
	 */
	public String getSpdCol12() {
		return spdCol12;
	}

	/**
	 * @param spdCol12 the spdCol12 to set
	 */
	public void setSpdCol12(String spdCol12) {
		this.spdCol12 = spdCol12;
	}

	/**
	 * @return the spdColnm1
	 */
	public String getSpdColnm1() {
		return spdColnm1;
	}

	/**
	 * @param spdColnm1 the spdColnm1 to set
	 */
	public void setSpdColnm1(String spdColnm1) {
		this.spdColnm1 = spdColnm1;
	}

	/**
	 * @return the spdColnm2
	 */
	public String getSpdColnm2() {
		return spdColnm2;
	}

	/**
	 * @param spdColnm2 the spdColnm2 to set
	 */
	public void setSpdColnm2(String spdColnm2) {
		this.spdColnm2 = spdColnm2;
	}

	/**
	 * @return the spdColnm3
	 */
	public String getSpdColnm3() {
		return spdColnm3;
	}

	/**
	 * @param spdColnm3 the spdColnm3 to set
	 */
	public void setSpdColnm3(String spdColnm3) {
		this.spdColnm3 = spdColnm3;
	}

	/**
	 * @return the spdColnm4
	 */
	public String getSpdColnm4() {
		return spdColnm4;
	}

	/**
	 * @param spdColnm4 the spdColnm4 to set
	 */
	public void setSpdColnm4(String spdColnm4) {
		this.spdColnm4 = spdColnm4;
	}

	/**
	 * @return the spdColnm5
	 */
	public String getSpdColnm5() {
		return spdColnm5;
	}

	/**
	 * @param spdColnm5 the spdColnm5 to set
	 */
	public void setSpdColnm5(String spdColnm5) {
		this.spdColnm5 = spdColnm5;
	}

	/**
	 * @return the spdColnm6
	 */
	public String getSpdColnm6() {
		return spdColnm6;
	}

	/**
	 * @param spdColnm6 the spdColnm6 to set
	 */
	public void setSpdColnm6(String spdColnm6) {
		this.spdColnm6 = spdColnm6;
	}

	/**
	 * @return the spdColnm7
	 */
	public String getSpdColnm7() {
		return spdColnm7;
	}

	/**
	 * @param spdColnm7 the spdColnm7 to set
	 */
	public void setSpdColnm7(String spdColnm7) {
		this.spdColnm7 = spdColnm7;
	}

	/**
	 * @return the spdColnm8
	 */
	public String getSpdColnm8() {
		return spdColnm8;
	}

	/**
	 * @param spdColnm8 the spdColnm8 to set
	 */
	public void setSpdColnm8(String spdColnm8) {
		this.spdColnm8 = spdColnm8;
	}

	/**
	 * @return the spdColnm9
	 */
	public String getSpdColnm9() {
		return spdColnm9;
	}

	/**
	 * @param spdColnm9 the spdColnm9 to set
	 */
	public void setSpdColnm9(String spdColnm9) {
		this.spdColnm9 = spdColnm9;
	}

	/**
	 * @return the spdColnm10
	 */
	public String getSpdColnm10() {
		return spdColnm10;
	}

	/**
	 * @param spdColnm10 the spdColnm10 to set
	 */
	public void setSpdColnm10(String spdColnm10) {
		this.spdColnm10 = spdColnm10;
	}

	/**
	 * @return the spdColnm11
	 */
	public String getSpdColnm11() {
		return spdColnm11;
	}

	/**
	 * @param spdColnm11 the spdColnm11 to set
	 */
	public void setSpdColnm11(String spdColnm11) {
		this.spdColnm11 = spdColnm11;
	}

	/**
	 * @return the spdColnm12
	 */
	public String getSpdColnm12() {
		return spdColnm12;
	}

	/**
	 * @param spdColnm12 the spdColnm12 to set
	 */
	public void setSpdColnm12(String spdColnm12) {
		this.spdColnm12 = spdColnm12;
	}

	/**
	 * @return the spdColwd1
	 */
	public int getSpdColwd1() {
		return spdColwd1;
	}

	/**
	 * @param spdColwd1 the spdColwd1 to set
	 */
	public void setSpdColwd1(int spdColwd1) {
		this.spdColwd1 = spdColwd1;
	}

	/**
	 * @return the spdColwd2
	 */
	public int getSpdColwd2() {
		return spdColwd2;
	}

	/**
	 * @param spdColwd2 the spdColwd2 to set
	 */
	public void setSpdColwd2(int spdColwd2) {
		this.spdColwd2 = spdColwd2;
	}

	/**
	 * @return the spdColwd3
	 */
	public int getSpdColwd3() {
		return spdColwd3;
	}

	/**
	 * @param spdColwd3 the spdColwd3 to set
	 */
	public void setSpdColwd3(int spdColwd3) {
		this.spdColwd3 = spdColwd3;
	}

	/**
	 * @return the spdColwd4
	 */
	public int getSpdColwd4() {
		return spdColwd4;
	}

	/**
	 * @param spdColwd4 the spdColwd4 to set
	 */
	public void setSpdColwd4(int spdColwd4) {
		this.spdColwd4 = spdColwd4;
	}

	/**
	 * @return the spdColwd5
	 */
	public int getSpdColwd5() {
		return spdColwd5;
	}

	/**
	 * @param spdColwd5 the spdColwd5 to set
	 */
	public void setSpdColwd5(int spdColwd5) {
		this.spdColwd5 = spdColwd5;
	}

	/**
	 * @return the spdColwd6
	 */
	public int getSpdColwd6() {
		return spdColwd6;
	}

	/**
	 * @param spdColwd6 the spdColwd6 to set
	 */
	public void setSpdColwd6(int spdColwd6) {
		this.spdColwd6 = spdColwd6;
	}

	/**
	 * @return the spdColwd7
	 */
	public int getSpdColwd7() {
		return spdColwd7;
	}

	/**
	 * @param spdColwd7 the spdColwd7 to set
	 */
	public void setSpdColwd7(int spdColwd7) {
		this.spdColwd7 = spdColwd7;
	}

	/**
	 * @return the spdColwd8
	 */
	public int getSpdColwd8() {
		return spdColwd8;
	}

	/**
	 * @param spdColwd8 the spdColwd8 to set
	 */
	public void setSpdColwd8(int spdColwd8) {
		this.spdColwd8 = spdColwd8;
	}

	/**
	 * @return the spdColwd9
	 */
	public int getSpdColwd9() {
		return spdColwd9;
	}

	/**
	 * @param spdColwd9 the spdColwd9 to set
	 */
	public void setSpdColwd9(int spdColwd9) {
		this.spdColwd9 = spdColwd9;
	}

	/**
	 * @return the spdColwd10
	 */
	public int getSpdColwd10() {
		return spdColwd10;
	}

	/**
	 * @param spdColwd10 the spdColwd10 to set
	 */
	public void setSpdColwd10(int spdColwd10) {
		this.spdColwd10 = spdColwd10;
	}

	/**
	 * @return the spdColwd11
	 */
	public int getSpdColwd11() {
		return spdColwd11;
	}

	/**
	 * @param spdColwd11 the spdColwd11 to set
	 */
	public void setSpdColwd11(int spdColwd11) {
		this.spdColwd11 = spdColwd11;
	}

	/**
	 * @return the spdColwd12
	 */
	public int getSpdColwd12() {
		return spdColwd12;
	}

	/**
	 * @param spdColwd12 the spdColwd12 to set
	 */
	public void setSpdColwd12(int spdColwd12) {
		this.spdColwd12 = spdColwd12;
	}

	/**
	 * @return the spdColuse
	 */
	public int getSpdColuse() {
		return spdColuse;
	}

	/**
	 * @param spdColuse the spdColuse to set
	 */
	public void setSpdColuse(int spdColuse) {
		this.spdColuse = spdColuse;
	}

	/**
	 * @return the spdColwdc
	 */
	public int getSpdColwdc() {
		return spdColwdc;
	}

	/**
	 * @param spdColwdc the spdColwdc to set
	 */
	public void setSpdColwdc(int spdColwdc) {
		this.spdColwdc = spdColwdc;
	}

	/**
	 * @return the spdColhid
	 */
	public int getSpdColhid() {
		return spdColhid;
	}

	/**
	 * @param spdColhid the spdColhid to set
	 */
	public void setSpdColhid(int spdColhid) {
		this.spdColhid = spdColhid;
	}

	/**
	 * @return the schItem1
	 */
	public String getSchItem1() {
		return schItem1;
	}

	/**
	 * @param schItem1 the schItem1 to set
	 */
	public void setSchItem1(String schItem1) {
		this.schItem1 = schItem1;
	}

	/**
	 * @return the schItem2
	 */
	public String getSchItem2() {
		return schItem2;
	}

	/**
	 * @param schItem2 the schItem2 to set
	 */
	public void setSchItem2(String schItem2) {
		this.schItem2 = schItem2;
	}

	/**
	 * @return the schItem3
	 */
	public String getSchItem3() {
		return schItem3;
	}

	/**
	 * @param schItem3 the schItem3 to set
	 */
	public void setSchItem3(String schItem3) {
		this.schItem3 = schItem3;
	}

	/**
	 * @return the schItem4
	 */
	public String getSchItem4() {
		return schItem4;
	}

	/**
	 * @param schItem4 the schItem4 to set
	 */
	public void setSchItem4(String schItem4) {
		this.schItem4 = schItem4;
	}

	/**
	 * @return the schItem5
	 */
	public String getSchItem5() {
		return schItem5;
	}

	/**
	 * @param schItem5 the schItem5 to set
	 */
	public void setSchItem5(String schItem5) {
		this.schItem5 = schItem5;
	}

	/**
	 * @return the schItem6
	 */
	public String getSchItem6() {
		return schItem6;
	}

	/**
	 * @param schItem6 the schItem6 to set
	 */
	public void setSchItem6(String schItem6) {
		this.schItem6 = schItem6;
	}

	/**
	 * @return the schName1
	 */
	public String getSchName1() {
		return schName1;
	}

	/**
	 * @param schName1 the schName1 to set
	 */
	public void setSchName1(String schName1) {
		this.schName1 = schName1;
	}

	/**
	 * @return the schName2
	 */
	public String getSchName2() {
		return schName2;
	}

	/**
	 * @param schName2 the schName2 to set
	 */
	public void setSchName2(String schName2) {
		this.schName2 = schName2;
	}

	/**
	 * @return the schName3
	 */
	public String getSchName3() {
		return schName3;
	}

	/**
	 * @param schName3 the schName3 to set
	 */
	public void setSchName3(String schName3) {
		this.schName3 = schName3;
	}

	/**
	 * @return the schName4
	 */
	public String getSchName4() {
		return schName4;
	}

	/**
	 * @param schName4 the schName4 to set
	 */
	public void setSchName4(String schName4) {
		this.schName4 = schName4;
	}

	/**
	 * @return the schName5
	 */
	public String getSchName5() {
		return schName5;
	}

	/**
	 * @param schName5 the schName5 to set
	 */
	public void setSchName5(String schName5) {
		this.schName5 = schName5;
	}

	/**
	 * @return the schName6
	 */
	public String getSchName6() {
		return schName6;
	}

	/**
	 * @param schName6 the schName6 to set
	 */
	public void setSchName6(String schName6) {
		this.schName6 = schName6;
	}

	/**
	 * @return the chkUse
	 */
	public String getChkUse() {
		return chkUse;
	}

	/**
	 * @param chkUse the chkUse to set
	 */
	public void setChkUse(String chkUse) {
		this.chkUse = chkUse;
	}

	/**
	 * @return the kanaItem
	 */
	public String getKanaItem() {
		return kanaItem;
	}

	/**
	 * @param kanaItem the kanaItem to set
	 */
	public void setKanaItem(String kanaItem) {
		this.kanaItem = kanaItem;
	}

	/**
	 * @return the helpHtml
	 */
	public String getHelpHtml() {
		return helpHtml;
	}

	/**
	 * @param helpHtml the helpHtml to set
	 */
	public void setHelpHtml(String helpHtml) {
		this.helpHtml = helpHtml;
	}

	/**
	 * @return the useFlg
	 */
	public String getUseFlg() {
		return useFlg;
	}

	/**
	 * @param useFlg the useFlg to set
	 */
	public void setUseFlg(String useFlg) {
		this.useFlg = useFlg;
	}

	/* end: Public Properties */
}