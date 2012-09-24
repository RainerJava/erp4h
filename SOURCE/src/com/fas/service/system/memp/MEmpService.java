/************************************************************************************
*
*	会社名		： 林兼コンピューター株式会社
*
*	プロジェクト名	： fas
*
*	ファイル名		： MEmpService.java
*
*	記述			：
*
*	作成日		：  2011/08/24  03:01:43 午後
*
*	作成者		： LENOVO-F23A3B72
*
*	備考			：
*
************************************************************************************/

package com.fas.service.system.memp;

import java.sql.SQLException;
import java.util.List;

import com.fas.common.exception.BizException;
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
public interface MEmpService {
	
	/**
	 * @param iOrderBy
	 * @return
	 * @throws BizException
	 * @description
	 */
	public List<MEmpVo> getAll(int iOrderBy) throws BizException;
	
	/**
	 * @param empCode
	 * @return
	 * @throws BizException
	 * @description
	 */
	public MEmpVo getByKey(String empCode) throws BizException;
	
	/**
	 * @param dataVo
	 * @return
	 * @throws BizException
	 * @description
	 */
	public MEmpVo getByKey(MEmpVo dataVo) throws BizException;
	
	/**
	 * @param dataVo
	 * @return
	 * @throws BizException
	 * @description
	 */
	public boolean insertVo(MEmpVo dataVo) throws BizException;
	
	/**
	 * @param dataVo
	 * @return
	 * @throws BizException
	 * @description
	 */
	public boolean updateVo(MEmpVo dataVo) throws BizException;

	/**
	 * @param dataVo
	 * @return
	 * @throws SQLException
	 * @throws BizException
	 * @description
	 */
	public boolean deleteVo(MEmpVo dataVo) throws SQLException, BizException;

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
	public boolean isDoubleKey(MEmpVo dataVo) throws BizException;
	
	/**
	 * @return
	 * @throws BizException
	 * @description
	 */
	public String getLatestCode() throws BizException;
	
	/**
	 * @param strFilePath
	 * @return
	 * @throws BizException
	 * @description
	 */
	public boolean exportEXCEL(String strFilePath) throws BizException;
	
	/**
	 * @param strEmpUser
	 * @return
	 * @throws BizException
	 * @description
	 */
	public int getVoCountByUserEmpUser(String strEmpUser) throws BizException;

}
