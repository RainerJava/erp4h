/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：ColorContants.java
*
*     記述				：
*     
*     作成日			：2009/12/24   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/
package com.fas.common.constants.dbtable;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import com.fas.common.utils.StringUtils;
import com.fas.vo.color.MClrCtlVo;


/**
 * @author PC13
 *
 */
public class DBColorContants {
	
	/** */
	protected static Map<String, MClrCtlVo> mapMClrCtlVo = new HashMap<String, MClrCtlVo>();
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public DBColorContants() {
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
	public static void setMapMClrCtlVo(Map<String, MClrCtlVo> mapData) {
		mapMClrCtlVo = mapData;
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
	public static MClrCtlVo getMClrCtlVo(String strKey) {
		return (MClrCtlVo) mapMClrCtlVo.get(StringUtils.trimString(strKey));
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
	public static Color getMColor(String strKey) {
		
		MClrCtlVo dataVo = (MClrCtlVo) mapMClrCtlVo.get(StringUtils.trimString(strKey));
		
		if (dataVo != null) {
			Color color = new Color(dataVo.getClrRed(), dataVo.getClrGreen(), dataVo.getClrBlue());
			return color;
		} else {			
			return null;
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
	 * @param userId
	 * @return
	 */
	public static Color getMColor(String strKey, String userId) {
		
		MClrCtlVo dataVo = (MClrCtlVo) mapMClrCtlVo.get(StringUtils.trimString(strKey + userId));
		
		if (dataVo != null) {
			Color color = new Color(dataVo.getClrRed(), dataVo.getClrGreen(), dataVo.getClrBlue());
			return color;
		} else {
			dataVo = (MClrCtlVo) mapMClrCtlVo.get(StringUtils.trimString(strKey + "SYSTEM"));
			
			if (dataVo != null) {
				Color color = new Color(dataVo.getClrRed(), dataVo.getClrGreen(), dataVo.getClrBlue());
				return color;
			}
			
			return null;
		}
	}

}
