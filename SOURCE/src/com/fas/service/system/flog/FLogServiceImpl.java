package com.fas.service.system.flog;

import java.io.File;
import java.util.List;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Border;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableFont.FontName;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.fas.common.exception.BizException;
import com.fas.common.exception.DaoException;
import com.fas.common.utils.StringUtils;
import com.fas.dao.system.flog.FLogDao;
import com.fas.dao.system.flog.FLogDaoImpl;
import com.fas.service.base.BaseService;
import com.fas.vo.base.SortObjVo;
import com.fas.vo.flog.FLogConditionVo;
import com.fas.vo.flog.FLogVo;

/**
 * @author PC13
 *
 */
public class FLogServiceImpl extends BaseService implements FLogService {

	/** */
	private FLogDao dao = null;

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>	 
	 */
	public FLogServiceImpl() {
		dao = new FLogDaoImpl();
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>	 
	 */
	public boolean create(FLogVo logInfo) throws BizException {
		
		try {
			return dao.create(logInfo);
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
	 */
	public List<FLogVo> getLstLogInfor(SortObjVo sortObj) throws BizException {
		try {
			return dao.getLstLogInfor(sortObj);
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
	 */
	public List<FLogVo> getLstLogInfor(FLogConditionVo conditionObj)throws BizException{
			try {
			return dao.getLstLogInfor(conditionObj);
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
	 */
	public int countLogInfor(FLogConditionVo conditionObj)
			throws BizException {
		try {
			return dao.countLogInfor(conditionObj);
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
	 */
	public int countVoByUserUser(String strUserUser) throws BizException {
		try {
			return dao.countVoByUserUser(strUserUser);
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
	* @param FLogListVo , String
	* @return int
	* @throws BizException
	*/
	public boolean exportEXCEL(String strFilePath, FLogConditionVo conditionVo) throws BizException{
		try {
			List<FLogVo> lstFLogVo = null; 

			/** データある場合、データ取得 */
			lstFLogVo = dao.exportEXCEL(conditionVo);

			if (lstFLogVo == null || lstFLogVo.size() == 0) {
			    return false;
			}
			WritableWorkbook workbook = Workbook.createWorkbook(new File(strFilePath));
			WritableSheet s1 = workbook.createSheet("F_LOG", 0);

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
			 "日付"
			,"時刻"
			,"担当者"
			,"機能名"
			,"操作"
			,"メモ"
			,"担当者コード"
			,"PC名"
			,"USER_TYPE"
			};

			int columnCount = excelHeader.length;
			for(int i=0; i< columnCount; i++){
    			Label lbHeader = new Label(i, 0, excelHeader[i], headFormat);	
    			s1.addCell(lbHeader);
    			s1.setColumnView(i, 20); 
			}
			for (int i = 0; i < lstFLogVo.size(); i++) {
				int column = 0;
				FLogVo fLogVo = (FLogVo) lstFLogVo.get(i);
				
				s1.addCell(new Label(column++, i+1, StringUtils.convertIntegerToStr(fLogVo.getActDate()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertIntegerToStr(fLogVo.getActTime()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, fLogVo.getUserUser(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, fLogVo.getMenuexeCode(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, fLogVo.getActionType(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, fLogVo.getText(), leftCellFormat));				
				s1.addCell(new Label(column++, i+1, fLogVo.getEmpCode(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, fLogVo.getPcid(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, fLogVo.getUserType(), leftCellFormat));
			}
			workbook.write();
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}  
		return true;
	}
}

