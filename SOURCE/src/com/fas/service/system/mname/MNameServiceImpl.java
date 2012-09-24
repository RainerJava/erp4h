/** *********************************************************************************
 *     
 *     会社名			：林兼コンピューター株式会社
 *
 *     プロジェクト名	：
 * 
 *     ファイル名		：NameServiceImpl.java
 *
 *     記述				：
 *     
 *     作成日			：2009/12/09   
 *
 *     作成者			：PC13
 *
 *     備考				：
 *
 **************************************************************************************/
package com.fas.service.system.mname;

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
import com.fas.common.utils.StringUtils;
import com.fas.dao.system.mname.MNameDao;
import com.fas.dao.system.mname.MNameDaoImpl;
import com.fas.service.base.BaseService;
import com.fas.vo.mname.MNameVo;
import com.fas.vo.mnamecls.MNameclsVo;

/**
 * @author PC13
 * 
 */
/**
 * @author Administrator
 * @date 2011/09/02
 * @description
 */
/**
 * @author Administrator
 * @date 2011/09/02
 * @description
 */
public class MNameServiceImpl extends BaseService implements MNameService {

	/** */
	MNameDao dao = null;

	/**
	 * 
	 */
	public MNameServiceImpl() {
		dao = new MNameDaoImpl();
	}
	
	/* 
	 * @return
	 * @throws BizException
	 * @description
	 */
	public List<MNameVo> getAll() throws BizException{
		try {
			return dao.getAll();
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}

	/* 
	 * @param nameclsCode
	 * @param nameCode
	 * @return
	 * @throws BizException
	 * @description
	 */
	public MNameVo getByKey(String nameclsCode, String nameCode) throws BizException{
		try {			
			return dao.getByKey(nameclsCode, nameCode);
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
	public MNameVo getByKey(MNameVo dataVo) throws BizException{
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
	public boolean insertVo(MNameVo dataVo) throws BizException{
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
	public boolean updateVo(MNameVo dataVo) throws BizException{
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
	public boolean deleteVo(MNameVo dataVo) throws SQLException, BizException{
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
	public boolean isDoubleKey(MNameVo dataVo) throws BizException{
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
	public MNameVo getLatestCode() throws BizException{
		try {
			return dao.getLatestCode();
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}

	public boolean exportEXCEL(String strFilePath) throws BizException{
		try {
			List<MNameVo> lstMNameVo = null; 

			/** データある場合、データ取得 */
			lstMNameVo = dao.exportEXCEL();

			if (lstMNameVo == null || lstMNameVo.size() == 0) {
			    return false;
			}
			WritableWorkbook workbook = Workbook.createWorkbook(new File(strFilePath));
			WritableSheet s1 = workbook.createSheet("M_NAME", 0);

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
			 "NAMECLS_CODE"
			,"NAME_CODE"
			,"NAME_NAME"
			,"NAME_RNAME"
			,"NAME_PNAME"
			,"NAME_TNAME"
			,"DEFAULT_TYPE"
			,"DSP_FLG"
			,"DSPORDER_NO"
			,"NAMETYPE"
			,"ADD_USER"
			,"ADD_PC"
			,"ADD_DATE"
			,"ADD_TIME"
			,"LASTUP_USER"
			,"LASTUP_PC"
			,"LASTUP_DATE"
			,"LASTUP_TIME"
			};

			int columnCount = excelHeader.length;
			for(int i=0; i< columnCount; i++){
    			Label lbHeader = new Label(i, 0, excelHeader[i], headFormat);	
    			s1.addCell(lbHeader);
    			s1.setColumnView(i, 20); 
			}
			for (int i = 0; i < lstMNameVo.size(); i++) {
				int column = 0;
				MNameVo mNameVo = (MNameVo) lstMNameVo.get(i);

				s1.addCell(new Label(column++, i+1, mNameVo.getNameclsCode(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, mNameVo.getNameCode(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, mNameVo.getNameName(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, mNameVo.getNameRname(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, mNameVo.getNamePname(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, mNameVo.getNameTname(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, mNameVo.getDefaultType(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, mNameVo.getDspFlg(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, StringUtils.convertIntegerToStr(mNameVo.getDsporderNo()), rightCellFormat));
				s1.addCell(new Label(column++, i+1, mNameVo.getNametype(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, mNameVo.getAddUserView(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, mNameVo.getAddPc(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, DateUtils.getDateWithSplitYobi(mNameVo.getAddDate()), centerCellFormat));
				s1.addCell(new Label(column++, i+1, DateUtils.getTimeWithSplit(mNameVo.getAddTime()), centerCellFormat));
				s1.addCell(new Label(column++, i+1, mNameVo.getLastupUserView(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, mNameVo.getLastupPc(), leftCellFormat));
				s1.addCell(new Label(column++, i+1, DateUtils.getDateWithSplitYobi(mNameVo.getLastupDate()), centerCellFormat));
				s1.addCell(new Label(column++, i+1, DateUtils.getTimeWithSplit(mNameVo.getLastupTime()), centerCellFormat));
			}
			workbook.write();
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}  
		return true;
	}
	
	/* 
	 * @return
	 * @throws BizException
	 * @description
	 */
	public Map<String, MNameVo> getMapNameVo() throws BizException {
		try {
			return dao.getMapNameVo();
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}	
	
	/* 
	 * @return
	 * @throws BizException
	 * @description
	 */
	public List<MNameclsVo> getListNameclsVo() throws BizException{
		try {
			return dao.getListNameclsVo();
		} catch (DaoException e) {
			throw new BizException(e);
		}		
	}

	/* 
	 * @return
	 * @throws BizException
	 * @description
	 */
	public int countNameclsVo() throws BizException {
		try {
			return dao.countNameclsVo();
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
	public boolean updateSystemNameVo(MNameVo dataVo) throws BizException {
		try {
			return dao.updateSystemNameVo(dataVo);
		} catch (DaoException e) {
			throw new BizException(e);
		}	
	}
	
}
