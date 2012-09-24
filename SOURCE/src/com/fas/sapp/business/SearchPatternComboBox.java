/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：SearchPatternComboBox.java
*
*     記述				：
*     
*     作成日			：2010/03/03  
*
*     作成者			：PC14
*
*     備考				：
*
**************************************************************************************/
package com.fas.sapp.business;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.border.Border;

import com.fas.common.utils.StringUtils;
import com.fas.jface.checkbox.BaseCheckBox;
import com.fas.jface.combo.BaseComboBox;
import com.fas.common.constants.screen.ColorConstants;
import com.fas.common.constants.screen.FaceContants;
import com.fas.service.common.checkbox.CheckBoxService;
import com.fas.service.common.checkbox.CheckBoxServiceImpl;
import com.fas.vo.base.CheckBoxVo;

/**
 * @author PC14
 *
 */
public class SearchPatternComboBox extends BaseComboBox {

	/** */
	private static final long serialVersionUID = 1L;
	/** */
	private BaseCheckBox arrChkbox[] ;
	/** */
	private Border DEFAULT_BORDER;
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public SearchPatternComboBox(ComboBoxModel aModel) {
	    super(aModel);
	    init();
	}
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param items
	 */
	public SearchPatternComboBox(final Object[] items) {
	    super(items);
	    init();
	}
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param items
	 */
	public SearchPatternComboBox(Vector items) {
	    super(items);
	    init();
	}
	
	private void init() {
		
		DEFAULT_BORDER = getBorder();
		
		addFocusListener(new FocusListener() {

			/* (non-Javadoc)
			 * @see java.awt.event.FocusListener#focusGained(java.awt.event.FocusEvent)
			 */
			public void focusGained(FocusEvent e) {
				if (isEnabled()) {
					setBackground(ColorConstants.DEFAULT_COMBOBOX_FOCUS_BACKGROUND_COLOR);
				} else {
					setBackground(ColorConstants.DEFAULT_COMBOBOX_DISABLE_BACKGROUND_COLOR);
				}
				setBorder(FaceContants.DOT_LINE_BORDER);
			}

			/* (non-Javadoc)
			 * @see java.awt.event.FocusListener#focusLost(java.awt.event.FocusEvent)
			 */
			public void focusLost(FocusEvent e) {
				if (isEnabled()) {
					setBackground(ColorConstants.DEFAULT_COMBOBOX_BACKGROUND_COLOR);
				} else {
					setBackground(ColorConstants.DEFAULT_COMBOBOX_DISABLE_BACKGROUND_COLOR);
				}
				setBorder(DEFAULT_BORDER);
			}
			
		});
	}

    /* (non-Javadoc)
     * @see javax.swing.JComboBox#setSelectedIndex(int)
     */
    public void setSelectedIndex(int anIndex) {
        super.setSelectedIndex(anIndex);
        List<CheckBoxVo> lstCheckVo = new ArrayList<CheckBoxVo>();
		CheckBoxService checkSerVice = new CheckBoxServiceImpl();
		String codeName = "";
		
		String stCodeName = StringUtils.emptyIfNull((String) getSelectedItem());
		String[] arrTemp = stCodeName.split(" ");
		stCodeName = arrTemp[0];
		
		try {
			lstCheckVo = checkSerVice.getLstINSP(stCodeName);
		} catch (Exception e) {
			
		}
		// uncheck
		for(int i = 0; i < arrChkbox.length; i++){
				arrChkbox[i].setSelected(false);
		}
		// setcheck
    	if (arrChkbox != null) {
    		for(int j = 0; j < lstCheckVo.size(); j++){
    			codeName = lstCheckVo.get(j).getCode() +": " + lstCheckVo.get(j).getValue1();
    			for(int i = 0; i < arrChkbox.length; i++){
	    			if(arrChkbox[i].getText().equalsIgnoreCase(codeName)){
	    				arrChkbox[i].setSelected(true);
	    			}
	    		}
    		}
    	}
    }
    
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the arrChkbox
	 */
	public BaseCheckBox[] getArrChkbox() {
		return arrChkbox;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param the arrChk[]
	 */
	public void setArrChkbox(BaseCheckBox arrChk[]) {
		this.arrChkbox = arrChk;
	}
}