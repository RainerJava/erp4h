/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：SortObjVo.java
*
*     記述				：
*     
*     作成日			：2010/02/02   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/
package com.fas.vo.base;

import com.fas.vo.base.BaseVo;

/**
 * @author PC13
 *
 */
public class SortObjVo extends BaseVo {

	/** */
	private static final long serialVersionUID = 1L;
	/** */
	private int m_sortCol = 0;
	/** */
	private boolean m_sortAsc = true;
	/** */
	private boolean isInit = true;
	/** */
	private String strCmbKey = "";
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public SortObjVo() {
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the m_sortCol
	 */
	public int getM_sortCol() {
		return m_sortCol;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param col the m_sortCol to set
	 */
	public void setM_sortCol(int col) {
		m_sortCol = col;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the m_sortAsc
	 */
	public boolean isM_sortAsc() {
		return m_sortAsc;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param asc the m_sortAsc to set
	 */
	public void setM_sortAsc(boolean asc) {
		m_sortAsc = asc;
	}

	/**
	 * @return the isInit
	 */
	public boolean isInit() {
		return isInit;
	}

	/**
	 * @param isInit the isInit to set
	 */
	public void setInit(boolean isInit) {
		this.isInit = isInit;
	}

	/**
	 * @return the strCmbKey
	 */
	public String getStrCmbKey() {
		return strCmbKey;
	}

	/**
	 * @param strCmbKey the strCmbKey to set
	 */
	public void setStrCmbKey(String strCmbKey) {
		this.strCmbKey = strCmbKey;
	}

}
