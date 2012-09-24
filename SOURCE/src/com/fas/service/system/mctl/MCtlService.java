/************************************************************************************
*
*	会社名		： 林兼コンピューター株式会社
*
*	プロジェクト名	： fas
*
*	ファイル名		： MCtlService.java
*
*	記述			：
*
*	作成日		：  2011/08/31  09:35:12 午前
*
*	作成者		： LENOVO-F23A3B72
*
*	備考			：
*
************************************************************************************/

package com.fas.service.system.mctl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.fas.common.exception.BizException;
import com.fas.vo.mctl.MCtlVo;

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
public interface MCtlService {
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return all table data
	* @throws BizException
	*/
	public List<MCtlVo> getAll(int iOrderBy) throws BizException;
	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>	
	* @param String userid, String cKey
	* @return MCtlVo if object exist with key else return NULL
	* @throws BizException
	*/
	public MCtlVo getByKey(String userid, String cKey) throws BizException;
		
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param dataVo
	* @return TRUE if insert susscessful else return FALSE
	* @throws BizException
	*/
	public boolean insertVo(MCtlVo dataVo) throws BizException;
	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param dataVo
	* @return TRUE if update susscessful else return FALSE
	* @throws BizException
	*/
	public boolean updateVo(MCtlVo dataVo) throws BizException;

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param dataVo
	* @return TRUE if delete susscessful else return FALSE
	* @throws BizException
	*/
	public boolean deleteVo(MCtlVo dataVo) throws SQLException, BizException;

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return count of MCtlVo
	* @throws BizException
	*/
	public int getVoCount() throws BizException;

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param dataVo
	* @return boolean
	* @throws BizException
	*/
	public boolean isDoubleKey(MCtlVo dataVo) throws BizException;

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return String
	* @throws BizException
	*/
	public String getLatestCode() throws BizException;

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param MCtlListVo , String
	* @return boolean
	* @throws BizException
	*/
	public boolean exportEXCEL(String strFilePath) throws BizException;
	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>	
	* @param String userid, String cKey, int iMtnFlg
	* @return MCtlVo if object exist with key else return NULL
	* @throws BizException
	*/
	public MCtlVo getByKey(String userid, String cKey, int iMtnFlg) throws BizException;	

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>	
	* @param String userid, int iMtnFlg
	* @return List MCtlVo if exist with key else return list empty
	* @throws BizException
	*/
	public List<MCtlVo> getListVo(String strUserid, int iMtnFlg) throws BizException;
	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>	
	* @param String userid, String strCKey, int iMtnFlg
	* @return List MCtlVo if exist with key else return list empty
	* @throws BizException
	*/
	public List<MCtlVo> getListVo(String strUserid, String strCKey, int iMtnFlg) throws BizException ;
	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>	
	* @param String userid, String strCKey, int iMtnFlg, int iCntFlg
	* @return List MCtlVo if exist with key else return list empty
	* @throws BizException
	*/
	public List<MCtlVo> getListVo(String strUserid, String strCKey, int iMtnFlg, int iCntFlg) throws BizException;

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return
	 * @throws BizException
	 */
	public Map<String, MCtlVo> getMapMCtlVo() throws BizException;

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return
	 * @throws BizException
	 */
	public boolean fillDataFromUseridToUserid(String strFromUserid, String strToUserid, int iMtnFlg) throws BizException ;

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return
	 * @throws BizException
	 */
	public boolean deleteDataByUserid(String strUserid, int iMtnFlg) throws BizException ;
}
