/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：InitService.java
*
*     記述				：
*     
*     作成日			：2009/12/04   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/
package com.fas.service.init;

import com.fas.common.exception.BizException;

/**
 * @author PC13
 *
 */
public interface InitService {
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @throws BizException
	 */
	public void init() throws BizException;
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @throws BizException
	 */
	public void initAffterLogin() throws BizException;
}
