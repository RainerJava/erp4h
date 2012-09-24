/** *********************************************************************************
 *     
 *     会社名			：林兼コンピューター株式会社
 *
 *     プロジェクト名	：
 * 
 *     ファイル名		：SearchDaoImpl.java
 *
 *     記述				：
 *     
 *     作成日			：2010/02/12   
 *
 *     作成者			：PC13
 *
 *     備考				：
 *
 **************************************************************************************/
package com.fas.dao.search;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.fas.common.DBConnection;
import com.fas.common.DBConnectionManager;
import com.fas.common.constants.DataTypeConstants;
import com.fas.common.constants.XMLContants;
import com.fas.common.exception.DaoException;
import com.fas.common.utils.StringUtils;
import com.fas.dao.base.BaseDao;
import com.fas.sapp.base.SearchMasterFrame;
import com.fas.sapp.base.SearchTransactionFrame;
import com.fas.vo.base.EntityVo;
import com.fas.vo.base.SortObjVo;
import com.fas.vo.search.MSchDspVo;
import com.fas.vo.search.SearchConditionVo;
import com.fas.vo.search.SearchVo;
import com.fas.vo.search.TrSchdspVo;

/**
 * @author PC13
 * 
 */
public class SearchDaoImpl extends BaseDao implements SearchDao {

	/** */
	static Logger logger = Logger.getLogger(SearchDaoImpl.class);

	/**
	 * <DL>
	 * <DT>コンストラクター記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 */
	public SearchDaoImpl() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.dao.search.SearchDao#getMSchDspVo(java.lang.String)
	 */
	public MSchDspVo getMSchDspVo(String key) throws DaoException {
		logger.info("SearchDaoImpl.getMSchDspVo 開始。");
		DBConnection con = null;
		MSchDspVo dataVo = null;
		String strSQL = getSQL(XMLContants.FILE_M_SCHDSP, XMLContants.M_SCHDSP001);
		try {
			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			preStmt.setString(1, key);
			ResultSet rs = preStmt.executeQuery();
			if (rs.next()) {
				dataVo = new MSchDspVo();
				dataVo.setDspCode(StringUtils.emptyIfNull(rs.getString("DSP_CODE")).trim());
				dataVo.setDspName(StringUtils.emptyIfNull(rs.getString("DSP_NAME")).trim());
				dataVo.setSchCtl(StringUtils.emptyIfNull(rs.getString("SCH_CTL")).trim());
				dataVo.setTblName(StringUtils.emptyIfNull(rs.getString("TBL_NAME")).trim());
				dataVo.setSqlId(StringUtils.emptyIfNull(rs.getString("SQL_ID")).trim());
				dataVo.setTtlName1(StringUtils.emptyIfNull(rs.getString("TTL_NAME1")).trim());
				dataVo.setTtlName2(StringUtils.emptyIfNull(rs.getString("TTL_NAME2")).trim());
				dataVo.setCmbItem1(StringUtils.emptyIfNull(rs.getString("CMB_ITEM1")).trim());
				dataVo.setTxtItem1(StringUtils.emptyIfNull(rs.getString("TXT_ITEM1")).trim());
				dataVo.setSpdCol1(StringUtils.emptyIfNull(rs.getString("SPD_COL1")).trim());
				dataVo.setSpdCol2(StringUtils.emptyIfNull(rs.getString("SPD_COL2")).trim());
				dataVo.setSpdCol3(StringUtils.emptyIfNull(rs.getString("SPD_COL3")).trim());
				dataVo.setSpdCol4(StringUtils.emptyIfNull(rs.getString("SPD_COL4")).trim());
				dataVo.setSpdCol5(StringUtils.emptyIfNull(rs.getString("SPD_COL5")).trim());
				dataVo.setSpdColnm1(StringUtils.emptyIfNull(rs.getString("SPD_COLNM1")).trim());
				dataVo.setSpdColnm2(StringUtils.emptyIfNull(rs.getString("SPD_COLNM2")).trim());
				dataVo.setSpdColnm3(StringUtils.emptyIfNull(rs.getString("SPD_COLNM3")).trim());
				dataVo.setSpdColnm4(StringUtils.emptyIfNull(rs.getString("SPD_COLNM4")).trim());
				dataVo.setSpdColnm5(StringUtils.emptyIfNull(rs.getString("SPD_COLNM5")).trim());
				dataVo.setSpdColwd1(rs.getInt("SPD_COLWD1"));
				dataVo.setSpdColwd2(rs.getInt("SPD_COLWD2"));
				dataVo.setSpdColwd3(rs.getInt("SPD_COLWD3"));
				dataVo.setSpdColwd4(rs.getInt("SPD_COLWD4"));
				dataVo.setSpdColwd5(rs.getInt("SPD_COLWD5"));
				dataVo.setSpdColuse(rs.getInt("SPD_COLUSE"));
				dataVo.setSpdColwdc(rs.getInt("SPD_COLWDC"));
				dataVo.setSpdColhid(rs.getInt("SPD_COLHID"));
				dataVo.setSchItem1(StringUtils.emptyIfNull(rs.getString("SCH_ITEM1")).trim());
				dataVo.setSchItem2(StringUtils.emptyIfNull(rs.getString("SCH_ITEM2")).trim());
				dataVo.setSchItem3(StringUtils.emptyIfNull(rs.getString("SCH_ITEM3")).trim());
				dataVo.setSchItem4(StringUtils.emptyIfNull(rs.getString("SCH_ITEM4")).trim());
				dataVo.setSchItem5(StringUtils.emptyIfNull(rs.getString("SCH_ITEM5")).trim());
				dataVo.setSchItem6(StringUtils.emptyIfNull(rs.getString("SCH_ITEM6")).trim());
				dataVo.setSchName1(StringUtils.emptyIfNull(rs.getString("SCH_NAME1")).trim());
				dataVo.setSchName2(StringUtils.emptyIfNull(rs.getString("SCH_NAME2")).trim());
				dataVo.setSchName3(StringUtils.emptyIfNull(rs.getString("SCH_NAME3")).trim());
				dataVo.setSchName4(StringUtils.emptyIfNull(rs.getString("SCH_NAME4")).trim());
				dataVo.setSchName5(StringUtils.emptyIfNull(rs.getString("SCH_NAME5")).trim());
				dataVo.setSchName6(StringUtils.emptyIfNull(rs.getString("SCH_NAME6")).trim());
				dataVo.setChkUse(StringUtils.emptyIfNull(rs.getString("CHK_USE")).trim());
				dataVo.setKanaItem(StringUtils.emptyIfNull(rs.getString("KANA_ITEM")).trim());
				dataVo.setHelpHtml(StringUtils.emptyIfNull(rs.getString("HELP_HTML")).trim());
			}
		} catch (SQLException e) {
			logger.error(e.getStackTrace());
			return null;
		} finally {
			if (con != null) {
				try {
					DBConnectionManager.releaseConnection(con);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		logger.info("SearchDaoImpl.getMSchDspVo 終了。");
		return dataVo;
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pipe.dao.search.SearchDao#getLstSearchVo(com.pipe.vo.search
	 * .MSchDspVo, com.pipe.vo.search.SearchConditionVo,
	 * com.pipe.vo.base.SortObjVo)
	 */
	public List<SearchVo> getLstSearchVo(MSchDspVo dspVo, SearchConditionVo condition, SortObjVo sortObj) throws DaoException {
		//View in Table SCHDSP in DB
		if ((SearchMasterFrame.I_M_EMP_SEARCH  + "").equals(dspVo.getDspCode())) {//search MEmp
			return getLstMEmpVo(dspVo, condition, sortObj);
		} else if ((SearchMasterFrame.I_M_POST_SEARCH + "").equals(dspVo.getDspCode())) {//search MPost
			return getLstMPostVo(dspVo, condition, sortObj);		
		} else if ((SearchMasterFrame.I_M_USERGROUP_SEARCH + "").equals(dspVo.getDspCode())) {//search MUserGroup
			return getLstMUsergrpVo(dspVo, condition, sortObj);		
		} else {
			return new ArrayList<SearchVo>();
		}
	}
	
	//Form Search MPost
	private List<SearchVo> getLstMPostVo(MSchDspVo dspVo, SearchConditionVo condition, SortObjVo sortObj) throws DaoException {
		if(!StringUtils.isValid(condition.getTxtValue1()))
		{
			return null;
		}
		logger.info("SearchDaoImpl.getLstPostVo 開始。");

		DBConnection con = null;
		SearchVo dataVo = null;
		List<SearchVo> lstData = new ArrayList<SearchVo>();
		// This XML line get in DB table M_SCHDSP value SQL_ID
		String strSQL = getSQL(XMLContants.FILE_M_SEARCH, dspVo.getSqlId()
				.trim());
		String strWhere = "";
		String strOrderBy = "";
		List<EntityVo> lstEntity = new ArrayList<EntityVo>();

		try {

			con = DBConnectionManager.getConnection();
			
			 if (sortObj.getM_sortCol() == 0) {
			 strOrderBy = " ORDER BY M.F3 ";
			 } else {
			 strOrderBy = " ORDER BY (M.F7 + M.F8 + M.F9) ";
			 }
			
			 strOrderBy += sortObj.isM_sortAsc() ? " ASC " : " DESC ";

			strWhere = getMPostWhereSql(condition, lstEntity);

			strSQL += strWhere + strOrderBy;
			logger.info(strSQL);

			PreparedStatement preStmt = con.prepareStatement(strSQL);
			setParameter(lstEntity, preStmt);
			ResultSet rs = preStmt.executeQuery();

			while (rs.next()) {
				dataVo = new SearchVo();
				dataVo.setProPer1(StringUtils.subString(StringUtils.emptyIfNull(rs.getString("F3")), 3) + "-" 
						+ StringUtils.subString(StringUtils.emptyIfNull(rs.getString("F3")), 3, 4));
				dataVo.setProPer2(StringUtils.emptyIfNull(rs.getString("F7"))
						+ StringUtils.emptyIfNull(rs.getString("F8"))
						+ StringUtils.emptyIfNull(rs.getString("F9")).trim());
//				dataVo.setProPer3(StringUtils.emptyIfNull(rs.getString("PWD"))
//						.trim());
//				dataVo.setProPer4(StringUtils.emptyIfNull(
//						rs.getString("LOGVIEW_FLG")).trim());
//				dataVo.setProPer5(StringUtils.emptyIfNull(
//						rs.getString("EXCELOUT")).trim());
				// dataVo.setUseFlg(StringUtils.emptyIfNull(
				// rs.getString("USE_FLG")).trim());
				dataVo.setUseFlg("0");// If ==1 ForeColor is Red
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

		logger.info("SearchDaoImpl.getLstPostVo 開始。");

		return lstData;
	}

	private String getMPostWhereSql(SearchConditionVo conditionVo,
			List<EntityVo> lstEntity) {

		String strWhere = "";
		EntityVo eVo = null;

		strWhere = " WHERE 1 = 1 ";

		// if (!conditionVo.isUsed()) {
		// strWhere += " AND M.USE_FLG = '0' ";
		// }

		strWhere += getSearchIndex(conditionVo.getIndexValue());

		if (StringUtils.isValid(conditionVo.getTxtValue1())) {
			if ("1".equalsIgnoreCase(conditionVo.getItemValue())) {
				eVo = new EntityVo();
				eVo.setStringValue(conditionVo.getTxtValue1());
				eVo.setValueType(DataTypeConstants.STRING_TYPE);
				lstEntity.add(eVo);
				strWhere += " AND M.F3 >= ? ";
			} else if ("2".equalsIgnoreCase(conditionVo.getItemValue())) {
				eVo = new EntityVo();
				eVo.setStringValue("%" + conditionVo.getTxtValue1() + "%");
				eVo.setValueType(DataTypeConstants.STRING_TYPE);
				lstEntity.add(eVo);
				strWhere += " AND (M.F7 + M.F8 + M.F9) LIKE ? ";
			} else if ("3".equalsIgnoreCase(conditionVo.getItemValue())) {
				eVo = new EntityVo();
				eVo.setStringValue("%" + conditionVo.getTxtValue1() + "%");
				eVo.setValueType(DataTypeConstants.STRING_TYPE);
				lstEntity.add(eVo);
				strWhere += " AND (M.F4 + M.F5 + M.F6) LIKE ? ";
			} else {
				eVo = new EntityVo();
				eVo.setStringValue(conditionVo.getTxtValue1());
				eVo.setValueType(DataTypeConstants.STRING_TYPE);
				lstEntity.add(eVo);
				strWhere += " AND M.F2 >= ? ";
			}
		}

		return strWhere;
	}

	//Form Search MEmp
	private List<SearchVo> getLstMEmpVo(MSchDspVo dspVo, SearchConditionVo condition, SortObjVo sortObj) throws DaoException {

		logger.info("SearchDaoImpl.getLstEmpVo 開始。");

		DBConnection con = null;
		SearchVo dataVo = null;
		List<SearchVo> lstData = new ArrayList<SearchVo>();
		String strSQL = getSQL(XMLContants.FILE_M_SEARCH, dspVo.getSqlId()
				.trim());
		String strWhere = "";
		String strOrderBy = "";
		List<EntityVo> lstEntity = new ArrayList<EntityVo>();

		try {

			con = DBConnectionManager.getConnection();

			if (sortObj.isInit()) {
				if(sortObj.getM_sortCol() == 1){
					strOrderBy = " ORDER BY M.EMP_CODE ";
				} else if(sortObj.getM_sortCol() == 2){
					strOrderBy = " ORDER BY M.EMP_NAME ";
				}else if(sortObj.getM_sortCol() == 3){
					strOrderBy = " ORDER BY M.EMP_KANA ";
				} else if(sortObj.getM_sortCol() == 6){
					strOrderBy = " ORDER BY M.OLD_CODE ";
				} else if(sortObj.getM_sortCol() == 9){
					strOrderBy = " ORDER BY M.DSPORDER_NO ";
				} else{
					strOrderBy = " ORDER BY M.DSPORDER_NO ";
				}
			} else {
				if (sortObj.getM_sortCol() == 1) {
					strOrderBy = " ORDER BY M.EMP_CODE ";
				} else if ((sortObj.getM_sortCol() == 2)) {
					strOrderBy = " ORDER BY M.EMP_NAME ";
				} else if ((sortObj.getM_sortCol() == 3)) {
					strOrderBy = " ORDER BY M.EMP_KANA ";
				} else {
					strOrderBy = " ORDER BY M.EMP_CODE ";
				}

				strOrderBy += sortObj.isM_sortAsc() ? " ASC " : " DESC ";
			}

			strWhere = getMEmpWhereSql(condition, lstEntity);

			strSQL += strWhere + strOrderBy;
			logger.info(strSQL);

			PreparedStatement preStmt = con.prepareStatement(strSQL);
			setParameter(lstEntity, preStmt);
			ResultSet rs = preStmt.executeQuery();

			while (rs.next()) {
				dataVo = new SearchVo();
				dataVo.setProPer1(StringUtils.emptyIfNull(
						rs.getString("EMP_CODE")).trim());
				dataVo.setProPer2(StringUtils.emptyIfNull(
						rs.getString("EMP_NAME")).trim());
				dataVo.setProPer3(StringUtils.emptyIfNull(
						rs.getString("USE_FLG")).trim());
				dataVo.setUseFlg(StringUtils.emptyIfNull(
						rs.getString("USE_FLG")).trim());
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

		logger.info("SearchDaoImpl.getLstEmpVo 開始。");

		return lstData;
	}

	private String getMEmpWhereSql(SearchConditionVo conditionVo,
			List<EntityVo> lstEntity) {

		String strWhere = "";
		EntityVo eVo = null;

		strWhere = " WHERE 1 = 1 ";

		if (!conditionVo.isUsed()) {
			strWhere += " AND M.USE_FLG = '0' ";
		}

		strWhere += getSearchIndex(conditionVo.getIndexValue());

		if (StringUtils.isValid(conditionVo.getTxtValue1())) {
			eVo = new EntityVo();
			eVo.setStringValue("%" + conditionVo.getTxtValue1() + "%");
			eVo.setValueType(DataTypeConstants.STRING_TYPE);
			lstEntity.add(eVo);
			if ("1".equalsIgnoreCase(conditionVo.getItemValue())) {
				eVo.setStringValue(conditionVo.getTxtValue1());
				strWhere += " AND M.EMP_CODE >= ? ";
			} else if ("2".equalsIgnoreCase(conditionVo.getItemValue())) {
				strWhere += " AND M.EMP_NAME LIKE ? ";
			} else if ("3".equalsIgnoreCase(conditionVo.getItemValue())) {
				strWhere += " AND M.EMP_KANA LIKE ? ";
				// } else if
				// ("4".equalsIgnoreCase(conditionVo.getKomokuValue())) {
			} else if ("6".equalsIgnoreCase(conditionVo.getItemValue())) {
				strWhere += " AND M.OLD_CODE LIKE ? ";
				// } else if
				// ("6".equalsIgnoreCase(conditionVo.getKomokuValue())) {
			} else {
				eVo.setStringValue(conditionVo.getTxtValue1());
				strWhere += " AND M.EMP_CODE >= ? ";
			}
		}

		return strWhere;
	}
	
	//Form Search MUsergrp
	private List<SearchVo> getLstMUsergrpVo(MSchDspVo dspVo, SearchConditionVo condition, SortObjVo sortObj) throws DaoException {

		logger.info("SearchDaoImpl.getLstUsergrpVo 開始。");

		DBConnection con = null;
		SearchVo dataVo = null;
		List<SearchVo> lstData = new ArrayList<SearchVo>();
		// This XML line get in DB table M_SCHDSP value SQL_ID
		String strSQL = getSQL(XMLContants.FILE_M_SEARCH, dspVo.getSqlId()
				.trim());
		String strWhere = "";
		String strOrderBy = "";
		List<EntityVo> lstEntity = new ArrayList<EntityVo>();

		try {

			con = DBConnectionManager.getConnection();

			if (sortObj.getM_sortCol() == 0) {
				strOrderBy = " ORDER BY M.USERGID ";
			} else {
				strOrderBy = " ORDER BY M.USERTXT ";
			}

			strOrderBy += sortObj.isM_sortAsc() ? " ASC " : " DESC ";

			strWhere = getMUsergrpWhereSql(condition, lstEntity);

			strSQL += strWhere + strOrderBy;
			logger.info(strSQL);

			PreparedStatement preStmt = con.prepareStatement(strSQL);
			setParameter(lstEntity, preStmt);
			ResultSet rs = preStmt.executeQuery();

			while (rs.next()) {
				dataVo = new SearchVo();
				dataVo.setProPer1(StringUtils.emptyIfNull(
						rs.getString("USERGID")).trim());
				dataVo.setProPer2(StringUtils.emptyIfNull(
						rs.getString("USERTXT")).trim());
				dataVo.setProPer3(StringUtils.emptyIfNull(rs.getString("PWD"))
						.trim());
				dataVo.setProPer4(StringUtils.emptyIfNull(
						rs.getString("LOGVIEW_FLG")).trim());
				dataVo.setProPer5(StringUtils.emptyIfNull(
						rs.getString("EXCELOUT")).trim());
				// dataVo.setUseFlg(StringUtils.emptyIfNull(
				// rs.getString("USE_FLG")).trim());
				dataVo.setUseFlg("0");// If ==1 ForeColor is Red
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

		logger.info("SearchDaoImpl.getLstUsergrpVo 開始。");

		return lstData;
	}

	private String getMUsergrpWhereSql(SearchConditionVo conditionVo,
			List<EntityVo> lstEntity) {

		String strWhere = "";
		EntityVo eVo = null;

		strWhere = " WHERE 1 = 1 ";

		// if (!conditionVo.isUsed()) {
		// strWhere += " AND M.USE_FLG = '0' ";
		// }

		strWhere += getSearchIndex(conditionVo.getIndexValue());

		if (StringUtils.isValid(conditionVo.getTxtValue1())) {
			eVo = new EntityVo();
			eVo.setStringValue(conditionVo.getTxtValue1());
			eVo.setValueType(DataTypeConstants.STRING_TYPE);
			lstEntity.add(eVo);
			if ("1".equalsIgnoreCase(conditionVo.getItemValue())) {
				strWhere += " AND M.USERGID >= ? ";
			} else if ("2".equalsIgnoreCase(conditionVo.getItemValue())) {
				eVo.setStringValue("%" + conditionVo.getTxtValue1() + "%");
				strWhere += " AND M.USERTXT LIKE ? ";
			} else {
				strWhere += " AND M.USERGID >= ? ";
			}
		}

		return strWhere;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param searchIndex
	 * @return
	 */
	private String getSearchIndex(String searchIndex) {

		String strReturn = "";

		if (StringUtils.isValid(searchIndex)) {
			if ("ア".equalsIgnoreCase(searchIndex)) {
				strReturn += " AND((M.SEARCHIDX >= 'ｱ' AND  M.SEARCHIDX <= 'ｵ') OR (M.SEARCHIDX >= 'ア' AND  M.SEARCHIDX <= 'オ')) ";
			} else if ("ハ".equalsIgnoreCase(searchIndex)) {
				strReturn += " AND((M.SEARCHIDX >= 'ﾊ' AND  M.SEARCHIDX <= 'ﾎ') OR (M.SEARCHIDX >= 'ハ' AND  M.SEARCHIDX <= 'ホ')) ";
			} else if ("カ".equalsIgnoreCase(searchIndex)) {				
				strReturn += " AND((M.SEARCHIDX >= 'ｶ' AND  M.SEARCHIDX <= 'ｺ') OR (M.SEARCHIDX >= 'カ' AND  M.SEARCHIDX <= 'コ')) ";
			} else if ("マ".equalsIgnoreCase(searchIndex)) {
				strReturn += " AND((M.SEARCHIDX >= 'ﾏ' AND  M.SEARCHIDX <= 'ﾓ') OR (M.SEARCHIDX >= 'マ' AND  M.SEARCHIDX <= 'モ')) ";
			} else if ("サ".equalsIgnoreCase(searchIndex)) {
				strReturn += " AND((M.SEARCHIDX >= 'ｻ' AND  M.SEARCHIDX <= 'ｿ') OR (M.SEARCHIDX >= 'サ' AND  M.SEARCHIDX <= 'ソ')) ";
			} else if ("ヤ".equalsIgnoreCase(searchIndex)) {
				strReturn += " AND((M.SEARCHIDX >= 'ﾔ' AND  M.SEARCHIDX <= 'ﾖ') OR (M.SEARCHIDX >= 'ヤ' AND  M.SEARCHIDX <= 'ヨ')) ";
			} else if ("タ".equalsIgnoreCase(searchIndex)) {
				strReturn += " AND((M.SEARCHIDX >= 'ﾀ' AND  M.SEARCHIDX <= 'ﾄ') OR (M.SEARCHIDX >= 'タ' AND  M.SEARCHIDX <= 'ト')) ";
			} else if ("ラ".equalsIgnoreCase(searchIndex)) {
				strReturn += " AND((M.SEARCHIDX >= 'ﾗ' AND  M.SEARCHIDX <= 'ﾛ') OR (M.SEARCHIDX >= 'ラ' AND  M.SEARCHIDX <= 'ロ')) ";
			} else if ("ナ".equalsIgnoreCase(searchIndex)) {
				strReturn += " AND((M.SEARCHIDX >= 'ﾅ' AND  M.SEARCHIDX <= 'ﾉ') OR (M.SEARCHIDX >= 'ナ' AND  M.SEARCHIDX <= 'ノ')) "; 
			} else if ("ワ".equalsIgnoreCase(searchIndex)) {
				strReturn += " AND((M.SEARCHIDX >= 'ﾜ' AND  M.SEARCHIDX <= 'ﾝ') OR (M.SEARCHIDX >= 'ワ' AND  M.SEARCHIDX <= 'ン')) ";
			} else if ("e".equalsIgnoreCase(searchIndex)) {
				strReturn += " AND ((M.SEARCHIDX < 'ｱ' OR  M.SEARCHIDX > 'ﾝ') OR (M.SEARCHIDX < 'ア' OR  M.SEARCHIDX > 'ン')) ";
			}
		}

		return strReturn;
	}

	/*
	 * (non-Javadoc)
	 *
	 */
	public List<SearchVo> getLstSearchVo(TrSchdspVo dspVo, SearchConditionVo condition, SortObjVo sortObj) throws DaoException {
		//View in Table SCHDSP in DB
		if ((SearchTransactionFrame.I_PE01_SEARCH  + "").equals(dspVo.getDspCode())) {//search PE01
			return getLstPe01Vo(dspVo, condition, sortObj);
		} else if ((SearchTransactionFrame.I_PE03_SEARCH  + "").equals(dspVo.getDspCode())) {//search PE03
			return getLstPe03Vo(dspVo, condition, sortObj);
		} else if ((SearchTransactionFrame.I_PG01_SEARCH  + "").equals(dspVo.getDspCode())) {//search PG01
			return getLstPg01Vo(dspVo, condition, sortObj);
		} else if ((SearchTransactionFrame.I_PG02_SEARCH  + "").equals(dspVo.getDspCode())) {//search PG02
			return getLstPg02Vo(dspVo, condition, sortObj);
		} else if ((SearchTransactionFrame.I_PC01_SEARCH  + "").equals(dspVo.getDspCode())) {//search PC01
			return getLstPc01Vo(dspVo, condition, sortObj);
		} else {
			return new ArrayList<SearchVo>();
		}
	}

	//Form Search Pe01
	private List<SearchVo> getLstPe01Vo(TrSchdspVo dspVo, SearchConditionVo condition, SortObjVo sortObj) throws DaoException {

		logger.info("SearchDaoImpl.getLstPe01Vo 開始。");
		DBConnection con = null;
		SearchVo dataVo = null;
		List<SearchVo> lstData = new ArrayList<SearchVo>();
		String strSQL = getSQL(XMLContants.FILE_M_SEARCH, dspVo.getSqlId().trim());
		String strWhere = "";
		String strOrderBy = "";
		List<EntityVo> lstEntity = new ArrayList<EntityVo>();
		try {
			con = DBConnectionManager.getConnection();
			if (sortObj.isInit()) {
				if(sortObj.getM_sortCol() == 1){
					strOrderBy = " ORDER BY M.E0101 ";
				} else if(sortObj.getM_sortCol() == 2){
					strOrderBy = " ORDER BY M.E0104 ";
				}else if(sortObj.getM_sortCol() == 3){
					strOrderBy = "  ";
				} else if(sortObj.getM_sortCol() == 6){
					strOrderBy = "  ";
				} else if(sortObj.getM_sortCol() == 9){
					strOrderBy = "  ";
				} else{
					strOrderBy = " ORDER BY M.E0101 ";
				}
			} else {
				if (sortObj.getM_sortCol() == 0) {
					strOrderBy = " ORDER BY M.E0101 ";
				} else if ((sortObj.getM_sortCol() == 1)) {
					strOrderBy = " ORDER BY M.E0104 ";
				} else if ((sortObj.getM_sortCol() == 2)) {
					strOrderBy = " ORDER BY M.E0113 ";
				} else if ((sortObj.getM_sortCol() == 3)) {
					strOrderBy = " ORDER BY M.E0114 ";
				} else if ((sortObj.getM_sortCol() == 4)) {
					strOrderBy = " ORDER BY M.E0108 ";
				} else if ((sortObj.getM_sortCol() == 5)) {
					strOrderBy = " ORDER BY (M.E0108 * 0.05 ) ";
				} else {
					strOrderBy = " ORDER BY M.E0101 ";
				}

				strOrderBy += sortObj.isM_sortAsc() ? " ASC " : " DESC ";
			}

			strWhere = getPe01WhereSql(condition, lstEntity);

			strSQL += strWhere + strOrderBy;
			logger.info(strSQL);

			PreparedStatement preStmt = con.prepareStatement(strSQL);
			setParameter(lstEntity, preStmt);
			ResultSet rs = preStmt.executeQuery();

			//Format du lieu
			NumberFormat formaterDouble = new DecimalFormat("###,###,##0.00");
			NumberFormat formaterInt = new DecimalFormat("###,###,##0");
//			s = formatter.format(-.567); 
			while (rs.next()) {
				dataVo = new SearchVo();
				dataVo.setProPer1(StringUtils.trimAllVN( StringUtils.emptyIfNull(rs.getString("E0101"))));
				dataVo.setProPer2(StringUtils.trimAllVN( StringUtils.emptyIfNull(rs.getString("E0104"))));
				
				dataVo.setProPer3( formaterInt.format(rs.getDouble("E0113")) );
				dataVo.setProPer4( formaterInt.format(rs.getDouble("E0114")) );
				dataVo.setProPer5( formaterDouble.format(rs.getDouble("E0108")) );
				dataVo.setProPer6( formaterDouble.format(rs.getDouble("TaxIncluded")) );
				dataVo.setUseFlg("");
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
		logger.info("SearchDaoImpl.getLstPe01Vo 開始。");
		return lstData;
	}

	private String getPe01WhereSql(SearchConditionVo conditionVo, List<EntityVo> lstEntity) {

		String strWhere = "";
		EntityVo eVo = null;

		strWhere = " WHERE 1 = 1 ";

//		if (!conditionVo.isUsed()) {
//			strWhere += " AND M.USE_FLG = '0' ";
//		}

		strWhere += getSearchIndex(conditionVo.getIndexValue());

		if (StringUtils.isValid(conditionVo.getTxtValue1())) {// ComboBox
			eVo = new EntityVo();
			eVo.setValueType(DataTypeConstants.STRING_TYPE);
			eVo.setStringValue(conditionVo.getTxtValue1());
			strWhere += " AND M.E0103 = ? ";
			lstEntity.add(eVo);
		}
		
		if (StringUtils.isValid(conditionVo.getTxtValue2())) {// TextBox
			eVo = new EntityVo();			
			eVo.setValueType(DataTypeConstants.STRING_TYPE);			
			if ("1".equalsIgnoreCase(conditionVo.getItemValue())) {
				eVo.setStringValue(conditionVo.getTxtValue2());
				strWhere += " AND M.E0101 >= ? ";
			} else if ("2".equalsIgnoreCase(conditionVo.getItemValue())) {
				eVo.setStringValue("%" + conditionVo.getTxtValue2() + "%");
				strWhere += " AND M.E0104 LIKE ? ";
			} else if ("3".equalsIgnoreCase(conditionVo.getItemValue())) {
				eVo.setStringValue("%" + conditionVo.getTxtValue2() + "%");
				strWhere += " AND M.E0105 LIKE ? ";			
			} else {
				eVo.setStringValue(conditionVo.getTxtValue2());
				strWhere += " AND M.E0101 >= ? ";
			}
			lstEntity.add(eVo);
		}
		return strWhere;
	}
	
	//Form Search Pe03
	private List<SearchVo> getLstPe03Vo(TrSchdspVo dspVo, SearchConditionVo condition, SortObjVo sortObj) throws DaoException {

		logger.info("SearchDaoImpl.getLstPe03Vo 開始。");
		DBConnection con = null;
		SearchVo dataVo = null;
		List<SearchVo> lstData = new ArrayList<SearchVo>();
		String strSQL = getSQL(XMLContants.FILE_M_SEARCH, dspVo.getSqlId().trim());
		String strWhere = "";
		String strOrderBy = "";
		List<EntityVo> lstEntity = new ArrayList<EntityVo>();
		try {
			con = DBConnectionManager.getConnection();
			if (sortObj.isInit()) {// Sort by Item select
				if(sortObj.getM_sortCol() == 1){
					strOrderBy = " ORDER BY M.E0301 ";
				} else if(sortObj.getM_sortCol() == 2){
					strOrderBy = " ORDER BY M.E0302 ";
				}else if(sortObj.getM_sortCol() == 3){
					strOrderBy = " ORDER BY M.E0304 ";
				} else if(sortObj.getM_sortCol() == 6){
					strOrderBy = "  ";
				} else if(sortObj.getM_sortCol() == 9){
					strOrderBy = "  ";
				} else{
					strOrderBy = " ORDER BY M.E0301 ";
				}
			} else {// Sort by click
				if (sortObj.getM_sortCol() == 0) {
					strOrderBy = " ORDER BY M.E0301 ";
				} else if ((sortObj.getM_sortCol() == 1)) {
					strOrderBy = " ORDER BY G.G0103 ";
				} else if ((sortObj.getM_sortCol() == 2)) {
					strOrderBy = " ORDER BY M.E0302 ";
				} else {
					strOrderBy = " ORDER BY M.E0301 ";
				}

				strOrderBy += sortObj.isM_sortAsc() ? " ASC " : " DESC ";
			}

			strWhere = getPe03WhereSql(condition, lstEntity);

			strSQL += strWhere + strOrderBy;
			logger.info(strSQL);

			PreparedStatement preStmt = con.prepareStatement(strSQL);
			setParameter(lstEntity, preStmt);
			ResultSet rs = preStmt.executeQuery();

			while (rs.next()) {
				dataVo = new SearchVo();
				dataVo.setProPer1(StringUtils.trimAllVN( StringUtils.emptyIfNull(rs.getString("E0301"))));
				dataVo.setProPer2(StringUtils.trimAllVN( StringUtils.emptyIfNull(rs.getString("G0103"))));
				dataVo.setProPer3(StringUtils.trimAllVN( StringUtils.emptyIfNull(rs.getString("E0302"))));
				dataVo.setUseFlg("");
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
		logger.info("SearchDaoImpl.getLstPe03Vo 開始。");
		return lstData;
	}

	private String getPe03WhereSql(SearchConditionVo conditionVo, List<EntityVo> lstEntity) {

		String strWhere = "";
		EntityVo eVo = null;

		strWhere = " WHERE 1 = 1 ";

//		if (!conditionVo.isUsed()) {
//			strWhere += " AND M.USE_FLG = '0' ";
//		}

		strWhere += getSearchIndex(conditionVo.getIndexValue());

		if (StringUtils.isValid(conditionVo.getTxtValue1())) {// ComboBox E0357
			eVo = new EntityVo();
			eVo.setValueType(DataTypeConstants.STRING_TYPE);
			eVo.setStringValue(conditionVo.getTxtValue1());
			strWhere += " AND M.E0357 = ? ";
			lstEntity.add(eVo);
		}
		
		if (StringUtils.isValid(conditionVo.getTxtValue2())) {// TextBox
			eVo = new EntityVo();			
			eVo.setValueType(DataTypeConstants.STRING_TYPE);			
			if ("1".equalsIgnoreCase(conditionVo.getItemValue())) {// Code E0301
				eVo.setStringValue(conditionVo.getTxtValue2());
				strWhere += " AND M.E0301 >= ? ";
			} else if ("2".equalsIgnoreCase(conditionVo.getItemValue())) {// Name E0302
				eVo.setStringValue("%" + conditionVo.getTxtValue2() + "%");
				strWhere += " AND M.E0302 LIKE ? ";
			} else if ("3".equalsIgnoreCase(conditionVo.getItemValue())) {// Kana E0304
				eVo.setStringValue("%" + conditionVo.getTxtValue2() + "%");
				strWhere += " AND M.E0304 LIKE ? ";			
			}
			lstEntity.add(eVo);
		}
		return strWhere;
	}
	
	//Form Search Pg01
	private List<SearchVo> getLstPg01Vo(TrSchdspVo dspVo, SearchConditionVo condition, SortObjVo sortObj) throws DaoException {

		logger.info("SearchDaoImpl.getLstPg01Vo 開始。");
		DBConnection con = null;
		SearchVo dataVo = null;
		List<SearchVo> lstData = new ArrayList<SearchVo>();
		String strSQL = getSQL(XMLContants.FILE_M_SEARCH, dspVo.getSqlId().trim());
		String strWhere = "";
		String strOrderBy = "";
		List<EntityVo> lstEntity = new ArrayList<EntityVo>();
		try {
			con = DBConnectionManager.getConnection();
			if (sortObj.isInit()) {// Sort by Item select
				if(sortObj.getM_sortCol() == 1){
					strOrderBy = " ORDER BY M.G0101 ";
				} else if(sortObj.getM_sortCol() == 2){
					strOrderBy = " ORDER BY M.G0102 ";
				}else if(sortObj.getM_sortCol() == 3){
					strOrderBy = " ORDER BY M.G0103 ";
				} else if(sortObj.getM_sortCol() == 9){
					strOrderBy = "  ";
				} else{
					strOrderBy = " ORDER BY M.G0101 ";
				}
			} else {// Sort by click
				if (sortObj.getM_sortCol() == 0) {
					strOrderBy = " ORDER BY M.G0101 ";
				} else if ((sortObj.getM_sortCol() == 1)) {
					strOrderBy = " ORDER BY G.G0102 ";
				} else if ((sortObj.getM_sortCol() == 2)) {
					strOrderBy = " ORDER BY G.G0103 ";
				} else {
					strOrderBy = " ORDER BY M.G0101 ";
				}

				strOrderBy += sortObj.isM_sortAsc() ? " ASC " : " DESC ";
			}

			strWhere = getPg01WhereSql(condition, lstEntity);

			strSQL += strWhere + strOrderBy;
			logger.info(strSQL);

			PreparedStatement preStmt = con.prepareStatement(strSQL);
			setParameter(lstEntity, preStmt);
			ResultSet rs = preStmt.executeQuery();

			while (rs.next()) {
				dataVo = new SearchVo();
				dataVo.setProPer1(StringUtils.trimAllVN( StringUtils.emptyIfNull(rs.getString("G0101"))));
				dataVo.setProPer2(StringUtils.trimAllVN( StringUtils.emptyIfNull(rs.getString("G0102"))));
				dataVo.setProPer3(StringUtils.trimAllVN( StringUtils.emptyIfNull(rs.getString("G0103"))));
				dataVo.setUseFlg("");
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
		logger.info("SearchDaoImpl.getLstPg01Vo 開始。");
		return lstData;
	}

	private String getPg01WhereSql(SearchConditionVo conditionVo, List<EntityVo> lstEntity) {

		String strWhere = "";
		EntityVo eVo = null;

		strWhere = " WHERE 1 = 1 ";

//		if (!conditionVo.isUsed()) {
//			strWhere += " AND M.USE_FLG = '0' ";
//		}

		strWhere += getSearchIndex(conditionVo.getIndexValue());

		if (StringUtils.isValid(conditionVo.getTxtValue1())) {// ComboBox G0101
			eVo = new EntityVo();
			eVo.setValueType(DataTypeConstants.STRING_TYPE);
			eVo.setStringValue(conditionVo.getTxtValue1());
			strWhere += " AND M.G0101 = ? ";
			lstEntity.add(eVo);
		}
		
		if (StringUtils.isValid(conditionVo.getTxtValue2())) {// TextBox
			eVo = new EntityVo();			
			eVo.setValueType(DataTypeConstants.STRING_TYPE);			
			if ("1".equalsIgnoreCase(conditionVo.getItemValue())) {// Code G0101
				eVo.setStringValue(conditionVo.getTxtValue2());
				strWhere += " AND M.G0101 >= ? ";
			} else if ("2".equalsIgnoreCase(conditionVo.getItemValue())) {// Name G0102
				eVo.setStringValue("%" + conditionVo.getTxtValue2() + "%");
				strWhere += " AND M.G0102 LIKE ? ";
			}
			lstEntity.add(eVo);
		}
		return strWhere;
	}
	
	//Form Search Pg02
	private List<SearchVo> getLstPg02Vo(TrSchdspVo dspVo, SearchConditionVo condition, SortObjVo sortObj) throws DaoException {

		logger.info("SearchDaoImpl.getLstPg02Vo 開始。");
		DBConnection con = null;
		SearchVo dataVo = null;
		List<SearchVo> lstData = new ArrayList<SearchVo>();
		String strSQL = getSQL(XMLContants.FILE_M_SEARCH, dspVo.getSqlId().trim());
		String strWhere = "";
		String strOrderBy = "";
		List<EntityVo> lstEntity = new ArrayList<EntityVo>();
		try {
			con = DBConnectionManager.getConnection();
			if (sortObj.isInit()) {// Sort by Item select
				if(sortObj.getM_sortCol() == 1){
					strOrderBy = " ORDER BY M.G0201 ";
				} else if(sortObj.getM_sortCol() == 2){
					strOrderBy = " ORDER BY M.G0202 ";
				}else if(sortObj.getM_sortCol() == 3){
					strOrderBy = " ORDER BY M.G0203 ";
				} else if(sortObj.getM_sortCol() == 9){
					strOrderBy = "  ";
				} else{
					strOrderBy = " ORDER BY M.G0201 ";
				}
			} else {// Sort by click
				if (sortObj.getM_sortCol() == 0) {
					strOrderBy = " ORDER BY M.G0201 ";
				} else if ((sortObj.getM_sortCol() == 1)) {
					strOrderBy = " ORDER BY G.G0202 ";
				} else if ((sortObj.getM_sortCol() == 2)) {
					strOrderBy = " ORDER BY G.G0204 ";
				} else {
					strOrderBy = " ORDER BY M.G0201 ";
				}

				strOrderBy += sortObj.isM_sortAsc() ? " ASC " : " DESC ";
			}

			strWhere = getPg02WhereSql(condition, lstEntity);

			strSQL += strWhere + strOrderBy;
			logger.info(strSQL);

			PreparedStatement preStmt = con.prepareStatement(strSQL);
			setParameter(lstEntity, preStmt);
			ResultSet rs = preStmt.executeQuery();

			while (rs.next()) {
				dataVo = new SearchVo();
				dataVo.setProPer1(StringUtils.trimAllVN( StringUtils.emptyIfNull(rs.getString("G0201"))));
				dataVo.setProPer2(StringUtils.trimAllVN( StringUtils.emptyIfNull(rs.getString("G0202"))));
				dataVo.setProPer3(StringUtils.trimAllVN( StringUtils.emptyIfNull(rs.getString("G0204"))));
				dataVo.setUseFlg("");
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
		logger.info("SearchDaoImpl.getLstPg02Vo 開始。");
		return lstData;
	}

	private String getPg02WhereSql(SearchConditionVo conditionVo, List<EntityVo> lstEntity) {

		String strWhere = "";
		EntityVo eVo = null;

		strWhere = " WHERE 1 = 1 ";

//		if (!conditionVo.isUsed()) {
//			strWhere += " AND M.USE_FLG = '0' ";
//		}

		strWhere += getSearchIndex(conditionVo.getIndexValue());

		if (StringUtils.isValid(conditionVo.getTxtValue1())) {// ComboBox G0201
			eVo = new EntityVo();
			eVo.setValueType(DataTypeConstants.STRING_TYPE);
			eVo.setStringValue(conditionVo.getTxtValue1());
			strWhere += " AND M.G0201 = ? ";
			lstEntity.add(eVo);
		}
		
		if (StringUtils.isValid(conditionVo.getTxtValue2())) {// TextBox
			eVo = new EntityVo();			
			eVo.setValueType(DataTypeConstants.STRING_TYPE);			
			if ("1".equalsIgnoreCase(conditionVo.getItemValue())) {// Code G0201
				eVo.setStringValue(conditionVo.getTxtValue2());
				strWhere += " AND M.G0201 >= ? ";
			} else if ("2".equalsIgnoreCase(conditionVo.getItemValue())) {// Name G0202
				eVo.setStringValue("%" + conditionVo.getTxtValue2() + "%");
				strWhere += " AND M.G0202 LIKE ? ";
			} else if ("3".equalsIgnoreCase(conditionVo.getItemValue())) {// Kana G0204
				eVo.setStringValue("%" + conditionVo.getTxtValue2() + "%");
				strWhere += " AND M.G0204 LIKE ? ";
			}
			lstEntity.add(eVo);
		}
		return strWhere;
	}
	
	//Form Search Pc01
	private List<SearchVo> getLstPc01Vo(TrSchdspVo dspVo, SearchConditionVo condition, SortObjVo sortObj) throws DaoException {

		logger.info("SearchDaoImpl.getLstPc01Vo 開始。");
		DBConnection con = null;
		SearchVo dataVo = null;
		List<SearchVo> lstData = new ArrayList<SearchVo>();
		String strSQL = getSQL(XMLContants.FILE_M_SEARCH, dspVo.getSqlId().trim());
		String strWhere = "";
		String strOrderBy = "";
		List<EntityVo> lstEntity = new ArrayList<EntityVo>();
		try {
			con = DBConnectionManager.getConnection();
			if (sortObj.isInit()) {// Sort by Item select
				if(sortObj.getM_sortCol() == 1){
					strOrderBy = " ORDER BY M.C0101 ";
				} else if(sortObj.getM_sortCol() == 2){
					strOrderBy = " ORDER BY M.C0104 ";
				}else if(sortObj.getM_sortCol() == 3){
					strOrderBy = " ORDER BY M.C0122, M.C0123 ";
				} else if(sortObj.getM_sortCol() == 6){
					strOrderBy = "  ";
				} else if(sortObj.getM_sortCol() == 9){
					strOrderBy = "  ";
				} else{
					strOrderBy = " ORDER BY M.C0101 ";
				}
			} else {// Sort by click
				if (sortObj.getM_sortCol() == 0) {
					strOrderBy = " ORDER BY M.C0101 ";
				} else if ((sortObj.getM_sortCol() == 1)) {
					strOrderBy = " ORDER BY G.G0104 ";
				} else if ((sortObj.getM_sortCol() == 2)) {
					strOrderBy = " ORDER BY M.C0122, M.C0123 ";
				} else {
					strOrderBy = " ORDER BY M.C0101 ";
				}

				strOrderBy += sortObj.isM_sortAsc() ? " ASC " : " DESC ";
			}

			strWhere = getPc01WhereSql(condition, lstEntity);

			strSQL += strWhere + strOrderBy;
			logger.info(strSQL);

			PreparedStatement preStmt = con.prepareStatement(strSQL);
			setParameter(lstEntity, preStmt);
			ResultSet rs = preStmt.executeQuery();

			while (rs.next()) {
				dataVo = new SearchVo();
				dataVo.setProPer1(StringUtils.trimAllVN( StringUtils.emptyIfNull(rs.getString("C0101"))));
				dataVo.setProPer2(StringUtils.trimAllVN( StringUtils.emptyIfNull(rs.getString("G0103"))));
				dataVo.setProPer3(StringUtils.trimAllVN( StringUtils.emptyIfNull(rs.getString("C0122"))) + StringUtils.trimAllVN( StringUtils.emptyIfNull(rs.getString("C0123"))));
				dataVo.setUseFlg("");
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
		logger.info("SearchDaoImpl.getLstPc01Vo 開始。");
		return lstData;
	}

	private String getPc01WhereSql(SearchConditionVo conditionVo, List<EntityVo> lstEntity) {

		String strWhere = "";
		EntityVo eVo = null;

		strWhere = " WHERE 1 = 1 ";

//		if (!conditionVo.isUsed()) {
//			strWhere += " AND M.USE_FLG = '0' ";
//		}

		strWhere += getSearchIndex(conditionVo.getIndexValue());

		if (StringUtils.isValid(conditionVo.getTxtValue1())) {// ComboBox C0157
			eVo = new EntityVo();
			eVo.setValueType(DataTypeConstants.STRING_TYPE);
			eVo.setStringValue(conditionVo.getTxtValue1());
			strWhere += " AND M.C0104 = ? ";
			lstEntity.add(eVo);
		}
		
		if (StringUtils.isValid(conditionVo.getTxtValue2())) {// TextBox
			eVo = new EntityVo();
			eVo.setValueType(DataTypeConstants.STRING_TYPE);			
			if ("1".equalsIgnoreCase(conditionVo.getItemValue())) {// Code C0101
				eVo.setStringValue(conditionVo.getTxtValue2());
				strWhere += " AND M.C0101 >= ? ";
			} else if ("2".equalsIgnoreCase(conditionVo.getItemValue())) {// Name C0122 + C0123 
				eVo.setStringValue("%" + conditionVo.getTxtValue2() + "%");
				lstEntity.add(eVo);//Add 2 gia tri Vo vao trong List lstEntity
				
				eVo = new EntityVo();				
				eVo.setStringValue("%" + conditionVo.getTxtValue2() + "%");
				strWhere += " AND M.C0122 LIKE ? OR M.C0123 LIKE ? ";
			} else if ("3".equalsIgnoreCase(conditionVo.getItemValue())) {// Kana C0124
				eVo.setStringValue("%" + conditionVo.getTxtValue2() + "%");
				strWhere += " AND M.C0124 LIKE ? ";			
			}
			lstEntity.add(eVo);
		}
		return strWhere;
	}
	
}
