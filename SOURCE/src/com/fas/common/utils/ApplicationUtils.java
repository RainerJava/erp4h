/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：ApplicationUtils.java
*
*     記述				：
*     
*     作成日			：2009/12/17   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/
package com.fas.common.utils;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.event.TableModelEvent;

import com.fas.common.constants.ApplicationConstants;
import com.fas.common.constants.LogContants;
import com.fas.common.exception.BizException;
import com.fas.jface.combo.ArrayListComboBoxModel;
import com.fas.jface.table.BTableModelData;
import com.fas.service.common.combo.ComboService;
import com.fas.service.common.combo.ComboServiceImpl;
import com.fas.service.system.flog.FLogService;
import com.fas.service.system.flog.FLogServiceImpl;
import com.fas.vo.base.ComboObjectVo;
import com.fas.vo.flog.FLogVo;

/**
 * @author PC13
 *
 */
public class ApplicationUtils {

	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public ApplicationUtils() {
	}

    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param uerId
     * @param menuGrp
     * @param menuId
     * @param actionId
     * @param content
     */
    public static void logData(String uerId, String menuGrp, String menuId, String actionId, String content) {
    		
    	FLogService logService = new FLogServiceImpl();
    	
    	try {
    		
    		FLogVo logVo = new FLogVo();
    		
    		logVo.setUserUser(uerId);
    		logVo.setActionType(actionId);
    		logVo.setMenugrpCode(menuGrp);
    		logVo.setMenuexeCode(menuId);
    		
    		int iTime = DateUtils.getITime();
    		
    		if (LogContants.PLUS_TIME == iTime) {
    			++iTime;
    			LogContants.PLUS_TIME = iTime; 
    		}
    		
			logVo.setActTime(iTime);
			System.out.println("Log Time " + iTime);
			
    		logVo.setActDate(DateUtils.getCurrentDate());
    		logVo.setText(content);
    		
    		if (ApplicationConstants.loginUser != null) {
    			logVo.setPcid(ApplicationConstants.loginUser.getLoginPC());
    		}
    		
    		logService.create(logVo);
    		
    	} catch (BizException e) {
    		e.printStackTrace();
    	}
    }
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param logVo
     */
    public static void logData(FLogVo logVo) {
		
    	FLogService logService = new FLogServiceImpl();

    	if (logVo != null) {
    		
    		int iTime = DateUtils.getITime();
    		if (LogContants.PLUS_TIME == iTime) {
    			++iTime;
    			LogContants.PLUS_TIME = iTime; 
    		}
			logVo.setActTime(iTime);
			System.out.println("Log Time " + iTime);

    	}

    	try {

    		logService.create(logVo);
    		
    	} catch (BizException e) {
    		e.printStackTrace();
    	}
    }
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param m_table
     * @param m_data
     */
    public static void doRefreshTable(JTable m_table, BTableModelData m_data) {
    	int iCurRow = m_table.getSelectedRow();
		m_data.retrieveData();
        m_table.tableChanged(new TableModelEvent(m_data)); 
        m_data.fireTableDataChanged();
        m_table.repaint();
        if (iCurRow < 0 || iCurRow >= m_table.getRowCount()) {
        	iCurRow = 0;
        }
        
        if (m_table.getRowCount() > 0) {
        	m_table.setRowSelectionInterval(iCurRow, iCurRow);
        }
    }
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param m_table
     * @param m_data
     */
    public static void doRefreshTableWithoutDatabase(JTable m_table, BTableModelData m_data) {
    	int iCurRow = m_table.getSelectedRow();
        m_table.tableChanged(new TableModelEvent(m_data)); 
        m_data.fireTableDataChanged();
        m_table.repaint();
        if (iCurRow < 0 || iCurRow >= m_table.getRowCount()) {
        	iCurRow = 0;
        }
        
        if (m_table.getRowCount() > 0) {
        	m_table.setRowSelectionInterval(iCurRow, iCurRow);
        }
    }
    
    /**
     * @param nameClsCd
     * @param width1
     * @param width2
     * @param showType
     * @return
     */
    public static ArrayListComboBoxModel createComboDataModel(String nameClsCd, int width1, int width2, int showType, boolean includeBlank) {
		
    	List<ComboObjectVo> lstData = null;
		ComboService combService = new ComboServiceImpl();
		
		try {
			lstData = combService.getLstFromName(nameClsCd,includeBlank);
		} catch (Exception e) {
			lstData = new ArrayList<ComboObjectVo>();
		}
		ArrayListComboBoxModel aModel = new ArrayListComboBoxModel(lstData);
		
		aModel.setIShowType(showType);
		aModel.LEVEL1_LENGTH = width1;
		aModel.LEVEL2_LENGTH = width2;
		
		return aModel;
    }

    /**
     * @param lstData
     * @param width1
     * @param width2
     * @param showType
     * @return
     */
    public static ArrayListComboBoxModel createComboDataModel(List<ComboObjectVo> lstData, int width1, int width2, int showType) {
		
		ArrayListComboBoxModel aModel = new ArrayListComboBoxModel(lstData);
		
		aModel.setIShowType(showType);
		aModel.LEVEL1_LENGTH = width1;
		aModel.LEVEL2_LENGTH = width2;
		
		return aModel;
    }

//	public static GyoNoComboModel createGyoNoComboModel(List<PipeSVo> listData) {
//		GyoNoComboModel aModel = new GyoNoComboModel(listData);
//		return aModel;
//	}
    
    /**
     * @param nameClsCd
     * @param width1
     * @param width2
     * @param showType
     * @param level
     * @return
     */
    public static ArrayListComboBoxModel createComboDataModel(String nameClsCd, int width1, int width2, int showType, int level) {
		
    	List<ComboObjectVo> lstData = null;
		ComboService combService = new ComboServiceImpl();
		
		try {
			lstData = combService.getLstFromName(nameClsCd, false);
		} catch (Exception e) {
			lstData = new ArrayList<ComboObjectVo>();
		}
		ArrayListComboBoxModel aModel = new ArrayListComboBoxModel(lstData);
		
		aModel.setIShowType(showType);
		aModel.LEVEL1_LENGTH = width1;
		aModel.LEVEL2_LENGTH = width2;
		aModel.setiLevel(level);
		
		return aModel;
    }

    /**
     * @param lstData
     * @param width1
     * @param width2
     * @param showType
     * @param level
     * @return
     */
    public static ArrayListComboBoxModel createComboDataModel(List<ComboObjectVo> lstData, int width1, int width2, int showType, int level) {
		
		ArrayListComboBoxModel aModel = new ArrayListComboBoxModel(lstData);
		
		aModel.setIShowType(showType);
		aModel.LEVEL1_LENGTH = width1;
		aModel.LEVEL2_LENGTH = width2;
		aModel.setiLevel(level);
		
		return aModel;
    }    

    public static boolean isPipe(String strLclsCode)
	{
		if( NumberUtils.getIntFromString( StringUtils.trimAllVN( strLclsCode ) ) == 0)
			return true;
		return false;
	}

}
