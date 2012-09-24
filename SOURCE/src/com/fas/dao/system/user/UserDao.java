/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：UserDao.java
*
*     記述				：
*     
*     作成日			：2009/10/06   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/

package com.fas.dao.system.user;

import com.fas.common.exception.DaoException;
import com.fas.vo.user.LoginUser;

/**
 * <DL>
 *   <DT>クラス記述：</DT>
 * 	<DD></DD>
 * <BR>
 *   <DT>変更歴史：</DT>
 * 	<DD>著作者: PC13</DD><BR>
 * 	<DD></DD>
 * </DL>
 */

public interface UserDao {
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD>ログインする</DD>
	 * <BR>
	 *
	 * </DL>
	 * @param userInfor
	 * @return
	 * @throws DaoException
	 */
	public LoginUser login(LoginUser userInfor) throws DaoException;
	
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param pcId
	 * @return
	 * @throws DaoException
	 */
	public String getLastLoginUser(String pcId) throws DaoException;
	
}