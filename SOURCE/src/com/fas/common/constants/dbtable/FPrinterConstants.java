/** *********************************************************************************
 *     
 *     会社名			：林兼コンピューター株式会社
 *
 *     プロジェクト名	：
 * 
 *     ファイル名		：FPrinterConstants.java
 *
 *     記述				：
 *     
 *     作成日			：2010/08/11   
 *
 *     作成者			：NQHung
 *
 *     備考				：
 *
 **************************************************************************************/

package com.fas.common.constants.dbtable;

import java.util.HashMap;
import java.util.Map;

import com.fas.common.utils.StringUtils;
import com.fas.vo.fprinter.FPrinterVo;

/**
 * <DL>
 * <DT>クラス記述：</DT>
 * <DD></DD>
 * <BR>
 * <DT>変更歴史：</DT>
 * <DD>著作者: PC12</DD><BR>
 * <DD></DD>
 * </DL>
 */

public class FPrinterConstants {

	/**
	 * key = USERID + MENU_GRP + MENU_EXE
	 * 
	 * */
	protected static Map<String, FPrinterVo> mapPrinterVo = new HashMap<String, FPrinterVo>();

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param strKey
	 * @return
	 */
	public static FPrinterVo getFPrinterVo(String strKey) {
		return (FPrinterVo) mapPrinterVo.get(StringUtils.trimString(strKey));
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param strKey
	 * @return
	 */
	public static String getPrtidBykey(String strKey) {

		if (!StringUtils.isValid(strKey))
			return "";

		FPrinterVo fPrinterVo = getFPrinterVo(strKey);

		return fPrinterVo == null ? "" : fPrinterVo.getPrtid();
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param userId
	 * @param menuGrp
	 * @param menuExe
	 * @return
	 */
	public static String getPrtidBykey(String userId, String menuGrp, String menuExe) {
		return getPrtidBykey(userId.trim() + menuGrp.trim() + menuExe.trim());
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @return the mapPrinterVo
	 */
	public static Map<String, FPrinterVo> getMapPrinterVo() {
		return mapPrinterVo;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param mapPrinterVo
	 *            the mapPrinterVo to set
	 */
	public static void setMapPrinterVo(Map<String, FPrinterVo> mapPrinterVo) {
		FPrinterConstants.mapPrinterVo = mapPrinterVo;
	}

}