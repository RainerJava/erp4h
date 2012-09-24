/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：RequiredLabel.java
*
*     記述				：
*     
*     作成日			：2010/01/28   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/
package com.fas.jface.label;

import java.awt.Dimension;

import javax.swing.JLabel;

import com.fas.common.constants.screen.FontConstants;
import com.fas.common.constants.screen.ColorConstants;

/**
 * @author PC13
 *
 */
public class RequiredLabel extends JLabel {

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
    public RequiredLabel(){
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
    public RequiredLabel(String text){
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
        setBorder(null);
        setLayout(null);
        setFont(FontConstants.LABEL_TEXT_FONT);
        setForeground(ColorConstants.LABEL_REQUIRED_FOR_COLOR);
        setBackground(ColorConstants.PANEL_DEFAULT_COLOR);
    }	
}
