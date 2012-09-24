/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名		：
* 
*     ファイル名		：BackButton.java
*
*     記述			：
*     
*     作成日			：2009/10/07   
*
*     作成者			：PC13
*
*     備考			：
*
**************************************************************************************/

package com.fas.jface.button;

import java.lang.reflect.Method;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.fas.jface.BaseCacheManager;
import com.fas.jface.BaseDataManager;
import com.fas.jface.MessageBox;
import com.fas.jface.gui.BaseFrame;
import com.fas.jface.utils.SwingUtils;

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

public class BackButton extends ActionButton {

    /** */
	private static final long serialVersionUID = 1L;
	/** */
    private ActionButton actionButton=this;
    /** */
    private JFrame frame = null;
    /** */
    private String msg = "";
    /** */
    private Class cls = null;
    /** */
    private BackAction bAction;
    /** */
    private Action action;    
    /** */
    private Action afterAction;
    /** */
    private Method refreshMethod = null;
    /** */
    private Object params[] = null;    
	
    /**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param name
	 */
	public BackButton(String name){
	    super(name);
	    bAction = new BackAction();
	    super.addAction(bAction);
	    SwingUtilities.invokeLater(new Runnable(){
    		public void run() {
    			actionButton.requestFocusInWindow();
    		};
    	});
	}
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param name
	 * @param frame
	 */
	public BackButton(String name, JFrame frame){
	    super(name);
	    bAction = new BackAction();
	    super.addAction(bAction);
	    SwingUtilities.invokeLater(new Runnable(){
    		public void run() {
    			actionButton.requestFocusInWindow();
    		};
    	});
	    this.frame = frame;
	}
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param name
	 * @param cls
	 */
	public BackButton(String name, Class cls){
	    super(name);
	    super.addAction(new BackAction());
	    SwingUtilities.invokeLater(new Runnable(){
    		public void run() {
    			actionButton.requestFocusInWindow();
    		};
    	});
	    this.cls = cls;
	}
	
	/* (non-Javadoc)
	 * @see com.linc.jface.base.button.ActionButton#addAction(com.linc.jface.base.button.Action)
	 */
	public void addAction(Action l) {
		this.action=l;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param l
	 */
	public void addAfterAction(Action l) {
		this.afterAction=l;
	}

    
	public void addRefreshMethod(Method refreshMethod, Object objs[]) {
		this.refreshMethod= refreshMethod;
		this.params = objs;
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
	private class BackAction implements Action{
        public void execute() {
        	boolean flg = true;
        	if(action!=null){
        		action.execute();
        	}
        	JFrame now=SwingUtils.getParentFrame(actionButton);
        	if (getFrame() != null) {
        		// 変更があった場合、確認メッセージ出力
        		if(now instanceof BaseFrame){
        			BaseFrame aps = (BaseFrame) now;
            		if ( aps.getChangeFlg() ) {
            			if (MessageBox.confirm(getFrame(), getMsg()) != MessageBox.YES) {
                			flg = false;
                		}
            		}
        		}
        	}
        	
        	if (flg) {
            	if(afterAction != null){
            		afterAction.execute();
            	}

            	if (getCls() == null){
            		setCls(getRootPane().getParent().getClass());
            	}
	            JFrame back = BaseDataManager.getMovement(getCls());
	            
	            back.setVisible(true);
	            back.setEnabled(true);

	            if(now instanceof BaseFrame){
	            	BaseFrame frame = (BaseFrame) now;
	            	if(frame.isCached()){
	            		BaseCacheManager.set(frame);
	            		now.setVisible(false);
	            		return;
	            	}
	            }
	            now.dispose();
	            
	            //最新表示
	            if(refreshMethod != null) {
	            	try{
		            	refreshMethod.invoke(back , params);
	            	}catch (Exception e) {
	            		e.printStackTrace();
					}
	            }
	            
	            ((BaseFrame)back).firstAction();
	            
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
	public JFrame getBackFrame(){
		JFrame back = BaseDataManager.getMovement(getRootPane().getParent().getClass());
		return back;
	}


	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param frame
	 */
	public void setFrame(JFrame frame) {
		this.frame = frame;
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
	public Class getCls() {
		return cls;
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param cls
	 */
	public void setCls(Class cls) {
		this.cls = cls;
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public void execBackAction(){
		bAction.execute();
	}
}

