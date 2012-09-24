/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：MenuGrpDao.java
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
package com.fas.dao.system.menugrp;

import java.util.List;

import com.fas.common.exception.DaoException;
import com.fas.vo.menugrp.MenuGrpVo;
import com.fas.vo.user.LoginUser;

/**
 * @author PC13
 *
 */
public interface MenuGrpDao {
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return
	 * @throws DaoException
	 */
	public List<MenuGrpVo> getLstMenuGrpVo() throws DaoException;
	


	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param userInfor
	 * @return
	 * @throws DaoException
	 */
	public List<MenuGrpVo> getLstMenuGrpVoWithPermissionFilter(LoginUser userInfor) throws DaoException;
}
