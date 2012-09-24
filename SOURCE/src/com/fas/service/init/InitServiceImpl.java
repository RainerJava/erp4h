/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：InitServiceImpl.java
*
*     記述				：
*     
*     作成日			：2009/12/04   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/
package com.fas.service.init;

import java.util.Map;

import com.fas.common.PermissionPolicy;
import com.fas.common.constants.ApplicationConstants;
import com.fas.common.constants.dbtable.DBColorContants;
import com.fas.common.constants.dbtable.FPrinterConstants;
import com.fas.common.constants.dbtable.MCtlConstants;
import com.fas.common.constants.dbtable.MOutCtlContants;
import com.fas.common.constants.dbtable.MessageConstants;
import com.fas.common.constants.dbtable.NameConstants;
import com.fas.common.constants.screen.ColorConstants;
import com.fas.common.exception.BizException;
import com.fas.common.utils.XMLUtils;
import com.fas.service.base.BaseService;
import com.fas.service.common.color.ColorService;
import com.fas.service.common.color.ColorServiceImpl;
import com.fas.service.common.msg.MsgService;
import com.fas.service.common.msg.MsgServiceImpl;
import com.fas.service.common.printer.PrinterService;
import com.fas.service.common.printer.PrinterServiceImpl;
import com.fas.service.system.mctl.MCtlService;
import com.fas.service.system.mctl.MCtlServiceImpl;
import com.fas.service.system.mname.MNameService;
import com.fas.service.system.mname.MNameServiceImpl;
import com.fas.service.system.moutctl.MOutCtlService;
import com.fas.service.system.moutctl.MOutCtlServiceImpl;
import com.fas.service.system.permission.PermissionService;
import com.fas.service.system.permission.PermissionServiceImpl;
import com.fas.vo.color.MClrCtlVo;
import com.fas.vo.fprinter.FPrinterVo;

/**
 * @author PC13
 *
 */
public class InitServiceImpl extends BaseService implements InitService {

	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	
	
	public InitServiceImpl() {
	}
	
	/* (non-Javadoc)
	 * @see com.hanbai.service.init.InitService#init()
	 */
	public void init() throws BizException {
		
		MCtlService ctlService = new MCtlServiceImpl();
		MCtlConstants.setMapMCtlVo(ctlService.getMapMCtlVo());

		MsgService msgService = new MsgServiceImpl();
		MessageConstants.setMapMMsgVo(msgService.getMapMsg());
		
		MNameService nameService = new MNameServiceImpl();
		NameConstants.setMapNameVo(nameService.getMapNameVo());

		PermissionService permission = new PermissionServiceImpl();
		PermissionPolicy.MAP_EXECTL1VO = permission.getMapExect1Vo();
		PermissionPolicy.MAP_EXECTL2VO = permission.getMapExect2Vo();

		ApplicationConstants.LST_HOLIDAY_DATE = XMLUtils.getLstHoliday(ApplicationConstants.XML_HOLIDAY_FILE);
		
		MOutCtlService outCtlService = new MOutCtlServiceImpl();
		MOutCtlContants.setMapMOutCtlVo(outCtlService.getMapMOutCtlVo());
		
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.service.init.InitService#initAffterLogin()
	 */
	public void initAffterLogin() throws BizException {
		
		ColorService colorService = new ColorServiceImpl();
		Map<String, MClrCtlVo> mapColor = colorService.getMapMClrCtlVo(ApplicationConstants.LOGIN_USER_ID);
		
		if (mapColor.size() == 0) {
			mapColor = colorService.copyClrCtlVo(ApplicationConstants.LOGIN_USER_ID);
		}
		
		DBColorContants.setMapMClrCtlVo(mapColor);
		
		
		PrinterService printerService = new PrinterServiceImpl();
		Map<String, FPrinterVo> mapPrinterVo = printerService.getMapFPrinterVo(ApplicationConstants.LOGIN_USER_ID);
		FPrinterConstants.setMapPrinterVo(mapPrinterVo);
		
		ColorConstants.TABLE_ROW_FOCUS_BACKGROUND_COLOR = ((MClrCtlVo) mapColor.get(ApplicationConstants.LOGIN_USER_ID + "INQCSRCLRB")).getColor();
		ColorConstants.TABLE_ROW_FOCUS_FORE_COLOR = ((MClrCtlVo) mapColor.get(ApplicationConstants.LOGIN_USER_ID + "INQCSRCLRF")).getColor();

		ColorConstants.TABLE_EVEN_ROW_BACKGROUND_COLOR = ((MClrCtlVo) mapColor.get(ApplicationConstants.LOGIN_USER_ID + "INQDT1CLRB")).getColor();
		ColorConstants.TABLE_EVEN_ROW_FORE_COLOR = ((MClrCtlVo) mapColor.get(ApplicationConstants.LOGIN_USER_ID + "INQDT1CLRF")).getColor();
		
		ColorConstants.TABLE_ODD_ROW_BACKGROUND_COLOR = ((MClrCtlVo) mapColor.get(ApplicationConstants.LOGIN_USER_ID + "INQDT2CLRB")).getColor();
		ColorConstants.TABLE_ODD_ROW_FORE_COLOR = ((MClrCtlVo) mapColor.get(ApplicationConstants.LOGIN_USER_ID + "INQDT2CLRF")).getColor();

		ColorConstants.TABLE_HEADER_BACKGROUND_COLOR = ((MClrCtlVo) mapColor.get(ApplicationConstants.LOGIN_USER_ID + "INQHDRCLRB")).getColor();
		ColorConstants.TABLE_HEADER_FORE_COLOR = ((MClrCtlVo) mapColor.get(ApplicationConstants.LOGIN_USER_ID + "INQHDRCLRF")).getColor();
	
		ColorConstants.DEFAULT_TEXT_FOCUS_BACKGROUND_COLOR = ((MClrCtlVo) mapColor.get(ApplicationConstants.LOGIN_USER_ID + "ACTCSRCLR")).getColor();
		ColorConstants.DEFAULT_COMBOBOX_FOCUS_BACKGROUND_COLOR = ((MClrCtlVo) mapColor.get(ApplicationConstants.LOGIN_USER_ID + "ACTCSRCLR")).getColor();
		ColorConstants.DEFAULT_CHECKBOX_FOCUS_BACKGROUND_COLOR = ((MClrCtlVo) mapColor.get(ApplicationConstants.LOGIN_USER_ID + "ACTCSRCLR")).getColor();
		ColorConstants.DEFAULT_RAJIO_FOCUS_BACKGROUND_COLOR = ((MClrCtlVo) mapColor.get(ApplicationConstants.LOGIN_USER_ID + "ACTCSRCLR")).getColor();

		ColorConstants.LABEL_CD_BACKGROUND_COLOR = ((MClrCtlVo) mapColor.get(ApplicationConstants.LOGIN_USER_ID + "HDDSPCLRB")).getColor();
		ColorConstants.LABEL_CD_FORE_COLOR = ((MClrCtlVo) mapColor.get(ApplicationConstants.LOGIN_USER_ID + "HDDSPCLRF")).getColor();

		ColorConstants.LABEL_EDIT_BACKGROUND_COLOR = ((MClrCtlVo) mapColor.get(ApplicationConstants.LOGIN_USER_ID + "HDENTCLRB")).getColor();
		ColorConstants.LABEL_EDIT_FORE_COLOR = ((MClrCtlVo) mapColor.get(ApplicationConstants.LOGIN_USER_ID + "HDENTCLRF")).getColor();
		
		ColorConstants.DEFAULT_BACK_COLOR = ((MClrCtlVo) mapColor.get(ApplicationConstants.LOGIN_USER_ID + "BACKCLR")).getColor();
		ColorConstants.PANEL_DEFAULT_COLOR = ((MClrCtlVo) mapColor.get(ApplicationConstants.LOGIN_USER_ID + "BACKCLR")).getColor();
		ColorConstants.FRAME_DEFAULT_COLOR = ((MClrCtlVo) mapColor.get(ApplicationConstants.LOGIN_USER_ID + "BACKCLR")).getColor();
		
		ColorConstants.DEFAULT_TABBEDPANE_BACKGROUND_COLOR = ((MClrCtlVo) mapColor.get(ApplicationConstants.LOGIN_USER_ID + "TABCLRB")).getColor();
		ColorConstants.DEFAULT_TABBEDPANE_FORE_COLOR = ((MClrCtlVo) mapColor.get(ApplicationConstants.LOGIN_USER_ID + "TABCLRF")).getColor();
		
		ColorConstants.DEFAULT_BUTTON_BACKGROUND_COLOR = ColorConstants.DEFAULT_BACK_COLOR;
		ColorConstants.STATUS_BAR_DEFAULT_COLOR = ColorConstants.DEFAULT_BACK_COLOR;
		ColorConstants.DEFAULT_RAJIO_BACKGROUND_COLOR = ColorConstants.DEFAULT_BACK_COLOR;
		ColorConstants.DEFAULT_CHECKBOX_BACKGROUND_COLOR = ColorConstants.DEFAULT_BACK_COLOR;
		ColorConstants.DEFAULT_LABEL_BACKGROUND_COLOR = ColorConstants.DEFAULT_BACK_COLOR;
		
		ColorConstants.DEFAULT_BUTTON_DISABLE_BACKGROUND_COLOR = ColorConstants.DEFAULT_BACK_COLOR;
		ColorConstants.DEFAULT_COMBOBOX_DISABLE_BACKGROUND_COLOR = ColorConstants.DEFAULT_BACK_COLOR;
		ColorConstants.DEFAULT_RAJIO_DISABLE_BACKGROUND_COLOR = ColorConstants.DEFAULT_BACK_COLOR;
		ColorConstants.DEFAULT_CHECKBOX_DISABLE_BACKGROUND_COLOR = ColorConstants.DEFAULT_BACK_COLOR;
		ColorConstants.DEFAULT_TEXT_DISABLE_BACKGROUND_COLOR = ColorConstants.DEFAULT_BACK_COLOR;
		
		ColorConstants.MENU_BUTTON_BACK_COLOR = ((MClrCtlVo) mapColor.get(ApplicationConstants.LOGIN_USER_ID + "MENUCLRB")).getColor();
		ColorConstants.MENU_BUTTON_FORE_COLOR = ((MClrCtlVo) mapColor.get(ApplicationConstants.LOGIN_USER_ID + "MENUCLRF")).getColor();

		ColorConstants.EXE_BUTTON_BACK_COLOR = ((MClrCtlVo) mapColor.get(ApplicationConstants.LOGIN_USER_ID + "MENUSCLRB")).getColor();
		ColorConstants.EXE_BUTTON_FORE_COLOR = ((MClrCtlVo) mapColor.get(ApplicationConstants.LOGIN_USER_ID + "MENUSCLRF")).getColor();
		
		ColorConstants.BORDER_BOUND_TABLE_PANEL_GAIN_FOCUS = ((MClrCtlVo) mapColor.get(ApplicationConstants.LOGIN_USER_ID + "GRPBXCLRF")).getColor();
		
		    
	}		
	
	
}
