/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：BaseInputText.java
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

package com.fas.jface.text;

import java.awt.Insets;

import com.fas.common.constants.screen.FaceContants;

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
public class BaseInputText extends AbstractInputText {

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
    public BaseInputText(){
        super();
        setBorder(FaceContants.LINE_BORDER_GRAY);
    }
    
    /**
     * <DL>
     *   <DT>コンストラクター記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param name
     */
    public BaseInputText(String name) {
        this(name,20);
        setBorder(FaceContants.LINE_BORDER_GRAY);
    }
    
    /**
     * <DL>
     *   <DT>コンストラクター記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param name
     * @param columns
     */
    public BaseInputText(String name,int columns){
        this(name,columns,0);
        setBorder(FaceContants.LINE_BORDER_GRAY);
    }
    
    /**
     * <DL>
     *   <DT>コンストラクター記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param name
     * @param columns
     * @param maxlength
     */
    public BaseInputText(String name,int columns,int maxlength) {
        super(name, columns, ImeText.IM_NONE, maxlength);
        setBorder(FaceContants.LINE_BORDER_GRAY);
    }
     
    /**
     * <DL>
     *   <DT>コンストラクター記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param name
     * @param columns
     * @param ime
     * @param maxlength
     */
    public BaseInputText(String name, int columns, int ime,int maxlength) {
        super(name, columns, ime, maxlength);
        setBorder(FaceContants.LINE_BORDER_GRAY);
    }

    /* (non-Javadoc)
     * @see com.hanbai.jface.base.text.AbstractInputText#checkMode(java.lang.String)
     */
    protected  boolean checkMode(String charString){
    	return true;
    }
    
    private Insets inset = new Insets(1, 4, 0, 2);

    /* (non-Javadoc)
     * @see javax.swing.JComponent#getInsets()
     */
    public Insets getInsets()
    {
        return inset;
    }
}

