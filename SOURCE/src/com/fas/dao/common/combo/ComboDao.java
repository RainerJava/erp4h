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
package com.fas.dao.common.combo;

import java.util.List;

import com.fas.common.exception.BizException;
import com.fas.common.exception.DaoException;
import com.fas.vo.base.ComboObjectVo;
import com.fas.vo.user.LoginUser;

/**
 * @author PC13
 *
 */
public interface ComboDao {
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
	public List<ComboObjectVo> getLstSect() throws DaoException;
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
	public List<ComboObjectVo> getLstEmp() throws DaoException;
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
	public List<ComboObjectVo> getLstLMS() throws DaoException;
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param userInfor
	 * @return
	 * @throws DaoException
	 */
	public List<ComboObjectVo> getLstMenuGrpVo(LoginUser userInfor) throws DaoException;
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
	public List<ComboObjectVo> getLstINSPP() throws DaoException ;
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
	public List<ComboObjectVo> getLstState() throws DaoException;
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
	public List<ComboObjectVo> getLstColor(String userId) throws DaoException;
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
	public List<ComboObjectVo> getLstInsp3(String insp1Code, String insp2Code) throws DaoException;
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param inspNo
	 * @param planDate
	 * @param insp1Code
	 * @param insp2Code
	 * @return
	 * @throws DaoException
	 */
	public List<ComboObjectVo> getLstInsp3(int inspNo, int planDate, String insp1Code, String insp2Code) throws DaoException;
	public List<ComboObjectVo> getLstInsp4(String insp1Code, String insp2Code, String insp3Code) throws DaoException;
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param inspNo
	 * @param planDate
	 * @param insp1Code
	 * @param insp2Code
	 * @param insp3Code
	 * @return
	 * @throws DaoException
	 */
	public List<ComboObjectVo> getLstInsp4(int inspNo, int planDate, String insp1Code, String insp2Code, String insp3Code) throws DaoException;
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
	public List<ComboObjectVo> getLstInsp3Dao() throws DaoException ;
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
	public List<ComboObjectVo> getLstInsp4Dao() throws DaoException;
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
	public List<ComboObjectVo> getLstBENDERR(String nameclsCode) throws DaoException;
	public List<ComboObjectVo> getLstFromName(String nameclsCode, boolean includeBlank) throws DaoException;
	
	/**
	 * @param includeBlank
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public List<ComboObjectVo> getLstG0103OfPG01(boolean includeBlank) throws DaoException;
	
	/**
	 * @param includeBlank
	 * @return
	 * @throws DaoException
	 * @description
	 */
	public List<ComboObjectVo> getLstG0102OfPG01(boolean includeBlank) throws DaoException;
	
	public List<ComboObjectVo> getLstFromMasterName(String nameclsCode, boolean includeBlank) throws DaoException ;

	public List<ComboObjectVo> getLstTTL()throws DaoException;
	//Add by HUNG NV 2010.04.05 13h46
	public List<ComboObjectVo> getLstUSERGRP() throws DaoException;	
	
	public List<ComboObjectVo> getLstInspArea() throws DaoException;	
	public List<ComboObjectVo> getLstDpntFlg() throws DaoException;
	public List<ComboObjectVo> getLstCTLOrderByCKey(String strUserid,  int iMtnFlg, int iCntFlg) throws DaoException;
	
	public List<ComboObjectVo> getCmbMCtlByUseridAndMtnFlg(String strUserid,  int iMtnFlg) throws DaoException;
	public List<ComboObjectVo> getLstUserGrp() throws DaoException;
	public List<ComboObjectVo> getLstUser() throws DaoException;
	public List<ComboObjectVo> getLstMenuExe() throws DaoException;
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* 	<DT>created by longtv</DT>
	* <BR>
	* @return
	* @throws DaoException
	* </DL>
	*/
	public List<ComboObjectVo> getLstColorCls() throws DaoException;

	/* pipe project */
	public List<ComboObjectVo> getLstLoginUser() throws DaoException;

	/**
	 * @return
	 * @throws DaoException
	 */	
	public List<ComboObjectVo> getMenuPrt() throws DaoException;

	public List<ComboObjectVo> getLstTrOrdrHOrdrNo(String strCustCode, String strShipNo, String strDrawType) throws DaoException;
	
	public List<ComboObjectVo> getLstSizeNameFrmProd(String lclsCode, String mclsCode, String sclsCode, String pdmeter, String materCode, String stdCode, String typeCode) throws DaoException;
	
	public List<ComboObjectVo> getLstSizeNameFrmProd(String lclsCode, String mclsCode, String sclsCode, String pdmeter, String materCode,String stdCode, String typeCode, String custCode) throws DaoException;
	
	public List<ComboObjectVo> getLstDecPlc() throws DaoException;
	
	
}
