package com.fas.jface.button;

import javax.swing.JFrame;

import com.fas.common.constants.ApplicationConstants;
import com.fas.common.utils.ApplicationUtils;
import com.fas.jface.MessageBox;

/**
 * @author PC13
 *
 */
public class ExitButton extends ActionButton {

    /** */
	private static final long serialVersionUID = 1L;
	/** */
	private JFrame frame = null;
    /** */
    private String msg = "終了してもよろしいでしょうか？";
    
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public ExitButton(){
	    super("閉じる");
	    addAction(new ExitAction());
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
	public ExitButton(String name){
	    super(name);
	    addAction(new ExitAction());
	}
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param frame
	 */
	public ExitButton(JFrame frame){
	    this();
	    this.frame = frame;
	}

	/**
	 * @author PC13
	 *
	 */
	private class ExitAction implements Action {
		
        public void execute() {
        	boolean flg = true;
        	if (getFrame() != null) {
        		if (MessageBox.confirm(getFrame(), getMsg()) == MessageBox.NO) {
        			flg = false;
        		}
        	}
        	if (flg) {
        		ApplicationUtils.logData(ApplicationConstants.loginUser.getUserId(), ApplicationConstants.MENU_GRP, ApplicationConstants.MENU_EXE, "CL", "");
	            System.exit(0);
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
	public String getMsg() {
		return msg;
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param msg
	 */
	public void setMsg(String msg) {
		this.msg = msg;
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
	public JFrame getFrame() {
		return frame;
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param myFrame
	 */
	public void setFrame(JFrame myFrame) {
		frame = myFrame;
	}
}

