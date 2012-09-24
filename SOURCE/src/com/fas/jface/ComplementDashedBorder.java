/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：ComplementDashedBorder.java
*
*     記述				：
*     
*     作成日			：2010/02/19   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/
package com.fas.jface;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.LineBorder;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicGraphicsUtils;

/**
 * @author PC13
 *
 */
public class ComplementDashedBorder extends LineBorder implements UIResource {
    
	/** */
	private static final long serialVersionUID = 1L;
	/** */
	private Color origColor;
    /** */
    private Color paintColor;

    /**
     * <DL>
     *   <DT>コンストラクター記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     */
    public ComplementDashedBorder() {
        super(null);
    }

    /* (non-Javadoc)
     * @see javax.swing.border.LineBorder#paintBorder(java.awt.Component, java.awt.Graphics, int, int, int, int)
     */
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Color color = c.getBackground();

        if (origColor != color) {
            origColor = color;
            paintColor = new Color(~origColor.getRGB());
        }

        g.setColor(paintColor);
        BasicGraphicsUtils.drawDashedRect(g, x + 1, y, width - 1, height);
    }
    
    /* (non-Javadoc)
     * @see javax.swing.border.LineBorder#getBorderInsets(java.awt.Component)
     */
    public Insets getBorderInsets(Component c)       {
        return new Insets(1, 2, 1, 1);
    }
}