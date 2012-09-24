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
import com.fas.dao.system.fslp.FSlpDao;
import com.fas.dao.system.fslp.FSlpDaoImpl;
import com.fas.service.base.BaseService;
import com.fas.vo.slp.SlpVo;

/**
 * @author PC13
 *
 */
public class FSlpServiceImpl extends BaseService implements FSlpService {
	
	/** */
	private FSlpDao fSlpDao;
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public FSlpServiceImpl() {
		fSlpDao = new FSlpDaoImpl();
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

	public List<SlpVo> getLstSlpVo() throws BizException {
		try {
			return fSlpDao.getLstSlpVo();
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}

	public SlpVo getSlpVo(String slpType, String slpDate) throws BizException {
		try {
			return fSlpDao.getSlpVo(slpType, slpDate);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}

	public boolean updateSlpVo(SlpVo dataVo) throws BizException {
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
}
