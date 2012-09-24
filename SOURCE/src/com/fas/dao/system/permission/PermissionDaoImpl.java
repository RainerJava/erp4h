/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：PermissionDaoImpl.java
*
*     記述				：
*     
*     作成日			：2010/02/09   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/
package com.fas.dao.system.permission;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.fas.common.DBConnection;
import com.fas.common.DBConnectionManager;
import com.fas.common.constants.XMLContants;
import com.fas.common.exception.DaoException;
import com.fas.common.utils.StringUtils;
import com.fas.dao.base.BaseDao;
import com.fas.vo.exectl1.Exectl1Vo;
import com.fas.vo.exectl2.Exectl2Vo;

/**
 * @author PC13
 *
 */
public class PermissionDaoImpl extends BaseDao implements PermissionDao {
	
	/** */
	static Logger logger = Logger.getLogger(PermissionDaoImpl.class);
	
	
	/* (non-Javadoc)
	 * @see com.pipe.dao.permission.PermissionDao#getMapExect1Vo()
	 */
	public Map<String, Exectl1Vo> getMapExect1Vo() throws DaoException {
		
		logger.info("PermissionDaoImpl.getMapExect1Vo 開始。");
		
		String strSQL = getSQL(XMLContants.FILE_M_EXECTL1, XMLContants.M_EXECTL1001);
		DBConnection con = null;
		Map<String, Exectl1Vo> mapVo = new HashMap<String, Exectl1Vo>();
		Exectl1Vo dataVo = null;

		try {

			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			ResultSet rs = null;
			rs = preStmt.executeQuery();

			while (rs.next()) {
				
				dataVo = new Exectl1Vo();
				dataVo.setUserid(StringUtils.emptyIfNull(rs.getString("USERID")).trim());
				dataVo.setMenugrpCode(StringUtils.emptyIfNull(rs.getString("MENUGRP_CODE")).trim());
				dataVo.setControlType(StringUtils.emptyIfNull(rs.getString("CONTROL_TYPE")).trim());
				dataVo.setAddUser(StringUtils.emptyIfNull(rs.getString("ADD_USER")).trim());
				dataVo.setAddPc(StringUtils.emptyIfNull(rs.getString("ADD_PC")).trim());
				dataVo.setAddDate(rs.getInt("ADD_DATE"));
				dataVo.setAddTime(rs.getInt("ADD_TIME"));
				dataVo.setLastupUser(StringUtils.emptyIfNull(rs.getString("LASTUP_USER")).trim());
				dataVo.setLastupPc(StringUtils.emptyIfNull(rs.getString("LASTUP_PC")).trim());
				dataVo.setLastupDate(rs.getInt("LASTUP_DATE"));
				dataVo.setLastupTime(rs.getInt("LASTUP_TIME"));

				mapVo.put(StringUtils.emptyIfNull(rs.getString("USERID")).trim() + StringUtils.emptyIfNull(rs.getString("MENUGRP_CODE")).trim(), dataVo);
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
		
		logger.info("PermissionDaoImpl.getMapExect1Vo 終了。");
		
		return mapVo;
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.dao.permission.PermissionDao#getMapExect2Vo()
	 */
	public Map<String, Exectl2Vo> getMapExect2Vo() throws DaoException {
		
		logger.info("PermissionDaoImpl.getMapExect2Vo 開始。");
		
		String strSQL = getSQL(XMLContants.FILE_M_EXECTL2, XMLContants.M_EXECTL2001);
		DBConnection con = null;
		Map<String, Exectl2Vo> mapVo = new HashMap<String, Exectl2Vo>();
		Exectl2Vo dataVo = null;

		try {

			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			ResultSet rs = null;
			rs = preStmt.executeQuery();

			while (rs.next()) {
				
				dataVo = new Exectl2Vo();
				
				dataVo.setUserid(StringUtils.emptyIfNull(rs.getString("USERID")).trim());
				dataVo.setMenugrpCode(StringUtils.emptyIfNull(rs.getString("MENUGRP_CODE")).trim());
				dataVo.setMenuexeCode(StringUtils.emptyIfNull(rs.getString("MENUEXE_CODE")).trim());
				dataVo.setControlType(StringUtils.emptyIfNull(rs.getString("CONTROL_TYPE")).trim());
				dataVo.setAddUser(StringUtils.emptyIfNull(rs.getString("ADD_USER")).trim());
				dataVo.setAddPc(StringUtils.emptyIfNull(rs.getString("ADD_PC")).trim());
				dataVo.setAddDate(rs.getInt("ADD_DATE"));
				dataVo.setAddTime(rs.getInt("ADD_TIME"));
				dataVo.setLastupUser(StringUtils.emptyIfNull(rs.getString("LASTUP_USER")).trim());
				dataVo.setLastupPc(StringUtils.emptyIfNull(rs.getString("LASTUP_PC")).trim());
				dataVo.setLastupDate(rs.getInt("LASTUP_DATE"));
				dataVo.setLastupTime(rs.getInt("LASTUP_TIME"));

				mapVo.put(StringUtils.emptyIfNull(rs.getString("USERID")).trim() + StringUtils.emptyIfNull(rs.getString("MENUGRP_CODE")).trim() + StringUtils.emptyIfNull(rs.getString("MENUEXE_CODE")).trim(), dataVo);
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
		
		logger.info("PermissionDaoImpl.getMapExect2Vo 終了。");
		
		return mapVo;
	}
}
