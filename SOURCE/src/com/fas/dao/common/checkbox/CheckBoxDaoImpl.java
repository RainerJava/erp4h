/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：CheckBoxDaoImpl.java
*
*     記述				：
*     
*     作成日			：2010/03/03   
*
*     作成者			：PC14
*
*     備考				：
*
**************************************************************************************/
package com.fas.dao.common.checkbox;

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
import com.fas.vo.base.CheckBoxVo;

/**
 * @author PC14
 *
 */
public class CheckBoxDaoImpl extends BaseDao implements CheckBoxDao {
	
	/** */
	static Logger logger = Logger.getLogger(CheckBoxDaoImpl.class);
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public CheckBoxDaoImpl() {
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.dao.checkbox.CheckBox#getLstINSP()
	 */
	public List<CheckBoxVo> getLstINSP(String isppCode) throws DaoException {
		
		logger.info("CheckBoxDaoImpl.getLstSect 開始。");
		
		DBConnection con = null;
		CheckBoxVo dataVo = null;
		List<CheckBoxVo> lstData = new ArrayList<CheckBoxVo>();
		String strSQL = getSQL(XMLContants.FILE_M_CHECKBOX, XMLContants.M_CHECKBOX001);
		
		try {

			con = DBConnectionManager.getConnection();
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			preStmt.setString(1, StringUtils.nulToString(isppCode));
			ResultSet rs = preStmt.executeQuery();

			while (rs.next()) {
				dataVo = new CheckBoxVo();
				dataVo.setCode(StringUtils.emptyIfNull(rs.getString("INSP2_CODE")).trim());
				dataVo.setValue1(StringUtils.emptyIfNull(rs.getString("INSP2_NAME")).trim());
				dataVo.setValue2(StringUtils.emptyIfNull(rs.getString("INSPP_CODE")).trim());
				dataVo.setValue3(StringUtils.emptyIfNull(rs.getString("INSP1_CODE")).trim());
				lstData.add(dataVo);
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
		
		logger.info("CheckBoxDaoImpl.getLstSect 終了。");
		
		return lstData;
	}
	/* (non-Javadoc)
	 * @see com.pipe.dao.checkbox.CheckBox#getLstFRE()
	 */
	public List<CheckBoxVo> getLstFRE() throws DaoException {
		
		logger.info("CheckBoxDaoImpl.getLstFRE 開始。");
		
		DBConnection con = null;
		CheckBoxVo dataVo = null;
		List<CheckBoxVo> lstData = new ArrayList<CheckBoxVo>();
		String strSQL = getSQL(XMLContants.FILE_M_CHECKBOX, XMLContants.M_CHECKBOX002);
		
		try {
			con = DBConnectionManager.getConnection();
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			ResultSet rs = preStmt.executeQuery();			
			while (rs.next()) {
				dataVo = new CheckBoxVo();
				dataVo.setCode(StringUtils.emptyIfNull(rs.getString("CODE")).trim());
				dataVo.setValue1(StringUtils.emptyIfNull(rs.getString("NAME")).trim());
				dataVo.setValue2(StringUtils.emptyIfNull(rs.getString("RNAME")).trim());
				dataVo.setValue3(StringUtils.emptyIfNull(rs.getString("CLS_CODE")).trim());
				lstData.add(dataVo);
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
		
		logger.info("CheckBoxDaoImpl.getLstFRE 終了。");
		
		return lstData;
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.dao.checkbox.CheckBox#getLstINSP2()
	 */
	public List<CheckBoxVo> getLstINSP2() throws DaoException {
		
		logger.info("CheckBoxDaoImpl.getLstINSP2 開始。");
		
		DBConnection con = null;
		CheckBoxVo dataVo = null;
		List<CheckBoxVo> lstData = new ArrayList<CheckBoxVo>();
		String strSQL = getSQL(XMLContants.FILE_M_CHECKBOX, XMLContants.M_CHECKBOX003);
		
		try {
			con = DBConnectionManager.getConnection();
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			ResultSet rs = preStmt.executeQuery();			
			while (rs.next()) {
				dataVo = new CheckBoxVo();
				dataVo.setCode(StringUtils.emptyIfNull(rs.getString("INSP2_CODE")).trim());
				dataVo.setValue1(StringUtils.emptyIfNull(rs.getString("INSP2_NAME")).trim());
				dataVo.setValue2(StringUtils.emptyIfNull(rs.getString("INSP2_RNAME")).trim());
				dataVo.setValue3(StringUtils.emptyIfNull(rs.getString("INSP1_CODE")).trim());
				lstData.add(dataVo);
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
		
		logger.info("CheckBoxDaoImpl.getLstINSP2 終了。");
		
		return lstData;
	}

	/* (non-Javadoc)
	 * @see com.pipe.dao.checkbox.CheckBoxDao#getLstState()
	 */
	public List<CheckBoxVo> getLstState() throws DaoException {
		
		logger.info("CheckBoxDaoImpl.getLstState 開始。");
		
		DBConnection con = null;
		CheckBoxVo dataVo = null;
		List<CheckBoxVo> lstData = new ArrayList<CheckBoxVo>();
		String strSQL = getSQL(XMLContants.FILE_M_CHECKBOX, XMLContants.M_CHK_COLOR002);
		
		try {
			con = DBConnectionManager.getConnection();
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			ResultSet rs = preStmt.executeQuery();			
			while (rs.next()) {
				dataVo = new CheckBoxVo();
				dataVo.setCode(StringUtils.emptyIfNull(rs.getString("COLOR_CODE")).trim());
				dataVo.setValue1(StringUtils.emptyIfNull(rs.getString("COLOR_NAME")).trim());
				dataVo.setValue2(StringUtils.emptyIfNull(rs.getString("COLOR_RNAME")).trim());
				dataVo.setValue3(StringUtils.emptyIfNull(rs.getString("COLORCLS_CODE")).trim());
				lstData.add(dataVo);
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
		
		logger.info("CheckBoxDaoImpl.getLstState 終了。");
		
		return lstData;
	}

	/* (non-Javadoc)
	 * @see com.pipe.dao.checkbox.CheckBoxDao#getLstState()
	 */
	public List<CheckBoxVo> getLstPassType() throws DaoException {
		
		logger.info("CheckBoxDaoImpl.getLstPassType 開始。");
		
		DBConnection con = null;
		CheckBoxVo dataVo = null;
		List<CheckBoxVo> lstData = new ArrayList<CheckBoxVo>();
		String strSQL = getSQL(XMLContants.FILE_M_CHECKBOX, XMLContants.M_CHK_COLOR003);
		
		try {
			con = DBConnectionManager.getConnection();
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			ResultSet rs = preStmt.executeQuery();			
			while (rs.next()) {
				dataVo = new CheckBoxVo();
				dataVo.setCode(StringUtils.emptyIfNull(rs.getString("COLOR_CODE")).trim());
				dataVo.setValue1(StringUtils.emptyIfNull(rs.getString("COLOR_NAME")).trim());
				dataVo.setValue2(StringUtils.emptyIfNull(rs.getString("COLOR_RNAME")).trim());
				dataVo.setValue3(StringUtils.emptyIfNull(rs.getString("COLORCLS_CODE")).trim());
				lstData.add(dataVo);
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
		
		logger.info("CheckBoxDaoImpl.getLstPassType 終了。");
		
		return lstData;
	}
	
}
