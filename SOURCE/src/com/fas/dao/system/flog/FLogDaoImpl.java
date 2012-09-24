/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：LogDaoImpl.java
*
*     記述				：
*     
*     作成日			：2009/10/19   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/

package com.fas.dao.system.flog;

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
import com.fas.common.utils.DateUtils;
import com.fas.common.utils.StringUtils;
import com.fas.dao.base.BaseDao;
import com.fas.vo.base.SortObjVo;
import com.fas.vo.flog.FLogConditionVo;
import com.fas.vo.flog.FLogVo;

/**
 * <DL>
 *   <DT>クラス記述：</DT>
 * 	<DD></DD>
 * <BR>
 *   <DT>変更歴史：</DT>
 * 	<DD>著作者: PC13</DD><BR>
 * 	<DD></DD>
 * </DL>
 */

public class FLogDaoImpl extends BaseDao implements FLogDao {
	
	/** */
	static Logger logger = Logger.getLogger(FLogDaoImpl.class);
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>	 
	 */
	public FLogDaoImpl() {
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>	 
	 */
	public boolean create(FLogVo logInfo) throws DaoException {
		
		logger.info("LogDaoImpl.create 開始。");

		String strSQL = getSQL(XMLContants.FILE_F_LOG, XMLContants.F_LOG500);

		DBConnection con = null;

		try {

			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			int i=1;
			preStmt.setString(i++, logInfo.getUserUser());
			preStmt.setInt(i++, logInfo.getActDate());
			preStmt.setInt(i++, logInfo.getActTime());
			preStmt.setString(i++, logInfo.getMenugrpCode());
			preStmt.setString(i++, logInfo.getMenuexeCode());
			preStmt.setString(i++, logInfo.getActionType());
			preStmt.setString(i++, logInfo.getText());
			preStmt.setString(i++, logInfo.getPcid());

			preStmt.executeUpdate();

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
		
		logger.info("LogDaoImpl.create 終了。");
		
		return true;		
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>	 
	 */
	public List<FLogVo> getLstLogInfor(SortObjVo sortObj) throws DaoException {
		
		logger.info("LogDaoImpl.getLstLogInfor 開始。");

		String strSQL = getSQL(XMLContants.FILE_F_LOG, XMLContants.F_LOG004);
		DBConnection con = null;
		FLogVo dataVo;
		List<FLogVo> lstData = new ArrayList<FLogVo>();
		String strWhere = "";
		String strOrderBy = "";
		
		try {

			con = DBConnectionManager.getConnection();
			
			strWhere = " WHERE L.ACT_DATE = " + DateUtils.getCurrentDate();
			
//			if (sortObj.getM_sortCol() == 0) {
//				strOrderBy = " Order By L.ACT_TIME";
//			} else if (sortObj.getM_sortCol() == 1) {
//				strOrderBy = " Order By U.EMP_TNAME";
//			} else if (sortObj.getM_sortCol() == 2) {
//				strOrderBy = " Order By U.EMP_TNAME";
//			} else if (sortObj.getM_sortCol() == 3) {
//				strOrderBy = " Order By A.NAME_NAME";
//			} else if (sortObj.getM_sortCol() == 4) {
//				strOrderBy = " Order By L.TEXT";
//			} else {
//				strOrderBy = " Order By L.ACT_TIME";
//			}
//			
//			if (sortObj.isM_sortAsc()) {
//				strOrderBy += " ASC ";
//			} else {
//				strOrderBy += " DESC ";
//			}
			
			strOrderBy = " Order By L.ACT_TIME DESC";
			
			strSQL = strSQL + strWhere + strOrderBy;
			
			logger.info(strSQL);
			
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			
			ResultSet rs = preStmt.executeQuery();
			
			while (rs.next()) {
				
				dataVo = new FLogVo();
				
				dataVo.setUserUser(StringUtils.emptyIfNull(rs.getString("EMP_USER")));
				dataVo.setPcid(StringUtils.emptyIfNull(rs.getString("PCNAME")));
				dataVo.setMenugrpCode(StringUtils.emptyIfNull(rs.getString("MENUEXE_NAME")));
				dataVo.setMenuexeCode(StringUtils.emptyIfNull(rs.getString("MENUEXE_NAME")));
				dataVo.setActionType(StringUtils.emptyIfNull(rs.getString("ACTION_TYPE")));
				dataVo.setText(StringUtils.emptyIfNull(rs.getString("MEMO")));
				dataVo.setActDate(rs.getInt("ACT_DATE"));
				dataVo.setActTime(rs.getInt("ACT_TIME"));
				
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
		
		logger.info("LogDaoImpl.getLstLogInfor 終了。");
		
		return lstData;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>	 
	 */
	public List<FLogVo> getLstLogInfor(FLogConditionVo conditionVo)
			throws DaoException {
		
		logger.info("LogDaoImpl.conditionVo 開始。");

		String strSQL = getSQL(XMLContants.FILE_F_LOG, XMLContants.F_LOG510);
		if(!conditionVo.isLogForUser())
		{
			strSQL = getSQL(XMLContants.FILE_F_LOG, XMLContants.F_LOG511);
		}
		DBConnection con = null;
		FLogVo dataVo;
		List<FLogVo> lstData = new ArrayList<FLogVo>();
		String strWhere = "WHERE 1 = 1";
		String strOrderBy = "";		
		try {
			con = DBConnectionManager.getConnection();
			if(conditionVo.getDateFrom() > 0 && conditionVo.getDateTo() > 0)
				strWhere += " AND F.ACT_DATE >= " + conditionVo.getDateFrom() +" AND F.ACT_DATE <= "+conditionVo.getDateTo();
			if(conditionVo.getTimeFrom() >= 0 && conditionVo.getTimeTo() >= 0)
				strWhere += " AND F.ACT_TIME >= " + conditionVo.getTimeFrom() +" AND F.ACT_TIME <= "+conditionVo.getTimeTo();
			if(StringUtils.isValid(conditionVo.getName()))
				{
				if(conditionVo.isLogForUser())
					strWhere += " AND TBL.EMP_TNAME = '"+conditionVo.getName()+"'";
				else
					strWhere += " AND U.USERTXT = '"+conditionVo.getName()+"'";
				}
			if(StringUtils.isValid(conditionVo.getMenuExeGrp()) && StringUtils.isValid(conditionVo.getMenuExeCode()))
				strWhere += " AND M.MENUGRP_CODE = '"+conditionVo.getMenuExeGrp()+"' AND M.MENUEXE_CODE = '"+conditionVo.getMenuExeCode()+"' " ;
			if(!(conditionVo.isCheckAll()|| conditionVo.isDontCheck()))
			{					
				String strChk ="NM.NAME_CODE IN( ";
				if(conditionVo.isAD())
					strChk += "'AD',";
				if(conditionVo.isUP())
					strChk += "'UP',";
				if(conditionVo.isDE())
					strChk += "'DE',";
				if(conditionVo.isCL())
					strChk += "'CL',";
				if(conditionVo.isIN())
					strChk += "'IN',";
				if(conditionVo.isLI())
					strChk += "'LI',";
				if(conditionVo.isLO())
					strChk += "'LO',";
				if(conditionVo.isOP())
					strChk += "'OP',";
				strChk = strChk.substring(0, (strChk.length()-1));
				strChk +=")";
				strWhere +=" AND "+strChk;
			}			
			if(StringUtils.isValid(conditionVo.getPCName()))
			strWhere +=" AND F.PCID LIKE '"+conditionVo.getPCName()+"%'";			
			strOrderBy = " Order By F.ACT_DATE,F.ACT_TIME DESC";			
			strSQL = strSQL + strWhere + strOrderBy;			
			logger.info(strSQL);			
			PreparedStatement preStmt = con.prepareStatement(strSQL);			
			ResultSet rs = preStmt.executeQuery();			
			while (rs.next()) {				
				dataVo = new FLogVo();				
				dataVo.setUserUser(StringUtils.emptyIfNull(rs.getString("EMP_NAME")));
				dataVo.setPcid(StringUtils.emptyIfNull(rs.getString("PCID")));
				dataVo.setMenugrpCode(StringUtils.emptyIfNull(rs.getString("MENUEXE_NAME")));
				dataVo.setMenuexeCode(StringUtils.emptyIfNull(rs.getString("MENUEXE_NAME")));
				dataVo.setActionType(StringUtils.emptyIfNull(rs.getString("ACTION_NAME")));
				dataVo.setText(StringUtils.emptyIfNull(rs.getString("TEXT")));
				dataVo.setActDate(rs.getInt("ACT_DATE"));
				dataVo.setActTime(rs.getInt("ACT_TIME"));
				
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
		logger.info("LogDaoImpl.conditionVo 終了。");		
		return lstData;
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>	 
	 */
	public int countLogInfor(FLogConditionVo conditionVo) throws DaoException {
		logger.info("LogDaoImpl.countLogInfor 開始。");
		int iCount = 0;
		String strSQL = getSQL(XMLContants.FILE_F_LOG, XMLContants.F_LOG514);
		if(!conditionVo.isLogForUser())
		{
			strSQL = getSQL(XMLContants.FILE_F_LOG, XMLContants.F_LOG515);
		}
		DBConnection con = null;
		String strWhere = "WHERE 1 = 1";
		try {
			con = DBConnectionManager.getConnection();
			if(conditionVo.getDateFrom() > 0 && conditionVo.getDateTo() > 0)
				strWhere += " AND F.ACT_DATE >= " + conditionVo.getDateFrom() +" AND F.ACT_DATE <= "+conditionVo.getDateTo();
			if(conditionVo.getTimeFrom() >= 0 && conditionVo.getTimeTo() >= 0)
				strWhere += " AND F.ACT_TIME >= " + conditionVo.getTimeFrom() +" AND F.ACT_TIME <= "+conditionVo.getTimeTo();
			if(StringUtils.isValid(conditionVo.getName()))
				{
				if(conditionVo.isLogForUser())
					strWhere += " AND TBL.EMP_TNAME = '" + conditionVo.getName()+"'";
				else
					strWhere += " AND U.USERTXT = '" + conditionVo.getName()+"'";
				}
			if(StringUtils.isValid(conditionVo.getMenuExeGrp())&& StringUtils.isValid(conditionVo.getMenuExeCode()))
				strWhere += " AND M.MENUGRP_CODE = '" + conditionVo.getMenuExeGrp() + "' AND M.MENUEXE_CODE = '" + conditionVo.getMenuExeCode()+"' " ;
			if(!(conditionVo.isCheckAll()|| conditionVo.isDontCheck()))
			{
				String strChk ="NM.NAME_CODE IN( ";
				if(conditionVo.isAD())
					strChk += "'AD',";
				if(conditionVo.isUP())
					strChk += "'UP',";
				if(conditionVo.isDE())
					strChk += "'DE',";
				if(conditionVo.isCL())
					strChk += "'CL',";
				if(conditionVo.isIN())
					strChk += "'IN',";
				if(conditionVo.isLI())
					strChk += "'LI',";
				if(conditionVo.isLO())
					strChk += "'LO',";
				if(conditionVo.isOP())
					strChk += "'OP',";
				strChk = strChk.substring(0, (strChk.length()-1));
				strChk +=")";
				strWhere +=" AND "+strChk;
			}			
			if(StringUtils.isValid(conditionVo.getPCName()))
				strWhere +=" AND F.PCID LIKE '"+conditionVo.getPCName()+"%'";			
			strSQL = strSQL + strWhere;			
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
		logger.info("LogDaoImpl.countLogInfor 終了。");		
		return iCount;
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>	 
	 */
	public int countVoByUserUser( String strUserUser ) throws DaoException {
		
		logger.info("MLogDaoImpl.countVoByUserName 開始。");		
		String strSQL = getSQL(XMLContants.FILE_F_LOG, XMLContants.F_LOG516);
		String strWhere = " WHERE 1=1 ";
		strWhere += " AND USER_USER = '" + strUserUser.toString() + "'";
		strSQL += strWhere;
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
		
		logger.info("MLogDaoImpl.countVoByUserName 終了。");		
		return iCount;			
		
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return int
	 * @throws DaoException
	 */
	public List<FLogVo> exportEXCEL(FLogConditionVo conditionVo) throws DaoException{
		// TODO Auto-generated method stub		 
		logger.info("FLogDaoImpl.exportEXCEL 開始。");
		//Get strSQL by Condition, User Group
		String strSQL = getSQL(XMLContants.FILE_F_LOG, XMLContants.F_LOG009);
		if(!conditionVo.isLogForUser()) //User
		{
			strSQL = getSQL(XMLContants.FILE_F_LOG, XMLContants.F_LOG010);
		}
		
		String strWhere = "WHERE 1 = 1";
		String strOrderBy = "";
		
		if(conditionVo.getDateFrom() > 0 && conditionVo.getDateTo() > 0)
			strWhere += " AND F.ACT_DATE >= " + conditionVo.getDateFrom() +" AND F.ACT_DATE <= "+conditionVo.getDateTo();
		if(conditionVo.getTimeFrom() >= 0 && conditionVo.getTimeTo() >= 0)
			strWhere += " AND F.ACT_TIME >= " + conditionVo.getTimeFrom() +" AND F.ACT_TIME <= "+conditionVo.getTimeTo();
		if(StringUtils.isValid(conditionVo.getName()))
			{
			if(conditionVo.isLogForUser())
				strWhere += " AND E.EMP_TNAME = '"+conditionVo.getName()+"'";
			else
				strWhere += " AND U.USERTXT = '"+conditionVo.getName()+"'";
			}
		if(StringUtils.isValid(conditionVo.getMenuExeGrp())&& StringUtils.isValid(conditionVo.getMenuExeCode()) )
			strWhere += " AND M.MENUGRP_CODE = '"+conditionVo.getMenuExeGrp()+"' AND M.MENUEXE_CODE = '"+conditionVo.getMenuExeCode()+"' " ;
		if(!(conditionVo.isCheckAll()|| conditionVo.isDontCheck()))
		{
			String strChk ="NM.NAME_CODE IN(NULL";			
			if(conditionVo.isAD())
				strChk += ",'AD'";
			else if(conditionVo.isUP())
				strChk += ",'UP'";
			else if(conditionVo.isDE())
				strChk += ",'DE'";
			else if(conditionVo.isCL())
				strChk += ",'CL'";
			else if(conditionVo.isIN())
				strChk += ",'IN'";
			else if(conditionVo.isLI())
				strChk += ",'LI'";
			else if(conditionVo.isLO())
				strChk += ",'LO'";
			else if(conditionVo.isOP())
				strChk += ",'OP'";				
			strChk +=")";
				strWhere +=" AND "+strChk;
			}
		if(StringUtils.isValid(conditionVo.getPCName()))
		strWhere +=" AND F.PCID LIKE '"+conditionVo.getPCName()+"%'";
		strOrderBy = " ORDER BY F.ACT_DATE,F.ACT_TIME DESC";
		strSQL = strSQL + strWhere + strOrderBy;
		logger.info(strSQL);			
		//End get strSQL
		
		FLogVo dataVo = null;
		DBConnection con = null;
		List<FLogVo> lstFLogVo = new ArrayList<FLogVo>();
		try {
			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);;
			ResultSet rs = preStmt.executeQuery();
			while (rs.next()) {
				dataVo = new FLogVo();				
				dataVo.setActDate(rs.getInt("ACT_DATE"));
				dataVo.setActTime(rs.getInt("ACT_TIME"));
				dataVo.setUserUser(StringUtils.nulToString(rs.getString("USER_USER")));
				dataVo.setMenuexeCode(StringUtils.nulToString(rs.getString("MENUEXE_CODE")));
				dataVo.setActionType(StringUtils.nulToString(rs.getString("ACTION_TYPE")));
				dataVo.setText(StringUtils.nulToString(rs.getString("TEXT")));
				dataVo.setPcid(StringUtils.nulToString(rs.getString("PCID")));
				dataVo.setEmpCode(StringUtils.nulToString(rs.getString("EMP_CODE")));
				dataVo.setUserType(StringUtils.nulToString(rs.getString("USER_TYPE")));
				lstFLogVo.add(dataVo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		} finally {
			if (con != null) {
				try {
					DBConnectionManager.releaseConnection(con);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		logger.info("FLogDaoImpl.exportEXCEL 終了。");		
		return lstFLogVo;
	}
}