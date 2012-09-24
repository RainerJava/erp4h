/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：DashedBorder.java
*
*     記述				：
*     
*     作成日			：2010/02/18   
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

import javax.swing.border.Border;

/**
 * @author PC13
 *
 */
public class DashedBorder implements Border {
	
    int THICKNESS = 1;
    Color color;
    int dashWidth;
    int dashHeight;
    
    /**
     * <DL>
     *   <DT>コンストラクター記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     */
    public DashedBorder () {
    	this (Color.black, 1, 1);
    }
    
    /**
     * <DL>
     *   <DT>コンストラクター記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param c
     * @param width
     * @param height
     */
    public DashedBorder (Color c, int width, int height) {
    	if (width < 1) {
    		throw new IllegalArgumentException("Invalid width: " + width);
    	}
    	if (height < 1) {
    		throw new IllegalArgumentException ("Invalid height: " + height);
    	}
    	color = c;
    	dashWidth = width;
    	dashHeight = height;
    }
    
    public void paintBorder (Component c, Graphics g, 
                             int x, int y, 
                             int width, int height) {
    	Insets insets = getBorderInsets(c);
    	g.setColor (color);
    	int numWide = (int)Math.round(width / dashWidth);
    	int numHigh = (int)Math.round(height / dashHeight);
    	int startPoint;
    	for (int i=0;i<=numWide;i+=2) {
    		startPoint = x + dashWidth * i;
    		g.fillRect (startPoint, y, dashWidth, THICKNESS);
    		g.fillRect (startPoint, y+height-insets.bottom, 
                    dashWidth, THICKNESS);
    	}
    	for (int i=0;i<=numHigh;i+=2) {
    		startPoint = x + dashHeight * i;
    		g.fillRect (x, startPoint, THICKNESS, dashHeight);
    		g.fillRect (x+width-insets.right, startPoint, 
                    THICKNESS, dashHeight);
    	}
    }

    /* (non-Javadoc)
     * @see javax.swing.border.Border#isBorderOpaque()
     */
    public boolean isBorderOpaque() {
    	return false;
    }
    
	/* (non-Javadoc)
	 * @see javax.swing.border.Border#getBorderInsets(java.awt.Component)
	 */
	public Insets getBorderInsets(Component c) {
		return new Insets(1, 2, 1, 1);
	}
}
