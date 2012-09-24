/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：BaseLabel.java
*
*     記述				：
*     
*     作成日			：2009/10/05   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/

package com.fas.jface.label;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphMetrics;
import java.awt.font.GlyphVector;
import java.awt.geom.Point2D;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import com.fas.common.constants.screen.ColorConstants;
import com.fas.common.constants.screen.FontConstants;

/**
 * <DL>
 *   <DT>クラス記述：</DT>
 * 	<DD></DD>
 * <BR>
 *   <DT>変更歴史：</DT>
 * 	<DD>著作者: PC13</DD><BR>
 * 	<DD></DD>
 * </DL>
 */
public class BaseLabel extends JLabel {

	/** */
	private static final long serialVersionUID = 1L;
    /** */
    public static final Border LINE = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
    /** */
    public static final Border RAISE = BorderFactory.createRaisedBevelBorder();
    /** */
    public static final Border LOWER = BorderFactory.createLoweredBevelBorder();
    /** */
    private Border border = LINE;
    /** */
    private GlyphVector gvtext;
    
    /* (non-Javadoc)
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    protected void paintComponent(Graphics g) {
        if(getHorizontalAlignment()==SwingConstants.LEFT){
	        Graphics2D g2 = (Graphics2D)g;
	        int WRAPPING_WIDTH = getWidth()-getInsets().left-getInsets().right;
	        FontRenderContext frc = g2.getFontRenderContext();
	        gvtext = getWrappedGlyphVector(getText(), WRAPPING_WIDTH, getFont(), frc);
	        g2.setPaint(getBackground());
	        g2.fillRect(0, 0, getWidth(), getHeight());
	        g2.setPaint(getForeground());
	        g2.drawGlyphVector(gvtext, getInsets().left, getFont().getSize()+getInsets().top);
        } else {
            super.paintComponent(g);
        }
      }
    
      /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param str
     * @param wrap
     * @param font
     * @param frc
     * @return
     */
    private GlyphVector getWrappedGlyphVector(String str, float wrap,
                                    Font font, FontRenderContext frc) {
        Point2D gmPos    = new Point2D.Double(0.0d, 0.0d);
        GlyphVector gv   = font.createGlyphVector(frc, str);
        float lineheight = (float) (gv.getLogicalBounds().getHeight());
        float xpos       = 0.0f;
        float advance    = 0.0f;
        int   lineCount  = 0;
        GlyphMetrics gm;
        for(int i=0;i<gv.getNumGlyphs();i++) {
          gm = gv.getGlyphMetrics(i);
          advance = gm.getAdvance();
          if(xpos<wrap && wrap<=xpos+advance) {
            lineCount++;
            xpos = 0.0f;
          }
          gmPos.setLocation(xpos, lineheight*lineCount);
          gv.setGlyphPosition(i, gmPos);
          xpos = xpos + advance;
        }
        return gv;
      }
      
    /**
     * <DL>
     *   <DT>コンストラクター記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     */
    public BaseLabel(){
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
    public BaseLabel(String text){
        super(text);
        init();
    }
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param width
     */
    public void setWidth(int width){
        Dimension d=getPreferredSize();
        d.setSize(width,d.getHeight());
        setPreferredSize(d);
    }
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param height
     */
    public void setHeight(int height){
        Dimension d=getPreferredSize();
        d.setSize(d.getWidth(),height);
        setPreferredSize(d);
    }
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     */
    private void init(){
        setOpaque(true);
        setBorder(border);
        setFont(FontConstants.LABEL_TEXT_FONT);
        setForeground(ColorConstants.DEFAULT_LABEL_FORE_COLOR);
        setBackground(ColorConstants.DEFAULT_LABEL_BACKGROUND_COLOR);
        setLayout(null);
    }	
}

