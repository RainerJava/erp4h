/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：LincFrame.java
*
*     記述				：
*     
*     作成日			：2009/10/06   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/

package com.fas.jface.gui;

import java.awt.Container;

import com.fas.common.constants.ApplicationConstants;

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
public abstract class MasterFrame extends AbstractMasterFrame {

    /** */
	private static final long serialVersionUID = 1L;
	/** */
	protected BasePanel pnlCenter = new BasePanel();	
	/** */
	protected BasePanel pnlLeft = new BasePanel();
	/** */
	protected BasePanel pnlRight = new BasePanel();
	/** */
	protected BasePanel headerPnl = new BasePanel();
	/** */
	protected BasePanel footerPnl = new BasePanel();
	/** */
	protected boolean isRequestFocus = true;
    /**
     * <DL>
     *   <DT>コンストラクター記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     */
    public MasterFrame() {
        this("");
    }
    
    /**
     * <DL>
     *   <DT>コンストラクター記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     */
    public MasterFrame(BaseFrame baseFrame) {
        this("");
        parentFrame = baseFrame;
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
    public MasterFrame(String name){
        super(name);
    }
    
    /**
     * <DL>
     *   <DT>コンストラクター記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param name
     * @param param
     */
    public MasterFrame(String name, Object[] param){
        super(name,param);
    }
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param contentPane
     */
    public void setContentPane(Container contentPane) {
    }

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the isRequestFocus
	 */
	public boolean isRequestFocus() {
		return isRequestFocus;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param isRequestFocus the isRequestFocus to set
	 */
	public void setRequestFocus(boolean isRequestFocus) {
		this.isRequestFocus = isRequestFocus;
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
			return "";
		}
	}
}

