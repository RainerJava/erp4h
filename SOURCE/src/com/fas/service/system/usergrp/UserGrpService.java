/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名		：エスイーシー化成
* 
*     ファイル名		：UserGrpService.java
*
*     記述			：
*     
*     作成日			：Apr 16, 2010   
*
*     作成者			：Bui Ngoc Viet
*
*     備考			：
*
**************************************************************************************/

package com.fas.service.system.usergrp;

import com.fas.common.exception.BizException;
import com.fas.vo.user.LoginUser;
import com.fas.vo.usergrp.UserGrpVo;

/**
 * <DL>
 *   <DT>クラス記述：</DT>
 * 	<DD></DD>
 * <BR>
 *   <DT>変更歴史：</DT>
 * 	<DD>著作者: Bui Ngoc Viet</DD><BR>
 * 	<DD></DD>
 * </DL>
 */
public interface UserGrpService {
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD>ログインする</DD>
	 * <BR>
	 *
	 * </DL>
	 * @param userInfor
	 * @return
	 * @throws BizException
	 */
	public LoginUser login(LoginUser userInfor) throws BizException;
	
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param pcId
	 * @return
	 * @throws BizException
	 */
	public String getLastLoginUser(String pcId) throws BizException;
	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @param userGrpId
	* @return
	* @throws BizException
	* </DL>
	*/
	public UserGrpVo getUserGrp(String userGrpId) throws BizException;
	
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
	public UserGrpVo updateUserGrp(UserGrpVo userGrpVo) throws BizException;
	
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
	public UserGrpVo createUserGrp(UserGrpVo userGrpVo) throws BizException;
	
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
	public boolean delUserGrp(UserGrpVo userGrpVo) throws BizException;
	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @return
	* @throws BizException
	* </DL>
	*/
	public int getUserGrpCount() throws BizException;

}
