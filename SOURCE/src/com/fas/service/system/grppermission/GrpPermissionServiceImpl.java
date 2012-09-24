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

package com.fas.service.system.grppermission;

import java.util.List;

import com.fas.common.constants.ApplicationConstants;
import com.fas.common.exception.BizException;
import com.fas.common.exception.DaoException;
import com.fas.common.utils.DateUtils;
import com.fas.dao.system.grppermission.GrpPermissionDao;
import com.fas.dao.system.grppermission.GrpPermissionDaoImpl;
import com.fas.service.base.BaseService;
import com.fas.service.system.usergrp.UserGrpService;
import com.fas.service.system.usergrp.UserGrpServiceImpl;
import com.fas.vo.menuexe.MenuExeVo;
import com.fas.vo.menugrp.MenuGrpVo;
import com.fas.vo.usergrp.UserGrpVo;

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
public class GrpPermissionServiceImpl extends BaseService implements GrpPermissionService {

	/** */
	private GrpPermissionDao grpPermissionDao; 
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 * </DL>
	 */
	public GrpPermissionServiceImpl() {
		grpPermissionDao = new GrpPermissionDaoImpl();
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.service.grppermission.GrpPermissionService#getLstMenuVoWithPermission(java.lang.String)
	 */
	public List<MenuExeVo> getLstMenuVoWithPermission(String strUserId) throws BizException {
		try {
			return grpPermissionDao.getLstMenuVoWithPermission(strUserId);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.service.grppermission.GrpPermissionService#getLstMenuGrpVoWithPermission(java.lang.String)
	 */
	public List<MenuGrpVo> getLstMenuGrpVoWithPermission(String strUserId) throws BizException {
		try {
			return grpPermissionDao.getLstMenuGrpVoWithPermission(strUserId);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.service.grppermission.GrpPermissionService#createPermission(java.util.List, java.util.List, com.pipe.vo.usergrp.UserGrpVo)
	 */
	public boolean createPermission(List<MenuGrpVo> lstMenuGrpVo, List<MenuExeVo> lstMenuExeVo, UserGrpVo userGrpVo) throws BizException {

		try {

			UserGrpService userGrpService = new UserGrpServiceImpl();
			
			userGrpVo.setAddUser(ApplicationConstants.getLoginUser().getUserId());
			userGrpVo.setAddPc(ApplicationConstants.getLoginUser().getLoginPC());
			userGrpVo.setAddDate(DateUtils.getIntDate());
			userGrpVo.setAddTime(DateUtils.getITime());
			userGrpVo.setLastupUser(ApplicationConstants.getLoginUser().getUserId());
			userGrpVo.setLastupPc(ApplicationConstants.getLoginUser().getLoginPC());
			userGrpVo.setLastupDate(DateUtils.getIntDate());
			userGrpVo.setLastupTime(DateUtils.getITime());
			
			
			userGrpService.createUserGrp(userGrpVo);
			
			for (int i = 0; i < lstMenuExeVo.size(); i++) {
				lstMenuExeVo.get(i).setAddUser(ApplicationConstants.getLoginUser().getUserId());
				lstMenuExeVo.get(i).setAddPc(ApplicationConstants.getLoginUser().getLoginPC());
				lstMenuExeVo.get(i).setAddDate(DateUtils.getIntDate());
				lstMenuExeVo.get(i).setAddTime(DateUtils.getITime());
				lstMenuExeVo.get(i).setLastupUser(ApplicationConstants.getLoginUser().getUserId());
				lstMenuExeVo.get(i).setLastupPc(ApplicationConstants.getLoginUser().getLoginPC());
				lstMenuExeVo.get(i).setLastupDate(DateUtils.getIntDate());
				lstMenuExeVo.get(i).setLastupTime(DateUtils.getITime());
			}			
			grpPermissionDao.createMenuExePermission(lstMenuExeVo);
			
			for (int i = 0; i < lstMenuGrpVo.size(); i++) {
				lstMenuGrpVo.get(i).setAddUser(ApplicationConstants.getLoginUser().getUserId());
				lstMenuGrpVo.get(i).setAddPc(ApplicationConstants.getLoginUser().getLoginPC());
				lstMenuGrpVo.get(i).setAddDate(DateUtils.getIntDate());
				lstMenuGrpVo.get(i).setAddTime(DateUtils.getITime());
				lstMenuGrpVo.get(i).setLastupUser(ApplicationConstants.getLoginUser().getUserId());
				lstMenuGrpVo.get(i).setLastupPc(ApplicationConstants.getLoginUser().getLoginPC());
				lstMenuGrpVo.get(i).setLastupDate(DateUtils.getIntDate());
				lstMenuGrpVo.get(i).setLastupTime(DateUtils.getITime());
			}			
			grpPermissionDao.createMenuGrpPermission(lstMenuGrpVo);
			
		} catch (DaoException e) {
			delPermission(userGrpVo);
			throw new BizException(e);
		} catch(BizException biz) {
			delPermission(userGrpVo);
			throw biz;
		}
		
		return true;
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.service.grppermission.GrpPermissionService#updatePermission(java.util.List, java.util.List, com.pipe.vo.usergrp.UserGrpVo)
	 */
	public boolean updatePermission(List<MenuGrpVo> lstMenuGrpVo, List<MenuExeVo> lstMenuExeVo, UserGrpVo userGrpVo) throws BizException {

		try {
			
			UserGrpService userGrpService = new UserGrpServiceImpl();
			
			userGrpVo.setLastupUser(ApplicationConstants.getLoginUser().getUserId());
			userGrpVo.setLastupPc(ApplicationConstants.getLoginUser().getLoginPC());
			userGrpVo.setLastupDate(DateUtils.getIntDate());
			userGrpVo.setLastupTime(DateUtils.getITime());
			
			userGrpService.updateUserGrp(userGrpVo);
			
			for (int i = 0; i < lstMenuExeVo.size(); i++) {
				lstMenuExeVo.get(i).setAddUser(ApplicationConstants.getLoginUser().getUserId());
				lstMenuExeVo.get(i).setAddPc(ApplicationConstants.getLoginUser().getLoginPC());
				lstMenuExeVo.get(i).setAddDate(DateUtils.getIntDate());
				lstMenuExeVo.get(i).setAddTime(DateUtils.getITime());
				lstMenuExeVo.get(i).setLastupUser(ApplicationConstants.getLoginUser().getUserId());
				lstMenuExeVo.get(i).setLastupPc(ApplicationConstants.getLoginUser().getLoginPC());
				lstMenuExeVo.get(i).setLastupDate(DateUtils.getIntDate());
				lstMenuExeVo.get(i).setLastupTime(DateUtils.getITime());
			}			
			
			grpPermissionDao.updateMenuExePermission(lstMenuExeVo);
			
			for (int i = 0; i < lstMenuGrpVo.size(); i++) {
				lstMenuGrpVo.get(i).setAddUser(ApplicationConstants.getLoginUser().getUserId());
				lstMenuGrpVo.get(i).setAddPc(ApplicationConstants.getLoginUser().getLoginPC());
				lstMenuGrpVo.get(i).setAddDate(DateUtils.getIntDate());
				lstMenuGrpVo.get(i).setAddTime(DateUtils.getITime());
				lstMenuGrpVo.get(i).setLastupUser(ApplicationConstants.getLoginUser().getUserId());
				lstMenuGrpVo.get(i).setLastupPc(ApplicationConstants.getLoginUser().getLoginPC());
				lstMenuGrpVo.get(i).setLastupDate(DateUtils.getIntDate());
				lstMenuGrpVo.get(i).setLastupTime(DateUtils.getITime());
			}			
			
			grpPermissionDao.updateMenuGrpPermission(lstMenuGrpVo);
			
		} catch (DaoException e) {
			throw new BizException(e);
		} catch(BizException biz) {
			throw biz;
		}
		
		return true;
	}

	/* (non-Javadoc)
	 * @see com.pipe.service.grppermission.GrpPermissionService#delPermission(java.util.List, java.util.List, com.pipe.vo.usergrp.UserGrpVo)
	 */
	public boolean delPermission(UserGrpVo userGrpVo) throws BizException {
		try {
			UserGrpService userGrpService = new UserGrpServiceImpl();
			userGrpService.delUserGrp(userGrpVo);
			grpPermissionDao.delPermission(userGrpVo);
		} catch (DaoException e) {
			throw new BizException(e);
		} catch(BizException biz) {
			throw biz;
		}
		return true;
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.service.grppermission.GrpPermissionService#isInUse(com.pipe.vo.usergrp.UserGrpVo)
	 */
	public boolean isInUse(UserGrpVo userGrpVo) throws BizException {
		try {
			return grpPermissionDao.isInUse(userGrpVo);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
	
}
