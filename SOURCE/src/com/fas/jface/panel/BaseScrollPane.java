/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：BaseScrollPane.java
*
*     記述				：
*     
*     作成日			：2009/10/13   
*
*     作成者			：PC12
*
*     備考				：
*
**************************************************************************************/
package com.fas.jface.panel;
import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;

import com.fas.common.constants.screen.ColorConstants;

public class BaseScrollPane extends JScrollPane {
    
	/**  */
    private static final long serialVersionUID =  1L;
    public BaseScrollPane() {
        super();
        init();
    }
    /**
     * @param view
     */
    public BaseScrollPane(Component view){
        super(view);
        init();
    }
	
    /**
	 * 
	 */
	private void init(){
	    setOpaque(true);
	    getViewport().setBackground(ColorConstants.DEFAULT_TEXT_BACKGROUND_COLOR);
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
	}
}
