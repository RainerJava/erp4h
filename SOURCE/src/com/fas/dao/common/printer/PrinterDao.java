/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：PrinterDao.java
*
*     記述				：
*     
*     作成日			：2010/01/22   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/
package com.fas.dao.common.printer;

import java.util.Map;

import com.fas.common.exception.DaoException;
import com.fas.vo.fprinter.FPrinterVo;

/**
 * @author PC13
 *
 */
public interface PrinterDao {
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
	 * @throws DaoException
	 */
	public String getDefaultPrinter(String userId, String menuGrp, String menuExe) throws DaoException;
	
	
	/**
	 * @param userId
	 * @param menuGrp
	 * @param menuExe
	 * @param prtId
	 * @return
	 * @throws DaoException
	 */
	public boolean setDefaultPrinter(String userId, String menuGrp, String menuExe, String prtId) throws DaoException;


	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param userId
	 * @return
	 */
	public Map<String, FPrinterVo> getMapFPrinterVo(String userId) throws DaoException;

}
