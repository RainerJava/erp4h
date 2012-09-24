/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：NameConstants.java
*
*     記述				：key is NAMECLS_CODE + NAME_CODE
*     
*     作成日			：2009/12/09   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/
package com.fas.common.constants.dbtable;

import java.util.HashMap;
import java.util.Map;

import com.fas.common.utils.StringUtils;
import com.fas.vo.mname.MNameVo;

/**
 * @author PC13
 *
 */
public class NameConstants {

	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public NameConstants() {
	}

	
	/** */
	protected static Map<String, MNameVo> mapNameVo = new HashMap<String, MNameVo>();
	
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
	public static MNameVo getNameVo(String strKey) {
		return (MNameVo) mapNameVo.get(StringUtils.trimString(strKey));
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param mapData
	 */
	public static void setMapNameVo(Map<String, MNameVo> mapData) {
		mapNameVo = mapData;
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
	public static String getName(String strKey) {
		
		MNameVo nameVo = mapNameVo.get(StringUtils.trimString(strKey));
		
		if (nameVo != null) {
			return nameVo.getNameName();
		} else {
			return "";
		}
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
	public static boolean checkValue(String strKey, String checkField, String checkValue) {
		
		boolean isExist = false;
		MNameVo nameVo = mapNameVo.get(StringUtils.trimString(strKey));
		
		if (nameVo != null) {
			if ("DEFAULT_TYPE".equalsIgnoreCase(checkField)) {
				if (StringUtils.trimString(checkValue).equalsIgnoreCase(nameVo.getDefaultType())) {
					isExist = true;
				}
			}
		} 
		
		return isExist;
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
	public static String getRName(String strKey) {
		
		MNameVo nameVo = mapNameVo.get(StringUtils.trimString(strKey));
		
		if (nameVo != null) {
			return nameVo.getNameRname();
		} else {
			return "";
		}
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
	public static String getPName(String strKey) {
		
		MNameVo nameVo = mapNameVo.get(StringUtils.trimString(strKey));
		
		if (nameVo != null) {
			return nameVo.getNamePname();
		} else {
			return "";
		}
	}
}
