/************************************************************************************
*
*	会社名		： 林兼コンピューター株式会社
*
*	プロジェクト名	： fas
*
*	ファイル名		： Pc01ServiceImpl.java
*
*	記述			：
*
*	作成日		：  2011/09/26  11:42:07 午前
*
*	作成者		： LENOVO-F23A3B72
*
*	備考			：
*
************************************************************************************/

package com.fas.service.master.pc01;

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
import com.fas.common.utils.StringUtils;
import com.fas.dao.master.pc01.Pc01Dao;
import com.fas.dao.master.pc01.Pc01DaoImpl;
import com.fas.service.base.BaseService;
import com.fas.vo.pc01.Pc01Vo;

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
public class Pc01ServiceImpl extends BaseService implements Pc01Service {
	
	/** */
	Pc01Dao dao = null;

	/** 
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public Pc01ServiceImpl() {
		dao = new Pc01DaoImpl();
	}
	
	/* 
	 * @param iOrderBy
	 * @return
	 * @throws BizException
	 * @description
	 */
	public List<Pc01Vo> getAll(int iOrderBy) throws BizException{
		try {
			return dao.getAll(iOrderBy);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}

	/* 
	 * @param c0101
	 * @return
	 * @throws BizException
	 * @description
	 */
	public Pc01Vo getByKey(int c0101) throws BizException{
		try {			
			return dao.getByKey(c0101);
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
	public Pc01Vo getByKey(Pc01Vo dataVo) throws BizException{
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
	public boolean insertVo(Pc01Vo dataVo) throws BizException{
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
	public boolean updateVo(Pc01Vo dataVo) throws BizException{
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
	public boolean deleteVo(Pc01Vo dataVo) throws SQLException, BizException{
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
	public boolean isDoubleKey(Pc01Vo dataVo) throws BizException{
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
			List<Pc01Vo> lstPc01Vo = null; 

			/** データある場合、データ取得 */
			lstPc01Vo = dao.exportEXCEL();

			if (lstPc01Vo == null || lstPc01Vo.size() == 0) {
			    return false;
			}
			WritableWorkbook workbook = Workbook.createWorkbook(new File(strFilePath));
			WritableSheet s1 = workbook.createSheet("PC01", 0);

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
			 "C0101"
			,"C0102"
			,"C0103"
			,"C0104"
			,"C0105"
			,"C0106"
			,"C0107"
			,"C0108"
			,"C0109"
			,"C0110"
			,"C0111"
			,"C0112"
			,"C0113"
			,"C0114"
			,"C0115"
			,"C0116"
			,"C0117"
			,"C0118"
			,"C0119"
			,"C0120"
			,"C0121"
			,"C0122"
			,"C0123"
			,"C0124"
			,"C0125"
			,"C0126"
			,"C0127"
			,"C0128"
			,"C0129"
			,"C0130"
			,"C0131"
			,"C0132"
			,"C0133"
			,"C0134"
			,"C0135"
			,"C0136"
			,"C0137"
			,"C0138"
			,"C0139"
			,"C0140"
			,"C0141"
			,"C0142"
			,"C0143"
			,"C0144"
			,"C0145"
			,"C0146"
			,"C0147"
			,"C0148"
			,"C0149"
			,"C0150"
			,"C0151"
			,"C0152"
			,"C0153"
			,"C0154"
			,"C0155"
			,"C0156"
			,"C0157"
			,"C0158"
			,"C0159"
			,"C0160"
			,"C0161"
			,"C0162"
			,"C0163"
			,"C0164"
			,"C0165"
			,"C0166"
			,"C0167"
			,"C0168"
			,"C0169"
			,"C0170"
			,"C0171"
			,"C0172"
			,"C0173"
			,"C0174"
			,"C0175"
			,"C0176"
			,"C0177"
			,"C0178"
			,"C0179"
			,"C0180"
			,"C0181"
			,"C0182"
			,"C0183"
			,"C0184"
			,"C0185"
			,"C0186"
			,"C0187"
			,"C0188"
			,"C0189"
			,"C0190"
			,"C0191"
			,"C01DM"
			};

			int columnCount = excelHeader.length;
			for(int i=0; i< columnCount; i++){
    			Label lbHeader = new Label(i, 0, excelHeader[i], headFormat);	
    			s1.addCell(lbHeader);
    			s1.setColumnView(i, 20); 
			}
			for (int i = 0; i < lstPc01Vo.size(); i++) {
				int column = 0;
				Pc01Vo pc01Vo = (Pc01Vo) lstPc01Vo.get(i);

				s1.addCell(new Label(column++, i+1, StringUtils.convertIntegerToStr(pc01Vo.getC0101()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertIntegerToStr(pc01Vo.getC0102()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertIntegerToStr(pc01Vo.getC0103()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, pc01Vo.getC0104(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertIntegerToStr(pc01Vo.getC0105()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertIntegerToStr(pc01Vo.getC0106()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertIntegerToStr(pc01Vo.getC0107()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertIntegerToStr(pc01Vo.getC0108()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertIntegerToStr(pc01Vo.getC0109()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertIntegerToStr(pc01Vo.getC0110()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertIntegerToStr(pc01Vo.getC0111()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertIntegerToStr(pc01Vo.getC0112()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertIntegerToStr(pc01Vo.getC0113()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertIntegerToStr(pc01Vo.getC0114()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertIntegerToStr(pc01Vo.getC0115()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertIntegerToStr(pc01Vo.getC0116()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertIntegerToStr(pc01Vo.getC0117()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertIntegerToStr(pc01Vo.getC0118()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertIntegerToStr(pc01Vo.getC0119()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertIntegerToStr(pc01Vo.getC0120()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertIntegerToStr(pc01Vo.getC0121()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, pc01Vo.getC0122(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, pc01Vo.getC0123(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, pc01Vo.getC0124(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, pc01Vo.getC0125(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertIntegerToStr(pc01Vo.getC0126()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, pc01Vo.getC0127(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, pc01Vo.getC0128(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertDoubleToStr(pc01Vo.getC0129()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertDoubleToStr(pc01Vo.getC0130()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertDoubleToStr(pc01Vo.getC0131()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertDoubleToStr(pc01Vo.getC0132()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertDoubleToStr(pc01Vo.getC0133()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertDoubleToStr(pc01Vo.getC0134()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertDoubleToStr(pc01Vo.getC0135()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertDoubleToStr(pc01Vo.getC0136()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertDoubleToStr(pc01Vo.getC0137()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertDoubleToStr(pc01Vo.getC0138()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertDoubleToStr(pc01Vo.getC0139()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertDoubleToStr(pc01Vo.getC0140()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertDoubleToStr(pc01Vo.getC0141()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertDoubleToStr(pc01Vo.getC0142()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertDoubleToStr(pc01Vo.getC0143()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertDoubleToStr(pc01Vo.getC0144()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertDoubleToStr(pc01Vo.getC0145()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertDoubleToStr(pc01Vo.getC0146()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertDoubleToStr(pc01Vo.getC0147()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertDoubleToStr(pc01Vo.getC0148()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertDoubleToStr(pc01Vo.getC0149()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertDoubleToStr(pc01Vo.getC0150()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertDoubleToStr(pc01Vo.getC0151()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertDoubleToStr(pc01Vo.getC0152()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertDoubleToStr(pc01Vo.getC0153()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertDoubleToStr(pc01Vo.getC0154()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertDoubleToStr(pc01Vo.getC0155()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertDoubleToStr(pc01Vo.getC0156()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertDoubleToStr(pc01Vo.getC0157()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertDoubleToStr(pc01Vo.getC0158()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertDoubleToStr(pc01Vo.getC0159()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertDoubleToStr(pc01Vo.getC0160()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertDoubleToStr(pc01Vo.getC0161()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertDoubleToStr(pc01Vo.getC0162()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertDoubleToStr(pc01Vo.getC0163()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertDoubleToStr(pc01Vo.getC0164()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertDoubleToStr(pc01Vo.getC0165()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertDoubleToStr(pc01Vo.getC0166()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertDoubleToStr(pc01Vo.getC0167()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertIntegerToStr(pc01Vo.getC0168()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertIntegerToStr(pc01Vo.getC0169()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertIntegerToStr(pc01Vo.getC0170()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertIntegerToStr(pc01Vo.getC0171()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertIntegerToStr(pc01Vo.getC0172()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertIntegerToStr(pc01Vo.getC0173()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertIntegerToStr(pc01Vo.getC0174()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertIntegerToStr(pc01Vo.getC0175()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertIntegerToStr(pc01Vo.getC0176()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertIntegerToStr(pc01Vo.getC0177()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertIntegerToStr(pc01Vo.getC0178()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertIntegerToStr(pc01Vo.getC0179()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, pc01Vo.getC0180(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, pc01Vo.getC0181(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, pc01Vo.getC0182(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertIntegerToStr(pc01Vo.getC0183()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, pc01Vo.getC0184(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, pc01Vo.getC0185(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertIntegerToStr(pc01Vo.getC0186()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertIntegerToStr(pc01Vo.getC0187()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertIntegerToStr(pc01Vo.getC0188()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertIntegerToStr(pc01Vo.getC0189()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, pc01Vo.getC0190(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, pc01Vo.getC0191(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, pc01Vo.getC01dm(), leftCellFormat));
			}
			workbook.write();
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}  
		return true;
	}
}