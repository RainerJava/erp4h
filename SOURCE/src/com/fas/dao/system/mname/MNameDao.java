/************************************************************************************
*
*	会社名		： 林兼コンピューター株式会社
*
*	プロジェクト名	： fas
*
*	ファイル名		： MNameDao.java
*
*	記述			：
*
*	作成日		：  2011/09/02  10:18:48 午前
*
*	作成者		： LENOVO-F23A3B72
*
*	備考			：
*
************************************************************************************/

package com.fas.dao.system.mname;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.fas.common.exception.DaoException;
import com.fas.vo.mname.MNameVo;
import com.fas.vo.mnamecls.MNameclsVo;

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
public interface MNameDao {
	
	/**
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public List<MNameVo> getAll() throws DaoException;

	/**
	 * @param nameclsCode
	 * @param nameCode
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public MNameVo getByKey(String nameclsCode, String nameCode) throws DaoException;

	/**
	 * @param dataVo
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public MNameVo getByKey(MNameVo dataVo) throws DaoException;

	/**
	 * @param dataVo
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public boolean insertVo(MNameVo dataVo) throws DaoException;

	/**
	 * @param dataVo
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public boolean updateVo(MNameVo dataVo) throws DaoException;

	/**
	 * @param dataVo
	 * @return
	 * @throws SQLException
	 * @throws DaoException
	 * @description
	 */
	public boolean deleteVo(MNameVo dataVo) throws SQLException, DaoException;

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
	public boolean isDoubleKey(MNameVo dataVo) throws DaoException;
	
	/**
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public MNameVo getLatestCode() throws DaoException;

	/**
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public List<MNameVo> exportEXCEL() throws DaoException;
	
	/**
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public List<MNameclsVo> getListNameclsVo() throws DaoException;

	/**
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public int countNameclsVo() throws DaoException;

	/**
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public Map<String, MNameVo> getMapNameVo() throws DaoException;

	/**
	 * @param dataVo
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public boolean updateSystemNameVo(MNameVo dataVo) throws DaoException;
}

