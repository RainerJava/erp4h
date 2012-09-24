/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：ColorDao.java
*
*     記述				：
*     
*     作成日			：2010/02/22   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/
package com.fas.service.common.color;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fas.common.exception.BizException;
import com.fas.vo.color.DClrCtlVo;
import com.fas.vo.color.MClrCtlVo;
import com.fas.vo.color.MColorVo;

/**
 * @author PC13
 *
 */
public interface ColorService {

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param userId
	 * @return
	 * @throws BizException
	 */
	public Map<String, MClrCtlVo> getMapMClrCtlVo(String userId) throws BizException;
	

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
	public Map<String, DClrCtlVo> getMapDClrCtlVo() throws BizException;

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param userId
	 * @return
	 * @throws BizException
	 */
	public Map<String, MClrCtlVo> copyClrCtlVo(String userId) throws BizException;
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param clrKey
	 * @return
	 * @throws BizException
	 */
	public DClrCtlVo getDClrCtlVo(String clrKey) throws BizException;
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param clrVo
	 * @return
	 * @throws BizException
	 */
	public MClrCtlVo updateMClrCtlVo(MClrCtlVo clrVo) throws BizException;
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param colorclsCode
	 * @return
	 * @throws BizException
	 */
	public Map<String, MColorVo> getMapMColorVo(String colorclsCode) throws BizException;
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param colorclsCode
	 * @return
	 * @throws BizException
	 */
	public List<MColorVo> getLstMColorVo(String colorclsCode) throws BizException;

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @param colorclsCode
	* @param iOrderBy
	* @return
	* @throws BizException
	* </DL>
	*/
	public List<MColorVo> getLstMColorVo(String colorclsCode, int iOrderBy) throws BizException;

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @param strColorclsCode
	* @return
	* @throws BizException
	* </DL>
	*/
	public int getCountVo(String strColorclsCode) throws BizException;
	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @param strColorclsCode
	* @return
	* @throws BizException
	* </DL>
	*/
	public String getMaxColorCode(String strColorclsCode) throws BizException;

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @param dataVo
	* @return
	* @throws BizException
	* </DL>
	*/
	public boolean updateMColorVo(MColorVo dataVo) throws BizException;

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @return
	* @throws BizException
	* </DL>
	*/
	public ArrayList<ArrayList<String>> exportExcel() throws BizException;
	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @param strColorClsCode
	* @param strColorCode
	* @return
	* @throws BizException
	* </DL>
	*/
	public MColorVo getMColorVo(String strColorClsCode, String strColorCode) throws BizException;
	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @param dataVo
	* @return
	* @throws BizException
	* </DL>
	*/
	public boolean insertColorVo(MColorVo dataVo) throws BizException;
	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @param dataVo
	* @return
	* @throws BizException
	* </DL>
	*/
	public boolean delColorVo(MColorVo dataVo) throws BizException;
	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @return
	* @throws BizException
	* </DL>
	*/
	public int countAllColorVo() throws BizException;

	/**
	 * 
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @param strUserID
	* @return
	* @throws BizException
	* </DL>
	 */
	public boolean deleteClrCtlByUserID(String strUserID) throws BizException;
}
