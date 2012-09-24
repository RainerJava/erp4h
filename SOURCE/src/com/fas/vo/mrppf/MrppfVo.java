/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名		：
* 
*     ファイル名		：MrppfVo.java
*
*     記述			：
*     
*     作成日			：2010/09/13
*
*     作成者			：phuongnt
*
*     備考			：
*
**************************************************************************************/

package com.fas.vo.mrppf;

import com.fas.vo.mrppf.MrppfVo;



/**
 * <DL>
 *   <DT>クラス記述：</DT>
 * 	<DD></DD>
 * <BR>
 *   <DT>変更歴史：</DT>
 * 	<DD>著作者: phuongnt</DD><BR>
 * 	<DD></DD>
 * </DL>
 */
public class MrppfVo {

	public String getLclsName() {
		return lclsName;
	}

	public void setLclsName(String lclsName) {
		this.lclsName = lclsName;
	}

	public String getMclsName() {
		return mclsName;
	}

	public void setMclsName(String mclsName) {
		this.mclsName = mclsName;
	}

	

	/** 大分類 - LCLS_CODE */
	private String lclsCode = "";
	
	private String lclsName = "";
	
	/** 大分類 - MCLS_CODE */	
	private String mclsCode = "";
	
	private String mclsName = "";	
	
	/** 呼径コード(From) */
	
	private String pdmeterFrom = "";
	
	/** 呼径コード(To) */
	
	private String pdmeterTo = "";
	
	/**　引代 */
	private int pfQty = 0;
	
	public int getPfQty() {
		return pfQty;
	}
	public void setPfQty(int pfQty) {
		this.pfQty = pfQty;
	}
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
	

	

	public String getLclsCode() {
		return lclsCode;
	}

	public void setLclsCode(String lclsCode) {
		this.lclsCode = lclsCode;
	}

	public String getMclsCode() {
		return mclsCode;
	}

	public void setMclsCode(String mclsCode) {
		this.mclsCode = mclsCode;
	}

	public String getPdmeterFrom() {
		return pdmeterFrom;
	}

	public void setPdmeterFrom(String pdmeterFrom) {
		this.pdmeterFrom = pdmeterFrom;
	}

	public String getPdmeterTo() {
		return pdmeterTo;
	}

	public void setPdmeterTo(String pdmeterTo) {
		this.pdmeterTo = pdmeterTo;
	}

	public String getAddUser() {
		return addUser;
	}

	public void setAddUser(String addUser) {
		this.addUser = addUser;
	}

	public String getAddPc() {
		return addPc;
	}

	public void setAddPc(String addPc) {
		this.addPc = addPc;
	}

	public int getAddDate() {
		return addDate;
	}

	public void setAddDate(int addDate) {
		this.addDate = addDate;
	}

	public int getAddTime() {
		return addTime;
	}

	public void setAddTime(int addTime) {
		this.addTime = addTime;
	}

	public String getLastupUser() {
		return lastupUser;
	}

	public void setLastupUser(String lastupUser) {
		this.lastupUser = lastupUser;
	}

	public String getLastupPc() {
		return lastupPc;
	}

	public void setLastupPc(String lastupPc) {
		this.lastupPc = lastupPc;
	}

	public int getLastupDate() {
		return lastupDate;
	}

	public void setLastupDate(int lastupDate) {
		this.lastupDate = lastupDate;
	}

	public int getLastupTime() {
		return lastupTime;
	}

	public void setLastupTime(int lastupTime) {
		this.lastupTime = lastupTime;
	}
	
	
	static public boolean equal(MrppfVo dataVo1, MrppfVo dataVo2){
		
		if (dataVo1 == null && dataVo2 == null) return true;
		
		if (dataVo1 == null || dataVo2 == null) return false;		
		
		if(!dataVo1.getLclsCode().trim().equals(dataVo2.getLclsCode().trim()))
			return false;
		
		if(!dataVo1.getMclsCode().trim().equals(dataVo2.getMclsCode().trim()))
			return false;
		
		if(!dataVo1.getPdmeterFrom().trim().equals(dataVo2.getPdmeterFrom().trim()))
			return false;
		
		if(!dataVo1.getPdmeterTo().trim().equals(dataVo2.getPdmeterTo().trim()))
			return false;	
		
		if(dataVo1.getPfQty() != dataVo2.getPfQty())
			return false;	
		
		return true;
	}
	
}
