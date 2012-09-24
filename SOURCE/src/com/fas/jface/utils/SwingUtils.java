/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：SwingUtils.java
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

package com.fas.jface.utils;

import java.awt.Component;
import java.awt.Rectangle;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import com.fas.common.constants.screen.ColorConstants;
import com.fas.jface.gui.BasePanel;

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

public class SwingUtils {

	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public SwingUtils() {
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param comp
	 * @return
	 */
	public static JFrame getParentFrame(Component comp){
        Component temp=comp;
        JFrame frame=null;
        while(temp!=null){
            if(temp instanceof JFrame){
                frame=(JFrame)temp;
                break;
            }
            temp=temp.getParent();
        }
        return frame;
    }
	
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD>最終行にスクロールする。</DD>
     * <BR>
     *
     * </DL>
     * @param table
     */
    public static void scrollBottom(final JTable table){
    	scrollRow(table, table.getRowCount());
    }

    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD>指定行にスクロールする。</DD>
     * <BR>
     *
     * </DL>
     * @param table
     * @param row
     */
    public static void scrollRow(final JTable table, final int row){
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
	    		Rectangle rec=table.getCellRect(row,0,false);
	    		table.scrollRectToVisible(rec);    				
			};
		});
    }
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD>指定列にスクロールする。</DD>
     * <BR>
     *
     * </DL>
     * @param table
     * @param colmun
     */
    public static void scrollColumn(final JTable table, final int colmun){
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
	    		Rectangle rec=table.getCellRect(0,colmun,false);
	    		table.scrollRectToVisible(rec);    				
			};
		});
    }
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD>指定行、列にスクロールする。</DD>
     * <BR>
     *
     * </DL>
     * @param table
     * @param row
     * @param colmun
     */
    public static void scrollRowColumn(final JTable table, final int row, final int colmun){
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
	    		Rectangle rec=table.getCellRect(row,colmun,false);
	    		table.scrollRectToVisible(rec);    				
			};
		});
    }	
    
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param comp
	 */
	public static void repaintAll(JFrame comp){
        Component[] subcomp = comp.getComponents();
        for (Component obj :  subcomp) {
        	if (obj != null) {
        		obj.repaint();
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
	 * @param comp
	 */
	public static void repaintAll(JDialog comp){
        Component[] subcomp = comp.getComponents();
        for (Component obj :  subcomp) {
        	if (obj != null) {
        		obj.repaint();
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
	 * @param comp
	 */
	public static void repaintPanel(BasePanel comp){
		if (comp != null) {
	        Component[] subcomp = comp.getComponents();
	        comp.setBackground(ColorConstants.PANEL_DEFAULT_COLOR);
	        comp.repaint();
	        for (Component obj :  subcomp) {
	        	if (obj != null) {
	        		if (obj instanceof BasePanel) {
	        			repaintPanel((BasePanel) obj);
	        		} 	
	        	}
	        }
		}
	}

}

