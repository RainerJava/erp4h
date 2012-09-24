/** *********************************************************************************
 *     
 *     会社名			：林兼コンピューター株式会社
 *
 *     プロジェクト名	：
 * 
 *     ファイル名		：HaComboCellRenderer.java
 *
 *     記述				：
 *     
 *     作成日			：2010/04/08
 *
 *     作成者			：PC12
 *
 *     備考				：
 *
 **************************************************************************************/

package com.fas.jface.table;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.fas.common.constants.screen.ColorConstants;
import com.fas.common.constants.screen.FontConstants;
import com.fas.jface.label.BaseValueLabel;

/**
 * @author PC12
 * 
 */
public class HaCellComboboxRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;

	public HaCellComboboxRenderer() {
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

		Component renderer = null;

		if (value != null) {
			ArrayList<String> arrData = new ArrayList<String>();
			arrData.add("" + value);
			renderer = new JComboBox(arrData.toArray());
			((JComboBox) renderer).setSelectedIndex(0);
			renderer.setFont(FontConstants.COMBOBOX_FONT);
			((JComponent) renderer).setBorder(null);
		} else {
			renderer = new BaseValueLabel("" + value);
			System.out.println("HaCellComboboxRenderer has something wrong!");
		}

		// カーソル選択のカラー設定
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

		if (hasFocus) {
			renderer.setBackground(ColorConstants.TABLE_CELL_FOCUS_BACKGROUND_COLOR);
		}

		return renderer;
	}
}