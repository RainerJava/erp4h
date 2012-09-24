/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：MClrCtl.java
*
*     記述				：
*     
*     作成日			：2010/02/22   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/
package com.fas.vo.color;

import java.awt.Color;

import com.fas.vo.base.BaseVo;

/**
 * @author PC13
 *
 */
public class MClrCtlVo extends BaseVo {

	/** */
	private static final long serialVersionUID = 1L;
	/** */
	private String clrKey = "";
	/** */
	private String userId = "";
	/** */
	private String clrName = "";
	/** */
	private int clrRed = 0;
	/** */
	private int clrGreen = 0;
	/** */
	private int clrBlue = 0;
	/** */
	private int clrAlpha = 0;
	/** */
	private String clrHelp = "";
	/** */
	private String addUser = "";
	/** */
	private String addPc = "";	
	/** */
	private double addDate = 0;
	/** */
	private double addTime = 0;
	/** */
	private String lastupUser = "";	
	/** */
	private String lastupPc = "";		
	/** */
	private double lastupDate = 0;
	/** */
	private double lastupTime = 0;
	/** */
	private Color color;
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public MClrCtlVo() {
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the clrKey
	 */
	public String getClrKey() {
		return clrKey;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param clrKey the clrKey to set
	 */
	public void setClrKey(String clrKey) {
		this.clrKey = clrKey;
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
	 * @return the clrName
	 */
	public String getClrName() {
		return clrName;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param clrName the clrName to set
	 */
	public void setClrName(String clrName) {
		this.clrName = clrName;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the clrRed
	 */
	public int getClrRed() {
		return clrRed;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param clrRed the clrRed to set
	 */
	public void setClrRed(int clrRed) {
		this.clrRed = clrRed;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the clrGreen
	 */
	public int getClrGreen() {
		return clrGreen;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param clrGreen the clrGreen to set
	 */
	public void setClrGreen(int clrGreen) {
		this.clrGreen = clrGreen;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the clrBlue
	 */
	public int getClrBlue() {
		return clrBlue;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param clrBlue the clrBlue to set
	 */
	public void setClrBlue(int clrBlue) {
		this.clrBlue = clrBlue;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the clrAlpha
	 */
	public double getClrAlpha() {
		return clrAlpha;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param clrAlpha the clrAlpha to set
	 */
	public void setClrAlpha(int clrAlpha) {
		this.clrAlpha = clrAlpha;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the clrHelp
	 */
	public String getClrHelp() {
		return clrHelp;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param clrHelp the clrHelp to set
	 */
	public void setClrHelp(String clrHelp) {
		this.clrHelp = clrHelp;
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
	public double getAddDate() {
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
	public void setAddDate(double addDate) {
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
	public double getAddTime() {
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
	public void setAddTime(double addTime) {
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
	public double getLastupDate() {
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
	public void setLastupDate(double lastupDate) {
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
	public double getLastupTime() {
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
	public void setLastupTime(double lastupTime) {
		this.lastupTime = lastupTime;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

}
