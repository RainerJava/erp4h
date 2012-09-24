/************************************************************************************
*
*	会社名		： 林兼コンピューター株式会社
*
*	プロジェクト名	： fas
*
*	ファイル名		： MNameDaoImpl.java
*
*	記述			：
*
*	作成日		：  2011/09/02  10:18:48 午前
*
*	作成者		： LENOVO-F23A3B72
*
*	備考			：
*
************************************************************************************/

package com.fas.dao.system.mname;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.fas.common.DBConnection;
import com.fas.common.DBConnectionManager;
import com.fas.common.constants.ApplicationConstants;
import com.fas.common.constants.XMLContants;
import com.fas.common.exception.DaoException;
import com.fas.common.utils.DateUtils;
import com.fas.common.utils.StringUtils;
import com.fas.dao.base.BaseDao;
import com.fas.vo.mname.MNameVo;
import com.fas.vo.mnamecls.MNameclsVo;


/**
 * @author PC12
 * 
 */
public class MNameDaoImpl extends BaseDao implements MNameDao {
	/** */
	static Logger logger = Logger.getLogger(MNameDaoImpl.class);
	
	/* 
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public List<MNameVo> getAll() throws DaoException{
		// TODO Auto-generated method stub
		logger.info("MNameDaoImpl.getAll 開始。");
		String strSQL = getSQL(XMLContants.FILE_M_NAME, XMLContants.M_NAME001);
		DBConnection con = null;
		List<MNameVo> lstVo = new ArrayList<MNameVo>();
		MNameVo dataVo = null;
		try {
			con = DBConnectionManager.getConnection();
			String strOrderBy = "";
			//generate code OrderBy
			strOrderBy = " ORDER BY M.DSPORDER_NO";
			strSQL += strOrderBy;
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			ResultSet rs = null;
			rs = preStmt.executeQuery();
			while (rs.next()) 
			{
				dataVo = new MNameVo();
				dataVo.getFromSQLServerResultSet(rs);
				dataVo.setNameclsName(StringUtils.nulToString(rs.getString("NAMECLS_NAME")));
				dataVo.setDefaultType((dataVo.getDefaultType()=="1") ? "*" : "");
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
		logger.info("MNameDaoImpl.getAll 終了。");
		return lstVo;
	}
	
	/* 
	 * @param nameclsCode
	 * @param nameCode
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public MNameVo getByKey(String nameclsCode, String nameCode) throws DaoException{
		// TODO Auto-generated method stub
		logger.info("MNameDaoImpl.getByKey 開始。");
		String strSQL = getSQL(XMLContants.FILE_M_NAME, XMLContants.M_NAME002);
		MNameVo outVo = null;
		DBConnection con = null;
		try {
			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			resetParameter(preStmt);
			
			//Where condition
			setParameter(nameclsCode, Types.VARCHAR, preStmt);
			setParameter(nameCode, Types.VARCHAR, preStmt);

			ResultSet rs = preStmt.executeQuery();
			if (rs.next()) {
				outVo = new MNameVo();
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
		logger.info("MNameDaoImpl.getByKey 終了。");
		return outVo;
	}
	
	/* 
	 * @param dataVo
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public MNameVo getByKey(MNameVo dataVo) throws DaoException{
		// TODO Auto-generated method stub
		logger.info("MNameDaoImpl.getByKey 開始。");
		String strSQL = getSQL(XMLContants.FILE_M_NAME, XMLContants.M_NAME002);
		MNameVo outVo = null;
		DBConnection con = null;
		try {
			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			resetParameter(preStmt);

			//Where condition
			setParameter(dataVo.getNameclsCode(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getNameCode(), Types.VARCHAR, preStmt);

			ResultSet rs = preStmt.executeQuery();
			if (rs.next()) {
				outVo = new MNameVo();
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
		logger.info("MNameDaoImpl.getByKey 終了。");
		return outVo;
	}

	/* 
	 * @param dataVo
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public boolean insertVo(MNameVo dataVo) throws DaoException{
		// TODO Auto-generated method stub
		logger.info("MNameDaoImpl.insertVo 開始。");
		String strSQL = getSQL(XMLContants.FILE_M_NAME, XMLContants.M_NAME003);
		DBConnection con = null;
		boolean isSuccess = true;
		try {
			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			resetParameter(preStmt);

			setParameter(dataVo.getNameclsCode(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getNameCode(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getNameName(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getNameRname(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getNamePname(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getNameTname(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getDefaultType(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getDspFlg(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getDsporderNo(), Types.INTEGER, preStmt);
			setParameter(dataVo.getNametype(), Types.VARCHAR, preStmt);
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
		logger.info("MNameDaoImpl.insertVo 終了。");
		return isSuccess;
	}

	/* 
	 * @param dataVo
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public boolean updateVo(MNameVo dataVo) throws DaoException{
		// TODO Auto-generated method stub
		logger.info("MNameDaoImpl.updateVo 開始。");
		String strSQL = getSQL(XMLContants.FILE_M_NAME, XMLContants.M_NAME004);
		DBConnection con = null;
		boolean isSuccess = true;
		try {
			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			resetParameter(preStmt);

			setParameter(dataVo.getNameclsCode(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getNameCode(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getNameName(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getNameRname(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getNamePname(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getNameTname(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getDefaultType(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getDspFlg(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getDsporderNo(), Types.INTEGER, preStmt);
			setParameter(dataVo.getNametype(), Types.VARCHAR, preStmt);
			setParameter(ApplicationConstants.getLoginUser().getUserId(), Types.VARCHAR, preStmt);
			setParameter(ApplicationConstants.getLoginUser().getLoginPC(), Types.VARCHAR, preStmt);
			setParameter(Integer.parseInt(DateUtils.getCurrentDateString()), Types.INTEGER, preStmt);
			setParameter(Integer.parseInt(DateUtils.getTime()), Types.INTEGER, preStmt);
			//Where condition
			setParameter(dataVo.getNameclsCode(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getNameCode(), Types.VARCHAR, preStmt);

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
		logger.info("MNameDaoImpl.updateVo 終了。");
		return isSuccess;
	}

	/* 
	 * @param dataVo
	 * @return
	 * @throws SQLException
	 * @throws DaoException
	 * @description
	 */
	public boolean deleteVo(MNameVo dataVo) throws SQLException, DaoException{
		// TODO Auto-generated method stub
		logger.info("MNameDaoImpl.deleteVo 開始。");
		String strSQL = getSQL(XMLContants.FILE_M_NAME, XMLContants.M_NAME005);
		DBConnection con = null;
		boolean isSuccess = true;
		try {
			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			resetParameter(preStmt);

			//Where condition
			setParameter(dataVo.getNameclsCode(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getNameCode(), Types.VARCHAR, preStmt);

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
		logger.info("MNameDaoImpl.deleteVo 終了。");
		return isSuccess;
	}

	/* 
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public int getVoCount() throws DaoException{
		// TODO Auto-generated method stub
		logger.info("MNameDaoImpl.getVoCount 開始。");
		String strSQL = getSQL(XMLContants.FILE_M_NAME, XMLContants.M_NAME006);
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
		logger.info("MNameDaoImpl.getVoCount 終了。"); 
		return iCount;
	}

	/* 
	 * @param dataVo
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public boolean isDoubleKey(MNameVo dataVo) throws DaoException{
		// TODO Auto-generated method stub
		logger.info("MNameDaoImpl.isDoubleKey 開始。");
		if(getByKey(dataVo) == null) {
		    return false;
		}
		logger.info("MNameDaoImpl.isDoubleKey 終了。");
		return true;
	}

	/* 
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public MNameVo getLatestCode() throws DaoException {
		// TODO Auto-generated method stub
		logger.info("NameDaoImpl.getLatestCode 開始。");		
		String strSQL = getSQL(XMLContants.FILE_M_NAME, XMLContants.M_NAME007);
		DBConnection con = null;
		MNameVo dataVo = null;
		try {

			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			ResultSet rs = preStmt.executeQuery();
			if (rs.next()) {
				dataVo = new MNameVo();
				dataVo.setNameclsCode(StringUtils.nulToString(rs.getString("NAMECLS_CODE")));
				dataVo.setNameCode(StringUtils.nulToString(rs.getString("NAME_CODE")));
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
		logger.info("NameDaoImpl.getLatestCode 終了。");		
		return dataVo;	
	}
	
	/* 
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public List<MNameVo> exportEXCEL() throws DaoException{
		// TODO Auto-generated method stub		 
		logger.info("MNameDaoImpl.exportEXCEL 開始。");
		String strSQL = getSQL(XMLContants.FILE_M_NAME, XMLContants.M_NAME009);
		MNameVo dataVo = null;
		DBConnection con = null;
		List<MNameVo> lstMNameVo = new ArrayList<MNameVo>();
		try {
			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);;
			ResultSet rs = preStmt.executeQuery();
			while (rs.next()) {
				dataVo = new MNameVo();
				dataVo.getFromSQLServerResultSet(rs);
				lstMNameVo.add(dataVo);
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
		logger.info("MNameDaoImpl.exportEXCEL 終了。");		
		return lstMNameVo;
	}
		
	/* 
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public List<MNameclsVo> getListNameclsVo() throws DaoException {
		
		logger.info("NameDaoImpl.getListNameclsVo 開始。");		
		String strSQL = getSQL(XMLContants.FILE_M_NAMECLS, XMLContants.M_NAMECLS001);
		DBConnection con = null;
		List<MNameclsVo> lstVo = new ArrayList<MNameclsVo>();
		MNameclsVo dataVo = null;
		String strWhere = "";
		String strOrderBy = "";
		try {
			con = DBConnectionManager.getConnection();			
			strOrderBy = " ORDER BY M.NAMECLS_NAME";						
			strSQL += strWhere + strOrderBy;
			strSQL = strSQL.replace("$WHERE","");			
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			ResultSet rs = null;
			rs = preStmt.executeQuery();

			while (rs.next()) {
				dataVo = new MNameclsVo();
				dataVo.setNameclsCode(StringUtils.nulToString(rs.getString("NAMECLS_CODE")));
				dataVo.setNameclsName(StringUtils.nulToString(rs.getString("NAMECLS_NAME")));

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
		logger.info("NameDaoImpl.getListNameclsVo 終了。");		
		return lstVo;	
	}
	
	/* 
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public int countNameclsVo() throws DaoException {
		// TODO Auto-generated method stub
		logger.info("NameDaoImpl.getCountVo 開始。");		
		String strSQL = getSQL(XMLContants.FILE_M_NAMECLS, XMLContants.M_NAMECLS002);		
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
		logger.info("NameDaoImpl.getCountVo 終了。");		
		return iCount;
	}
	
	/* 
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public Map<String, MNameVo> getMapNameVo() throws DaoException {

		logger.info("NameDaoImpl.getMapNameVo 開始。");
		String strSQL = getSQL(XMLContants.FILE_M_NAME, XMLContants.M_NAME001);
		DBConnection con = null;
		String strOrderBy = "";
		Map<String, MNameVo> mapName = new HashMap<String, MNameVo>();
		MNameVo dataVo = null;
		try {
			con = DBConnectionManager.getConnection();			
			strOrderBy = " ORDER BY M.NAME_CODE, M.DEFAULT_TYPE ";
			strSQL += strOrderBy;
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			ResultSet rs = null;
			rs = preStmt.executeQuery();
			while (rs.next()) {
				dataVo = new MNameVo();
				dataVo.getFromSQLServerResultSet(rs);
				mapName.put(StringUtils.nulToString(rs.getString("NAMECLS_CODE")) + StringUtils.nulToString(rs.getString("NAME_CODE")), dataVo);
			}
		} catch (SQLException e) {
			logger.error(e.getStackTrace());
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
		logger.info("NameDaoImpl.getMapNameVo 終了。");
		return mapName;
	}
	
	/* 
	 * @param dataVo
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public boolean updateSystemNameVo(MNameVo dataVo) throws DaoException {
		
		logger.info("NameDaoImpl.updateSystemNameVo 開始。");		
		String strSQL = getSQL(XMLContants.FILE_M_NAME, XMLContants.M_NAME011);
		DBConnection con = null;
		boolean isSuccess = false;
		try {
			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			int i=1;
			preStmt.setString(i++, StringUtils.emptyIfNull(dataVo.getNameName()));
			preStmt.setString(i++, StringUtils.emptyIfNull(dataVo.getNameRname()));
			preStmt.setString(i++, StringUtils.emptyIfNull(dataVo.getLastupUser()));
			preStmt.setString(i++, StringUtils.emptyIfNull(dataVo.getLastupPc()));
			preStmt.setInt(i++, dataVo.getLastupDate());
			preStmt.setInt(i++, dataVo.getLastupTime());
			preStmt.setString(i++, StringUtils.emptyIfNull(dataVo.getNameclsCode()));
			preStmt.setString(i++, StringUtils.emptyIfNull(dataVo.getNameCode()));
			preStmt.executeUpdate();
			isSuccess = true;			
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
		logger.info("NameDaoImpl.updateSystemNameVo 終了。");		
		return isSuccess;			
	}
}
