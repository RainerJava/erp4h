/** *********************************************************************************
 *     
 *     会社名			：林兼コンピューター株式会社
 *
 *     プロジェクト名	：
 * 
 *     ファイル名		：FSlpDaoImpl.java
 *
 *     記述				：
 *     
 *     作成日			：2010/03/17   
 *
 *     作成者			：PC13
 *
 *     備考				：
 *
 **************************************************************************************/
package com.fas.dao.system.fslp;

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
import com.fas.vo.slp.SlpOdrVo;
import com.fas.vo.user.LoginUser;

/**
 * @author PC13
 * 
 */
public class FSlpOdrDaoImpl extends BaseDao implements FSlpOdrDao {

	/** */
	static Logger logger = Logger.getLogger(FSlpOdrDaoImpl.class);

	/**
	 * <DL>
	 * <DT>コンストラクター記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 */
	public FSlpOdrDaoImpl() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.dao.slp.FSlpDao#getSlpNumber(java.lang.String,
	 * java.lang.String, com.pipe.vo.user.LoginUser)
	 */
	public int getSlpNumber(String strCustCode, String strDrawType, LoginUser userInfor)
			throws DaoException {

		logger.info("FSlpDaoImpl.getSlpNumber 開始。");

		String strSQL = getSQL(XMLContants.FILE_F_SLP, XMLContants.F_SLP506);
		int iValue = 0;
		int iMax = 0;
		int iMin = 0;
		DBConnection con = null;

		try {

			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			int i = 1;
			preStmt.setString(i++, StringUtils.nulToString(strCustCode));
			preStmt.setString(i++, StringUtils.nulToString(strDrawType));

			ResultSet rs = preStmt.executeQuery();

			if (rs.next()) {
				iValue = rs.getInt("SLPNEW");
				iMax = rs.getInt("SLPMAX");
				iMin = rs.getInt("SLPMIN");
			}

			if (iValue < iMax) {
				iValue = iValue + 1;
			} else {
				iValue = iMin;
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

		logger.info("FSlpDaoImpl.getSlpNumber 終了。");

		return iValue;
	}

	public List<SlpOdrVo> getLstSlpVo() throws DaoException {
		
		logger.info("FSlpDaoImpl.getLstSlpVo 開始。");

		String strSQL = getSQL(XMLContants.FILE_F_SLP, XMLContants.F_SLP503);
		DBConnection con = null;
		List<SlpOdrVo> lstVo = new ArrayList<SlpOdrVo>();
		SlpOdrVo dataVo = null;
		String strWhere = "";
		String strOrderBy = "";

		try {

			con = DBConnectionManager.getConnection();

			strSQL += strWhere + strOrderBy;

			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			ResultSet rs = null;
			rs = preStmt.executeQuery();
			while (rs.next()) {
				dataVo = new SlpOdrVo();
				dataVo.setCustCode(StringUtils.nullIfEmpty(rs
						.getString("CUST_CODE")));
				dataVo.setCustName(StringUtils.nullIfEmpty(rs
						.getString("CUSTR_NAME")));
				dataVo.setDrawType(StringUtils.nullIfEmpty(rs
						.getString("DRAW_TYPE")));
				dataVo.setDrawName(StringUtils.nullIfEmpty(rs
						.getString("DRAW_NAME")));
				dataVo.setSlpnew(rs.getInt("SLPNEW"));
				dataVo.setSlpmin(rs.getInt("SLPMIN"));
				dataVo.setSlpmax(rs.getInt("SLPMAX"));
				dataVo.setAddUser(StringUtils.nulToString(rs
								.getString("ADD_USER")));
				dataVo
						.setAddPc(StringUtils.nulToString(rs
								.getString("ADD_PC")));
				dataVo.setAddDate(rs.getInt("ADD_DATE"));
				dataVo.setAddTime(rs.getInt("ADD_TIME"));
				dataVo.setLastupUser(StringUtils.nulToString(rs
						.getString("LASTUP_USER")));
				dataVo.setLastupPc(StringUtils.nulToString(rs
						.getString("LASTUP_PC")));
				dataVo.setLastupDate(rs.getInt("LASTUP_DATE"));
				dataVo.setLastupTime(rs.getInt("LASTUP_TIME"));
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
		logger.info("FSlpDaoImpl.getLstSlpVo 終了。");
		
		return lstVo;
	}

	public boolean updateSlpVo(SlpOdrVo dataVo) throws DaoException {
		logger.info("FSlpOdrDaoImpl.updateSlpVo 開始。");
		String strSQL = getSQL(XMLContants.FILE_F_SLP, XMLContants.F_SLP504);
		DBConnection con = null;
		boolean isSuccess = true;

		try {
			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			
			preStmt.setInt(1, dataVo.getSlpnew());
			preStmt.setInt(2, dataVo.getSlpmin());
			preStmt.setInt(3, dataVo.getSlpmax());
			preStmt.setString(4, StringUtils.emptyIfNull(dataVo.getLastupUser()));
			preStmt.setString(5, StringUtils.emptyIfNull(dataVo.getLastupPc()));
			preStmt.setInt(6, dataVo.getLastupDate());
			preStmt.setInt(7, dataVo.getLastupTime());
			preStmt.setString(8, StringUtils.emptyIfNull(dataVo.getCustCode()));
			preStmt.setString(9, StringUtils.emptyIfNull(dataVo.getDrawType()));
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

		logger.info("FSlpOdrDaoImpl.updateSlpVo 終了。");

		return isSuccess;
	}

	public SlpOdrVo getSlpVo(String custCode, String drawType) throws DaoException {
		logger.info("FSlpDaoImpl.getSlpVo 開始。");

		String strSQL = getSQL(XMLContants.FILE_F_SLP, XMLContants.F_SLP505);
		DBConnection con = null;
		SlpOdrVo dataVo = null;

		try {

			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			preStmt.setString(1, StringUtils.emptyIfNull(custCode));
			preStmt.setString(2, StringUtils.emptyIfNull(drawType));
			ResultSet rs = preStmt.executeQuery();

			if (rs.next()) {
				dataVo = new SlpOdrVo();
				dataVo.setCustCode(StringUtils.nulToString(rs.getString("CUST_CODE")));
				dataVo.setDrawType(StringUtils.nulToString(rs.getString("DRAW_TYPE")));
				dataVo.setSlpnew(rs.getInt("SLPNEW"));
				dataVo.setSlpmin(rs.getInt("SLPMIN"));
				dataVo.setSlpmax(rs.getInt("SLPMAX"));
				dataVo
						.setAddPc(StringUtils.nulToString(rs
								.getString("ADD_PC")));
				dataVo.setAddUser(StringUtils.nulToString(rs
								.getString("ADD_USER")));
				dataVo.setAddDate(rs.getInt("ADD_DATE"));
				dataVo.setAddTime(rs.getInt("ADD_TIME"));
				dataVo.setLastupUser(StringUtils.nulToString(rs
						.getString("LASTUP_USER")));
				dataVo.setLastupPc(StringUtils.nulToString(rs
						.getString("LASTUP_PC")));
				dataVo.setLastupDate(rs.getInt("LASTUP_DATE"));
				dataVo.setLastupTime(rs.getInt("LASTUP_TIME"));

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

		logger.info("FSlpDaoImpl.getSlpVo 終了。");
		return dataVo;
	}
	
	public boolean insertSlpOdrVo(SlpOdrVo dataVo) throws DaoException {
		
		logger.info("FSlpDaoImpl.insertSlpOdrVo 開始。");
		
		String strSQL = getSQL(XMLContants.FILE_F_SLP, XMLContants.F_SLP509);
		DBConnection con = null;
		boolean isSuccess = true;

		try {

			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			
			int i = 1;
			preStmt.setString(i++, StringUtils.emptyIfNull(dataVo.getCustCode()));
			preStmt.setString(i++, StringUtils.emptyIfNull(dataVo.getDrawType()));
			preStmt.setInt(i++, dataVo.getSlpnew());
			preStmt.setInt(i++, dataVo.getSlpmin());
			preStmt.setInt(i++, dataVo.getSlpmax());
			preStmt.setString(i++, StringUtils.emptyIfNull(dataVo.getAddUser()));
			preStmt.setString(i++, StringUtils.emptyIfNull(dataVo.getAddPc()));
			preStmt.setInt(i++, dataVo.getAddDate());
			preStmt.setInt(i++, dataVo.getAddTime());
			preStmt.setString(i++, StringUtils.emptyIfNull(dataVo.getLastupUser()));
			preStmt.setString(i++, StringUtils.emptyIfNull(dataVo.getLastupPc()));
			preStmt.setInt(i++, dataVo.getLastupDate());
			preStmt.setInt(i++, dataVo.getLastupTime());
			
			
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
		
		logger.info("FSlpDaoImpl.insertSlpOdrVo 終了。");
		
		return isSuccess;	
	}
	
	public boolean delSlpByCustCode(String custCode) throws DaoException {
		
		logger.info("FSlpDaoImpl.delSlpByCustCode 開始。");
		
		String strSQL = getSQL(XMLContants.FILE_F_SLP, XMLContants.F_SLP510);
		DBConnection con = null;
		boolean isSuccess = true;

		try {
			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);			
			preStmt.setString(1, StringUtils.emptyIfNull(custCode));			
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
		
		logger.info("FSlpDaoImpl.delSlpByCustCode 終了。");
		
		return isSuccess;					
	}
}
