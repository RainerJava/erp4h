package org.erp4h.testcase;

import java.awt.Component;
import java.awt.FlowLayout;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;

/**
 * @author Bart Cremers
 */
public class TableCombo extends JFrame {

	protected void frameInit() {
		super.frameInit();

		setLayout(new FlowLayout());

		JComboBox box = new JComboBox(new Row[] {
				new Row("Row 1", "Row 1 value", "Row 1 Extra"),
				new Row("Row 2", "Row 2 value", "Row 2 Extra"),
				new Row("Row 3", "Row 3 value", "Row 3 Extra"),
				new Row("Row 4", "Row 4 value", "Row 4 Extra"),
				new Row("Row 5", "Row 5 value", "Row 5 Extra"),
				new Row("Row 6", "Row 6 value", "Row 6 Extra"),
				new Row("Row 7", "Row 7 value", "Row 7 Extra"),
				new Row("Row 8", "Row 8 value", "Row 8 Extra"),
				new Row("Row 9", "Row 9 value", "Row 9 Extra"), });

		box.setRenderer(new RowCellRenderer());

		add(box);
	}

	public static void main(String[] args) {
		JFrame f = new TableCombo();
		f.pack();
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(EXIT_ON_CLOSE);
		f.setVisible(true);
	}

	private static class Row {

		private String id, val, extra;

		public Row(String id, String val, String extra) {
			this.id = id;
			this.val = val;
			this.extra = extra;
		}

		public String getId() {
			return id;
		}

		public String getVal() {
			return val;
		}

		public String getExtra() {
			return extra;
		}
	}

	private static class RowCellRenderer extends JTable implements
			ListCellRenderer {

		public RowCellRenderer() {

			setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		}

		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean cellHasFocus) {
			setModel(new RowTableModel((Row) value));
			if (isSelected) {
				getSelectionModel().setSelectionInterval(0, 0);
			}
			return this;
		}
	}

	private static class RowTableModel extends AbstractTableModel {

		private Row row;

		public RowTableModel(Row row) {
			this.row = row;
		}

		public int getRowCount() {
			return 1;
		}

		public int getColumnCount() {
			return 3;
		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			switch (columnIndex) {
			case 0:
				return row.getId();
			case 1:
				return row.getVal();
			case 2:
				return row.getExtra();
			}
			return null;
		}
	}
}
