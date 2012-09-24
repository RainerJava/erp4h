/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：MenuGrpService.java
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
package com.fas.service.system.menugrp;

import java.util.List;

import com.fas.common.exception.BizException;
import com.fas.vo.menugrp.MenuGrpVo;
import com.fas.vo.user.LoginUser;

/**
 * @author PC13
 *
 */
public interface MenuGrpService {
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return
	 * @throws BizException
	 */
	public List<MenuGrpVo> getLstMenuGrpVo() throws BizException;
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param userInfor
	 * @return
	 * @throws BizException
	 */
	public List<MenuGrpVo> getLstMenuGrpVoWithPermissionFilter(LoginUser userInfor) throws BizException;
}
