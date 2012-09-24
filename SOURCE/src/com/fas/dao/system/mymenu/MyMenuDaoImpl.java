/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：MenuExeDaoImpl.java
*
*     記述				：
*     
*     作成日			：2010/01/25   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/
package com.fas.dao.system.mymenu;

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

/**
 * @author PC13
 *
 */
public class MyMenuDaoImpl extends BaseDao implements MyMenuDao {

	/** */
	static Logger logger = Logger.getLogger(MyMenuDaoImpl.class);
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public MyMenuDaoImpl() {
	}

	/* (non-Javadoc)
	 * @see com.pipe.dao.menuexe.MenuExeDao#getLstMenuExeVo()
	 */
	public List<MenuExeVo> getLstMyMenuVo(String strUserId) throws DaoException {
		
		logger.info("MyMenuDaoImpl.getLstMyMenuVo 開始。");
		
		List<MenuExeVo> lstArr = new ArrayList<MenuExeVo>();
		DBConnection con = null;
		String strSQL = getSQL(XMLContants.FILE_M_MENUEXE, XMLContants.M_MENUEXE002);
		MenuExeVo menuExeVo = null;
		String strWhere = "";
		
		try {
			
			con = DBConnectionManager.getConnection();
			
			strWhere = " WHERE M.USERID = '" + StringUtils.trimAll(strUserId) + "'";
			strSQL = strSQL.replaceAll("TAG_WHERE", strWhere);

			logger.info(strSQL);
			
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			ResultSet rs = null;
			
			rs = preStmt.executeQuery();

			while (rs.next()) {
				
				menuExeVo = new MenuExeVo();
				menuExeVo.setUserId(StringUtils.emptyIfNull(rs.getString("USERID")));
				menuExeVo.setMenugrpCode(StringUtils.emptyIfNull(rs.getString("MENUGRP_CODE")));
				menuExeVo.setMenuexeCode(StringUtils.emptyIfNull(rs.getString("MENUEXE_CODE")));
				menuExeVo.setMenuexeName(StringUtils.emptyIfNull(rs.getString("MENUEXE_NAME")));
				menuExeVo.setMenuexeHelp(StringUtils.emptyIfNull(rs.getString("MENUEXE_HELP")));
				menuExeVo.setFunType(StringUtils.emptyIfNull(rs.getString("FUN_TYPE")));
				menuExeVo.setClassName(StringUtils.emptyIfNull(rs.getString("CLASS_NAME")));
				menuExeVo.setPathName(StringUtils.emptyIfNull(rs.getString("PATH_NAME")));
				menuExeVo.setMenuexeSeq(StringUtils.emptyIfNull(rs.getString("MENUEXE_SEQ")));
				if ("000".equalsIgnoreCase(menuExeVo.getMenugrpCode()) && "000".equalsIgnoreCase(menuExeVo.getMenuexeCode())
						&& !StringUtils.isValid(menuExeVo.getPathName())) {
					menuExeVo.setMenuexeName("");
				}
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
		
		logger.info("MyMenuDaoImpl.getLstMyMenuVo 終了。");
		
		return lstArr;
	}
	

	/* (non-Javadoc)
	 * @see com.pipe.dao.mymenu.MyMenuDao#getLstMyMenuVoG(java.lang.String)
	 */
	public List<MenuExeVo> getLstMyMenuVoG(String strUserId) throws DaoException {	
	
		logger.info("MyMenuDaoImpl.getLstMyMenuVoG 開始。");
		
		List<MenuExeVo> lstArr = new ArrayList<MenuExeVo>();
		DBConnection con = null;
		String strSQL = getSQL(XMLContants.FILE_M_MENUEXE, XMLContants.M_MENUEXE003);
		MenuExeVo menuExeVo = null;
		String strWhere = "";
		
		try {
			
			con = DBConnectionManager.getConnection();
			
			strWhere = " WHERE M.USERGID = '" + StringUtils.trimAll(strUserId) + "'";
			strSQL = strSQL.replaceAll("TAG_WHERE", strWhere);
	
			logger.info(strSQL);
			
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			ResultSet rs = null;
			
			rs = preStmt.executeQuery();
	
			while (rs.next()) {
				
				menuExeVo = new MenuExeVo();
				menuExeVo.setUserId(StringUtils.emptyIfNull(rs.getString("USERGID")));
				menuExeVo.setMenugrpCode(StringUtils.emptyIfNull(rs.getString("MENUGRP_CODE")));
				menuExeVo.setMenuexeCode(StringUtils.emptyIfNull(rs.getString("MENUEXE_CODE")));
				menuExeVo.setMenuexeName(StringUtils.emptyIfNull(rs.getString("MENUEXE_NAME")));
				menuExeVo.setMenuexeHelp(StringUtils.emptyIfNull(rs.getString("MENUEXE_HELP")));
				menuExeVo.setFunType(StringUtils.emptyIfNull(rs.getString("FUN_TYPE")));
				menuExeVo.setClassName(StringUtils.emptyIfNull(rs.getString("CLASS_NAME")));
				menuExeVo.setPathName(StringUtils.emptyIfNull(rs.getString("PATH_NAME")));
				menuExeVo.setMenuexeSeq(StringUtils.emptyIfNull(rs.getString("MENUEXE_SEQ")));
				if ("000".equalsIgnoreCase(menuExeVo.getMenugrpCode()) && "000".equalsIgnoreCase(menuExeVo.getMenuexeCode())
						&& !StringUtils.isValid(menuExeVo.getPathName())) {
					menuExeVo.setMenuexeName("");
				}
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
		
		logger.info("MyMenuDaoImpl.getLstMyMenuVoG 終了。");
		
		return lstArr;
	}	
}
