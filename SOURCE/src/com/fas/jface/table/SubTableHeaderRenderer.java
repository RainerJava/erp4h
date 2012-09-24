/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fas.jface.table;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * 
 * @author Administrator
 */
public class SubTableHeaderRenderer extends JLabel implements TableCellRenderer {

	private static final long serialVersionUID = 6693073105805664679L;

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		return this;
	}
}
