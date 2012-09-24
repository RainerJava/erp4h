/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名		：エスイーシー化成
* 
*     ファイル名		：MOutCtlDao.java
*
*     記述			：
*     
*     作成日			：Apr 9, 2010   
*
*     作成者			：Bui Ngoc Viet
*
*     備考			：
*
**************************************************************************************/

package com.fas.dao.system.moutctl;

import java.util.Map;

import com.fas.common.exception.DaoException;
import com.fas.vo.moutctl.MOutCtlVo;
import com.fas.vo.outctl.OutCtlVo;

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
public interface MOutCtlDao {

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @return
	* @throws DaoException
	* </DL>
	*/
	public Map<String, MOutCtlVo> getMapMOutCtlVo() throws DaoException;
	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @param userId
	* @return
	* @throws DaoException
	* </DL>
	*/
	public OutCtlVo getOutCtlVoByUserId(String userId) throws DaoException;
	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @param dataVo
	* @return
	* @throws DaoException
	* </DL>
	*/
	public boolean updateOutCtlVo(OutCtlVo dataVo) throws DaoException;
	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @param dataVo
	* @return
	* @throws DaoException
	* </DL>
	*/
	public boolean insertOutCtlVo(OutCtlVo dataVo) throws DaoException;

}
