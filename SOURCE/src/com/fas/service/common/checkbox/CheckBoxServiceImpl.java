/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：CheckBoxServiceImpl.java
*
*     記述				：
*     
*     作成日			：2010/03/03   
*
*     作成者			：PC14
*
*     備考				：
*
**************************************************************************************/
package com.fas.service.common.checkbox;

import java.util.List;

import com.fas.common.exception.BizException;
import com.fas.common.exception.DaoException;
import com.fas.dao.common.checkbox.CheckBoxDao;
import com.fas.dao.common.checkbox.CheckBoxDaoImpl;
import com.fas.vo.base.CheckBoxVo;

/**
 * @author PC14
 *
 */
public class CheckBoxServiceImpl implements CheckBoxService {

	private CheckBoxDao checkBoxDao;
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public CheckBoxServiceImpl() {
		checkBoxDao = new CheckBoxDaoImpl();
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return
	 * @throws BizException
	 */
	public List<CheckBoxVo> getLstINSP(String isppCode) throws BizException {
		try {
			return checkBoxDao.getLstINSP(isppCode);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return
	 * @throws BizException
	 */
	public List<CheckBoxVo> getLstFRE() throws BizException {
		try {
			return checkBoxDao.getLstFRE();
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return
	 * @throws BizException
	 */
	public List<CheckBoxVo> getLstINSP2() throws BizException {
		try {
			return checkBoxDao.getLstINSP2();
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.pipe.service.checkbox.CheckBoxService#getLstState()
	 */
	public List<CheckBoxVo> getLstState() throws BizException {
		try {
			return checkBoxDao.getLstState();
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.pipe.service.checkbox.CheckBoxService#getLstPassType()
	 */
	public List<CheckBoxVo> getLstPassType() throws BizException {
		try {
			return checkBoxDao.getLstPassType();
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
	
}
