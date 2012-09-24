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
package com.fas.dao.common.color;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fas.common.exception.DaoException;
import com.fas.vo.color.DClrCtlVo;
import com.fas.vo.color.MClrCtlVo;
import com.fas.vo.color.MColorVo;

/**
 * @author PC13
 *
 */
public interface ColorDao {
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param userId
	 * @return
	 * @throws DaoException
	 */
	public Map<String, MClrCtlVo> getMapMClrCtlVo(String userId) throws DaoException;
	
	
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
	public Map<String, DClrCtlVo> getMapDClrCtlVo() throws DaoException;
	
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
	public Map<String, MColorVo> getMapMColorVo(String colorclsCode) throws DaoException;
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param userId
	 * @return
	 * @throws DaoException
	 */
	public Map<String, MClrCtlVo> copyClrCtlVo(String userId) throws DaoException;
	
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
	public DClrCtlVo getDClrCtlVo(String clrKey) throws DaoException;
	

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param clrVo
	 * @return
	 * @throws DaoException
	 */
	public MClrCtlVo updateMClrCtlVo(MClrCtlVo clrVo) throws DaoException;
	
	/**
	 * @param colorclsCode
	 * @return
	 * @throws DaoException
	 */
	public List<MColorVo> getLstMColorVo(String colorclsCode) throws DaoException;

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @param strColorclsCode
	* @param iOderBy
	* @return
	* @throws DaoException
	* </DL>
	*/
	public List<MColorVo> getLstMColorVo(String strColorclsCode, int iOderBy) throws DaoException;

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @param strColorclsCode
	* @return
	* @throws DaoException
	* </DL>
	*/
	public int getCountVo(String strColorclsCode) throws DaoException ;
	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @param strColorclsCode
	* @return
	* @throws DaoException
	* </DL>
	*/
	public String getMaxColorCode(String strColorclsCode) throws DaoException ;

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @param dataVo
	* @return
	* @throws DaoException
	* </DL>
	*/
	public boolean updateMColorVo(MColorVo dataVo) throws DaoException;

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @return
	* @throws DaoException
	* </DL>
	*/
	public ArrayList<ArrayList<String>> exportExcel() throws DaoException ;
	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @param strColorClsCode
	* @param strColorCode
	* @return
	* @throws DaoException
	* </DL>
	*/
	public MColorVo getMColorVo(String strColorClsCode, String strColorCode) throws DaoException;
	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @param dataVo
	* @return
	* @throws DaoException
	* </DL>
	*/
	public boolean insertColorVo(MColorVo dataVo) throws DaoException;
	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @param dataVo
	* @return
	* @throws DaoException
	* </DL>
	*/
	public boolean delColorVo(MColorVo dataVo) throws DaoException;
	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @return
	* @throws DaoException
	* </DL>
	*/
	public int countAllColorVo() throws DaoException;

	/**
	 * 
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @param strUserID
	* @return
	* @throws DaoException
	* </DL>
	 */
	public boolean deleteClrCtlByUserID(String strUserID) throws DaoException;
}
