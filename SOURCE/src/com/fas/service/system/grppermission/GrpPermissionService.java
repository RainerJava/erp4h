/************************************************************************************
*
*	会社名		： 林兼コンピューター株式会社
*
*	プロジェクト名	： fas
*
*	ファイル名		： MEmpDao.java
*
*	記述			：
*
*	作成日		：  2011/08/26  11:55:02 午前
*
*	作成者		： LENOVO-F23A3B72
*
*	備考			：
*
************************************************************************************/

package com.fas.service.system.grppermission;

import java.util.List;

import com.fas.common.exception.BizException;
import com.fas.vo.menuexe.MenuExeVo;
import com.fas.vo.menugrp.MenuGrpVo;
import com.fas.vo.usergrp.UserGrpVo;

/**
 * <DL>
 *   <DT>クラス記述：</DT>
 * 	 <DD></DD>
 * <BR>
 *   <DT>変更歴史：</DT>
 * 	 <DD>著作者: LENOVO-F23A3B72 </DD><BR>
 * 	 <DD></DD>
 * </DL>
**/
public interface GrpPermissionService {
	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @param strUserId
	* @return
	* @throws BizException
	* </DL>
	*/
	public List<MenuExeVo> getLstMenuVoWithPermission(String strUserId) throws BizException;
	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @param strUserId
	* @return
	* @throws BizException
	* </DL>
	*/
	public List<MenuGrpVo> getLstMenuGrpVoWithPermission(String strUserId) throws BizException;

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @param lstMenuGrpVo
	* @param lstMenuExeVo
	* @param userGrpVo
	* @return
	* @throws BizException
	* </DL>
	*/
	public boolean createPermission(List<MenuGrpVo> lstMenuGrpVo, List<MenuExeVo> lstMenuExeVo, UserGrpVo userGrpVo) throws BizException;
	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @param strUserId
	* @return
	* @throws BizException
	* </DL>
	*/
	public boolean updatePermission(List<MenuGrpVo> lstMenuGrpVo, List<MenuExeVo> lstMenuExeVo, UserGrpVo userGrpVo) throws BizException;

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @param userGrpVo
	* @return
	* @throws BizException
	* </DL>
	*/
	public boolean delPermission(UserGrpVo userGrpVo) throws BizException;

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @param userGrpVo
	* @return
	* @throws BizException
	* </DL>
	*/
	public boolean isInUse(UserGrpVo userGrpVo) throws BizException;
}
