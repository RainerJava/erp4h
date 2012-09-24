/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：中野工業殿
* 
*     ファイル名		：FPrinterVo.java
*
*     記述				：
*     
*     作成日			：2010/08/11   
*
*     作成者			：NQHung
*
*     備考				：
*
**************************************************************************************/

package com.fas.vo.fprinter;

import com.fas.vo.base.BaseVo;

/**
 * <DL>
 *   <DT>クラス記述：</DT>
 * 	<DD></DD>
 * <BR>
 *   <DT>変更歴史：</DT>
 * 	<DD>著作者: KIKAKU7</DD><BR>
 * 	<DD></DD>
 * </DL>
 */

public class FPrinterVo extends BaseVo {
	
	/** */
	private static final long serialVersionUID = 1L;

	/** ユーザー */
	private String userid = "";

	/** メニューコード */
	private String menuCode = "";

	/** メニューサブコード */
	private String menusCode = "";

	/** 出力コントロール */
	private String outctl = "";
	
	/** PRINTER ID */
	private String prtid = "";

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
		return userid;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param userid the userid to set
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
	 * @return the menuCode
	 */
	public String getMenuCode() {
		return menuCode;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param menuCode the menuCode to set
	 */
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the menusCode
	 */
	public String getMenusCode() {
		return menusCode;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param menusCode the menusCode to set
	 */
	public void setMenusCode(String menusCode) {
		this.menusCode = menusCode;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the outctl
	 */
	public String getOutctl() {
		return outctl;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param outctl the outctl to set
	 */
	public void setOutctl(String outctl) {
		this.outctl = outctl;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the prtid
	 */
	public String getPrtid() {
		return prtid;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param prtid the prtid to set
	 */
	public void setPrtid(String prtid) {
		this.prtid = prtid;
	}


}

