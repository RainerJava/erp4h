package com.fas.dao.common.printer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.fas.common.DBConnection;
import com.fas.common.DBConnectionManager;
import com.fas.common.constants.XMLContants;
import com.fas.common.exception.DaoException;
import com.fas.common.utils.StringUtils;
import com.fas.dao.base.BaseDao;
import com.fas.vo.fprinter.FPrinterVo;

public class PrinterDaoImpl extends BaseDao implements PrinterDao {
	
	/** */
	static Logger logger = Logger.getLogger(PrinterDaoImpl.class);
	
	/* (non-Javadoc)
	 * @see com.pipe.dao.printer.PrinterDao#getDefaultPrinter(java.lang.String, java.lang.String, java.lang.String)
	 */
	public String getDefaultPrinter(String userId, String menuGrp, String menuExe) throws DaoException {
		
		logger.info("PrinterDaoImpl.getDefaultPrinter 開始。");
		
		String strSQL = getSQL(XMLContants.FILE_F_PRINTER, XMLContants.F_PRINTER500);
		DBConnection con = null;
		String strReturnValue = "";

		try {

			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			ResultSet rs = null;
			
			preStmt.setString(1, userId);
			preStmt.setString(2, menuGrp);
			preStmt.setString(3, menuExe);
			
			rs = preStmt.executeQuery();

			if (rs.next()) {
				strReturnValue = StringUtils.nulToString(rs.getString(1));
			}
			
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			if (con != null) {
				try {
					DBConnectionManager.releaseConnection(con);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		logger.info("PrinterDaoImpl.getDefaultPrinter 終了。");
		
		return strReturnValue;	
	}
	
	
	/* (non-Javadoc)
	 * @see com.pipe.dao.printer.PrinterDao#setDefaultPrinter(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public boolean setDefaultPrinter(String userId, String menuGrp, String menuExe, String prtId) throws DaoException{
		
		logger.info("PrinterDaoImpl.setDefaultPrinter 開始。");
		
		String strSQL = "";
		DBConnection con = null;
		
		prtId = prtId.toUpperCase();
//		System.out.println(userId + " menuGrp:" + menuGrp + " menuExe:" + menuExe + " prtId:" + prtId + " default: " + ReportConstants.DEFAULT_PRINTER);
		//thong tin ve may in moi ko duoc set cho den khi user login lai.
		
		try {
			con = DBConnectionManager.getConnection();
			PreparedStatement preStmt = null;

//			if (StringUtils.isValid(ReportConstants.DEFAULT_PRINTER)){
//				//System.out.println("kokichi update");
//				strSQL = getSQL(XMLContants.FILE_F_PRINTER, XMLContants.F_PRINTER004);	//UPDATE
//				logger.info(strSQL);
//
//				preStmt = con.prepareStatement(strSQL);
//				resetParameter(preStmt);
//				
//				preStmt.setString(1, prtId);
//				preStmt.setString(2, userId);
//				preStmt.setString(3, menuGrp);
//				preStmt.setString(4, menuExe);
//				preStmt.setString(5, "0");
//			}else{

			strSQL = getSQL(XMLContants.FILE_F_PRINTER, XMLContants.F_PRINTER002);	//DELETE
			logger.info(strSQL);
			
			preStmt = con.prepareStatement(strSQL);
			resetParameter(preStmt);
			
			preStmt.setString(1, userId);
			preStmt.setString(2, menuGrp);
			preStmt.setString(3, menuExe);
			preStmt.setString(4, "0");
			preStmt.executeUpdate();
			
			
			strSQL = getSQL(XMLContants.FILE_F_PRINTER, XMLContants.F_PRINTER003);	//INSERT
			logger.info(strSQL);
			
			preStmt = con.prepareStatement(strSQL);
			resetParameter(preStmt);
			
			preStmt.setString(1, userId);
			preStmt.setString(2, menuGrp);
			preStmt.setString(3, menuExe);
			preStmt.setString(4, "0");
			preStmt.setString(5, prtId);
			preStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw new DaoException(e.getMessage());
		} finally {
			if (con != null) {
				try {
					DBConnectionManager.releaseConnection(con);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		logger.info("PrinterDaoImpl.setDefaultPrinter 終了。");
		
		return true;	
	}


	/* (non-Javadoc)
	 * @see com.pipe.dao.printer.PrinterDao#getMapFPrinterVo(java.lang.String)
	 */
	@Override
	public Map<String, FPrinterVo> getMapFPrinterVo(String userId) throws DaoException {

		
		logger.info("PrinterDaoImpl.getMapFPrinterVo 開始。");
		
		String strSQL = getSQL(XMLContants.FILE_F_PRINTER, XMLContants.F_PRINTER600);
		DBConnection con = null;
		Map<String, FPrinterVo> mapFPrinterVo = new HashMap<String, FPrinterVo>();
		FPrinterVo dataVo = null;
		try {

			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			ResultSet rs = null;
			
			preStmt.setString(1, userId);
			
			rs = preStmt.executeQuery();

			while (rs.next()) {
				dataVo = new FPrinterVo();
				dataVo.setUserid(userId);
				dataVo.setMenuCode(StringUtils.nulToString(rs.getString("MENU_CODE")));
				dataVo.setMenusCode(StringUtils.nulToString(rs.getString("MENUS_CODE")));
				dataVo.setOutctl(StringUtils.nulToString(rs.getString("OUTCTL")));
				dataVo.setPrtid(StringUtils.nulToString(rs.getString("PRTID")));
				mapFPrinterVo.put(userId + StringUtils.nulToString(rs.getString("MENU_CODE")) + StringUtils.nulToString(rs.getString("MENUS_CODE")), dataVo);
			}
			
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			if (con != null) {
				try {
					DBConnectionManager.releaseConnection(con);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		logger.info("PrinterDaoImpl.getMapFPrinterVo 終了。");
		
		return mapFPrinterVo;	
		
	}

}
