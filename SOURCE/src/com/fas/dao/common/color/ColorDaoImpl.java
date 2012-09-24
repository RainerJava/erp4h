/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：ColorDaoImpl.java
*
*     記述				：
*     
*     作成日			：2010/02/22   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/
package com.fas.dao.common.color;

import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.fas.common.DBConnection;
import com.fas.common.DBConnectionManager;
import com.fas.common.constants.XMLContants;
import com.fas.common.exception.BizException;
import com.fas.common.exception.DaoException;
import com.fas.common.utils.DateUtils;
import com.fas.common.utils.StringUtils;
import com.fas.dao.base.BaseDao;
import com.fas.service.common.exportexcel.ExportExcelService;
import com.fas.service.common.exportexcel.ExportExcelServiceImpl;
import com.fas.vo.color.DClrCtlVo;
import com.fas.vo.color.MClrCtlVo;
import com.fas.vo.color.MColorVo;

/**
 * @author PC13
 *
 */
public class ColorDaoImpl extends BaseDao implements ColorDao {

	/** */
	static Logger logger = Logger.getLogger(ColorDaoImpl.class);
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public ColorDaoImpl() {
	}

	/* (non-Javadoc)
	 * @see com.pipe.dao.color.ColorDao#getMapMClrCtlVo(java.lang.String)
	 */
	public Map<String, MClrCtlVo> getMapMClrCtlVo(String userId) throws DaoException {
		
		logger.info("ColorDaoImpl.getMapMClrCtlVo 開始。");
		
		String strSQL = getSQL(XMLContants.FILE_M_CLRCT, XMLContants.M_CLRCTL500);
		MClrCtlVo dataVo = new MClrCtlVo();
		DBConnection con = null;
		Map<String, MClrCtlVo> mapClrCtl = new HashMap<String, MClrCtlVo>();

		try {

			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			
			preStmt.setString(1, StringUtils.emptyIfNull(userId));
			
			ResultSet rs = preStmt.executeQuery();

			while (rs.next()) {
				
				dataVo = new MClrCtlVo();
				
				dataVo.setUserId(StringUtils.emptyIfNull(rs.getString("USERID")).trim());
				dataVo.setClrKey(StringUtils.emptyIfNull(rs.getString("CLR_KEY")).trim());
				dataVo.setClrHelp(StringUtils.emptyIfNull(rs.getString("CLR_HELP")));
				dataVo.setClrName(StringUtils.emptyIfNull(rs.getString("ASCLR_NAME")));
				dataVo.setClrRed(rs.getInt("CLR_RED"));
				dataVo.setClrGreen(rs.getInt("CLR_GREEN"));
				dataVo.setClrBlue(rs.getInt("CLR_BLUE"));
				dataVo.setClrAlpha(rs.getInt("CLR_ALPHA"));
				dataVo.setColor(new Color(dataVo.getClrRed(), dataVo.getClrGreen(), dataVo.getClrBlue()));
				
				mapClrCtl.put(StringUtils.emptyIfNull(rs.getString("USERID")).trim() + StringUtils.emptyIfNull(rs.getString("CLR_KEY")).trim(), dataVo);
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
		
		logger.info("ColorDaoImpl.getMapMClrCtlVo 終了。");
		
		return mapClrCtl;
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.dao.color.ColorDao#getMapMClrCtlVo(java.lang.String)
	 */
	public Map<String, DClrCtlVo> getMapDClrCtlVo() throws DaoException {
		
		logger.info("ColorDaoImpl.getMapDClrCtlVo 開始。");
		
		String strSQL = getSQL(XMLContants.FILE_D_CLRCT, XMLContants.D_CLRCTL001);
		DClrCtlVo dataVo = new DClrCtlVo();
		DBConnection con = null;
		Map<String, DClrCtlVo> mapClrCtl = new HashMap<String, DClrCtlVo>();

		try {

			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			
			ResultSet rs = preStmt.executeQuery();

			while (rs.next()) {
				
				dataVo = new DClrCtlVo();
				dataVo.setClrKey(StringUtils.emptyIfNull(rs.getString("CLR_KEY")).trim());
				dataVo.setClrHelp(StringUtils.emptyIfNull(rs.getString("CLR_HELP")));
				dataVo.setClrName(StringUtils.emptyIfNull(rs.getString("CLR_NAME")));
				dataVo.setClrRed(rs.getInt("CLR_RED"));
				dataVo.setClrGreen(rs.getInt("CLR_GREEN"));
				dataVo.setClrBlue(rs.getInt("CLR_BLUE"));
				dataVo.setClrAlpha(rs.getInt("CLR_ALPHA"));
				dataVo.setColor(new Color(dataVo.getClrRed(), dataVo.getClrGreen(), dataVo.getClrBlue()));
				
				mapClrCtl.put(StringUtils.emptyIfNull(rs.getString("CLR_KEY")).trim(), dataVo);
				
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
		
		logger.info("ColorDaoImpl.getMapDClrCtlVo 終了。");
		
		return mapClrCtl;
	}
	

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param userId
	 * @return
	 * @throws DaoException
	 */
	public Map<String, MClrCtlVo> copyClrCtlVo(String userId) throws DaoException {
		
		logger.info("ColorDaoImpl.copyClrCtlVo 開始。");
		
		String strSQL = getSQL(XMLContants.FILE_D_CLRCT, XMLContants.D_CLRCTL004);
		MClrCtlVo dataVo = null;
		DBConnection con = null;
		Map<String, MClrCtlVo> mapClrCtl = new HashMap<String, MClrCtlVo>();
		Map<String, DClrCtlVo> mapCtl = getMapDClrCtlVo();

		try {

			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);

			Collection<?> c = mapCtl.values();
			Iterator<?> itr = c.iterator();
			
			while(itr.hasNext()) {
				dataVo = new MClrCtlVo();
				DClrCtlVo dCtlVo = (DClrCtlVo) itr.next();
				preStmt.setString(1, dCtlVo.getClrKey());
				preStmt.setString(2, userId);
				preStmt.setString(3, dCtlVo.getClrName());
				preStmt.setInt(4, dCtlVo.getClrRed());
				preStmt.setInt(5, dCtlVo.getClrGreen());
				preStmt.setInt(6, dCtlVo.getClrBlue());
				preStmt.setInt(7, dCtlVo.getClrAlpha());
				preStmt.setString(8, dCtlVo.getClrHelp());
				preStmt.setString(9, userId);
				preStmt.setString(10, "");
				preStmt.setInt(11, DateUtils.getCurrentDate());
				preStmt.setInt(12, DateUtils.getITime());
				preStmt.setString(13, userId);
				preStmt.setString(14, "");
				preStmt.setInt(15, DateUtils.getCurrentDate());
				preStmt.setInt(16, DateUtils.getITime());
				preStmt.addBatch();
				
				dataVo = new MClrCtlVo();
				dataVo.setUserId(userId);
				dataVo.setClrKey(dCtlVo.getClrKey());
				dataVo.setClrHelp(dCtlVo.getClrHelp());
				dataVo.setClrName(dCtlVo.getClrName());
				dataVo.setClrRed(dCtlVo.getClrRed());
				dataVo.setClrGreen(dCtlVo.getClrGreen());
				dataVo.setClrBlue(dCtlVo.getClrBlue());
				dataVo.setClrAlpha(dCtlVo.getClrAlpha());
				dataVo.setColor(new Color(dataVo.getClrRed(), dataVo.getClrGreen(), dataVo.getClrBlue()));
				
				mapClrCtl.put(userId + dataVo.getClrKey(), dataVo);
			}
			
			preStmt.executeBatch();

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
		
		logger.info("ColorDaoImpl.copyClrCtlVo 終了。");
		
		return mapClrCtl;
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.dao.color.ColorDao#getDClrCtlVo(java.lang.String)
	 */
	public DClrCtlVo getDClrCtlVo(String clrKey) throws DaoException {
		
		logger.info("ColorDaoImpl.getDClrCtlVo 開始。");
		
		String strSQL = getSQL(XMLContants.FILE_D_CLRCT, XMLContants.D_CLRCTL003);
		DClrCtlVo dataVo = null;
		DBConnection con = null;

		try {

			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			preStmt.setString(1, clrKey);
			ResultSet rs = preStmt.executeQuery();

			if (rs.next()) {
				
				dataVo = new DClrCtlVo();
				dataVo.setClrKey(StringUtils.emptyIfNull(rs.getString("CLR_KEY")).trim());
				dataVo.setClrHelp(StringUtils.emptyIfNull(rs.getString("CLR_HELP")));
				dataVo.setClrName(StringUtils.emptyIfNull(rs.getString("CLR_NAME")));
				dataVo.setClrRed(rs.getInt("CLR_RED"));
				dataVo.setClrGreen(rs.getInt("CLR_GREEN"));
				dataVo.setClrBlue(rs.getInt("CLR_BLUE"));
				dataVo.setClrAlpha(rs.getInt("CLR_ALPHA"));
				dataVo.setColor(new Color(dataVo.getClrRed(), dataVo.getClrGreen(), dataVo.getClrBlue()));
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
		
		logger.info("ColorDaoImpl.getDClrCtlVo 終了。");
		
		return dataVo;
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.dao.color.ColorDao#updateMClrCtlVo(java.lang.String, java.lang.String)
	 */
	public MClrCtlVo updateMClrCtlVo(MClrCtlVo clrVo) throws DaoException {
		
		logger.info("ColorDaoImpl.updateMClrCtlVo 開始。");
		
		String strSQL = getSQL(XMLContants.FILE_D_CLRCT, XMLContants.D_CLRCTL005);
		MClrCtlVo dataVo = null;
		DBConnection con = null;

		try {

			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			preStmt.setInt(1, clrVo.getClrRed());
			preStmt.setInt(2, clrVo.getClrGreen());
			preStmt.setInt(3, clrVo.getClrBlue());
			preStmt.setDouble(4, clrVo.getClrAlpha());
			preStmt.setString(5, StringUtils.emptyIfNull(clrVo.getLastupUser()));
			preStmt.setString(6, StringUtils.emptyIfNull(clrVo.getLastupPc()));
			preStmt.setDouble(7, clrVo.getLastupDate());
			preStmt.setDouble(8, clrVo.getLastupTime());

			preStmt.setString(9, StringUtils.emptyIfNull(clrVo.getClrKey()));
			preStmt.setString(10, StringUtils.emptyIfNull(clrVo.getUserId()));
			preStmt.executeUpdate();
			dataVo = clrVo;
			
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
		
		logger.info("ColorDaoImpl.updateMClrCtlVo 終了。");
		
		return dataVo;
	}

	/* (non-Javadoc)
	 * @see com.pipe.dao.color.ColorDao#getMapMColorVo(java.lang.String)
	 */
	public Map<String, MColorVo> getMapMColorVo(String colorclsCode) throws DaoException {

		logger.info("ColorDaoImpl.getMapMColorVo 開始。");
		
		String strSQL = getSQL(XMLContants.FILE_M_CLRCT, XMLContants.M_CLRCTL501);
		
		strSQL = strSQL.replace("$WHERE", " WHERE COLORCLS_CODE LIKE '%" + colorclsCode + "%'" );
		
		MColorVo dataVo = new MColorVo();
		DBConnection con = null;
		Map<String, MColorVo> mapClrCtl = new HashMap<String, MColorVo>();

		try {

			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			
			ResultSet rs = preStmt.executeQuery();

			while (rs.next()) {
				
				dataVo = new MColorVo();
				dataVo.setColorclsCode(StringUtils.emptyIfNull(rs.getString("COLORCLS_CODE")).trim());
				dataVo.setColorCode(StringUtils.emptyIfNull(rs.getString("COLOR_CODE")));
				dataVo.setColorName(StringUtils.emptyIfNull(rs.getString("COLOR_NAME")));
				dataVo.setColorRname(StringUtils.emptyIfNull(rs.getString("COLOR_RNAME")));
				dataVo.setDefaultType(StringUtils.emptyIfNull(rs.getString("DEFAULT_TYPE")));
				dataVo.setDspFlg(StringUtils.emptyIfNull(rs.getString("DSP_FLG")));
				dataVo.setDsporderNo(rs.getInt("DSPORDER_NO"));
				dataVo.setColortype(StringUtils.emptyIfNull(rs.getString("COLORTYPE")));
				dataVo.setBclrRed(rs.getInt("BCLR_RED"));
				dataVo.setBclrGreen(rs.getInt("BCLR_GREEN"));
				dataVo.setBclrBlue(rs.getInt("BCLR_BLUE"));
				dataVo.setFclrRed(rs.getInt("FCLR_RED"));
				dataVo.setFclrGreen(rs.getInt("FCLR_GREEN"));
				dataVo.setFclrBlue(rs.getInt("FCLR_BLUE"));
				
				dataVo.setAddUser(StringUtils.nulToString(rs.getString("ADD_USER")));
				dataVo.setAddPc(StringUtils.nulToString(rs.getString("ADD_PC")));
				dataVo.setAddDate(rs.getInt("ADD_DATE"));
				dataVo.setAddTime(rs.getInt("ADD_TIME"));
				dataVo.setLastupUser(StringUtils.nulToString(rs.getString("LASTUP_USER")));
				dataVo.setLastupPc(StringUtils.nulToString(rs.getString("LASTUP_PC")));
				dataVo.setLastupDate(rs.getInt("LASTUP_DATE"));
				dataVo.setLastupTime(rs.getInt("LASTUP_TIME"));
				
				mapClrCtl.put(StringUtils.emptyIfNull(rs.getString("COLORCLS_CODE")).trim() + rs.getString("COLOR_CODE").trim(), dataVo);
				
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
		
		logger.info("ColorDaoImpl.getMapMColorVo 終了。");
		
		return mapClrCtl;
	
	}

	/* (non-Javadoc)
	 * @see com.pipe.dao.color.ColorDao#getLstMColorVo(java.lang.String)
	 */
	public List<MColorVo> getLstMColorVo(String colorclsCode) throws DaoException {
		logger.info("ColorDaoImpl.getLstMColorVo 開始。");
		
		String strSQL = getSQL(XMLContants.FILE_M_CLRCT, XMLContants.M_CLRCTL501);
		
		strSQL = strSQL.replace("$WHERE", " WHERE COLORCLS_CODE LIKE '%" + colorclsCode + "%'" );
		
		MColorVo dataVo = new MColorVo();
		DBConnection con = null;
		PreparedStatement preStmt = null;
		List<MColorVo> lstClrCtl = new ArrayList<MColorVo>();

		try {

			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			preStmt = con.prepareStatement(strSQL);
			
			ResultSet rs = preStmt.executeQuery();

			while (rs.next()) {
				
				dataVo = new MColorVo();
				dataVo.setColorclsCode(StringUtils.emptyIfNull(rs.getString("COLORCLS_CODE")).trim());
				dataVo.setColorCode(StringUtils.emptyIfNull(rs.getString("COLOR_CODE")));
				dataVo.setColorName(StringUtils.emptyIfNull(rs.getString("COLOR_NAME")));
				dataVo.setColorRname(StringUtils.emptyIfNull(rs.getString("COLOR_RNAME")));
				dataVo.setDefaultType(StringUtils.emptyIfNull(rs.getString("DEFAULT_TYPE")));
				dataVo.setDspFlg(StringUtils.emptyIfNull(rs.getString("DSP_FLG")));
				dataVo.setDsporderNo(rs.getInt("DSPORDER_NO"));
				dataVo.setColortype(StringUtils.emptyIfNull(rs.getString("COLORTYPE")));
				dataVo.setBclrRed(rs.getInt("BCLR_RED"));
				dataVo.setBclrGreen(rs.getInt("BCLR_GREEN"));
				dataVo.setBclrBlue(rs.getInt("BCLR_BLUE"));
				dataVo.setFclrRed(rs.getInt("FCLR_RED"));
				dataVo.setFclrGreen(rs.getInt("FCLR_GREEN"));
				dataVo.setFclrBlue(rs.getInt("FCLR_BLUE"));
				
				dataVo.setAddUser(StringUtils.nulToString(rs.getString("ADD_USER")));
				dataVo.setAddPc(StringUtils.nulToString(rs.getString("ADD_PC")));
				dataVo.setAddDate(rs.getInt("ADD_DATE"));
				dataVo.setAddTime(rs.getInt("ADD_TIME"));
				dataVo.setLastupUser(StringUtils.nulToString(rs.getString("LASTUP_USER")));
				dataVo.setLastupPc(StringUtils.nulToString(rs.getString("LASTUP_PC")));
				dataVo.setLastupDate(rs.getInt("LASTUP_DATE"));
				dataVo.setLastupTime(rs.getInt("LASTUP_TIME"));
				
				lstClrCtl.add(dataVo);
				
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			if (preStmt != null) {
				try {
					preStmt.close();
				} catch (SQLException e) {
					e.printStackTrace();

				} 
			}			
			if (con != null) {
				try {
					DBConnectionManager.releaseConnection(con);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		logger.info("ColorDaoImpl.getLstMColorVo 終了。");
		
		return lstClrCtl;
	}

	/* (non-Javadoc)
	 * @see com.pipe.dao.color.ColorDao#getLstMColorVo(java.lang.String, int)
	 */
	public List<MColorVo> getLstMColorVo(String strColorclsCode, int iOderBy) throws DaoException 
	{
		//Write log
		logger.info("MColorDaoImpl.getLstColorVo 開始。");
		//M_COLOR001 Get list ColorVo by User and Pass
		String strSQL = getSQL(XMLContants.FILE_M_COLOR, XMLContants.M_COLOR500);
		DBConnection con = null;
		List<MColorVo> lstVo = new ArrayList<MColorVo>();
		MColorVo dataVo = null;		

		try {
			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			preStmt.setString(1, strColorclsCode);
			ResultSet rs = null;
			rs = preStmt.executeQuery();

			while (rs.next()) 
			{
				dataVo = new MColorVo();
				dataVo.setColorclsCode(StringUtils.nulToString(rs.getString("COLORCLS_CODE")));
				dataVo.setColorCode(StringUtils.nulToString(rs.getString("COLOR_CODE")));
				dataVo.setColorName(StringUtils.nulToString(rs.getString("COLOR_NAME")));
				dataVo.setColorRname(StringUtils.nulToString(rs.getString("COLOR_RNAME")));
				dataVo.setDefaultType(StringUtils.nulToString(rs.getString("DEFAULT_TYPE")));
				dataVo.setDspFlg(StringUtils.nulToString(rs.getString("DSP_FLG")));
				dataVo.setDsporderNo(rs.getInt("DSPORDER_NO"));
				dataVo.setColortype(StringUtils.nulToString(rs.getString("COLORTYPE")));
				dataVo.setBclrRed(rs.getInt("BCLR_RED"));
				dataVo.setBclrGreen(rs.getInt("BCLR_GREEN"));
				
				dataVo.setBclrBlue(rs.getInt("BCLR_BLUE"));
				dataVo.setFclrRed(rs.getInt("FCLR_RED"));
				dataVo.setFclrGreen(rs.getInt("FCLR_GREEN"));
				dataVo.setFclrBlue(rs.getInt("FCLR_BLUE"));
				
				dataVo.setAddUser(StringUtils.nulToString(rs.getString("ADD_USER")));
				dataVo.setAddPc(StringUtils.nulToString(rs.getString("ADD_PC")));								
				dataVo.setAddDate(rs.getInt("ADD_DATE"));
				dataVo.setAddTime(rs.getInt("ADD_TIME"));
				dataVo.setLastupUser(StringUtils.nulToString(rs.getString("LASTUP_USER")));				
				dataVo.setLastupPc(StringUtils.nulToString(rs.getString("LASTUP_PC")));				
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
		
		logger.info("MColorDaoImpl.getLstColorVo 終了。");
		
		return lstVo;	
	}

	/* (non-Javadoc)
	 * @see com.pipe.dao.color.ColorDao#getCountVo(java.lang.String)
	 */
	public int getCountVo(String strColorclsCode) throws DaoException {
		
		logger.info("MColorDaoImpl.getCountVo 開始。");
		
		String strSQL = getSQL(XMLContants.FILE_M_COLOR, XMLContants.M_COLOR501);
		DBConnection con = null;
		int iCount = 0;

		try {

			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			
			int i=1;			
			preStmt.setString(i++, StringUtils.emptyIfNull(strColorclsCode));
			
			ResultSet rs = preStmt.executeQuery();

			if (rs.next()) {
				iCount = rs.getInt(1);
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
		
		logger.info("MColorDaoImpl.getCountVo 終了。");
		
		return iCount;			
		
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.dao.color.ColorDao#getMaxColorCode(java.lang.String)
	 */
	public String getMaxColorCode(String strColorclsCode) throws DaoException {
		
		logger.info("MColorDaoImpl.getMaxColorCode 開始。");
		
		String strSQL = getSQL(XMLContants.FILE_M_COLOR, XMLContants.M_COLOR502);
		DBConnection con = null;
		
		String maxColorCode = "";

		try {

			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			
			int i=1;			
			preStmt.setString(i++, StringUtils.emptyIfNull(strColorclsCode));
						
			ResultSet rs = preStmt.executeQuery();

			if (rs.next()) {
				maxColorCode = StringUtils.nulToString(rs.getString("COLOR_CODE"));
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
		
		logger.info("MColorDaoImpl.getMaxColorCode 終了。");
		
		return maxColorCode;
		
	}

	/* (non-Javadoc)
	 * @see com.pipe.dao.color.ColorDao#updateMColorVo(com.pipe.vo.color.MColorVo)
	 */
	public boolean updateMColorVo(MColorVo dataVo) throws DaoException
	{
		logger.info("MColorDaoImpl.updateColorVo 開始。");
		
		String strSQL = getSQL(XMLContants.FILE_M_COLOR, XMLContants.M_COLOR504);
		DBConnection con = null;
		boolean isSuccess = true;
		try {
			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);		
			
			preStmt.setString(1, StringUtils.emptyIfNull(dataVo.getColorName()));
			preStmt.setString(2, StringUtils.emptyIfNull(dataVo.getColorRname()));			
			preStmt.setString(3, StringUtils.emptyIfNull(dataVo.getDspFlg()));
			preStmt.setInt(4, dataVo.getDsporderNo());
			
			preStmt.setInt(5, dataVo.getBclrRed());
			preStmt.setInt(6, dataVo.getBclrGreen());
			preStmt.setInt(7, dataVo.getBclrBlue());
			preStmt.setInt(8, dataVo.getFclrRed());			
			preStmt.setInt(9, dataVo.getFclrGreen());
			preStmt.setInt(10, dataVo.getFclrBlue());			
						
			preStmt.setString(11, StringUtils.emptyIfNull(dataVo.getLastupUser()));
			preStmt.setString(12, StringUtils.emptyIfNull(dataVo.getLastupPc()));
			preStmt.setInt(13, dataVo.getLastupDate());
			preStmt.setInt(14, dataVo.getLastupTime());
			//Where Condition
			preStmt.setString(15, StringUtils.emptyIfNull(dataVo.getColorclsCode()));
			preStmt.setString(16, StringUtils.emptyIfNull(dataVo.getColorCode()));

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
		logger.info("MColorDaoImpl.updateColorVo 終了。");
		
		return isSuccess;
	}

	/* (non-Javadoc)
	 * @see com.pipe.dao.color.ColorDao#exportExcel()
	 */
	public ArrayList<ArrayList<String>> exportExcel() throws DaoException {
		logger.info("MColorDaoImpl.export 開始。");

		String strSQL = getSQL(XMLContants.FILE_M_COLOR, XMLContants.M_COLOR506);
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		ExportExcelService ex = new ExportExcelServiceImpl();		
		try {
			try
			{
				ResultSet rs = ex.export(strSQL);		
				
				ResultSetMetaData mtdt = rs.getMetaData();
				int count = mtdt.getColumnCount();
				int iRow = 0;
				ArrayList<String>  arrTmp = new ArrayList<String>();
				for(int i = 1; i<=count;i++)
				{				
					arrTmp.add(mtdt.getColumnName(i));
				}
				result.add(arrTmp);
				//add row to file EXCEL
				while (rs.next()) 
				{
					iRow ++;
					arrTmp = new ArrayList<String>();
					for(int i = 1; i<=count;i++)
					{
						arrTmp.add(StringUtils.nulToString(rs.getString(i)));
					}
					result.add(arrTmp);
				}
			} 
			catch(SQLException ex1)
			{}
		} catch (BizException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("MColorDaoImpl.export 終了。");

		return result;
	}

	/* (non-Javadoc)
	 * @see com.pipe.dao.color.ColorDao#getMColorVo(java.lang.String, java.lang.String)
	 */
	public MColorVo getMColorVo(String strColorClsCode, String strColorCode) throws DaoException{
		logger.info("MColorDaoImpl.getMColorVo 開始。");
		
		String strSQL = getSQL(XMLContants.FILE_M_COLOR, XMLContants.M_COLOR507);
		MColorVo dataVo = new MColorVo();
		DBConnection con = null;

		try {

			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			
			preStmt.setString(1, StringUtils.nulToString(strColorClsCode));
			preStmt.setString(2, StringUtils.nulToString(strColorCode));
			
			ResultSet rs = preStmt.executeQuery();
			if (rs.next()) {
				dataVo.setColorclsCode(StringUtils.nulToString(rs.getString("COLORCLS_CODE")));
				dataVo.setColorCode(StringUtils.nulToString(rs.getString("COLOR_CODE")));
				dataVo.setColorName(StringUtils.nulToString(rs.getString("COLOR_NAME")));
				dataVo.setColorRname(StringUtils.nulToString(rs.getString("COLOR_RNAME")));
				dataVo.setDefaultType(StringUtils.nulToString(rs.getString("DEFAULT_TYPE")));
				dataVo.setDspFlg(StringUtils.nulToString(rs.getString("DSP_FLG")));
				dataVo.setDsporderNo(rs.getInt("DSPORDER_NO"));
				dataVo.setColortype(StringUtils.nulToString(rs.getString("COLORTYPE")));
				dataVo.setBclrRed(rs.getInt("BCLR_RED"));
				dataVo.setBclrGreen(rs.getInt("BCLR_GREEN"));
				dataVo.setBclrBlue(rs.getInt("BCLR_BLUE"));
				dataVo.setFclrRed(rs.getInt("FCLR_RED"));
				dataVo.setFclrGreen(rs.getInt("FCLR_GREEN"));
				dataVo.setFclrBlue(rs.getInt("FCLR_BLUE"));				
			} else {
				dataVo = null;
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
		
		logger.info("MColorDaoImpl.getMColorVo 終了。");
		
		return dataVo;
	}
	
	public boolean insertColorVo(MColorVo dataVo) throws DaoException{
		logger.info("MColorDaoImpl.insertColorVo 開始。");
		
		String strSQL = getSQL(XMLContants.FILE_M_COLOR, XMLContants.M_COLOR503);
		DBConnection con = null;
		boolean isSuccess = true;

		try {

			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			
			preStmt.setString(1, StringUtils.emptyIfNull(dataVo.getColorclsCode()));			
			preStmt.setString(2, StringUtils.emptyIfNull(dataVo.getColorCode()));
			preStmt.setString(3, StringUtils.emptyIfNull(dataVo.getColorName()));
			preStmt.setString(4, StringUtils.emptyIfNull(dataVo.getColorRname()));
			
			preStmt.setString(5, StringUtils.emptyIfNull(dataVo.getDefaultType()));			
			preStmt.setString(6, StringUtils.emptyIfNull(dataVo.getDspFlg()));
			preStmt.setInt(7,dataVo.getDsporderNo());
			preStmt.setString(8, StringUtils.emptyIfNull(dataVo.getColortype()));			
			
			preStmt.setInt(9, dataVo.getBclrRed());
			preStmt.setInt(10, dataVo.getBclrGreen());
			preStmt.setInt(11, dataVo.getBclrBlue());
			preStmt.setInt(12, dataVo.getFclrRed());
			preStmt.setInt(13, dataVo.getFclrGreen());
			preStmt.setInt(14, dataVo.getFclrBlue());
		
			preStmt.setString(15, StringUtils.emptyIfNull(dataVo.getAddUser()));
			preStmt.setString(16, StringUtils.emptyIfNull(dataVo.getAddPc()));
			preStmt.setInt(17, dataVo.getAddDate());
			preStmt.setInt(18, dataVo.getAddTime());
			preStmt.setString(19, StringUtils.emptyIfNull(dataVo.getLastupUser()));
			preStmt.setString(20, StringUtils.emptyIfNull(dataVo.getLastupPc()));
			preStmt.setInt(21, dataVo.getLastupDate());
			preStmt.setInt(22, dataVo.getLastupTime());			
			
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
		
		logger.info("MColorDaoImpl.insertColorVo 終了。");
		
		return isSuccess;	
	}

	public boolean delColorVo(MColorVo dataVo) throws DaoException {
		logger.info("MColorDaoImpl.delColorVo 開始。");
		
		String strSQL = getSQL(XMLContants.FILE_M_COLOR, XMLContants.M_COLOR508);
		DBConnection con = null;
		boolean isSuccess = true;

		try {

			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			
			preStmt.setString(1, StringUtils.emptyIfNull(dataVo.getColorclsCode()));
			preStmt.setString(2, StringUtils.emptyIfNull(dataVo.getColorCode()));
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
		
		logger.info("MColorDaoImpl.delColorVo 終了。");
		
		return isSuccess;
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.dao.color.ColorDao#countAllColorVo()
	 */
	public int countAllColorVo() throws DaoException {
		logger.info("MColorDaoImpl.countAllColorVo 開始。");
		
		String strSQL = getSQL(XMLContants.FILE_M_COLOR, XMLContants.M_COLOR510);
		DBConnection con = null;
		int iCount = 0;

		try {

			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			ResultSet rs = preStmt.executeQuery();

			if (rs.next()) {
				iCount = rs.getInt(1);
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
		
		logger.info("MColorDaoImpl.countAllColorVo 終了。");
		
		return iCount;
	}

	@Override
	public boolean deleteClrCtlByUserID(String strUserID) throws DaoException {
		logger.info("ColorDaoImpl.deleteClrCtlByUserID 開始。");
		
		String strSQL = getSQL(XMLContants.FILE_M_CLRCT, XMLContants.M_CLRCTL502);
		DBConnection con = null;
		boolean isSuccess = true;
		try {
			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			preStmt.setString(1, StringUtils.emptyIfNull(strUserID));
			
			preStmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
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
		
		logger.info("ColorDaoImpl.deleteClrCtlByUserID 終了。");
		
		return isSuccess;
	}
}
