/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：MUserGrpDaoImpl.java
*
*     記述				：
*     
*     作成日			：2010/03/03   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/
package com.fas.dao.system.usergrp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.fas.common.DBConnection;
import com.fas.common.DBConnectionManager;
import com.fas.common.constants.XMLContants;
import com.fas.common.exception.DaoException;
import com.fas.common.utils.DateUtils;
import com.fas.common.utils.StringUtils;
import com.fas.dao.base.BaseDao;
import com.fas.vo.user.LoginUser;
import com.fas.vo.usergrp.UserGrpVo;

/**
 * @author PC13
 *
 */
public class MUserGrpDaoImpl extends BaseDao implements MUserGrpDao {
	
	/** */
	static Logger logger = Logger.getLogger(MUserGrpDaoImpl.class);
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public MUserGrpDaoImpl() {
	}

	/* (non-Javadoc)
	 * @see com.pipe.dao.usergrp.MUserGrpDao#login(com.pipe.vo.user.LoginUser)
	 */
	public LoginUser login(LoginUser userInfor) throws DaoException {
		
		logger.info("MUserGrpDaoImpl.login 開始。");
		LoginUser loginUserVo = null;
		DBConnection con = null;
		String strSQL = getSQL(XMLContants.FILE_M_USERGRP, XMLContants.M_USERGRP001);
		String strSQLUpdateLastLogin = getSQL(XMLContants.FILE_F_LOGIN, XMLContants.F_LOGIN003);
		String strSQLInsertLastLogin = getSQL(XMLContants.FILE_F_LOGIN, XMLContants.F_LOGIN002);
		
		try {

			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			ResultSet rs = null;
			
			preStmt.setString(1, StringUtils.trimString(userInfor.getUserId()));
			preStmt.setString(2, StringUtils.trimString(userInfor.getPwd()));

			rs = preStmt.executeQuery();

			if (rs.next()) {

				loginUserVo = new LoginUser();
				loginUserVo.setUserId(StringUtils.emptyIfNull(rs.getString("USERGID")).trim());
				loginUserVo.setEmpName(StringUtils.emptyIfNull(rs.getString("USERTXT")).trim());
				loginUserVo.setUserName(StringUtils.emptyIfNull(rs.getString("USERTXT")).trim());
				loginUserVo.setPwd(StringUtils.emptyIfNull(rs.getString("PWD")).trim());
				loginUserVo.setUserUser(StringUtils.emptyIfNull(rs.getString("USERGID")).trim());
				loginUserVo.setLogview_flg(StringUtils.emptyIfNull(rs.getString("LOGVIEW_FLG")).trim());
				loginUserVo.setExelOut(StringUtils.emptyIfNull(rs.getString("EXCELOUT")).trim());
				loginUserVo.setGroup(true);
				
				String strLastLogin = getLastLoginUser(userInfor.getLoginPC());
				if (StringUtils.isValid(strLastLogin)) {
					PreparedStatement preUpdate = con.prepareStatement(strSQLUpdateLastLogin);
					preUpdate.setString(1, userInfor.getUserId());
					preUpdate.setString(2, userInfor.getPwd());
					preUpdate.setInt(3, DateUtils.getCurrentDate());
					preUpdate.setInt(4, DateUtils.getITime());
					preUpdate.setInt(5, DateUtils.getCurrentDate());
					preUpdate.setInt(6, DateUtils.getITime());					
					preUpdate.setString(7, userInfor.getLoginPC());
					preUpdate.executeUpdate();
				} else {
					PreparedStatement preInsert = con.prepareStatement(strSQLInsertLastLogin);
					preInsert.setString(1, userInfor.getLoginPC());
					preInsert.setString(2, userInfor.getUserId());
					preInsert.setString(3, userInfor.getPwd());
					preInsert.setInt(4, DateUtils.getCurrentDate());
					preInsert.setInt(5, DateUtils.getITime());
					preInsert.setInt(6, DateUtils.getCurrentDate());
					preInsert.setInt(7, DateUtils.getITime());
					preInsert.executeUpdate();
				}
				
			} else {
				loginUserVo = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
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
		
		logger.info("MUserGrpDaoImpl.login 終了。");
		
		return loginUserVo;		
	}
	
	/* (non-Javadoc)
	 * @see com.linc.dao.user.UserDao#getLastLoginUser()
	 */
	public String getLastLoginUser(String pcId) throws DaoException {
		
		logger.info("MUserGrpDaoImpl.getLastLoginUser 開始。");
		
		DBConnection con = null;
		String strLastUser = "";
		String strSQL = getSQL(XMLContants.FILE_F_LOGIN, XMLContants.F_LOGIN001);

		try {

			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			ResultSet rs = null;
			preStmt.setString(1, pcId);
			rs = preStmt.executeQuery();
			if (rs.next()) {
				strLastUser = StringUtils.emptyIfNull(rs.getString("USERID"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
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
		
		logger.info("MUserGrpDaoImpl.getLastLoginUser 終了。");
		
		return strLastUser;		
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.dao.usergrp.MUserGrpDao#getUserGrp(java.lang.String)
	 */
	public UserGrpVo getUserGrp(String userGrpId) throws DaoException {
		
		logger.info("MUserGrpDaoImpl.getUserGrp 開始。");
		
		DBConnection con = null;
		UserGrpVo dataVo = null;
		String strSQL = getSQL(XMLContants.FILE_M_USERGRP, XMLContants.M_USERGRP002);

		try {

			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			preStmt.setString(1, userGrpId);
			ResultSet rs = preStmt.executeQuery();
			
			if (rs.next()) {
				dataVo = new UserGrpVo();
				dataVo.setUserGrpId(StringUtils.emptyIfNull(rs.getString("USERGID")));
				dataVo.setPwd(StringUtils.emptyIfNull(rs.getString("PWD")));
				dataVo.setExcelOut(StringUtils.emptyIfNull(rs.getString("EXCELOUT")));
				dataVo.setLogViewflg(StringUtils.emptyIfNull(rs.getString("LOGVIEW_FLG")));
				dataVo.setUserText(StringUtils.emptyIfNull(rs.getString("USERTXT")));
				dataVo.setPwdDate(rs.getInt("PWD_DATE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
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
		
		logger.info("MUserGrpDaoImpl.getUserGrp 終了。");
		
		return dataVo;		
	}
	
	
	/* (non-Javadoc)
	 * @see com.pipe.dao.usergrp.MUserGrpDao#updateUserGrp(java.lang.String)
	 */
	public UserGrpVo updateUserGrp(UserGrpVo userGrpVo) throws DaoException {
		
		logger.info("MUserGrpDaoImpl.updateUserGrp 開始。");
		
		DBConnection con = null;
		String strSQL = getSQL(XMLContants.FILE_M_USERGRP, XMLContants.M_USERGRP005);

		try {

			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			
			preStmt.setString(1, StringUtils.emptyIfNull(userGrpVo.getPwd()));
			preStmt.setString(2, StringUtils.emptyIfNull(userGrpVo.getUserText()));
			preStmt.setString(3, StringUtils.emptyIfNull(userGrpVo.getLogViewflg()));
			preStmt.setString(4, StringUtils.emptyIfNull(userGrpVo.getExcelOut()));
			preStmt.setInt(5, userGrpVo.getPwdDate());
			preStmt.setString(6, StringUtils.emptyIfNull(userGrpVo.getPwd1()));
			preStmt.setString(7, StringUtils.emptyIfNull(userGrpVo.getPwd2()));
			preStmt.setString(8, StringUtils.emptyIfNull(userGrpVo.getPwd3()));			
			preStmt.setString(9, StringUtils.emptyIfNull(userGrpVo.getLastupUser()));
			preStmt.setString(10, StringUtils.emptyIfNull(userGrpVo.getLastupPc()));
			preStmt.setInt(11, userGrpVo.getLastupDate());
			preStmt.setInt(12, userGrpVo.getLastupTime());			
			preStmt.setString(13, StringUtils.emptyIfNull(userGrpVo.getUserGrpId()));
			
			preStmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
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
		
		logger.info("MUserGrpDaoImpl.updateUserGrp 終了。");
		
		return userGrpVo;		
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.dao.usergrp.MUserGrpDao#createUserGrp(com.pipe.vo.usergrp.UserGrpVo)
	 */
	public UserGrpVo createUserGrp(UserGrpVo userGrpVo) throws DaoException {
		
		logger.info("MUserGrpDaoImpl.createUserGrp 開始。");
		
		DBConnection con = null;
		String strSQL = getSQL(XMLContants.FILE_M_USERGRP, XMLContants.M_USERGRP004);

		try {

			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			
			preStmt.setString(1, StringUtils.emptyIfNull(userGrpVo.getUserGrpId()));
			preStmt.setString(2, StringUtils.emptyIfNull(userGrpVo.getPwd()));
			preStmt.setString(3, StringUtils.emptyIfNull(userGrpVo.getUserText()));
			preStmt.setString(4, StringUtils.emptyIfNull(userGrpVo.getLogViewflg()));
			preStmt.setString(5, StringUtils.emptyIfNull(userGrpVo.getExcelOut()));
			preStmt.setInt(6, userGrpVo.getPwdDate());
//			preStmt.setString(8, StringUtils.emptyIfNull(userGrpVo.getPwd1()));
//			preStmt.setString(9, StringUtils.emptyIfNull(userGrpVo.getPwd2()));
//			preStmt.setString(10, StringUtils.emptyIfNull(userGrpVo.getPwd3()));			
			preStmt.setString(7, StringUtils.emptyIfNull(userGrpVo.getAddUser()));
			preStmt.setString(8, StringUtils.emptyIfNull(userGrpVo.getAddPc()));
			preStmt.setInt(9, userGrpVo.getAddDate());
			preStmt.setInt(10, userGrpVo.getAddTime());			
			preStmt.setString(11, StringUtils.emptyIfNull(userGrpVo.getLastupUser()));
			preStmt.setString(12, StringUtils.emptyIfNull(userGrpVo.getLastupPc()));
			preStmt.setInt(13, userGrpVo.getLastupDate());
			preStmt.setInt(14, userGrpVo.getLastupTime());			
			
			preStmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
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
		
		logger.info("MUserGrpDaoImpl.createUserGrp 終了。");
		
		return userGrpVo;		
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.dao.usergrp.MUserGrpDao#delUserGrp(com.pipe.vo.usergrp.UserGrpVo)
	 */
	public boolean delUserGrp(UserGrpVo userGrpVo) throws DaoException {
		
		logger.info("MUserGrpDaoImpl.delUserGrp 開始。");
		
		DBConnection con = null;
		String strSQL = getSQL(XMLContants.FILE_M_USERGRP, XMLContants.M_USERGRP006);

		try {

			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			
			preStmt.setString(1, StringUtils.emptyIfNull(userGrpVo.getUserGrpId()));
			
			preStmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
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
		
		logger.info("MUserGrpDaoImpl.delUserGrp 終了。");
		
		return true;
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.dao.usergrp.MUserGrpDao#getUserGrpCount()
	 */
	public int getUserGrpCount() throws DaoException {
		
		logger.info("MUserGrpDaoImpl.getUserGrpCount 開始。");
		
		DBConnection con = null;
		String strSQL = "SELECT count(*) FROM M_USERGRP";
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
			e.printStackTrace();
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
		
		logger.info("MUserGrpDaoImpl.getUserGrpCount 終了。");
		
		return iCount;
	}
}
