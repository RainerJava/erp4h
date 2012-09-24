/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：LogDao.java
*
*     記述				：
*     
*     作成日			：2009/10/19   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/

package com.fas.dao.system.flog;

import java.util.List;

import com.fas.common.exception.DaoException;
import com.fas.vo.base.SortObjVo;
import com.fas.vo.flog.FLogConditionVo;
import com.fas.vo.flog.FLogVo;

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

public interface FLogDao {
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>	 
	 */
	public boolean create(FLogVo logInfo) throws DaoException;	

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>	 
	 */
	public List<FLogVo> getLstLogInfor(SortObjVo sortObj) throws DaoException;
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>	 
	 */
	public List<FLogVo> getLstLogInfor(FLogConditionVo conditionVo) throws DaoException;
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>	 
	 */
	public int countLogInfor(FLogConditionVo conditionVo) throws DaoException;

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>	 
	 */
	public int countVoByUserUser(String strUserUser) throws DaoException ;
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return int
	 * @throws DaoException
	 */
	public List<FLogVo> exportEXCEL(FLogConditionVo conditionVo) throws DaoException;
}

