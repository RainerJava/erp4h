/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名		：エスイーシー化成
* 
*     ファイル名		：MOutCtlDaoImpl.java
*
*     記述			：
*     
*     作成日			：Apr 9, 2010   
*
*     作成者			：NVH
*
*     備考			：
*
**************************************************************************************/

package com.fas.dao.system.moutctl;

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
import com.fas.vo.moutctl.MOutCtlVo;
import com.fas.vo.outctl.OutCtlVo;

/**
 * <DL>
 *   <DT>クラス記述：</DT>
 * 	<DD></DD>
 * <BR>
 *   <DT>変更歴史：</DT>
 * 	<DD>著作者: NVH</DD><BR>
 * 	<DD></DD>
 * </DL>
 */
public class MOutCtlDaoImpl extends BaseDao implements MOutCtlDao {

	/** */
	static Logger logger = Logger.getLogger(MOutCtlDaoImpl.class);
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 * </DL>
	 */
	public MOutCtlDaoImpl() {
	}

	/* (non-Javadoc)
	 * @see com.pipe.dao.moutctl.MOutCtlDao#getMapMOutCtlVo()
	 */
	public Map<String, MOutCtlVo> getMapMOutCtlVo() throws DaoException {
		
		logger.info("MOutCtlDaoImpl.getMapMOutCtlVo 開始。");
		
		String strSQL = getSQL(XMLContants.FILE_M_OUTCTL, XMLContants.M_OUTCTL001);
		DBConnection con = null;
		Map<String, MOutCtlVo> mapCtl = new HashMap<String, MOutCtlVo>();
		MOutCtlVo dataVo = null;

		try {

			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			ResultSet rs = null;
			rs = preStmt.executeQuery();

			while (rs.next()) {
				dataVo = new MOutCtlVo();
				dataVo.setUserId(StringUtils.nulToString(rs.getString("USERID")));
				dataVo.setOut1Path(StringUtils.nulToString(rs.getString("OUT1_PATH")));
				dataVo.setOut2Path(StringUtils.nulToString(rs.getString("OUT2_PATH")));
				dataVo.setOut3Path(StringUtils.nulToString(rs.getString("OUT3_PATH")));
				dataVo.setOut4Path(StringUtils.nulToString(rs.getString("OUT4_PATH")));
				dataVo.setOut5Path(StringUtils.nulToString(rs.getString("OUT5_PATH")));
				dataVo.setAddUser(StringUtils.nulToString(rs.getString("ADD_USER")));
				dataVo.setAddPc(StringUtils.nulToString(rs.getString("ADD_PC")));
				dataVo.setAddDate(rs.getInt("ADD_DATE"));
				dataVo.setAddTime(rs.getInt("ADD_TIME"));
				dataVo.setLastupUser(StringUtils.nulToString(rs.getString("LASTUP_USER")));
				dataVo.setLastupPc(StringUtils.nulToString(rs.getString("LASTUP_PC")));
				dataVo.setLastupDate(rs.getInt("LASTUP_DATE"));
				dataVo.setLastupTime(rs.getInt("LASTUP_TIME"));
				mapCtl.put(StringUtils.nulToString(rs.getString("USERID")), dataVo);
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
		
		logger.info("MOutCtlDaoImpl.getMapMOutCtlVo 終了。");
		
		return mapCtl;			
	}
	
	public OutCtlVo getOutCtlVoByUserId(String userId) throws DaoException {
		logger.info("OutCtlDaoImpl.getOutCtlVoByUserId 開始。");
		
		String strSQL = getSQL(XMLContants.FILE_M_OUTCTL, XMLContants.M_OUTCTL004);
		OutCtlVo dataVo = new OutCtlVo();
		DBConnection con = null;

		try {

			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			
			preStmt.setString(1, StringUtils.nulToString(userId));
			
			ResultSet rs = preStmt.executeQuery();
			if (rs.next()) {
				dataVo.setUserId(StringUtils.nulToString(rs.getString("USERID")));
				dataVo.setOut1Path(StringUtils.nulToString(rs.getString("OUT1_PATH")));
				dataVo.setOut2Path(StringUtils.nulToString(rs.getString("OUT2_PATH")));
				dataVo.setOut3Path(StringUtils.nulToString(rs.getString("OUT3_PATH")));
				dataVo.setOut4Path(StringUtils.nulToString(rs.getString("OUT4_PATH")));
				dataVo.setOut5Path(StringUtils.nulToString(rs.getString("OUT5_PATH")));
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
		
		logger.info("OutCtlDaoImpl.getOutCtlVoByUserId 終了。");
		
		return dataVo;
	}

	public boolean insertOutCtlVo(OutCtlVo dataVo) throws DaoException {
		logger.info("OutCtlDaoImpl.insertOutCtlVo 開始。");
		
		String strSQL = getSQL(XMLContants.FILE_M_OUTCTL, XMLContants.M_OUTCTL006);
		DBConnection con = null;
		boolean isSuccess = true;

		try {

			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			
			preStmt.setString(1, StringUtils.emptyIfNull(dataVo.getUserId()));
			
			preStmt.setString(2, StringUtils.emptyIfNull(dataVo.getOut1Path()));
			preStmt.setString(3, StringUtils.emptyIfNull(dataVo.getOut2Path()));
			preStmt.setString(4, StringUtils.emptyIfNull(dataVo.getOut3Path()));
			preStmt.setString(5, StringUtils.emptyIfNull(dataVo.getOut4Path()));
			preStmt.setString(6, StringUtils.emptyIfNull(dataVo.getOut5Path()));		
		
			preStmt.setString(7, StringUtils.emptyIfNull(dataVo.getAddUser()));
			preStmt.setString(8, StringUtils.emptyIfNull(dataVo.getAddPc()));
			preStmt.setInt(9, dataVo.getAddDate());
			preStmt.setInt(10, dataVo.getAddTime());
			preStmt.setString(11, StringUtils.emptyIfNull(dataVo.getLastupUser()));
			preStmt.setString(12, StringUtils.emptyIfNull(dataVo.getLastupPc()));
			preStmt.setInt(13, dataVo.getLastupDate());
			preStmt.setInt(14, dataVo.getLastupTime());
			
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
		
		logger.info("OutCtlDaoImpl.insertOutCtlVo 終了。");
		
		return isSuccess;	
	}

	public boolean updateOutCtlVo(OutCtlVo dataVo) throws DaoException {
		logger.info("OutCtlDaoImpl.updateOutCtlVo 開始。");
		
		String strSQL = getSQL(XMLContants.FILE_M_OUTCTL, XMLContants.M_OUTCTL005);
		DBConnection con = null;
		boolean isSuccess = true;

		try {

			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			
			preStmt.setString(1, StringUtils.emptyIfNull(dataVo.getOut1Path()));
			preStmt.setString(2, StringUtils.emptyIfNull(dataVo.getOut2Path()));
			preStmt.setString(3, StringUtils.emptyIfNull(dataVo.getOut3Path()));
			preStmt.setString(4, StringUtils.emptyIfNull(dataVo.getOut4Path()));
			preStmt.setString(5, StringUtils.emptyIfNull(dataVo.getOut5Path()));
			
			preStmt.setString(6, StringUtils.emptyIfNull(dataVo.getLastupUser()));
			preStmt.setString(7, StringUtils.emptyIfNull(dataVo.getLastupPc()));
			preStmt.setInt(8, dataVo.getLastupDate());
			preStmt.setInt(9, dataVo.getLastupTime());
			
			preStmt.setString(10, StringUtils.emptyIfNull(dataVo.getUserId()));			
			preStmt.executeUpdate();

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
		
		logger.info("OutCtlDaoImpl.updateOutCtlVo 終了。");
		
		return isSuccess;		
	}
}
