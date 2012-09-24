/************************************************************************************
*
*	会社名		： 林兼コンピューター株式会社
*
*	プロジェクト名	： fas
*
*	ファイル名		： Pc01Dao.java
*
*	記述			：
*
*	作成日		：  2011/09/26  11:42:07 午前
*
*	作成者		： LENOVO-F23A3B72
*
*	備考			：
*
************************************************************************************/

package com.fas.dao.master.pc01;

import java.sql.SQLException;
import java.util.List;

import com.fas.common.exception.DaoException;
import com.fas.vo.pc01.Pc01Vo;

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
public interface Pc01Dao {
	/**
	 * @param iOrderBy
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public List<Pc01Vo> getAll(int iOrderBy) throws DaoException;

	/**
	 * @param c0101
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public Pc01Vo getByKey(int c0101) throws DaoException;

	/**
	 * @param dataVo
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public Pc01Vo getByKey(Pc01Vo dataVo) throws DaoException;

	/**
	 * @param dataVo
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public boolean insertVo(Pc01Vo dataVo) throws DaoException;

	/**
	 * @param dataVo
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public boolean updateVo(Pc01Vo dataVo) throws DaoException;

	/**
	 * @param dataVo
	 * @return
	 * @throws SQLException
	 * @throws DaoException
	 * @description
	 */
	public boolean deleteVo(Pc01Vo dataVo) throws SQLException, DaoException;

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
	public boolean isDoubleKey(Pc01Vo dataVo) throws DaoException;

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
	public List<Pc01Vo> exportEXCEL() throws DaoException;
}