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

package com.fas.dao.system.grppermission;

import java.util.List;

import com.fas.common.exception.DaoException;
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
public interface GrpPermissionDao {
	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @param lstMenuExeVo
	* @return
	* @throws DaoException
	* </DL>
	*/
	public int[] createMenuExePermission(List<MenuExeVo> lstMenuExeVo) throws DaoException;
	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @param lstMenuExeVo
	* @return
	* @throws DaoException
	* </DL>
	*/
	public int[] updateMenuExePermission(List<MenuExeVo> lstMenuExeVo) throws DaoException;
	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @param strUserId
	* @return
	* @throws DaoException
	* </DL>
	*/
	public List<MenuExeVo> getLstMenuVoWithPermission(String strUserId) throws DaoException;
	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @param strUserId
	* @return
	* @throws DaoException
	* </DL>
	*/
	public List<MenuGrpVo> getLstMenuGrpVoWithPermission(String strUserId) throws DaoException;
	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @param lstMenuGrpVo
	* @return
	* @throws DaoException
	* </DL>
	*/
	public int[] createMenuGrpPermission(List<MenuGrpVo> lstMenuGrpVo) throws DaoException;
	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @param lstMenuGrpVo
	* @return
	* @throws DaoException
	* </DL>
	*/
	public int[] updateMenuGrpPermission(List<MenuGrpVo> lstMenuGrpVo) throws DaoException;
	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @param userGrpVo
	* @return
	* @throws DaoException
	* </DL>
	*/
	public boolean delPermission(UserGrpVo userGrpVo) throws DaoException;
	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @param userGrpVo
	* @return
	* @throws DaoException
	* </DL>
	*/
	public boolean isInUse(UserGrpVo userGrpVo) throws DaoException;
}
