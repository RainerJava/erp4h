/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：SystemConfigVo.java
*
*     記述				：
*     
*     作成日			：2010/03/10   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/
package com.fas.vo.config;

import com.fas.vo.base.BaseVo;
import com.fas.vo.color.MClrCtlVo;

/**
 * @author PC13
 *
 */
public class SystemConfigVo extends BaseVo {

	/** */
	private static final long serialVersionUID = 1L;

	/**基本設定 */
	/** */
	private String shaMei = "";
	/** */
	private String jidouSaiban = "";
	/** */
	private String jidouSaibanJuchuu = "";
	/** */
	private String kikanFrom = "";
	/** */
	private String kikanTo = "";
	/** */
	private String hinmei = "";
	/** */
	private String tokuisaki = "";
	/** */
	private String shiten = "";
	/** */
	private String keishou1 = "";
	/** */
	private String keishou2 = "";
	/** */
	private String keishou3 = "";
	/** */
	private String keishou4 = "";
	/** */
	private String keishou5 = "";
	
	/**権限設定のため */
	/** */
	private String yuzaKengen = "";
	/** */
	private String loguonJouhou = "";
	/** */
	private String password = "";
	/** */
	private String yuzaSentaku = "";
	
	/**表示設定のため */
	/** */
	private MClrCtlVo clrVo; 
	/** */
	private int iAction = 0;
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public SystemConfigVo() {
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the shaMei
	 */
	public String getShaMei() {
		return shaMei;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param shaMei the shaMei to set
	 */
	public void setShaMei(String shaMei) {
		this.shaMei = shaMei;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the jidouSaiban
	 */
	public String getJidouSaiban() {
		return jidouSaiban;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param jidouSaiban the jidouSaiban to set
	 */
	public void setJidouSaiban(String jidouSaiban) {
		this.jidouSaiban = jidouSaiban;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the jidouSaibanJuchuu
	 */
	public String getJidouSaibanJuchuu() {
		return jidouSaibanJuchuu;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param jidouSaibanJuchuu the jidouSaibanJuchuu to set
	 */
	public void setJidouSaibanJuchuu(String jidouSaibanJuchuu) {
		this.jidouSaibanJuchuu = jidouSaibanJuchuu;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the kikanFrom
	 */
	public String getKikanFrom() {
		return kikanFrom;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param kikanFrom the kikanFrom to set
	 */
	public void setKikanFrom(String kikanFrom) {
		this.kikanFrom = kikanFrom;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the kikanTo
	 */
	public String getKikanTo() {
		return kikanTo;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param kikanTo the kikanTo to set
	 */
	public void setKikanTo(String kikanTo) {
		this.kikanTo = kikanTo;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the hinmei
	 */
	public String getHinmei() {
		return hinmei;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param hinmei the hinmei to set
	 */
	public void setHinmei(String hinmei) {
		this.hinmei = hinmei;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the tokuisaki
	 */
	public String getTokuisaki() {
		return tokuisaki;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param tokuisaki the tokuisaki to set
	 */
	public void setTokuisaki(String tokuisaki) {
		this.tokuisaki = tokuisaki;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the shiten
	 */
	public String getShiten() {
		return shiten;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param shiten the shiten to set
	 */
	public void setShiten(String shiten) {
		this.shiten = shiten;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the keishou1
	 */
	public String getKeishou1() {
		return keishou1;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param keishou1 the keishou1 to set
	 */
	public void setKeishou1(String keishou1) {
		this.keishou1 = keishou1;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the keishou2
	 */
	public String getKeishou2() {
		return keishou2;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param keishou2 the keishou2 to set
	 */
	public void setKeishou2(String keishou2) {
		this.keishou2 = keishou2;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the keishou3
	 */
	public String getKeishou3() {
		return keishou3;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param keishou3 the keishou3 to set
	 */
	public void setKeishou3(String keishou3) {
		this.keishou3 = keishou3;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the keishou4
	 */
	public String getKeishou4() {
		return keishou4;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param keishou4 the keishou4 to set
	 */
	public void setKeishou4(String keishou4) {
		this.keishou4 = keishou4;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the keishou5
	 */
	public String getKeishou5() {
		return keishou5;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param keishou5 the keishou5 to set
	 */
	public void setKeishou5(String keishou5) {
		this.keishou5 = keishou5;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the yuzaKengen
	 */
	public String getYuzaKengen() {
		return yuzaKengen;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param yuzaKengen the yuzaKengen to set
	 */
	public void setYuzaKengen(String yuzaKengen) {
		this.yuzaKengen = yuzaKengen;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the loguonJouhou
	 */
	public String getLoguonJouhou() {
		return loguonJouhou;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param loguonJouhou the loguonJouhou to set
	 */
	public void setLoguonJouhou(String loguonJouhou) {
		this.loguonJouhou = loguonJouhou;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the yuzaSentaku
	 */
	public String getYuzaSentaku() {
		return yuzaSentaku;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param yuzaSentaku the yuzaSentaku to set
	 */
	public void setYuzaSentaku(String yuzaSentaku) {
		this.yuzaSentaku = yuzaSentaku;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the iAction
	 */
	public int getIAction() {
		return iAction;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param action the iAction to set
	 */
	public void setIAction(int action) {
		iAction = action;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the clrVo
	 */
	public MClrCtlVo getClrVo() {
		return clrVo;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param clrVo the clrVo to set
	 */
	public void setClrVo(MClrCtlVo clrVo) {
		this.clrVo = clrVo;
	}
}
