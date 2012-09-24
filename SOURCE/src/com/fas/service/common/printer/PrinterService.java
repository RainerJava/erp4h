package com.fas.service.common.printer;

import java.util.Map;

import com.fas.common.exception.BizException;
import com.fas.vo.fprinter.FPrinterVo;

public interface PrinterService {
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param userId
	 * @param menuGrp
	 * @param menuExe
	 * @return
	 * @throws BizException
	 */
	public String getDefaultPrinter(String userId, String menuGrp, String menuExe) throws BizException;
	
	/**
	 * @param userId
	 * @param menuGrp
	 * @param menuExe
	 * @param prtId
	 * @return
	 * @throws BizException
	 */
	public boolean setDefaultPrinter(String userId, String menuGrp, String menuExe, String prtId) throws BizException;
	
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param userId
	 * @return
	 * @throws BizException
	 */
	public Map<String, FPrinterVo> getMapFPrinterVo(String userId) throws BizException;


}
