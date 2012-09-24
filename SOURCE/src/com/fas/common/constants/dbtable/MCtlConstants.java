/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：ConfigConstants.java
*
*     記述				：
*     
*     作成日			：2009/10/07   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/

package com.fas.common.constants.dbtable;

import java.util.HashMap;
import java.util.Map;

import com.fas.common.constants.ApplicationConstants;
import com.fas.common.utils.StringUtils;
import com.fas.vo.mctl.MCtlVo;

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

public class MCtlConstants {
	
	/** */
	protected static Map<String, MCtlVo> mapCtlVo = new HashMap<String, MCtlVo>();
	
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
	public static MCtlVo getMCtlVo(String strKey) {
		return (MCtlVo) mapCtlVo.get(StringUtils.trimString(strKey));
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param cKey
	 * @return
	 */
	public static MCtlVo getMCtlVoByCKey(String cKey) {
		MCtlVo dataVo;
		String strKey = StringUtils.emptyIfNull(ApplicationConstants.LOGIN_USER_ID).trim() + cKey;
		dataVo = (MCtlVo) mapCtlVo.get(StringUtils.trimString(strKey));
		if (dataVo == null) {
			dataVo = (MCtlVo) mapCtlVo.get(StringUtils.trimString("SYSTEM" + cKey));
		}
		return dataVo;
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param cKey
	 * @return
	 */
	public static String getValueByCKey(String cKey) {
		MCtlVo dataVo;
		String strKey = StringUtils.emptyIfNull(ApplicationConstants.LOGIN_USER_ID).trim() + cKey;
		dataVo = (MCtlVo) mapCtlVo.get(StringUtils.trimString(strKey));
		if (dataVo == null) {
			dataVo = (MCtlVo) mapCtlVo.get(StringUtils.trimString("SYSTEM" + cKey));
		}
		if (dataVo != null) {
			return dataVo.getCData();
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
	 * @param mapData
	 */
	public static void setMapMCtlVo(Map<String, MCtlVo> mapData) {
		mapCtlVo = mapData;
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param strKey
	 * @param strValue
	 * @return
	 */
	public static boolean checkValue(String strKey, String strValue) {
		
		boolean check = false;
		MCtlVo mctlVo = MCtlConstants.getMCtlVo(strKey);
		
		if (mctlVo != null && strValue.equalsIgnoreCase(StringUtils.trimString(mctlVo.getCData()))) {
			check = true;
		} else {
			check = false;
		}
		
		return check;
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
	public static String getValue(String strKey) {
		
		MCtlVo mCtlVo = mapCtlVo.get(StringUtils.trimString(strKey));
		
		if (mCtlVo != null) {
			return StringUtils.trimString(mCtlVo.getCData());
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
	public static String getValueName(String strKey) {
		
		MCtlVo mCtlVo = mapCtlVo.get(StringUtils.trimString(strKey));
		
		if (mCtlVo != null) {
			return StringUtils.trimString(mCtlVo.getCName());
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
	public static String getValueWithoutTrim(String strKey) {
		
		MCtlVo ctlVo = mapCtlVo.get(StringUtils.trimString(strKey));
		
		if (ctlVo != null) {
			return ctlVo.getCData() == null ? "" :  ctlVo.getCData();
		} else {
			return "";
		}
	}
}