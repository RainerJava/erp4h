/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：MColorVo.java
*
*     記述				：
*     
*     作成日			：2010/03/24
*
*     作成者			：PC12
*
*     備考				：
*
**************************************************************************************/
package com.fas.vo.color;

import java.awt.Color;

import com.fas.vo.base.BaseVo;

/**
 * @author PC12
 *
 */
public class MColorVo extends BaseVo {

	/** */
	private static final long serialVersionUID = 1L;
	
	/** カラー種別コード */
	private String colorclsCode = "";

	/** カラーコード */
	private String colorCode = "";

	/** カラー名称 */
	private String colorName = "";

	/** カラー略称 */
	private String colorRname = "";

	/** デフォルトフラグ */
	private String defaultType = "";

	/** 表示フラグ */
	private String dspFlg = "";

	/** 表示順序 */
	private int dsporderNo = 0;

	/** カラー分類 */
	private String colortype = "";

	/** RED */
	private int bclrRed = 0;

	/** GREEN */
	private int bclrGreen = 0;

	/** BLUE */
	private int bclrBlue = 0;

	/** RED */
	private int fclrRed = 0;

	/** GREEN */
	private int fclrGreen = 0;

	/** BLUE */
	private int fclrBlue = 0;

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

	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public MColorVo() {
	}

	/**
	 * @return the colorclsCode
	 */
	public String getColorclsCode() {
		return colorclsCode;
	}



	/**
	 * @param colorclsCode the colorclsCode to set
	 */
	public void setColorclsCode(String colorclsCode) {
		this.colorclsCode = colorclsCode;
	}



	/**
	 * @return the colorCode
	 */
	public String getColorCode() {
		return colorCode;
	}



	/**
	 * @param colorCode the colorCode to set
	 */
	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}



	/**
	 * @return the colorName
	 */
	public String getColorName() {
		return colorName;
	}



	/**
	 * @param colorName the colorName to set
	 */
	public void setColorName(String colorName) {
		this.colorName = colorName;
	}



	/**
	 * @return the colorRname
	 */
	public String getColorRname() {
		return colorRname;
	}



	/**
	 * @param colorRname the colorRname to set
	 */
	public void setColorRname(String colorRname) {
		this.colorRname = colorRname;
	}



	/**
	 * @return the defaultType
	 */
	public String getDefaultType() {
		return defaultType;
	}



	/**
	 * @param defaultType the defaultType to set
	 */
	public void setDefaultType(String defaultType) {
		this.defaultType = defaultType;
	}



	/**
	 * @return the dspFlg
	 */
	public String getDspFlg() {
		return dspFlg;
	}



	/**
	 * @param dspFlg the dspFlg to set
	 */
	public void setDspFlg(String dspFlg) {
		this.dspFlg = dspFlg;
	}



	/**
	 * @return the dsporderNo
	 */
	public int getDsporderNo() {
		return dsporderNo;
	}



	/**
	 * @param dsporderNo the dsporderNo to set
	 */
	public void setDsporderNo(int dsporderNo) {
		this.dsporderNo = dsporderNo;
	}



	/**
	 * @return the colortype
	 */
	public String getColortype() {
		return colortype;
	}



	/**
	 * @param colortype the colortype to set
	 */
	public void setColortype(String colortype) {
		this.colortype = colortype;
	}



	/**
	 * @return the bclrRed
	 */
	public int getBclrRed() {
		return bclrRed;
	}



	/**
	 * @param bclrRed the bclrRed to set
	 */
	public void setBclrRed(int bclrRed) {
		this.bclrRed = bclrRed;
	}



	/**
	 * @return the bclrGreen
	 */
	public int getBclrGreen() {
		return bclrGreen;
	}



	/**
	 * @param bclrGreen the bclrGreen to set
	 */
	public void setBclrGreen(int bclrGreen) {
		this.bclrGreen = bclrGreen;
	}



	/**
	 * @return the bclrBlue
	 */
	public int getBclrBlue() {
		return bclrBlue;
	}



	/**
	 * @param bclrBlue the bclrBlue to set
	 */
	public void setBclrBlue(int bclrBlue) {
		this.bclrBlue = bclrBlue;
	}



	/**
	 * @return the fclrRed
	 */
	public int getFclrRed() {
		return fclrRed;
	}



	/**
	 * @param fclrRed the fclrRed to set
	 */
	public void setFclrRed(int fclrRed) {
		this.fclrRed = fclrRed;
	}



	/**
	 * @return the fclrGreen
	 */
	public int getFclrGreen() {
		return fclrGreen;
	}



	/**
	 * @param fclrGreen the fclrGreen to set
	 */
	public void setFclrGreen(int fclrGreen) {
		this.fclrGreen = fclrGreen;
	}



	/**
	 * @return the fclrBlue
	 */
	public int getFclrBlue() {
		return fclrBlue;
	}



	/**
	 * @param fclrBlue the fclrBlue to set
	 */
	public void setFclrBlue(int fclrBlue) {
		this.fclrBlue = fclrBlue;
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
	 * @return
	 */
	public Color getBackgroundColor() {
		return new Color(bclrRed, bclrGreen, bclrBlue);
	}
	
	
	
	/**
	 * @return
	 */
	public Color getForegroundColor() {
		return new Color(fclrRed, fclrGreen, fclrBlue);
	}

}
