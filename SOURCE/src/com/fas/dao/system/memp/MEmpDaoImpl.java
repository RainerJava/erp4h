/************************************************************************************
*
*	会社名		： 林兼コンピューター株式会社
*
*	プロジェクト名	： fas
*
*	ファイル名		： MEmpDaoImpl.java
*
*	記述			：
*
*	作成日		：  2011/08/24  03:26:34 午後
*
*	作成者		： LENOVO-F23A3B72
*
*	備考			：
*
************************************************************************************/

package com.fas.dao.system.memp;

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
import com.fas.vo.memp.MEmpVo;

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
public class MEmpDaoImpl extends BaseDao implements MEmpDao {
	
	/**
	 * 
	 */
	static Logger logger = Logger.getLogger(MEmpDaoImpl.class);
	
	/* 
	 * @param iOrderBy
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public List<MEmpVo> getAll(int iOrderBy) throws DaoException{
		// TODO Auto-generated method stub
		logger.info("MEmpDaoImpl.getAll 開始。");
		String strSQL = getSQL(XMLContants.FILE_M_EMP, XMLContants.M_EMP001);
		DBConnection con = null;
		List<MEmpVo> lstVo = new ArrayList<MEmpVo>();
		MEmpVo dataVo = null;
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
				dataVo = new MEmpVo();
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
		logger.info("MEmpDaoImpl.getAll 終了。");
		return lstVo;
	}

	/* 
	 * @param empCode
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public MEmpVo getByKey(String empCode) throws DaoException{
		// TODO Auto-generated method stub
		logger.info("MEmpDaoImpl.getByKey 開始。");
		String strSQL = getSQL(XMLContants.FILE_M_EMP, XMLContants.M_EMP002);
		MEmpVo outVo = null;
		DBConnection con = null;
		try {
			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			resetParameter(preStmt);
			
			//Where condition
			setParameter(empCode, Types.VARCHAR, preStmt);

			ResultSet rs = preStmt.executeQuery();
			if (rs.next()) {
				outVo = new MEmpVo();
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
		logger.info("MEmpDaoImpl.getByKey 終了。");
		return outVo;
	}
	
	/* 
	 * @param dataVo
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public MEmpVo getByKey(MEmpVo dataVo) throws DaoException{
		// TODO Auto-generated method stub
		logger.info("MEmpDaoImpl.getByKey 開始。");
		String strSQL = getSQL(XMLContants.FILE_M_EMP, XMLContants.M_EMP002);
		MEmpVo outVo = null;
		DBConnection con = null;
		try {
			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			resetParameter(preStmt);

			//Where condition
			setParameter(dataVo.getEmpCode(), Types.VARCHAR, preStmt);

			ResultSet rs = preStmt.executeQuery();
			if (rs.next()) {
				outVo = new MEmpVo();
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
		logger.info("MEmpDaoImpl.getByKey 終了。");
		return outVo;
	}

	/* 
	 * @param dataVo
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public boolean insertVo(MEmpVo dataVo) throws DaoException{
		// TODO Auto-generated method stub
		logger.info("MEmpDaoImpl.insertVo 開始。");
		String strSQL = getSQL(XMLContants.FILE_M_EMP, XMLContants.M_EMP003);
		DBConnection con = null;
		boolean isSuccess = true;
		try {
			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			resetParameter(preStmt);

			setParameter(dataVo.getEmpCode(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getEmpKana(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getEmpName(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getEmpTname(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getUserUser(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getEmpUser(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getPwd(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getDsporderNo(), Types.INTEGER, preStmt);
			setParameter(dataVo.getInoutFlg(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getTelno(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getMailadr(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getMailadrm(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getSectCode(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getSect1Code(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getSect2Code(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getSect3Code(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getSect4Code(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getEmptyp1Code(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getEmptyp2Code(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getEmptyp3Code(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getPcid(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getLogviewFlg(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getExcelout(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getPathName(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getOldCode(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getSearchidx(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getUsingFlg(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getUseFlg(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getPwdDate(), Types.INTEGER, preStmt);
			setParameter(dataVo.getPwd1(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getPwd2(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getPwd3(), Types.VARCHAR, preStmt);
			setParameter(ApplicationConstants.getLoginUser().getUserId(), Types.VARCHAR, preStmt);
			setParameter(ApplicationConstants.getLoginUser().getLoginPC(), Types.VARCHAR, preStmt);
			setParameter(Integer.parseInt(DateUtils.getCurrentDateString()), Types.INTEGER, preStmt);
			setParameter(Integer.parseInt(DateUtils.getTime()), Types.INTEGER, preStmt);
			setParameter(ApplicationConstants.getLoginUser().getUserId(), Types.VARCHAR, preStmt);
			setParameter(ApplicationConstants.getLoginUser().getLoginPC(), Types.VARCHAR, preStmt);
			setParameter(Integer.parseInt(DateUtils.getCurrentDateString()), Types.INTEGER, preStmt);
			setParameter(Integer.parseInt(DateUtils.getTime()), Types.INTEGER, preStmt);

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
		logger.info("MEmpDaoImpl.insertVo 終了。");
		return isSuccess;
	}

	/* 
	 * @param dataVo
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public boolean updateVo(MEmpVo dataVo) throws DaoException{
		// TODO Auto-generated method stub
		logger.info("MEmpDaoImpl.updateVo 開始。");
		String strSQL = getSQL(XMLContants.FILE_M_EMP, XMLContants.M_EMP004);
		DBConnection con = null;
		boolean isSuccess = true;
		try {
			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			resetParameter(preStmt);

			setParameter(dataVo.getEmpCode(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getEmpKana(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getEmpName(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getEmpTname(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getUserUser(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getEmpUser(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getPwd(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getDsporderNo(), Types.INTEGER, preStmt);
			setParameter(dataVo.getInoutFlg(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getTelno(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getMailadr(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getMailadrm(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getSectCode(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getSect1Code(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getSect2Code(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getSect3Code(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getSect4Code(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getEmptyp1Code(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getEmptyp2Code(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getEmptyp3Code(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getPcid(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getLogviewFlg(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getExcelout(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getPathName(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getOldCode(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getSearchidx(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getUsingFlg(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getUseFlg(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getPwdDate(), Types.INTEGER, preStmt);
			setParameter(dataVo.getPwd1(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getPwd2(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getPwd3(), Types.VARCHAR, preStmt);
			setParameter(ApplicationConstants.getLoginUser().getUserId(), Types.VARCHAR, preStmt);
			setParameter(ApplicationConstants.getLoginUser().getLoginPC(), Types.VARCHAR, preStmt);
			setParameter(Integer.parseInt(DateUtils.getCurrentDateString()), Types.INTEGER, preStmt);
			setParameter(Integer.parseInt(DateUtils.getTime()), Types.INTEGER, preStmt);
			//Where condition
			setParameter(dataVo.getEmpCode(), Types.VARCHAR, preStmt);

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
		logger.info("MEmpDaoImpl.updateVo 終了。");
		return isSuccess;
	}

	/* 
	 * @param dataVo
	 * @return
	 * @throws SQLException
	 * @throws DaoException
	 * @description
	 */
	public boolean deleteVo(MEmpVo dataVo) throws SQLException, DaoException{
		// TODO Auto-generated method stub
		logger.info("MEmpDaoImpl.deleteVo 開始。");
		String strSQL = getSQL(XMLContants.FILE_M_EMP, XMLContants.M_EMP005);
		DBConnection con = null;
		boolean isSuccess = true;
		try {
			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			resetParameter(preStmt);

			//Where condition
			setParameter(dataVo.getEmpCode(), Types.VARCHAR, preStmt);

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
		logger.info("MEmpDaoImpl.deleteVo 終了。");
		return isSuccess;
	}

	/* 
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public int getVoCount() throws DaoException{
		// TODO Auto-generated method stub
		logger.info("MEmpDaoImpl.getVoCount 開始。");
		String strSQL = getSQL(XMLContants.FILE_M_EMP, XMLContants.M_EMP006);
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
		logger.info("MEmpDaoImpl.getVoCount 終了。"); 
		return iCount;
	}

	/* 
	 * @param dataVo
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public boolean isDoubleKey(MEmpVo dataVo) throws DaoException{
		// TODO Auto-generated method stub
		logger.info("MEmpDaoImpl.isDoubleKey 開始。");
		if(getByKey(dataVo) == null) {
		    return false;
		}
		logger.info("MEmpDaoImpl.isDoubleKey 終了。");
		return true;
	}

	/* 
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public String getLatestCode() throws DaoException {
		// TODO Auto-generated method stub
		logger.info("MEmpDaoImpl.getLatestCode 開始。");
		String strSQL = getSQL(XMLContants.FILE_M_EMP, XMLContants.M_EMP007);
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
    	logger.info("MEmpDaoImpl.getLatestCode 終了。");
		return strLatestCode;
	}

	/* 
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public List<MEmpVo> exportEXCEL() throws DaoException{
		// TODO Auto-generated method stub		 
		logger.info("MEmpDaoImpl.exportEXCEL 開始。");
		String strSQL = getSQL(XMLContants.FILE_M_EMP, XMLContants.M_EMP009);
		MEmpVo dataVo = null;
		DBConnection con = null;
		List<MEmpVo> lstMEmpVo = new ArrayList<MEmpVo>();
		try {
			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			ResultSet rs = preStmt.executeQuery();
			while (rs.next()) {
				dataVo = new MEmpVo();
				dataVo.getFromSQLServerResultSet(rs);
				lstMEmpVo.add(dataVo);
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
		logger.info("MEmpDaoImpl.exportEXCEL 終了。");		
		return lstMEmpVo;
	}	
	
	/* 
	 * @param strEmpUser
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public int getVoCountByUserEmpUser( String strEmpUser ) throws DaoException {
		// TODO Auto-generated method stub	
		logger.info("MEmpDaoImpl.getVoCountByUserEmpUser 開始");
		String strSQL = getSQL(XMLContants.FILE_M_EMP, XMLContants.M_EMP006);
		DBConnection con = null;
		String strWhere = " WHERE EMP_USER = '" + strEmpUser.toString() +"'";
		strSQL += strWhere;		
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
		logger.info("MEmpDaoImpl.getVoCountByUserEmpUser 終了");		
		return iCount;
	}
}