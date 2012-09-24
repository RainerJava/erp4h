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
import com.fas.vo.slp.SlpVo;
import com.fas.vo.user.LoginUser;

/**
 * @author PC13
 *
 */
public interface FSlpDao {
	
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
	public List<SlpVo> getLstSlpVo()throws DaoException;
	public SlpVo getSlpVo(String SlpType, String SlpDate)throws DaoException;
	public boolean updateSlpVo(SlpVo dataVo)throws DaoException;
}
