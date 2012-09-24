/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：BaseDialog.java
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

package com.fas.jface.gui;

import java.awt.Frame;
import java.awt.KeyboardFocusManager;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JDialog;
import javax.swing.KeyStroke;

import com.fas.common.constants.ApplicationConstants;
import com.fas.common.constants.screen.ColorConstants;
import com.fas.common.utils.DateUtils;
import com.fas.jface.BaseDataManager;
import com.fas.sapp.login.LoginFrame;
import com.fas.vo.user.LoginUser;

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

public class BaseDialog extends JDialog {

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
    public BaseDialog() {
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
     * @param owner
     * @param modal
     */
    public BaseDialog(Frame owner,boolean modal) {
        super(owner, modal);
        init();
    }
    /**
     * <DL>
     *   <DT>コンストラクター記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param owner
     * @param modal
     */
    public BaseDialog(JDialog owner,boolean modal) {
        super(owner, modal);
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
    @SuppressWarnings("unchecked")
	private void init() {
        setBackground(ColorConstants.DEFAULT_BACK_COLOR);
        getContentPane().setBackground(ColorConstants.DEFAULT_BACK_COLOR);
      	setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
     // set press key
		Set forwardKeys = getFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS);
		Set newForwardKeys = new HashSet(forwardKeys);
		newForwardKeys.add(KeyStroke.getKeyStroke("shift ENTER"));
		setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, newForwardKeys);
   
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
    protected static boolean checkLogin() {
    	
    	LoginUser user = BaseDataManager.getLoginUser();
    	
    	if (user == null) {
    		return false;
    	} else {
    		return true;
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
    protected static void requestLogin() {
		LoginFrame loginFrame = new LoginFrame();
		loginFrame.pack();
		loginFrame.setVisible(true);
    }
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD> ログイン情報を取得する。</DD>
     * <BR>
     *
     * </DL>
     * @return
     */
    public LoginUser getLoginUser(){
    	return ApplicationConstants.getLoginUser();
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
    public String getSystemDateTime() {
    	return DateUtils.getCurrentDateTime();
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
    protected String getPCName() {
    	if (ApplicationConstants.loginUser != null) {
    		return ApplicationConstants.loginUser.getLoginPC();
    	} else {
    		return "";
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
    protected String getUserName() {
    	if (ApplicationConstants.loginUser != null) {
    		return ApplicationConstants.loginUser.getUserId();
    	} else {
    		return "  ";
    	}
    }
}

