/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：UserServiceImpl.java
*
*     記述				：
*     
*     作成日			：2009/10/06   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/

package com.fas.service.system.user;

import org.apache.log4j.Logger;

import com.fas.common.exception.BizException;
import com.fas.common.exception.DaoException;
import com.fas.dao.system.user.UserDao;
import com.fas.dao.system.user.UserDaoImpl;
import com.fas.dao.system.usergrp.MUserGrpDao;
import com.fas.dao.system.usergrp.MUserGrpDaoImpl;
import com.fas.vo.user.LoginUser;

/**
 * <DL>
 *   <DT>クラス記述：</DT>
 * 	<DD></DD>
 * <BR>
 *   <DT>変更歴史：</DT>
 * 	<DD>著作者: PC13</DD><BR>
 * 	<DD></DD>
 * </DL>
 */

/**
 * @author PC12
 *
 */
/**
 * @author PC12
 *
 */
public class UserServiceImpl implements UserService {
	
	/** */
	static Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	private UserDao userDao = null;
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public UserServiceImpl() {
		userDao = new UserDaoImpl();
	}

	/* (non-Javadoc)
	 * @see com.linc.service.user.UserService#login(com.linc.vo.user.LoginUser)
	 */
	public LoginUser login(LoginUser userInfor) throws BizException {
		try {
			if (userInfor.isGroup()) {
				MUserGrpDao userGrpDao = new MUserGrpDaoImpl();
				return userGrpDao.login(userInfor);
			} else {
				return userDao.login(userInfor);
			}
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.linc.service.user.UserService#getLastLoginUser()
	 */
	public String getLastLoginUser(String pcId) throws BizException {
		try {
			return userDao.getLastLoginUser(pcId);
		} catch (Exception e) {
			throw new BizException(e);
		}
	}
}

