/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：LincJOptionPane.java
*
*     記述				：
*     
*     作成日			：2009/10/08   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/

package com.fas.jface.panel;

import java.awt.Component;
import java.awt.Container;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

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

public class BaseOptionPane extends JOptionPane {

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
	public BaseOptionPane() {
	}

    /* (non-Javadoc)
     * @see javax.swing.JOptionPane#createDialog(java.awt.Component, java.lang.String)
     */
    public JDialog createDialog(Component parentComponent, String title) throws HeadlessException {
    	
    	JDialog pane = super.createDialog(parentComponent, title);
    	
        KeyListener keyListener = new KeyListener()
        {
            public void keyPressed(KeyEvent e)
            {
                if(e.getKeyCode() == KeyEvent.VK_SPACE)
                    e.consume();
                else
                    System.out.println(e.getKeyCode());
            }
            public void keyReleased(KeyEvent e) {}
            public void keyTyped(KeyEvent e) {}
        };
        Component[] components = pane.getComponents();
        for(int i = 0; i < components.length; i++)
        {
            Component[] comps = ((Container)components[i]).getComponents();
            for(int j = 0; j < comps.length; j++)
                if(comps[j] instanceof JButton)
                {
                    JButton button = (JButton)comps[j];
                    button.addKeyListener(keyListener);
                }
        }
        
        return pane;
    }
    
/** デフォルトボーターンため*/    
//    int answer = JOptionPane.showOptionDialog(AnanyaCurves.this, message,
//            "Close Ananya Curves", 0, JOptionPane.WARNING_MESSAGE, null,
//            new String[]{"Yes", "No", "Cancel"}, "No");

    
}

