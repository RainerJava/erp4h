/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：RequiredImpl.java
*
*     記述				：
*     
*     作成日			：2009/10/05   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/

package com.fas.jface.utils;

import com.fas.jface.validate.Validate;

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

public class RequiredImpl implements Validate {

	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public RequiredImpl() {
	}

    /** */
    private String message="必須入力";
    

	/* (non-Javadoc)
	 * @see com.linc.jface.base.validate.Validate#execute(java.lang.Object, java.lang.Object[])
	 */
	public boolean execute(Object value,Object[] args) {
	      if(value==null){
	          return false;
	      }
	      if("".equals(value.toString())){
	          return false;
	      }
	      return true;
	}
	
	/* (non-Javadoc)
	 * @see com.linc.jface.base.validate.Validate#getMessage()
	 */
	public String getMessage() {
	      return message;
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param message
	 */
	public void setMessage(String message){
	      this.message=message;
	}	
}

