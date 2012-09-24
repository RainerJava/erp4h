/************************************************************************************
*
*	会社名		： 林兼コンピューター株式会社
*
*	プロジェクト名	： fas
*
*	ファイル名		： MEmpDao.java
*
*	記述			：
*
*	作成日		：  2011/08/24  03:08:14 午後
*
*	作成者		： LENOVO-F23A3B72
*
*	備考			：
*
************************************************************************************/

package com.fas.dao.system.memp;

import java.sql.SQLException;
import java.util.List;

import com.fas.common.exception.DaoException;
import com.fas.vo.memp.MEmpVo;

/**
 * <DL>
 *   <DT>クラス記述：</DT>
 * 	 <DD></DD>
 * <BR>
 *   <DT>変更歴史：</DT>
 * 	 <DD>著作者: LENOVO-F23A3B72 </DD><BR>
 * 	 <DD></DD>
 * </DL>
**/
public interface MEmpDao {
	
	/**
	 * @param iOrderBy
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public List<MEmpVo> getAll(int iOrderBy) throws DaoException;

	/**
	 * @param empCode
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public MEmpVo getByKey(String empCode) throws DaoException;

	/**
	 * @param dataVo
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public MEmpVo getByKey(MEmpVo dataVo) throws DaoException;

	/**
	 * @param dataVo
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public boolean insertVo(MEmpVo dataVo) throws DaoException;
	
	/**
	 * @param dataVo
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public boolean updateVo(MEmpVo dataVo) throws DaoException;

	/**
	 * @param dataVo
	 * @return
	 * @throws SQLException
	 * @throws DaoException
	 * @description
	 */
	public boolean deleteVo(MEmpVo dataVo) throws SQLException, DaoException;

	/**
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public int getVoCount() throws DaoException;

	/**
	 * @param dataVo
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public boolean isDoubleKey(MEmpVo dataVo) throws DaoException;
	
	/**
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public String getLatestCode() throws DaoException;

	/**
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public List<MEmpVo> exportEXCEL() throws DaoException;
	
	/**
	 * @param strEmpUser
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public int getVoCountByUserEmpUser(String strEmpUser) throws DaoException;
}
