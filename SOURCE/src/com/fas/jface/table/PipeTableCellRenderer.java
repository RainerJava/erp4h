package com.fas.jface.table;

import java.awt.Component;

import javax.swing.JTable;

import org.jdesktop.swingx.renderer.DefaultTableRenderer;

import com.fas.common.constants.screen.ColorConstants;
import com.fas.common.constants.screen.FontConstants;

public class PipeTableCellRenderer extends DefaultTableRenderer {

	private static final long serialVersionUID = -4959360791350693601L;

	public PipeTableCellRenderer() {
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		Component renderer = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		if (table.isRowSelected(row)) {
			renderer.setBackground(ColorConstants.TABLE_ROW_FOCUS_BACKGROUND_COLOR);
			renderer.setForeground(ColorConstants.TABLE_ROW_FOCUS_FORE_COLOR);
		} else {
			if (row % 2 == 0) {
				renderer.setBackground(ColorConstants.TABLE_EVEN_ROW_BACKGROUND_COLOR);
				renderer.setForeground(ColorConstants.TABLE_EVEN_ROW_FORE_COLOR);
			} else {
				renderer.setBackground(ColorConstants.TABLE_ODD_ROW_BACKGROUND_COLOR);
				renderer.setForeground(ColorConstants.TABLE_ODD_ROW_FORE_COLOR);
			}
		}
//		renderer.setFont(FontConstants.LABEL_TEXT_FONT);
		renderer.setFont(FontConstants.TABLE_TEXT_FONT);
		//Define number of Column USE_FLG to show RED row		
		try
		{
			for(int i = 0; i < table.getColumnCount(); i++)
			{
				if(table.getColumnName(i) == "��")
				{
					Object objUseFlg = table.getValueAt(row, i);
//					if ( (i != column) &&  ("��".equals("" + objUseFlg) || "��".equals("" + objUseFlg) || "��".equals("" + objUseFlg) || "*".equals("" + objUseFlg) || "��".equals("" + objUseFlg)) ) 
					if ( "��".equals("" + objUseFlg) || "��".equals("" + objUseFlg) || "��".equals("" + objUseFlg) || "*".equals("" + objUseFlg) || "��".equals("" + objUseFlg) )
					{
						renderer.setForeground(ColorConstants.TABLE_SHIYOUFUKA_FORE_COLOR);
					}
					break;
				}
			}
		}
		catch(Exception ex)
		{}
		
		return renderer;
	}
}