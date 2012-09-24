/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名		：エスイーシー化成
* 
*     ファイル名		：UserGrpServiceImpl.java
*
*     記述			：
*     
*     作成日			：Apr 16, 2010   
*
*     作成者			：Bui Ngoc Viet
*
*     備考			：
*
**************************************************************************************/

package com.fas.service.system.usergrp;

import com.fas.common.exception.BizException;
import com.fas.common.exception.DaoException;
import com.fas.dao.system.usergrp.MUserGrpDao;
import com.fas.dao.system.usergrp.MUserGrpDaoImpl;
import com.fas.service.base.BaseService;
import com.fas.vo.user.LoginUser;
import com.fas.vo.usergrp.UserGrpVo;

/**
 * <DL>
 *   <DT>クラス記述：</DT>
 * 	<DD></DD>
 * <BR>
 *   <DT>変更歴史：</DT>
 * 	<DD>著作者: Nguyen Hung</DD><BR>
 * 	<DD></DD>
 * </DL>
 */
public class UserGrpServiceImpl extends BaseService implements UserGrpService {

	private MUserGrpDao userGrpDao;
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 * </DL>
	 */
	public UserGrpServiceImpl() {
		userGrpDao = new MUserGrpDaoImpl();
	}

	/* (non-Javadoc)
	 * @see com.pipe.service.usergrp.UserGrpService#login(com.pipe.vo.user.LoginUser)
	 */
	public LoginUser login(LoginUser userInfor) throws BizException {
		try {
			return userGrpDao.login(userInfor);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.service.usergrp.UserGrpService#getLastLoginUser(java.lang.String)
	 */
	public String getLastLoginUser(String pcId) throws BizException {
		try {
			return userGrpDao.getLastLoginUser(pcId);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.service.usergrp.UserGrpService#getUserGrp(java.lang.String)
	 */
	public UserGrpVo getUserGrp(String userGrpId) throws BizException {
		try {
			return userGrpDao.getUserGrp(userGrpId);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.service.usergrp.UserGrpService#updateUserGrp(com.pipe.vo.usergrp.UserGrpVo)
	 */
	public UserGrpVo updateUserGrp(UserGrpVo userGrpVo) throws BizException {
		try {
			return userGrpDao.updateUserGrp(userGrpVo);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.service.usergrp.UserGrpService#createUserGrp(com.pipe.vo.usergrp.UserGrpVo)
	 */
	public UserGrpVo createUserGrp(UserGrpVo userGrpVo) throws BizException {
		try {
			return userGrpDao.createUserGrp(userGrpVo);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.service.usergrp.UserGrpService#delUserGrp(com.pipe.vo.usergrp.UserGrpVo)
	 */
	public boolean delUserGrp(UserGrpVo userGrpVo) throws BizException {
		try {
			return userGrpDao.delUserGrp(userGrpVo);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.service.usergrp.UserGrpService#getUserGrpCount()
	 */
	public int getUserGrpCount() throws BizException {
		try {
			return userGrpDao.getUserGrpCount();
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
}
