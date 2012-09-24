/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：HolidayVo.java
*
*     記述				：
*     
*     作成日			：2009/11/11   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/

package com.fas.vo.holiday;

import com.fas.vo.base.BaseVo;

/**
 * <DL>
 *   <DT>クラス記述：</DT>
 * 	<DD></DD>
 * <BR>
 *   <DT>変更歴史：</DT>
 * 	<DD>著作者: PC13</DD><BR>
 * 	<DD></DD>
 * </DL>
 */

public class HolidayVo extends BaseVo {

	/** */
	private static final long serialVersionUID = 1L;
	/** */
	private String shujitsubi = "";
	/** */
	private String shujitsumei = "";
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public HolidayVo() {
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the shujitsubi
	 */
	public String getShujitsubi() {
		return shujitsubi;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the shujitsumei
	 */
	public String getShujitsumei() {
		return shujitsumei;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param shujitsubi the shujitsubi to set
	 */
	public void setShujitsubi(String shujitsubi) {
		this.shujitsubi = shujitsubi;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param shujitsumei the shujitsumei to set
	 */
	public void setShujitsumei(String shujitsumei) {
		this.shujitsumei = shujitsumei;
	}
	
}

