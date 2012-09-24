/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：MenuGrpDaoImpl.java
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
package com.fas.dao.system.menugrp;

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
import com.fas.vo.menugrp.MenuGrpVo;
import com.fas.vo.user.LoginUser;

/**
 * @author PC13
 *
 */
public class MenuGrpDaoImpl extends BaseDao implements MenuGrpDao {
	
	/** */
	static Logger logger = Logger.getLogger(MenuGrpDaoImpl.class);
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public MenuGrpDaoImpl() {
	}

	/* (non-Javadoc)
	 * @see com.pipe.dao.menugrp.MenuGrpDao#getLstMenuGrpVo()
	 */
	public List<MenuGrpVo> getLstMenuGrpVo() throws DaoException {
		
		logger.info("MenuGrpDaoImpl.getLstMenuGrpVo 開始。");
		
		List<MenuGrpVo> lstArr = new ArrayList<MenuGrpVo>();
		DBConnection con = null;
		String strSQL = getSQL(XMLContants.FILE_M_MENUGRP, XMLContants.M_MENUGRP001);
		MenuGrpVo menuGrpVo = null;
		
		try {
			
			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			ResultSet rs = null;
			
			rs = preStmt.executeQuery();

			while (rs.next()) {
				menuGrpVo = new MenuGrpVo();
				menuGrpVo.setMenugrpCode(StringUtils.emptyIfNull(rs.getString("MENUGRP_CODE")));
				menuGrpVo.setMenugrpName(StringUtils.emptyIfNull(rs.getString("MENUGRP_NAME")));
				menuGrpVo.setMenugrpHelp(StringUtils.emptyIfNull(rs.getString("MENUGRP_HELP")));
				menuGrpVo.setMenugrpSeq(StringUtils.emptyIfNull(rs.getString("MENUGRP_SEQ")));
				lstArr.add(menuGrpVo);
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
		
		logger.info("MenuGrpDaoImpl.getLstMenuGrpVo 終了。");
		
		return lstArr;
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.dao.menugrp.MenuGrpDao#getLstMenuGrpVoWithPermissionFilter(com.pipe.vo.user.LoginUser)
	 */
	public List<MenuGrpVo> getLstMenuGrpVoWithPermissionFilter(LoginUser userInfor) throws DaoException {
		
		logger.info("MenuGrpDaoImpl.getLstMenuGrpVoWithPermissionFilter 開始。");
		
		List<MenuGrpVo> lstArr = new ArrayList<MenuGrpVo>();
		DBConnection con = null;
		String strSQL = getSQL(XMLContants.FILE_M_MENUGRP, XMLContants.M_MENUGRP002);
		MenuGrpVo menuGrpVo = null;
		
		try {
			
			con = DBConnectionManager.getConnection();
			strSQL = strSQL.replaceAll("DIF_TAG", "<>");
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			preStmt.setString(1, userInfor.getUserUser());
			ResultSet rs = null;
			
			rs = preStmt.executeQuery();

			while (rs.next()) {
				menuGrpVo = new MenuGrpVo();
				menuGrpVo.setMenugrpCode(StringUtils.emptyIfNull(rs.getString("MENUGRP_CODE")));
				menuGrpVo.setMenugrpName(StringUtils.emptyIfNull(rs.getString("MENUGRP_NAME")));
				menuGrpVo.setMenugrpHelp(StringUtils.emptyIfNull(rs.getString("MENUGRP_HELP")));
				menuGrpVo.setMenugrpSeq(StringUtils.emptyIfNull(rs.getString("MENUGRP_SEQ")));
				lstArr.add(menuGrpVo);
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
		
		logger.info("MenuGrpDaoImpl.getLstMenuGrpVoWithPermissionFilter 終了。");
		
		return lstArr;
	}
}
