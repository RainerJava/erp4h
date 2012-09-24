/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：SQLUserDaoImpl.java
*
*     記述				：
*     
*     作成日			：2009/10/06   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/

package com.fas.dao.system.user;

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

/**
 * <DL>
 *   <DT>クラス記述：</DT>
 * 	<DD></DD>
 * <BR>
 *   <DT>変更歴史：</DT>
 * 	<DD>著作者: PC13</DD><BR>
 * 	<DD></DD>
 * </DL>
 */
public class UserDaoImpl extends BaseDao implements UserDao {
	
	/** */
	static Logger logger = Logger.getLogger(UserDaoImpl.class);
		
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public UserDaoImpl() {
	}

	/* (non-Javadoc)
	 * @see com.linc.dao.user.UserDao#login(com.linc.vo.user.LoginUser)
	 */
	public LoginUser login(LoginUser userInfor) throws DaoException {
		
		logger.info("UserDaoImpl.login 開始。");
		LoginUser loginUserVo = null;
		DBConnection con = null;
		String strSQL = getSQL(XMLContants.FILE_M_EMP, XMLContants.M_EMP011);
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
				loginUserVo.setUserId(StringUtils.emptyIfNull(rs.getString("EMP_USER")).trim());
				loginUserVo.setEmpCode(StringUtils.emptyIfNull(rs.getString("EMP_CODE")).trim());
				loginUserVo.setEmpKana(StringUtils.emptyIfNull(rs.getString("EMP_KANA")).trim());
				loginUserVo.setEmpName(StringUtils.emptyIfNull(rs.getString("EMP_NAME")).trim());
				loginUserVo.setUserName(StringUtils.emptyIfNull(rs.getString("EMP_NAME")).trim());
				loginUserVo.setPwd(StringUtils.emptyIfNull(rs.getString("PWD")).trim());
				loginUserVo.setUserUser(StringUtils.emptyIfNull(rs.getString("USER_USER")).trim());
				loginUserVo.setPcid(StringUtils.emptyIfNull(rs.getString("PCID")).trim());
				loginUserVo.setGroup(false);
				
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
		
		logger.info("UserDaoImpl.login 終了。");
		
		return loginUserVo;		
	}
	
	/* (non-Javadoc)
	 * @see com.linc.dao.user.UserDao#getLastLoginUser()
	 */
	public String getLastLoginUser(String pcId) throws DaoException {
		
		logger.info("UserDaoImpl.getLastLoginUser 開始。");
		
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
			logger.error(e.getMessage());
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
		
		logger.info("UserDaoImpl.getLastLoginUser 終了。");
		
		return strLastUser;		
	}

}

