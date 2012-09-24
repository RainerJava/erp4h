/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：ComboDaoImpl.java
*
*     記述				：
*     
*     作成日			：2010/02/16   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/
package com.fas.dao.common.combo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.fas.common.DBConnection;
import com.fas.common.DBConnectionManager;
import com.fas.common.PermissionPolicy;
import com.fas.common.constants.ApplicationConstants;
import com.fas.common.constants.XMLContants;
import com.fas.common.exception.DaoException;
import com.fas.common.utils.StringUtils;
import com.fas.dao.base.BaseDao;
import com.fas.vo.base.ComboObjectVo;
import com.fas.vo.exectl2.Exectl2Vo;
import com.fas.vo.user.LoginUser;

/**
 * @author PC13
 *
 */
/**
 * @author Admin
 *
 */
public class ComboDaoImpl extends BaseDao implements ComboDao {
	
	/** */
	static Logger logger = Logger.getLogger(ComboDaoImpl.class);
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public ComboDaoImpl() {
	}

	public List<ComboObjectVo> getLstSect() throws DaoException {
		
		logger.info("ComboDaoImpl.getLstSect 開始。");
		
		DBConnection con = null;
		ComboObjectVo dataVo = null;
		List<ComboObjectVo> lstData = new ArrayList<ComboObjectVo>();
		String strSQL = getSQL(XMLContants.FILE_M_COMBO, XMLContants.M_COMBOSECT001);
		logger.info(strSQL);
		dataVo = new ComboObjectVo();
		dataVo.setCode("");
		lstData.add(dataVo);
		
		try {

			con = DBConnectionManager.getConnection();
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			ResultSet rs = preStmt.executeQuery();

			while (rs.next()) {
				dataVo = new ComboObjectVo();
				dataVo.setCode(StringUtils.emptyIfNull(rs.getString("SECT_CODE")).trim());
				dataVo.setValue1(StringUtils.emptyIfNull(rs.getString("SECT_NAME")).trim());
				dataVo.setValue2(StringUtils.emptyIfNull(rs.getString("USE_FLG")).trim());
				dataVo.setValue3(StringUtils.emptyIfNull(rs.getString("SECT_KANA")).trim());
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
		
		logger.info("ComboDaoImpl.getLstSect 終了。");
		
		return lstData;

	}
	
	/* (non-Javadoc)
	 * @
	 */
	public List<ComboObjectVo> getLstLMS() throws DaoException {
		
		logger.info("ComboDaoImpl.getLstLMS 開始。");
		
		DBConnection con = null;
		ComboObjectVo dataVo = null;
		List<ComboObjectVo> lstData = new ArrayList<ComboObjectVo>();
		String strSQL = getSQL(XMLContants.FILE_M_COMBO, XMLContants.V_PRODCLS001);
		logger.info(strSQL);
		dataVo = new ComboObjectVo();
		dataVo.setCode("");
		lstData.add(dataVo);
		try {

			con = DBConnectionManager.getConnection();
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			ResultSet rs = preStmt.executeQuery();
			while (rs.next()) {
				dataVo = new ComboObjectVo();
				dataVo.setCode(StringUtils.emptyIfNull(rs.getString("CODE")).trim());
				dataVo.setValue1(StringUtils.emptyIfNull(rs.getString("NAME")).trim());
				dataVo.setValue2(StringUtils.emptyIfNull(rs.getString("KANA")).trim());
				dataVo.setValue3(StringUtils.emptyIfNull(rs.getString("USEF")).trim());
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
		
		logger.info("ComboDaoImpl.getLstLMS 終了。");
		
		return lstData;

	}
	
	/* (non-Javadoc)
	 * @
	 */
	public List<ComboObjectVo> getLstMenuGrpVo(LoginUser userInfor) throws DaoException {
		
		logger.info("ComboDaoImpl.getLstMenuGrpVo 開始。");
		
		List<ComboObjectVo> lstArr = new ArrayList<ComboObjectVo>();
		DBConnection con = null;
		String strSQL = getSQL(XMLContants.FILE_M_MENUGRP, XMLContants.M_MENUGRP002);
		ComboObjectVo dataVo = null;
		
		try {
			
			con = DBConnectionManager.getConnection();
			strSQL = strSQL.replaceAll("DIF_TAG", "<>");
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			preStmt.setString(1, userInfor.getUserUser());
			ResultSet rs = null;
			
			rs = preStmt.executeQuery();

			while (rs.next()) {
				dataVo = new ComboObjectVo();
				dataVo.setCode(StringUtils.emptyIfNull(rs.getString("MENUGRP_CODE")));
				dataVo.setValue1(StringUtils.emptyIfNull(rs.getString("MENUGRP_NAME")));
				dataVo.setValue2(StringUtils.emptyIfNull(rs.getString("MENUGRP_HELP")));
				dataVo.setValue3(StringUtils.emptyIfNull(rs.getString("MENUGRP_SEQ")));
				lstArr.add(dataVo);
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
		
		logger.info("ComboDaoImpl.getLstMenuGrpVo 終了。");
		
		return lstArr;
	}

	/* 　品質規格表問合せ条件　*/
	/* (non-Javadoc)
	 * @
	 */
	public List<ComboObjectVo> getLstINSPP() throws DaoException {
		
		logger.info("ComboDaoImpl.getLstINSPP 開始。");
		
		DBConnection con = null;
		ComboObjectVo dataVo = null;
		List<ComboObjectVo> lstData = new ArrayList<ComboObjectVo>();
		String strSQL = getSQL(XMLContants.FILE_M_COMBO, XMLContants.M_INSPPN001);
		logger.info(strSQL);
		dataVo = new ComboObjectVo();
		dataVo.setCode("");
		lstData.add(dataVo);
		try {

			con = DBConnectionManager.getConnection();
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			ResultSet rs = preStmt.executeQuery();
			while (rs.next()) {
				dataVo = new ComboObjectVo();
				dataVo.setCode(StringUtils.emptyIfNull(rs.getString("CODE")).trim());
				dataVo.setValue1(StringUtils.emptyIfNull(rs.getString("NAME")).trim());
				dataVo.setValue2(StringUtils.emptyIfNull(rs.getString("RNAME")).trim());
				dataVo.setValue3(StringUtils.emptyIfNull(rs.getString("TNAME")).trim());
				dataVo.setValue4(StringUtils.emptyIfNull(rs.getString("INSP_LAYOUT")).trim());
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
		
		logger.info("ComboDaoImpl.getLstINSPP 終了。");
		
		return lstData;

	}
	
	/* (non-Javadoc)
	 * @see com.pipe.dao.combo.ComboDao#getLstColor(java.lang.String)
	 */
	public List<ComboObjectVo> getLstColor(String userId) throws DaoException {
		
		logger.info("ComboDaoImpl.getLstColor 開始。");
		
		DBConnection con = null;
		ComboObjectVo dataVo = null;
		List<ComboObjectVo> lstData = new ArrayList<ComboObjectVo>();
		String strSQL = getSQL(XMLContants.FILE_M_COMBO, XMLContants.M_CLRCTL002);
		logger.info(strSQL);
		dataVo = new ComboObjectVo();
		dataVo.setCode("");
		lstData.add(dataVo);
		
		try {
			con = DBConnectionManager.getConnection();
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			preStmt.setString(1, userId);
			ResultSet rs = preStmt.executeQuery();
			
			while (rs.next()) {
				dataVo = new ComboObjectVo();
				dataVo.setCode(StringUtils.emptyIfNull(rs.getString("ASCLR_NAME")).trim());
				dataVo.setValue1(StringUtils.emptyIfNull(rs.getString("CLR_KEY")).trim());
				dataVo.setValue2(StringUtils.emptyIfNull(rs.getString("CLR_HELP")).trim());
				dataVo.setValue3(StringUtils.emptyIfNull(rs.getString("USERID")).trim());
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
		
		logger.info("ComboDaoImpl.getLstColor 終了。");
		
		return lstData;
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.dao.combo.ComboDao#getLstColor(java.lang.String)
	 */
	public List<ComboObjectVo> getLstState() throws DaoException {
		
		logger.info("ComboDaoImpl.getLstState 開始。");
		
		DBConnection con = null;
		ComboObjectVo dataVo = null;
		List<ComboObjectVo> lstData = new ArrayList<ComboObjectVo>();
		String strSQL = getSQL(XMLContants.FILE_M_COMBO, XMLContants.M_COLOR002);
		logger.info(strSQL);
		dataVo = new ComboObjectVo();
		dataVo.setCode("");
		lstData.add(dataVo);
		
		try {
			con = DBConnectionManager.getConnection();
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			ResultSet rs = preStmt.executeQuery();
			
			while (rs.next()) {
				dataVo = new ComboObjectVo();
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
		
		logger.info("ComboDaoImpl.getLstState 終了。");
		
		return lstData;
	}

	/* (non-Javadoc)
	 * @see com.pipe.dao.combo.ComboDao#getLstInsp3(com.pipe.vo.insp.minsp.MInsp2Vo)
	 */
	public List<ComboObjectVo> getLstInsp3(String insp1Code, String insp2Code) throws DaoException {
		
		logger.info("ComboDaoImpl.getLstInsp3 開始。");
		
		DBConnection con = null;
		ComboObjectVo dataVo = null;
		List<ComboObjectVo> lstData = new ArrayList<ComboObjectVo>();
		String strSQL = getSQL(XMLContants.FILE_M_COMBO, XMLContants.M_CB_INSP003);
		logger.info(strSQL);
		dataVo = new ComboObjectVo();
		dataVo.setCode("");
		lstData.add(dataVo);
		
		try {
			con = DBConnectionManager.getConnection();
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			preStmt.setString(1, insp1Code);
			preStmt.setString(2, insp2Code);
			ResultSet rs = preStmt.executeQuery();
			
			while (rs.next()) {
				if (StringUtils.isValid(rs.getString("INSP3_CODE")) == false){
					continue;
				}
				dataVo = new ComboObjectVo();
				dataVo.setCode(StringUtils.emptyIfNull(rs.getString("INSP3_CODE")).trim());
				dataVo.setValue1(StringUtils.emptyIfNull(rs.getString("INSP3_NAME")).trim());
				dataVo.setValue2(StringUtils.emptyIfNull(rs.getString("INSP3_KANA")).trim());
				dataVo.setValue3(StringUtils.emptyIfNull(rs.getString("INSP3_RNAME")).trim());
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
		
		logger.info("ComboDaoImpl.getLstInsp3 終了。");
		
		return lstData;
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.dao.combo.ComboDao#getLstInsp3(com.pipe.vo.insp.minsp.MInsp2Vo)
	 */
	public List<ComboObjectVo> getLstInsp3(int inspNo, int planDate, String insp1Code, String insp2Code) throws DaoException {
		
		logger.info("ComboDaoImpl.getLstInsp3 開始。");
		
		DBConnection con = null;
		ComboObjectVo dataVo = null;
		List<ComboObjectVo> lstData = new ArrayList<ComboObjectVo>();
		String strSQL = getSQL(XMLContants.FILE_M_COMBO, XMLContants.M_CB_INSP603);
		logger.info(strSQL);
//		System.out.println(strSQL + inspNo + "date" + planDate + " " + insp1Code + " " + insp2Code);
		
		dataVo = new ComboObjectVo();
		dataVo.setCode("");
		lstData.add(dataVo);
		
		try {
			con = DBConnectionManager.getConnection();
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			preStmt.setInt(1, inspNo);
			preStmt.setInt(2, planDate);
			preStmt.setString(3, insp1Code);
			preStmt.setString(4, insp2Code);
			ResultSet rs = preStmt.executeQuery();
			
			while (rs.next()) {
				if (StringUtils.isValid(rs.getString("INSP3_CODE")) == false){
					continue;
				}
				dataVo = new ComboObjectVo();
				dataVo.setCode(StringUtils.emptyIfNull(rs.getString("INSP3_CODE")).trim());
				dataVo.setValue1(StringUtils.emptyIfNull(rs.getString("INSP3_NAME")).trim());
				dataVo.setValue2(StringUtils.emptyIfNull(rs.getString("INSP3_KANA")).trim());
				dataVo.setValue3(StringUtils.emptyIfNull(rs.getString("INSP3_RNAME")).trim());
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
		
		logger.info("ComboDaoImpl.getLstInsp3 終了。");
		
		return lstData;
	}

	/* (non-Javadoc)
	 * @see com.pipe.dao.combo.ComboDao#getLstInsp4(com.pipe.vo.insp.minsp.MInsp2Vo)
	 */
	public List<ComboObjectVo> getLstInsp4(String insp1Code, String insp2Code, String insp3Code) throws DaoException {
		
		logger.info("ComboDaoImpl.getLstInsp4 開始。");
		
		DBConnection con = null;
		ComboObjectVo dataVo = null;
		List<ComboObjectVo> lstData = new ArrayList<ComboObjectVo>();
		String strSQL = getSQL(XMLContants.FILE_M_COMBO, XMLContants.M_CB_INSP004);
		logger.info(strSQL);
		dataVo = new ComboObjectVo();
		dataVo.setCode("");
		lstData.add(dataVo);
		
		try {
			con = DBConnectionManager.getConnection();
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			preStmt.setString(1, insp1Code);
			preStmt.setString(2, insp2Code);
			preStmt.setString(3, insp3Code);
			ResultSet rs = preStmt.executeQuery();
			
			while (rs.next()) {
				if (StringUtils.isValid(rs.getString("INSP4_CODE")) == false){
					continue;
				}
				dataVo = new ComboObjectVo();
				dataVo.setCode(StringUtils.emptyIfNull(rs.getString("INSP4_CODE")).trim());
				dataVo.setValue1(StringUtils.emptyIfNull(rs.getString("INSP4_NAME")).trim());
				dataVo.setValue2(StringUtils.emptyIfNull(rs.getString("INSP4_KANA")).trim());
				dataVo.setValue3(StringUtils.emptyIfNull(rs.getString("INSP4_RNAME")).trim());
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
		
		logger.info("ComboDaoImpl.getLstInsp4 終了。");
		
		return lstData;
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.dao.combo.ComboDao#getLstInsp4(int, int, java.lang.String, java.lang.String, java.lang.String)
	 */
	public List<ComboObjectVo> getLstInsp4(int inspNo, int planDate, String insp1Code, String insp2Code, String insp3Code) throws DaoException {
		
		logger.info("ComboDaoImpl.getLstInsp4 開始。");
		
		DBConnection con = null;
		ComboObjectVo dataVo = null;
		List<ComboObjectVo> lstData = new ArrayList<ComboObjectVo>();
		String strSQL = getSQL(XMLContants.FILE_M_COMBO, XMLContants.M_CB_INSP604);
		logger.info(strSQL);
		dataVo = new ComboObjectVo();
		dataVo.setCode("");
		lstData.add(dataVo);
		
		try {
			con = DBConnectionManager.getConnection();
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			preStmt.setInt(1, inspNo);
			preStmt.setInt(2, planDate);
			preStmt.setString(3, insp1Code);
			preStmt.setString(4, insp2Code);
			preStmt.setString(5, insp3Code);
			ResultSet rs = preStmt.executeQuery();
			
			while (rs.next() ) {
				if (StringUtils.isValid(rs.getString("INSP4_CODE")) == false){
					continue;
				}
				dataVo = new ComboObjectVo();
				dataVo.setCode(StringUtils.emptyIfNull(rs.getString("INSP4_CODE")).trim());
				dataVo.setValue1(StringUtils.emptyIfNull(rs.getString("INSP4_NAME")).trim());
				dataVo.setValue2(StringUtils.emptyIfNull(rs.getString("INSP4_KANA")).trim());
				dataVo.setValue3(StringUtils.emptyIfNull(rs.getString("INSP4_RNAME")).trim());
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
		
		logger.info("ComboDaoImpl.getLstInsp4 終了。");
		
		return lstData;
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.dao.combo.ComboDao#getLstInsp3Dao(com.pipe.vo.insp.minsp.MInsp2Vo)
	 */
	public List<ComboObjectVo> getLstInsp3Dao() throws DaoException {
		
		logger.info("ComboDaoImpl.getLstInsp3Dao 開始。");
		
		DBConnection con = null;
		ComboObjectVo dataVo = null;
		List<ComboObjectVo> lstData = new ArrayList<ComboObjectVo>();
		String strSQL = getSQL(XMLContants.FILE_M_COMBO, XMLContants.M_INSP506);
		logger.info(strSQL);
		dataVo = new ComboObjectVo();
		dataVo.setCode("");
		lstData.add(dataVo);
		
		try {
			con = DBConnectionManager.getConnection();
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			ResultSet rs = preStmt.executeQuery();
			
			while (rs.next()) {
				dataVo = new ComboObjectVo();
				dataVo.setCode(StringUtils.emptyIfNull(rs.getString("INSP3_CODE")).trim());
				dataVo.setValue1(StringUtils.emptyIfNull(rs.getString("INSP3_NAME")).trim());
				dataVo.setValue2(StringUtils.emptyIfNull(rs.getString("INSP3_KANA")).trim());
				dataVo.setValue3(StringUtils.emptyIfNull(rs.getString("INSP3_RNAME")).trim());
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
		
		logger.info("ComboDaoImpl.getLstInsp3Dao 終了。");
		
		return lstData;
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.dao.combo.ComboDao#getLstInsp4Dao(com.pipe.vo.insp.minsp.MInsp2Vo)
	 */
	public List<ComboObjectVo> getLstInsp4Dao() throws DaoException {
		
		logger.info("ComboDaoImpl.getLstInsp4Dao 開始。");
		
		DBConnection con = null;
		ComboObjectVo dataVo = null;
		List<ComboObjectVo> lstData = new ArrayList<ComboObjectVo>();
		String strSQL = getSQL(XMLContants.FILE_M_COMBO, XMLContants.M_INSP507);
		logger.info(strSQL);
		dataVo = new ComboObjectVo();
		dataVo.setCode("");
		lstData.add(dataVo);
		
		try {
			con = DBConnectionManager.getConnection();
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			ResultSet rs = preStmt.executeQuery();
			
			while (rs.next()) {
				dataVo = new ComboObjectVo();
				dataVo.setCode(StringUtils.emptyIfNull(rs.getString("INSP4_CODE")).trim());
				dataVo.setValue1(StringUtils.emptyIfNull(rs.getString("INSP4_NAME")).trim());
				dataVo.setValue2(StringUtils.emptyIfNull(rs.getString("INSP4_KANA")).trim());
				dataVo.setValue3(StringUtils.emptyIfNull(rs.getString("INSP4_RNAME")).trim());
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
		
		logger.info("ComboDaoImpl.getLstInsp4Dao 終了。");
		
		return lstData;
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.dao.combo.ComboDao#getLstEmp()
	 */
	public List<ComboObjectVo> getLstEmp() throws DaoException {
		
		logger.info("ComboDaoImpl.getLstEmp 開始。");
		
		DBConnection con = null;
		ComboObjectVo dataVo = null;
		List<ComboObjectVo> lstData = new ArrayList<ComboObjectVo>();
		String strSQL = getSQL(XMLContants.FILE_M_COMBO, XMLContants.M_CB_EMP501);
		logger.info(strSQL);
		dataVo = new ComboObjectVo();
		dataVo.setCode("");
		lstData.add(dataVo);
		
		try {
			con = DBConnectionManager.getConnection();
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			
			ResultSet rs = preStmt.executeQuery();
			
			while (rs.next()) {
				dataVo = new ComboObjectVo();
				dataVo.setCode(StringUtils.emptyIfNull(rs.getString("EMP_CODE")).trim());
				dataVo.setValue1(StringUtils.emptyIfNull(rs.getString("EMP_NAME")).trim());
				dataVo.setValue2(StringUtils.emptyIfNull(rs.getString("EMP_KANA")).trim());
				dataVo.setValue3(StringUtils.emptyIfNull(rs.getString("EMP_TNAME")).trim());
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
		
		logger.info("ComboDaoImpl.getLstEmp 終了。");
		
		return lstData;
	}

	/* (non-Javadoc)
	 * @see com.pipe.dao.combo.ComboDao#getLstFromName(java.lang.String)
	 */
	public List<ComboObjectVo> getLstFromName(String nameclsCode, boolean includeBlank) throws DaoException {		
		logger.info("ComboDaoImpl.getLstFromName 開始。");		
		DBConnection con = null;
		ComboObjectVo dataVo = null;
		List<ComboObjectVo> lstData = new ArrayList<ComboObjectVo>();
		String strSQL = getSQL(XMLContants.FILE_M_COMBO, XMLContants.M_CB_NAME500);
		logger.info(strSQL);
		if (includeBlank) {
			dataVo = new ComboObjectVo();
			dataVo.setCode("");
			lstData.add(dataVo);	
		}
		
		try {
			con = DBConnectionManager.getConnection();
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			preStmt.setString(1, StringUtils.emptyIfNull(nameclsCode));

			ResultSet rs = preStmt.executeQuery();
			
			while (rs.next()) {
				dataVo = new ComboObjectVo();
				dataVo.setCode(StringUtils.emptyIfNull(rs.getString("CODE")).trim());
				dataVo.setValue1(StringUtils.emptyIfNull(rs.getString("NAME")).trim());
				dataVo.setValue2(StringUtils.emptyIfNull(rs.getString("RNAME")).trim());
				dataVo.setValue3(StringUtils.emptyIfNull(rs.getString("DEFAULT_TYPE")).trim());
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
		logger.info("ComboDaoImpl.getLstFromName 終了。");		
		return lstData;	
	}
	
	
	/* 
	 * @param includeBlank
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public List<ComboObjectVo> getLstG0103OfPG01( boolean includeBlank) throws DaoException {		
		logger.info("ComboDaoImpl.getLstG0103OfPG01 開始。");		
		DBConnection con = null;
		ComboObjectVo dataVo = null;
		List<ComboObjectVo> lstData = new ArrayList<ComboObjectVo>();
		String strSQL = getSQL(XMLContants.FILE_M_COMBO, XMLContants.M_CB_PG01001);
		logger.info(strSQL);
		if (includeBlank) {
			dataVo = new ComboObjectVo();
			dataVo.setCode("");
			lstData.add(dataVo);
		}		
		try {
			con = DBConnectionManager.getConnection();
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			ResultSet rs = preStmt.executeQuery();			
			while (rs.next()) {
				dataVo = new ComboObjectVo();
				dataVo.setCode(StringUtils.emptyIfNull(rs.getString("G0101")).trim());
				dataVo.setValue1(StringUtils.emptyIfNull(rs.getString("G0103")).trim());
				dataVo.setValue2(StringUtils.emptyIfNull(rs.getString("G0103")).trim());
				dataVo.setValue3("");
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
		logger.info("ComboDaoImpl.getLstG0103OfPG01 終了。");		
		return lstData;	
	}
	

	/* 
	 * @param includeBlank
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public List<ComboObjectVo> getLstG0102OfPG01( boolean includeBlank) throws DaoException {		
		logger.info("ComboDaoImpl.getLstG0102OfPG01 開始。");		
		DBConnection con = null;
		ComboObjectVo dataVo = null;
		List<ComboObjectVo> lstData = new ArrayList<ComboObjectVo>();
		String strSQL = getSQL(XMLContants.FILE_M_COMBO, XMLContants.M_CB_PG01001);
		logger.info(strSQL);
		if (includeBlank) {
			dataVo = new ComboObjectVo();
			dataVo.setCode("");
			lstData.add(dataVo);
		}		
		try {
			con = DBConnectionManager.getConnection();
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			ResultSet rs = preStmt.executeQuery();			
			while (rs.next()) {
				dataVo = new ComboObjectVo();
				dataVo.setCode(StringUtils.emptyIfNull(rs.getString("G0101")).trim());
				dataVo.setValue1(StringUtils.emptyIfNull(rs.getString("G0102")).trim());
				dataVo.setValue2(StringUtils.emptyIfNull(rs.getString("G0102")).trim());
				dataVo.setValue3("");
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
		logger.info("ComboDaoImpl.getLstG0102OfPG01 終了。");		
		return lstData;	
	}
	
	
	public List<ComboObjectVo> getLstFromMasterName(String nameclsCode, boolean includeBlank) throws DaoException {

		
		logger.info("ComboDaoImpl.getLstFromMasterName 開始。");
		
		DBConnection con = null;
		ComboObjectVo dataVo = null;
		List<ComboObjectVo> lstData = new ArrayList<ComboObjectVo>();
		String strSQL = getSQL(XMLContants.FILE_M_COMBO, XMLContants.M_CB_NAME500);
		logger.info(strSQL);
		if (includeBlank) {
			dataVo = new ComboObjectVo();
			dataVo.setCode("");
			lstData.add(dataVo);	
		}
		
		try {
			con = DBConnectionManager.getConnection();
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			preStmt.setString(1, StringUtils.emptyIfNull(nameclsCode));

			ResultSet rs = preStmt.executeQuery();
			
			while (rs.next()) {
				dataVo = new ComboObjectVo();
				dataVo.setCode(StringUtils.emptyIfNull(rs.getString("CODE")).trim());
				dataVo.setValue1("");
				dataVo.setValue2(StringUtils.emptyIfNull(rs.getString("RNAME")).trim());
				dataVo.setValue3(StringUtils.emptyIfNull(rs.getString("DEFAULT_TYPE")).trim());
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
		
		logger.info("ComboDaoImpl.getLstFromMasterName 終了。");
		
		return lstData;
	
	}
	/* (non-Javadoc)
	 * @see com.pipe.dao.combo.ComboDao#getLstFromName(java.lang.String)
	 */
	public List<ComboObjectVo> getLstBENDERR(String nameclsCode) throws DaoException {

		
		logger.info("ComboDaoImpl.getLstBENDERR 開始。");
		
		DBConnection con = null;
		ComboObjectVo dataVo = null;
		List<ComboObjectVo> lstData = new ArrayList<ComboObjectVo>();
		String strSQL = getSQL(XMLContants.FILE_M_COMBO, XMLContants.M_CB_BENDERR);
		logger.info(strSQL);
		try {
			con = DBConnectionManager.getConnection();
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			preStmt.setString(1, StringUtils.emptyIfNull(nameclsCode));

			ResultSet rs = preStmt.executeQuery();
			
			while (rs.next()) {
				dataVo = new ComboObjectVo();
				dataVo.setCode(StringUtils.emptyIfNull(rs.getString("BENDERR")).trim());
				dataVo.setValue1(StringUtils.emptyIfNull(rs.getString("BENDERR")).trim());
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
		
		logger.info("ComboDaoImpl.getLstBENDERR 終了。");
		
		return lstData;
	
	}
	/* (non-Javadoc)
	 * @see com.pipe.dao.combo.ComboDao#getLstTTL()
	 */
	public List<ComboObjectVo> getLstTTL() throws DaoException {

		
		logger.info("ComboDaoImpl.getLstTTL 開始。");
		
		DBConnection con = null;
		ComboObjectVo dataVo = null;
		List<ComboObjectVo> lstData = new ArrayList<ComboObjectVo>();
		String strSQL = getSQL(XMLContants.FILE_M_COMBO, XMLContants.M_CB_TTL502);
		logger.info(strSQL);
		dataVo = new ComboObjectVo();
		dataVo.setCode("");
		lstData.add(dataVo);
		
		try {
			con = DBConnectionManager.getConnection();
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			ResultSet rs = preStmt.executeQuery();
			
			while (rs.next()) {
				dataVo = new ComboObjectVo();
				dataVo.setCode(StringUtils.emptyIfNull(rs.getString("CODE")).trim());
				dataVo.setValue1(StringUtils.emptyIfNull(rs.getString("NAME")).trim());
				dataVo.setValue2(StringUtils.emptyIfNull(rs.getString("RNAME")).trim());
				dataVo.setValue3(StringUtils.emptyIfNull(rs.getString("DEFAULT_TYPE")).trim());
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
		
		logger.info("ComboDaoImpl.getLstTTL 終了。");
		
		return lstData;
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.dao.combo.ComboDao#getLstEmp()
	 */
	public List<ComboObjectVo> getLstUSERGRP() throws DaoException {
		
		logger.info("ComboDaoImpl.getLstUSERGRP 開始。");
		
		DBConnection con = null;
		ComboObjectVo dataVo = null;
		List<ComboObjectVo> lstData = new ArrayList<ComboObjectVo>();
		String strSQL = getSQL(XMLContants.FILE_M_COMBO, XMLContants.M_CB_USERGRP500);
		logger.info(strSQL);
		dataVo = new ComboObjectVo();
		dataVo.setCode("");
		lstData.add(dataVo);
		
		try {
			con = DBConnectionManager.getConnection();
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			
			ResultSet rs = preStmt.executeQuery();
			
			while (rs.next()) {
				dataVo = new ComboObjectVo();
				dataVo.setCode(StringUtils.emptyIfNull(rs.getString("USERGID")).trim());
				dataVo.setValue1(StringUtils.emptyIfNull(rs.getString("USERTXT")).trim());
				dataVo.setValue2("");
				dataVo.setValue3("");
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
		
		logger.info("ComboDaoImpl.getLstUSERGRP 終了。");
		
		return lstData;
	}

	/* (non-Javadoc)
	 * @see com.pipe.dao.combo.ComboDao#getLstInspArea()
	 */
	public List<ComboObjectVo> getLstInspArea() throws DaoException
	{
		logger.info("ComboDaoImpl.getLstInspArea 開始。");
		
		DBConnection con = null;
		ComboObjectVo dataVo = null;
		List<ComboObjectVo> lstData = new ArrayList<ComboObjectVo>();
		String strSQL = getSQL(XMLContants.FILE_M_COMBO, XMLContants.M_CB_INSPAREA500);
		logger.info(strSQL);
		dataVo = new ComboObjectVo();
		dataVo.setCode("");
		lstData.add(dataVo);
		
		try {
			con = DBConnectionManager.getConnection();
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			
			ResultSet rs = preStmt.executeQuery();
			
			while (rs.next()) {
				dataVo = new ComboObjectVo();
				dataVo.setCode(StringUtils.emptyIfNull(rs.getString("NAME_CODE")).trim());
				dataVo.setValue1(StringUtils.emptyIfNull(rs.getString("NAME_NAME")).trim());
				dataVo.setValue2("");
				dataVo.setValue3("");
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
		
		logger.info("ComboDaoImpl.getLstInspArea 終了。");
		
		return lstData;	
	}

	/* (non-Javadoc)
	 * @see com.pipe.dao.combo.ComboDao#getLstDpntFlg()
	 */
	public List<ComboObjectVo> getLstDpntFlg() throws DaoException
	{
		logger.info("ComboDaoImpl.getLstDpntFlg 開始。");
		
		DBConnection con = null;
		ComboObjectVo dataVo = null;
		List<ComboObjectVo> lstData = new ArrayList<ComboObjectVo>();
		String strSQL = getSQL(XMLContants.FILE_M_COMBO, XMLContants.M_CB_DPNTFLG500);
		logger.info(strSQL);
		dataVo = new ComboObjectVo();
		dataVo.setCode("");
		lstData.add(dataVo);
		
		try {
			con = DBConnectionManager.getConnection();
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			
			ResultSet rs = preStmt.executeQuery();
			
			while (rs.next()) {
				dataVo = new ComboObjectVo();
				dataVo.setCode(StringUtils.emptyIfNull(rs.getString("NAME_CODE")).trim());
				dataVo.setValue1(StringUtils.emptyIfNull(rs.getString("NAME_NAME")).trim());
				dataVo.setValue2("");
				dataVo.setValue3("");
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
		
		logger.info("ComboDaoImpl.getLstDpntFlg 終了。");
		
		return lstData;	
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.dao.combo.ComboDao#getLstCTLOrderByCKey(java.lang.String, int, int)
	 */
	public List<ComboObjectVo> getLstCTLOrderByCKey(String strUserid,  int iMtnFlg, int iCntFlg) throws DaoException {
		
		logger.info("ComboDaoImpl.getLstCTL 開始。");
		
		DBConnection con = null;
		ComboObjectVo dataVo = null;
		List<ComboObjectVo> lstData = new ArrayList<ComboObjectVo>();
		String strSQL = getSQL(XMLContants.FILE_M_COMBO, XMLContants.M_CB_CTL500);
		String strWhere = " WHERE M.USERID = '" + strUserid.toString() + "'"+" AND M.MTN_FLG = " + iMtnFlg+" AND M.CNT_FLG = " + iCntFlg+" ";
		String strOrderBy = " ORDER BY M.C_KEY";		
		strSQL += strWhere;
		strSQL += strOrderBy;
		logger.info(strSQL);
		dataVo = new ComboObjectVo();
		dataVo.setCode("");
		lstData.add(dataVo);		
		try {
			con = DBConnectionManager.getConnection();
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			
			ResultSet rs = preStmt.executeQuery();
			
			while (rs.next()) {
				dataVo = new ComboObjectVo();
				dataVo.setCode(StringUtils.emptyIfNull(rs.getString("C_KEY")).trim());
				dataVo.setValue1(StringUtils.emptyIfNull(rs.getString("C_NAME")).trim());
				dataVo.setValue2("");
				dataVo.setValue3("");
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
		
		logger.info("ComboDaoImpl.getLstCTL 終了。");
		
		return lstData;
	}

	/* (non-Javadoc)
	 * @see com.pipe.dao.combo.ComboDao#getCmbMCtlByUseridAndMtnFlg(java.lang.String, int)
	 */
	public List<ComboObjectVo> getCmbMCtlByUseridAndMtnFlg(String strUserid,  int iMtnFlg) throws DaoException
	{
		logger.info("ComboDaoImpl.getCmbMCtlByUseridAndMtnFlg 開始。");		
		DBConnection con = null;
		ComboObjectVo dataVo = null;
		List<ComboObjectVo> lstData = new ArrayList<ComboObjectVo>();
		String strSQL = getSQL(XMLContants.FILE_M_COMBO, XMLContants.M_CB_CTL500);
		String strWhere = " WHERE M.USERID = ? AND M.MTN_FLG = ? ";
		String strOrderBy = " ORDER BY M.C_KEY";		
		strSQL += strWhere + strOrderBy;
		logger.info(strSQL);
		dataVo = new ComboObjectVo();
		dataVo.setCode("");
		lstData.add(dataVo);		
		try {
			con = DBConnectionManager.getConnection();
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			int i=1;
			preStmt.setString(i++, strUserid);
			preStmt.setString(i++, StringUtils.convertIntegerToStr(iMtnFlg));
			ResultSet rs = preStmt.executeQuery();
			
			while (rs.next()) {
				dataVo = new ComboObjectVo();
				dataVo.setCode(StringUtils.emptyIfNull(rs.getString("C_KEY")).trim());
				dataVo.setValue1(StringUtils.emptyIfNull(rs.getString("C_NAME")).trim());
				dataVo.setValue2("");
				dataVo.setValue3("");
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
		logger.info("ComboDaoImpl.getCmbMCtlByUseridAndMtnFlg 終了。");		
		return lstData;		
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.dao.combo.ComboDao#getLstMenuExe()
	 */
	public List<ComboObjectVo> getLstMenuExe() throws DaoException {
		logger.info("ComboDaoImpl.getLstMenuExe 開始。");
		
		DBConnection con = null;
		ComboObjectVo dataVo = null;
		List<ComboObjectVo> lstData = new ArrayList<ComboObjectVo>();
		String strSQL = getSQL(XMLContants.FILE_M_COMBO, XMLContants.M_CB_MENUEXE510);
		logger.info(strSQL);
		dataVo = new ComboObjectVo();
		dataVo.setCode("");
		lstData.add(dataVo);
		
		try {
			con = DBConnectionManager.getConnection();
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			
			ResultSet rs = preStmt.executeQuery();
			
			while (rs.next()) {
				dataVo = new ComboObjectVo();
				dataVo.setCode(StringUtils.emptyIfNull(rs.getString("ID")).trim());
				dataVo.setValue1(StringUtils.emptyIfNull(rs.getString("NAME")).trim());
				dataVo.setValue2("");
				dataVo.setValue3("");
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
		
		logger.info("ComboDaoImpl.getLstMenuExe 終了。");
		
		return lstData;
	}

	/* (non-Javadoc)
	 * @see com.pipe.dao.combo.ComboDao#getLstUser()
	 */
	public List<ComboObjectVo> getLstUser() throws DaoException {
		logger.info("ComboDaoImpl.getLstUser 開始。");
		
		DBConnection con = null;
		ComboObjectVo dataVo = null;
		List<ComboObjectVo> lstData = new ArrayList<ComboObjectVo>();
		String strSQL = getSQL(XMLContants.FILE_M_COMBO, XMLContants.M_CB_USER509);
		logger.info(strSQL);
		dataVo = new ComboObjectVo();
		dataVo.setCode("");
		lstData.add(dataVo);
		
		try {
			con = DBConnectionManager.getConnection();
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			
			ResultSet rs = preStmt.executeQuery();
			
			while (rs.next()) {
				dataVo = new ComboObjectVo();
				dataVo.setCode(StringUtils.emptyIfNull(rs.getString("EMPCODE")).trim());
				dataVo.setValue1(StringUtils.emptyIfNull(rs.getString("EMPNAME")).trim());
				dataVo.setValue2("");
				dataVo.setValue3("");
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
		
		logger.info("ComboDaoImpl.getLstUser 終了。");
		
		return lstData;
	}

	/* (non-Javadoc)
	 * @see com.pipe.dao.combo.ComboDao#getLstUserGrp()
	 */
	public List<ComboObjectVo> getLstUserGrp() throws DaoException {
		
		logger.info("ComboDaoImpl.getLstUserGrp 開始。");
		
		DBConnection con = null;
		ComboObjectVo dataVo = null;
		List<ComboObjectVo> lstData = new ArrayList<ComboObjectVo>();
		String strSQL = getSQL(XMLContants.FILE_M_COMBO, XMLContants.M_CB_USERGRP508);
		logger.info(strSQL);
		dataVo = new ComboObjectVo();
		dataVo.setCode("");
		lstData.add(dataVo);
		
		try {
			con = DBConnectionManager.getConnection();
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			
			ResultSet rs = preStmt.executeQuery();
			
			while (rs.next()) {
				dataVo = new ComboObjectVo();
				dataVo.setCode(StringUtils.emptyIfNull(rs.getString("USERGID")).trim());
				dataVo.setValue1(StringUtils.emptyIfNull(rs.getString("USERTXT")).trim());
				dataVo.setValue2("");
				dataVo.setValue3("");
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
		
		logger.info("ComboDaoImpl.getLstUserGrp 終了。");
		
		return lstData;
	}

	/* (non-Javadoc)
	 * @see com.pipe.dao.combo.ComboDao#getLstColorCls()
	 */
	public List<ComboObjectVo> getLstColorCls() throws DaoException {
		logger.info("ComboDaoImpl.getLstColorCls 開始。");
		
		DBConnection con = null;
		ComboObjectVo dataVo = null;
		List<ComboObjectVo> lstData = new ArrayList<ComboObjectVo>();
		String strSQL = getSQL(XMLContants.FILE_M_COMBO, XMLContants.M_CB_COLORCLS001);
		logger.info(strSQL);
		dataVo = new ComboObjectVo();
		dataVo.setCode("");
		lstData.add(dataVo);
		
		try {
			con = DBConnectionManager.getConnection();
			PreparedStatement preStmt = con.prepareStatement(strSQL);	
			ResultSet rs = preStmt.executeQuery();
			
			while (rs.next()) {
				dataVo = new ComboObjectVo();
				dataVo.setCode(StringUtils.emptyIfNull(rs.getString("COLORCLS_CODE")).trim());
				dataVo.setValue1(StringUtils.emptyIfNull(rs.getString("COLORCLS_NAME")).trim());
				dataVo.setValue2("");
				dataVo.setValue3("");
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
		
		logger.info("ComboDaoImpl.getLstColorCls 終了。");
		
		return lstData;
	}
	
	public List<ComboObjectVo> getLstLoginUser() throws DaoException {
		logger.info("ComboDaoImpl.getLstLoginUser 開始。");
		DBConnection con = null;
		ComboObjectVo dataVo = null;
		List<ComboObjectVo> lstData = new ArrayList<ComboObjectVo>();
		String strSQL = getSQL(XMLContants.FILE_M_COMBO, XMLContants.M_CB_LOGIN601);
		logger.info(strSQL);
		dataVo = new ComboObjectVo();
		dataVo.setCode("");
		lstData.add(dataVo);
		
		try {
			con = DBConnectionManager.getConnection();
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			
			ResultSet rs = preStmt.executeQuery();
			
			while (rs.next()) {
				dataVo = new ComboObjectVo();
				dataVo.setCode(StringUtils.emptyIfNull(rs.getString("EMP_NAME")).trim());
				dataVo.setValue1("");
				dataVo.setValue2(StringUtils.emptyIfNull(rs.getString("EMP_CODE")).trim());
				dataVo.setValue3(StringUtils.emptyIfNull(rs.getString("EMP_USER")).trim());
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
		
		logger.info("ComboDaoImpl.getLstLoginUser 終了。");
		
		return lstData;
	}
	
	/* (non-Javadoc)
	 * @
	 */
	public String getMclsSusType(String strLclsCode, String strMclsCode) throws DaoException {
		
		logger.info("ComboDaoImpl.getMclsSusType 開始。");
		
		DBConnection con = null;		
		String strSQL = getSQL(XMLContants.FILE_M_COMBO, XMLContants.M_CB_MCLS502);
		String susType = "";
		
		try {

			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			int i = 1;
			
			preStmt.setString(i++, StringUtils.emptyIfNull(strLclsCode));
			preStmt.setString(i++, StringUtils.emptyIfNull(strMclsCode));
			
			ResultSet rs = preStmt.executeQuery();
			
			while (rs.next()) {
				susType = StringUtils.emptyIfNull(rs.getString("SUS_TYPE")).trim();
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
		
		logger.info("ComboDaoImpl.getMclsSusType 終了。");
		
		return susType;

	}
		
	/* (non-Javadoc)
	 * @
	 */
	public String getMclsSPCLType(String strLclsCode, String strMclsCode) throws DaoException {
		
		logger.info("ComboDaoImpl.getMclsSPCLType 開始。");
		
		DBConnection con = null;		
		String strSQL = getSQL(XMLContants.FILE_M_COMBO, XMLContants.M_CB_MCLS503);
		String spclType = "";
		
		try {

			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			int i = 1;
			
			preStmt.setString(i++, StringUtils.emptyIfNull(strLclsCode));
			preStmt.setString(i++, StringUtils.emptyIfNull(strMclsCode));
			
			ResultSet rs = preStmt.executeQuery();
			
			while (rs.next()) {
				spclType = StringUtils.emptyIfNull(rs.getString("SPCL_TYPE")).trim();
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
		
		logger.info("ComboDaoImpl.getMclsSPCLType 終了。");
		
		return spclType;

	}
	
	/* (non-Javadoc)
	 * @
	 */
	public List<ComboObjectVo> getLstScls( String strLclsCode, String strMclsCode) throws DaoException {
		
		logger.info("ComboDaoImpl.getSstLcls 開始。");
		
		DBConnection con = null;
		ComboObjectVo dataVo = null;
		List<ComboObjectVo> lstData = new ArrayList<ComboObjectVo>();
		String strSQL = getSQL(XMLContants.FILE_M_COMBO, XMLContants.M_CB_SCLS501);
		logger.info(strSQL);
//		dataVo = new ComboObjectVo();
//		dataVo.setCode("");
//		lstData.add(dataVo);
		try {

			con = DBConnectionManager.getConnection();
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			int i=1;
			preStmt.setString(i++, StringUtils.emptyIfNull(strLclsCode));
			preStmt.setString(i++, StringUtils.emptyIfNull(strMclsCode));
			ResultSet rs = preStmt.executeQuery();
			while (rs.next()) {
				dataVo = new ComboObjectVo();
				dataVo.setCode(StringUtils.emptyIfNull(rs.getString("SCLS_CODE")).trim());
				dataVo.setValue1(StringUtils.emptyIfNull(rs.getString("SCLS_NAME")).trim());
				dataVo.setValue2(StringUtils.emptyIfNull(rs.getString("SCLS_TNAME")).trim());
				dataVo.setValue3(StringUtils.emptyIfNull(rs.getString("SCLS_KANA")).trim());
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
		
		logger.info("ComboDaoImpl.getLstScls 終了。");
		
		return lstData;

	}

	public List<ComboObjectVo> getMenuPrt() throws DaoException {
		logger.info("ComboDaoImpl.getMenuPrt");
		
		DBConnection con = null;
		ComboObjectVo dataVo = null;
		List<ComboObjectVo> lstData = new ArrayList<ComboObjectVo>();
		String strSQL = getSQL(XMLContants.FILE_M_COMBO, XMLContants.M_CB_MENUPRT_001);
		logger.info(strSQL);
		try {
			con = DBConnectionManager.getConnection();
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			//preStmt.setString(1, ApplicationConstants.LOGIN_USER_ID);
			logger.info(strSQL);
			ResultSet rs = preStmt.executeQuery();
			while (rs.next()) {
				dataVo = new ComboObjectVo();
				String strKey = ApplicationConstants.getLoginUser().getUserUser() + StringUtils.emptyIfNull(rs.getString("MENUGRP_CODE")).trim()
								+ StringUtils.emptyIfNull(rs.getString("MENUEXE_CODE")).trim();
				
				Exectl2Vo exec2Vo = PermissionPolicy.MAP_EXECTL2VO.get(strKey);
				if(exec2Vo == null) return null;
				if(!PermissionPolicy.MNEXE_VIEW_NO_PERMISSION.equalsIgnoreCase(exec2Vo.getControlType().trim())){
					dataVo.setCode(StringUtils.emptyIfNull(rs.getString("MENUEXE_NAME")).trim());
					dataVo.setValue1("");	
					dataVo.setValue2(StringUtils.emptyIfNull(rs.getString("MENUGRP_CODE")).trim());
					dataVo.setValue3(StringUtils.emptyIfNull(rs.getString("MENUEXE_CODE")).trim());
					dataVo.setValue4(StringUtils.emptyIfNull(rs.getString("C_DATA")).trim());		
					lstData.add(dataVo);
				}
				
			}
		}catch(SQLException e){
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
		logger.info("ComboDaoImpl.getMenuPrt");
		return lstData;
	}

	public List<ComboObjectVo> getLstTrOrdrHOrdrNo(String strCustCode, String strShipNo, String strDrawType) throws DaoException {		
		logger.info("ComboDaoImpl.getLstTrOrdrHOrdrNo 開始。");
		
		DBConnection con = null;
		ComboObjectVo dataVo = null;
		List<ComboObjectVo> lstData = new ArrayList<ComboObjectVo>();
		String strSQL = getSQL(XMLContants.FILE_M_COMBO, XMLContants.M_CB_TR_ORDRH500);
		logger.info(strSQL);
//		dataVo = new ComboObjectVo();
//		dataVo.setCode("");
//		lstData.add(dataVo);
		
		try {
			con = DBConnectionManager.getConnection();
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			int i= 1;
			preStmt.setString(i++, strCustCode);
			preStmt.setString(i++, strShipNo);
			preStmt.setString(i++, strDrawType);
			
			ResultSet rs = preStmt.executeQuery();
			
			while (rs.next()) {
				dataVo = new ComboObjectVo();
				dataVo.setCode(StringUtils.emptyIfNull(rs.getString("ORDR_NO")).trim());
				dataVo.setValue1("");
				dataVo.setValue2("");
				dataVo.setValue3("");
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
		
		logger.info("ComboDaoImpl.getLstTrOrdrHOrdrNo 終了。");
		
		return lstData;
	}
	
	@Override
	public List<ComboObjectVo> getLstDecPlc() throws DaoException {
		// TODO Auto-generated method stub
		logger.info("ComboDaoImpl.getLstDecPlc 開始。");
		
		DBConnection con = null;
		ComboObjectVo dataVo = null;
		List<ComboObjectVo> lstData = new ArrayList<ComboObjectVo>();
		String strSQL = getSQL(XMLContants.FILE_M_COMBO, XMLContants.M_DECPLC001);
		
		try {
			con = DBConnectionManager.getConnection();
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			logger.info(strSQL);
			ResultSet rs = preStmt.executeQuery();
			while (rs.next()) {
				dataVo = new ComboObjectVo();
				dataVo.setCode(StringUtils.emptyIfNull(rs.getString("NAME_CODE")).trim());
				dataVo.setValue1(StringUtils.emptyIfNull(rs.getString("NAME_NAME")).trim());
				dataVo.setValue2("");
				dataVo.setValue3("");	
				dataVo.setValue4(StringUtils.emptyIfNull(rs.getString("DEFAULT_TYPE")).trim());							
				lstData.add(dataVo);
			}
		}catch(SQLException e){
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
				
		logger.info("ComboDaoImpl.getLstDecPlc 終了。");		
		return lstData;	
	}
	
	@Override
	public List<ComboObjectVo> getLstSizeNameFrmProd(String lclsCode, String mclsCode, String sclsCode, String pdmeter, 
			String materCode, String stdCode,String typeCode) throws DaoException {
		// TODO Auto-generated method stub
		logger.info("ComboDaoImpl.getLstSizeNameFrmProd 開始。");
		DBConnection con = null;
		ComboObjectVo dataVo = null;
		List<ComboObjectVo> lstData = new ArrayList<ComboObjectVo>();
		String strSQL = getSQL(XMLContants.FILE_M_COMBO, XMLContants.M_CB_SIZE_NAME);		
		
		logger.info(strSQL);
		try {
			con = DBConnectionManager.getConnection();			
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			
			int i= 1;			
			preStmt.setString(i++, lclsCode);
			preStmt.setString(i++, mclsCode);			
			preStmt.setString(i++, pdmeter);
			preStmt.setString(i++, materCode);
			preStmt.setString(i++, stdCode);	
			preStmt.setString(i++, typeCode);	
			
			ResultSet rs = preStmt.executeQuery();
			while (rs.next()) {
				dataVo = new ComboObjectVo();
				dataVo.setCode(StringUtils.emptyIfNull(rs.getString("SIZE_NAME")).trim());
				dataVo.setValue1("");
				dataVo.setValue3(StringUtils.emptyIfNull(rs.getString("CUST_CODE")).trim());
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
		logger.info("ComboDaoImpl.getLstSizeNameFrmProd 終了。");
		return lstData;
	}
	
	@Override
	public List<ComboObjectVo> getLstSizeNameFrmProd(String lclsCode, String mclsCode, String sclsCode, 
			String pdmeter, String materCode, String stdCode, String typeCode, String custCode) throws DaoException {
		// TODO Auto-generated method stub
		logger.info("ComboDaoImpl.getLstSizeNameFrmProd 開始。");
		DBConnection con = null;
		ComboObjectVo dataVo = null;
		List<ComboObjectVo> lstData = new ArrayList<ComboObjectVo>();
		String strSQL = getSQL(XMLContants.FILE_M_COMBO, XMLContants.M_CB_SIZE_NAME_001);		
		
		logger.info(strSQL);
		try {
			con = DBConnectionManager.getConnection();			
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			
			int i= 1;
			preStmt.setString(i++, custCode);
			preStmt.setString(i++, lclsCode);
			preStmt.setString(i++, mclsCode);			
			preStmt.setString(i++, pdmeter);
			preStmt.setString(i++, materCode);
			preStmt.setString(i++, stdCode);	
			preStmt.setString(i++, typeCode);			
			
			ResultSet rs = preStmt.executeQuery();
			while (rs.next()) {
				dataVo = new ComboObjectVo();
				dataVo.setCode(StringUtils.emptyIfNull(rs.getString("SIZE_NAME")).trim());
				dataVo.setValue1("");
				dataVo.setValue3(StringUtils.emptyIfNull(rs.getString("CUST_CODE")).trim());
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
		logger.info("ComboDaoImpl.getLstSizeNameFrmProd 終了。");
		return lstData;
	}
	
}
