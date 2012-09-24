/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：MenuGrpServiceImpl.java
*
*     記述				：
*     
*     作成日			：2010/01/25   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/
package com.fas.service.system.menuexe;

import java.util.List;

import com.fas.common.constants.ApplicationConstants;
import com.fas.common.exception.BizException;
import com.fas.common.exception.DaoException;
import com.fas.dao.system.menuexe.MenuExeDao;
import com.fas.dao.system.menuexe.MenuExeDaoImpl;
import com.fas.service.base.BaseService;
import com.fas.service.system.mymenu.MyMenuService;
import com.fas.service.system.mymenu.MyMenuServiceImpl;
import com.fas.vo.menuexe.MenuExeVo;
import com.fas.vo.user.LoginUser;

/**
 * @author PC13
 *
 */
public class MenuExeServiceImpl extends BaseService implements MenuExeService {
	
	/** */
	private MenuExeDao menuExeDao = null;
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public MenuExeServiceImpl() {
		menuExeDao = new MenuExeDaoImpl();
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.service.menuexe.MenuGrpService#getLstMenuExeVo()
	 */
	public List<MenuExeVo> getLstMenuExeVo(String strMenuGrp) throws BizException {
		try {
			if ("000".equals(strMenuGrp)) {
				MyMenuService myMenuService = new MyMenuServiceImpl();
				return myMenuService.getLstMyMenuVo(ApplicationConstants.getLoginUser());
			} else {
				return menuExeDao.getLstMenuExeVo(strMenuGrp);
			}
		} catch (DaoException e) {
			throw new BizException(e);
		} catch (BizException eb) {
			throw eb;
		}
	}


	/* (non-Javadoc)
	 * @see com.pipe.service.menuexe.MenuExeService#getLstMyMenuExeVo(com.pipe.vo.user.LoginUser)
	 */
	public List<MenuExeVo> getLstMyMenuExeVo(LoginUser userInfor) throws BizException {
		try {
			MyMenuService myMenuService = new MyMenuServiceImpl();
			return myMenuService.getLstMyMenuVo(ApplicationConstants.getLoginUser());
		} catch (BizException eb) {
			throw eb;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.service.menuexe.MenuExeService#getLstMenuExeVoWithPermis(java.lang.String, com.pipe.vo.user.LoginUser)
	 */
	public List<MenuExeVo> getLstMenuExeVoWithPermis(String strMenuGrp, LoginUser userInfor) throws BizException {
		try {
			return menuExeDao.getLstMenuExeVo(strMenuGrp);
		} catch (DaoException e) {
			throw new BizException(e);
		} 
	}

	
	/* (non-Javadoc)
	 * @see com.pipe.service.menuexe.MenuExeService#getLstMyMenuExeVo(com.pipe.vo.user.LoginUser)
	 */
	public MenuExeVo getMenuExeVoByPathName(String strPathName) throws BizException
	{
		try {			
			return menuExeDao.getMenuExeVoByPathName(strPathName);
		} catch (DaoException ex) {
			throw new BizException(ex);
		}
	}
}