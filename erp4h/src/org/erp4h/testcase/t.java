package org.erp4h.testcase;

import java.awt.EventQueue;
import javax.swing.JFrame;
import org.erp4h.common.utils.XTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;

public class t extends JFrame {
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					t frame = new t();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public t() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 357);
		getContentPane().setLayout(null);
		
		XTextField textField = new XTextField();
		textField.setBounds(114, 85, 211, 20);
		getContentPane().add(textField);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
			},
			new String[] {
				"New column", "New column"
			}
		));
		table.setBounds(118, 136, 314, 183);
		getContentPane().add(table);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(114, 11, 211, 22);
		getContentPane().add(comboBox);
		System.out.println(table.getComponent(0).getClass().getName());
		
	}
}
