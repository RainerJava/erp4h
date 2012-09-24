/** *********************************************************************************
 *     
 *     会社名			：林兼コンピューター株式会社
 *
 *     プロジェクト名	：
 * 
 *     ファイル名		：InspectTableRenderer.java
 *
 *     記述				：
 *     
 *     作成日			：2010/02/22
 *
 *     作成者			：PC12
 *
 *     備考				：
 *
 **************************************************************************************/

package com.fas.report;

import java.awt.Component;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import com.fas.common.constants.screen.ColorConstants;
import com.fas.common.constants.screen.FontConstants;
import com.fas.common.utils.DateUtils;
import com.fas.common.utils.StringUtils;
import com.fas.jface.checkbox.BaseCheckBox;
import com.fas.jface.label.BaseLabel;
import com.fas.jface.label.BaseValueLabel;

/**
 * @author PC12
 * 
 */
public class HaEditableCellRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;


	private int[] align;

	public HaEditableCellRenderer(int[] align) {
		this.align = new int[align.length];
		this.align = align;
	}
		
	public HaEditableCellRenderer() {

	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

		Component renderer = null;

		if (value instanceof String) {
			BaseValueLabel renderString = new BaseValueLabel("" + value);
			renderString.setHorizontalAlignment(SwingConstants.LEFT);
			renderer = renderString;
		} else if (value instanceof Integer) {
			BaseValueLabel renderInteger = new BaseValueLabel("" + value);
			renderInteger.setHorizontalAlignment(SwingConstants.RIGHT);
			renderer = renderInteger;
		} else if (value instanceof Boolean) {
			BaseCheckBox renderCheckBox = new BaseCheckBox(null, (Boolean) value);
			renderCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
			renderer =renderCheckBox;
		}
		else if (value instanceof Date) {
			BaseValueLabel renderDate = new BaseValueLabel(DateUtils.getDateWithSplitYobi((Date) value));
			renderDate.setHorizontalAlignment(SwingConstants.CENTER);
			renderer = renderDate;
		} else if (value instanceof JButton) {
			renderer = (JButton) value;
		} else {
			renderer = new BaseValueLabel("" + value);
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
		
		if (hasFocus){
			renderer.setBackground(ColorConstants.TABLE_CELL_FOCUS_BACKGROUND_COLOR);
		}

		if (align != null && align.length >= column) {
			{				
			((BaseValueLabel) renderer).setHorizontalAlignment(align[column]);
				int x = BaseLabel.RIGHT;
			}
			
		} else {
//			if (StringUtils.isDouble("" + value)) {
//				((BaseValueLabel) renderer).setHorizontalAlignment(BaseLabel.RIGHT);
//			}

			if ("●".equals("" + value) || "★".equals("" + value) || "レ".equals("" + value) || "*".equals("" + value) || "☆".equals("" + value)
				|| "超特急".equals("" + value) || "出来次第".equals("" + value) || "大至急".equals("" + value)) {
				((BaseValueLabel) renderer).setHorizontalAlignment(BaseLabel.CENTER);
			}
			
			String stValue = "" + value;
			String stTemp = StringUtils.subString(stValue, 10);
			// data is "YYYY/MM/DD"
			if (DateUtils.getDateWithyyyymmdd(stTemp) > 0) {
				((BaseValueLabel) renderer).setHorizontalAlignment(BaseLabel.CENTER);
			}
			// data is "HH:MM:SS"
			if (stValue != null) {
				String[] arrStValue = stValue.split(":");
				if (arrStValue.length > 2) {
					((BaseValueLabel) renderer).setHorizontalAlignment(BaseLabel.CENTER);
				}
			}

		}
		
		renderer.setFont(FontConstants.TABLE_TEXT_FONT);

		return renderer;
	}
}

/**
 * <DL>
 *   <DT>クラス記述：chua xong</DT>
 * 		<DD></DD>
 * <BR>
 *
 * @author PC12
 *
 */
class ComboRenderer extends JComboBox implements TableCellRenderer {

	public ComboRenderer() {
		super();
		addItem("Male");
		addItem("Female");
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

		if (isSelected) {
			setForeground(table.getSelectionForeground());
			super.setBackground(table.getSelectionBackground());
		} else {
			setForeground(table.getForeground());
			setBackground(table.getBackground());
		}

		boolean isMale = ((Boolean) value).booleanValue();
		setSelectedIndex(1);
		return this;
	}
}