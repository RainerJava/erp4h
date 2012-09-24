/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名		：エスイーシー化成
* 
*     ファイル名		：ExportExcelImpl.java
*
*     記述			：
*     
*     作成日			：Apr 8, 2010   
*
*     作成者			：Bui Ngoc Viet
*
*     備考			：
*
**************************************************************************************/

package com.fas.service.common.exportexcel;

import java.sql.ResultSet;

import com.fas.common.exception.BizException;
import com.fas.common.exception.DaoException;
import com.fas.dao.common.exportexcel.ExportExcelDao;
import com.fas.dao.common.exportexcel.ExportExcelDaoImpl;
import com.fas.service.base.BaseService;

/**
 * <DL>
 *   <DT>クラス記述：</DT>
 * 	<DD></DD>
 * <BR>
 *   <DT>変更歴史：</DT>
 * 	<DD>著作者: Bui Ngoc Viet</DD><BR>
 * 	<DD></DD>
 * </DL>
 */
public class ExportExcelServiceImpl extends BaseService implements ExportExcelService
{

	private ExportExcelDao expDao = null;
	public ExportExcelServiceImpl()
	{
		expDao = new ExportExcelDaoImpl();
	}

//	public String export(String strSQL) throws BizException {
//	try {
//		return expDao.export(strSQL);
//	} catch (DaoException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	return null;

	public ResultSet export(String strSQL) throws BizException {
		try {
			return expDao.export(strSQL);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


//	public ArrayList<ArrayList<String>> export(String strSQL) throws BizException {
//		try {
//			return expDao.export(strSQL);
//		} catch (DaoException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}

}
