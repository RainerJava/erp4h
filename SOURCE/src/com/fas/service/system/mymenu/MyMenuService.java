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
package com.fas.service.system.mymenu;

import java.util.List;

import com.fas.common.exception.BizException;
import com.fas.vo.menuexe.MenuExeVo;
import com.fas.vo.user.LoginUser;

/**
 * @author PC13
 *
 */
public interface MyMenuService {
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param userInfo
	 * @return
	 * @throws BizException
	 */
	public List<MenuExeVo> getLstMyMenuVo(LoginUser userInfo) throws BizException;
}
