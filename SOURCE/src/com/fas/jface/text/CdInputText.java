/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：CdInputText.java
*
*     記述				：
*     
*     作成日			：2010/02/03   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/
package com.fas.jface.text;

import java.awt.event.FocusEvent;

import javax.swing.SwingUtilities;

import com.fas.common.utils.StringUtils;
import com.fas.jface.button.ActionButton;


/**
 * @author PC13
 *
 */
public class CdInputText extends BaseInputText {

	/** */
	private static final long serialVersionUID = 1L;
	/** */
	private ActionButton btnSearch = null;
	/** */
	private boolean numberMode = false;
	
	/**
 	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public CdInputText() {
		super();
		setIMType(IM_OFF);
	}

	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param name
	 * @param columns
	 * @param ime
	 * @param maxlength
	 */
	public CdInputText(String name, int columns, int ime,int maxlength) {
		super(name, columns, ime, maxlength);
	}
	
	
	/**
	 * @param name
	 * @param columns
	 * @param maxlength
	 */
	public CdInputText(String name, int columns, int maxlength) {
		super(name, columns, maxlength);
		setIMType(IM_OFF);
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param btnBase
	 */
	public void setBtnSearch(ActionButton btnBase) {
		btnSearch = btnBase;
	}

    /* (non-Javadoc)
     * @see com.pipe.jface.text.BaseInputText#checkMode(java.lang.String)
     */
    protected  boolean checkMode(String charString){
        char[] cr=charString.toCharArray();
        char c=cr[0];
        if (numberMode)
        	return Character.isDigit(c);
        else 
        	return StringUtils.isCode("" + c);
    }
    
    /**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the numberMode
	 */
	public boolean isNumberMode() {
		return numberMode;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param numberMode the numberMode to set
	 */
	public void setNumberMode(boolean numberMode) {
		this.numberMode = numberMode;
	}

	/* (non-Javadoc)
     * @see com.pipe.jface.text.AbstractInputText#focusLost(java.awt.event.FocusEvent)
     */
    public void focusLost(FocusEvent e) {
    	super.focusLost(e);
//    	btnSearch.setSearchEnable(true);
    	if (btnSearch != null) {
    		SwingUtilities.invokeLater(new Runnable() {

				public void run() {
					btnSearch.setEnabled(false);
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
    		btnSearch.setEnabled(true);
    	}
    }	
}
