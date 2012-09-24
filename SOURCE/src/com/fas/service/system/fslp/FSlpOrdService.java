/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：FSlpService.java
*
*     記述				：
*     
*     作成日			：2010/03/17   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/
package com.fas.service.system.fslp;

import java.util.List;

import com.fas.common.exception.BizException;
import com.fas.vo.slp.SlpOdrVo;
import com.fas.vo.slp.SlpVo;

/**
 * @author PC13
 *
 */
public interface FSlpOrdService {
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param slpType
	 * @param slpDate
	 * @return
	 * @throws BizException
	 */
	public int getSlpNumber(String slpType, String slpDate) throws BizException;
	public SlpOdrVo getSlpVo(String custCode, String drawType) throws BizException;
	public List<SlpOdrVo> getLstSlpVo()throws BizException;
	public boolean updateSlpVo(SlpOdrVo dataVo)throws BizException;
	public boolean insertSlpOdrVo(SlpOdrVo dataVo) throws BizException;
	public boolean delSlpByCustCode(String custCode)throws BizException;
}
