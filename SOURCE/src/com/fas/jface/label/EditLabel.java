package com.fas.jface.label;

import java.awt.Dimension;

import javax.swing.JLabel;

import com.fas.common.constants.screen.FaceContants;
import com.fas.common.constants.screen.FontConstants;
import com.fas.common.constants.screen.ColorConstants;

public class EditLabel extends JLabel {
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
    public EditLabel(){
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
    public EditLabel(String text){
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
		setBorder(FaceContants.LINE_BORDER);
		setBackground(ColorConstants.LABEL_EDIT_BACKGROUND_COLOR);
		setForeground(ColorConstants.LABEL_EDIT_FORE_COLOR);
    }	
}
