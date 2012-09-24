package com.fas.service.system.flog;

import java.util.List;

import com.fas.common.exception.BizException;
import com.fas.vo.base.SortObjVo;
import com.fas.vo.flog.FLogConditionVo;
import com.fas.vo.flog.FLogVo;

/**
 * @author PC13
 *
 */
public interface FLogService {

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>	 
	 */
	public boolean create(FLogVo logInfo) throws BizException;
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>	 
	 */
	public List<FLogVo> getLstLogInfor(SortObjVo sortObj) throws BizException;
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>	 
	 */
	public List<FLogVo> getLstLogInfor(FLogConditionVo conditionObj) throws BizException;
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>	 
	 */
	public int countLogInfor(FLogConditionVo conditionObj) throws BizException;
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>	 
	 */
	public int countVoByUserUser(String strUserUser) throws BizException;
	
	/**
		/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param FLogListVo , String
	* @return boolean
	* @throws BizException
	*/
	public boolean exportEXCEL(String strFilePath, FLogConditionVo conditionVo) throws BizException;
}

