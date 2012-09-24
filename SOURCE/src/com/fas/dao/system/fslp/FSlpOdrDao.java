/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：FSlpDao.java
*
*     記述				：
*     
*     作成日			：2010/03/17   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/
package com.fas.dao.system.fslp;

import java.util.List;

import com.fas.common.exception.DaoException;
import com.fas.vo.slp.SlpOdrVo;
import com.fas.vo.user.LoginUser;

/**
 * @author PC13
 *
 */
public interface FSlpOdrDao {
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param slpType
	 * @param slpDate
	 * @param userInfor
	 * @return
	 * @throws DaoException
	 */
	public int getSlpNumber(String slpType, String slpDate,  LoginUser userInfor) throws DaoException;
	public List<SlpOdrVo> getLstSlpVo() throws DaoException ;
	public SlpOdrVo getSlpVo(String custCode, String drawType) throws DaoException ;
	public boolean updateSlpVo(SlpOdrVo dataVo) throws DaoException;
	public boolean insertSlpOdrVo(SlpOdrVo dataVo) throws DaoException;
	public boolean delSlpByCustCode(String custCode) throws DaoException;
}
