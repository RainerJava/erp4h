/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：ComboDao.java
*
*     記述				：
*     
*     作成日			：2010/02/16   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/
package com.fas.dao.common.checkbox;

import java.util.List;

import com.fas.common.exception.DaoException;
import com.fas.vo.base.CheckBoxVo;

/**
 * @author PC13
 *
 */
public interface CheckBoxDao {
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return
	 * @throws DaoException
	 */
	public List<CheckBoxVo> getLstINSP(String isppCode) throws DaoException;
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return
	 * @throws DaoException
	 */
	public List<CheckBoxVo> getLstFRE() throws DaoException;
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return
	 * @throws DaoException
	 */
	public List<CheckBoxVo> getLstINSP2() throws DaoException ;
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return
	 * @throws DaoException
	 */
	public List<CheckBoxVo> getLstState() throws DaoException ;
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return
	 */
	public List<CheckBoxVo> getLstPassType() throws DaoException ;

}
