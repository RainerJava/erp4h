/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：MenuDaoImpl.java
*
*     記述				：
*     
*     作成日			：2010/03/04   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/
package com.fas.dao.system.menu;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.fas.common.DBConnection;
import com.fas.common.DBConnectionManager;
import com.fas.common.constants.XMLContants;
import com.fas.common.exception.DaoException;
import com.fas.common.utils.StringUtils;
import com.fas.dao.base.BaseDao;
import com.fas.vo.menuexe.MenuExeVo;
import com.fas.vo.user.LoginUser;

/**
 * @author PC13
 *
 */
public class MenuDaoImpl extends BaseDao implements MenuDao {

	/** */
	static Logger logger = Logger.getLogger(MenuDaoImpl.class);
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public MenuDaoImpl() {
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.dao.menu.MenuDao#getLstMenuExeVoWithPermis(java.lang.String, com.pipe.vo.user.LoginUser)
	 */
	public List<MenuExeVo> getLstMenuExeVoWithPermis(String strMenuGrp, LoginUser userInfor) throws DaoException {
		
		logger.info("MenuDaoImpl.getLstMenuExeVoWithPermis 開始。");
		
		List<MenuExeVo> lstArr = new ArrayList<MenuExeVo>();
		DBConnection con = null;
		String strSQL = getSQL(XMLContants.FILE_M_MENU, XMLContants.M_MENU002);
		MenuExeVo menuExeVo = null;
		
		try {
			
			con = DBConnectionManager.getConnection();
			strSQL = strSQL.replaceAll("DIV_TAG", " <> ");
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			preStmt.setString(1, strMenuGrp);
			preStmt.setString(2, userInfor.getUserUser());
			ResultSet rs = preStmt.executeQuery();

			while (rs.next()) {
				
				menuExeVo = new MenuExeVo();
				menuExeVo.setMenugrpCode(StringUtils.emptyIfNull(rs.getString("MENUGRP_CODE")));
				menuExeVo.setMenuexeCode(StringUtils.emptyIfNull(rs.getString("MENUEXE_CODE")));
				menuExeVo.setMenuexeName(StringUtils.emptyIfNull(rs.getString("MENUEXE_NAME")));
				menuExeVo.setFunType(StringUtils.emptyIfNull(rs.getString("FUN_TYPE")));
				menuExeVo.setClassName(StringUtils.emptyIfNull(rs.getString("CLASS_NAME")));
				menuExeVo.setPathName(StringUtils.emptyIfNull(rs.getString("PATH_NAME")));
				menuExeVo.setMenuexeSeq(StringUtils.emptyIfNull(rs.getString("MENUEXE_SEQ")));
				
				lstArr.add(menuExeVo);
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
		
		logger.info("MenuDaoImpl.getLstMenuExeVoWithPermis 終了。");
		
		return lstArr;
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.dao.menu.MenuDao#getLstMyMenuExe(com.pipe.vo.user.LoginUser)
	 */
	public List<MenuExeVo> getLstMyMenuExe(LoginUser userInfor) throws DaoException {
		
		logger.info("MenuDaoImpl.getLstMyMenuExe 開始。");
		
		List<MenuExeVo> lstArr = new ArrayList<MenuExeVo>();
		DBConnection con = null;
		String strSQL = getSQL(XMLContants.FILE_M_MENU, XMLContants.M_MENU003);
		MenuExeVo menuExeVo = null;
		
		try {
			
			con = DBConnectionManager.getConnection();
			strSQL = strSQL.replaceAll("DIV_TAG", " <> ");
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			preStmt.setString(1, userInfor.getUserId());
			ResultSet rs = preStmt.executeQuery();

			while (rs.next()) {
				
				menuExeVo = new MenuExeVo();
				menuExeVo.setMenugrpCode(StringUtils.emptyIfNull(rs.getString("MENUGRP_CODE")));
				menuExeVo.setMenuexeCode(StringUtils.emptyIfNull(rs.getString("MENUEXE_CODE")));
				menuExeVo.setMenuexeName(StringUtils.emptyIfNull(rs.getString("MENUEXE_NAME")));
				menuExeVo.setMenuexeHelp(StringUtils.emptyIfNull(rs.getString("MENUEXE_HELP")));
				menuExeVo.setFunType(StringUtils.emptyIfNull(rs.getString("FUN_TYPE")));
				menuExeVo.setClassName(StringUtils.emptyIfNull(rs.getString("CLASS_NAME")));
				menuExeVo.setPathName(StringUtils.emptyIfNull(rs.getString("PATH_NAME")));
				menuExeVo.setMenuexeSeq(StringUtils.emptyIfNull(rs.getString("MENUEXE_SEQ")));
				
				lstArr.add(menuExeVo);
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
		
		logger.info("MenuDaoImpl.getLstMyMenuExe 終了。");
		
		return lstArr;
	}

	/* (non-Javadoc)
	 * @see com.pipe.dao.menu.MenuDao#createMyMenu(com.pipe.vo.user.LoginUser, java.util.List)
	 */
	public List createMyMenu(LoginUser userInfor, List lstData) throws DaoException {
		
		logger.info("MenuDaoImpl.createMyMenu 開始。");
		
		List<MenuExeVo> lstArr = new ArrayList<MenuExeVo>();
		DBConnection con = null;
		String strSQLInsert = getSQL(XMLContants.FILE_M_MENU, XMLContants.M_MENU006);
		String strSQLDelete = getSQL(XMLContants.FILE_M_MENU, XMLContants.M_MENU005);
		
		try {
			
			con = DBConnectionManager.getConnection();
			logger.info(strSQLDelete);
			PreparedStatement preDelStmt = con.prepareStatement(strSQLDelete);
			preDelStmt.setString(1, userInfor.getUserId());
			preDelStmt.executeUpdate();
			
			PreparedStatement preInsert = con.prepareStatement(strSQLInsert);
			
			for (int i = 0; i < lstData.size(); i++) {
				
				MenuExeVo menuExeVo = (MenuExeVo) lstData.get(i);
				menuExeVo.setMenuexeSeq(i + "");
				menuExeVo.setUserId(userInfor.getUserId());
				lstArr.add(menuExeVo);
				
				preInsert.setString(1, menuExeVo.getUserId());
				preInsert.setString(2, menuExeVo.getMenugrpCode());
				preInsert.setString(3, menuExeVo.getMenuexeCode());
				preInsert.setString(4, menuExeVo.getMenuexeName());
				preInsert.setString(5, menuExeVo.getMenuexeHelp());
				preInsert.setString(6, menuExeVo.getFunType());
				preInsert.setString(7, menuExeVo.getClassName());
				preInsert.setString(8, menuExeVo.getPathName());
				preInsert.setString(9, menuExeVo.getMenuexeSeq());
				preInsert.addBatch();
			}
			preInsert.executeBatch();
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
		
		logger.info("MenuDaoImpl.createMyMenu 終了。");
		
		return lstArr;
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.dao.menu.MenuDao#getLstMyMenuExeG(com.pipe.vo.user.LoginUser)
	 */
	public List<MenuExeVo> getLstMyMenuExeG(LoginUser userInfor) throws DaoException {
		
		logger.info("MenuDaoImpl.getLstMyMenuExeG 開始。");
		
		List<MenuExeVo> lstArr = new ArrayList<MenuExeVo>();
		DBConnection con = null;
		String strSQL = getSQL(XMLContants.FILE_M_MENU, XMLContants.M_MENU004);
		MenuExeVo menuExeVo = null;
		
		try {
			
			con = DBConnectionManager.getConnection();
			strSQL = strSQL.replaceAll("DIV_TAG", " <> ");
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			preStmt.setString(1, userInfor.getUserId());
			ResultSet rs = preStmt.executeQuery();

			while (rs.next()) {
				
				menuExeVo = new MenuExeVo();
				menuExeVo.setMenugrpCode(StringUtils.emptyIfNull(rs.getString("MENUGRP_CODE")));
				menuExeVo.setMenuexeCode(StringUtils.emptyIfNull(rs.getString("MENUEXE_CODE")));
				menuExeVo.setMenuexeName(StringUtils.emptyIfNull(rs.getString("MENUEXE_NAME")));
				menuExeVo.setMenuexeHelp(StringUtils.emptyIfNull(rs.getString("MENUEXE_HELP")));
				menuExeVo.setFunType(StringUtils.emptyIfNull(rs.getString("FUN_TYPE")));
				menuExeVo.setClassName(StringUtils.emptyIfNull(rs.getString("CLASS_NAME")));
				menuExeVo.setPathName(StringUtils.emptyIfNull(rs.getString("PATH_NAME")));
				menuExeVo.setMenuexeSeq(StringUtils.emptyIfNull(rs.getString("MENUEXE_SEQ")));
				
				lstArr.add(menuExeVo);
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
		
		logger.info("MenuDaoImpl.getLstMyMenuExeG 終了。");
		
		return lstArr;
	}

	
	/* (non-Javadoc)
	 * @see com.pipe.dao.menu.MenuDao#createMyMenu(com.pipe.vo.user.LoginUser, java.util.List)
	 */
	public List<MenuExeVo> createMyMenuG(LoginUser userInfor, List lstData) throws DaoException {
		
		logger.info("MenuDaoImpl.createMyMenuG 開始。");
		
		List<MenuExeVo> lstArr = new ArrayList<MenuExeVo>();
		DBConnection con = null;
		String strSQLDelete = getSQL(XMLContants.FILE_M_MENU, XMLContants.M_MENU007);
		String strSQLInsert = getSQL(XMLContants.FILE_M_MENU, XMLContants.M_MENU008);
		
		try {
			
			con = DBConnectionManager.getConnection();
			logger.info(strSQLDelete);
			PreparedStatement preDelStmt = con.prepareStatement(strSQLDelete);
			preDelStmt.setString(1, userInfor.getUserId());
			preDelStmt.executeUpdate();
			
			PreparedStatement preInsert = con.prepareStatement(strSQLInsert);
			
			for (int i = 0; i < lstData.size(); i++) {
				
				MenuExeVo menuExeVo = (MenuExeVo) lstData.get(i);
				menuExeVo.setMenuexeSeq(i + "");
				menuExeVo.setUserId(userInfor.getUserId());
				lstArr.add(menuExeVo);
				
				preInsert.setString(1, menuExeVo.getUserId());
				preInsert.setString(2, menuExeVo.getMenugrpCode());
				preInsert.setString(3, menuExeVo.getMenuexeCode());
				preInsert.setString(4, menuExeVo.getMenuexeName());
				preInsert.setString(5, menuExeVo.getMenuexeHelp());
				preInsert.setString(6, menuExeVo.getFunType());
				preInsert.setString(7, menuExeVo.getClassName());
				preInsert.setString(8, menuExeVo.getPathName());
				preInsert.setString(9, menuExeVo.getMenuexeSeq());
				preInsert.addBatch();
			}
			preInsert.executeBatch();
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
		
		logger.info("MenuDaoImpl.createMyMenuG 終了。");
		
		return lstArr;
	}
}
