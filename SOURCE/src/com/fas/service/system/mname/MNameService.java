/************************************************************************************
*
*	会社名		： 林兼コンピューター株式会社
*
*	プロジェクト名	： fas
*
*	ファイル名		： MNameService.java
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

package com.fas.service.system.mname;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fas.common.exception.BizException;
import com.fas.vo.base.SortObjVo;
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
public interface MNameService {
		
	/**
	 * @return
	 * @throws BizException
	 * @description
	 */
	public List<MNameVo> getAll() throws BizException;

	/**
	 * @param nameclsCode
	 * @param nameCode
	 * @return
	 * @throws BizException
	 * @description
	 */
	public MNameVo getByKey(String nameclsCode, String nameCode) throws BizException;
	
	/**
	 * @param dataVo
	 * @return
	 * @throws BizException
	 * @description
	 */
	public MNameVo getByKey(MNameVo dataVo) throws BizException;

	/**
	 * @param dataVo
	 * @return
	 * @throws BizException
	 * @description
	 */
	public boolean insertVo(MNameVo dataVo) throws BizException;
	
	/**
	 * @param dataVo
	 * @return
	 * @throws BizException
	 * @description
	 */
	public boolean updateVo(MNameVo dataVo) throws BizException;

	/**
	 * @param dataVo
	 * @return
	 * @throws SQLException
	 * @throws BizException
	 * @description
	 */
	public boolean deleteVo(MNameVo dataVo) throws SQLException, BizException;

	/**
	 * @return
	 * @throws BizException
	 * @description
	 */
	public int getVoCount() throws BizException;

	/**
	 * @param dataVo
	 * @return
	 * @throws BizException
	 * @description
	 */
	public boolean isDoubleKey(MNameVo dataVo) throws BizException;

	/**
	 * @return
	 * @throws BizException
	 * @description
	 */
	public MNameVo getLatestCode() throws BizException;

	/**
	 * @param strFilePath
	 * @return
	 * @throws BizException
	 * @description
	 */
	public boolean exportEXCEL(String strFilePath) throws BizException;
	
	/**
	 * @return
	 * @throws BizException
	 * @description
	 */
	public Map<String, MNameVo> getMapNameVo() throws BizException;

	/**
	 * @return
	 * @throws BizException
	 * @description
	 */
	public int countNameclsVo() throws BizException;
	

	/**
	 * @return
	 * @throws BizException
	 * @description
	 */
	public List<MNameclsVo> getListNameclsVo() throws BizException;
	
	/**
	 * @param dataVo
	 * @return
	 * @throws BizException
	 * @description
	 */
	public boolean updateSystemNameVo(MNameVo dataVo) throws BizException;
}
