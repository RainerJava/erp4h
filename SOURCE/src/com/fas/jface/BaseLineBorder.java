/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：BaseLineBorder.java
*
*     記述				：
*     
*     作成日			：2009/12/24   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/
package com.fas.jface;

import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;

import javax.swing.border.LineBorder;

/**
 * @author PC13
 *
 */
public class BaseLineBorder extends LineBorder {

	/** */
	private static final long serialVersionUID = 1L;

    public BaseLineBorder(Color color) {
        super(color, 1, false);
    }

    /**
     * <DL>
     *   <DT>コンストラクター記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param color
     * @param thickness
     */
    public BaseLineBorder(Color color, int thickness)  {
    	super(color, thickness, false);
    }

    public Insets getBorderInsets(Component c)       { 
        return new Insets(1, 5, 1, 1);
    }

    /* (non-Javadoc)
     * @see javax.swing.border.LineBorder#getBorderInsets(java.awt.Component, java.awt.Insets)
     */
    public Insets getBorderInsets(Component c, Insets insets) {
        insets.top = insets.right = insets.bottom = 1;
        insets.left = 5;
        return insets;
    }    
}
