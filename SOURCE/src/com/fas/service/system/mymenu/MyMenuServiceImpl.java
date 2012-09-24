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
package com.fas.service.system.mymenu;

import java.util.List;

import com.fas.common.exception.BizException;
import com.fas.common.exception.DaoException;
import com.fas.dao.system.mymenu.MyMenuDao;
import com.fas.dao.system.mymenu.MyMenuDaoImpl;
import com.fas.service.base.BaseService;
import com.fas.vo.menuexe.MenuExeVo;
import com.fas.vo.user.LoginUser;

/**
 * @author PC13
 *
 */
public class MyMenuServiceImpl extends BaseService implements MyMenuService {
	
	/** */
	private MyMenuDao myMenuDao = null;
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public MyMenuServiceImpl() {
		myMenuDao = new MyMenuDaoImpl();
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.service.mymenu.MyMenuService#getLstMyMenuVo(com.pipe.vo.user.LoginUser)
	 */
	public List<MenuExeVo> getLstMyMenuVo(LoginUser userInfo) throws BizException {
		try {
			if (userInfo.isGroup()) {
				return myMenuDao.getLstMyMenuVoG(userInfo.getUserId());
			} else {
				return myMenuDao.getLstMyMenuVo(userInfo.getUserId());
			}
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}

}
