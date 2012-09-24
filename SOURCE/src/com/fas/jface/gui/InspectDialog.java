/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：InspectDialog.java
*
*     記述				：
*     
*     作成日			：2010/02/15   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/
package com.fas.jface.gui;

import java.awt.Container;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;

import com.fas.vo.menuexe.MenuExeVo;

/**
 * @author PC13
 *
 */
public abstract class InspectDialog extends AbstractDialog {

    /** */
	private static final long serialVersionUID = 1L;
	/** */
	protected BasePanel mainPnl;	
	/** */
	protected BasePanel headerMainPnl;
	/** */
	protected BasePanel footerMainPnl;
	/** */
	protected BasePanel lInputPnl;
	/** */
	protected boolean isRequestFocus = true;
	/** */
	protected MenuExeVo exeMenu;

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
    public InspectDialog() {
        super("");
    }
    
    /**
     * <DL>
     *   <DT>コンストラクター記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     */
    public InspectDialog(BaseFrame pFrame, boolean modal) {
    	super(pFrame, modal);
    }
    /**
     * <DL>
     *   <DT>コンストラクター記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     */
    public InspectDialog(BaseDialog dialog, boolean modal) {
    	super(dialog, modal);
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
	 * @return the exeMenu
	 */
	public MenuExeVo getExeMenu() {
		return exeMenu;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param exeMenu the exeMenu to set
	 */
	public void setExeMenu(MenuExeVo exeMenu) {
		this.exeMenu = exeMenu;
	}
	/**
	 * @return the lInputPnl
	 */
	protected BasePanel getlInputPnl() {
		return lInputPnl;
	}
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param com
     */
    protected void setFocusComponent(final JComponent com) {
    	SwingUtilities.invokeLater(new Runnable() {
    		public void run() {
    			com.requestFocus();
    		}
    	});
    }

}
