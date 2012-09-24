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
package com.fas.service.common.color;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fas.common.constants.ApplicationConstants;
import com.fas.common.exception.BizException;
import com.fas.common.exception.DaoException;
import com.fas.common.utils.DateUtils;
import com.fas.dao.common.color.ColorDao;
import com.fas.dao.common.color.ColorDaoImpl;
import com.fas.service.base.BaseService;
import com.fas.vo.color.DClrCtlVo;
import com.fas.vo.color.MClrCtlVo;
import com.fas.vo.color.MColorVo;

/**
 * @author PC13
 *
 */
public class ColorServiceImpl extends BaseService implements ColorService {

	/** */
	private ColorDao colorDao = null;
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public ColorServiceImpl() {
		colorDao = new ColorDaoImpl();
	}

	/* (non-Javadoc)
	 * @see com.pipe.service.color.ColorService#getMapMClrCtlVo(java.lang.String)
	 */
	public Map<String, MClrCtlVo> getMapMClrCtlVo(String userId) throws BizException {
		try {
			return colorDao.getMapMClrCtlVo(userId);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.service.color.ColorService#getMapDClrCtlVo(java.lang.String)
	 */
	public Map<String, DClrCtlVo> getMapDClrCtlVo() throws BizException {
		try {
			return colorDao.getMapDClrCtlVo();
		} catch (DaoException e) {
			throw new BizException(e);
		}	
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.service.color.ColorService#copyClrCtlVo(java.lang.String)
	 */
	public Map<String, MClrCtlVo> copyClrCtlVo(String userId) throws BizException {
		try {
			return colorDao.copyClrCtlVo(userId);
		} catch (DaoException e) {
			throw new BizException(e);
		}	
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.service.color.ColorService#getDClrCtlVo(java.lang.String)
	 */
	public DClrCtlVo getDClrCtlVo(String clrKey) throws BizException {
		try {
			return colorDao.getDClrCtlVo(clrKey);
		} catch (DaoException e) {
			throw new BizException(e);
		}	
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.service.color.ColorService#updateMClrCtlVo(com.pipe.vo.color.MClrCtlVo)
	 */
	public MClrCtlVo updateMClrCtlVo(MClrCtlVo clrVo) throws BizException {
		try {
			return colorDao.updateMClrCtlVo(clrVo);
		} catch (DaoException e) {
			throw new BizException(e);
		}	
	}

	/* (non-Javadoc)
	 * @see com.pipe.service.color.ColorService#getMapMColorVo(java.lang.String)
	 */
	public Map<String, MColorVo> getMapMColorVo(String colorclsCode) throws BizException {
		try {
			return colorDao.getMapMColorVo(colorclsCode);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.pipe.service.color.ColorService#getLstMColorVo(java.lang.String)
	 */
	public List<MColorVo> getLstMColorVo(String colorclsCode) throws BizException {
		try {
			return colorDao.getLstMColorVo(colorclsCode);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
	
	public List<MColorVo> getLstMColorVo(String colorclsCode, int iOrderBy) throws BizException
	{
		try {
			return colorDao.getLstMColorVo(colorclsCode, iOrderBy);
		} catch (DaoException e) {
			throw new BizException(e);
		}	
	}

	public int getCountVo(String strColorclsCode) throws BizException 
	{
		try {
			return colorDao.getCountVo(strColorclsCode);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
	
	public String getMaxColorCode(String strColorclsCode) throws BizException 
	{
		try {
			return colorDao.getMaxColorCode(strColorclsCode);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}

	public boolean updateMColorVo(MColorVo dataVo) throws BizException 
	{
		try {
			dataVo.setLastupUser(ApplicationConstants.getLoginUser().getUserId());
			dataVo.setLastupPc(ApplicationConstants.getLoginUser().getLoginPC());
			dataVo.setLastupDate(DateUtils.getIntDate());
			dataVo.setLastupTime(DateUtils.getITime());
			return colorDao.updateMColorVo(dataVo);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}

	public ArrayList<ArrayList<String>> exportExcel() throws BizException
	{
		try {
			return colorDao.exportExcel();
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}

	public MColorVo getMColorVo(String strColorClsCode, String strColorCode)
			throws BizException {
		try {
			return colorDao.getMColorVo(strColorClsCode, strColorCode);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.pipe.service.color.ColorService#insertColorVo(com.pipe.vo.color.MColorVo)
	 */
	public boolean insertColorVo(MColorVo dataVo) throws BizException{
		try {
			dataVo.setAddUser(ApplicationConstants.getLoginUser().getUserId());
			dataVo.setAddPc(ApplicationConstants.getLoginUser().getLoginPC());
			dataVo.setAddDate(DateUtils.getIntDate());
			dataVo.setAddTime(DateUtils.getITime());
			dataVo.setLastupUser(ApplicationConstants.getLoginUser().getUserId());
			dataVo.setLastupPc(ApplicationConstants.getLoginUser().getLoginPC());
			dataVo.setLastupDate(DateUtils.getIntDate());
			dataVo.setLastupTime(DateUtils.getITime());
			
			dataVo.setDefaultType("0");
			dataVo.setColortype("M");
			
			return colorDao.insertColorVo(dataVo);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.pipe.service.color.ColorService#delColorVo(com.pipe.vo.color.MColorVo)
	 */
	public boolean delColorVo(MColorVo dataVo) throws BizException {
		try {
			return colorDao.delColorVo(dataVo);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.service.color.ColorService#countAllColorVo()
	 */
	public int countAllColorVo() throws BizException{
		try {
			return colorDao.countAllColorVo();
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.service.color.ColorService#deleteClrCtlByUserID(java.lang.String)
	 */
	@Override
	public boolean deleteClrCtlByUserID(String strUserID) throws BizException {
		try {
			return colorDao.deleteClrCtlByUserID(strUserID);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
}
