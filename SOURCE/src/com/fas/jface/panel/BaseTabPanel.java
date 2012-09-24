/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：BaseTabPanel.java
*
*     記述				：
*     
*     作成日			：2010/03/01   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/
package com.fas.jface.panel;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTabbedPane;

import com.fas.common.constants.screen.ColorConstants;

/**
 * @author PC13
 *
 */
public class BaseTabPanel extends JTabbedPane {

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
	public BaseTabPanel() {
		super();
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
	private void init() {
		setBackground(ColorConstants.DEFAULT_TABBEDPANE_BACKGROUND_COLOR);
		setForeground(ColorConstants.DEFAULT_TABBEDPANE_FORE_COLOR);
		
		MouseAdapter mouseAdapter = new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent e) {
				getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		};

		this.addMouseListener(mouseAdapter);
	}

}
