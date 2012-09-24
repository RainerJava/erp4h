/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：ExitWindow.java
*
*     記述				：
*     
*     作成日			：2009/10/14   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/

package com.fas.jface;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

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

public class ExitWindow extends WindowAdapter {

	/** */
	private JButton btnAction;
	/** */
	private JFrame mainFrame;
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public ExitWindow(JButton btnButton) {
		btnAction = btnButton;
	}

    /* (non-Javadoc)
     * @see java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent)
     */
    public void windowClosing( WindowEvent e ) {  
    	btnAction.doClick();
    	if (mainFrame != null) {
    		mainFrame.requestFocus();
    	}
    }
    
    /* (non-Javadoc)
     * @see java.awt.event.WindowAdapter#windowOpened(java.awt.event.WindowEvent)
     */
    public void windowOpened(WindowEvent e) {
    	if (mainFrame != null) {
    		mainFrame.setFocusableWindowState(true);
    	}
    }

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the mainFrame
	 */
	public JFrame getMainFrame() {
		return mainFrame;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param mainFrame the mainFrame to set
	 */
	public void setMainFrame(JFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
}

