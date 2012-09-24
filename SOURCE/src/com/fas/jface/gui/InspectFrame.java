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
public abstract class InspectFrame extends AbstractFrame {

    /** */
	private static final long serialVersionUID = 1L;
	/** */
	protected BasePanel mainPnl;	
	/** */
	protected BasePanel lInputPnl;
	/** */
	protected BasePanel rInputPnl;
	/** */
	protected BasePanel headerMainPnl;
	/** */
	protected BasePanel footerMainPnl;
	/** */
	protected boolean isRequestFocus = true;
	
	/**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @return
     */
    protected abstract BasePanel getBodyPanel();

    /**
     * <DL>
     *   <DT>コンストラクター記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     */
    public InspectFrame() {
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
    public InspectFrame(BaseFrame pFrame) {
        this("");
        parentFrame = pFrame;
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
    public InspectFrame(String name){
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
    public InspectFrame(String name, Object[] param){
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
	 * @return the lInputPnl
	 */
	protected BasePanel getlInputPnl() {
		return lInputPnl;
	}


	/**
	 * @return the rInputPnl
	 */
	protected BasePanel getrInputPnl() {
		return rInputPnl;
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

