/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名		：エスイーシー化成
* 
*     ファイル名		：MOutCtlService.java
*
*     記述			：
*     
*     作成日			：Apr 9, 2010   
*
*     作成者			：NVH
*
*     備考			：
*
**************************************************************************************/

package com.fas.service.system.moutctl;

import java.util.Map;

import com.fas.common.exception.BizException;
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
public interface MOutCtlService {
	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @return
	* @throws BizException
	* </DL>
	*/
	public Map<String, MOutCtlVo> getMapMOutCtlVo() throws BizException;
	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @param userId
	* @return
	* @throws BizException
	* </DL>
	*/
	public OutCtlVo getOutCtlVoByUserId(String userId) throws BizException;
	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @param dataVo
	* @return
	* @throws BizException
	* </DL>
	*/
	public boolean updateOutCtlVo(OutCtlVo dataVo) throws BizException;
	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @param dataVo
	* @return
	* @throws BizException
	* </DL>
	*/
	public boolean insertOutCtlVo(OutCtlVo dataVo) throws BizException;
}
