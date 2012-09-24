/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：MenuServiceImpl.java
*
*     記述				：
*     
*     作成日			：2010/03/04   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/
package com.fas.service.system.menu;

import java.util.ArrayList;
import java.util.List;

import com.fas.common.exception.BizException;
import com.fas.common.exception.DaoException;
import com.fas.dao.system.menu.MenuDao;
import com.fas.dao.system.menu.MenuDaoImpl;
import com.fas.service.base.BaseService;
import com.fas.vo.menuexe.MenuExeVo;
import com.fas.vo.user.LoginUser;

/**
 * @author PC13
 *
 */
public class MenuServiceImpl extends BaseService implements MenuService {

	/** */
	private MenuDao menuDao = null;
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public MenuServiceImpl() {
		menuDao = new MenuDaoImpl();
	}

	/* (non-Javadoc)
	 * @see com.pipe.service.menu.MenuService#getLstMenuExeVoWithPermis(java.lang.String, com.pipe.vo.user.LoginUser)
	 */
	public List<MenuExeVo> getLstMenuExeVoWithPermis(String strMenuGrp, LoginUser userInfor) throws BizException {
		try {
			if ("000".equalsIgnoreCase(strMenuGrp)) {
				List<MenuExeVo> lstData = new ArrayList<MenuExeVo>();
				MenuExeVo mneVo = new MenuExeVo();
				mneVo.setMenugrpCode("000");
				mneVo.setMenuexeCode("000");
				mneVo.setMenuexeName("（空白）");
				mneVo.setFunType("1");
				mneVo.setMenuexeSeq("99");
				lstData.add(mneVo);
				return lstData;
			} else {
				return menuDao.getLstMenuExeVoWithPermis(strMenuGrp, userInfor);
			}
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.service.menu.MenuService#getLstMyMenuExe(com.pipe.vo.user.LoginUser)
	 */
	public List<MenuExeVo> getLstMyMenuExe(LoginUser userInfor) throws BizException {
		try {
			if (userInfor.isGroup()) {
				return menuDao.getLstMyMenuExeG(userInfor);
			} else {
				return menuDao.getLstMyMenuExe(userInfor);
			}
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.service.menu.MenuService#createMyMenu(com.pipe.vo.user.LoginUser, java.util.List)
	 */
	public List<MenuExeVo> createMyMenu(LoginUser userInfor, List lstData) throws BizException {
		try {
			if (userInfor.isGroup()) {
				return menuDao.createMyMenuG(userInfor, lstData);
			} else {
				return menuDao.createMyMenu(userInfor, lstData);
			}
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}

}