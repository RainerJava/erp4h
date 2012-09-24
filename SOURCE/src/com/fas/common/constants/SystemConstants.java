/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：SystemConstants.java
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

package com.fas.common.constants;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

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

public class SystemConstants {

	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public SystemConstants() {
	}
	
    /** Stringバイト数取得用エンコード */
    public static final String STRING_GET_BYTES_ENCODE = "Windows-31J";	

	/** システム名	*/
	public static final String SYSTEM_NAME = SystemConstants.getString("SYSTEM_NAME");
	
	/**
	 * メッセージをアプリケーションプロパティから取得する.
	 * @param key キー
	 * @return メッセージ
	 */
	public static String getString(String key) {
		
		try {
			
			String bundleName = "resources.system";
			ResourceBundle rb = ResourceBundle.getBundle(bundleName);			
			
			return rb.getString(key);
			
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}	
}

