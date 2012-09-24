/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		： FrameConstants.java
*
*     記述				：key is MenugrpCode + MenuexeCode
*     
*     作成日			：2010/06/25   
*
*     作成者			：PC14
*
*     備考			：
*
**************************************************************************************/
package com.fas.common.constants;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fas.common.utils.StringUtils;

/**
 * @author PC14
 *
 */
public class FrameConstants {

	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public FrameConstants() {
	}

	
	/** */
	protected static Map<String, String> mapNameFrame = new LinkedHashMap<String, String>();
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param strKey
	 * @return
	 */
	public static String getNameFrame(String strKey) {
		return (String) mapNameFrame.get(StringUtils.trimString(strKey));
	}
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param strKey
	 * @return
	 */
	public static Map<String, String> getMap() {
		return mapNameFrame;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param strKey
	 * @return
	 */
	public static String removeNameFrame(String strKey) {
		return (String) mapNameFrame.remove(StringUtils.trimString(strKey));
	}
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param strKey, nameFrame
	 * @return
	 */
	public static void addNameFrame(String strKey, String nameFrame) {
		mapNameFrame.put(strKey, nameFrame);	
	}
}
