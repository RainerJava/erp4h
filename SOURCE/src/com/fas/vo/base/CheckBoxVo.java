/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：CheckBoxVo.java
*
*     記述				：
*     
*     作成日			：2010/03/03 
*
*     作成者			：PC14
*
*     備考				：
*
**************************************************************************************/
package com.fas.vo.base;

import com.fas.vo.base.BaseVo;


/**
 * @author PC14
 *
 */
public class CheckBoxVo extends BaseVo {

	/** */
	private static final long serialVersionUID = 1L;
    /** */
    private String code;
    /** */
    private String value1;
    /** */
    private String value2;
    /** */
    private String value3;
    
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the value1
	 */
	public String getValue1() {
		return value1;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param value1 the value1 to set
	 */
	public void setValue1(String value1) {
		this.value1 = value1;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the value2
	 */
	public String getValue2() {
		return value2;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param value2 the value2 to set
	 */
	public void setValue2(String value2) {
		this.value2 = value2;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the value3
	 */
	public String getValue3() {
		return value3;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param value3 the value3 to set
	 */
	public void setValue3(String value3) {
		this.value3 = value3;
	}

	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public CheckBoxVo() {
	}

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
//    public boolean equals(Object obj) {
//    	if (obj instanceof CheckBoxVo) {
//    		CheckBoxVo other = (CheckBoxVo) obj;
//    		if (other != null && StringUtils.nullIfEmpty(other.getCode()).trim().equalsIgnoreCase(StringUtils.emptyIfNull(code).trim())) {
//    			return true;
//    		} else {
//    			return false;
//    		}
//    	} if (obj instanceof String) {
//    		if (StringUtils.nullIfEmpty(obj).trim().equalsIgnoreCase(StringUtils.emptyIfNull(code).trim())) {
//    			return true;
//    		} else {
//    			return false;
//    		}
//    	} else {
//    		return false;
//    	}
//    }
}
