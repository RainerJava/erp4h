/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：ImeText.java
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

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.InputMethodEvent;
import java.awt.font.TextHitInfo;
import java.awt.im.InputContext;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.StyleConstants;

import com.fas.common.constants.screen.ColorConstants;
import com.fas.jface.utils.RequiredImpl;
import com.fas.jface.validate.Validation;

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

public class ImeText extends JTextField implements Validation {

	/** */
	private static final long serialVersionUID = 1L;

	private boolean requiredCheck = false;
	
	private RequiredImpl required = new RequiredImpl();
	
	public ImeText(){
        this("",20);
	    setPreferredSize(new Dimension(100,20));
	}
	
    public ImeText(String name,int maxlength){
		super(maxlength);
		setName(name);
        init();
	}
    
    public ImeText(String name){
        this(name,20);
    }
	
    public void setComponent(Component componet) {
    }
    
	private String message="";
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message){
        this.message=message;
    }
    
    public Component getComponent() {
        return this;
    }
	public boolean check(){
		String text=getText();
		boolean check=true;
		if(requiredCheck==true){
		    check =required.execute(text,null);
		    if(check==false){
			    message=required.getMessage();
				return false;
			}
		}
		return true;
	}
	
    /** 変換モード指定定数：指定なし */
    public static final int IM_NONE        = 0;
    /** 変換モード指定定数：ひらがな */
    public static final int IM_HIRAGANA    = 1;
    /** 変換モード指定定数：半角カナ */
    public static final int IM_HALFKANA    = 2;
    /** 変換モード指定定数：全角英数 */
    public static final int IM_FULLASCII   = 3;
    /** 変換モード指定定数：直接入力 */
    public static final int IM_OFF         = 4;

    private int imType    = IM_NONE;

    /**
     * <DL>
     *   <DT>コンストラクター記述：</DT>
     * 		<DD>列数、文字列長、変換モードを指定してフィールドを作成します。</DD>
     * <BR>
     *
     * </DL>
     * @param columns
     * @param type
     */
    public ImeText(int columns,int type){
        super(columns);
        setIMType(type);
        init();
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
        addFocusListener(new InputContorollFieldListener());    	
    }

    /* (non-Javadoc)
     * @see javax.swing.text.JTextComponent#setText(java.lang.String)
     */
    public void setText(String str) {
        Document doc = getDocument();
        InputControllDocument hogeDoc = null;
        if(doc instanceof InputControllDocument) {
            hogeDoc = (InputControllDocument)doc;
        }
        if (hogeDoc != null) {
            //ドキュメントクラスのsetText()で文字列を挿入
            hogeDoc.setText(str);
            moveCaretPosition(0);
        }
    }


    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD>変換モードを設定します。</DD>
     * <BR>
     *
     * </DL>
     * @param imType
     */
    public void setIMType(int imType) {
        this.imType = imType;
    }

    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD>変換モードを取得します。</DD>
     * <BR>
     *
     * </DL>
     * @return
     */
    public int getIMType() {
        return imType;
    }

    /* (non-Javadoc)
     * @see javax.swing.JTextField#createDefaultModel()
     */
    protected Document createDefaultModel() {
        return new InputControllDocument(this);
    }

    /**
     * <DL>
     *   <DT>クラス記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * @author PC13
     *
     */
    class InputControllDocument extends DefaultStyledDocument {

        /** */
		private static final long serialVersionUID = 1L;
		/** */
		ImeText parent = null;

        /**
         * <DL>
         *   <DT>コンストラクター記述：</DT>
         * 		<DD></DD>
         * <BR>
         *
         * </DL>
         * @param initParent
         */
        public InputControllDocument(ImeText initParent){
            super();
            parent = initParent;
        }

        /**
         * <DL>
         *   <DT>メソッド記述：</DT>
         * 		<DD>ドキュメントの文字列を設定します。</DD>
         * <BR>
         *
         * </DL>
         * @param str
         */
        protected void setText(String str) {
            try {
                super.remove(0, getLength());
                super.insertString(0, str, null);
            } catch (BadLocationException e) {
                //ignore
            }
        }

        /* (non-Javadoc)
         * @see javax.swing.text.AbstractDocument#remove(int, int)
         */
        public void remove(int offs , int length)
            throws BadLocationException{
            if(!hasFocus()){
                return;
            }
            super.remove(offs, length);
        }


        /**
         * <DL>
         *   <DT>メソッド記述：</DT>
         * 		<DD></DD>
         * <BR>
         *
         * </DL>
         * @param offs
         * @param length
         * @throws BadLocationException
         */
        public void superRemove(int offs , int length)
            throws BadLocationException {
            super.remove(offs, length);
        }

        /* (non-Javadoc)
         * @see javax.swing.text.AbstractDocument#insertString(int, java.lang.String, javax.swing.text.AttributeSet)
         */
        public void insertString(int offs, String str, AttributeSet a)
            throws BadLocationException {

            if(!hasFocus()){
                return;
            }

            if (str == null) return;

            if ((a != null) &&
                (a.isDefined(StyleConstants.ComposedTextAttribute))) {
                super.insertString(offs, str, a);
                return;
            }
            int checkOffs=offs;

            for (int i = 0; i < str.length(); i++){
                checkOffs = offs + i;
                char checkChar = str.charAt(i);
                
                super.insertString(i, String.valueOf(checkChar), a);
            }
        }
    }


    /**
     * <DL>
     *   <DT>クラス記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * @author PC13
     *
     */
    class InputContorollFieldListener implements FocusListener{

        /* (non-Javadoc)
         * @see java.awt.event.FocusListener#focusLost(java.awt.event.FocusEvent)
         */
        public void focusLost(FocusEvent event){
            focusLostHandler();
        }


        /* (non-Javadoc)
         * @see java.awt.event.FocusListener#focusGained(java.awt.event.FocusEvent)
         */
        public void focusGained(FocusEvent event){
            focusGainedHandler();
        }
    }

    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD>フォクス時の処理です。</DD>
     * <BR>
     *
     * </DL>
     */
    private void focusGainedHandler(){
        if (imType != IM_NONE) {
            Character.Subset[] subsets = null;
            switch (imType) {
                case IM_HIRAGANA :
                    subsets = new Character.Subset[] {java.awt.im.InputSubset.KANJI};
                    break;
                case IM_HALFKANA :
                    subsets = new Character.Subset[] {java.awt.im.InputSubset.HALFWIDTH_KATAKANA};
                    break;
                case IM_FULLASCII :
                    subsets = new Character.Subset[] {java.awt.im.InputSubset.FULLWIDTH_LATIN};
                    break;
                default :
                    subsets = null;
            }
            InputContext iContext = getInputContext();
            if (iContext != null) {
            	iContext.setCharacterSubsets(subsets);
            }
        }
        //文字列を全選択状態にする。
        if(imType==IM_HIRAGANA){
	        select(0, getText().length());
        }
    }


    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD>フォクス消失時の処理です。</DD>
     * <BR>
     *
     * </DL>
     */
    private void focusLostHandler() {
        if (imType != IM_NONE) {
        	if(getInputContext()!=null){
	            getInputContext().setCharacterSubsets(null);
        	}
        }
        removeUndecidedCharacters();
    }
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD>IMEの未確定文字列を削除します。</DD>
     * <BR>
     *
     * </DL>
     */
    public void removeUndecidedCharacters(){
        InputControllDocument document = null;
        if(getDocument() instanceof InputControllDocument){
            document = (InputControllDocument)getDocument();
        }
        if(document == null){
            return;
        }
        int begin = 0; 
        int len   = 0; 

        for(int i = 0; i < document.getLength(); i++){
            Element e = document.getCharacterElement(i);
            if(e.getAttributes().isDefined(StyleConstants.ComposedTextAttribute)){
                
            	if(len == 0){
                    begin = i;
                }

                len++;
            }
        }
        if(len != 0){
            try {
                document.superRemove(begin, len);

                if(imType == IM_NONE){
                    return;
                }
                InputMethodEvent event = 
                    new InputMethodEvent(this,
                                         InputMethodEvent.INPUT_METHOD_TEXT_CHANGED,
                                         null,
                                         0,
                                         TextHitInfo.beforeOffset(0),
                                         null);
                processInputMethodEvent(event);
            }catch (BadLocationException ignore){
            }
        }
    }
    
    /* (non-Javadoc)
     * @see javax.swing.text.JTextComponent#setEditable(boolean)
     */
    public void setEditable(boolean b) {
        super.setEditable(b);
        if(b==true){
            setBackground(ColorConstants.DEFAULT_TEXT_BACKGROUND_COLOR);
        }else{
            setBackground(ColorConstants.DEFAULT_TEXT_DISABLE_BACKGROUND_COLOR);            
        }
    }

    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @return
     */
    public boolean isRequiredCheck() {
        return requiredCheck;
    }
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param requiredCheck
     */
    public void setRequiredCheck(boolean requiredCheck) {
        this.requiredCheck = requiredCheck;
    }

}

