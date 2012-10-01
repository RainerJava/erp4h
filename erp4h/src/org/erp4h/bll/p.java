package org.erp4h.bll;

import javax.swing.JPanel;
import javax.swing.JTextField;
import org.jdesktop.swingx.JXDatePicker;

public class p extends JPanel {
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the panel.
	 */
	public p() {
		setLayout(null);
		
		textField = new JTextField();
		
		textField.setBounds(276, 21, 150, 20);
		textField.setBorder(null);
		add(textField);
		textField.setColumns(0);
		
		textField_1 = new JTextField();
		textField_1.setBounds(273, 52, 86, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		JXDatePicker datePicker = new JXDatePicker();
		datePicker.setBounds(272, 83, 230, 22);
		add(datePicker);

	}
}
