/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：JidouSaibanUtils.java
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
package com.fas.common.utils;

import com.fas.common.exception.BizException;
import com.fas.service.system.fslp.FSlpOrdService;
import com.fas.service.system.fslp.FSlpOrdServiceImpl;
import com.fas.service.system.fslp.FSlpService;
import com.fas.service.system.fslp.FSlpServiceImpl;
import com.fas.vo.slp.SlpOdrVo;
import com.fas.vo.slp.SlpVo;

/**
 * @author PC13
 *
 */
public class JidouSaibanUtils {
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param slpType
	 * @return
	 * @throws BizException
	 */
	public static synchronized int getSlpNumber(String slpType) throws BizException {
		FSlpService fSlpService = new FSlpServiceImpl();
		return fSlpService.getSlpNumber(slpType, "0000");
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param slpType
	 * @return
	 * @throws BizException
	 */
	public static synchronized int getFixSlpNumber(String slpType) throws BizException {
		FSlpService fSlpService = new FSlpServiceImpl();
		int iFSlpNumber = fSlpService.getSlpNumber(slpType, "0000");
		SlpVo dataVo = fSlpService.getSlpVo(slpType, "0000");
		dataVo.setSlpnew(iFSlpNumber);
		fSlpService.updateSlpVo(dataVo);		
		return iFSlpNumber;
	}
	

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param slpType
	 * @return
	 * @throws BizException
	 */
	public static synchronized int getSlpOrdNumber(String strCustCode, String strDrawType) throws BizException {
		FSlpOrdService fSlpOrdService = new FSlpOrdServiceImpl();
		return fSlpOrdService.getSlpNumber(strCustCode, strDrawType);
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param slpType
	 * @return
	 * @throws BizException
	 */
	public static synchronized int getFixSlpOrdNumber(String strCustCode, String strDrawType) throws BizException {
		FSlpOrdService fSlpOrdService = new FSlpOrdServiceImpl();
		int iFSlpOrdNumber = fSlpOrdService.getSlpNumber(strCustCode, strDrawType);
		SlpOdrVo dataVo = fSlpOrdService.getSlpVo(strCustCode, strDrawType);
		dataVo.setSlpnew(iFSlpOrdNumber);
		fSlpOrdService.updateSlpVo(dataVo);		
		return iFSlpOrdNumber;
	}
	
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			System.out.println(JidouSaibanUtils.getSlpNumber("100"));
		} catch (Exception e) {
		}
	}
}
