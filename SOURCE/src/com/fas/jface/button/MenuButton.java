/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：MenuButton.java
*
*     記述				：
*     
*     作成日			：2009/10/07   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/

package com.fas.jface.button;

import javax.swing.Icon;
import javax.swing.JFrame;

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

public class MenuButton extends ActionButton {

	/** */
	private static final long serialVersionUID = 1L;
	/** */
	private static final String MENU_NAME = "メニュー" ;
	/** */
	private ActionButton actionButton = this;
    /** */
    private String msg = "";
    /** */
    private Action action;
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public MenuButton(){
	    super(MENU_NAME);
	    super.addAction(new goMenuAction(this.action, this.actionButton));
	}
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param icon
	 */
	public MenuButton(Icon icon){
	    super(MENU_NAME);
	    super.addAction(new goMenuAction(this.action, this.actionButton));
	    setIcon(icon);
	}
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param _name
	 */
	public MenuButton(String _name){
	    super(_name);
	    super.addAction(new goMenuAction(this.action, this.actionButton));
	}

	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param icon
	 * @param _name
	 */
	public MenuButton(Icon icon, String _name){
	    super(_name);
	    super.addAction(new goMenuAction(this.action, this.actionButton));
	    setIcon(icon);
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
	 *   <DT>クラス記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * @author PC13
	 *
	 */
	private class goMenuAction implements Action {
		
		/** */
		private Action act;
		/** */
		private ActionButton btn;
		
		/**
		 * <DL>
		 *   <DT>コンストラクター記述：</DT>
		 * 		<DD></DD>
		 * <BR>
		 *
		 * </DL>
		 * @param _act
		 * @param _btn
		 */
		public goMenuAction(Action _act, ActionButton _btn){
			super();
			act = _act;
			btn = _btn;
		}
		

        /* (non-Javadoc)
         * @see com.linc.jface.base.button.Action#execute()
         */
        public void execute() {
        	
        	boolean flg = true;
        	
        	if (act != null) {
        		act.execute();
        	}
        	
        	JFrame frm = SwingUtils.getParentFrame(btn);
        	
        	if (frm != null) {
        		// 変更があった場合、確認メッセージ出力
        		if(frm instanceof BaseFrame){
        			BaseFrame aps = (BaseFrame) frm;
            		if ( aps.getChangeFlg() ) {
            			if (MessageBox.confirm(	frm, getMsg()) != MessageBox.YES) {
                			flg = false;
                		}
            		}
        		}
        	}

        	// メニュー画面への遷移処理
        	if (flg) {
	        	// 履歴情報のクリア
	        	BaseDataManager.remove(BaseDataManager.KEY_FRAME_MOVEMENT);
	        	
	        	// 画面遷移
	        	nextScreen(frm);
        	}
        }
        
    	/**
    	 * <DL>
    	 *   <DT>メソッド記述：</DT>
    	 * 		<DD></DD>
    	 * <BR>
    	 *
    	 * </DL>
    	 * @param _frm
    	 */
    	private void nextScreen(JFrame _frm){
        
    	}        
	}
}

