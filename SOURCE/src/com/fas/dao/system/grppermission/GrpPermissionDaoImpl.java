/************************************************************************************
*
*	会社名		： 林兼コンピューター株式会社
*
*	プロジェクト名	： fas
*
*	ファイル名		： MEmpDao.java
*
*	記述			：
*
*	作成日		：  2011/08/26  11:55:02 午前
*
*	作成者		： LENOVO-F23A3B72
*
*	備考			：
*
*************************************************************************************/

package com.fas.dao.system.grppermission;

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
import com.fas.vo.menugrp.MenuGrpVo;
import com.fas.vo.usergrp.UserGrpVo;

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
public class GrpPermissionDaoImpl extends BaseDao implements GrpPermissionDao {

	/** */
	static Logger logger = Logger.getLogger(GrpPermissionDaoImpl.class);
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 * </DL>
	 */
	public GrpPermissionDaoImpl() {
	}

	/* (non-Javadoc)
	 * @see com.pipe.dao.grppermission.GrpPermissionDao#createMenuExePermission(java.util.List)
	 */
	public int[] createMenuExePermission(List<MenuExeVo> lstMenuExeVo) throws DaoException {
		
		logger.info("GrpPermissionDaoImpl.createMenuExePermission 開始。");
		
		DBConnection con = null;
		String strSQL = getSQL(XMLContants.FILE_M_EXECTL2, XMLContants.M_EXECTL2002);
		int[] arrReturn = null;
		
		try {
			
			con = DBConnectionManager.getConnection();
			
			logger.info(strSQL);
			
			PreparedStatement preStmt = con.prepareStatement(strSQL);

			for (MenuExeVo menuExeVo : lstMenuExeVo) {
				preStmt.setString(1, menuExeVo.getUserId());
				preStmt.setString(2, menuExeVo.getMenugrpCode());
				preStmt.setString(3, menuExeVo.getMenuexeCode());
				preStmt.setString(4, menuExeVo.getControlType());
				preStmt.setString(5, StringUtils.emptyIfNull(menuExeVo.getAddUser()));
				preStmt.setString(6, StringUtils.emptyIfNull(menuExeVo.getAddPc()));
				preStmt.setInt(7, menuExeVo.getAddDate());
				preStmt.setInt(8, menuExeVo.getAddTime());
				preStmt.setString(9, StringUtils.emptyIfNull(menuExeVo.getLastupUser()));
				preStmt.setString(10, StringUtils.emptyIfNull(menuExeVo.getLastupPc()));
				preStmt.setInt(11, menuExeVo.getLastupDate());
				preStmt.setInt(12, menuExeVo.getLastupTime());				
				
				preStmt.addBatch();
			}
			
			arrReturn = preStmt.executeBatch();
			
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
		
		logger.info("GrpPermissionDaoImpl.createMenuExePermission 終了。");
		
		return arrReturn;
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.dao.grppermission.GrpPermissionDao#updateMenuExePermission(java.util.List)
	 */
	public int[] updateMenuExePermission(List<MenuExeVo> lstMenuExeVo) throws DaoException {
		
		logger.info("GrpPermissionDaoImpl.updateMenuExePermission 開始。");
		
		DBConnection con = null;
		String strSQL = getSQL(XMLContants.FILE_M_EXECTL2, XMLContants.M_EXECTL2003);
		int[] arrReturn = null;
		
		try {
			
			con = DBConnectionManager.getConnection();
			
			logger.info(strSQL);
			
			PreparedStatement preStmt = con.prepareStatement(strSQL);

			for (MenuExeVo menuExeVo : lstMenuExeVo) {
				preStmt.setString(1, menuExeVo.getControlType());
				preStmt.setString(2, StringUtils.emptyIfNull(menuExeVo.getLastupUser()));
				preStmt.setString(3, StringUtils.emptyIfNull(menuExeVo.getLastupPc()));
				preStmt.setInt(4, menuExeVo.getLastupDate());
				preStmt.setInt(5, menuExeVo.getLastupTime());					
				preStmt.setString(6, menuExeVo.getUserId());
				preStmt.setString(7, menuExeVo.getMenugrpCode());
				preStmt.setString(8, menuExeVo.getMenuexeCode());
				preStmt.addBatch();
			}
			
			arrReturn = preStmt.executeBatch();
			
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
		
		logger.info("GrpPermissionDaoImpl.updateMenuExePermission 終了。");
		
		return arrReturn;
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.dao.grppermission.GrpPermissionDao#getLstMenuVoWithPermission(java.lang.String)
	 */
	public List<MenuExeVo> getLstMenuVoWithPermission(String strUserId) throws DaoException {
		
		logger.info("GrpPermissionDaoImpl.getLstMenuVoWithPermission 開始。");
		
		List<MenuExeVo> lstArr = new ArrayList<MenuExeVo>();
		DBConnection con = null;
		String strSQL = getSQL(XMLContants.FILE_M_MENUEXE, XMLContants.M_MENUEXE004);
		MenuExeVo menuExeVo = null;
		String strWhere = "";
		
		try {
			
			con = DBConnectionManager.getConnection();
			
			strWhere = " WHERE M.MENUEXE_NAME <> '' ";

			strSQL = strSQL.replaceAll("TAG_WHERE", strWhere);

			logger.info(strSQL);
			
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			preStmt.setString(1, StringUtils.trimAll(strUserId));
			ResultSet rs = null;
			
			rs = preStmt.executeQuery();

			while (rs.next()) {
				
				menuExeVo = new MenuExeVo();
				menuExeVo.setUserId(StringUtils.emptyIfNull(strUserId));
				menuExeVo.setMenugrpCode(StringUtils.emptyIfNull(rs.getString("MENUGRP_CODE")));
				menuExeVo.setMenuexeCode(StringUtils.emptyIfNull(rs.getString("MENUEXE_CODE")));
				menuExeVo.setMenuexeName(StringUtils.emptyIfNull(rs.getString("MENUEXE_NAME")));
				menuExeVo.setMenuexeHelp(StringUtils.emptyIfNull(rs.getString("MENUEXE_HELP")));
				menuExeVo.setFunType(StringUtils.emptyIfNull(rs.getString("FUN_TYPE")));
				menuExeVo.setClassName(StringUtils.emptyIfNull(rs.getString("CLASS_NAME")));
				menuExeVo.setPathName(StringUtils.emptyIfNull(rs.getString("PATH_NAME")));
				menuExeVo.setMenuexeSeq(StringUtils.emptyIfNull(rs.getString("MENUEXE_SEQ")));
				menuExeVo.setControlType(StringUtils.emptyIfNull(rs.getString("CONTROL_TYPE")));
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
		
		logger.info("GrpPermissionDaoImpl.getLstMenuVoWithPermission 終了。");		
		return lstArr;
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.dao.grppermission.GrpPermissionDao#getLstMenuGrpVoWithPermission(java.lang.String)
	 */
	public List<MenuGrpVo> getLstMenuGrpVoWithPermission(String strUserId) throws DaoException {
		
		logger.info("GrpPermissionDaoImpl.getLstMenuGrpVoWithPermission 開始。");
		
		List<MenuGrpVo> lstArr = new ArrayList<MenuGrpVo>();
		DBConnection con = null;
		String strSQL = getSQL(XMLContants.FILE_M_MENUGRP, XMLContants.M_MENUGRP003);
		MenuGrpVo menuGrpVo = null;
		String strWhere = "";
		
		try {
			
			con = DBConnectionManager.getConnection();
			
			strWhere = " WHERE M.MENUGRP_NAME <> '' AND M.MENUGRP_CODE <> '000' ";
			strSQL = strSQL.replaceAll("TAG_WHERE", strWhere);
			logger.info(strSQL);
			
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			preStmt.setString(1, StringUtils.trimAll(strUserId));
			ResultSet rs = null;
			
			rs = preStmt.executeQuery();

			while (rs.next()) {
				
				menuGrpVo = new MenuGrpVo();
				menuGrpVo.setUserId(StringUtils.emptyIfNull(strUserId));
				menuGrpVo.setMenugrpCode(StringUtils.emptyIfNull(rs.getString("MENUGRP_CODE")));
				menuGrpVo.setMenugrpName(StringUtils.emptyIfNull(rs.getString("MENUGRP_NAME")));
				menuGrpVo.setMenugrpHelp(StringUtils.emptyIfNull(rs.getString("MENUGRP_HELP")));
				menuGrpVo.setMenugrpSeq(StringUtils.emptyIfNull(rs.getString("MENUGRP_SEQ")));
				menuGrpVo.setControlType(StringUtils.emptyIfNull(rs.getString("CONTROL_TYPE")));
				lstArr.add(menuGrpVo);
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
		
		logger.info("GrpPermissionDaoImpl.getLstMenuGrpVoWithPermission 終了。");
		
		return lstArr;
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.dao.grppermission.GrpPermissionDao#createMenuGrpPermission(java.util.List)
	 */
	public int[] createMenuGrpPermission(List<MenuGrpVo> lstMenuGrpVo) throws DaoException {
		
		logger.info("GrpPermissionDaoImpl.createMenuGrpPermission 開始。");
		
		DBConnection con = null;
		String strSQL = getSQL(XMLContants.FILE_M_EXECTL1, XMLContants.M_EXECTL1002);
		int[] arrReturn = null;
		
		try {
			
			con = DBConnectionManager.getConnection();
			
			logger.info(strSQL);
			
			PreparedStatement preStmt = con.prepareStatement(strSQL);

			for (MenuGrpVo menuGrpVo : lstMenuGrpVo) {
				preStmt.setString(1, menuGrpVo.getUserId());
				preStmt.setString(2, menuGrpVo.getMenugrpCode());
				preStmt.setString(3, menuGrpVo.getControlType());
				preStmt.setString(4, StringUtils.emptyIfNull(menuGrpVo.getAddUser()));
				preStmt.setString(5, StringUtils.emptyIfNull(menuGrpVo.getAddPc()));
				preStmt.setInt(6, menuGrpVo.getAddDate());
				preStmt.setInt(7, menuGrpVo.getAddTime());
				preStmt.setString(8, StringUtils.emptyIfNull(menuGrpVo.getLastupUser()));
				preStmt.setString(9, StringUtils.emptyIfNull(menuGrpVo.getLastupPc()));
				preStmt.setInt(10, menuGrpVo.getLastupDate());
				preStmt.setInt(11, menuGrpVo.getLastupTime());				
				
				preStmt.addBatch();
			}
			
			arrReturn = preStmt.executeBatch();
			
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
		
		logger.info("GrpPermissionDaoImpl.createMenuGrpPermission 終了。");
		
		return arrReturn;
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.dao.grppermission.GrpPermissionDao#updateMenuGrpPermission(java.util.List)
	 */
	public int[] updateMenuGrpPermission(List<MenuGrpVo> lstMenuGrpVo) throws DaoException {
		
		logger.info("GrpPermissionDaoImpl.updateMenuGrpPermission 開始。");
		
		DBConnection con = null;
		String strSQL = getSQL(XMLContants.FILE_M_EXECTL1, XMLContants.M_EXECTL1003);
		int[] arrReturn = null;
		
		try {
			
			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);

			for (MenuGrpVo menuGrpVo : lstMenuGrpVo) {
				preStmt.setString(1, menuGrpVo.getControlType());
				preStmt.setString(2, StringUtils.emptyIfNull(menuGrpVo.getLastupUser()));
				preStmt.setString(3, StringUtils.emptyIfNull(menuGrpVo.getLastupPc()));
				preStmt.setInt(4, menuGrpVo.getLastupDate());
				preStmt.setInt(5, menuGrpVo.getLastupTime());						
				preStmt.setString(6, menuGrpVo.getUserId());
				preStmt.setString(7, menuGrpVo.getMenugrpCode());
				preStmt.addBatch();
			}
			
			arrReturn = preStmt.executeBatch();
			
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
		
		logger.info("GrpPermissionDaoImpl.updateMenuGrpPermission 終了。");
		
		return arrReturn;
	}
		
	/* (non-Javadoc)
	 * @see com.pipe.dao.grppermission.GrpPermissionDao#delPermission(com.pipe.vo.usergrp.UserGrpVo)
	 */
	public boolean delPermission(UserGrpVo userGrpVo) throws DaoException {
		
		logger.info("GrpPermissionDaoImpl.delPermission 開始。");
		
		DBConnection con = null;
		String strSQLDel1 = getSQL(XMLContants.FILE_M_EXECTL1, XMLContants.M_EXECTL1004);
		String strSQLDel2 = getSQL(XMLContants.FILE_M_EXECTL2, XMLContants.M_EXECTL2004);
		
		try {
			
			con = DBConnectionManager.getConnection();
			
			logger.info(strSQLDel1);
			PreparedStatement preStmt = con.prepareStatement(strSQLDel1);
			preStmt.setString(1, userGrpVo.getUserGrpId());
			preStmt.executeUpdate();
			
			logger.info(strSQLDel2);
			preStmt = con.prepareStatement(strSQLDel2);
			preStmt.setString(1, userGrpVo.getUserGrpId());
			preStmt.executeUpdate();
			
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
		
		logger.info("GrpPermissionDaoImpl.delPermission 終了。");
		
		return true;
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.dao.grppermission.GrpPermissionDao#isInUse(com.pipe.vo.usergrp.UserGrpVo)
	 */
	public boolean isInUse(UserGrpVo userGrpVo) throws DaoException {
		
		logger.info("GrpPermissionDaoImpl.isInUse 開始。");
		
		DBConnection con = null;
		String strSQL = getSQL(XMLContants.FILE_M_USERGRP, XMLContants.M_USERGRP007);
		boolean isUse = false;

		try {
			
			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			preStmt.setString(1, userGrpVo.getUserGrpId());
			ResultSet rs = preStmt.executeQuery();
			
			if (rs.next()) {
				if (rs.getInt(1) > 0) {
					isUse = true;
				}
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
		
		logger.info("GrpPermissionDaoImpl.isInUse 終了。");
		
		return isUse;
	}
	
}
