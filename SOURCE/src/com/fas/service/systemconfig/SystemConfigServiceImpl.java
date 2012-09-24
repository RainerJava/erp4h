/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：SystemConfigServiceImpl.java
*
*     記述				：
*     
*     作成日			：2010/03/11   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/
package com.fas.service.systemconfig;

import com.fas.common.constants.ApplicationConstants;
import com.fas.common.constants.dbtable.MCtlConstants;
import com.fas.common.constants.dbtable.NameConstants;
import com.fas.common.exception.BizException;
import com.fas.common.utils.DateUtils;
import com.fas.service.base.BaseService;
import com.fas.service.common.color.ColorService;
import com.fas.service.common.color.ColorServiceImpl;
import com.fas.service.init.InitService;
import com.fas.service.init.InitServiceImpl;
import com.fas.service.system.mctl.MCtlService;
import com.fas.service.system.mctl.MCtlServiceImpl;
import com.fas.service.system.mname.MNameService;
import com.fas.service.system.mname.MNameServiceImpl;
import com.fas.vo.color.MClrCtlVo;
import com.fas.vo.config.SystemConfigVo;
import com.fas.vo.mctl.MCtlVo;
import com.fas.vo.mname.MNameVo;

/**
 * @author PC13
 *
 */
public class SystemConfigServiceImpl extends BaseService implements SystemConfigService {

	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public SystemConfigServiceImpl() {
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.service.systemconfig.SystemConfigService#updateSystemConfig(com.pipe.vo.config.SystemConfigVo)
	 */
	public void updateSystemConfig(SystemConfigVo configVo) throws BizException {
		
		MCtlService ctlService = new MCtlServiceImpl();
		if (configVo.getIAction() == 0) {//基本設定
			
			MCtlVo ctlVo = new MCtlVo();
			ctlVo.setUserid("SYSTEM");
			ctlVo.setLastupUser(ApplicationConstants.getLoginUser().getUserId());
			ctlVo.setLastupPc(ApplicationConstants.getLoginUser().getLoginPC());
			ctlVo.setLastupDate(DateUtils.getCurrentDate());
			ctlVo.setLastupTime(DateUtils.getITime());
			//会社名を変更
			ctlVo.setCKey("CMP_NAME");
			ctlVo.setCData(configVo.getShaMei());
			ctlService.updateVo(ctlVo);
			//自動採番
			ctlVo.setCKey("SLP_AUTO");
			ctlVo.setCData(configVo.getJidouSaiban());
			ctlService.updateVo(ctlVo);
			//自動採番(受注:まだつかいません
			
			//データ入力期間
			ctlVo.setCKey("DATE_ENT");
			ctlVo.setCData(configVo.getKikanFrom() + configVo.getKikanTo());
			ctlService.updateVo(ctlVo);
			//品名桁数
			ctlVo.setCKey("BM_PROD");
			ctlVo.setCData(configVo.getHinmei());
			ctlService.updateVo(ctlVo);
			//得意先桁数
			ctlVo.setCKey("BM_CUST");
			ctlVo.setCData(configVo.getTokuisaki());
			ctlService.updateVo(ctlVo);
			//支店桁数:使用なし
			
			
			MCtlConstants.setMapMCtlVo(ctlService.getMapMCtlVo());
			
			MNameService nameService = new MNameServiceImpl();
			//敬称
			MNameVo nameVo = new MNameVo();
			nameVo.setNameclsCode("TTL");
			nameVo.setLastupUser(ApplicationConstants.getLoginUser().getUserId());
			nameVo.setLastupPc(ApplicationConstants.getLoginUser().getLoginPC());
			nameVo.setLastupDate(DateUtils.getCurrentDate());
			nameVo.setLastupTime(DateUtils.getITime());
			//敬称１
			nameVo.setNameName(configVo.getKeishou1());
			nameVo.setNameRname(configVo.getKeishou1());
			nameVo.setNameCode("1");
			nameService.updateSystemNameVo(nameVo);
			//敬称２
			nameVo.setNameName(configVo.getKeishou2());
			nameVo.setNameRname(configVo.getKeishou2());
			nameVo.setNameCode("2");
			nameService.updateSystemNameVo(nameVo);
			//敬称３
			nameVo.setNameName(configVo.getKeishou3());
			nameVo.setNameRname(configVo.getKeishou3());
			nameVo.setNameCode("3");
			nameService.updateSystemNameVo(nameVo);
			//敬称４
			nameVo.setNameName(configVo.getKeishou4());
			nameVo.setNameRname(configVo.getKeishou4());
			nameVo.setNameCode("4");
			nameService.updateSystemNameVo(nameVo);
			//敬称５
			nameVo.setNameName(configVo.getKeishou5());
			nameVo.setNameRname(configVo.getKeishou5());
			nameVo.setNameCode("5");
			nameService.updateSystemNameVo(nameVo);
			
			NameConstants.setMapNameVo(nameService.getMapNameVo());
			
		} else if (configVo.getIAction() == 1) {//権限設定
			
			MCtlVo ctlVo = new MCtlVo();
			ctlVo.setUserid("SYSTEM");
			ctlVo.setLastupUser(ApplicationConstants.getLoginUser().getUserId());
			ctlVo.setLastupPc(ApplicationConstants.getLoginUser().getLoginPC());
			ctlVo.setLastupDate(DateUtils.getCurrentDate());
			ctlVo.setLastupTime(DateUtils.getITime());
			
			//ユーザー権限
			ctlVo.setCKey("USR_PRVL");
			ctlVo.setCData(configVo.getYuzaKengen());
			ctlService.updateVo(ctlVo);
			
			if ("0".equalsIgnoreCase(configVo.getYuzaKengen())) {
				//ログオン情報
				ctlVo.setCKey("USR_RWRT");
				ctlVo.setCData(configVo.getLoguonJouhou());
				ctlService.updateVo(ctlVo);

				//パスワード
				ctlVo.setCKey("PWD_ENPT");
				ctlVo.setCData(configVo.getPassword());
				ctlService.updateVo(ctlVo);

				//ユーザー選択
				ctlVo.setCKey("USR_TYPE");
				ctlVo.setCData(configVo.getYuzaSentaku());
				ctlService.updateVo(ctlVo);
			}
			
			MCtlConstants.setMapMCtlVo(ctlService.getMapMCtlVo());
			
		} else if (configVo.getIAction() == 3) { //表示設定
			MClrCtlVo clrVo = configVo.getClrVo();
			clrVo.setLastupUser(ApplicationConstants.getLoginUser().getUserId());
			clrVo.setLastupPc(ApplicationConstants.getLoginUser().getLoginPC());
			clrVo.setLastupDate(DateUtils.getCurrentDate());
			clrVo.setLastupTime(DateUtils.getITime());
			ColorService colorService = new ColorServiceImpl();
			colorService.updateMClrCtlVo(clrVo);
			InitService initService = new InitServiceImpl();
			initService.initAffterLogin();
		}
	}

}
