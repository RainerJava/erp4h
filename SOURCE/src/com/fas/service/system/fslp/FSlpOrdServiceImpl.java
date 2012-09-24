/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：FSlpServiceImpl.java
*
*     記述				：
*     
*     作成日			：2010/03/17   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/
package com.fas.service.system.fslp;

import java.util.List;

import com.fas.common.constants.ApplicationConstants;
import com.fas.common.exception.BizException;
import com.fas.common.exception.DaoException;
import com.fas.common.utils.DateUtils;
import com.fas.dao.system.fslp.FSlpOdrDao;
import com.fas.dao.system.fslp.FSlpOdrDaoImpl;
import com.fas.service.base.BaseService;
import com.fas.vo.slp.SlpOdrVo;

/**
 * @author PC13
 *
 */
public class FSlpOrdServiceImpl extends BaseService implements FSlpOrdService {
	
	/** */
	private FSlpOdrDao fSlpDao;
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public FSlpOrdServiceImpl() {
		fSlpDao = new FSlpOdrDaoImpl();
	}

	/* (non-Javadoc)
	 * @see com.pipe.service.slp.FSlpService#getSlpNumber(java.lang.String, java.lang.String)
	 */
	public int getSlpNumber(String slpType, String slpDate) throws BizException {
		try {
			return fSlpDao.getSlpNumber(slpType, slpDate, ApplicationConstants.getLoginUser());
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}

	public List<SlpOdrVo> getLstSlpVo() throws BizException {
		try {
			return fSlpDao.getLstSlpVo();
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}

	public SlpOdrVo getSlpVo(String custCode, String drawType) throws BizException {
		try {
			return fSlpDao.getSlpVo(custCode, drawType);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}

	public boolean updateSlpVo(SlpOdrVo dataVo) throws BizException {
		try {
			dataVo.setLastupUser(ApplicationConstants.getLoginUser().getUserId());
			dataVo.setLastupPc(ApplicationConstants.getLoginUser().getLoginPC());
			dataVo.setLastupDate(DateUtils.getIntDate());
			dataVo.setLastupTime(DateUtils.getITime());
			return fSlpDao.updateSlpVo(dataVo);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
	
	public boolean insertSlpOdrVo(SlpOdrVo dataVo) throws BizException {
		try {
			dataVo.setAddUser(ApplicationConstants.getLoginUser().getUserId());
			dataVo.setAddPc(ApplicationConstants.getLoginUser().getLoginPC());
			dataVo.setAddDate(DateUtils.getIntDate());
			dataVo.setAddTime(DateUtils.getITime());
			dataVo.setLastupUser(ApplicationConstants.getLoginUser().getUserId());
			dataVo.setLastupPc(ApplicationConstants.getLoginUser().getLoginPC());
			dataVo.setLastupDate(DateUtils.getIntDate());
			dataVo.setLastupTime(DateUtils.getITime());
			return fSlpDao.insertSlpOdrVo(dataVo);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
	
	public boolean delSlpByCustCode(String custCode)throws BizException{
		try {
			
			return fSlpDao.delSlpByCustCode(custCode);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
	
}
