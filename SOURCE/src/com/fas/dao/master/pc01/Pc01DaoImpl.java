/************************************************************************************
*
*	会社名		： 林兼コンピューター株式会社
*
*	プロジェクト名	： fas
*
*	ファイル名		： Pc01DaoImpl.java
*
*	記述			：
*
*	作成日		：  2011/09/26  01:11:29 午後
*
*	作成者		： LENOVO-F23A3B72
*
*	備考			：
*
************************************************************************************/

package com.fas.dao.master.pc01;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import com.fas.common.DBConnection;
import com.fas.common.DBConnectionManager;
import com.fas.common.constants.ApplicationConstants;
import com.fas.common.constants.XMLContants;
import com.fas.common.exception.DaoException;
import com.fas.common.utils.DateUtils;
import com.fas.dao.base.BaseDao;
import com.fas.vo.pc01.Pc01Vo;

/**
 * <DL>
 *   <DT>クラス記述：</DT>
 * 	 <DD></DD>
 * <BR>
 *   <DT>変更歴史：</DT>
 * 	 <DD>著作者: LENOVO-F23A3B72 </DD><BR>
 * 	 <DD></DD>
 * </DL>
**/
public class Pc01DaoImpl extends BaseDao implements Pc01Dao {
	/** */
	static Logger logger = Logger.getLogger(Pc01DaoImpl.class);
	/* 
	 * @param iOrderBy
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public List<Pc01Vo> getAll(int iOrderBy) throws DaoException{
		// TODO Auto-generated method stub
		logger.info("Pc01DaoImpl.getAll 開始。");
		String strSQL = getSQL(XMLContants.FILE_PC01, XMLContants.PC01001);
		DBConnection con = null;
		List<Pc01Vo> lstVo = new ArrayList<Pc01Vo>();
		Pc01Vo dataVo = null;
		try {
			con = DBConnectionManager.getConnection();
			String strOrderBy = "";
			//generate code OrderBy
			strSQL += strOrderBy;
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			ResultSet rs = null;
			rs = preStmt.executeQuery();
			while (rs.next()) 
			{
				dataVo = new Pc01Vo();
				dataVo.getFromSQLServerResultSet(rs);
				lstVo.add(dataVo);
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
		logger.info("Pc01DaoImpl.getAll 終了。");
		return lstVo;
	}

	/* 
	 * @param c0101
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public Pc01Vo getByKey(int c0101) throws DaoException{
		// TODO Auto-generated method stub
		logger.info("Pc01DaoImpl.getByKey 開始。");
		String strSQL = getSQL(XMLContants.FILE_PC01, XMLContants.PC01002);
		Pc01Vo outVo = null;
		DBConnection con = null;
		try {
			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			resetParameter(preStmt);
			
			//Where condition
			setParameter(c0101, Types.INTEGER, preStmt);

			ResultSet rs = preStmt.executeQuery();
			if (rs.next()) {
				outVo = new Pc01Vo();
				outVo.getFromSQLServerResultSet(rs);
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
		logger.info("Pc01DaoImpl.getByKey 終了。");
		return outVo;
	}

	/* 
	 * @param dataVo
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public Pc01Vo getByKey(Pc01Vo dataVo) throws DaoException{
		// TODO Auto-generated method stub
		logger.info("Pc01DaoImpl.getByKey 開始。");
		String strSQL = getSQL(XMLContants.FILE_PC01, XMLContants.PC01002);
		Pc01Vo outVo = null;
		DBConnection con = null;
		try {
			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			resetParameter(preStmt);

			//Where condition
			setParameter(dataVo.getC0101(), Types.INTEGER, preStmt);

			ResultSet rs = preStmt.executeQuery();
			if (rs.next()) {
				outVo = new Pc01Vo();
				outVo.getFromSQLServerResultSet(rs);
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
		logger.info("Pc01DaoImpl.getByKey 終了。");
		return outVo;
	}

	/* 
	 * @param dataVo
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public boolean insertVo(Pc01Vo dataVo) throws DaoException{
		// TODO Auto-generated method stub
		logger.info("Pc01DaoImpl.insertVo 開始。");
		String strSQL = getSQL(XMLContants.FILE_PC01, XMLContants.PC01003);
		DBConnection con = null;
		boolean isSuccess = true;
		try {
			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			resetParameter(preStmt);

			setParameter(dataVo.getC0101(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0102(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0103(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0104(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getC0105(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0106(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0107(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0108(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0109(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0110(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0111(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0112(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0113(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0114(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0115(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0116(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0117(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0118(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0119(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0120(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0121(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0122(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getC0123(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getC0124(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getC0125(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getC0126(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0127(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getC0128(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getC0129(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0130(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0131(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0132(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0133(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0134(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0135(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0136(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0137(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0138(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0139(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0140(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0141(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0142(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0143(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0144(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0145(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0146(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0147(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0148(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0149(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0150(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0151(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0152(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0153(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0154(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0155(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0156(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0157(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0158(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0159(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0160(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0161(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0162(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0163(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0164(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0165(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0166(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0167(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0168(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0169(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0170(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0171(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0172(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0173(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0174(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0175(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0176(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0177(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0178(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0179(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0180(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getC0181(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getC0182(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getC0183(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0184(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getC0185(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getC0186(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0187(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0188(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0189(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0190(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getC0191(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getC01dm(), Types.VARCHAR, preStmt);

			preStmt.execute();
		} catch (SQLException e) {
			isSuccess = false;
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
		logger.info("Pc01DaoImpl.insertVo 終了。");
		return isSuccess;
	}

	/* 
	 * @param dataVo
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public boolean updateVo(Pc01Vo dataVo) throws DaoException{
		// TODO Auto-generated method stub
		logger.info("Pc01DaoImpl.updateVo 開始。");
		String strSQL = getSQL(XMLContants.FILE_PC01, XMLContants.PC01004);
		DBConnection con = null;
		boolean isSuccess = true;
		try {
			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			resetParameter(preStmt);

			setParameter(dataVo.getC0102(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0103(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0104(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getC0105(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0106(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0107(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0108(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0109(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0110(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0111(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0112(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0113(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0114(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0115(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0116(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0117(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0118(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0119(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0120(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0121(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0122(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getC0123(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getC0124(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getC0125(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getC0126(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0127(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getC0128(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getC0129(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0130(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0131(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0132(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0133(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0134(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0135(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0136(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0137(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0138(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0139(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0140(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0141(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0142(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0143(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0144(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0145(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0146(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0147(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0148(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0149(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0150(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0151(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0152(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0153(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0154(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0155(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0156(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0157(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0158(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0159(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0160(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0161(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0162(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0163(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0164(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0165(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0166(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0167(), Types.DOUBLE, preStmt);
			setParameter(dataVo.getC0168(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0169(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0170(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0171(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0172(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0173(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0174(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0175(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0176(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0177(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0178(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0179(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0180(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getC0181(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getC0182(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getC0183(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0184(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getC0185(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getC0186(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0187(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0188(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0189(), Types.INTEGER, preStmt);
			setParameter(dataVo.getC0190(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getC0191(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getC01dm(), Types.VARCHAR, preStmt);
			//Where condition
			setParameter(dataVo.getC0101(), Types.INTEGER, preStmt);

			preStmt.executeUpdate();
		} catch (SQLException e) {
			isSuccess = false;
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
		logger.info("Pc01DaoImpl.updateVo 終了。");
		return isSuccess;
	}

	/* 
	 * @param dataVo
	 * @return
	 * @throws SQLException
	 * @throws DaoException
	 * @description
	 */
	public boolean deleteVo(Pc01Vo dataVo) throws SQLException, DaoException{
		// TODO Auto-generated method stub
		logger.info("Pc01DaoImpl.deleteVo 開始。");
		String strSQL = getSQL(XMLContants.FILE_PC01, XMLContants.PC01005);
		DBConnection con = null;
		boolean isSuccess = true;
		try {
			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			resetParameter(preStmt);

			//Where condition
			setParameter(dataVo.getC0101(), Types.INTEGER, preStmt);

			preStmt.execute();
		} catch (SQLException sqlException){
			isSuccess = false;
			logger.error(sqlException.getMessage());
			throw new SQLException(sqlException);
		} catch (Exception e) {
			isSuccess = false;
			logger.error(e.getMessage());
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
		logger.info("Pc01DaoImpl.deleteVo 終了。");
		return isSuccess;
	}

	/* 
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public int getVoCount() throws DaoException{
		// TODO Auto-generated method stub
		logger.info("Pc01DaoImpl.getVoCount 開始。");
		String strSQL = getSQL(XMLContants.FILE_PC01, XMLContants.PC01006);
		DBConnection con = null;
		int iCount = 0;
		try {
			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			ResultSet rs = preStmt.executeQuery();
			if (rs.next()) {
				iCount = rs.getInt(1);
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
		logger.info("Pc01DaoImpl.getVoCount 終了。"); 
		return iCount;
	}

	/* 
	 * @param dataVo
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public boolean isDoubleKey(Pc01Vo dataVo) throws DaoException{
		// TODO Auto-generated method stub
		logger.info("Pc01DaoImpl.isDoubleKey 開始。");
		if(getByKey(dataVo) == null) {
		    return false;
		}
		logger.info("Pc01DaoImpl.isDoubleKey 終了。");
		return true;
	}

	/* 
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public String getLatestCode() throws DaoException {
		// TODO Auto-generated method stub
		logger.info("Pc01DaoImpl.getLatestCode 開始。");
		String strSQL = getSQL(XMLContants.FILE_PC01, XMLContants.PC01007);
		DBConnection con = null;
		String strLatestCode = "";
		try {
			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			ResultSet rs = null;
			rs = preStmt.executeQuery();
			if (rs.next()) {
				strLatestCode = rs.getString("MAX_CODE");
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (con != null) {
				try {
					DBConnectionManager.releaseConnection(con);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
    	logger.info("Pc01DaoImpl.getLatestCode 終了。");
		return strLatestCode;
	}

	/* 
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public List<Pc01Vo> exportEXCEL() throws DaoException{
		// TODO Auto-generated method stub		 
		logger.info("Pc01DaoImpl.exportEXCEL 開始。");
		String strSQL = getSQL(XMLContants.FILE_PC01, XMLContants.PC01009);
		Pc01Vo dataVo = null;
		DBConnection con = null;
		List<Pc01Vo> lstPc01Vo = new ArrayList<Pc01Vo>();
		try {
			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);;
			ResultSet rs = preStmt.executeQuery();
			while (rs.next()) {
				dataVo = new Pc01Vo();
				dataVo.getFromSQLServerResultSet(rs);
				lstPc01Vo.add(dataVo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		} finally {
			if (con != null) {
				try {
					DBConnectionManager.releaseConnection(con);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		logger.info("Pc01DaoImpl.exportEXCEL 終了。");		
		return lstPc01Vo;
	}
}