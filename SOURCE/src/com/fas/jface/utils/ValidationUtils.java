/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名		：
* 
*     ファイル名		：ValidationUtils.java
*
*     記述			：
*     
*     作成日			：2009/10/05   
*
*     作成者			：PC13
*
*     備考			：
*
**************************************************************************************/

package com.fas.jface.utils;

import java.awt.Component;

import com.fas.jface.MessageBox;
import com.fas.jface.validate.Validation;

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

public class ValidationUtils {

	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public ValidationUtils() {
	}

    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param validation
     * @return
     */
    public static boolean check(Validation validation){
		
    	boolean check= validation.check();

		if (check == false){
		    
			Component componet = validation.getComponent();
		    if(componet != null){
		        componet.requestFocus();
		    }
		    
		    String message = validation.getMessage();
		    if(message!=null && !"".equals(message)){
		        MessageBox.error(componet,message);
		    }
		}
		
		return check;
    }	
}

