/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：MenuExeDao.java
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
package com.fas.dao.system.mymenu;

import java.util.List;

import com.fas.common.exception.DaoException;
import com.fas.vo.menuexe.MenuExeVo;

/**
 * @author PC13
 *
 */
public interface MyMenuDao {
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param strUserId
	 * @return
	 * @throws DaoException
	 */
	public List<MenuExeVo> getLstMyMenuVo(String strUserId) throws DaoException;
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param strUserId
	 * @return
	 * @throws DaoException
	 */
	public List<MenuExeVo> getLstMyMenuVoG(String strUserId) throws DaoException;

}