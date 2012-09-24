package com.fas.jface.button;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

import com.fas.common.constants.ApplicationConstants;
import com.fas.common.constants.dbtable.MessageConstants;
import com.fas.common.utils.ApplicationUtils;
import com.fas.jface.MessageBox;
import com.fas.jface.gui.InspectFrame;
import com.fas.vo.menuexe.MenuExeVo;

/**
 * @author PC13
 *
 */
public class ExitButtonRef extends ActionButton {

    /** */
	private static final long serialVersionUID = 1L;
	/** */
	private JFrame frame = null;
	/** */
	private JFrame parentFrame = null;
    /** */
    private boolean isConfirmRerquired = false;
    
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public ExitButtonRef(){
	    super("閉じる");
	    initData();
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
	public ExitButtonRef(String name){
	    super(name);
	    initData();
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	private void initData() {
		
	    ExitMasterAction existAction = new ExitMasterAction("existAction");
	    addActionListener(existAction);
    	getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F12"), existAction.getValue(Action.NAME));
    	getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt Q"), existAction.getValue(Action.NAME));
    	getActionMap().put("existAction", existAction);
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
	public ExitButtonRef(JFrame frame){
	    this();
	    this.frame = frame;
	}

	/**
	 * @author PC13
	 *
	 */
	private class ExitMasterAction extends AbstractAction implements Action {
		
		/** */
		private static final long serialVersionUID = 1L;

		public ExitMasterAction(String name) {
		    super(name);
		}
		
		/* (non-Javadoc)
		* @see com.linc.jface.base.button.Action#execute()
		*/
		public void actionPerformed(ActionEvent e) {
			execute();	
		}
		
        /* (non-Javadoc)
         * @see com.pipe.jface.button.Action#execute()
         */
        public void execute() {
        	
        	MenuExeVo exeVo = null;
        	
        	if (frame != null && frame instanceof InspectFrame) exeVo = ((InspectFrame) frame).getExeMenu();
        	
        	if (exeVo == null) exeVo = new MenuExeVo();
        	
        	if (isConfirmRerquired == true) {
	        	
        		boolean flg = true;
	        	
	        	if (getFrame() != null) {
	        		if (MessageBox.message(getFrame(), MessageConstants.getMstMsgVo("Q0001")) == MessageBox.NO) {
	        			flg = false;
	        		}
	        	}
	        	
	        	if (flg) {
		            if (frame != null) {
		            	
		            	ApplicationUtils.logData(ApplicationConstants.loginUser.getUserId(), "100", "100", "CL", "");
		            	frame.dispose();
		            	frame = null;
		            	if (parentFrame != null) {
		            		parentFrame.setEnabled(true);
		            		parentFrame.setExtendedState(JFrame.NORMAL);
		            		parentFrame.toFront();
		            	}
		            }
	        	}
        	} else {
        		ApplicationUtils.logData(ApplicationConstants.loginUser.getUserId(), "100", "100", "CL", "");
	            if (frame != null) {
	            	frame.dispose();
	            	frame = null;
	            	if (parentFrame != null) {
	            		parentFrame.setEnabled(true);
	            		parentFrame.setExtendedState(JFrame.NORMAL);
	            		parentFrame.toFront();
	            	}
	            }
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

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the isConfirmRerquired
	 */
	public boolean isConfirmRerquired() {
		return isConfirmRerquired;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param isConfirmRerquired the isConfirmRerquired to set
	 */
	public void setConfirmRerquired(boolean isConfirmRerquired) {
		this.isConfirmRerquired = isConfirmRerquired;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the parentFrame
	 */
	public JFrame getParentFrame() {
		return parentFrame;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param parentFrame the parentFrame to set
	 */
	public void setParentFrame(JFrame parentFrame) {
		this.parentFrame = parentFrame;
	}
}

