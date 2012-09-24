/************************************************************************************
*
*	会社名		： 林兼コンピューター株式会社
*
*	プロジェクト名	： fas
*
*	ファイル名		： MCtlDaoImpl.java
*
*	記述			：
*
*	作成日		：  2011/08/31  09:35:12 午前
*
*	作成者		： LENOVO-F23A3B72
*
*	備考			：
*
************************************************************************************/

package com.fas.dao.system.mctl;

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
import com.fas.common.exception.BizException;
import com.fas.common.exception.DaoException;
import com.fas.common.utils.DateUtils;
import com.fas.common.utils.StringUtils;
import com.fas.dao.base.BaseDao;
import com.fas.vo.mctl.MCtlVo;

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
public class MCtlDaoImpl extends BaseDao implements MCtlDao {
	/** */
	static Logger logger = Logger.getLogger(MCtlDaoImpl.class);
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return all table data
	 * @throws DaoException
	 */
	public List<MCtlVo> getAll(int iOrderBy) throws DaoException{
		// TODO Auto-generated method stub
		logger.info("MCtlDaoImpl.getAll 開始。");
		String strSQL = getSQL(XMLContants.FILE_M_CTL, XMLContants.M_CTL001);
		DBConnection con = null;
		List<MCtlVo> lstVo = new ArrayList<MCtlVo>();
		MCtlVo dataVo = null;
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
				dataVo = new MCtlVo();
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
		logger.info("MCtlDaoImpl.getAll 終了。");
		return lstVo;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param String userid, String cKey
	 * @return MCtlVo if object exist with key else return NULL
	 * @throws DaoException
	 */
	public MCtlVo getByKey(String userid, String cKey) throws DaoException{
		// TODO Auto-generated method stub
		logger.info("MCtlDaoImpl.getByKey 開始。");
		String strSQL = getSQL(XMLContants.FILE_M_CTL, XMLContants.M_CTL002);
		MCtlVo outVo = null;
		DBConnection con = null;
		try {
			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			resetParameter(preStmt);
			
			//Where condition
			setParameter(userid, Types.VARCHAR, preStmt);
			setParameter(cKey, Types.VARCHAR, preStmt);

			ResultSet rs = preStmt.executeQuery();
			if (rs.next()) {
				outVo = new MCtlVo();
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
		logger.info("MCtlDaoImpl.getByKey 終了。");
		return outVo;
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param dataVo
	 * @return MCtlVo if object exist with key else return NULL
	 * @throws DaoException
	 */
	public MCtlVo getByKey(MCtlVo dataVo) throws DaoException{
		// TODO Auto-generated method stub
		logger.info("MCtlDaoImpl.getByKey 開始。");
		String strSQL = getSQL(XMLContants.FILE_M_CTL, XMLContants.M_CTL002);
		MCtlVo outVo = null;
		DBConnection con = null;
		try {
			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			resetParameter(preStmt);

			//Where condition
			setParameter(dataVo.getUserid(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getCKey(), Types.VARCHAR, preStmt);

			ResultSet rs = preStmt.executeQuery();
			if (rs.next()) {
				outVo = new MCtlVo();
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
		logger.info("MCtlDaoImpl.getByKey 終了。");
		return outVo;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param dataVo
	 * @return TRUE if insert susscessful else return FALSE
	 * @throws DaoException
	 */
	public boolean insertVo(MCtlVo dataVo) throws DaoException{
		// TODO Auto-generated method stub
		logger.info("MCtlDaoImpl.insertVo 開始。");
		String strSQL = getSQL(XMLContants.FILE_M_CTL, XMLContants.M_CTL003);
		DBConnection con = null;
		boolean isSuccess = true;
		try {
			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			resetParameter(preStmt);

			setParameter(dataVo.getUserid(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getCKey(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getCName(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getCData(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getCHelp(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getCBm(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getCDecbm(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getCAttr(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getMtnFlg(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getCntFlg(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getUpdFlg(), Types.VARCHAR, preStmt);
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
		logger.info("MCtlDaoImpl.insertVo 終了。");
		return isSuccess;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param dataVo
	 * @return TRUE if update susscessful else return FALSE
	 * @throws DaoException
	 */
	public boolean updateVo(MCtlVo dataVo) throws DaoException{
		// TODO Auto-generated method stub
		logger.info("MCtlDaoImpl.updateVo 開始。");
		String strSQL = getSQL(XMLContants.FILE_M_CTL, XMLContants.M_CTL004);
		DBConnection con = null;
		boolean isSuccess = true;
		try {
			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			resetParameter(preStmt);

			setParameter(dataVo.getUserid(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getCKey(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getCName(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getCData(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getCHelp(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getCBm(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getCDecbm(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getCAttr(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getMtnFlg(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getCntFlg(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getUpdFlg(), Types.VARCHAR, preStmt);
			setParameter(ApplicationConstants.getLoginUser().getUserId(), Types.VARCHAR, preStmt);
			setParameter(ApplicationConstants.getLoginUser().getLoginPC(), Types.VARCHAR, preStmt);
			setParameter(Integer.parseInt(DateUtils.getCurrentDateString()), Types.INTEGER, preStmt);
			setParameter(Integer.parseInt(DateUtils.getTime()), Types.INTEGER, preStmt);
			//Where condition
			setParameter(dataVo.getUserid(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getCKey(), Types.VARCHAR, preStmt);

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
		logger.info("MCtlDaoImpl.updateVo 終了。");
		return isSuccess;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param dataVo
	 * @return TRUE if delete susscessful else return FALSE
	 * @throws DaoException
	 */
	public boolean deleteVo(MCtlVo dataVo) throws SQLException, DaoException{
		// TODO Auto-generated method stub
		logger.info("MCtlDaoImpl.deleteVo 開始。");
		String strSQL = getSQL(XMLContants.FILE_M_CTL, XMLContants.M_CTL005);
		DBConnection con = null;
		boolean isSuccess = true;
		try {
			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			resetParameter(preStmt);

			//Where condition
			setParameter(dataVo.getUserid(), Types.VARCHAR, preStmt);
			setParameter(dataVo.getCKey(), Types.VARCHAR, preStmt);

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
		logger.info("MCtlDaoImpl.deleteVo 終了。");
		return isSuccess;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return count of MCtlVo
	 * @throws DaoException
	 */
	public int getVoCount() throws DaoException{
		// TODO Auto-generated method stub
		logger.info("MCtlDaoImpl.getVoCount 開始。");
		String strSQL = getSQL(XMLContants.FILE_M_CTL, XMLContants.M_CTL006);
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
		logger.info("MCtlDaoImpl.getVoCount 終了。"); 
		return iCount;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param dataVo
	 * @return boolean
	 * @throws DaoException
	 */
	public boolean isDoubleKey(MCtlVo dataVo) throws DaoException{
		// TODO Auto-generated method stub
		logger.info("MCtlDaoImpl.isDoubleKey 開始。");
		if(getByKey(dataVo) == null) {
		    return false;
		}
		logger.info("MCtlDaoImpl.isDoubleKey 終了。");
		return true;
	}

	/**
	 * <DL>
	 * <DT>コンストラクター記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * @return String
	 * @throws DaoException
	 */
	public String getLatestCode() throws DaoException {
		// TODO Auto-generated method stub
		logger.info("MCtlDaoImpl.getLatestCode 開始。");
		String strSQL = getSQL(XMLContants.FILE_M_CTL, XMLContants.M_CTL007);
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
    	logger.info("MCtlDaoImpl.getLatestCode 終了。");
		return strLatestCode;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return int
	 * @throws DaoException
	 */
	public List<MCtlVo> exportEXCEL() throws DaoException{
		// TODO Auto-generated method stub		 
		logger.info("MCtlDaoImpl.exportEXCEL 開始。");
		String strSQL = getSQL(XMLContants.FILE_M_CTL, XMLContants.M_CTL009);
		MCtlVo dataVo = null;
		DBConnection con = null;
		List<MCtlVo> lstMCtlVo = new ArrayList<MCtlVo>();
		try {
			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);;
			ResultSet rs = preStmt.executeQuery();
			while (rs.next()) {
				dataVo = new MCtlVo();
				dataVo.getFromSQLServerResultSet(rs);
				lstMCtlVo.add(dataVo);
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
		logger.info("MCtlDaoImpl.exportEXCEL 終了。");		
		return lstMCtlVo;
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param String userid, String cKey, int iMtnFlg
	 * @return MCtlVo if object exist with key else return NULL
	 * @throws DaoException
	 */
	public MCtlVo getByKey(String userid, String cKey, int iMtnFlg) throws DaoException{
		// TODO Auto-generated method stub
		logger.info("MCtlDaoImpl.getByKey 開始。");
		String strSQL = getSQL(XMLContants.FILE_M_CTL, XMLContants.M_CTL002);
		strSQL += " AND M.MTN_FLG = ? ";	
		MCtlVo outVo = null;
		DBConnection con = null;
		try {
			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			resetParameter(preStmt);
			
			//Where condition
			setParameter(userid, Types.VARCHAR, preStmt);
			setParameter(cKey, Types.VARCHAR, preStmt);
			setParameter(iMtnFlg, Types.INTEGER, preStmt);

			ResultSet rs = preStmt.executeQuery();
			if (rs.next()) {
				outVo = new MCtlVo();
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
		logger.info("MCtlDaoImpl.getByKey 終了。");
		return outVo;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>	
	* @param String userid, int iMtnFlg
	* @return List MCtlVo if exist with key else return list empty
	* @throws BizException
	*/
	public List<MCtlVo> getListVo(String strUserid, int iMtnFlg) throws DaoException 
	{
		// TODO Auto-generated method stub
		logger.info("MCtlDaoImpl.getListVo 開始。");
		String strSQL = getSQL(XMLContants.FILE_M_CTL, XMLContants.M_CTL001);
		DBConnection con = null;
		List<MCtlVo> lstVo = new ArrayList<MCtlVo>();
		MCtlVo dataVo = null;

		String strWhere = " WHERE 1=1 " ;
		strWhere += " AND M.USERID = ? ";
		strWhere += " AND M.MTN_FLG = ? ";		
		String strOrderBy = " ORDER BY M.C_KEY";
		
		try {
			con = DBConnectionManager.getConnection();			
			strSQL += strWhere + strOrderBy;			
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			resetParameter(preStmt);
			setParameter(strUserid, Types.VARCHAR, preStmt);
			setParameter(iMtnFlg, Types.INTEGER, preStmt);
			ResultSet rs = null;
			rs = preStmt.executeQuery();
			while (rs.next()) 
			{
				dataVo = new MCtlVo();
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
		logger.info("MCtlDaoImpl.getListVo 終了。");		
		return lstVo;	
	}
	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>	
	* @param String userid, String strCKey, int iMtnFlg
	* @return List MCtlVo if exist with key else return list empty
	* @throws BizException
	*/
	public List<MCtlVo> getListVo(String strUserid, String strCKey, int iMtnFlg) throws DaoException 
	{
		// TODO Auto-generated method stub
		logger.info("MCtlDaoImpl.getListVo 開始。");
		String strSQL = getSQL(XMLContants.FILE_M_CTL, XMLContants.M_CTL001);
		DBConnection con = null;
		List<MCtlVo> lstVo = new ArrayList<MCtlVo>();
		MCtlVo dataVo = null;
		
		String strWhere = " WHERE 1=1 ";
		strWhere += " AND M.USERID = ? ";
		strWhere += " AND M.C_KEY = ? " ; 
		strWhere += " AND M.MTN_FLG = ? ";		
		String strOrderBy = " ORDER BY M.C_KEY";
				
		try {
			con = DBConnectionManager.getConnection();			
			strSQL += strWhere + strOrderBy;			
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			resetParameter(preStmt);
			setParameter(strUserid, Types.VARCHAR, preStmt);
			setParameter(strCKey, Types.VARCHAR, preStmt);
			setParameter(iMtnFlg, Types.INTEGER, preStmt);
			
			ResultSet rs = null;
			rs = preStmt.executeQuery();
			while (rs.next()) 
			{
				dataVo = new MCtlVo();
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
		logger.info("MCtlDaoImpl.getListVo 終了。");		
		return lstVo;	
	}
	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>	
	* @param String userid, String strCKey, int iMtnFlg, int iCntFlg
	* @return List MCtlVo if exist with key else return list empty
	* @throws BizException
	*/
	public List<MCtlVo> getListVo(String strUserid, String strCKey, int iMtnFlg, int iCntFlg) throws DaoException 
	{
		// TODO Auto-generated method stub
		logger.info("MCtlDaoImpl.getListVo 開始。");
		String strSQL = getSQL(XMLContants.FILE_M_CTL, XMLContants.M_CTL001);
		DBConnection con = null;
		List<MCtlVo> lstVo = new ArrayList<MCtlVo>();
		MCtlVo dataVo = null;
				
		String strWhere = " WHERE 1=1 ";
		strWhere += " AND M.USERID = ? ";
		strWhere += " AND M.C_KEY = ? ";
		strWhere += " AND M.MTN_FLG = ? ";
		strWhere += " AND M.CNT_FLG = ? ";
		String strOrderBy = " ORDER BY M.C_KEY";
		
		try {
			con = DBConnectionManager.getConnection();			
			strSQL += strWhere + strOrderBy;			
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			resetParameter(preStmt);
			setParameter(strUserid, Types.VARCHAR, preStmt);
			setParameter(strCKey, Types.VARCHAR, preStmt);
			setParameter(iMtnFlg, Types.INTEGER, preStmt);
			setParameter(iCntFlg, Types.INTEGER, preStmt);
			
			ResultSet rs = null;
			rs = preStmt.executeQuery();
			while (rs.next()) 
			{
				dataVo = new MCtlVo();
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
		logger.info("MCtlDaoImpl.getListVo 終了。");		
		return lstVo;	
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return
	 * @throws BizException
	 */
	public Map<String, MCtlVo> getMapMCtlVo() throws DaoException {
		// TODO Auto-generated method stub
		logger.info("MCtlDaoImpl.getMapMCtlVo 開始。");		
		String strSQL = getSQL(XMLContants.FILE_M_CTL, XMLContants.M_CTL001);
		DBConnection con = null;
		Map<String, MCtlVo> mapMCtl = new HashMap<String, MCtlVo>();
		MCtlVo dataVo = null;
		try {
			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			ResultSet rs = null;
			rs = preStmt.executeQuery();
			while (rs.next()) {
				dataVo = new MCtlVo();
				dataVo.getFromSQLServerResultSet(rs);
				mapMCtl.put(StringUtils.nulToString(rs.getString("USERID")) + StringUtils.nulToString(rs.getString("C_KEY")), dataVo);
			} 
		} catch (SQLException e) {
			logger.error(e.getStackTrace());
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
		logger.info("MCtlDaoImpl.getMapMCtlVo 終了。");		
		return mapMCtl;	
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return
	 * @throws BizException
	 */
	public boolean fillDataFromUseridToUserid(String strFromUserid, String strToUserid, int iMtnFlg) throws DaoException {
		// TODO Auto-generated method stub
		logger.info("MCtlDaoImpl.fillDataFromUseridToUserid 開始。");		
		String strSQL = getSQL(XMLContants.FILE_M_CTL, XMLContants.M_CTL011);
		DBConnection con = null;
		boolean isSuccess = true;
		try {
			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			int i=1;
			preStmt.setString(i++, StringUtils.emptyIfNull(strToUserid));
			preStmt.setString(i++, StringUtils.emptyIfNull(strFromUserid));
			preStmt.setString(i++, StringUtils.emptyIfNull(strToUserid));
			preStmt.setInt(i++, iMtnFlg);			
			preStmt.executeQuery();			 
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
		logger.info("MCtlDaoImpl.fillDataFromUseridToUserid 終了。");		
		return isSuccess;		
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return
	 * @throws BizException
	 */
	public boolean deleteDataByUserid(String strUserid, int iMtnFlg) throws DaoException {
		// TODO Auto-generated method stub
		logger.info("MCtlDaoImpl.deleteDataByUserid 開始。");		
		String strSQL = getSQL(XMLContants.FILE_M_CTL, XMLContants.M_CTL012);
		DBConnection con = null;
		boolean isSuccess = true;
		try {
			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			int i=1;
			preStmt.setString(i++, StringUtils.emptyIfNull(strUserid));
			preStmt.setInt(i++, iMtnFlg);			
			preStmt.executeQuery();			 
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
		logger.info("MCtlDaoImpl.deleteDataByUserid 終了。");		
		return isSuccess;
	}
}
