/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：SearchConditionVo.java
*
*     記述				：
*     
*     作成日			：2010/02/15   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/
package com.fas.vo.search;

import com.fas.vo.base.BaseVo;

/**
 * @author PC13
 *
 */
public class SearchConditionVo extends BaseVo {

	/** */
	private static final long serialVersionUID = 1L;

	/** */
	private String indexValue = "";
	/** */
	private String itemValue = "";
	/** */
	private String txtValue1 = "";
	/** */
	private String txtValue2 = "";
	/** */
	private boolean isUsed = false;
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public SearchConditionVo() {
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the indexValue
	 */
	public String getIndexValue() {
		return indexValue;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param indexValue the indexValue to set
	 */
	public void setIndexValue(String indexValue) {
		this.indexValue = indexValue;
	}

	/**
	 * @return the itemValue
	 */
	public String getItemValue() {
		return itemValue;
	}

	/**
	 * @param itemValue the itemValue to set
	 */
	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the txtValue1
	 */
	public String getTxtValue1() {
		return txtValue1;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param txtValue1 the txtValue1 to set
	 */
	public void setTxtValue1(String txtValue1) {
		this.txtValue1 = txtValue1;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the txtValue2
	 */
	public String getTxtValue2() {
		return txtValue2;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param txtValue2 the txtValue2 to set
	 */
	public void setTxtValue2(String txtValue2) {
		this.txtValue2 = txtValue2;
	}

	/**
	 * @return the isUsed
	 */
	public boolean isUsed() {
		return isUsed;
	}

	/**
	 * @param isUsed the isUsed to set
	 */
	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}

	

}
