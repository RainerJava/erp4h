/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名		：エスイーシー化成
* 
*     ファイル名		：SlpVo.java
*
*     記述			：
*     
*     作成日			：Apr 9, 2010   
*
*     作成者			：Bui Ngoc Viet
*
*     備考			：
*
**************************************************************************************/

package com.fas.vo.slp;

import com.fas.vo.base.BaseVo;

/**
 * <DL>
 *   <DT>クラス記述：</DT>
 * 	<DD></DD>
 * <BR>
 *   <DT>変更歴史：</DT>
 * 	<DD>著作者: Bui Ngoc Viet</DD><BR>
 * 	<DD></DD>
 * </DL>
 */
public class SlpVo extends BaseVo  {
    private static final long serialVersionUID = 1L;
    
    /** */
    private String slpType="";
    
    /** */
    private String slpDate="";
    
    /** */
    private String slpName="";
    
    /** */
    private int slpnew=0;
    
    /** */
    private int slpmin=0;
    
    /** */
    private int slpmax=0;
    
    /** */
    private String addUser="";
    
    /** */
    private String addPc="";
    
    /** */
    private int addDate=0;
    
    /** */
    private int addTime=0;
    
    /** */
    private String lastupUser="";
    
    /** */
    private String lastupPc="";
    
    /** */
    private int lastupDate=0;
    
    /** */
    private int lastupTime = 0;

    /**
    * <DL>
    *   <DT>コンストラクター記述：</DT>
    * 		<DD></DD>
    * <BR>
    * </DL>
    */
    public SlpVo() {
    }
    
    
	/**
	 * @return the slpType
	 */
	public String getSlpType() {
		return slpType;
	}


	/**
	 * @param slpType the slpType to set
	 */
	public void setSlpType(String slpType) {
		this.slpType = slpType;
	}


	/**
	 * @return the slpDate
	 */
	public String getSlpDate() {
		return slpDate;
	}


	/**
	 * @param slpDate the slpDate to set
	 */
	public void setSlpDate(String slpDate) {
		this.slpDate = slpDate;
	}


	/**
	 * @return the slpName
	 */
	public String getSlpName() {
		return slpName;
	}


	/**
	 * @param slpName the slpName to set
	 */
	public void setSlpName(String slpName) {
		this.slpName = slpName;
	}


	/**
	 * @return the slpnew
	 */
	public int getSlpnew() {
		return slpnew;
	}


	/**
	 * @param slpnew the slpnew to set
	 */
	public void setSlpnew(int slpnew) {
		this.slpnew = slpnew;
	}


	/**
	 * @return the slpmin
	 */
	public int getSlpmin() {
		return slpmin;
	}


	/**
	 * @param slpmin the slpmin to set
	 */
	public void setSlpmin(int slpmin) {
		this.slpmin = slpmin;
	}


	/**
	 * @return the slpmax
	 */
	public int getSlpmax() {
		return slpmax;
	}


	/**
	 * @param slpmax the slpmax to set
	 */
	public void setSlpmax(int slpmax) {
		this.slpmax = slpmax;
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
	 * @return the serialVersionUID
	 */
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}


	/*
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @param dataVo1
	* @param dataVo2
	* @return
	* </DL>
	*/
	static public boolean equal(SlpVo dataVo1, SlpVo dataVo2) {
		
		if (dataVo1 == null && dataVo2 == null) return true;
		
		if (dataVo1 == null || dataVo2 == null) return false;		
		
		if(!dataVo1.getSlpType().trim().equalsIgnoreCase(dataVo2.getSlpType().trim()))
			return false;
		
		if(!dataVo1.getSlpDate().trim().equalsIgnoreCase(dataVo2.getSlpDate().trim()))
			return false;
		
		if(!dataVo1.getSlpName().trim().equalsIgnoreCase(dataVo2.getSlpName().trim()))
			return false;
		
		if(dataVo1.getSlpnew() != dataVo2.getSlpnew())
				return false;
		
		if(dataVo1.getSlpmin() != dataVo2.getSlpmin())
			return false;
		
		if(dataVo1.getSlpmax() != dataVo2.getSlpmax())
			return false;
		
		
		return true;
	}

}
