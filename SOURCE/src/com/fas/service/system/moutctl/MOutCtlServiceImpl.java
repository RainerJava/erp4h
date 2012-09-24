/************************************************************************************
*
*	会社名		： 林兼コンピューター株式会社
*
*	プロジェクト名	： fas
*
*	ファイル名		： MEmpDao.java
*
*	記述			：
*
*	作成日		：  2011/08/26  11:55:02 午前
*
*	作成者		： LENOVO-F23A3B72
*
*	備考			：
*
************************************************************************************/

package com.fas.service.system.moutctl;

import java.util.Map;

import com.fas.common.constants.ApplicationConstants;
import com.fas.common.exception.BizException;
import com.fas.common.exception.DaoException;
import com.fas.common.utils.DateUtils;
import com.fas.dao.system.moutctl.MOutCtlDao;
import com.fas.dao.system.moutctl.MOutCtlDaoImpl;
import com.fas.service.base.BaseService;
import com.fas.vo.moutctl.MOutCtlVo;
import com.fas.vo.outctl.OutCtlVo;

/**
 * <DL>
 *   <DT>クラス記述：</DT>
 * 	<DD></DD>
 * <BR>
 *   <DT>変更歴史：</DT>
 * 	<DD>著作者: NVH</DD><BR>
 * 	<DD></DD>
 * </DL>
 */
public class MOutCtlServiceImpl extends BaseService implements MOutCtlService {

	/** */
	private MOutCtlDao mOutCtlDao = null;
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 * </DL>
	 */
	public MOutCtlServiceImpl() {
		mOutCtlDao = new MOutCtlDaoImpl();
	}

	public Map<String, MOutCtlVo> getMapMOutCtlVo() throws BizException {
		try {
			return mOutCtlDao.getMapMOutCtlVo();
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}

	public OutCtlVo getOutCtlVoByUserId(String userId) throws BizException {
		try {
			return mOutCtlDao.getOutCtlVoByUserId(userId);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}

	public boolean insertOutCtlVo(OutCtlVo dataVo) throws BizException {		
		try {
			dataVo.setAddUser(ApplicationConstants.getLoginUser().getUserId());
			dataVo.setAddPc(ApplicationConstants.getLoginUser().getLoginPC());
			dataVo.setAddDate(DateUtils.getIntDate());
			dataVo.setAddTime(DateUtils.getITime());
			dataVo.setLastupUser(ApplicationConstants.getLoginUser().getUserId());
			dataVo.setLastupPc(ApplicationConstants.getLoginUser().getLoginPC());
			dataVo.setLastupDate(DateUtils.getIntDate());
			dataVo.setLastupTime(DateUtils.getITime());
			
			return mOutCtlDao.insertOutCtlVo(dataVo);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}

	public boolean updateOutCtlVo(OutCtlVo dataVo) throws BizException {		
		try {
			dataVo.setLastupUser(ApplicationConstants.getLoginUser().getUserId());
			dataVo.setLastupPc(ApplicationConstants.getLoginUser().getLoginPC());
			dataVo.setLastupDate(DateUtils.getIntDate());
			dataVo.setLastupTime(DateUtils.getITime());
			
			return mOutCtlDao.updateOutCtlVo(dataVo);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
}
