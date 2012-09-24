/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：SystemConfigService.java
*
*     記述				：
*     
*     作成日			：2010/03/11   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/
package com.fas.service.systemconfig;

import com.fas.common.exception.BizException;
import com.fas.vo.config.SystemConfigVo;

/**
 * @author PC13
 *
 */
public interface SystemConfigService {
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param configVo
	 * @throws BizException
	 */
	public void updateSystemConfig(SystemConfigVo configVo) throws BizException;
}
