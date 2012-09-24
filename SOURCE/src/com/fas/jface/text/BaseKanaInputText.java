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
public class BaseKanaInputText extends AbstractKanaInputText {

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
    public BaseKanaInputText(){
        super();
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
    public BaseKanaInputText(String name) {
        this(name,20);
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
    public BaseKanaInputText(String name,int columns){
        this(name,columns,0);
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
    public BaseKanaInputText(String name,int columns,int maxlength) {
        super(name, columns, ImeText.IM_NONE, maxlength);
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
    public BaseKanaInputText(String name, int columns, int ime,int maxlength) {
        super(name, columns, ime, maxlength);
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

