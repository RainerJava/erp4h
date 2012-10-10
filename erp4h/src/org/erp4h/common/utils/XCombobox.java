package org.erp4h.common.utils;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class XCombobox extends JComboBox {
	public XCombobox() {
		super();
	}

	private BasicComboPopup getComboPopup() {
		for (int i = 0, n = getUI().getAccessibleChildrenCount(this); i < n; i++) {
			Object component = getUI().getAccessibleChild(this, i);
			if (component instanceof javax.swing.plaf.basic.BasicComboPopup) {
				return (javax.swing.plaf.basic.BasicComboPopup) component;
			}
		}
		return null;
	}

	public void setPopupComponent(JComponent component) throws Exception {
		BasicComboPopup comboPopup = getComboPopup();
		if (comboPopup != null && comboPopup instanceof JPopupMenu) {
			comboPopup.removeAll();
			comboPopup.add(component);
		} else {
			throw new Exception("asdasd");
		}
	}

	public static void createAndShowGUI() {
		XCombobox combobox = new XCombobox();
		combobox.setEditable(true);

		JPanel popupPanel = new JPanel();
		popupPanel.setBorder(new EmptyBorder(2, 2, 2, 2));
		popupPanel.setLayout(new BorderLayout(0, 0));
		popupPanel.setPreferredSize(new Dimension(500,100));
		System.out.println(popupPanel.getPreferredSize());

		String[] columnNames = { "First Name", "Last Name", "Sport",
				"# of Years", "Vegetarian" };
		Object[][] data = {
				{ "Kathy", "Smith", "Snowboarding", new Integer(5),
						new Boolean(false) },
				{ "John", "Doe", "Rowing", new Integer(3), new Boolean(true) },
				{ "Sue", "Black", "Knitting", new Integer(2),
						new Boolean(false) },
				{ "Jane", "White", "Speed reading", new Integer(20),
						new Boolean(true) },
				{ "Joe", "Brown", "Pool", new Integer(10), new Boolean(false) } };

		TableModel model = new DefaultTableModel(data, columnNames) {
			public Class getColumnClass(int column) {
				Class returnValue;
				if ((column >= 0) && (column < getColumnCount())) {
					returnValue = getValueAt(0, column).getClass();
				} else {
					returnValue = Object.class;
				}
				return returnValue;
			}
		};
		
		JTable table = new JTable(model) {
			public Object getValueAt(int row, int col) {
				return super.getValueAt(row, col);
			}
		};
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
	    table.setRowSorter(sorter);
		JLabel l1 = new JLabel("111");
		JScrollPane jsp = new JScrollPane(table);
		jsp.setBounds(0, 0, 100, 100);

//		 jsp.setBounds(null);
//		 jsp.setBorder(new EmptyBorder(2, 2, 2, 2));
		popupPanel.add(jsp, BorderLayout.CENTER);
		popupPanel.add(l1, BorderLayout.SOUTH);

		try {
			combobox.setPopupComponent(popupPanel);
		} catch (Exception e) {
			e.printStackTrace();
		}

		JPanel demoPanel = new JPanel();
		demoPanel.setBorder(new javax.swing.border.EmptyBorder(20, 20, 20, 20));
		demoPanel.setLayout(new java.awt.GridLayout(1, 2, 5, 5));
		demoPanel.add(new JLabel("Demo: "));
		demoPanel.add(combobox);

		// create and show a window containing the combo box
		final JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(3);
		frame.getContentPane().add(demoPanel);
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}
