package com.fas.sapp.business;

import java.awt.Dimension;
import java.awt.event.FocusEvent;

import javax.swing.SwingUtilities;

import com.fas.common.constants.dbtable.MCtlConstants;
import com.fas.common.constants.screen.FaceContants;
import com.fas.common.utils.BussinessUtils;
import com.fas.common.utils.StringUtils;
import com.fas.jface.button.ActionButton;
import com.fas.jface.label.BaseLabel;
import com.fas.jface.text.BaseInputText;
import com.fas.jface.text.ImeText;
import com.fas.sapp.base.SearchMasterFrame;

/**
 * @author PC14
 * 
 */
public class MEmpInputTextBox extends BaseInputText {

	/** */
	private BaseLabel lblEmpName = null;
	/** */
	private ActionButton btnSearch = null;
	/** */
	private SearchTypeObj searchType = null;
	/** */
	private static final long serialVersionUID = 1L;

	/**
	 * <DL>
	 * <DT>クラス記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * <DT>変更歴史：</DT>
	 * <DD>著作者: PC14</DD><BR>
	 * <DD></DD>
	 * </DL>
	 */
	public MEmpInputTextBox() {
		setIMType(ImeText.IM_OFF);
		int maxLength = Integer.parseInt(MCtlConstants.getValue("SYSTEMBM_EMP"));
		setMaxLength(maxLength);
	}

	/**
	 * <DL>
	 * <DT>クラス記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * <DT>変更歴史：</DT>
	 * <DD>著作者: PC14</DD><BR>
	 * <DD></DD>
	 * </DL>
	 */
	public MEmpInputTextBox(String name, int columns) {
		super(name, columns, 0);
	}

	/**
	 * <DL>
	 * <DT>クラス記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * <DT>変更歴史：</DT>
	 * <DD>著作者: PC14</DD><BR>
	 * <DD></DD>
	 * </DL>
	 */
	public void setLblEmpName(BaseLabel lblEmp) {
		lblEmpName = lblEmp;
		lblEmpName.setBorder(FaceContants.LABEL_BORDER);
		lblEmpName.setLocation(this.getLocation().x + this.getSize().width, this.getLocation().y);
		lblEmpName.setSize(new Dimension(300, FaceContants.LABLE_HEIGHT));
	}

	/**
	 * <DL>
	 * <DT>クラス記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * <DT>変更歴史：</DT>
	 * <DD>著作者: PC14</DD><BR>
	 * <DD></DD>
	 * </DL>
	 */
	public void setBtnSearch(ActionButton btnBase) {
		btnSearch = btnBase;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hanbai.jface.base.text.BaseInputText#checkMode(java.lang.String)
	 */
	protected boolean checkMode(String charString) {
		char[] cr = charString.toCharArray();
		char c = cr[0];
		return StringUtils.isCode("" + c);
	}

   /* (non-Javadoc)
     * @see com.hanbai.jface.base.text.AbstractInputText#focusLost(java.awt.event.FocusEvent)
     */
    public void focusLost(FocusEvent e) {
    	super.focusLost(e);
    	if (lblEmpName != null) {
    		lblEmpName.setText(BussinessUtils.getTantoushaName(this.getText()));
    	}
    	
    	if (btnSearch != null) {
    		SwingUtilities.invokeLater(new Runnable() {
				
				public void run() {
					if (btnSearch.isFocusOwner()) {
						btnSearch.setEnabled(false);
					}
				}
			});
    	}
    }
    
    /* (non-Javadoc)
     * @see com.pipe.jface.text.AbstractInputText#focusGained(java.awt.event.FocusEvent)
     */
    public void focusGained(FocusEvent e) {
    	super.focusGained(e);
    	if (btnSearch != null) {
    		SwingUtilities.invokeLater(new Runnable() {
				
				public void run() {
					if (!btnSearch.isFocusOwner()) {
						btnSearch.setEnabled(true);
					}
				}
			});
    		searchType.setSearchType(SearchMasterFrame.I_M_EMP_SEARCH);
    	}
    }

    /* (non-Javadoc)
     * @see com.pipe.jface.text.AbstractInputText#setText(java.lang.String)
     */
    public void setText(String str){
    	super.setText(str);
    	if (lblEmpName != null) {
    		lblEmpName.setText(BussinessUtils.getTantoushaName(this.getText()));
    	}
    }
    
	/**
	 * @return the searchType
	 */
	public SearchTypeObj getSearchType() {
		return searchType;
	}

	/**
	 * @param searchType the searchType to set
	 */
	public void setSearchType(SearchTypeObj searchType) {
		this.searchType = searchType;
	}
}
