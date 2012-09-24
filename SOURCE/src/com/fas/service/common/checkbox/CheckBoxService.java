/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：CheckBoxService.java
*
*     記述				：
*     
*     作成日			：2010/03/03   
*
*     作成者			：PC14
*
*     備考				：
*
**************************************************************************************/
package com.fas.service.common.checkbox;

import java.util.List;

import com.fas.common.exception.BizException;
import com.fas.vo.base.CheckBoxVo;

/**
 * @author PC14
 *
 */
public interface CheckBoxService {

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
	public List<CheckBoxVo> getLstINSP(String isppCode) throws BizException;
	
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
	public List<CheckBoxVo> getLstFRE() throws BizException;
	
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
	public List<CheckBoxVo> getLstState() throws BizException;
	
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
	public List<CheckBoxVo> getLstINSP2() throws BizException;

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return
	 */
	public List<CheckBoxVo> getLstPassType() throws BizException;

}
