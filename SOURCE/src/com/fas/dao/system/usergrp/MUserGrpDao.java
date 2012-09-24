/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：MUserGrp.java
*
*     記述				：
*     
*     作成日			：2010/03/03   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/
package com.fas.dao.system.usergrp;

import com.fas.common.exception.DaoException;
import com.fas.vo.user.LoginUser;
import com.fas.vo.usergrp.UserGrpVo;

/**
 * @author PC13
 *
 */
public interface MUserGrpDao {
	
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
	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @param userGrpId
	* @return
	* @throws DaoException
	* </DL>
	*/
	public UserGrpVo getUserGrp(String userGrpId) throws DaoException;
	
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
	public UserGrpVo updateUserGrp(UserGrpVo userGrpVo) throws DaoException;
	
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
	public UserGrpVo createUserGrp(UserGrpVo userGrpVo) throws DaoException;
	
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
	public boolean delUserGrp(UserGrpVo userGrpVo) throws DaoException;

	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @return
	* @throws DaoException
	* </DL>
	*/
	public int getUserGrpCount() throws DaoException;
}
