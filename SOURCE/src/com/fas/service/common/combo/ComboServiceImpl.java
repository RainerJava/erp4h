/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：ComboServiceImpl.java
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
package com.fas.service.common.combo;

import java.util.ArrayList;
import java.util.List;

import com.fas.common.exception.BizException;
import com.fas.common.exception.DaoException;
import com.fas.dao.common.combo.ComboDao;
import com.fas.dao.common.combo.ComboDaoImpl;
import com.fas.vo.base.ComboObjectVo;
import com.fas.vo.user.LoginUser;

/**
 * @author PC13
 *
 */
public class ComboServiceImpl implements ComboService {

	private ComboDao comboDao;
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public ComboServiceImpl() {
		comboDao = new ComboDaoImpl();
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
	public List<ComboObjectVo> getLstSect() throws BizException {
		try {
			return comboDao.getLstSect();
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
	public List<ComboObjectVo> getLstLMS() throws BizException {
		try {
			return comboDao.getLstLMS();
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.service.combo.ComboService#getLstMenuGrpVo(com.pipe.vo.user.LoginUser)
	 */
	public List<ComboObjectVo> getLstMenuGrpVo(LoginUser userInfor) throws BizException {
		try {
			List<ComboObjectVo> lstData = comboDao.getLstMenuGrpVo(userInfor);
			ComboObjectVo mneData = new ComboObjectVo();
			mneData.setCode("000");
			mneData.setValue1("（空白）");
			lstData.add(0, mneData);
			return lstData;
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
	public List<ComboObjectVo> getLstINSPP() throws BizException {
		try {
			return comboDao.getLstINSPP();
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
	public List<ComboObjectVo> getLstSEND() throws BizException {
		try {
			return comboDao.getLstFromName("SEND", false);
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
	public List<ComboObjectVo> getLstLine() throws BizException {
		try {
			return comboDao.getLstFromName("LINE", false);
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
	public List<ComboObjectVo> getLstCalcType() throws BizException {
		try {
			return comboDao.getLstFromName("CALFM", false);
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
	public List<ComboObjectVo> getLstFraction() throws BizException {
		try {
			return comboDao.getLstFromName("FRC", false);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
	/* (non-Javadoc)
	 * @see com.pipe.service.combo.ComboService#getLstColor(java.lang.String)
	 */
	public List<ComboObjectVo> getLstColor(String userId) throws BizException {
		try {
			return comboDao.getLstColor(userId);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.pipe.service.combo.ComboService#getLstInsp3(java.lang.String, java.lang.String)
	 */
	public List<ComboObjectVo> getLstInsp3(String insp1Code, String insp2Code) throws BizException {
		try {
			return comboDao.getLstInsp3(insp1Code,insp2Code);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
	/* (non-Javadoc)
	 * @see com.pipe.service.combo.ComboService#getLstInsp3(int, int, java.lang.String, java.lang.String)
	 */
	public List<ComboObjectVo> getLstInsp3(int inspNo, int planDate, String insp1Code, String insp2Code) throws BizException {
		try {
			return comboDao.getLstInsp3(inspNo, planDate, insp1Code, insp2Code);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.pipe.service.combo.ComboService#getLstInsp4(java.lang.String, java.lang.String, java.lang.String)
	 */
	public List<ComboObjectVo> getLstInsp4(String insp1Code, String insp2Code, String insp3Code) throws BizException {
		try {
			return comboDao.getLstInsp4(insp1Code,insp2Code,insp3Code);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.pipe.service.combo.ComboService#getLstState()
	 */
	public List<ComboObjectVo> getLstState() throws BizException {
		try {
			return comboDao.getLstState();
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.service.combo.ComboService#getLstState()
	 */
	public List<ComboObjectVo> getLstUnit() throws BizException {
		try {
			return comboDao.getLstFromName("UNIT", false);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
	/* (non-Javadoc)
	 * @see com.pipe.service.combo.ComboService#getLstInsp3Service()
	 */
	public List<ComboObjectVo> getLstInsp3Service() throws BizException {
		try {
			return comboDao.getLstInsp3Dao();
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
	/* (non-Javadoc)
	 * @see com.pipe.service.combo.ComboService#getLstInsp4Service()
	 */
	public List<ComboObjectVo> getLstInsp4Service() throws BizException {
		try {
			return comboDao.getLstInsp4Dao();
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
	/* (non-Javadoc)
	 * @see com.pipe.service.combo.ComboService#getLstTRTM()
	 */
	public List<ComboObjectVo> getLstTRTM() throws BizException {
		try {
			return comboDao.getLstFromName("TRTM", false);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
	/* (non-Javadoc)
	 * @see com.pipe.service.combo.ComboService#getLstFRE()
	 */
	public List<ComboObjectVo> getLstFRE() throws BizException {
		try {
			return comboDao.getLstFromName("FRE", false);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
	/* (non-Javadoc)
	 * @see com.pipe.service.combo.ComboService#getLstRTBK()
	 */
	public List<ComboObjectVo> getLstREBK() throws BizException {
		try {
			return comboDao.getLstFromName("REBK", false);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
	

	/* (non-Javadoc)
	 * @see com.pipe.service.combo.ComboService#getLstEmp()
	 */
	public List<ComboObjectVo> getLstEmp() throws BizException {
		try {
			return comboDao.getLstEmp();
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.pipe.service.combo.ComboService#getLstEmp()
	 */
	public List<ComboObjectVo> getLstUSERGRP() throws BizException 
	{
		try 
		{
			return comboDao.getLstUSERGRP();
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}

	public List<ComboObjectVo> getLstInspArea() throws BizException
	{
		try 
		{
			return comboDao.getLstInspArea();
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
	public List<ComboObjectVo> getLstDpntFlg() throws BizException
	{
		try 
		{
			return comboDao.getLstDpntFlg();
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
	/* (non-Javadoc)
	 * @see com.pipe.service.combo.ComboService#getLstEmp()
	 */
	public List<ComboObjectVo> getLstCTLOrderByCKey(String strUserid,  int iMtnFlg, int iCntFlg) throws BizException 
	{
		try 
		{
			return comboDao.getLstCTLOrderByCKey(strUserid, iMtnFlg, iCntFlg);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}

	public List<ComboObjectVo> getCmbMCtlByUseridAndMtnFlg(String strUserid,  int iMtnFlg) throws BizException
	{
		try 
		{
			return comboDao.getCmbMCtlByUseridAndMtnFlg(strUserid, iMtnFlg);
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
	public List<ComboObjectVo> getLstFromName(String nameclsCode, boolean includeBlank) {
		try {
			return comboDao.getLstFromName(nameclsCode, includeBlank);
		} catch (DaoException e) {
			return new ArrayList<ComboObjectVo>();
		}
	}	
	
	
	/* 
	 * @param includeBlank
	 * @return
	 * @description
	 */
	public List<ComboObjectVo> getLstG0103OfPG01(boolean includeBlank) {
		try {
			return comboDao.getLstG0103OfPG01(includeBlank);
		} catch (DaoException e) {
			return new ArrayList<ComboObjectVo>();
		}
	}
	
	/* 
	 * @param includeBlank
	 * @return
	 * @description
	 */
	public List<ComboObjectVo> getLstG0102OfPG01(boolean includeBlank) {
		try {
			return comboDao.getLstG0102OfPG01(includeBlank);
		} catch (DaoException e) {
			return new ArrayList<ComboObjectVo>();
		}
	}
	
	public List<ComboObjectVo> getLstTTL() throws BizException {
		try {
			return comboDao.getLstTTL();
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
	/* (non-Javadoc)
	 * @see com.pipe.service.combo.ComboService#getLstInsp4(int, int, java.lang.String, java.lang.String, java.lang.String)
	 */
	public List<ComboObjectVo> getLstInsp4(int inspNo, int planDate, String insp1Code, String insp2Code, String insp3Code) throws BizException {
		try {
			return comboDao.getLstInsp4(inspNo, planDate, insp1Code, insp2Code, insp3Code);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
	public List<ComboObjectVo> getLstMenuExe() throws BizException {
		try {
			return comboDao.getLstMenuExe();
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}

	public List<ComboObjectVo> getLstUser() throws BizException {
		try {
			return comboDao.getLstUser();
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}

	public List<ComboObjectVo> getLstUserGrp() throws BizException {
		try {
			return comboDao.getLstUserGrp();
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}

	public List<ComboObjectVo> getLstColorCls() throws BizException {
		try {
			return comboDao.getLstColorCls();
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
	
	
	/* pipe project */
	public List<ComboObjectVo> getLstLoginUser() throws BizException {
		try {
			return comboDao.getLstLoginUser();
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
	public List<ComboObjectVo> getLstMNamePdmeter() throws BizException {
		try {
			return comboDao.getLstFromMasterName("PDMETER", false);
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
	public List<ComboObjectVo> getLstBENDERR(String nameclsCode) throws BizException {
		try {
			return comboDao.getLstBENDERR(nameclsCode);
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
	public List<ComboObjectVo> getLstMNameBenderr() throws BizException {
		try {
			return comboDao.getLstFromMasterName("BENDERR", false);
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
	public List<ComboObjectVo> getLstMNameProc() throws BizException {
		try {
			return comboDao.getLstFromName("PROC", false);
		} catch (DaoException e) {
			throw new BizException(e);
		}		
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.service.combo.ComboService#getLstMNameDraw()
	 */
	public List<ComboObjectVo> getLstMNameDraw() throws BizException {
		try {
			return comboDao.getLstFromName("DRAW", false);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.service.combo.ComboService#getLstMNameDraw()
	 */
	public List<ComboObjectVo> getLstMNameTpbm() throws BizException {
		try {
			return comboDao.getLstFromName("TPBM", false);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.service.combo.ComboService#getLstMNameHurry()
	 */
	public List<ComboObjectVo> getLstMNameHurry() throws BizException {
		try {
			return comboDao.getLstFromName("HURRY", false);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.service.combo.ComboService#getLstMNameOan()
	 */
	public List<ComboObjectVo> getLstMNameOan() throws BizException {
		try {
			return comboDao.getLstFromName("OAN", false);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
		
	/* (non-Javadoc)
	 * @see com.pipe.service.combo.ComboService#getLstMNameStandard()
	 */
	public List<ComboObjectVo> getLstMNameStandard() throws BizException {
		try {
			return comboDao.getLstFromName("STANDARD", false);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.service.combo.ComboService#getLstMNameStandard()
	 */
	public List<ComboObjectVo> getLstMNameTtl() throws BizException {
		try {
			return comboDao.getLstFromName("TTL", false);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
	
	/**
	 * @return
	 * @throws BizException
	 */
	public List<ComboObjectVo> getLstMNameCustType() throws BizException {
		try {
			return comboDao.getLstFromName("CUSTTYPE", false);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
	
	public List<ComboObjectVo> getMenuPrt() throws BizException
	{
		try {
			return comboDao.getMenuPrt();
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}

	public List<ComboObjectVo> getLstTrOrdrHOrdrNo(String strCustCode, String strShipNo, String strDrawType) throws BizException 
	{
		try {
			return comboDao.getLstTrOrdrHOrdrNo(strCustCode, strShipNo, strDrawType);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
	/* (non-Javadoc)
	 * @see com.pipe.service.combo.ComboService#getLstSrcLike()
	 */
	public List<ComboObjectVo> getLstSrcLike() throws BizException {
		try {
			return comboDao.getLstFromName("SRC_LIKE", false);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.service.combo.ComboService#getLstWtcalType()
	 */
	public List<ComboObjectVo> getLstWtcalType() throws BizException {
		try {
			return comboDao.getLstFromName("WTCAL", false);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
	
	@Override
	public List<ComboObjectVo> getLstDecPlc() throws BizException {
		try {
			return comboDao.getLstDecPlc();
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}	
}
