package com.fas.jface.table;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;

import com.fas.common.constants.screen.ColorConstants;
import com.fas.jface.label.BaseValueLabel;

public class MakinInspectTableRenderer extends InspectTableRenderer {
	private static final long serialVersionUID = 3416370612212050231L;

	public MakinInspectTableRenderer() {
		super();
	}

	public MakinInspectTableRenderer(int[] align) {
		super(align);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		Component renderer = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		if (column == 6) {
			((BaseValueLabel) renderer).setBackground(ColorConstants.CELL_MAKIN_PINK_COLOR);
		}
		
		if (column == 10) {
			((BaseValueLabel) renderer).setBackground(Color.YELLOW);
		}
		return renderer;
	}
}
