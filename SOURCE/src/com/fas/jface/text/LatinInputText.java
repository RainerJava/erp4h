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
public class LatinInputText extends BaseInputText {

	/** */
	private static final long serialVersionUID = 1L;
	/** */
	private ActionButton btnSearch = null;

	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public LatinInputText() {
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
	public LatinInputText(String name, int columns, int ime,int maxlength) {
		super(name, columns, ime, maxlength);
	}
	
	
	/**
	 * @param name
	 * @param columns
	 * @param maxlength
	 */
	public LatinInputText(String name, int columns, int maxlength) {
		super(name, columns, maxlength);
		setIMType(IM_OFF);
	}
	

    /* (non-Javadoc)
     * @see com.pipe.jface.text.BaseInputText#checkMode(java.lang.String)
     */
    protected  boolean checkMode(String charString){
        char[] cr=charString.toCharArray();
        char c=cr[0];
        return StringUtils.isAlphaBet("" + c);
    }
    
    public void focusLost(FocusEvent e) {
    	super.focusLost(e);
    	if (btnSearch != null) {
    		SwingUtilities.invokeLater(new Runnable() {

				public void run() {
					btnSearch.setEnabled(false);
				}
    		});
    	}
    }

    public void focusGained(FocusEvent e) {
    	super.focusGained(e);
    	if (btnSearch != null) {
    		btnSearch.setEnabled(true);
    	}
    }
    

	public ActionButton getBtnSearch() {
		return btnSearch;
	}

	public void setBtnSearch(ActionButton btnSearch) {
		this.btnSearch = btnSearch;
	}	
}
