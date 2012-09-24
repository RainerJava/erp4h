/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名		：
* 
*     ファイル名		：SYSwariaiVo.java
*
*     記述			：
*     
*     作成日			：2010/08/26
*
*     作成者			：phuongnt
*
*     備考			：
*
**************************************************************************************/

package com.fas.vo.mincr;

import com.fas.vo.mincr.MIncrVo;



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
public class MIncrVo {

	/** 増加率区分 - ICRATE_TYPE*/
	private String icrateType = "";
	
	private String icrateName = "";
	
	public String getIcrateName() {
		return icrateName;
	}

	public void setIcrateName(String icrateName) {
		this.icrateName = icrateName;
	}

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

	public String getMaterName() {
		return materName;
	}

	public void setMaterName(String materName) {
		this.materName = materName;
	}


	/** 大分類 - LCLS_CODE */
	private String lclsCode = "";
	
	private String lclsName = "";
	
	/** 大分類 - MCLS_CODE */	
	private String mclsCode = "";
	
	private String mclsName = "";
	
	/** 材質 - MATER_CODE */
	
	private String materCode = "";
	private String materName = "";
	
	/** 呼径コード(From) */
	
	private String pdmeterFrom = "";
	
	/** 呼径コード(To) */
	
	private String pdmeterTo = "";
	
	
	/** 長さ（From) */
	private int lenFrQty = 0;
	
	/** 長さ（To) */
	
	private int lenToQty = 0;
	
	/** 換算本数 */
	
	private double convQty = 0;
	
	
	/** 増加率   */
	
	private double rateQty = 0;
	
	
	/** 小数処理   */
	private String decPlcCode = "";
	
	
	public String getDecPlc() {
		return decPlcCode;
	}

	public void setDecPlc(String decPlc) {
		this.decPlcCode = decPlc;
	}
	
	/** 小数処理名   */
	private String decPlcName = "";


	public String getDecPlcName() {
		return decPlcName;
	}

	public void setDecPlcName(String decPlcName) {
		this.decPlcName = decPlcName;
	}


	/** 端数処理区分   */	
	private String frcType = "";
	
	/**/
	
	private String frcName = "";
	
	public String getFrcName() {
		return frcName;
	}

	public void setFrcName(String frcName) {
		this.frcName = frcName;
	}


	/** USE_FLG */
	private String useFlg = "";
	
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
	

	public String getIcrateType() {
		return icrateType;
	}

	public void setIcrateType(String icrateType) {
		this.icrateType = icrateType;
	}

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

	public String getMaterCode() {
		return materCode;
	}

	public void setMaterCode(String materCode) {
		this.materCode = materCode;
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

	public int getLenFrQty() {
		return lenFrQty;
	}

	public void setLenFrQty(int lenFrQty) {
		this.lenFrQty = lenFrQty;
	}

	public int getLenToQty() {
		return lenToQty;
	}

	public void setLenToQty(int lenToQty) {
		this.lenToQty = lenToQty;
	}

	public double getConvQty() {
		return convQty;
	}

	public void setConvQty(double convQty) {
		this.convQty = convQty;
	}

	public double getRateQty() {
		return rateQty;
	}

	public void setRateQty(double rateQty) {
		this.rateQty = rateQty;
	}

	public String getFrcType() {
		return frcType;
	}

	public void setFrcType(String frcType) {
		this.frcType = frcType;
	}
	
	public String getUseFlg() {
		return useFlg;
	}

	public void setUseFlg(String useFlg) {
		this.useFlg = useFlg;
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
	
	
	static public boolean equal(MIncrVo dataVo1, MIncrVo dataVo2){
		
		if (dataVo1 == null && dataVo2 == null) return true;
		
		if (dataVo1 == null || dataVo2 == null) return false;
		
		
		
		if(!dataVo1.getIcrateType().trim().equals(dataVo2.getIcrateType().trim()))
			return false;
		
		if(!dataVo1.getLclsCode().trim().equals(dataVo2.getLclsCode().trim()))
			return false;
		
		if(!dataVo1.getMclsCode().trim().equals(dataVo2.getMclsCode().trim()))
			return false;
		
		if(!dataVo1.getMaterCode().trim().equals(dataVo2.getMaterCode().trim()))
			return false;
		
		if(!dataVo1.getPdmeterFrom().trim().equals(dataVo2.getPdmeterFrom().trim()))
			return false;
		
		if(!dataVo1.getPdmeterTo().trim().equals(dataVo2.getPdmeterTo().trim()))
			return false;		
		
		if(dataVo1.getLenFrQty() != dataVo2.getLenFrQty())
			return false;	
		
		if(dataVo1.getLenToQty() != dataVo2.getLenToQty())
			return false;		
		
		if(dataVo1.getConvQty() != dataVo2.getConvQty())
			return false;		
		
		if(dataVo1.getRateQty() != dataVo2.getRateQty())
			return false;	
		
		if(!dataVo1.getDecPlc().trim().equals(dataVo2.getDecPlc().trim()))
			return false;	
		
		if(!dataVo1.getFrcType().trim().equals(dataVo2.getFrcType()))
			return false;			
		
		return true;
	}
	
}
