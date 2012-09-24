/************************************************************************************
*
*	会社名		： 林兼コンピューター株式会社
*
*	プロジェクト名	： fas
*
*	ファイル名		： MCtlServiceImpl.java
*
*	記述			：
*
*	作成日		：  2011/08/31  09:35:12 午前
*
*	作成者		： LENOVO-F23A3B72
*
*	備考			：
*
************************************************************************************/

package com.fas.service.system.mctl;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableFont.FontName;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.fas.common.exception.BizException;
import com.fas.common.exception.DaoException;
import com.fas.common.utils.DateUtils;
import com.fas.dao.system.mctl.MCtlDao;
import com.fas.dao.system.mctl.MCtlDaoImpl;
import com.fas.service.base.BaseService;
import com.fas.vo.mctl.MCtlVo;

/**
 * <DL>
 *   <DT>クラス記述：</DT>
 * 	 <DD></DD>
 * <BR>
 *   <DT>変更歴史：</DT>
 * 	 <DD>著作者: LENOVO-F23A3B72 </DD><BR>
 * 	 <DD></DD>
 * </DL>
**/
public class MCtlServiceImpl extends BaseService implements MCtlService {
	/** */
	MCtlDao dao = null;
	
	/** 
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public MCtlServiceImpl() {
		dao = new MCtlDaoImpl();
	}
	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return all table data
	* @throws BizException
	*/
	public List<MCtlVo> getAll(int iOrderBy) throws BizException{
		try {
			return dao.getAll(iOrderBy);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>	
	* @param String userid, String cKey
	* @return MCtlVo if object exist with key else return NULL
	* @throws BizException
	*/
	public MCtlVo getByKey(String userid, String cKey) throws BizException{
		try {			
			return dao.getByKey(userid, cKey);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param dataVo
	* @return MCtlVo if object exist with key else return NULL
	* @throws BizException
	*/
	public MCtlVo getByKey(MCtlVo dataVo) throws BizException{
		try {
			return dao.getByKey(dataVo);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param dataVo
	* @return TRUE if insert susscessful else return FALSE
	* @throws BizException
	*/
	public boolean insertVo(MCtlVo dataVo) throws BizException{
		try {
			return dao.insertVo(dataVo);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param dataVo
	* @return TRUE if update susscessful else return FALSE
	* @throws BizException
	*/
	public boolean updateVo(MCtlVo dataVo) throws BizException{
		try {
			return dao.updateVo(dataVo);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param dataVo
	* @return TRUE if delete susscessful else return FALSE
	* @throws BizException
	*/
	public boolean deleteVo(MCtlVo dataVo) throws SQLException, BizException{
		try {
			return dao.deleteVo(dataVo);
		}catch (SQLException e) {
			throw new SQLException(e);
		}catch (DaoException e) {
			throw new BizException(e);
		}
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return int
	* @throws BizException
	*/
	public int getVoCount() throws BizException{
		try {
			return dao.getVoCount();
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param dataVo
	* @return boolean
	* @throws BizException
	*/
	public boolean isDoubleKey(MCtlVo dataVo) throws BizException{
		try {
			return dao.isDoubleKey(dataVo);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return String
	* @throws BizException
	*/
	public String getLatestCode() throws BizException{
		try {
			return dao.getLatestCode();
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param MCtlListVo , String
	* @return int
	* @throws BizException
	*/
	public boolean exportEXCEL(String strFilePath) throws BizException{
		try {
			List<MCtlVo> lstMCtlVo = null; 

			/** データある場合、データ取得 */
			lstMCtlVo = dao.exportEXCEL();

			if (lstMCtlVo == null || lstMCtlVo.size() == 0) {
			    return false;
			}
			WritableWorkbook workbook = Workbook.createWorkbook(new File(strFilePath));
			WritableSheet s1 = workbook.createSheet("M_CTL", 0);

			FontName fontName = WritableFont.createFont("ＭＳ ゴシック");
			WritableFont fontFormat = new WritableFont(fontName, 12);

			// header format (color, padding, border)
			WritableCellFormat headFormat = new WritableCellFormat (fontFormat);
			headFormat.setAlignment(Alignment.CENTRE);
			headFormat.setVerticalAlignment( VerticalAlignment.CENTRE);
			headFormat.setBorder(Border.ALL,  BorderLineStyle.THIN);
			headFormat.setBackground(Colour.VERY_LIGHT_YELLOW);

			// left format
			WritableCellFormat leftCellFormat = new WritableCellFormat (fontFormat); 
			leftCellFormat.setBorder(Border.ALL,  BorderLineStyle.THIN);
			leftCellFormat.setAlignment(Alignment.LEFT);

			// center format
			WritableCellFormat centerCellFormat = new WritableCellFormat (fontFormat); 
			centerCellFormat.setBorder(Border.ALL,  BorderLineStyle.THIN);
			centerCellFormat.setAlignment(Alignment.CENTRE);

			// right format
			WritableCellFormat rightCellFormat = new WritableCellFormat (fontFormat);
			rightCellFormat.setBorder(Border.ALL,  BorderLineStyle.THIN);
			rightCellFormat.setAlignment(Alignment.RIGHT);

			final String[] excelHeader  = {
			 "USERID"
			,"KEY"
			,"NAME"
			,"DATA"
			,"HELP"
			,"入力桁数"
			,"入力桁数(小数桁)"
			,"入力属性地"
			,"メンテフラグ"
			,"コントロールフラグ"
			,"変更可否フラグ"
			,"登録ユーザー名"
			,"登録ＰＣ名"
			,"登録日付"
			,"登録時刻"
			,"最終更新ユーザー名"
			,"最終更新ＰＣ名"
			,"最終更新日付"
			,"最終更新時刻"
			};

			int columnCount = excelHeader.length;
			for(int i=0; i< columnCount; i++){
    			Label lbHeader = new Label(i, 0, excelHeader[i], headFormat);	
    			s1.addCell(lbHeader);
    			if(i==2 || i==3)
    				s1.setColumnView(i, 60);
    			else if(i==4)
    				s1.setColumnView(i, 150);
    			else
    				s1.setColumnView(i, 20);
			}
			for (int i = 0; i < lstMCtlVo.size(); i++) {
				int column = 0;
				MCtlVo mCtlVo = (MCtlVo) lstMCtlVo.get(i);

				s1.addCell(new Label(column++, i+1, mCtlVo.getUserid(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, mCtlVo.getCKey(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, mCtlVo.getCName(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, mCtlVo.getCData(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, mCtlVo.getCHelp(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, mCtlVo.getCBm(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, mCtlVo.getCDecbm(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, mCtlVo.getCAttr(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, mCtlVo.getMtnFlg(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, mCtlVo.getCntFlg(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, mCtlVo.getUpdFlg(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, mCtlVo.getAddUserView(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, mCtlVo.getAddPc(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, DateUtils.getDateWithSplitYobi(mCtlVo.getAddDate()), centerCellFormat));
				s1.addCell(new Label(column++, i+1, DateUtils.getTimeWithSplit(mCtlVo.getAddTime()), centerCellFormat));
				s1.addCell(new Label(column++, i+1, mCtlVo.getLastupUserView(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, mCtlVo.getLastupPc(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, DateUtils.getDateWithSplitYobi(mCtlVo.getLastupDate()), centerCellFormat));
				s1.addCell(new Label(column++, i+1, DateUtils.getTimeWithSplit(mCtlVo.getLastupTime()), centerCellFormat));
			}
			workbook.write();
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}  
		return true;
	}
	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>	
	* @param String userid, String cKey, int iMtnFlg
	* @return MCtlVo if object exist with key else return NULL
	* @throws BizException
	*/
	public MCtlVo getByKey(String userid, String cKey, int iMtnFlg) throws BizException{
		try {			
			return dao.getByKey(userid, cKey, iMtnFlg);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>	
	* @param String userid, int iMtnFlg
	* @return List MCtlVo if exist with key else return list empty
	* @throws BizException
	*/
	public List<MCtlVo> getListVo(String strUserid, int iMtnFlg) throws BizException {
		try {
			return dao.getListVo(strUserid, iMtnFlg);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>	
	* @param String userid, String strCKey, int iMtnFlg
	* @return List MCtlVo if exist with key else return list empty
	* @throws BizException
	*/
	public List<MCtlVo> getListVo(String strUserid, String strCKey, int iMtnFlg) throws BizException {
		try {
			return dao.getListVo(strUserid, strCKey, iMtnFlg);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>	
	* @param String userid, String strCKey, int iMtnFlg, int iCntFlg
	* @return List MCtlVo if exist with key else return list empty
	* @throws BizException
	*/
	public List<MCtlVo> getListVo(String strUserid, String strCKey, int iMtnFlg, int iCntFlg) throws BizException {
		try {
			return dao.getListVo(strUserid, strCKey, iMtnFlg, iCntFlg);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return
	 * @throws BizException
	 */
	public Map<String, MCtlVo> getMapMCtlVo() throws BizException {
		try {
			return dao.getMapMCtlVo();
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return
	 * @throws BizException
	 */
	public boolean fillDataFromUseridToUserid(String strFromUserid, String strToUserid, int iMtnFlg) throws BizException {
		try 
		{
			return dao.fillDataFromUseridToUserid(strFromUserid, strToUserid, iMtnFlg);	
		} 
		catch (DaoException e) {
			throw new BizException(e);
		}
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return
	 * @throws BizException
	 */
	public boolean deleteDataByUserid(String strUserid, int iMtnFlg) throws BizException {
		try 
		{
			return dao.deleteDataByUserid(strUserid, iMtnFlg);	
		} 
		catch (DaoException e) {
			throw new BizException(e);
		}
	}
}
