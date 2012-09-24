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
import com.fas.vo.slp.SlpVo;
import com.fas.vo.user.LoginUser;

/**
 * @author PC13
 * 
 */
public class FSlpDaoImpl extends BaseDao implements FSlpDao {

	/** */
	static Logger logger = Logger.getLogger(FSlpDaoImpl.class);

	/**
	 * <DL>
	 * <DT>コンストラクター記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 */
	public FSlpDaoImpl() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.dao.slp.FSlpDao#getSlpNumber(java.lang.String,
	 * java.lang.String, com.pipe.vo.user.LoginUser)
	 */
	public int getSlpNumber(String slpType, String slpDate, LoginUser userInfor)
			throws DaoException {

		logger.info("FSlpDaoImpl.getSlpNumber 開始。");

		String strSQL = getSQL(XMLContants.FILE_F_SLP, XMLContants.F_SLP001);
		int iValue = 0;
		int iMax = 0;
		int iMin = 0;
		DBConnection con = null;

		try {

			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			preStmt.setString(1, StringUtils.nulToString(slpType));
			preStmt.setString(2, StringUtils.nulToString(slpDate));

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

	public List<SlpVo> getLstSlpVo() throws DaoException {
		logger.info("FSlpDaoImpl.getLstSlpVo 開始。");

		String strSQL = getSQL(XMLContants.FILE_F_SLP, XMLContants.F_SLP501);
		DBConnection con = null;
		List<SlpVo> lstVo = new ArrayList<SlpVo>();
		SlpVo dataVo = null;
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
				dataVo = new SlpVo();

				dataVo = new SlpVo();
				dataVo.setSlpType(StringUtils.nullIfEmpty(rs
						.getString("SLP_TYPE")));
				dataVo.setSlpDate(StringUtils.nullIfEmpty(rs
						.getString("SLPDATE")));
				dataVo.setSlpName(StringUtils.nullIfEmpty(rs
						.getString("SLP_NAME")));
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

	public boolean updateSlpVo(SlpVo dataVo) throws DaoException {
		logger.info("FSlpDaoImpl.updateSlpVo 開始。");
		String strSQL = getSQL(XMLContants.FILE_F_SLP, XMLContants.F_SLP502);
		DBConnection con = null;
		boolean isSuccess = true;

		try {
			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);

			preStmt.setString(1, StringUtils.emptyIfNull(dataVo.getSlpName()));
			preStmt.setInt(2, dataVo.getSlpnew());
			preStmt.setInt(3, dataVo.getSlpmin());
			preStmt.setInt(4, dataVo.getSlpmax());
			preStmt.setString(5, StringUtils.emptyIfNull(dataVo.getLastupUser()));
			preStmt.setString(6, StringUtils.emptyIfNull(dataVo.getLastupPc()));
			preStmt.setInt(7, dataVo.getLastupDate());
			preStmt.setInt(8, dataVo.getLastupTime());
			
			preStmt.setString(9, StringUtils.emptyIfNull(dataVo.getSlpType()));
			preStmt.setString(10, StringUtils.emptyIfNull(dataVo.getSlpDate()));
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

		logger.info("FSlpDaoImpl.updateSlpVo 終了。");

		return isSuccess;
	}

	public SlpVo getSlpVo(String SlpType, String SlpDate) throws DaoException {
		logger.info("FSlpDaoImpl.getSlpVo 開始。");

		String strSQL = getSQL(XMLContants.FILE_F_SLP, XMLContants.F_SLP500);
		DBConnection con = null;
		SlpVo dataVo = null;

		try {

			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			preStmt.setString(1, StringUtils.emptyIfNull(SlpType));
			preStmt.setString(2, StringUtils.emptyIfNull(SlpDate));
			ResultSet rs = preStmt.executeQuery();

			if (rs.next()) {
				dataVo = new SlpVo();
				dataVo.setSlpType(StringUtils.nullIfEmpty(rs
						.getString("SLP_TYPE")));
				dataVo.setSlpDate(StringUtils.nullIfEmpty(rs
						.getString("SLPDATE")));
				dataVo.setSlpName(StringUtils.nullIfEmpty(rs
						.getString("SLP_NAME")));
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

}
