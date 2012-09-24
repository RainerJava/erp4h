/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：MenuService.java
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

import java.util.List;

import com.fas.common.exception.BizException;
import com.fas.vo.menuexe.MenuExeVo;
import com.fas.vo.user.LoginUser;

/**
 * @author PC13
 *
 */
public interface MenuService {
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param strMenuGrp
	 * @param userInfor
	 * @return
	 * @throws BizException
	 */
	public List<MenuExeVo> getLstMenuExeVoWithPermis(String strMenuGrp, LoginUser userInfor) throws BizException;

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
	public List<MenuExeVo> getLstMyMenuExe(LoginUser userInfor) throws BizException;

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param userInfor
	 * @param lstData
	 * @return
	 * @throws BizException
	 */
	public List<MenuExeVo> createMyMenu(LoginUser userInfor, List lstData) throws BizException;
}
