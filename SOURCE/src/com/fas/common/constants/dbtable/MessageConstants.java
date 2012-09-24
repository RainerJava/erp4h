/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：MessageConstants.java
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

import com.fas.common.utils.StringUtils;
import com.fas.vo.msg.MstMsgVo;

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

public class MessageConstants {

	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public MessageConstants() {
	}

	/** */
	protected static Map<String, MstMsgVo> mapMsg = new HashMap<String, MstMsgVo>();
	/** */
	public	final static String	SYS_ERR_MSG	=	"システムエラーが発生しました。";
	/** */
	public	final static String	SYS_ERR_MSG_DATABASE_INFO	=	"データベース情報が間違います。";
	/** */
	public	final static String	SYS_ERR_MSG_XML_SQL_READ	=	"SQL取得の時にエラーが発生しました。";
	/** */
	public	final static String	SYS_ERR_MSG_PARAMETER	=	"パラメタは間違います。";
	
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
	public static MstMsgVo getMstMsgVo(String strKey) {
		return mapMsg.get(StringUtils.trimString(strKey));
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
	public static void setMapMMsgVo(Map<String, MstMsgVo> mapData) {
		mapMsg = mapData;
	}
	
}

