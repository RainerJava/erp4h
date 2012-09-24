/************************************************************************************
*
*	会社名		： 林兼コンピューター株式会社
*
*	プロジェクト名	： fas
*
*	ファイル名		： MEmpServiceImpl.java
*
*	記述			：
*
*	作成日		：  2011/08/24  03:01:43 午後
*
*	作成者		： LENOVO-F23A3B72
*
*	備考			：
*
************************************************************************************/

package com.fas.service.system.memp;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

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
import com.fas.common.utils.StringUtils;
import com.fas.dao.system.memp.MEmpDao;
import com.fas.dao.system.memp.MEmpDaoImpl;
import com.fas.service.base.BaseService;
import com.fas.vo.memp.MEmpVo;

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
public class MEmpServiceImpl extends BaseService implements MEmpService {
	
	/** */
	MEmpDao dao = null;

	/** 
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public MEmpServiceImpl() {
		dao = new MEmpDaoImpl();
	}
		
	/* 
	 * @param iOrderBy
	 * @return
	 * @throws BizException
	 * @description
	 */
	public List<MEmpVo> getAll(int iOrderBy) throws BizException{
		try {
			return dao.getAll(iOrderBy);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}

	/* 
	 * @param empCode
	 * @return
	 * @throws BizException
	 * @description
	 */
	public MEmpVo getByKey(String empCode) throws BizException{
		try {			
			return dao.getByKey(empCode);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}

	/* 
	 * @param dataVo
	 * @return
	 * @throws BizException
	 * @description
	 */
	public MEmpVo getByKey(MEmpVo dataVo) throws BizException{
		try {
			return dao.getByKey(dataVo);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}

	/* 
	 * @param dataVo
	 * @return
	 * @throws BizException
	 * @description
	 */
	public boolean insertVo(MEmpVo dataVo) throws BizException{
		try {
			return dao.insertVo(dataVo);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}

	/* 
	 * @param dataVo
	 * @return
	 * @throws BizException
	 * @description
	 */
	public boolean updateVo(MEmpVo dataVo) throws BizException{
		try {
			return dao.updateVo(dataVo);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}

	/* 
	 * @param dataVo
	 * @return
	 * @throws SQLException
	 * @throws BizException
	 * @description
	 */
	public boolean deleteVo(MEmpVo dataVo) throws SQLException, BizException{
		try {
			return dao.deleteVo(dataVo);
		}catch (SQLException e) {
			throw new SQLException(e);
		}catch (DaoException e) {
			throw new BizException(e);
		}
	}

	/* 
	 * @return
	 * @throws BizException
	 * @description
	 */
	public int getVoCount() throws BizException{
		try {
			return dao.getVoCount();
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}

	/* 
	 * @param dataVo
	 * @return
	 * @throws BizException
	 * @description
	 */
	public boolean isDoubleKey(MEmpVo dataVo) throws BizException{
		try {
			return dao.isDoubleKey(dataVo);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}

	/* 
	 * @return
	 * @throws BizException
	 * @description
	 */
	public String getLatestCode() throws BizException{
		try {
			return dao.getLatestCode();
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}

	/* 
	 * @param strFilePath
	 * @return
	 * @throws BizException
	 * @description
	 */
	public boolean exportEXCEL(String strFilePath) throws BizException{
		try {
			List<MEmpVo> lstMEmpVo = null; 

			/** データある場合、データ取得 */
			lstMEmpVo = dao.exportEXCEL();

			if (lstMEmpVo == null || lstMEmpVo.size() == 0) {
			    return false;
			}
			WritableWorkbook workbook = Workbook.createWorkbook(new File(strFilePath));
			WritableSheet s1 = workbook.createSheet("M_EMP", 0);

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
			 "担当者コード"
			,"担当者名(カナ)"
			,"担当者名"
			,"担当者名(短縮)"
			,"ユーザー"
			,"ユーザー名"
			,"パスワード"
			,"表示順"
			,"内外フラグ"
			,"電話番号"
			,"メールアドレス"
			,"携帯メールアドレス"
			,"部門コード"
			,"部門コード1"
			,"部門コード2"
			,"部門コード3"
			,"部門コード4"
			,"担当者区分1"
			,"担当者区分2"
			,"担当者区分3"
			,"PCNAME"
			,"ログ表示フラグ"
			,"EXCEL出力区分"
			,"PATH_NAME"
			,"旧システムコード"
			,"検索INDEX"
			,"使用中フラグ"
			,"使用可否フラグ"
			,"パスワード設定日"
			,"PASSWORD1"
			,"PASSWORD2"
			,"PASSWORD3"
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
    			s1.setColumnView(i, 20); 
			}
			for (int i = 0; i < lstMEmpVo.size(); i++) {
				int column = 0;
				MEmpVo mEmpVo = (MEmpVo) lstMEmpVo.get(i);

				s1.addCell(new Label(column++, i+1, mEmpVo.getEmpCode(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, mEmpVo.getEmpKana(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, mEmpVo.getEmpName(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, mEmpVo.getEmpTname(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, mEmpVo.getUserUser(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, mEmpVo.getEmpUser(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, mEmpVo.getPwd(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertIntegerToStr(mEmpVo.getDsporderNo()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, mEmpVo.getInoutFlg(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, mEmpVo.getTelno(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, mEmpVo.getMailadr(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, mEmpVo.getMailadrm(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, mEmpVo.getSectCode(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, mEmpVo.getSect1Code(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, mEmpVo.getSect2Code(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, mEmpVo.getSect3Code(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, mEmpVo.getSect4Code(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, mEmpVo.getEmptyp1Code(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, mEmpVo.getEmptyp2Code(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, mEmpVo.getEmptyp3Code(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, mEmpVo.getPcid(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, mEmpVo.getLogviewFlg(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, mEmpVo.getExcelout(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, mEmpVo.getPathName(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, mEmpVo.getOldCode(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, mEmpVo.getSearchidx(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, mEmpVo.getUsingFlg(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, mEmpVo.getUseFlg(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertIntegerToStr(mEmpVo.getPwdDate()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, mEmpVo.getPwd1(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, mEmpVo.getPwd2(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, mEmpVo.getPwd3(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, mEmpVo.getAddUserView(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, mEmpVo.getAddPc(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, DateUtils.getDateWithSplitYobi(mEmpVo.getAddDate()), centerCellFormat));
				s1.addCell(new Label(column++, i+1, DateUtils.getTimeWithSplit(mEmpVo.getAddTime()), centerCellFormat));
				s1.addCell(new Label(column++, i+1, mEmpVo.getLastupUserView(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, mEmpVo.getLastupPc(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, DateUtils.getDateWithSplitYobi(mEmpVo.getLastupDate()), centerCellFormat));
				s1.addCell(new Label(column++, i+1, DateUtils.getTimeWithSplit(mEmpVo.getLastupTime()), centerCellFormat));
			}
			workbook.write();
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}  
		return true;
	}
	
	/* 
	 * @param strEmpUser
	 * @return
	 * @throws BizException
	 * @description
	 */
	public int getVoCountByUserEmpUser(String strEmpUser) throws BizException {
		try {
			return dao.getVoCountByUserEmpUser(strEmpUser);
		} catch (DaoException e) {
			throw new BizException(e);
		}				
	}
}
