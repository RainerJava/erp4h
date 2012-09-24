/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：PermissionService.java
*
*     記述				：
*     
*     作成日			：2010/02/09   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/
package com.fas.service.system.permission;

import java.util.Map;

import com.fas.common.exception.BizException;
import com.fas.vo.exectl1.Exectl1Vo;
import com.fas.vo.exectl2.Exectl2Vo;

/**
 * @author PC13
 *
 */
public interface PermissionService {

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
	public Map<String, Exectl1Vo> getMapExect1Vo() throws BizException;
	
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
	public Map<String, Exectl2Vo> getMapExect2Vo() throws BizException;
}
