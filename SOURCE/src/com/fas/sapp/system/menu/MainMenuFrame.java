/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：MainMenuFrame.java
*
*     記述				：
*     
*     作成日			：2010/01/20   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/
package com.fas.sapp.system.menu;

import com.fas.common.constants.ApplicationConstants;
import com.fas.common.constants.dbtable.MCtlConstants;
import com.fas.common.utils.DateUtils;
import com.fas.sapp.base.MenuBase;

/**
 * @author PC13
 *
 */
public class MainMenuFrame extends MenuBase {

	/** */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected String getHelpInfor() {
		return "メニューを選択して下さい。";
	}

	@Override
	protected String getSubName() {
		return MCtlConstants.getValueByCKey("CMP_ZSYS");
	}

	protected String getLoginTime() {
		return DateUtils.getCurrentDateTime();
	}
	
	protected String getLoginUserFullName() {
		return ApplicationConstants.LOGIN_USER_ID + "（" + ApplicationConstants.loginUser.getUserName() + "）";
		//return "";
	}
}
