/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：SearchTypeObj.java
*
*     記述				：
*     
*     作成日			：2010/02/23   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/
package com.fas.sapp.business;

import com.fas.vo.base.BaseVo;


/**
 * @author PC13
 *
 */
public class SearchTypeObj extends BaseVo {
	
	/** */
	private static final long serialVersionUID = 1L;

	/** */
	public static final int I_BUMON_SEARCH = 0;
	/** */
	public static final int I_TOKUISAKI_SEARCH = 1;
	/** */
	public static final int I_MAKER_SEARCH = 2;
	/** */
	public static final int I_TANTOUSHA_SEARCH = 3;
	/** */
	public static final int I_SHOUHIN_SEARCH = 4;
	/** */
	public static final int I_SHIIRESAKI_SEARCH = 5;
	/** */
	public static final int I_TORIHIKISAKI_SEARCH = 6;
	/** */
	public static final int I_AREA_SEARCH = 7;
	/** */
	public static final int I_SHOUHIN_BUNRUI_SEARCH = 8;
	/** */
	public static final int I_POST_SEARCH = 9;
	
	/** */
	private int searchType = 0;
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public SearchTypeObj() {
		searchType = 0;
	}
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param iValue
	 */
	public SearchTypeObj(int iValue) {
		searchType = iValue;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the searchType
	 */
	public int getSearchType() {
		return searchType;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param searchType the searchType to set
	 */
	public void setSearchType(int searchType) {
		this.searchType = searchType;
	}

}
