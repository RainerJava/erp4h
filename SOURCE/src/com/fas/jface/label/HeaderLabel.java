package com.fas.jface.label;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import com.fas.common.constants.screen.ColorConstants;
import com.fas.common.constants.screen.FontConstants;

public class HeaderLabel extends JLabel {
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
    public HeaderLabel(){
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
    public HeaderLabel(String text){
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
        setLayout(null);
        setHorizontalAlignment(JLabel.CENTER);
        setFont(FontConstants.LABEL_TEXT_FONT);
		setBorder(BorderFactory.createEtchedBorder());
		setBackground(ColorConstants.TABLE_HEADER_BACKGROUND_COLOR);
		setForeground(ColorConstants.TABLE_HEADER_FORE_COLOR);
    }	
}
