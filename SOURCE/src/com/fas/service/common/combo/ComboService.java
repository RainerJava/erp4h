/************************************************************************************
*
*	会社名		： 林兼コンピューター株式会社
*
*	プロジェクト名	： fas
*
*	ファイル名		： MEmpDao.java
*
*	記述			：
*
*	作成日		：  2011/08/26  11:55:02 午前
*
*	作成者		： LENOVO-F23A3B72
*
*	備考			：
*
************************************************************************************/
package com.fas.service.common.combo;

import java.util.List;

import com.fas.common.exception.BizException;
import com.fas.vo.base.ComboObjectVo;
import com.fas.vo.user.LoginUser;

/**
 * <DL>
 *   <DT>クラス記述：</DT>
 * 	 <DD></DD>
 * <BR>
 *   <DT>変更歴史：</DT>
 * 	 <DD>著作者: LENOVO-F23A3B72 </DD><BR>
 * 	 <DD></DD>
 * </DL>
**/
public interface ComboService {

	public List<ComboObjectVo> getLstEmp() throws BizException;
	
	public List<ComboObjectVo> getLstMenuGrpVo(LoginUser userInfor) throws BizException;
	
	public List<ComboObjectVo> getLstState() throws BizException;
	
	public List<ComboObjectVo> getLstColor(String userId) throws BizException;
	
	public List<ComboObjectVo> getLstREBK() throws BizException;
	
	public List<ComboObjectVo> getLstUSERGRP() throws BizException;
	
	public List<ComboObjectVo> getLstCTLOrderByCKey(String strUserid, int iMtnFlg, int iCntFlg) throws BizException;
	
	public List<ComboObjectVo> getCmbMCtlByUseridAndMtnFlg(String strUserid, int iMtnFlg) throws BizException;
	
	public List<ComboObjectVo> getLstFromName(String nameclsCode, boolean includeBlank);
	
	/**
	 * @param includeBlank
	 * @return
	 * @description
	 */
	public List<ComboObjectVo> getLstG0103OfPG01(boolean includeBlank);
	
	public List<ComboObjectVo> getLstG0102OfPG01(boolean includeBlank);
	
	public List<ComboObjectVo> getLstUserGrp() throws BizException;
	
	public List<ComboObjectVo> getLstUser() throws BizException;
	
	public List<ComboObjectVo> getLstMenuExe() throws BizException;
	
	public List<ComboObjectVo> getLstColorCls() throws BizException;
	
	public List<ComboObjectVo> getLstLoginUser() throws BizException ;

	public List<ComboObjectVo> getLstCalcType() throws BizException;
	
	public List<ComboObjectVo> getLstFraction() throws BizException;
	
	public List<ComboObjectVo> getLstMNameCustType() throws BizException;
		
	public List<ComboObjectVo> getMenuPrt() throws BizException;
	
	public List<ComboObjectVo> getLstSrcLike() throws BizException;
	
	public List<ComboObjectVo> getLstDecPlc() throws BizException;
}
