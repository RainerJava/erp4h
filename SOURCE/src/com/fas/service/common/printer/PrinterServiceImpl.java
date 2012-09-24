package com.fas.service.common.printer;

import java.util.Map;

import com.fas.common.exception.BizException;
import com.fas.common.exception.DaoException;
import com.fas.dao.common.printer.PrinterDao;
import com.fas.dao.common.printer.PrinterDaoImpl;
import com.fas.service.base.BaseService;
import com.fas.vo.fprinter.FPrinterVo;

public class PrinterServiceImpl extends BaseService implements PrinterService {
	
	/** */
	private PrinterDao printerDao = null;
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public PrinterServiceImpl() {
		printerDao = new PrinterDaoImpl();
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.service.printer.PrinterService#getDefaultPrinter(java.lang.String, java.lang.String, java.lang.String)
	 */
	public String getDefaultPrinter(String userId, String menuGrp, String menuExe) throws BizException {
		try {
			return printerDao.getDefaultPrinter(userId, menuGrp, menuExe);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
	
	
	/* (non-Javadoc)
	 * @see com.pipe.service.printer.PrinterService#setDefaultPrinter(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public boolean setDefaultPrinter(String userId, String menuGrp, String menuExe, String prtId) throws BizException{
		try {
			return printerDao.setDefaultPrinter(userId, menuGrp, menuExe, prtId);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.pipe.service.printer.PrinterService#getMapFPrinterVo(java.lang.String)
	 */
	@Override
	public Map<String, FPrinterVo> getMapFPrinterVo(String userId) throws BizException {
		try {
			return printerDao.getMapFPrinterVo(userId);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}

}
