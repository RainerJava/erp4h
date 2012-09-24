/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：MsgDao.java
*
*     記述				：
*     
*     作成日			：2009/12/07   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/
package com.fas.dao.common.msg;

import java.util.Map;

import com.fas.common.exception.DaoException;
import com.fas.vo.msg.MstMsgVo;

/**
 * @author PC13
 *
 */
public interface MsgDao {
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return
	 */
	public Map<String, MstMsgVo> getMapMsg() throws DaoException;
}
