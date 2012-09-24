/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：ValidateText.java
*
*     記述				：
*     
*     作成日			：2009/10/14   
*
*     作成者			：PC12
*
*     備考				：
*
**************************************************************************************/
package com.fas.jface.text;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFormattedTextField;
import javax.swing.text.DefaultFormatter;
import com.fas.jface.CheckVerifer;
import com.fas.common.constants.screen.ColorConstants;
import com.fas.jface.utils.RequiredImpl;
import com.fas.jface.validate.Validation;


/**
 * <DL>
 *   <DT>クラス記述：</DT>
 * 		<DD></DD>
 * <BR>
 *
 * @author PC13
 *
 */
public class ValidateText extends JFormattedTextField implements Validation {

	/** */
	private static final long serialVersionUID = 1L;
	/** */
	protected  final static int LENGTH = 20;
    /** */
    protected final static int MAX_LENGTH = 20;
	/** */
	private boolean requiredCheck=false;
	/** */
	private RequiredImpl required = new RequiredImpl();
	/** */
	private String message="";
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param mask
	 */
	public ValidateText(DefaultFormatter mask){
	    super(mask);
	    setInputVerifier(new CheckVerifer(this));
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
     * @see com.hanbai.jface.base.validate.Validation#getMessage()
     */
    public String getMessage() {
        return message;
    }
    
    /* (non-Javadoc)
     * @see com.hanbai.jface.base.validate.Validation#setComponent(java.awt.Component)
     */
    public void setComponent(Component componet) {
    }
    
    /* (non-Javadoc)
     * @see com.hanbai.jface.base.validate.Validation#setMessage(java.lang.String)
     */
    public void setMessage(String message){
        this.message=message;
    }
    
    /* (non-Javadoc)
     * @see com.hanbai.jface.base.validate.Validation#getComponent()
     */
    public Component getComponent() {
        return this;
    }
    
	/* (non-Javadoc)
	 * @see com.hanbai.jface.base.validate.Validation#check()
	 */
	public boolean check() {
		
		Object text = getValue();
		boolean check = true;
		
		if(isRequiredCheck()==true){
		    check = required.execute(text,null);
		    if(check==false) {
			    message=required.getMessage();
				return false;
			}
		}
		return true;
	}
	
    /* (non-Javadoc)
     * @see javax.swing.text.JTextComponent#setEditable(boolean)
     */
    public void setEditable(boolean b) {
        super.setEditable(b);
        
        if(b==true) {
        	setFocusable(true);
            setBackground(ColorConstants.DEFAULT_TEXT_BACKGROUND_COLOR);
        } else {
        	setFocusable(false);
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
    
	public void showCalendarDialog() {
	}
}
