/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：AbstractInputText.java
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

import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.font.TextHitInfo;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.StyleConstants;

import com.fas.common.constants.screen.ColorConstants;
import com.fas.common.constants.screen.FontConstants;
import com.fas.common.utils.StringUtils;

/**
 * <DL>
 *   <DT>クラス記述：</DT>
 * 	<DD></DD>
 * <BR>
 *   <DT>変更歴史：</DT>
 * 	<DD>著作者: PC12</DD><BR>
 * 	<DD></DD>
 * </DL>
 */

public abstract class AbstractInputText extends ImeText implements FocusListener {

    /** */
	private static final long serialVersionUID = 1L;
	/** */
    private int maxLength = 0;
    /** */
    private AttributeSet imeState = null;    
    /** */
    private boolean upperCaseLetters = false;

    public boolean isUpperCaseLetters() {
		return upperCaseLetters;
	}

	public void setUpperCaseLetters(boolean upperCaseLetters) {
		this.upperCaseLetters = upperCaseLetters;
	}

	/**
     * <DL>
     *   <DT>コンストラクター記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     */
    public AbstractInputText(){
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
     * @param name
     */
    public AbstractInputText(String name){
        this(name,20,0,0);
        init();
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
    public AbstractInputText(String name,int columns){
        this(name,columns,0,0);
        init();
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
     * @param mode
     * @param maxlength
     */
    public AbstractInputText(String name,int columns,int mode,int maxlength){
        super(columns, mode);
        this.maxLength=maxlength;
        setName(name);
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
        setPreferredSize(new Dimension(200,20));
        addFocusListener(this);
		setFont(FontConstants.TEXT_FIELD_FONT);
        //Make the ENTER key act like the TAB key
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
              int key = evt.getKeyCode();
              if (key == KeyEvent.VK_ENTER)
                transferFocus();
            }
          });
    }
    
    /* (non-Javadoc)
     * @see javax.swing.text.JTextComponent#paste()
     */
    public void paste(){
    	// ペースト処理時に、"\n"コードを排除する
    	// ペースト処理実施
    	super.paste();

    	try{
        	// ペーストされた文字列のチェック
        	Document doc = getDocument();
    		String test = doc.getText(0, doc.getLength());
        	if(test.indexOf("\n") != -1){
        		test = StringUtils.replaceAll(test, "\n", "");
        		setText(test);
        	}
    	} catch (BadLocationException e){
    	} catch (Exception e) {
		}
    }
    
    /* (non-Javadoc)
     * @see com.hanbai.jface.base.text.ImeText#setText(java.lang.String)
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
            if(str != null) {
            	moveCaretPosition(str.length());
            } else{
            	moveCaretPosition(0);           	
            }
            	
        }
    }

    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD>入力制限文字列長を設定します。</DD>
     * <BR>
     *
     * </DL>
     * @param maxLength
     */
    public void setMaxLength(int maxLength) {
        if (maxLength < 0) {
            this.maxLength = 0;
            return;
        }
        this.maxLength = maxLength;
    }

    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD>入力制限文字列長を取得します。</DD>
     * <BR>
     *
     * </DL>
     * @return
     */
    public int getMaxLength() {
        return maxLength;
    }

    /* (non-Javadoc)
     * @see com.hanbai.jface.base.text.ImeText#createDefaultModel()
     */
    protected Document createDefaultModel() {
        return new InputControllDocument(this);
    }

    /**
     * <DL>
     *   <DT>クラス記述：</DT>
     * 		<DD>HugeFieldドキュメントクラスです。</DD>
     * <BR>
     *
     * @author PC13
     *
     */
    class InputControllDocument extends DefaultStyledDocument {

	    /** */
		private static final long serialVersionUID = 1L;
		/** */
	    AbstractInputText parent = null;
	
	    /**
	     * <DL>
	     *   <DT>コンストラクター記述：</DT>
	     * 		<DD></DD>
	     * <BR>
	     *
	     * </DL>
	     * @param initParent
	     */
	    public InputControllDocument(AbstractInputText initParent){
	        super();
	        parent = initParent;
	    }

        /**
         * <DL>
         *   <DT>メソッド記述：</DT>
         * 		<DD></DD>
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

        	imeState=a;
            if(!hasFocus()){
                return;
            }     
            

            if (str == null) return;
            
            
            // chuyen sang chu hoa trong truong hop ky tu nhap vao la chu la tinh
            if(upperCaseLetters){
            	  char[] cr=str.toCharArray();
                  char c=cr[0];
                  if(StringUtils.isAlphaBet("" + c)){
                	  str = str.toUpperCase();                	  
                  }
            }
            

            if ((a != null) &&
                (a.isDefined(StyleConstants.ComposedTextAttribute))) {
                super.insertString(offs, str, a);
                return;
            }
            
            for (int i = 0; i < str.length(); i++){
                int  checkOffs = offs +i;
                char checkChar = str.charAt(i);
                
                if (maxLength != 0){
                        int fieldLength = StringUtils.getBytes(parent.getText()).length;
                        int insertStrLength = StringUtils.getBytes(String.valueOf(checkChar)).length;
                        if(fieldLength + insertStrLength > maxLength) {
                            return;
                        }
                }
                
                if(checkMode(Character.toString(checkChar))) {
                    super.insertString(checkOffs, String.valueOf(checkChar), a);
                    checkChar++;
                } else {
                    break;
                }
            }
        }
    }
    
    /* (non-Javadoc)
     * @see com.hanbai.jface.base.text.ImeText#removeUndecidedCharacters()
     */
    public void removeUndecidedCharacters() {
        
    	InputControllDocument document = null;
        
        if (getDocument() instanceof InputControllDocument) {
            document = (InputControllDocument)getDocument();
        }
        
        if (document == null){
            return;
        }
        
        int begin = 0;  
        int len   = 0; 

        for(int i = 0; i < document.getLength(); i++) {
            Element e = document.getCharacterElement(i);
            if(e.getAttributes().isDefined(StyleConstants.ComposedTextAttribute)){
                if(len == 0){
                    begin = i;
                }
                len++;
            }
        }
        
        if (len != 0) {
            try {
                document.superRemove(begin, len);

                if(getIMType() == IM_NONE){
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
            } catch (BadLocationException ignore) {
            	ignore.printStackTrace();
            }
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
	public boolean isImeActive(){
		if(imeState!=null){
	        return imeState.isDefined(StyleConstants.ComposedTextAttribute);		
		}else{
			return false;
		}
	}

    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param charString
     * @return
     */
    protected abstract boolean checkMode(String charString);

    /* (non-Javadoc)
     * @see com.hanbai.jface.base.text.ImeText#setEditable(boolean)
     */
    public void setEditable(boolean b) {
        super.setEditable(b);
        if ( b==true ) {
        	setFocusable(true);
            setBackground(ColorConstants.DEFAULT_TEXT_BACKGROUND_COLOR);
        } else {
        	setFocusable(false);
            setBackground(ColorConstants.DEFAULT_TEXT_DISABLE_BACKGROUND_COLOR);            
        }
    }
    
    /* (non-Javadoc)
     * @see java.awt.event.FocusListener#focusLost(java.awt.event.FocusEvent)
     */
    public void focusLost(FocusEvent e) {
    	if (this.isEditable() == true)
    		this.setBackground(ColorConstants.DEFAULT_TEXT_BACKGROUND_COLOR);
    	else
    		this.setBackground(ColorConstants.DEFAULT_TEXT_DISABLE_BACKGROUND_COLOR);

    }
    
    /* (non-Javadoc)
     * @see java.awt.event.FocusListener#focusGained(java.awt.event.FocusEvent)
     */
    public void focusGained(FocusEvent e) {
    	if (this.isEditable() == true) {
    		this.setBackground(ColorConstants.DEFAULT_TEXT_FOCUS_BACKGROUND_COLOR);
    		selectAll();
    	} else {
    		this.setBackground(ColorConstants.DEFAULT_TEXT_DISABLE_BACKGROUND_COLOR);
    	}
    }
}

