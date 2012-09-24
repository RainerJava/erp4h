/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名		：エスイーシー化成
* 
*     ファイル名		：MOutCtlContants.java
*
*     記述			：
*     
*     作成日			：Apr 9, 2010   
*
*     作成者			：NVH
*
*     備考			：
*
**************************************************************************************/

package com.fas.common.constants.dbtable;

import java.util.HashMap;
import java.util.Map;

import com.fas.common.utils.StringUtils;
import com.fas.vo.moutctl.MOutCtlVo;

/**
 * <DL>
 *   <DT>クラス記述：</DT>
 * 	<DD></DD>
 * <BR>
 *   <DT>変更歴史：</DT>
 * 	<DD>著作者: NVH</DD><BR>
 * 	<DD></DD>
 * </DL>
 */
public class MOutCtlContants {
	
	/** */
	protected static Map<String, MOutCtlVo> mapOutCtlVo = new HashMap<String, MOutCtlVo>();
	
	/**Excel出力用 */
	public static final int I_PATH1 = 0;
	/**PDFl出力用 */
	public static final int I_PATH2 = 0;
	/**予備 */
	public static final int I_PATH3 = 0;
	/**予備 */
	public static final int I_PATH4 = 0;
	/**予備 */
	public static final int I_PATH5 = 0;

	
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
	public static MOutCtlVo getMOutCtlVo(String strKey) {
		return (MOutCtlVo) mapOutCtlVo.get(StringUtils.trimString(strKey));
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
	public static void setMapMOutCtlVo(Map<String, MOutCtlVo> mapData) {
		mapOutCtlVo = mapData;
	}
	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @param strKey
	* @param iKindOfPath
	* @return
	* </DL>
	*/
	public static String getValue(String strKey, int iKindOfPath) {
		
		MOutCtlVo mCtlVo = mapOutCtlVo.get(StringUtils.trimString(strKey));
		
		if (mCtlVo != null) {
			if (iKindOfPath == I_PATH1) {
				return StringUtils.trimString(mCtlVo.getOut1Path());
			} else if (iKindOfPath == I_PATH2) {
				return StringUtils.trimString(mCtlVo.getOut2Path());
			} else if (iKindOfPath == I_PATH3) {
				return StringUtils.trimString(mCtlVo.getOut3Path());
			} else if (iKindOfPath == I_PATH4) {
				return StringUtils.trimString(mCtlVo.getOut4Path());
			} else if (iKindOfPath == I_PATH5) {
				return StringUtils.trimString(mCtlVo.getOut5Path());
			} else {
				return "";
			}
 		} else {
			return "";
		}
	}
}
