/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：LincRadioButton.java
*
*     記述				：
*     
*     作成日			：2009/11/13
*
*     作成者			：PC12
*
*     備考				：
*
**************************************************************************************/

package com.fas.jface;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JRadioButton;

import com.fas.common.constants.screen.FontConstants;
import com.fas.common.constants.screen.ColorConstants;

/**
 * <DL>
 * <DT>クラス記述：</DT>
 * <DD></DD>
 * <BR>
 * <DT>変更歴史：</DT>
 * <DD>著作者: PC12</DD><BR>
 * <DD></DD>
 * </DL>
 */
public class InspectRadioButton extends JRadioButton{

	/**
     * <code>serialVersionUID</code> のコメント
     */
	private static final long serialVersionUID = 1L;
    
	public InspectRadioButton(){
        super();
        init();
    }
    
    public InspectRadioButton(String text){
        super(text);
        init();
    }
    
    public InspectRadioButton(String text,boolean selected){
        super(text,selected);
        init();
    }
    
    private void init() {
    	
        setFont(FontConstants.LABEL_TEXT_FONT);
        setBackground(ColorConstants.DEFAULT_RAJIO_BACKGROUND_COLOR);
        setOpaque(true);
        
		// Make the ENTER key act like the TAB key
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent evt) {
				int key = evt.getKeyCode();
				if (key == KeyEvent.VK_ENTER)
					transferFocus();
			}
		});
		
		//Set Focus color
		this.addFocusListener(new FocusListener(){
			public void focusGained(FocusEvent e) {
				setBackground(ColorConstants.DEFAULT_RAJIO_FOCUS_BACKGROUND_COLOR);
			}
			
		    public void focusLost(FocusEvent e) {
		        setBackground(ColorConstants.DEFAULT_RAJIO_BACKGROUND_COLOR);
		    }
		});
    }
}
