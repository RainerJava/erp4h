/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：MSupplyVo.java
*
*     記述				：
*     
*     作成日			：2010/03/05
*
*     作成者			：PC14
*
*     備考				：
*
**************************************************************************************/
package com.fas.vo.supply;

import com.fas.vo.base.BaseVo;

/**
 * @author PC14
 *
 */
public class MSupplyVo extends BaseVo {

	/** */
	private static final long serialVersionUID = 1L;
	/** */
	private String custCode = "";
	/** */
	private String custKana = "";
	/** */
	private String custName = "";
	/** */
	private String custRname = "";	
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public MSupplyVo() {
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the custCode
	 */
	public String getCustCode() {
		return custCode;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param custCode the custCode to set
	 */
	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the custKana
	 */
	public String getCustKana() {
		return custKana;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param custKana the custKana to set
	 */
	public void setCustKana(String custKana) {
		this.custKana = custKana;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the custName
	 */
	public String getCustName() {
		return custName;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param custName the custName to set
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the custRname
	 */
	public String getCustRname() {
		return custRname;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param custRname the custRname to set
	 */
	public void setCustRname(String custRname) {
		this.custRname = custRname;
	}

}
