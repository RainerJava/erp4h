/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：BaseTextArea.java
*
*     記述				：
*     
*     作成日			：2010/03/08   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/
package com.fas.jface.textarea;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JTextArea;

import com.fas.common.constants.screen.ColorConstants;

/**
 * @author PC13
 *
 */
public class BaseTextArea extends JTextArea {

	/** */
	private static final long serialVersionUID = 1L;

	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public BaseTextArea() {
		super();
		init();
	}

    /**
     * <DL>
     *   <DT>コンストラクター記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param text
     */
    public BaseTextArea(String text) {
        super(null, text, 0, 0);
        init();
    }

    /**
     * <DL>
     *   <DT>コンストラクター記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param rows
     * @param columns
     */
    public BaseTextArea(int rows, int columns) {
    	super(null, null, rows, columns);
    	init();
    }


    /**
     * <DL>
     *   <DT>コンストラクター記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param text
     * @param rows
     * @param columns
     */
    public BaseTextArea(String text, int rows, int columns) {
    	super(null, text, rows, columns);
    	init();
    }	

    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     */
    private void init() {
    	
    	FocusAdapter areaFocus = new FocusAdapter() {
    	
    	    /* (non-Javadoc)
    	     * @see java.awt.event.FocusAdapter#focusLost(java.awt.event.FocusEvent)
    	     */
    	    public void focusLost(FocusEvent e) {
    	    	if (isEditable() == true)
    	    		setBackground(ColorConstants.DEFAULT_TEXT_BACKGROUND_COLOR);
    	    	else
    	    		setBackground(ColorConstants.DEFAULT_TEXT_DISABLE_BACKGROUND_COLOR);

    	    }
    	    
    	   
    	    /* (non-Javadoc)
    	     * @see java.awt.event.FocusAdapter#focusGained(java.awt.event.FocusEvent)
    	     */
    	    public void focusGained(FocusEvent e) {
    	    	if (isEditable() == true) {
    	    		setBackground(ColorConstants.DEFAULT_TEXT_FOCUS_BACKGROUND_COLOR);
    	    		selectAll();
    	    	} else {
    	    		setBackground(ColorConstants.DEFAULT_TEXT_DISABLE_BACKGROUND_COLOR);
    	    	}
    	    }
    	};
    	
    	addFocusListener(areaFocus);
    }
    

}
