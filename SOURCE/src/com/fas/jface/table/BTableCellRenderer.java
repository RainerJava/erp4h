/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：BTableCellRenderer.java
*
*     記述				：
*     
*     作成日			：2010/02/25   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/
package com.fas.jface.table;

import java.awt.Component;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.fas.common.constants.screen.ColorConstants;

/**
 * @author PC13
 *
 */
public class BTableCellRenderer extends DefaultTableCellRenderer {
	
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
	public BTableCellRenderer() {
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.DefaultTableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
	 */
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
		JComponent renderer;
		
		if(value instanceof JComponent == false) {
			Component comp = super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,col);
			if (comp instanceof JComponent) {
				renderer = (JComponent) comp;
			} else {
				return comp;
			}
		} else {
			renderer = (JComponent) value;
		}
		
		if (row != table.getSelectedRow()) {
			if (row % 2 == 0) {
				renderer.setBackground(ColorConstants.TABLE_EVEN_ROW_BACKGROUND_COLOR);
				renderer.setForeground(ColorConstants.TABLE_EVEN_ROW_FORE_COLOR);
			} else {
				renderer.setBackground(ColorConstants.TABLE_ODD_ROW_BACKGROUND_COLOR);
				renderer.setForeground(ColorConstants.TABLE_ODD_ROW_FORE_COLOR);
			}
		}
		
		return renderer;
	}
	
}
