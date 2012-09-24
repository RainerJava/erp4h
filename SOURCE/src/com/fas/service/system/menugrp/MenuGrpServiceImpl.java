package com.fas.service.system.menugrp;

import java.util.List;

import com.fas.common.exception.BizException;
import com.fas.common.exception.DaoException;
import com.fas.dao.system.menugrp.MenuGrpDao;
import com.fas.dao.system.menugrp.MenuGrpDaoImpl;
import com.fas.service.base.BaseService;
import com.fas.vo.menugrp.MenuGrpVo;
import com.fas.vo.user.LoginUser;

public class MenuGrpServiceImpl extends BaseService implements MenuGrpService {
	
	private MenuGrpDao menuGrpDao = null;
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public MenuGrpServiceImpl() {
		menuGrpDao = new MenuGrpDaoImpl();
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.service.menugrp.MenuGrpService#getLstMenuGrpVo()
	 */
	public List<MenuGrpVo> getLstMenuGrpVo() throws BizException {
		try {
			return menuGrpDao.getLstMenuGrpVo();
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
	
	public List<MenuGrpVo> getLstMenuGrpVoWithPermissionFilter(LoginUser userInfor) throws BizException {
		try {
			return menuGrpDao.getLstMenuGrpVoWithPermissionFilter(userInfor);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
}
