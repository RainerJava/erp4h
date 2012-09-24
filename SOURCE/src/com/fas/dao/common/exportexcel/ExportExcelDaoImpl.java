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

package com.fas.dao.common.exportexcel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.fas.common.DBConnection;
import com.fas.common.DBConnectionManager;
import com.fas.common.exception.DaoException;
import com.fas.dao.base.BaseDao;

/**
 * <DL>
 * <DT>クラス記述：</DT>
 * <DD></DD>
 * <BR>
 * <DT>変更歴史：</DT>
 * <DD>著作者: Bui Ngoc Viet</DD><BR>
 * <DD></DD>
 * </DL>
 */
public class ExportExcelDaoImpl extends BaseDao implements ExportExcelDao {

	static Logger logger = Logger.getLogger(ExportExcelDaoImpl.class);
	public ExportExcelDaoImpl()
{
	
}
//	public String export(String strSQL) throws DaoException {
//		logger.info("ExportExcelImpl.export 開始。");
//		StringBuffer bf = new StringBuffer();
//		DBConnection con = null;
//
//		try {
//			con = DBConnectionManager.getConnection();
//			logger.info(strSQL);
//			PreparedStatement preStmt = con.prepareStatement(strSQL);
//			ResultSet rs = preStmt.executeQuery();
//			ResultSetMetaData mtdt = rs.getMetaData();
//			int count = mtdt.getColumnCount();			
//					
//			for(int i = 1; i<=count;i++)
//			{
//				bf.append(mtdt.getColumnName(i));
//				bf.append(',');
//			}
//			
//			bf = bf.delete(bf.lastIndexOf(","), bf.length());
//			bf.append('\n');
//			//add row to file CSV
//			while (rs.next()) {
//				for(int i = 1; i<=count;i++)
//				{					
//					bf.append("\"");
//					bf.append(StringUtils.nulToString(rs.getString(i)));
//					bf.append("\"");
//					bf.append(',');
//				}
//				bf = bf.delete(bf.lastIndexOf(","), bf.length());
//				bf.append('\n');
//			}
//		} catch (SQLException e) {
//			throw new DaoException(e);
//		} finally {
//			if (con != null) {
//				try {
//					DBConnectionManager.releaseConnection(con);
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//
//		logger.info("ExportExcelImpl.export 終了。");
//
//		return bf.toString();
//	}
		
	public ResultSet export(String strSQL) throws DaoException{
		logger.info("ExportExcelImpl.export 開始。");
		StringBuffer bf = new StringBuffer();
		DBConnection con = null;

		try {
			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			ResultSet rs = preStmt.executeQuery();
			logger.info("ExportExcelImpl.export 終了。");
			return rs;
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
	}
	
//	public ArrayList<ArrayList<String>> export(String strSQL) throws DaoException{
//		logger.info("ExportExcelImpl.export 開始。");
//		StringBuffer bf = new StringBuffer();
//		DBConnection con = null;
//	
//		try {
//			con = DBConnectionManager.getConnection();
//			logger.info(strSQL);
//			PreparedStatement preStmt = con.prepareStatement(strSQL);
//			ResultSet rs = preStmt.executeQuery();
//			logger.info("ExportExcelImpl.export 終了。");
//			
//			//return rs;
//		} catch (SQLException e) {
//			throw new DaoException(e);
//		} finally {
//			if (con != null) {
//				try {
//					DBConnectionManager.releaseConnection(con);
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		return null;
//	}

}
