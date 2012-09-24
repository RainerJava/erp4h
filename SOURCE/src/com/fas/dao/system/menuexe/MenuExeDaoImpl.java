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
package com.fas.dao.system.menuexe;

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
public class MenuExeDaoImpl extends BaseDao implements MenuExeDao {

	/** */
	static Logger logger = Logger.getLogger(MenuExeDaoImpl.class);
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public MenuExeDaoImpl() {
	}

	/* (non-Javadoc)
	 * @see com.pipe.dao.menuexe.MenuExeDao#getLstMenuExeVo()
	 */
	public List<MenuExeVo> getLstMenuExeVo(String strGroupCd) throws DaoException {
		
		logger.info("MenuExeDaoImpl.getLstMenuExeVo 開始。");
		
		List<MenuExeVo> lstArr = new ArrayList<MenuExeVo>();
		DBConnection con = null;
		String strSQL = getSQL(XMLContants.FILE_M_MENUEXE, XMLContants.M_MENUEXE001);
		MenuExeVo menuExeVo = null;
		String strWhere = "";
		
		try {
			
			con = DBConnectionManager.getConnection();
			
			if (StringUtils.isValid(strGroupCd)) {
				strWhere = " WHERE M.MENUGRP_CODE = '" + strGroupCd + "'";
			}
			strSQL = strSQL.replaceAll("TAG_WHERE", strWhere);

			logger.info(strSQL);
			
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			ResultSet rs = null;
			
			rs = preStmt.executeQuery();

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
		
		logger.info("MenuExeDaoImpl.getLstMenuExeVo 終了。");
		
		return lstArr;
	}

	public MenuExeVo getMenuExeVoByPathName(String strPathName) throws DaoException
	{
		logger.info("MenuExeDaoImpl.getLstMenuExeVo 開始。");
		
		List<MenuExeVo> lstArr = new ArrayList<MenuExeVo>();
		DBConnection con = null;
		String strSQL = getSQL(XMLContants.FILE_M_MENUEXE, XMLContants.M_MENUEXE005);
		MenuExeVo menuExeVo = null;
		String strWhere = "";
		
		try {
			
			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			int i = 1;
			preStmt.setString(i++, strPathName);
			ResultSet rs = null;
			
			rs = preStmt.executeQuery();
			menuExeVo = new MenuExeVo();
			while (rs.next()) {
				menuExeVo.setMenugrpCode(StringUtils.emptyIfNull(rs.getString("MENUGRP_CODE")));
				menuExeVo.setMenuexeCode(StringUtils.emptyIfNull(rs.getString("MENUEXE_CODE")));
				menuExeVo.setMenuexeName(StringUtils.emptyIfNull(rs.getString("MENUEXE_NAME")));
				menuExeVo.setMenuexeHelp(StringUtils.emptyIfNull(rs.getString("MENUEXE_HELP")));
				menuExeVo.setFunType(StringUtils.emptyIfNull(rs.getString("FUN_TYPE")));
				menuExeVo.setClassName(StringUtils.emptyIfNull(rs.getString("CLASS_NAME")));
				menuExeVo.setPathName(StringUtils.emptyIfNull(rs.getString("PATH_NAME")));
				menuExeVo.setMenuexeSeq(StringUtils.emptyIfNull(rs.getString("MENUEXE_SEQ")));				
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
		
		logger.info("MenuExeDaoImpl.getLstMenuExeVo 終了。");
		
		return menuExeVo;
	}
}
